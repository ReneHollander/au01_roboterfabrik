package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supply;

public class SupplyTest {

	@Test
	public void testGetPart() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(p, s.getPart());
	}

	@Test
	public void testToString() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals("Supply [part=Part [type=EYE, numbers=[1, 2, 3]]]", s.toString());
	}

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

	@Test
	public void testEquals1() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(true, s.equals(s));
	}

	@Test
	public void testEquals2() {
		int[] ar = new int[3];
		for (int i = 0; i < 3; i++)
			ar[i] = i + 1;
		Part p = new Part(PartType.EYE, ar);
		Supply s = new Supply(p);
		assertEquals(false, s.equals("Hallo"));
	}

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
}
