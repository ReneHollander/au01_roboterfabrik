package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supplier;
import tgm.sew.hit.roboterfabrik.supply.Supply;

public class SupplierTest {

	private final File testDir = new File("./workingdir/test");
	private final File logDir = new File("./workingdir/log");

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
	public void testSupplierRun() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);
		final Part p = new Part(PartType.EYE, ar);
		final Supply sup = new Supply(p);

		Supplier s = new Supplier(sim, 1);
		s.run();
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

	@Test
	public void testSupplierEqualsOtherSupplier() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		Supplier s2 = new Supplier(sim, 2);
		assertEquals(false, s.equals(s2));
	}
}
