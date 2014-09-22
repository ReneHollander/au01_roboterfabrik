package tgm.sew.hit.roboterfabrik;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supply;
import at.rene8888.fastcsv.FastCSV;

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
		this.partFileMap = new EnumMap<PartType, FastCSV>(PartType.class);
		// Loop through all parttype enum values
		for (PartType partType : PartType.values()) {
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

	/**
	 * Ein Arbeiter kann mithilfe dieser Methode eine Teil aus dem Lager
	 * "bestellen". Der Lagermitarbeiter holt auf Anfrage das Teil aus dem Lager
	 * und uebergibt es dem Arbeiter
	 * 
	 * @param partType
	 *            Typ des Teiles das der Arbeiter will
	 * @return Teil aus dem Lager
	 */
	public Part getPart(PartType partType) {
		return null;
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
			return true;
		} catch (IOException e) {
			LOGGER.error("Error while trying to store assembled threadee", e);
			return false;
		}
	}

	public void close() {
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
