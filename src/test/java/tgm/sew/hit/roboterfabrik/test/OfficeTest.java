package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Office;

/**
 * Testet das Sekreteriat
 * 
 * @author Rene Hollander
 */
public class OfficeTest {

	/**
	 * Testet ob die nächste und erste ID gültig ist
	 */
	@Test
	public void testMitarbeiterId() {
		Office o = new Office();
		assertEquals(1, o.generateThreadeeID());
	}

	/**
	 * Testet ob die nächste und erste ID gültig ist
	 */
	@Test
	public void testWorkerId() {
		Office o = new Office();
		assertEquals(1, o.generateWorkerID());
	}

	/**
	 * Testet ob die nächste und erste ID gültig ist
	 */
	@Test
	public void testSupplierId() {
		Office o = new Office();
		assertEquals(1, o.generateSupplierID());
	}
}
