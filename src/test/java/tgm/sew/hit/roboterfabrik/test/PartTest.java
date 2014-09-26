package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.EnumMap;

import org.junit.Test;

import at.rene8888.fastcsv.FastCSV;
import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;

//getCSVRecod und readFromCSV müssen noch getestet werden!
public class PartTest {

	@Test
	public void testgetNumbers() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(ar, p.getNumbers());
	}

	@Test
	public void testgetPartType() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(pt, p.getPartType());
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType2() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("EYE", p.getPartType());
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType3() {
		PartType pt = PartType.BODY;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("BODY", p.getPartType());
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType4() {
		PartType pt = PartType.CHAINDRIVE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("CHAINDRIVE", p.getPartType());
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testgetPartType5() {
		PartType pt = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("ARM", p.getPartType());
	}

	@Test
	public void testgetPartType6() {
		PartType pt = PartType.ARM;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.ARM, p.getPartType());
	}

	@Test
	public void testgetPartType7() {
		PartType pt = PartType.CHAINDRIVE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.CHAINDRIVE, p.getPartType());
	}

	@Test
	public void testgetPartType8() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.EYE, p.getPartType());
	}

	@Test
	public void testgetPartType9() {
		PartType pt = PartType.BODY;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(PartType.BODY, p.getPartType());
	}

	@Test
	public void testEquals() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals(true, p.equals(p));
	}

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

	@Test
	public void testToString() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(pt, ar);
		assertEquals("Part [type=EYE, numbers=[1, 2, 3]]", p.toString());
	}

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
}
