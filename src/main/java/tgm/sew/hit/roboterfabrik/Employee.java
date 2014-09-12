package tgm.sew.hit.roboterfabrik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee {

	private static final Logger LOGGER = LogManager.getLogger(Employee.class);

	private boolean running;
	private int id;

	public Employee(int id) {
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

}
