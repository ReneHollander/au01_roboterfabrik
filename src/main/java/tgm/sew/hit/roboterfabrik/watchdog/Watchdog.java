package tgm.sew.hit.roboterfabrik.watchdog;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Watchdog um das herunterfahren von Threads nach einer bestimmten Zeit
 * 
 * @author Samuel Schober
 *
 */
public class Watchdog {

	private static final Logger LOGGER = LogManager.getLogger(Watchdog.class);

	private ArrayList<Watchable> watchables;
	private Timer t;
	private boolean running;
	private int runFor;

	/**
	 * Erstellt einen neuen Watchdog der mit der Methode startWatchdog gestartet
	 * wird und dann fuer die angegebene Zeit laeuft
	 * 
	 * @param runFor
	 *            Laufzeit in ms
	 */
	public Watchdog(int runFor) {
		this.watchables = new ArrayList<Watchable>();
		this.t = new Timer(true);
		this.runFor = runFor;
	}

	/**
	 * Fueg einen Watchable zu dem Watchdog hinzu
	 * 
	 * @param watchable
	 *            das unter diesem Watchdog laufen soll
	 */
	public void registerWatchable(Watchable watchable) {
		this.watchables.add(watchable);
	}

	/**
	 * Startet den Watchdog und beginnt den Countdown
	 */
	public void startWatchdog() {
		if (this.running == true) {
			throw new IllegalStateException("you cant start a watchdog twice!");
		}
		this.running = true;
		this.t.schedule(new TimerTask() {
			@Override
			public void run() {
				if (Watchdog.this.running) {
					Watchdog.this.stopWatchdog();
				}
			}
		}, this.runFor);
		LOGGER.info("Started Watchdog! Running for " + this.runFor + "ms!");
	}

	/**
	 * Erzwingt einen Shutdown der Watchables
	 */
	public void stopWatchdog() {
		if (this.running == false) {
			throw new IllegalStateException("the watchdog is already stopped!");
		}
		LOGGER.info("Stopping Watchdog and all underlying Watchables");
		this.t.cancel();
		this.t.purge();
		for (Watchable w : this.watchables) {
			w.stop();
		}
	}
}
