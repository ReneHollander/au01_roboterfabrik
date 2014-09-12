package tgm.sew.hit.roboterfabrik.supply;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.Warehouser;

/**
 * Der Lieferant liefert neue Teile welche von der Klasse 
 * Supply erstellt wurden an den Lagermitarbeiter.
 * Mehrere Lieferanten koennen Lieferungen von verschiedenen Teilen und
 * verschiedener Anzahl an Teilen an den Lagermitarbeiter übergeben.
 * 
 * @author Samuel Schober
 *
 */
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
