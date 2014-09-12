package tgm.sew.hit.roboterfabrik;

import java.io.RandomAccessFile;
import java.util.EnumMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supply;

/**
 * Der Lagermitarbeiter verwaltet das Lager (Dateien). Jede Lieferung, Anfrage
 * fuer Bauteile und das lagern der fertigen Threadees uebernimmt der
 * Lagermitarbeiter um Probleme mit der Nebenlauufigkeit zu unterbinden.
 * 
 * @author Rene Hollander
 */
public class Warehouser {

	private static final Logger LOGGER = LogManager.getLogger(Warehouser.class);

	private EnumMap<PartType, RandomAccessFile> partFileMap;

	/**
	 * Der Lieferant uebergibt mit dieser Methode dem Lagermitarbeiter seine
	 * Lieferung. Der Lagermitarbeiter speichert diese dann in einer Datei.
	 * 
	 * @param supply
	 *            Lieferung die gelagert werden soll
	 * @return gibt true zurueck, wenn die Lieferung erfolgreich gelagert wurde
	 */
	public boolean storeSupply(Supply supply) {
		return false;
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
		return false;
	}

}
