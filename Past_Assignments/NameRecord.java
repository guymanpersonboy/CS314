package Past_Assignments;
/*  Student information for assignment:
*
*  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: cc66496
*  email address: chris.carrasco@att.net
*  Grader: Andrew
*  Number of slip days I am using: 0
*/
import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord> {

	private final int BASE_DECADE;
	private final String NAME;
	private final ArrayList<Integer> RANKS;

	private static final int NOT_RANKED = 0;

	/**
	 * Constructs a NameRecord for a name. Each NameRecord object stores the
	 * data for an individual name based on the String[] parsedLine and int
	 * baseDecade passed in. Stores the base decade, name itself, and the
	 * rank of the name for each decade.
	 * 
	 * @param rawData    A line of the data for an individual name.
	 * @param baseDecade The decade of the first rank.
	 */
	public NameRecord(String[] parsedLine, int baseDecade) {
		BASE_DECADE = baseDecade;
		NAME = parsedLine[0];

		RANKS = new ArrayList<>();
		for (int rank = 1; rank < parsedLine.length; rank++) {
			RANKS.add(Integer.parseInt(parsedLine[rank]));
		}
	}

	/**
	 * A method to get the name for this NameRecord.
	 * 
	 * @return the name for this NameRecord.
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * A method that returns the base decade (decade of the first rank) for this
	 * NameRecord.
	 * 
	 * @return the base decade for this NameRecord.
	 */
	public int getBaseDecade() {
		return BASE_DECADE;
	}

	/**
	 * A method that returns the NameRecords rank for a given decade. Uses the
	 * convention that 0 is the start decade. Pre: 0 <= decade < totalDecades.
	 * 
	 * @param decade The decade to get the rank of.
	 * @return the rank for the given decade.
	 */
	public int getRank(int decade) {
		if (decade < 0 || decade >= RANKS.size()) {
			throw new IllegalArgumentException("Decade is out of range");
		}

		return RANKS.get(decade);
	}

	/**
	 * A method that returns an int for this NameRecord's best decade. In other
	 * words it returns the decade this name was most popular, using the most
	 * recent decade in the event of a tie.
	 * 
	 * @return the best decade
	 */
	public int getBestDecade() {
		int bestDecade = 0;
		int bestRank = 1001;

		for (int rankPos = 0; rankPos < RANKS.size(); rankPos++) {
			final int CURRENT_RANK = RANKS.get(rankPos);
			// if current rank is smaller than bestRank than it is better
			if (CURRENT_RANK > NOT_RANKED && CURRENT_RANK < bestRank) {
				bestDecade = BASE_DECADE + rankPos * 10;
				bestRank = CURRENT_RANK;
			}
		}

		return bestDecade;
	}

	/**
	 * A method that returns the number of decades this name has been ranked in
	 * the top 1000.
	 * 
	 * @return the number of decades ranked.
	 */
	public int getDecadesRanked() {
		int decadesRanked = 0;

		for (int rankPos = 0; rankPos < RANKS.size(); rankPos++) {
			if (RANKS.get(rankPos) > NOT_RANKED) {
				decadesRanked++;
			}
		}

		return decadesRanked;
	}

	/**
	 * A method that returns true if this name has been ranked in the top 1000
	 * in every decade.
	 * 
	 * @return true if ranks has no 0's for a rank
	 */
	public boolean everyDecadeRanked() {
		for (int rankPos = 0; rankPos < RANKS.size(); rankPos++) {
			if (RANKS.get(rankPos) == NOT_RANKED) {
				return false;
			}
		}

		return true;
	}

	/**
	 * a method that returns true if this name has been ranked in the top 1000
	 * in only one decade
	 * 
	 * @return true if this names has been ranked only once
	 */
	public boolean rankedOnce() {
		int timesRanked = 0;
		int rankPos = 0;

		// iterates through ranks until at least 2 ranks have been found
		while (timesRanked < 2 && rankPos < RANKS.size()) {
			if (RANKS.get(rankPos) > NOT_RANKED) {
				timesRanked++;
			}
			rankPos++;
		}

		return timesRanked == 1;
	}

	/**
	 * A method that returns true if this name has been getting more popular
	 * every decade in the time period covered. This will be true if every
	 * decades rank is better (closer to 1) than the previous decade. The rank
	 * must improve every decade, it cannot be equal to the previous decade.
	 * 
	 * @return true if the integers representing ranks have been monotonically
	 *         decreasing
	 */
	public boolean increasingPopularity() {
		int previousRank = RANKS.get(0);

		// for each rank after the first
		for (int rankPos = 1; rankPos < RANKS.size(); rankPos++) {
			final int CURRENT_RANK = RANKS.get(rankPos);
			// if two consecutive decades where the same rank
			// or the current rank is worse than the previous
			if (CURRENT_RANK == previousRank || CURRENT_RANK == NOT_RANKED
					|| (previousRank != NOT_RANKED
							&& CURRENT_RANK > previousRank)) {
				return false;
			}
			previousRank = CURRENT_RANK;
		}

		return true;
	}

	/**
	 * A method that returns true if this name has been getting less popular.
	 * This will be true if every decades rank is worse than the previous
	 * decade. The rank must get worse, it cannot be equal to the previous
	 * decade.
	 * 
	 * @return true if the integers representing ranks have been monotonically
	 *         increasing
	 */
	public boolean decreasingPopularity() {
		int previousRank = RANKS.get(0);

		// for each rank after the first
		for (int rankPos = 1; rankPos < RANKS.size(); rankPos++) {
			final int CURRENT_RANK = RANKS.get(rankPos);
			// if two consecutive decades where the same rank
			// or the current rank is better than the previous
			if (CURRENT_RANK == previousRank
					|| (previousRank == NOT_RANKED && CURRENT_RANK > 0)
					|| (CURRENT_RANK != NOT_RANKED
							&& CURRENT_RANK < previousRank)) {
				return false;
			}
			previousRank = CURRENT_RANK;
		}

		return true;
	}

	/**
	 * Overrides to String to represent a line of data for a name. If a name is
	 * not ranked in a given decade the Rank shall be a zero.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(NAME + "\n");
		for (int rankPos = 0; rankPos < RANKS.size(); rankPos++) {
			sb.append((BASE_DECADE + 10 * rankPos) + ": " + RANKS.get(rankPos)
					+ "\n");
		}

		return sb.toString();
	}

	@Override
	public int compareTo(NameRecord other) {
		return NAME.compareTo(other.NAME);
	}

}
