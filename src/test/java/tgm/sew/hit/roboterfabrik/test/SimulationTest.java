package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.part.PartType;

//toString Methode hat was
public class SimulationTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	@Test
	public void testGetWarehouser() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		sim.start();
		assertEquals(false, sim.getWarehouser().hasPartPackage());
		;
	}

	@Test
	public void testGetOffice() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		sim.start();
		assertEquals("class tgm.sew.hit.roboterfabrik.Office", sim.getOffice().getClass().toString());
	}

	@Test
	public void testToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		assertEquals("Simulation [duration=100, employeeCount=1, supplierCount=1, warehousePath=" + this.testDir.toString() + ", logFilePath=" + this.logDir.toString() + "]", sim.toString());
	}

	@Test
	public void testParseCLI1() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("help");
		assertEquals(null, sim);
	}

	@Test
	public void testParseCLI2() {
		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "100", "--lieferanten", "1", "--logs", this.logDir.toString(), "--monteure", "1");
		assertEquals("Simulation [duration=100, employeeCount=1, supplierCount=1, warehousePath=" + this.testDir.toString() + ", logFilePath=" + this.logDir.toString() + "]", sim.toString());
	}

	@Test
	public void testParseCLI3() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "0", "--lieferanten", "1", "--logs", this.logDir.toString(), "--monteure", "1");
		assertEquals(null, sim);
	}

	@Test
	public void testParseCLI4() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "1", "--lieferanten", "0", "--logs", this.logDir.toString(), "--monteure", "1");
		assertEquals(null, sim);
	}

	@Test
	public void testParseCLI5() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		Simulation sim = Simulation.parseCLI("--lager", this.testDir.toString(), "--laufzeit", "1", "--lieferanten", "1", "--logs", this.logDir.toString(), "--monteure", "0");
		assertEquals(null, sim);
	}

}
