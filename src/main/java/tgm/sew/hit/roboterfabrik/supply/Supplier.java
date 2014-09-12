package tgm.sew.hit.roboterfabrik.supply;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Simulation;

public class Supplier {

	private static final Logger LOGGER = LogManager.getLogger(Simulation.class);

	private boolean running;

	public Supplier() {
		this.running = true;
	}

	public void run() {
		while (this.running) {

		}
	}

	public void stopGracefully() {
		this.running = false;
	}

}
