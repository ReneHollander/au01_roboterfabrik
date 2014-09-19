package tgm.sew.hit.roboterfabrik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *Das Office verteilt IDs an alle Arbeiter (Employee) und Threadees.
 *Jeder Arbeiter bekommt eine eindeutige ID.
 *Jeder Threadee bekommt eine eindeutige ID.
 * 
 * @author Simon Wortha
 */
public class Office {
	private int workerId;
	private int threadeeId;
	private static final Logger LOGGER = LogManager.getLogger(Office.class);
	
	/**
	 * Erzeugt ein neues Office
	 * Setzt beim Start zuerst alle IDs auf Null
	 */
	public Office() {
		this.workerId = 0;
		this.threadeeId = 0;
	}
	/**
	 * Das Office erstellt die ID für einen Worker (und zählt immer +1 dazu)
	 * 
	 * @return gibt einen int Wert, bzw. die ID für einen Worker zurück
	 */
	public int generateWorkerID() {
		return ++workerId;
	}
	/**
	 * Das Office erstellt die ID für einen Threadee (und zählt immer +1 dazu)
	 * 
	 * @return gibt einen int Wert, bzw. die ID für einen Threadde zurück
	 */
	public int generateThreadeeID() {
		return ++threadeeId;
	}

}
