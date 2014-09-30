package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.part.PartType;

/**
 * Testfälle für PartType
 * 
 * @author Simon Wortha
 */
public class PartTypeTest {

	/**
	 * Testet ob die getName Methode richtig arbeitet, genau genommen ob der
	 * richtige Namen returned wird
	 */
	@Test
	public void testGetName1() {
		PartType p = PartType.EYE;
		assertEquals("Auge", p.getName());
	}

	/**
	 * Testet ob die getName Methode richtig arbeitet, genau genommen ob der
	 * richtige Namen returned wird
	 */
	@Test
	public void testGetName2() {
		PartType p = PartType.BODY;
		assertEquals("Rumpf", p.getName());
	}

	/**
	 * Testet ob die getName Methode richtig arbeitet, genau genommen ob der
	 * richtige Namen returned wird
	 */
	@Test
	public void testGetName3() {
		PartType p = PartType.ARM;
		assertEquals("Arm", p.getName());
	}

	/**
	 * Testet ob die getName Methode richtig arbeitet, genau genommen ob der
	 * richtige Namen returned wird
	 */
	@Test
	public void testGetName4() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals("Kettenantrieb", p.getName());
	}

	/**
	 * Testet ob die getFileName Methode richtig arbeitet, genau genommen ob der
	 * richtige FileNamen returned wird
	 */
	@Test
	public void testGetFilename1() {
		PartType p = PartType.BODY;
		assertEquals("rumpf", p.getFilename());
	}

	/**
	 * Testet ob die getFileName Methode richtig arbeitet, genau genommen ob der
	 * richtige FileNamen returned wird
	 */
	@Test
	public void testGetFilename2() {
		PartType p = PartType.ARM;
		assertEquals("arm", p.getFilename());
	}

	/**
	 * Testet ob die getFileName Methode richtig arbeitet, genau genommen ob der
	 * richtige FileNamen returned wird
	 */
	@Test
	public void testGetFilename3() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals("kettenantrieb", p.getFilename());
	}

	/**
	 * Testet ob die getFileName Methode richtig arbeitet, genau genommen ob der
	 * richtige FileNamen returned wird
	 */
	@Test
	public void testGetFilename4() {
		PartType p = PartType.EYE;
		assertEquals("auge", p.getFilename());
	}

	/**
	 * Testet ob die getAmountForThreadee Methode richtig arbeitet, genau
	 * genommen ob die richtige ANzahl der benötogen Teilen zurück gegeben wird
	 */
	@Test
	public void testGetAmountForThreadee1() {
		PartType p = PartType.EYE;
		assertEquals(2, p.getAmountForThreadee());
	}

	/**
	 * Testet ob die getAmountForThreadee Methode richtig arbeitet, genau
	 * genommen ob die richtige ANzahl der benötogen Teilen zurück gegeben wird
	 */
	@Test
	public void testGetAmountForThreadee2() {
		PartType p = PartType.ARM;
		assertEquals(2, p.getAmountForThreadee());
	}

	/**
	 * Testet ob die getAmountForThreadee Methode richtig arbeitet, genau
	 * genommen ob die richtige ANzahl der benötogen Teilen zurück gegeben wird
	 */
	@Test
	public void testGetAmountForThreadee3() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals(1, p.getAmountForThreadee());
	}

	/**
	 * Testet ob die getAmountForThreadee Methode richtig arbeitet, genau
	 * genommen ob die richtige ANzahl der benötogen Teilen zurück gegeben wird
	 */
	@Test
	public void testGetAmountForThreadee4() {
		PartType p = PartType.BODY;
		assertEquals(1, p.getAmountForThreadee());
	}

	/**
	 * Testet ob die from Methode richtig arbeitet, genau genommen ob der
	 * richtige Typ zurück gegeben wird
	 */
	@Test
	public void testFromName1() {
		PartType p = PartType.EYE;
		assertEquals(PartType.EYE, PartType.fromName("Auge"));
	}

	/**
	 * Testet ob die from Methode richtig arbeitet, genau genommen ob der
	 * richtige Typ zurück gegeben wird
	 */
	@Test
	public void testFromName2() {
		PartType p = PartType.BODY;
		assertEquals(PartType.BODY, PartType.fromName("Rumpf"));
	}

	/**
	 * Testet ob die from Methode richtig arbeitet, genau genommen ob der
	 * richtige Typ zurück gegeben wird
	 */
	@Test
	public void testFromName3() {
		PartType p = PartType.ARM;
		assertEquals(PartType.ARM, PartType.fromName("Arm"));
	}

	/**
	 * Testet ob die from Methode richtig arbeitet, genau genommen ob der
	 * richtige Typ zurück gegeben wird
	 */
	@Test
	public void testFromName4() {
		PartType p = PartType.CHAINDRIVE;
		assertEquals(PartType.CHAINDRIVE, PartType.fromName("Kettenantrieb"));
	}
}