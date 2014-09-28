package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Employee;
import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.Threadee;

public class ThreadeeTest {

	private final File testDir = new File("./workingdir/test");
	private final File logDir = new File("./workingdir/log");

	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	@Test
	public void testCreateThreadee() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);
	}

	@Test
	public void testGetThreadeeID() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);

		assertEquals(1, t.getID());
	}

	@Test
	public void testGetThreadeeCreator() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);

		assertEquals(e, t.getCreator());
	}

	/**@Test
	public void testGetThreadeeParts() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();
		List<Part> parts;

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, parts);

		assertEquals(, t.getParts());
	}**/
	
	@Test
	public void testThreadeeCSV() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);
		
		assertEquals(1, t.getID());
	}
}
