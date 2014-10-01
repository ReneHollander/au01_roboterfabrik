package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;

/**
 * Testfaelle fuer Part
 * 
 * @author Simon Wortha
 */
public class PartTest {

	/**
	 * Testet ob die getNumbers Methode bzw. der Konstruktor richtig arbeitet
	 */
	@Test
	public void testgetNumbers() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(ar, p.getNumbers());
	}

	/**
	 * Testet ob die getPartType Methode bzw. der Konstruktor richtig arbeitet
	 */
	@Test
	public void testgetPartType() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(pt, p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richtig arbeitet, genau genommen ob das
	 * Object und kein String ausgegeben wird
	 */
	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType2() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("EYE", p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richtig arbeitet, genau genommen ob das
	 * Object und kein String ausgegeben wird
	 */
	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType3() {
		PartType pt = PartType.BODY;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("BODY", p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richtig arbeitet, genau genommen ob das
	 * Object und kein String ausgegeben wird
	 */
	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType4() {
		PartType pt = PartType.CHAINDRIVE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("CHAINDRIVE", p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode tichtig arbeitet, genau genommen ob das
	 * Object und kein String ausgegeben wird
	 */
	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType5() {
		PartType pt = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("ARM", p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richtig arbeitet, genau genommen ob das
	 * richtige Object returned wird
	 */
	@Test
	public void testgetPartType6() {
		PartType pt = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.ARM, p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richtig arbeitet, genau genommen ob das
	 * richtige Object returned wird
	 */
	@Test
	public void testgetPartType7() {
		PartType pt = PartType.CHAINDRIVE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.CHAINDRIVE, p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richtig arbeitet, genau genommen ob das
	 * richtige Object returned wird
	 */
	@Test
	public void testgetPartType8() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.EYE, p.getPartType());
	}

	/**
	 * Testet ob die getPartType Methode richig arbeitet, genau genommen ob das
	 * richtige Object returned wird
	 */
	@Test
	public void testgetPartType9() {
		PartType pt = PartType.BODY;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.BODY, p.getPartType());
	}

	/**
	 * Testet ob die Equals Methode richtig arbeitet, genau genommen ob sie zwei
	 * Objects richtig miteinander vergleicht
	 */
	@Test
	public void testEquals() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(true, p.equals(p));
	}

	/**
	 * Testet ob die Equals Methode richtig arbeitet, genau genommen ob sie zwei
	 * Objects richtig miteinander vergleicht
	 */
	@Test
	public void testEquals2() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		Part p2 = new Part(pt, ar);
		assertEquals(true, p.equals(p2));
	}

	/**
	 * Testet ob die Equals Methode richtig arbeitet, genau genommen ob sie zwei
	 * Objects richtig miteinander vergleicht
	 */
	@Test
	public void testEquals3() {
		PartType pt = PartType.EYE;
		PartType pt2 = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		Part p2 = new Part(pt2, ar);
		assertEquals(false, p.equals(p2));
	}

	/**
	 * Testet die Equals Methode gegen ein null
	 */
	@Test
	public void testEquals4() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(false, p.equals(null));
	}

	/**
	 * Testet die Equals Methode gegen ein eine andere Klasse
	 */
	@Test
	public void testEquals5() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(false, p.equals("Ein String"));
	}

	/**
	 * Testet ob die toString Methode richtig arbeitet, genau genommen ob sie
	 * auch den richtigen String zurueck gibt
	 */
	@Test
	public void testToString() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Part [type=EYE, numbers=[1, 2, 3]]", p.toString());
	}

	/**
	 * Testet ob die hashCode Methode richtig arbeitet, genau genommen ob sie
	 * die Berechnung richtig macht
	 */
	@Test
	public void testHashCode() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		int hc = 31 + Arrays.hashCode(ar);
		hc = hc * 31 + ((pt == null) ? 0 : pt.hashCode());
		assertEquals(hc, p.hashCode());
	}

	/**
	 * Testet die hashCode methode mit einem ungueltigen PartType
	 */
	@Test
	public void testHashCode1() {
		PartType pt = null;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		int hc = 31 + Arrays.hashCode(ar);
		hc = hc * 31 + ((pt == null) ? 0 : pt.hashCode());
		assertEquals(hc, p.hashCode());
	}

	/**
	 * Testet ob die Equals Methode richtig arbeitet, genau genommen ob sie auch
	 * wirklich die Neuen Nummern zurueck gibt
	 */
	@Test
	public void testSetNumbers() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		int[] ar2 = new int[10];
		for (int i = 0; i < 10; i++)
			ar2[i] = i + 1;
		p.setNumbers(ar2);
		assertEquals(ar2, p.getNumbers());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testGetCSVRecord1() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Auge,1,2,3", p.getCSVRecord().toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testGetCSVRecord2() {
		PartType pt = PartType.BODY;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Rumpf,1,2,3", p.getCSVRecord().toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testGetCSVRecord3() {
		PartType pt = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Arm,1,2,3", p.getCSVRecord().toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testGetCSVRecord4() {
		PartType pt = PartType.CHAINDRIVE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Kettenantrieb,1,2,3", p.getCSVRecord().toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testReadFRomCSVRecord1() {
		PartType pt = PartType.CHAINDRIVE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Part [type=CHAINDRIVE, numbers=[1, 2, 3]]", Part.readFromCSV(p.getCSVRecord()).toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testReadFRomCSVRecord2() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Part [type=EYE, numbers=[1, 2, 3]]", Part.readFromCSV(p.getCSVRecord()).toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testReadFRomCSVRecord3() {
		PartType pt = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Part [type=ARM, numbers=[1, 2, 3]]", Part.readFromCSV(p.getCSVRecord()).toString());
	}

	/**
	 * Testet die CSVRecords
	 */
	@Test
	public void testReadFRomCSVRecord4() {
		PartType pt = PartType.BODY;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Part [type=BODY, numbers=[1, 2, 3]]", Part.readFromCSV(p.getCSVRecord()).toString());
	}

	/**
	 * Fix die code coverage damit es 100% sind
	 */
	@Test
	public void fixEnumTest() {
		PartType.valueOf("EYE");
	}
}