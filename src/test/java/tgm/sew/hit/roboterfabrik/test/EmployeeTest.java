package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Employee;
import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.supply.Supplier;

public class EmployeeTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	@Test
	public void testCreateEmployee() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		e.stop();
	}

	@Test
	public void testCreateEmployeeIfExists() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Employee e2 = new Employee(sim, 2);
		e.stop();
		e2.stop();
	}

	@Test
	public void testEmployeeGetID() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(1, e.getID());
	}

	@Test
	public void testEmployeeToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals("Employee [id=1]", e.toString());
	}

	@Test
	public void testEmployeeHashCode() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		e.hashCode();
	}

	@Test
	public void testEmployeeEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(true, e.equals(e));
	}

	@Test
	public void testEmployeeEqualsNull() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(false, e.equals(null));
	}

	@Test
	public void testEmployeeNotEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Simulation sim2 = new Simulation(200, 21, 21, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Employee e2 = new Employee(sim2, 2);
		assertEquals(false, e.equals(e2));
	}
}
