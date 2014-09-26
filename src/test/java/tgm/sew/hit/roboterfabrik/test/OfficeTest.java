package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Office;

public class OfficeTest {

	@Test
	public void testMitarbeiterId() {
		Office o = new Office();
		assertEquals(1, o.generateThreadeeID());
	}

	@Test
	public void testWorkerId() {
		Office o = new Office();
		assertEquals(1, o.generateWorkerID());
	}

	@Test
	public void testSupplierId() {
		Office o = new Office();
		assertEquals(1, o.generateSupplierID());
	}
}
