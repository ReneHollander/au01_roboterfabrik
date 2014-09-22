package tgm.sew.hit.roboterfabrik.supply;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Der Lieferant liefert neue Teile welche von der Klasse Supply erstellt wurden
 * an den Lagermitarbeiter. Mehrere Lieferanten koennen Lieferungen von
 * verschiedenen Teilen und verschiedener Anzahl an Teilen an den
 * Lagermitarbeiter uebergeben.
 * 
 * @author Samuel Schober
 *
 */
public class Supplier {

	private static final Logger LOGGER = LogManager.getLogger(Supplier.class);

	private boolean running;

	/**
	 * Ein Lieferanten Thread wird initialisiert.
	 * 
	 */
	public Supplier() {
		this.running = true;
	}

	/**
	 * Waehrend ein Lieferanten Thread laeuft uebergibt er die von Supply zur
	 * Verfuegung gestellten Teile einem Lagerarbeiter.
	 * 
	 */
	public void run() {
		while (this.running) {

		}
	}

	/**
	 * Ein Lieferanten Thread wird gestopt.
	 * 
	 */
	public void stopGracefully() {
		this.running = false;
	}

}
