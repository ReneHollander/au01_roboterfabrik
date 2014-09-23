package tgm.sew.hit.roboterfabrik;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Stack;

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
public class BetterWarehouser implements Closeable {

	private static final Logger LOGGER = LogManager.getLogger(BetterWarehouser.class);

	private static final int THRESHOLD_SIZE = 50;

	private File warehousePath;
	private EnumMap<PartType, FastCSV> partFileMap;
	private FastCSV threadeeCsvFile;
	private EnumMap<PartType, Stack<Part>> tempPartStorage;

	/**
	 * Erstellt einen neuen Lagermitarbeiter
	 * 
	 * @param warehousePath
	 *            Pfad zum Lager
	 */
	public BetterWarehouser(File warehousePath) {
		this.warehousePath = warehousePath;
		this.partFileMap = new EnumMap<PartType, FastCSV>(PartType.class);
		this.tempPartStorage = new EnumMap<PartType, Stack<Part>>(PartType.class);
		// Loop through all parttype enum values
		for (PartType partType : PartType.values()) {
			this.tempPartStorage.put(partType, new Stack<Part>());
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
				System.exit(1);
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
			System.exit(1);
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
		Stack<Part> partStack = this.tempPartStorage.get(part.getPartType());
		if (partStack.size() < THRESHOLD_SIZE) {
			partStack.add(part);
			return true;
		} else {
			// get the csv file matching the part type
			FastCSV currentCSV = this.partFileMap.get(part.getPartType());
			try {
				// serialize the part into csv format and write it to disk
				currentCSV.pushRecord(part.getCSVRecord());
				return true;
			} catch (IOException e) {
				LOGGER.error("Error while trying to store supply from supplier", e);
				return false;
			}
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
		ArrayList<Part> partPackage = new ArrayList<Part>();
		for (PartType type : PartType.values()) {
			Stack<Part> partStack = this.tempPartStorage.get(type);
			boolean found = false;
			synchronized (partStack) {
				if (partStack.size() >= type.getAmountForThreadee()) {
					found = true;
					if (type.getAmountForThreadee() == 1) {
						partPackage.add(partStack.pop());
					} else {
						for (int i = 0; i < type.getAmountForThreadee(); i++) {
							partPackage.add(partStack.pop());
						}
					}
				} else {
					found = true;
				}
			}
			if (found == false) {
				FastCSV csvFile = this.partFileMap.get(type);
				synchronized (csvFile) {
					if (partStack.size() < THRESHOLD_SIZE / 5) {
						for (int i = 0; i < THRESHOLD_SIZE; i++) {
							try {
								FastCSVRecord record = csvFile.popRecord();
								if (record != null) {
									partStack.add(Part.readFromCSV(record));
								} else {
									if (partStack.size() == 0)
										return null;
									break;
								}
							} catch (IOException e) {
								LOGGER.error("Error while trying to read a csv record from file", e);
							}
						}
					}
					if (type.getAmountForThreadee() == 1) {
						partPackage.add(partStack.pop());
					} else {
						for (int i = 0; i < type.getAmountForThreadee(); i++) {
							partPackage.add(partStack.pop());
						}
					}
				}
			}
		}
		return partPackage;
	}

	/**
	 * Wenn eine Arbeiter einen Threadee fergi gebaut hat, uebergibt er den
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
			LOGGER.debug("Successfully stored " + threadee.toString());
			return true;
		} catch (IOException e) {
			LOGGER.error("Error while trying to store assembled " + threadee.toString(), e);
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
