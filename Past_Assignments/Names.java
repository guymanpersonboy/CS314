package Past_Assignments;
/*  Student information for assignment:
*
*  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: cc66496
*  email address: chris.carrasco@att.net
*  Number of slip days I am using: 0
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A collection of NameRecords. Stores NameRecord objects and provides methods
 * to select NameRecords based on various criteria.
 */
public class Names {

	private ArrayList<NameRecord> records;
	private final int BASE_DECADE;
	private final int DECADES;

	/**
	 * Construct a new Names object based on the data source the Scanner sc is
	 * connected to. Assume the first two lines in the data source are the base
	 * year and number of decades to use. Any lines without the correct number
	 * of decades are discarded and are not part of the resulting Names object.
	 * Any names with ranks of all 0 are discarded and not part of the resulting
	 * Names object.
	 * 
	 * @param sc Is connected to a data file with baby names and positioned at
	 *           the start of the data source.
	 */
	public Names(Scanner sc) {
		if (sc == null) {
			throw new IllegalArgumentException("Scanner cannot be null");
		}
		records = new ArrayList<>();
		BASE_DECADE = sc.nextInt();
		sc.nextLine();
		DECADES = sc.nextInt();
		sc.nextLine();
		// fills records with valid name entries
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			String[] parsedLine = line.split("\\s+");

			if (parsedLine.length - 1 == DECADES
					&& rankedAtLeastOnce(parsedLine)) {
				records.add(new NameRecord(parsedLine, BASE_DECADE));
			}
		}
		Collections.sort(records);
	}

	private boolean rankedAtLeastOnce(String[] parsedLine) {
		boolean ranked = false;
		int rank = 1;

		while (!ranked && rank <= DECADES) {
			if (Integer.parseInt(parsedLine[rank]) != 0) {
				ranked = true;
			}
			rank++;
		}

		return ranked;
	}

	/**
	 * Returns an ArrayList of NameRecord objects that contain a given
	 * substring, ignoring case. The names must be in sorted order based on
	 * name.
	 * 
	 * @param partialName != null, partialName.length() > 0
	 * @return an ArrayList of NameRecords whose names contains partialName. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */
	public ArrayList<NameRecord> getMatches(String partialName) {
		if (partialName == null || partialName.length() == 0) {
			throw new IllegalArgumentException(
					"Violation of Precondition: partialName != null, partialName.length() > 0");
		}
		ArrayList<NameRecord> partialNameMatches = new ArrayList<>();
		String partialLowerCase = partialName.toLowerCase();

		for (NameRecord record : records) {
			if (record.getName().toLowerCase()
					.contains(partialLowerCase)) {
				partialNameMatches.add(record);
			}
		}

		return partialNameMatches;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better for every decade. The Strings must be in sorted order
	 * based on name.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or
	 *         better in every decade. The list is in sorted ascending order. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */

	public ArrayList<String> rankedEveryDecade() {
		ArrayList<String> rankedEveryNames = new ArrayList<>();

		for (int record = 0; record < records.size(); record++) {
			if (records.get(record).everyDecadeRanked()) {
				rankedEveryNames.add(records.get(record).getName());
			}
		}

		return rankedEveryNames;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better in exactly one decade. The Strings must be in sorted order
	 * based on name.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or
	 *         better in exactly one decade. The list is in sorted ascending
	 *         order. If there are no NameRecords that meet this criteria
	 *         returns an empty list.
	 */
	public ArrayList<String> rankedOnlyOneDecade() {
		ArrayList<String> rankedOnceNames = new ArrayList<>();

		for (int record = 0; record < records.size(); record++) {
			if (records.get(record).rankedOnce()) {
				rankedOnceNames.add(records.get(record).getName());
			}
		}

		return rankedOnceNames;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting more
	 * popular every decade. The Strings must be in sorted order based on name.
	 * 
	 * @return A list of the names that have been getting more popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> alwaysMorePopular() {
		ArrayList<String> morePopularNames = new ArrayList<>();

		for (int record = 0; record < records.size(); record++) {
			if (records.get(record).increasingPopularity()) {
				morePopularNames.add(records.get(record).getName());
			}
		}

		return morePopularNames;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting less
	 * popular every decade. The Strings must be in sorted order based on name.
	 * 
	 * @return A list of the names that have been getting less popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> alwaysLessPopular() {
		ArrayList<String> lessPopularNames = new ArrayList<>();

		for (int record = 0; record < records.size(); record++) {
			if (records.get(record).decreasingPopularity()) {
				lessPopularNames.add(records.get(record).getName());
			}
		}

		return lessPopularNames;
	}

	/**
	 * Return the NameRecord in this Names object that matches the given String,
	 * ignoring case. <br>
	 * <tt>pre: name != null</tt>
	 * 
	 * @param name The name to search for.
	 * @return The name record with the given name or null if no NameRecord in
	 *         this Names object contains the given name.
	 */
	public NameRecord getName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(
					"The parameter name cannot be null");
		}
		for (NameRecord record : records) {
			if (record.getName().toLowerCase().equals(name.toLowerCase())) {
				return record;
			}
		}

		return null;
	}

	/**
	 * Calculates the sum of a name's characters converted to ascii integers and
	 * determines if it is a prime number. Returns a String in the format of
	 * "NAME is (not) prime: ASCII SUM".
	 * 
	 * @param name The name to search for and test for prime.
	 * @return a String of if a name's characters converted to ascii integer sum
	 *         to a prime number.
	 */
	public String primeName(String name) {
		int asciiSum = 0;

		for (int letter = 0; letter < name.length(); letter++) {
			// for every character in the name convert to ascii
			asciiSum += (int) name.charAt(letter);
		}
		if (isPrime(asciiSum)) {
			return name + " is prime: " + asciiSum;
		} else {
			return name + " is not prime: " + asciiSum;
		}
	}

	/**
	 * Calculates the sum of a name's characters converted to ascii integers and
	 * determines if it is a prime number. Do this for every name in records and
	 * return an ArrayList of Strings for the names. A String is in the format
	 * of "NAME: ASCII SUM".
	 * 
	 * @return An ArrayList of the names who's ascii characters sum to a prime
	 *         number and that number.
	 */
	public ArrayList<String> primeName() {
		ArrayList<String> primeNames = new ArrayList<>();

		// checks every NameRecord
		for (NameRecord record : records) {
			String name = record.getName();
			int asciiSum = 0;

			for (int letter = 0; letter < name.length(); letter++) {
				// for every character in the name convert to ascii
				asciiSum += (int) name.charAt(letter);
			}
			if (isPrime(asciiSum)) {
				primeNames.add(name + ": " + asciiSum);
			}
		}
		
		return primeNames;
	}

	/*
	 * Pre: none. Post: return true if num is prime
	 */
	private boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		for (int factor = 2; factor < num; factor++) {
			if (num % factor == 0) {
				return false;
			}
		}

		return true;
	}

}
