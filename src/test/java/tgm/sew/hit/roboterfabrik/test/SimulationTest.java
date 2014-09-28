package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.part.PartType;

//toString Methode hat was
public class SimulationTest {

	private final File testDir = new File("./workingdir/test");
	private final File logDir = new File("./workingdir/log");

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
		System.out.println("!!!!!" + sim.toString());
		assertEquals("Simulation [duration=100, employeeCount=1, supplierCount=1, warehousePath=./workingdir/test, logFilePath=./workingdir/log]", sim.toString());
	}

	@Test
	public void testHashCode() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		final int prime = 31;
		int result = 1;
		result = prime * result + 100;
		result = prime * result + 1;
		result = prime * result + ((logDir == null) ? 0 : logDir.hashCode());
		result = prime * result + 1;
		result = prime * result + ((testDir == null) ? 0 : testDir.hashCode());
		assertEquals(result, sim.hashCode());
	}

	@Test
	public void testEquals1() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		assertEquals(true, sim.equals(sim));
	}

	@Test
	public void testEquals2() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		assertEquals(false, sim.equals("test"));
	}

	@Test
	public void testEquals3() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();
		PartType p = PartType.EYE;

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		assertEquals(false, sim.equals(p));
	}

	@Test
	public void testEquals4() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		assertEquals(false, sim.equals(Simulation.class));
	}

	@Test
	public void testEquals5() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Simulation sim2 = new Simulation(50, 1, 1, this.testDir, this.logDir);
		assertEquals(false, sim.equals(sim2));
	}

	@Test
	public void testEquals6() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Simulation sim2 = new Simulation(50, 2, 1, this.testDir, this.logDir);
		assertEquals(false, sim.equals(sim2));
	}

	@Test
	public void testEquals7() {

		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Simulation sim2 = new Simulation(50, 1, 2, this.testDir, this.logDir);
		assertEquals(false, sim.equals(sim2));
	}

	@Test
	public void testEquals8() {

		final File testDir2 = new File("./workingdir/test2");

		this.testDir.mkdirs();
		this.logDir.mkdirs();
		testDir2.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Simulation sim2 = new Simulation(50, 1, 2, testDir2, this.logDir);
		assertEquals(false, sim.equals(sim2));
	}

	@Test
	public void testEquals9() {

		final File logDir2 = new File("./workingdir/log2");

		this.testDir.mkdirs();
		this.logDir.mkdirs();
		logDir2.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Simulation sim2 = new Simulation(50, 1, 2, this.testDir, logDir2);
		assertEquals(false, sim.equals(sim2));
	}
}
