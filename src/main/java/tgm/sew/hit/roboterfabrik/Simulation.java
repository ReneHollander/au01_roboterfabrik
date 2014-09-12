package tgm.sew.hit.roboterfabrik;

import java.io.File;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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

	static {
		// set the logger to this path until the log path is set
		configureLogger(new File("./startup.log"));
	}

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

		configureLogger(new File(this.logFilePath, "roboterfabrik.log"));
	}

	/**
	 * Startet die Simulation mit den einstellungen die dem Konstruktor
	 * uebergeben worden sind
	 */
	public void start() {
		LOGGER.info(this.toString() + " started!");
	}

	@Override
	public String toString() {
		return "Simulation [duration=" + duration + ", employeeCount=" + employeeCount + ", supplierCount=" + supplierCount + ", warehousePath=" + warehousePath + ", logFilePath=" + logFilePath + "]";
	}

	/**
	 * Main Methode fuer die Roboterfabrik <br>
	 * Kommandozeilenargumente:
	 * 
	 * @param args
	 *            Kommandozeilenargumente
	 */
	public static void main(String[] args) {
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
				printHelpAndExit(helpFormatter, options, 0);
			}
		} catch (Exception e) {

		}

		try {
			// parse cli args
			// if an error occurs while parsing, print the parse message from
			// the exeption and the help page
			CommandLine cmd = cliParser.parse(options, args);
			if (cmd.hasOption("help")) {
				printHelpAndExit(helpFormatter, options, 0);
			}
			String lagerOptionValue = (String) cmd.getParsedOptionValue("lager");
			String logsOptionValue = (String) cmd.getParsedOptionValue("logs");
			int lieferantenOptionValue = ((Number) cmd.getParsedOptionValue("lieferanten")).intValue();
			int monteureOptionValue = ((Number) cmd.getParsedOptionValue("monteure")).intValue();
			int laufzeitOptionValue = ((Number) cmd.getParsedOptionValue("laufzeit")).intValue();

			File lagerDir = new File(lagerOptionValue);
			lagerDir.mkdirs();
			if (!lagerDir.isDirectory()) {
				LOGGER.error("the lager directory must not be a file");
				printHelpAndExit(helpFormatter, options, 1);
			}

			File loggingDir = new File(logsOptionValue);
			loggingDir.mkdirs();
			if (!loggingDir.isDirectory()) {
				LOGGER.error("the log directory must not be a file");
				printHelpAndExit(helpFormatter, options, 1);
			}

			if ((lieferantenOptionValue >= 1) == false) {
				LOGGER.error("you have to specify more or equal to 1 suppliers");
				printHelpAndExit(helpFormatter, options, 1);
			}

			if ((monteureOptionValue >= 1) == false) {
				LOGGER.error("you have to specify more or equal to 1 mechanics");
				printHelpAndExit(helpFormatter, options, 1);
			}

			try {
				Simulation sim = new Simulation(laufzeitOptionValue, monteureOptionValue, lieferantenOptionValue, lagerDir, loggingDir);
				sim.start();
			} catch (Exception e) {
				LOGGER.fatal("A fatal error occured while executing simulation", e);
				System.exit(1);
			}

		} catch (ParseException e) {
			// print error message and help dialog on failure
			LOGGER.error(e.getMessage());
			helpFormatter.printHelp("java - jar roboterfabrik.jar", "Verfuegbare Parameter:", options, null, true);
			System.exit(1);
		} catch (Exception e) {
			LOGGER.throwing(e);
		}
	}

	private static void printHelpAndExit(HelpFormatter helpFormatter, Options options, int exitValue) {
		helpFormatter.printHelp("java - jar roboterfabrik.jar", "Verfuegbare Parameter:", options, null, true);
		System.exit(exitValue);
	}

	private static void addOption(Options options, String opt, String desc, Class<?> type, boolean required, boolean hasArg) {
		OptionBuilder.withDescription(desc);
		if (type != null) {
			OptionBuilder.withType(type);
		}
		if (required) {
			OptionBuilder.isRequired();
		}
		if (hasArg) {
			OptionBuilder.hasArg();
		}
		Option o = OptionBuilder.create(opt);
		options.addOption(o);
	}

	private static void configureLogger(File logFile) {
		System.setProperty("logFilename", logFile.getAbsolutePath());
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.reconfigure();
	}
}
