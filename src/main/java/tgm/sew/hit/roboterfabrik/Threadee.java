package tgm.sew.hit.roboterfabrik;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;

/**
 * Der Threadee ist der Roboter, der von den Employees (Arbeitern) angefertigt wird.
 * Er besteht aus mehreren Teilen (Hier parts) die zur Zusammensetzung des Roboters benötigt werden. Diese muss der Arbeiter,
 * vorher von dem Supplier abholen.
 * 
 * @author Simon Wortha
 */
public class Threadee {

	private static final Logger LOGGER = LogManager.getLogger(Threadee.class);

	private int id;
	private Employee creator;
	private ArrayList<Part> parts;
	
	/**
	 * Erstellt einen neuen Threadee
	 * 
	 * @param id 		ist die ID die dem fertigen Threadee zugewiesen wird.
	 * @param creator 	ist der Employee der den Threadee zusammengebaut hat.
	 * @param parts 	ist eine ArrayList, die die ganzen Teile die für den Roboter benötigt werden.
	 */
	public Threadee(int id, Employee creator, ArrayList<Part> parts) {
		this.id = id;
		this.creator = creator;
		this.parts = parts;
	}

	public int getID() {
		return id;
	}

	public Employee getCreator() {
		return creator;
	}

	public ArrayList<Part> getParts() {
		return parts;
	}

}
