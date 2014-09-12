package tgm.sew.hit.roboterfabrik;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Simulation einer Roboterfabrik mit mehreren Arbeiter und Lieferanten.
 * Arbeiter und Lieferanten werden durch mehrere Threads simuliert
 * 
 * @author Rene Hollander
 */
public class Simulation {

	private static final Logger LOGGER = LogManager.getLogger(Simulation.class);

	private int duration;
	private int employeeCount;
	private int supplierCount;
	private File warehousePath;
	private File logFilePath;

	/**
	 * Erstellt eine neue Roboterfabrik simulation. Die Simulation wird mit der
	 * start() Methode gestartet
	 * 
	 * @param duration
	 *            Dauer der Simulation. 0 = unendlich
	 * @param employeeCount
	 *            Anzahl der Mitarbeiter die Threadees montieren
	 * @param supplierCount
	 *            Anzahl der Lieferanten die Teile liefern
	 * @param warehousePath
	 *            Pfad zum Ordner fuer das Lager
	 * @param logFilePath
	 *            Pfad zum Log File
	 */
	public Simulation(int duration, int employeeCount, int supplierCount, File warehousePath, File logFilePath) {
		this.duration = duration;
		this.employeeCount = employeeCount;
		this.supplierCount = supplierCount;
		this.warehousePath = warehousePath;
		this.logFilePath = logFilePath;

		this.configureLogger(this.logFilePath);
	}

	private void configureLogger(File logFilePath) {
		System.setProperty("logFilename", logFilePath.getAbsolutePath());
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.reconfigure();
	}

	/**
	 * Startet die Simulation mit den einstellungen die dem Konstruktor
	 * uebergeben worden sind
	 */
	public void start() {

	}

	/**
	 * Main Methode fuer die Roboterfabrik <br/>
	 * Kommandozeilenargumente:
	 * 
	 * @param args
	 *            Kommandozeilenargumente
	 */
	public static void main(String[] args) {

	}

}
