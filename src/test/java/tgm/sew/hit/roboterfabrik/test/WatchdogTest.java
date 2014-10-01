package tgm.sew.hit.roboterfabrik.test;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.watchdog.Watchable;
import tgm.sew.hit.roboterfabrik.watchdog.Watchdog;

/**
 * Tests fuer den Watchdog
 * 
 * @author Rene Hollander, Samuel Schober
 */
public class WatchdogTest {

	/**
	 * Test ob ein Watchdog erstellt und ein Watchable hinzugefuegt werden kann
	 */
	@Test
	public void testCreateWatchdog() {
		Watchdog w = new Watchdog(10);
		Watchable wa = new Watchable() {
			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		};
		w.registerWatchable(wa);
	}

	/**
	 * Testet ob der Watchdog gestartet und gestoppt werden kann
	 */
	@Test
	public void testStartStopWatchdog() {
		Watchdog w = new Watchdog(10);
		Watchable wa = new Watchable() {
			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		};
		w.registerWatchable(wa);
		w.startWatchdog();
		w.stopWatchdog();
	}

	/**
	 * Testet ob das doppelte Starten eines Watchdog einen Fehler wirft
	 */
	@Test(expected = IllegalStateException.class)
	public void testStartWatchdogTwice() {
		Watchdog w = new Watchdog(10);
		Watchable wa = new Watchable() {
			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		};
		w.registerWatchable(wa);
		w.startWatchdog();
		w.startWatchdog();
	}

	/**
	 * Testet ob das stoppen eines ungestarteten Watchdog einen Fehler wirft
	 */
	@Test(expected = IllegalStateException.class)
	public void testStopUnstartedWatchdog() {
		Watchdog w = new Watchdog(10);
		Watchable wa = new Watchable() {
			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		};
		w.registerWatchable(wa);
		w.stopWatchdog();
	}
}
