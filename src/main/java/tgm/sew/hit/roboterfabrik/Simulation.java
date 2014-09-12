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

	public void start() {

	}

	public static void main(String[] args) {

	}

}
