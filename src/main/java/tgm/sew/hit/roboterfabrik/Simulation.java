package tgm.sew.hit.roboterfabrik;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import tgm.sew.hit.roboterfabrik.supply.Supplier;
import tgm.sew.hit.roboterfabrik.watchdog.Watchable;
import tgm.sew.hit.roboterfabrik.watchdog.Watchdog;

/**
 * Simulation einer Roboterfabrik mit mehreren Arbeiter und Lieferanten.
 * Arbeiter und Lieferanten werden durch mehrere Threads simuliert
 * 
 * @author Rene Hollander
 */
public class Simulation implements Watchable {

	private static final Level LOG_LEVEL = Level.DEBUG;
	private static final Logger LOGGER = (Logger) LogManager.getLogger(Simulation.class);

	static {
		// set the logger to this path until the log path is set
		configureLogger(new File("./startup.log"), LOG_LEVEL);
	}

	private int duration;
	private int employeeCount;
	private int supplierCount;
	private File warehousePath;
	private File logFilePath;

	private ExecutorService threadExecutorService;
	private Watchdog watchdog;

	private Warehouser warehouser;
	private Office office;

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

		// configure logger to ouput to the specified path
		configureLogger(new File(this.logFilePath, "roboterfabrik " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")) + ".log"), LOG_LEVEL);
	}

	/**
	 * Startet die Simulation mit den einstellungen die dem Konstruktor
	 * uebergeben worden sind
	 */
	public void start() {
		LOGGER.info("Starting Simulation " + this.toString() + "!");

		// create new warehouser and Office
		this.warehouser = new Warehouser(this.warehousePath);
		this.office = new Office();

		this.threadExecutorService = Executors.newFixedThreadPool(this.employeeCount + this.supplierCount);
		this.watchdog = new Watchdog(this.duration);
		for (int i = 0; i < this.employeeCount; i++) {
			Employee employee = new Employee(this, this.getOffice().generateWorkerID());
			LOGGER.debug("Creating " + employee.toString());
			this.watchdog.registerWatchable(employee);
			this.threadExecutorService.execute(employee);
		}
		for (int i = 0; i < this.supplierCount; i++) {
			Supplier supplier = new Supplier(this, this.getOffice().generateSupplierID());
			LOGGER.debug("Creating " + supplier.toString());
			this.watchdog.registerWatchable(supplier);
			this.threadExecutorService.execute(supplier);
		}
		this.watchdog.registerWatchable(this);
		this.watchdog.startWatchdog();

		LOGGER.info("Sucessfully started Simulation " + this.toString());
	}

	/**
	 * Stops the running simulation gracefully
	 */
	public void stop() {
		LOGGER.info("Stopping Simulation " + this.toString() + "!");

		this.threadExecutorService.shutdown();
		try {
			this.threadExecutorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error("Error while waiting for termination of the threads", e);
		}
		// close warehouse to release ressources occupied by the random access
		// files
		this.warehouser.close();

		LOGGER.info("Sucessfully stopped Simulation " + this.toString());
	}

	/**
	 * The Warehouser used by the current Simulation
	 * 
	 * @return Warehouser in the current Simulation
	 */
	public Warehouser getWarehouser() {
		return this.warehouser;
	}

	/**
	 * The Office used by the current Simulation
	 * 
	 * @return Office in the current Simulation
	 */
	public Office getOffice() {
		return this.office;
	}

	@Override
	public String toString() {
		return "Simulation [duration=" + duration + ", employeeCount=" + employeeCount + ", supplierCount=" + supplierCount + ", warehousePath=" + warehousePath + ", logFilePath=" + logFilePath + "]";
	}

	/**
	 * Main Methode fuer die Roboterfabrik
	 * 
	 * @param args
	 *            Kommandozeilenargumente
	 */
	public static void main(String[] args) {
		// parse cli args and start simulation
		Simulation sim = parseCLI(args);
		if (sim == null) {
			System.exit(1);
		} else {
			sim.start();
		}
	}

	/**
	 * Parst die Argumente und gibt eine Simulation zurueck
	 * 
	 * @param args
	 *            Argumente um Simulation zu erstellen
	 * @return Simulation aus Argumenten
	 */
	public static Simulation parseCLI(String... args) {
		// parse command line arguments using apache commns cli

		// format the options as a help page
		HelpFormatter helpFormatter = new HelpFormatter();

		// cli parser used to parse the args parameter
		CommandLineParser cliParser = new BasicParser();

		// the options object stores all command line option for later argument
		// parsing
		Options options = new Options();

		// add all the options with a short description
		addOption(options, "lager", "Pfad zum Verzeichnis des Lagers", String.class, true, true);
		addOption(options, "logs", "Pfad zum Verzeichnis der Logs", String.class, true, true);
		addOption(options, "lieferanten", "Anzahl der Lieferanten", Number.class, true, true);
		addOption(options, "monteure", "Anzahl der Monteure", Number.class, true, true);
		addOption(options, "laufzeit", "Laufzeit der Simulation in ms", Number.class, true, true);
		addOption(options, "help", "Hilfeseite", null, false, false);

		// create another options object just to check if help is specified
		Options helpOptions = new Options();
		addOption(helpOptions, "help", "Hilfeseite", null, false, false);

		try {
			// check if cmd has help parameter. if true print help page and exit
			CommandLine cmd = cliParser.parse(helpOptions, args);
			if (cmd.hasOption("help")) {
				printHelp(helpFormatter, options);
				return null;
			}
		} catch (Exception e) {
		}

		try {
			// parse cli args
			// if an error occurs while parsing, print the parse message from
			// the exeption and the help page
			CommandLine cmd = cliParser.parse(options, args);
			if (cmd.hasOption("help")) {
				printHelp(helpFormatter, options);
				return null;
			}

			// read all values from the cli and parse them
			String lagerOptionValue = (String) cmd.getParsedOptionValue("lager");
			String logsOptionValue = (String) cmd.getParsedOptionValue("logs");
			int lieferantenOptionValue = ((Number) cmd.getParsedOptionValue("lieferanten")).intValue();
			int monteureOptionValue = ((Number) cmd.getParsedOptionValue("monteure")).intValue();
			int laufzeitOptionValue = ((Number) cmd.getParsedOptionValue("laufzeit")).intValue();

			// check if lagerDir is not a file and create all direcotries
			// log an error and stop the app if the path is not valid
			File lagerDir = new File(lagerOptionValue);
			lagerDir.mkdirs();
			if (!lagerDir.isDirectory()) {
				LOGGER.error("the lager directory must not be a file");
				printHelp(helpFormatter, options);
				return null;
			}

			// check if loggingDir is not a file and create all direcotries
			// log an error and stop the app if the path is not valid
			File loggingDir = new File(logsOptionValue);
			loggingDir.mkdirs();
			if (!loggingDir.isDirectory()) {
				LOGGER.error("the log directory must not be a file");
				printHelp(helpFormatter, options);
				return null;
			}

			// check if more then 1 suppliers are given
			// if less log to console and stop application
			if ((lieferantenOptionValue >= 1) == false) {
				LOGGER.error("you have to specify more or equal to 1 suppliers");
				printHelp(helpFormatter, options);
				return null;
			}

			// check if more then 1 mechanics are given
			// if less log to console and stop application
			if ((monteureOptionValue >= 1) == false) {
				LOGGER.error("you have to specify more or equal to 1 mechanics");
				printHelp(helpFormatter, options);
				return null;
			}

			// check if the runtime is bigger then 0
			if ((laufzeitOptionValue >= 1) == false) {
				LOGGER.error("you have to specify a longer runtime");
				printHelp(helpFormatter, options);
				return null;
			}

			Simulation sim = new Simulation(laufzeitOptionValue, monteureOptionValue, lieferantenOptionValue, lagerDir, loggingDir);
			return sim;

		} catch (ParseException e) {
			// print error message and help dialog on failure
			LOGGER.error(e.getMessage());
			printHelp(helpFormatter, options);
			return null;
		} catch (Exception e) {
			LOGGER.throwing(e);
			return null;
		}
	}

	private static void printHelp(HelpFormatter helpFormatter, Options options) {
		// print help dialog to console and exit app with given error code
		helpFormatter.printHelp("java - jar roboterfabrik.jar", "Verfuegbare Parameter:", options, null, true);
	}

	private static void addOption(Options options, String opt, String desc, Class<?> type, boolean required, boolean hasArg) {
		// add an option to the given options
		// just a small wrapper around OptionBuilder for easier usage
		OptionBuilder.withDescription(desc);
		if (type != null) {
			// type to cast cli value to
			OptionBuilder.withType(type);
		}
		if (required) {
			// if set to required, an error will occur if the options is not set
			OptionBuilder.isRequired();
		}
		if (hasArg) {
			// specify that the options needs and argument following it
			OptionBuilder.hasArg();
		}
		// build option and add to options
		Option o = OptionBuilder.create(opt);
		options.addOption(o);
	}

	private static void configureLogger(File logFile, Level logLevel) {
		// set logFilename and logLevel property used by log4j2.xml so you can
		// specify it during runtime
		System.setProperty("logFilename", logFile.getAbsolutePath());
		System.setProperty("logLevel", logLevel.toString().toLowerCase());
		// get context from logger and reconfigure it
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.reconfigure();
	}

}
