package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;

import javax.imageio.spi.RegisterableService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.watchdog.Watchable;
import tgm.sew.hit.roboterfabrik.watchdog.Watchdog;

public class WatchdogTest {

	@Test
	public void testCreateWatchdog() {
		Watchdog w = new Watchdog(10);
	}

	@Test
	public void testStartWatchdog() {
		Watchdog w = new Watchdog(10);
		Watchable wa = new Watchable() {

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		};
		w.registerWatchable(wa);
		w.startWatchdog();
	}

}
