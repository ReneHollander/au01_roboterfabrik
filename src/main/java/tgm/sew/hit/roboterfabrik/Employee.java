package tgm.sew.hit.roboterfabrik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.watchdog.AbstractWatchable;

public class Employee extends AbstractWatchable {

	private static final Logger LOGGER = LogManager.getLogger(Employee.class);

	private Simulation sim;
	private int id;

	public Employee(Simulation sim, int id) {
		this.id = id;
	}

	public void run() {
		while (this.isRunning()) {

		}
	}

	public int getID() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Employee [sim=" + sim + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((sim == null) ? 0 : sim.hashCode());
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
		if (sim == null) {
			if (other.sim != null)
				return false;
		} else if (!sim.equals(other.sim))
			return false;
		return true;
	}

}
