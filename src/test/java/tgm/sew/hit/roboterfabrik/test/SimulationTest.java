package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;

/**
 * Testfaelle fuer Simulation
 * 
 * @author Simon Wortha & Rene Hollander
 *
 */
public class SimulationTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	/**
	 * Bevor der naechste Test ausgefuehrt wird sollten die Test Ordner
	 * zurueckgesetzt werden
	 */
	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	/**
	 * Testet ob getWarehouser auch einen Warehouser zurueck liefert mit dem
	 * weiter gearbeitet werden kann
	 */
	@Test
	public void testGetWarehouser() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		sim.start();
		assertEquals(false, sim.getWarehouser().hasPartPackage());
	}

	/**
	 * Test ob getOffice auch ein Office zurueck liefert mit dem weiter
	 * gearbeitet werden kann
	 */
	@Test
	public void testGetOffice() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		sim.start();
		assertEquals("class tgm.sew.hit.roboterfabrik.Office", sim.getOffice().getClass().toString());
	}

	/**
	 * Testet ob die toString Methode richtig arbeitet, und den gewuenschten
	 * String zurueck liefert
	 */
	@Test
	public void testToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		assertEquals("Simulation [duration=100, employeeCount=1, supplierCount=1, warehousePath=" + this.testDir.toString() + ", logFilePath=" + this.logDir.toString() + "]", sim.toString());
	}

	/**
	 * Testet die ein Eingabe des help cli argument
	 */
	@Test
	public void testParseCLI1() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--help");
		assertEquals(null, sim);
	}

	/**
	 * Testet ob die cli args richtig eingelesen werden (fehlerfreie args)
	 */
	@Test
	public void testParseCLI2() {
		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "100", "--lieferanten", "1", "--logs", this.logDir.toString(), "--monteure", "1");
		assertEquals("Simulation [duration=100, employeeCount=1, supplierCount=1, warehousePath=" + this.testDir.toString() + ", logFilePath=" + this.logDir.toString() + "]", sim.toString());
	}

	/**
	 * Testet ob die Laufzeit groesser als 0 sein muss
	 */
	@Test
	public void testParseCLI3() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "0", "--lieferanten", "1", "--logs", this.logDir.toString(), "--monteure", "1");
		assertEquals(null, sim);
	}

	/**
	 * Testet ob die Anzahl der Lieferanten groesser als 0 sein muss
	 */
	@Test
	public void testParseCLI4() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "1", "--lieferanten", "0", "--logs", this.logDir.toString(), "--monteure", "1");
		assertEquals(null, sim);
	}

	/**
	 * Testet ob die Anzahl der Monteure groesser als 0 sein muss
	 */
	@Test
	public void testParseCLI5() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "1", "--lieferanten", "1", "--logs", this.logDir.toString(), "--monteure", "0");
		assertEquals(null, sim);
	}

}
