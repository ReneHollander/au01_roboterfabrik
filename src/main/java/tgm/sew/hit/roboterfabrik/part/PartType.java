package tgm.sew.hit.roboterfabrik.part;

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
	EYE("Auge", "auge"),

	/**
	 * Rumpf
	 */
	BODY("Rumpf", "rumpf"),

	/**
	 * Kettenantrieb
	 */
	CHAINDRIVE("Kettenantrieb", "kettenantrieb"),

	/**
	 * Arm
	 */
	ARM("Arm", "arm");

	private String name;
	private String filename;

	PartType(String name, String filename) {
		this.name = name;
		this.filename = filename;
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

}
