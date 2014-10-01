package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supply;

/**
 * Testfälle zu Supply
 * 
 * @author Simon Wortha
 *
 */
public class SupplyTest {

	/**
	 * Testet ob getPart auch den richtigen Roboterteil zurück liefert
	 */
	@Test
	public void testGetPart() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(p, s.getPart());
	}

	/**
	 * Testet ob toString auch den gewünschten String zurück liefert
	 */
	@Test
	public void testToString() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals("Supply [part=Part [type=EYE, numbers=[1, 2, 3]]]", s.toString());
	}

	/**
	 * Testet ob hashCode auch die gewünschten Werte berechnet
	 */
	@Test
	public void testHashCode() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		int result = 31 + ((p == null) ? 0 : p.hashCode());
		assertEquals(result, s.hashCode());
	}

	/**
	 * Testet ob hashCode mit einem null part funktioniert
	 */
	@Test
	public void testHashCode2() {
		Part p = null;
		Supply s = new Supply(p);
		int result = 31 + ((p == null) ? 0 : p.hashCode());
		assertEquals(result, s.hashCode());
	}

	/**
	 * Testet ob equals auch richtig vergleicht
	 */
	@Test
	public void testEquals1() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(true, s.equals(s));
	}

	/**
	 * Testet ob equals auch richtig vergleicht
	 */
	@Test
	public void testEquals2() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(false, s.equals("Hallo"));
	}

	/**
	 * Testet ob equals auch richtig vergleicht
	 */
	@Test
	public void testEquals3() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		Supply s2 = new Supply(p);
		assertEquals(true, s.equals(s2));
	}

	/**
	 * Testet ob equals auch richtig vergleicht
	 */
	@Test
	public void testEquals4() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		Supply s2 = new Supply(new Part(PartType.CHAINDRIVE, new int[3]));
		assertEquals(false, s.equals(s2));
	}

	/**
	 * Testet die equals Methode mit einem null
	 */
	@Test
	public void testEquals5() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(false, s.equals(null));
	}

	/**
	 * Testet die equals Methode mit einem ungueltigen Part
	 */
	@Test
	public void testEquals6() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s1 = new Supply(null);
		Supply s2 = new Supply(p);
		assertEquals(false, s1.equals(s2));
	}

	/**
	 * Testet die equals Methode mit einem ungueltigen Part im zu vergleichenden
	 * Supply
	 */
	@Test
	public void testEquals7() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s1 = new Supply(null);
		Supply s2 = new Supply(p);
		assertEquals(false, s1.equals(s2));
	}
}
