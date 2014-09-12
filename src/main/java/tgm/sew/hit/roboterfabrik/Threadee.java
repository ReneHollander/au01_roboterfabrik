package tgm.sew.hit.roboterfabrik;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;

public class Threadee {

	private static final Logger LOGGER = LogManager.getLogger(Threadee.class);

	private int id;
	private Employee creator;
	private ArrayList<Part> parts;

	public Threadee(int id, Employee creator, ArrayList<Part> parts) {

	}

	public int getID() {
		return 0;
	}

	public Employee getCreator() {
		return null;
	}

	public ArrayList<Part> getParts() {
		return null;
	}

}
