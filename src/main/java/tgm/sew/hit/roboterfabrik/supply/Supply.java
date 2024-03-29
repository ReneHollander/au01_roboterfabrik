package tgm.sew.hit.roboterfabrik.supply;

import tgm.sew.hit.roboterfabrik.part.Part;

/**
 * Supply stellt dem Lieferanten Teile, welche von Part erstellt wurden, zur
 * Verfuegung.
 * 
 * @author Samuel Schober
 *
 */
public class Supply {

	private Part part;

	/**
	 * Das von Part erstellte Teil wird im Konstruktor initialisiert.
	 * 
	 * @param part
	 *            das von Part erstellte Teil.
	 */
	public Supply(Part part) {
		this.part = part;
	}

	/**
	 * Gibt den im Supply enthaltenen Part zurueck
	 * 
	 * @return part der enthaltete Part
	 */
	public Part getPart() {
		return this.part;
	}

	@Override
	public String toString() {
		return "Supply [part=" + part + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((part == null) ? 0 : part.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supply other = (Supply) obj;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		return true;
	}

}
