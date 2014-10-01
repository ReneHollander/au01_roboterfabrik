package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.IntegerWrapper;

/**
 * Testet die Integer Wrapper Klasse
 * 
 * @author Rene Hollander
 */
public class TestIntegerWrapper {

	/**
	 * Testet ob der Integer Wrapper die richtige Anzahl hinzufuegt
	 */
	@Test
	public void testIntegerWrapper() {
		IntegerWrapper iw = new IntegerWrapper(0);
		iw.add(1);
		assertEquals(1, iw.get());
	}
}
