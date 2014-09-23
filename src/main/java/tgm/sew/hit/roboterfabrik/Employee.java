package tgm.sew.hit.roboterfabrik;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.watchdog.AbstractWatchable;

/**
 * Der Arbeiter (Employee) baut den Roboter zusammen. (wird simuliert in dem Er
 * zahlen ordnet) Er bekommt eine ID vom Office zugeteilt. Er holt sich die
 * Bauteile vom Lager und bring den fertigen Threadee anschliessend zurueck. Der
 * Arbeiter holt sich auch eine ID fuer den Threadee aus dem Ofiice
 * 
 * @author Simon Wortha
 *
 */
public class Employee extends AbstractWatchable {

	private static final Logger LOGGER = LogManager.getLogger(Employee.class);

	private Simulation sim;
	private long id;

	/**
	 * Erstellt einen neuen Arbeiter
	 * 
	 * @param sim
	 *            Simulation
	 * @param id
	 *            ID fuer Employee
	 */
	public Employee(Simulation sim, long id) {
		this.sim = sim;
		this.id = id;
	}

	public void run() {
		Thread.currentThread().setName("Employee " + this.id);
		while (this.isRunning()) {
			ArrayList<Part> partList = sim.getWarehouser().getPartPackage();
			if (partList != null) {
				for (Part p : partList) {
					Arrays.sort(p.getNumbers());
				}
				Threadee r2d2 = new Threadee(this.sim.getOffice().generateThreadeeID(), this, partList);
				LOGGER.debug("Successfully assembled " + r2d2.toString());
				this.sim.getWarehouser().storeThreadee(r2d2);
			}
		}
	}

	/**
	 * Eindeutige ID des Arbeiters
	 * 
	 * @return ID des Arbeiters
	 */
	public long getID() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
