package tgm.sew.hit.roboterfabrik;

import java.util.List;

import tgm.sew.hit.roboterfabrik.part.Part;
import at.rene8888.fastcsv.FastCSVRecord;

/**
 * Der Threadee ist der Roboter, der von den Employees (Arbeitern) angefertigt
 * wird. Er besteht aus mehreren Teilen (Hier parts) die zur Zusammensetzung des
 * Roboters benoetigt werden. Diese muss der Arbeiter, vorher von dem Supplier
 * abholen.
 * 
 * @author Simon Wortha
 */
public class Threadee {

	private long id;
	private Employee creator;
	private List<Part> parts;

	/**
	 * Erstellt einen neuen Threadee
	 * 
	 * @param id
	 *            ist die ID die dem fertigen Threadee zugewiesen wird.
	 * @param creator
	 *            ist der Employee der den Threadee zusammengebaut hat.
	 * @param parts
	 *            ist eine ArrayList, die die ganzen Teile enthaellt die fuer
	 *            den Roboter benoetigt werden.
	 */
	public Threadee(long id, Employee creator, List<Part> parts) {
		this.id = id;
		this.creator = creator;
		this.parts = parts;
	}

	/**
	 * Eindeutige ID des Threadee
	 * 
	 * @return ID des Threadees
	 */
	public long getID() {
		return id;
	}

	/**
	 * Arbeiter der den Threadee zusammen gebaut hat
	 * 
	 * @return Monteur des Threadee
	 */
	public Employee getCreator() {
		return creator;
	}

	/**
	 * Teile aus denen der Threadee besteht
	 * 
	 * @return Teile des Threadees
	 */
	public List<Part> getParts() {
		return parts;
	}

	/**
	 * Erstellt einen CSV Record aus IDs und den Teilen
	 * 
	 * @return CSVRecord
	 */
	public FastCSVRecord getCSVRecord() {
		FastCSVRecord record = new FastCSVRecord();
		record.addField("Threadee-ID" + this.getID());
		record.addField("Mitarbeiter-ID" + this.getCreator().getID());
		for (Part p : this.getParts()) {
			record.addField(p.getCSVRecord().toString());
		}
		return record;
	}

	@Override
	public String toString() {
		return "Threadee [id=" + id + ", creator=" + creator + ", parts=" + parts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((parts == null) ? 0 : parts.hashCode());
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
		Threadee other = (Threadee) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (id != other.id)
			return false;
		if (parts == null) {
			if (other.parts != null)
				return false;
		} else if (!parts.equals(other.parts))
			return false;
		return true;
	}

}
