package tgm.sew.hit.roboterfabrik.watchdog;

/**
 * Der Watchdog verwendet dieses Interface um Threads zu stoppen
 * 
 * @author Samuel Schober
 *
 */
public interface Watchable {

	/**
	 * Stoppt den Thread durch den Watchdog
	 */
	public void stop();

}
