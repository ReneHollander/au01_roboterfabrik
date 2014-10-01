package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Employee;
import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;

/**
 * Testet den Threadee
 * 
 * @author Samuel Schober
 * 
 */
public class ThreadeeTest {

	private final File testDir = new File("./test");
	private final File logDir = new File("./test/log");

	/**
	 * Loescht die Files bevor sie erstellt werden damit keine Errors eintraeten
	 */
	@Before
	public void resetFiles() {
		this.testDir.delete();
		this.logDir.delete();
	}

	/**
	 * Testet ob ein neuer Thredee erstellt werden kann
	 */
	@SuppressWarnings("unused")
	@Test
	public void testCreateThreadee() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);
	}

	/**
	 * Testet ob die Methode getID den richtigen Wert zurueck gibt
	 */
	@Test
	public void testGetThreadeeID() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);

		assertEquals(1, t.getID());
	}

	/**
	 * Testet ob die Methode getCreator den richtigen Employee zurueck gibt
	 */
	@Test
	public void testGetThreadeeCreator() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);

		assertEquals(e, t.getCreator());
	}

	/**
	 * Testet ob die equal Methode richtig funktioniert und eine richtige
	 * Uebereinstimmung zweier Threadees moeglich ist
	 */
	@Test
	public void testThreadeeEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);

		assertEquals(true, t.equals(t));
	}

	/**
	 * Testet ob die equal Methode richtig funktioniert indem man den zu
	 * vergleichenden Wert auf null setzt
	 */
	@Test
	public void testThreadeeEqualsNull() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);

		assertEquals(false, t.equals(null));
	}

	/**
	 * Testet ob der Erschaffer des Threadees null sein kann und die equals
	 * Methode eine Uebereinstimmung erkennt
	 */
	@SuppressWarnings("unused")
	@Test(expected = java.lang.NullPointerException.class)
	public void testThreadeeCreatorEqualsNull() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, null, null);

		assertEquals(true, (t.getCreator()).equals(t.getCreator()));
	}

	/**
	 * Testet ob die richtigen Teile bei der getParts Abfrage zurueck gegeben
	 * werden
	 */
	@Test
	public void testGetParts() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		PartType pt1 = PartType.EYE;
		PartType pt2 = PartType.EYE;
		PartType pt3 = PartType.ARM;
		PartType pt4 = PartType.ARM;
		PartType pt5 = PartType.CHAINDRIVE;
		PartType pt6 = PartType.BODY;

		Part p1 = new Part(pt1, new int[3]);
		Part p2 = new Part(pt2, new int[3]);
		Part p3 = new Part(pt3, new int[3]);
		Part p4 = new Part(pt4, new int[3]);
		Part p5 = new Part(pt5, new int[3]);
		Part p6 = new Part(pt6, new int[3]);

		List<Part> list = new ArrayList<Part>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, list);

		assertEquals(list, t.getParts());
	}

	/**
	 * Testet ob die Teile inklusive ID richtig zu einem String zusammengefuegt
	 * werden
	 */
	@Test
	public void testToString() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		PartType pt1 = PartType.EYE;
		PartType pt2 = PartType.EYE;
		PartType pt3 = PartType.ARM;
		PartType pt4 = PartType.ARM;
		PartType pt5 = PartType.CHAINDRIVE;
		PartType pt6 = PartType.BODY;

		Part p1 = new Part(pt1, new int[3]);
		Part p2 = new Part(pt2, new int[3]);
		Part p3 = new Part(pt3, new int[3]);
		Part p4 = new Part(pt4, new int[3]);
		Part p5 = new Part(pt5, new int[3]);
		Part p6 = new Part(pt6, new int[3]);

		List<Part> list = new ArrayList<Part>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, list);

		assertEquals("Threadee [id=1, creator=Employee [id=1], parts=[Part [type=EYE, numbers=[0, 0, 0]], Part [type=EYE, numbers=[0, 0, 0]], Part [type=ARM, numbers=[0, 0, 0]], Part [type=ARM, numbers=[0, 0, 0]], Part [type=CHAINDRIVE, numbers=[0, 0, 0]], Part [type=BODY, numbers=[0, 0, 0]]]]",
				t.toString());
	}

	/**
	 * Testet ob die Methode hashCode einen richtigen Hash Code generiert
	 */
	@Test
	public void testHashCode1() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		PartType pt1 = PartType.EYE;
		PartType pt2 = PartType.EYE;
		PartType pt3 = PartType.ARM;
		PartType pt4 = PartType.ARM;
		PartType pt5 = PartType.CHAINDRIVE;
		PartType pt6 = PartType.BODY;

		Part p1 = new Part(pt1, new int[3]);
		Part p2 = new Part(pt2, new int[3]);
		Part p3 = new Part(pt3, new int[3]);
		Part p4 = new Part(pt4, new int[3]);
		Part p5 = new Part(pt5, new int[3]);
		Part p6 = new Part(pt6, new int[3]);

		List<Part> list = new ArrayList<Part>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, list);

		final int prime = 31;
		int result = 1;
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + (int) (t.getID() ^ (t.getID() >>> 32));
		result = prime * result + ((list == null) ? 0 : list.hashCode());

		assertEquals(result, t.hashCode());
	}

	/**
	 * Testet ob equals zwei verschiedene Threadees erkennt
	 */
	@Test
	public void testEquals() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		PartType pt1 = PartType.EYE;
		PartType pt2 = PartType.EYE;
		PartType pt3 = PartType.ARM;
		PartType pt4 = PartType.ARM;
		PartType pt5 = PartType.CHAINDRIVE;
		PartType pt6 = PartType.BODY;

		Part p1 = new Part(pt1, new int[3]);
		Part p2 = new Part(pt2, new int[3]);
		Part p3 = new Part(pt3, new int[3]);
		Part p4 = new Part(pt4, new int[3]);
		Part p5 = new Part(pt5, new int[3]);
		Part p6 = new Part(pt6, new int[3]);

		List<Part> list = new ArrayList<Part>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, null);
		Threadee t2 = new Threadee(1, e, list);

		assertEquals(false, t.equals(t2));
	}

	/**
	 * Testet ob der CSV Record fuer den Threadee richtig eingetragen wird
	 */
	@Test
	public void testFastCSVRecord() {
		this.testDir.mkdirs();
		this.logDir.mkdirs();

		final Simulation sim = new Simulation(100, 1, 1, this.testDir, this.logDir);

		PartType pt1 = PartType.EYE;
		PartType pt2 = PartType.EYE;
		PartType pt3 = PartType.ARM;
		PartType pt4 = PartType.ARM;
		PartType pt5 = PartType.CHAINDRIVE;
		PartType pt6 = PartType.BODY;

		Part p1 = new Part(pt1, new int[3]);
		Part p2 = new Part(pt2, new int[3]);
		Part p3 = new Part(pt3, new int[3]);
		Part p4 = new Part(pt4, new int[3]);
		Part p5 = new Part(pt5, new int[3]);
		Part p6 = new Part(pt6, new int[3]);

		List<Part> list = new ArrayList<Part>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);

		Employee e = new Employee(sim, 1);
		Threadee t = new Threadee(1, e, list);

		assertEquals("Threadee-ID1,Mitarbeiter-ID1,Auge,0,0,0,Auge,0,0,0,Arm,0,0,0,Arm,0,0,0,Kettenantrieb,0,0,0,Rumpf,0,0,0", t.getCSVRecord().toString());
	}
}
