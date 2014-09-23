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
	private long workerId;
	private long threadeeId;
	private long supplierId;

	/**
	 * Erzeugt ein neues Office Setzt beim Start zuerst alle IDs auf Null
	 */
	public Office() {
		this.lock = new ReentrantLock();
		this.workerId = 0;
		this.threadeeId = 0;
		this.supplierId = 0;
	}

	/**
	 * Das Office erstellt die ID fuer einen Worker (und zaehlt immer +1 dazu)
	 * 
	 * @return gibt einen long Wert, bzw. die ID fuer einen Worker zurueck
	 */
	public long generateWorkerID() {
		lock.lock();
		workerId++;
		long id = workerId;
		lock.unlock();
		LOGGER.debug("new worker-id assigned: " + id);
		return id;
	}

	/**
	 * Das Office erstellt die ID fuer einen Threadee (und zaehlt immer +1 dazu)
	 * 
	 * @return gibt einen long Wert, bzw. die ID fuer einen Threadde zurueck
	 */
	public long generateThreadeeID() {
		lock.lock();
		threadeeId++;
		long id = threadeeId;
		lock.unlock();
		LOGGER.debug("new threadeeId-id assigned: " + id);
		return id;
	}

	/**
	 * Das Office erstellt die ID fuer einen Supplier (und zaehlt immer +1 dazu)
	 * 
	 * @return gibt einen long Wert, bzw. die ID fuer einen Supplier zurueck
	 */
	public long generateSupplierID() {
		lock.lock();
		supplierId++;
		long id = supplierId;
		lock.unlock();
		LOGGER.debug("new supplierId-id assigned: " + id);
		return id;
	}

}
