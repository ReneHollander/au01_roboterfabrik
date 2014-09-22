package tgm.sew.hit.roboterfabrik;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Das Office verteilt IDs an alle Arbeiter (Employee) und Threadees. Jeder
 * Arbeiter bekommt eine eindeutige ID. Jeder Threadee bekommt eine eindeutige
 * ID.
 * 
 * @author Simon Wortha
 */
public class Office {

	private static final Logger LOGGER = LogManager.getLogger(Office.class);

	private Lock lock;
	private int workerId;
	private int threadeeId;

	/**
	 * Erzeugt ein neues Office Setzt beim Start zuerst alle IDs auf Null
	 */
	public Office() {
		this.lock = new ReentrantLock();
		this.workerId = 0;
		this.threadeeId = 0;
	}

	/**
	 * Das Office erstellt die ID fuer einen Worker (und zaehlt immer +1 dazu)
	 * 
	 * @return gibt einen int Wert, bzw. die ID fuer einen Worker zurueck
	 */
	public int generateWorkerID() {
		lock.lock();
		workerId++;
		int id = workerId;
		lock.unlock();
		LOGGER.debug("new worker-id assigned: " + id);
		return id;
	}

	/**
	 * Das Office erstellt die ID fuer einen Threadee (und zaehlt immer +1 dazu)
	 * 
	 * @return gibt einen int Wert, bzw. die ID fuer einen Threadde zurueck
	 */
	public int generateThreadeeID() {
		lock.lock();
		threadeeId++;
		int id = threadeeId;
		lock.unlock();
		LOGGER.debug("new threadeeId-id assigned: " + id);
		return id;
	}

}
