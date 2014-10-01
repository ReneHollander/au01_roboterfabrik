package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supplier;

public class SupplierTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	@Test
	public void testCreateSupplier() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.stop();
	}

	@Test
	public void testCreateSupplierIfExist() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		Supplier s2 = new Supplier(sim, 2);
		s.stop();
		s2.stop();
	}

	@Test
	public void testSupplierToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.toString();
	}

	@Test
	public void testSupplierHashCode() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.hashCode();
	}

	@Test
	public void testSupplierEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		assertEquals(true, s.equals(s));
	}

	@Test
	public void testSupplierNotEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		assertEquals(false, s.equals("Hallo Welt"));
	}

	@Test
	public void testSupplierEqualsNull() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		Supplier s2 = new Supplier(sim, 2);
		assertEquals(false, s.equals(null));
	}

	/**
	 * Testet ob 2 komplett unterschiedliche Supplier nicht gleich sind
	 */
	@Test
	public void testSupplierEqualsOtherSupplier1() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.setNextPartType(PartType.ARM);
		Supplier s2 = new Supplier(sim, 2);
		s2.setNextPartType(PartType.EYE);
		assertEquals(false, s.equals(s2));
	}

	/**
	 * Testet ob 2 Supplier bei denen sich nur die ID unterscheidet gleich sind
	 */
	@Test
	public void testSupplierEqualsOtherSupplier2() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.setNextPartType(PartType.EYE);
		Supplier s2 = new Supplier(sim, 2);
		s2.setNextPartType(PartType.EYE);
		assertEquals(false, s.equals(s2));
	}

	/**
	 * Testet 2 Supplier ob sie gleich sind
	 */
	@Test
	public void testSupplierEqualsOtherSupplier3() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.setNextPartType(PartType.EYE);
		Supplier s2 = new Supplier(sim, 1);
		s2.setNextPartType(PartType.EYE);
		assertEquals(true, s.equals(s2));
	}

}
