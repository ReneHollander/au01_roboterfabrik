package tgm.sew.hit.roboterfabrik;

/**
 * A small Wrapper around a simple integer
 * 
 * @author Rene Hollander
 */
public class IntegerWrapper {

	private int integer;

	/**
	 * Creates a new IntegerWrapper with the provided initial value
	 * 
	 * @param initial
	 *            Integer Value
	 */
	public IntegerWrapper(int initial) {
		this.integer = initial;
	}

	/**
	 * Adds the given amount to the integer
	 * 
	 * @param delta
	 *            Amount to add
	 */
	public void add(int delta) {
		integer += delta;
	}

	/**
	 * Gets the current value of the Integer
	 * 
	 * @return current value
	 */
	public int get() {
		return this.integer;
	}
}
