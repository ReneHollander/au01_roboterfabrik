package tgm.sew.hit.roboterfabrik.supply;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.part.Part;
import tgm.sew.hit.roboterfabrik.part.PartType;
import tgm.sew.hit.roboterfabrik.watchdog.AbstractWatchable;

/**
 * Der Lieferant liefert neue Teile welche von der Klasse Supply erstellt wurden
 * an den Lagermitarbeiter. Mehrere Lieferanten koennen Lieferungen von
 * verschiedenen Teilen und verschiedener Anzahl an Teilen an den
 * Lagermitarbeiter uebergeben.
 * 
 * @author Samuel Schober
 *
 */
public class Supplier extends AbstractWatchable {

	private static final Logger LOGGER = LogManager.getLogger(Supplier.class);

	private static final int SUPPLY_CHANGE_POSSIBILITY = 15;
	private static final int SUPPLY_NUMBERAMOUNT = 20;
	private static final int SUPPLY_MAXNUMBER = 999;

	private Random random;
	private Simulation simulation;
	private PartType currentPartType;

	/**
	 * Ein Lieferanten Thread wird initialisiert.
	 * 
	 * @param simulation
	 *            Simulation in der dieser Supplier laeuft
	 */
	public Supplier(Simulation simulation) {
		this.random = new Random();
		this.simulation = simulation;
		this.changePartType();
	}

	/**
	 * Waehrend ein Lieferanten Thread laeuft uebergibt er die von Supply zur
	 * Verfuegung gestellten Teile einem Lagerarbeiter.
	 * 
	 */
	public void run() {
		while (this.isRunning()) {
			// verwende zufallszahl um den momentan gelieferten part zu wechseln
			if (this.random.nextInt(SUPPLY_CHANGE_POSSIBILITY) == 0) {
				this.changePartType();
			}
			// erzeuge int array um die zufaelligen Zahlen zu generieren
			int[] numbers = new int[SUPPLY_NUMBERAMOUNT];
			for (int i = 0; i < SUPPLY_NUMBERAMOUNT; i++) {
				// generiere neue zufaellige Zahl und fuege sie dem array hinzu
				numbers[i] = this.random.nextInt(SUPPLY_MAXNUMBER);
			}
			// erstelle part aus und uebergebe dieses dem lagermitarbeiter
			Part part = new Part(currentPartType, numbers);
			this.simulation.getWarehouser().storeSupply(new Supply(part));
			LOGGER.debug("Delivered new " + part.toString());
		}
	}

	private void changePartType() {
		// Setzt den part der momentan geliefert ist auf einen zufälligen aus
		// dem enum
		this.currentPartType = PartType.values()[this.random.nextInt(PartType.values().length)];
	}

}
