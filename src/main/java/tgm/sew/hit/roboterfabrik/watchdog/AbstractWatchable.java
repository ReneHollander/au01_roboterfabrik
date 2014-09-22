package tgm.sew.hit.roboterfabrik.watchdog;

/**
 * Einfache Implementation des Watchable Interface um die Verwendung zu
 * Vereinfachen
 * 
 * @author Samuel Schober
 */
public abstract class AbstractWatchable implements Watchable {

	private boolean running;

	/**
	 * Erstellt einen neuen AbstractWatchable und setzt den running Wert auf
	 * true
	 */
	public AbstractWatchable() {
		this.running = true;
	}

	public void stop() {
		this.running = false;
	}

	/**
	 * Gibt true zurueck, wenn der Watchable noch laufen soll
	 * 
	 * @return ob der Watchable noch laufen soll
	 */
	public boolean isRunning() {
		return this.running;
	}

}
