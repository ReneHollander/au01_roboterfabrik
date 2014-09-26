package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.IntegerWrapper;

public class TestIntegerWrapper {

	@Test
	public void testIntegerWrapper() {
		IntegerWrapper iw = new IntegerWrapper(0);
		iw.add(1);
		assertEquals(1, iw.get());
	}
}
