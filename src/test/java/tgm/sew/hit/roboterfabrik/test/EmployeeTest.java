package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Employee;
import tgm.sew.hit.roboterfabrik.Simulation;

/**
 * Testet den Employee
 * 
 * @author Rene Hollander, Samuel Schober
 *
 */
public class EmployeeTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	/**
	 * Bevor der nächste Test ausgeführt wird sollten die Test Ordner
	 * zurueckgesetzt werden
	 */
	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	/**
	 * Testet ob ein Employee erstellt und gestoppt werden kann
	 */
	@Test
	public void testCreateEmployee() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		e.stop();
	}

	/**
	 * Testet die getID Methode eines Employee
	 */
	@Test
	public void testEmployeeGetID() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(1, e.getID());
	}

	/**
	 * Testet die toString Methode eines employee
	 */
	@Test
	public void testEmployeeToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals("Employee [id=1]", e.toString());
	}

	/**
	 * Testet die hashCode Methode eines Employee
	 */
	@Test
	public void testEmployeeHashCode() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(32, e.hashCode());
	}

	/**
	 * Testet ob ein und der selbe Employee gleich sind
	 */
	@Test
	public void testEmployeeEquals1() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(true, e.equals(e));
	}

	/**
	 * Testet ob 2 Employees equal sind wenn beide die selbe id besitzen
	 */
	@Test
	public void testEmployeeEquals2() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e1 = new Employee(sim, 1);
		Employee e2 = new Employee(sim, 1);
		assertEquals(true, e1.equals(e2));
	}

	/**
	 * Testet ob ein Employee nicht equal null ist
	 */
	@Test
	public void testEmployeeEqualsNull() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(false, e.equals(null));
	}

	/**
	 * Testet ob 2 unterschiedliche Employees nicht gleich sind
	 */
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

	/**
	 * Testet ob der Employee keinem String entpricht
	 */
	@Test
	public void testEmployeeNotEquals2() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		assertEquals(false, e.equals("Ein String"));
	}
}
