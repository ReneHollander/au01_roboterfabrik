package tgm.sew.hit.roboterfabrik;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supply;
import at.rene8888.fastcsv.FastCSV;
import at.rene8888.fastcsv.FastCSVRecord;

/**
 * Der Lagermitarbeiter verwaltet das Lager (Dateien). Jede Lieferung, Anfrage
 * fuer Bauteile und das lagern der fertigen Threadees uebernimmt der
 * Lagermitarbeiter um Probleme mit der Nebenlauufigkeit zu unterbinden.
 * 
 * @author Rene Hollander
 */
public class Warehouser implements Closeable {

	private static final Logger LOGGER = LogManager.getLogger(Warehouser.class);

	private File warehousePath;
	private EnumMap<PartType, IntegerWrapper> partCountMap;
	private Lock partCountMapLock;
	private EnumMap<PartType, FastCSV> partFileMap;
	private FastCSV threadeeCsvFile;

	/**
	 * Erstellt einen neuen Lagermitarbeiter
	 * 
	 * @param warehousePath
	 *            Pfad zum Lager
	 */
	public Warehouser(File warehousePath) {
		this.warehousePath = warehousePath;
		this.partCountMap = new EnumMap<PartType, IntegerWrapper>(PartType.class);
		this.partCountMapLock = new ReentrantLock();
		this.partFileMap = new EnumMap<PartType, FastCSV>(PartType.class);
		// Loop through all parttype enum values
		for (PartType partType : PartType.values()) {
			this.partCountMap.put(partType, new IntegerWrapper(0));
			try {
				// create file for the current parttype
				File partFile = new File(this.warehousePath, partType.getFilename() + ".csv");
				// if the file exists, truncate the contents
				if (partFile.exists()) {
					new FileOutputStream(partFile).getChannel().truncate(0).close();
				}
				// create fastcsv object and add to enum map
				this.partFileMap.put(partType, new FastCSV(partFile));
			} catch (Exception e) {
				// if an error occurs, log to console and terminate apps
				LOGGER.fatal("Error while trying to create Lager file for " + partType.getName(), e);
			}
		}
		try {
			// create file for the assembled threadees
			File threadeeFile = new File(this.warehousePath, "auslieferung.csv");
			// if the file exists, truncate the contents
			if (threadeeFile.exists()) {
				new FileOutputStream(threadeeFile).getChannel().truncate(0).close();
			}
			// create fastcsv object
			this.threadeeCsvFile = new FastCSV(threadeeFile);
		} catch (Exception e) {
			// if an error occurs, log to console and terminate apps
			LOGGER.fatal("Error while trying to create Lager file for Threadees", e);
		}
	}

	/**
	 * Der Lieferant uebergibt mit dieser Methode dem Lagermitarbeiter seine
	 * Lieferung. Der Lagermitarbeiter speichert diese dann in einer Datei.
	 * 
	 * @param supply
	 *            Lieferung die gelagert werden soll
	 * @return gibt true zurueck, wenn die Lieferung erfolgreich gelagert wurde
	 */
	public boolean storeSupply(Supply supply) {
		Part part = supply.getPart();
		FastCSV currentCSV = this.partFileMap.get(part.getPartType());
		try {
			// serialize the part into csv format and write it to disk
			currentCSV.pushRecord(part.getCSVRecord());
			// increment the counter for the corresponding part by 1
			this.partCountMap.get(part.getPartType()).add(1);
			return true;
		} catch (Exception e) {
			LOGGER.error("Error while trying to store supply from supplier", e);
			return false;
		}
	}

	/**
	 * Ein Arbeiter kann mithilfe dieser Methode eine Teil aus dem Lager
	 * "bestellen". Der Lagermitarbeiter holt auf Anfrage das Teil aus dem Lager
	 * und uebergibt es dem Arbeiter
	 * 
	 * @return Teil aus dem Lager
	 */
	public ArrayList<Part> getPartPackage() {
		try {
			// check if all parts available
			if (this.hasPartPackage()) {
				ArrayList<Part> partPackage = new ArrayList<Part>();
				// loop through all PartType
				for (PartType type : PartType.values()) {
					FastCSV csvFile = this.partFileMap.get(type);
					// decrement the part counter by the needed amount of parts
					this.partCountMap.get(type).add(-type.getAmountForThreadee());
					// if we need just 1 Part, we dont go throug the loop
					if (type.getAmountForThreadee() == 1) {
						// pop the record from the file, deserialize the part
						// and add it to the package
						// if the record is null, throw an exception to catch it
						// later
						FastCSVRecord record = csvFile.popRecord();
						if (record == null) {
							LOGGER.error("a needed part could not be found!");
							return null;
						}
						partPackage.add(Part.readFromCSV(record));
					} else {
						// if we need more than 1 of a part, get the correct
						// amount and add it to the package
						for (int i = 0; i < type.getAmountForThreadee(); i++) {
							FastCSVRecord record = csvFile.popRecord();
							if (record == null) {
								LOGGER.error("a needed part could not be found!");
								return null;
							}
							partPackage.add(Part.readFromCSV(record));
						}
					}
				}
				return partPackage;
			} else {
				return null;
			}
		} catch (Exception e) {
			// if an error occurs, print it and return null
			LOGGER.error("Error while trying to read parts for a PartPackage", e);
			return null;
		}
	}

	/**
	 * Ueberprueft ob genug Teile fuer einen Threadee vorhanden sind
	 * 
	 * @return true wenn alle benoetigten Teile da sind
	 */
	public boolean hasPartPackage() {
		try {
			// loop through all parts and check if the correct amount is
			// available
			// if there is just 1 part missing, return false
			this.partCountMapLock.lock();
			for (PartType type : PartType.values()) {
				// simply check the counter if there is enough parts left
				if (this.partCountMap.get(type).get() < type.getAmountForThreadee()) {
					this.partCountMapLock.unlock();
					return false;
				}
			}
			this.partCountMapLock.unlock();
			return true;
		} catch (Exception e) {
			LOGGER.error("Error while trying to check if the PartPackage is available", e);
			return false;
		}
	}

	/**
	 * Wenn eine Arbeiter einen Threadee fertig gebaut hat, uebergibt er den
	 * Threadee dem Lagermitarbeiter. Dieser lager den Threadee in der Datei
	 * ein.
	 * 
	 * @param threadee
	 *            Threadee der gelagert werden soll
	 * @return gibt true zurueck, wenn der Threadee erfolgreich gelagert wurde
	 */
	public boolean storeThreadee(Threadee threadee) {
		try {
			// serialize the assembled threadee into csv format and write it to
			// disk
			threadeeCsvFile.pushRecord(threadee.getCSVRecord());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Successfully stored " + threadee.toString());
			}
			return true;
		} catch (Exception e) {
			LOGGER.error("Error while trying to store assembled " + threadee, e);
			return false;
		}
	}

	public synchronized void close() {
		for (Map.Entry<PartType, FastCSV> entry : this.partFileMap.entrySet()) {
			try {
				entry.getValue().close();
			} catch (Exception e) {
				LOGGER.error("Error while trying to close part file " + entry.getKey().getName(), e);
			}
		}
		try {
			this.threadeeCsvFile.close();
		} catch (Exception e) {
			LOGGER.error("Error while trying to close threadee file", e);
		}
	}
}
