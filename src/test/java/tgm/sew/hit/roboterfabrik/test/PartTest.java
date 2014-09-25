package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import java.util.EnumMap;

import org.junit.Test;

import at.rene8888.fastcsv.FastCSV;
import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;


public class PartTest {

	@Test
	public void testPart() {
		PartType pt = PartType.EYE;
		int[] ar = new int[3];
		for(int i=0; i<3; i++) ar[i] = i+1;
		Part p = new Part(pt, ar);
		assertEquals(ar, p.getNumbers());
	}

}
