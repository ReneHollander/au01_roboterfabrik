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
	EYE("Auge"),

	/**
	 * Rumpf
	 */
	BODY("Rumpf"),

	/**
	 * Kettenantrieb
	 */
	CHAINDRIVE("Kettenantrieb"),

	/**
	 * Arm
	 */
	ARM("Arm");

	private String name;

	PartType(String name) {
		this.name = name;
	}

	/**
	 * Der externe Name eines Teils kann mit dieser Methode geholt werden
	 * 
	 * @return externe Name des Teils
	 */
	public String getName() {
		return this.name;
	}

}
