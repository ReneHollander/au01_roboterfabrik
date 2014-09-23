package tgm.sew.hit.roboterfabrik.part;

import java.util.HashMap;

/**
 * Zischen den verschiedenen Teilen wird mithilfe des Enums PartType
 * unterschieden
 * 
 * @author Rene Hollander
 */
public enum PartType {

	/**
	 * Auge
	 */
	EYE("Auge", "auge", 2),

	/**
	 * Rumpf
	 */
	BODY("Rumpf", "rumpf", 1),

	/**
	 * Kettenantrieb
	 */
	CHAINDRIVE("Kettenantrieb", "kettenantrieb", 1),

	/**
	 * Arm
	 */
	ARM("Arm", "arm", 2);

	private static final HashMap<String, PartType> namePartTypeMapping;

	static {
		// add all names with the corresponding parttype to the map for easier
		// and faster access
		namePartTypeMapping = new HashMap<String, PartType>();
		for (PartType type : PartType.values()) {
			namePartTypeMapping.put(type.getName(), type);
		}
	}

	private String name;
	private String filename;
	private int amountForThreadee;

	PartType(String name, String filename, int amountForThreadee) {
		this.name = name;
		this.filename = filename;
		this.amountForThreadee = amountForThreadee;
	}

	/**
	 * Der externe Name eines Teils kann mit dieser Methode geholt werden
	 * 
	 * @return externe Name des Teils
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Der Dateiname fuer diesen Part
	 * 
	 * @return Dateiname
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Anzahl des Teiles fuer den Threadee
	 * 
	 * @return Anzahl
	 */
	public int getAmountForThreadee() {
		return this.amountForThreadee;
	}

	/**
	 * Sucht den PartType mit dem Angegebenen Namen
	 * 
	 * @param name
	 *            Name des zu findenden PartType
	 * @return PartType
	 */
	public static PartType fromName(String name) {
		return namePartTypeMapping.get(name);
	}

}
