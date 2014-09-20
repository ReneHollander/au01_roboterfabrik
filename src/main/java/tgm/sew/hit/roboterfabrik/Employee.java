package tgm.sew.hit.roboterfabrik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger(Employee.class);

	private boolean running;
	private Simulation sim;
	private int id;

	public Employee(Simulation sim, int id) {
		this.id = id;
		this.running = true;
	}

	public void run() {
		while (this.running) {

		}
	}

	public void stopGracefully() {
		this.running = false;
	}

	public int getID() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Employee [running=" + running + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (running ? 1231 : 1237);
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
		if (running != other.running)
			return false;
		return true;
	}

}
