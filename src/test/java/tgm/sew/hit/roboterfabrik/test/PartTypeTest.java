package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.part.PartType;

//fromNAme muss noch getestet werden
public class PartTypeTest {

	@Test
	public void testGetName1() {
		PartType p = PartType.EYE;
		assertEquals("Auge", p.getName());
	}

	@Test
	public void testGetName2() {
		PartType p = PartType.BODY;
		assertEquals("Rumpf", p.getName());
	}

	@Test
	public void testGetName3() {
		PartType p = PartType.ARM;
		assertEquals("Arm", p.getName());
	}

	@Test
	public void testGetName4() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals("Kettenantrieb", p.getName());
	}

	@Test
	public void testGetFilename1() {
		PartType p = PartType.BODY;
		assertEquals("rumpf", p.getFilename());
	}

	@Test
	public void testGetFilename2() {
		PartType p = PartType.ARM;
		assertEquals("arm", p.getFilename());
	}

	@Test
	public void testGetFilename3() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals("kettenantrieb", p.getFilename());
	}

	@Test
	public void testGetFilename4() {
		PartType p = PartType.EYE;
		assertEquals("auge", p.getFilename());
	}

	@Test
	public void testGetAmountForThreadee1() {
		PartType p = PartType.EYE;
		assertEquals(2, p.getAmountForThreadee());
	}

	@Test
	public void testGetAmountForThreadee2() {
		PartType p = PartType.ARM;
		assertEquals(2, p.getAmountForThreadee());
	}

	@Test
	public void testGetAmountForThreadee3() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals(1, p.getAmountForThreadee());
	}

	@Test
	public void testGetAmountForThreadee4() {
		PartType p = PartType.BODY;
		assertEquals(1, p.getAmountForThreadee());
	}
}