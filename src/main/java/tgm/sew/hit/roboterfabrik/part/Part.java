package tgm.sew.hit.roboterfabrik.part;

import java.util.Arrays;
import java.util.List;

import at.rene8888.fastcsv.FastCSVRecord;

/**
 * Part setzt den Typ und die ID Nummer eines Teiles/mehrerer Teile zusammen.
 * Dieses fertige Teil kann dann weiterverarbeitet werden.
 * 
 * @author Samuel Schober
 *
 */
public class Part {

	private PartType type;
	private int[] numbers;

	/**
	 * @param type
	 * @param numbers
	 */
	public Part(PartType type, int[] numbers) {
		this.type = type;
		this.numbers = numbers;
	}

	/**
	 * Der Typ des Teiles
	 * 
	 * @return type
	 */
	public PartType getPartType() {
		return this.type;
	}

	/**
	 * Die Nummern der einzelnen Teile
	 * 
	 * @return numbers
	 */
	public int[] getNumbers() {
		return this.numbers;
	}

	/**
	 * Setzt die ID Nummer fuer ein (mehrere Teile z.B. bei Arm, Auge) Teil
	 * 
	 * @param numbers
	 */
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	/**
	 * Erstellt einen CSV Record der in das Lager geschrieben wird
	 * 
	 * @return CSV Record
	 */
	public FastCSVRecord getCSVRecord() {
		FastCSVRecord record = new FastCSVRecord();
		record.addField(this.getPartType().getName());
		for (int i : this.numbers) {
			record.addField(String.valueOf(i));
		}
		return record;
	}

	/**
	 * Deserialisiert einen CSV Record zu einem Part
	 * 
	 * @param csvRecord
	 *            CSV Record der deseialisiert werden soll
	 * @return Deserialisierter Part
	 */
	public static Part readFromCSV(FastCSVRecord csvRecord) {
		List<String> fields = csvRecord.getFields();
		String name = fields.get(0);
		int[] numbers = new int[fields.size() - 1];
		for (int i = 1; i < fields.size(); i++) {
			numbers[i - 1] = Integer.parseInt(fields.get(i));
		}
		return new Part(PartType.fromName(name), numbers);
	}

	@Override
	public String toString() {
		return "Part [type=" + type + ", numbers=" + Arrays.toString(numbers) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(numbers);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		if (!Arrays.equals(numbers, other.numbers))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
