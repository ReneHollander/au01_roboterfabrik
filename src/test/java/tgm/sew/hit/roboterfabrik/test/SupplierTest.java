package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supplier;

/**
 * Testet den Supplier
 * 
 * @author Samuel
 */
public class SupplierTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	/**
	 * Loescht die Files bevor sie neu erstellt werden
	 */
	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	/**
	 * Testet ob ein Supplier erstellt werden kann
	 */
	@Test
	public void testCreateSupplier() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		s.stop();
	}

	/**
	 * Testet ob mehrere Supplier gleichzeitig laufen koennen
	 */
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

	/**
	 * Testet ob die Methode toString einen richtigen Wert ausgibt
	 */
	@Test
	public void testSupplierToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		String st = s.toString();
		assertEquals(st, s.toString());
	}

	/**
	 * Testet ob hashCode den richtigen Hash Code generiert
	 */
	@Test
	public void testSupplierHashCode() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		int hc = ((s == null) ? 0 : s.hashCode());
		assertEquals(hc, s.hashCode());
	}

	/**
	 * Testet ob der Supplier gleich dem Supplier ist
	 */
	@Test
	public void testSupplierEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		assertEquals(true, s.equals(s));
	}

	/**
	 * Testet ob der Supplier mit einer falschen Eingabe gleich ist
	 */
	@Test
	public void testSupplierNotEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		assertEquals(false, s.equals("Hallo Welt"));
	}

	/**
	 * Testet ob ein Supplier gleich mit null ist
	 */
	@Test
	public void testSupplierEqualsNull() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Supplier s = new Supplier(sim, 1);
		assertEquals(false, s.equals(null));
	}

	/**
	 * Testet ob zwei komplett unterschiedliche Supplier gleich sind
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
	 * Testet ob zwei Supplier bei denen sich nur die ID unterscheidet gleich
	 * sind
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
	 * Testet ob zwei Supplier gleich sind
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
