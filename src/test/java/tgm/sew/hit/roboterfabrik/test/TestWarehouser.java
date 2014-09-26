package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Employee;
import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.Warehouser;
import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.supply.Supply;

public class TestWarehouser {

	private final File testDir = new File("./workingdir/test");

	@Before
	public void resetFiles() {
		this.testDir.delete();
	}

	@Test
	public void testCreateWarehouser() {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		w.close();
	}

	@Test
	public void testCreateWarehouserIfExisted() {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		w.close();
		Warehouser w2 = new Warehouser(this.testDir);
		w2.close();
	}

	@Test
	public void testCreateWarehouserException() {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(new File(this.testDir, "nonExistentDir"));
	}

	@Test
	public void testStoreSupply() {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		assertEquals(true, w.storeSupply(new Supply(new Part(PartType.ARM, new int[0]))));
		w.close();
	}

	@Test
	public void testStoreSupplyAndCheckFile() throws FileNotFoundException, IOException {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		w.storeSupply(new Supply(new Part(PartType.ARM, new int[] { 1, 2 })));
		w.close();
		assertEquals("Arm,1,2", new BufferedReader(new FileReader(new File(this.testDir, "arm.csv"))).readLine());
	}

	@Test
	public void testGetPartPackage1() {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		assertEquals(null, w.getPartPackage());
	}

	@Test
	public void testGetPartPackage2() {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		w.storeSupply(new Supply(new Part(PartType.ARM, new int[] { 0, 1 })));
		w.storeSupply(new Supply(new Part(PartType.ARM, new int[] { 2, 3 })));
		w.storeSupply(new Supply(new Part(PartType.EYE, new int[] { 4, 5 })));
		w.storeSupply(new Supply(new Part(PartType.EYE, new int[] { 6, 7 })));
		w.storeSupply(new Supply(new Part(PartType.BODY, new int[] { 8, 9 })));
		w.storeSupply(new Supply(new Part(PartType.CHAINDRIVE, new int[] { 10, 11 })));
		assertEquals("[Part [type=EYE, numbers=[6, 7]], Part [type=EYE, numbers=[4, 5]], Part [type=BODY, numbers=[8, 9]], Part [type=CHAINDRIVE, numbers=[10, 11]], Part [type=ARM, numbers=[2, 3]], Part [type=ARM, numbers=[0, 1]]]", w.getPartPackage().toString());
		w.close();
	}

	@Test
	public void testStoreThreadee() throws FileNotFoundException, IOException {
		this.testDir.mkdirs();
		Warehouser w = new Warehouser(this.testDir);
		w.storeThreadee(new Threadee(0, new Employee(null, 0), Arrays.asList(new Part(PartType.CHAINDRIVE, new int[] { 0 }))));
		w.close();
		assertEquals("Threadee-ID0,Mitarbeiter-ID0,Kettenantrieb,0", new BufferedReader(new FileReader(new File(this.testDir, "auslieferung.csv"))).readLine());
	}
}
