package tgm.sew.hit.roboterfabrik.supply;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;

/**
 * Supply stellt dem Lieferanten Teile, welche von Part erstellt wurden, zur
 * Verfügung.
 * 
 * @author Samuel
 *
 */
public class Supply {

	private static final Logger LOGGER = LogManager.getLogger(Supply.class);

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
	 * @return part wird zurueckgegeben.
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
