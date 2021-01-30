package Past_Assignments;
/*  Student information for assignment:
 *
 *  On my honor, CHRISTOPHER CARRSACO, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Christopher Carrasco
 *  email address: chris.carrasco@att.net
 *  UTEID: cc66496
 *  Section 5 digit ID: 50875
 *  Grader name: Andrew
 *  Number of slip days used on this assignment: 1
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * Manages the details of EvilHangman. This class keeps tracks of the possible
 * words from a dictionary during rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

	private ArrayList<String> words;
	private boolean debugOn;
	private int wordLen;
	private int numGuesses;
	private HangmanDifficulty diff;
	private ArrayList<String> wordsLeft;
	private ArrayList<String> guessesMade;
	private String prevPattern;
	private String correctGuesses;
	private int guessCount;

	private static final String BLANK_LETTER = "-";
	private static final int LETTER_1 = 0;
	private static final int LETTER_2 = 1;
	private static final int LETTER_3 = 2;
	private static final int LETTER_4 = 3;

	/**
	 * Create a new HangmanManager from the provided set of words and phrases.
	 * pre: words != null, words.size() > 0
	 * 
	 * @param words   A set with the words for this instance of Hangman.
	 * @param debugOn true if we should print out debugging to System.out.
	 */
	public HangmanManager(Set<String> words, boolean debugOn) {
		if (words == null || words.size() == 0) {
			throw new IllegalArgumentException(
					"Violation of preconditions: words != null, words.size() > 0.");
		}
		this.words = new ArrayList<>();
		this.words.addAll(words);
		this.debugOn = debugOn;

		wordsLeft = new ArrayList<>();
		guessesMade = new ArrayList<>();
		prevPattern = "";
		correctGuesses = "";
	}

	/**
	 * Create a new HangmanManager from the provided set of words and phrases.
	 * Debugging is off. pre: words != null, words.size() > 0
	 * 
	 * @param words A set with the words for this instance of Hangman.
	 */
	public HangmanManager(Set<String> words) {
		this(words, false);
	}

	/**
	 * Get the number of words in this HangmanManager of the given length. pre:
	 * none
	 * 
	 * @param length The given length to check.
	 * @return the number of words in the original Dictionary with the given
	 *         length
	 */
	public int numWords(int length) {
		int count = 0;

		for (String word : words) {
			if (word.length() == length) {
				count++;
			}
		}

		return count;
	}

	/**
	 * Get for a new round of Hangman. Think of a round as a complete game of
	 * Hangman.
	 * 
	 * @param wordLen    the length of the word to pick this time.
	 *                   numWords(wordLen) > 0
	 * @param numGuesses the number of wrong guesses before the player loses the
	 *                   round. numGuesses >= 1
	 * @param diff       The difficulty for this round.
	 */
	public void prepForRound(int wordLen, int numGuesses,
			HangmanDifficulty diff) {
		if (numWords(wordLen) == 0 || numGuesses < 1) {
			throw new IllegalArgumentException(
					"Violation of preconditions: Word length > 0, number of guesses >= 1.");
		}
		// sets or resets for a new game
		this.wordLen = wordLen;
		this.numGuesses = numGuesses;
		this.diff = diff;

		// resets for a new game
		guessesMade = new ArrayList<>();
		correctGuesses = "";
		guessCount = 0;

		// sets or resets pattern to length of wordLen using "-"
		prevPattern = "";
		for (int length = 0; length < wordLen; length++) {
			prevPattern += BLANK_LETTER;
		}

		wordsLeft = limitWords();
	}

	/*
	 * Creates a new ArrayList to fill and return with all of the words
	 * available that are of the correct word length
	 */
	private ArrayList<String> limitWords() {
		ArrayList<String> wordsOfLength = new ArrayList<>();

		for (String word : words) {
			if (word.length() == wordLen) {
				wordsOfLength.add(word);
			}
		}

		return wordsOfLength;
	}

	// based on code Claire provided on Piazza @503
	private void debug(String... strings) {
		if (debugOn && strings.length > 0) {
			System.out.print("DEBUGGING: ");
			for (String string : strings) {
				System.out.println(string);
			}
			if (strings.length != 1)
				System.out.println("END DEBUGGING");
		}
	}

	/**
	 * The number of words still possible (live) based on the guesses so far.
	 * Guesses will eliminate possible words.
	 * 
	 * @return the number of words that are still possibilities based on the
	 *         original dictionary and the guesses so far.
	 */
	public int numWordsCurrent() {
		return wordsLeft.size();
	}

	/**
	 * Get the number of wrong guesses the user has left in this round (game) of
	 * Hangman.
	 * 
	 * @return the number of wrong guesses the user has left in this round
	 *         (game) of Hangman.
	 */
	public int getGuessesLeft() {
		return numGuesses;
	}

	/**
	 * Return a String that contains the letters the user has guessed so far
	 * during this round. The characters in the String are in alphabetical
	 * order. The String is in the form [let1, let2, let3, ... letN]. For
	 * example [a, c, e, s, t, z]
	 * 
	 * @return a String that contains the letters the user has guessed so far
	 *         during this round.
	 */
	public String getGuessesMade() {
		Collections.sort(guessesMade);
		StringBuilder sb = new StringBuilder();

		sb.append("[");

		if (!guessesMade.isEmpty()) {
			sb.append(guessesMade.get(0));
		}
		for (int letter = 1; letter < guessesMade.size(); letter++) {
			sb.append(", " + guessesMade.get(letter));
		}

		sb.append("]");

		return sb.toString();
	}

	/**
	 * Check the status of a character.
	 * 
	 * @param guess The character to check.
	 * @return true if guess has been used or guessed this round of Hangman,
	 *         false otherwise.
	 */
	public boolean alreadyGuessed(char guess) {
		for (String letter : guessesMade) {
			if (letter.equals("" + guess)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Get the current pattern. The pattern contains '-''s for unrevealed (or
	 * guessed) characters and the actual character for "correctly guessed"
	 * characters.
	 * 
	 * @return the current pattern.
	 */
	public String getPattern() {
		return prevPattern;
	}

	/**
	 * Update the game status (pattern, wrong guesses, word list), based on the
	 * give guess.
	 * 
	 * @param guess pre: !alreadyGuessed(ch), the current guessed character
	 * @return return a tree map with the resulting patterns and the number of
	 *         words in each of the new patterns. The return value is for
	 *         testing and debugging purposes.
	 */
	public TreeMap<String, Integer> makeGuess(char guess) {
		if (alreadyGuessed(guess)) {
			throw new IllegalStateException("Already guessed");
		}
		guessCount++;
		final String GUESS = ("" + guess).toLowerCase();

		Map<String, ArrayList<String>> patternMap = fillPatternMap(GUESS);
		TreeMap<String, Integer> resultMap = new TreeMap<>();

		// Also fills resultMap to return
		prevPattern = chooseNextWordList(patternMap, resultMap);

		guessesMade.add(GUESS);
		updateNumGuesses(GUESS);
		updateCorrectGuesses();
		// debug("Correct guesses: " + correctGuesses);

		wordsLeft.clear();
		wordsLeft.addAll(patternMap.get(prevPattern));
		debug("New pattern is: " + prevPattern + ". New family has "
				+ wordsLeft.size() + " words.");

		return resultMap;
	}

	/*
	 * Creates a new TreeMap called patternMap and fills it with all
	 * pattern-word family pairs to be returned.
	 */
	private TreeMap<String, ArrayList<String>> fillPatternMap(
			final String GUESS) {
		TreeMap<String, ArrayList<String>> patternMap = new TreeMap<>();
		String[] patterns = prevPattern.split("");

		putPrevPattern(patternMap, patterns, GUESS);
		// debug("putPrevPattern" + patternMap);
		putOtherPatterns(patternMap, patterns, GUESS);
		// debug("putOtherPatterns" + patternMap);

		return patternMap;
	}

	/*
	 * Puts the previous key-value pair in the patternMap excluding words that
	 * do not contain the current GUESS.
	 */
	private void putPrevPattern(TreeMap<String, ArrayList<String>> patternMap,
			String[] patterns, final String GUESS) {
		ArrayList<String> prevFamilyOfWords = new ArrayList<>();

		for (String word : wordsLeft) {
			if (!word.contains(GUESS) && !containPreviousGuesses(word)) {
				prevFamilyOfWords.add(word);
			}
		}
		String prevPatternKey = patternsToString(patterns);
		// add the current pattern to patternMap if there are words
		// for that pattern.
		if (!prevFamilyOfWords.isEmpty()) {
			patternMap.put(prevPatternKey, prevFamilyOfWords);
		}
	}

	/*
	 * Puts all other key-value pairs in the patternMap for words that contain
	 * the current GUESS and fit a pattern with it and previous correct guesses.
	 */
	private void putOtherPatterns(TreeMap<String, ArrayList<String>> patternMap,
			String[] patterns, final String GUESS) {
		String[] temps = { "", "", "", "" };
		// create list of all patterns using the current guess based off
		// previous pattern
		for (int letter1 = 0; letter1 < wordLen; letter1++) {
			temps[LETTER_1] = patterns[letter1];
			patterns[letter1] = GUESS;

			for (int letter2 = letter1; letter2 < wordLen; letter2++) {
				temps[LETTER_2] = patterns[letter2];
				patterns[letter2] = GUESS;

				for (int letter3 = letter2; letter3 < wordLen; letter3++) {
					temps[LETTER_3] = patterns[letter3];
					patterns[letter3] = GUESS;

					for (int letter4 = letter3; letter4 < wordLen; letter4++) {
						temps[LETTER_4] = patterns[letter4];
						patterns[letter4] = GUESS;

						// get the word family for the current pattern and put
						// it in patternMap if there are words
						ArrayList<String> familyOfWords = getFamilyOfWords(
								patterns, GUESS);
						String currentPatternKey = patternsToString(patterns);
						if (!familyOfWords.isEmpty()) {
							patternMap.put(currentPatternKey, familyOfWords);
						}

						// resets the pattern back to PrevPattern change letter2
						patterns[letter4] = temps[LETTER_4];
					}
					// resets the pattern back to prevPattern change letter1
					patterns[letter3] = temps[LETTER_3];
				}
				// resets the pattern back to prevPattern change letter
				patterns[letter2] = temps[LETTER_2];
			}
			// resets the pattern back to prevPattern
			patterns[letter1] = temps[LETTER_1];
		}
	}

	/*
	 * Gets all of the words that fit the current pattern and add it to a new
	 * ArrayList. Returns the ArrayList of words.
	 */
	private ArrayList<String> getFamilyOfWords(String[] patterns,
			final String GUESS) {
		ArrayList<String> familyOfWords = new ArrayList<>();

		for (String word : wordsLeft) {
			boolean sameFamily = true;

			for (int letter = 0; letter < wordLen; letter++) {
				// if letters not "-" or a correct letter are not equal at the
				// same spot in both the pattern and the current word,
				// or a letter is "-" and the word has GUESS in the same spot
				boolean differentLetter = ((!correctGuesses
						.contains(patterns[letter])
						&& !patterns[letter].equals(BLANK_LETTER)
						&& !patterns[letter].equals("" + word.charAt(letter)))
						|| (patterns[letter].equals(BLANK_LETTER)
								&& GUESS.equals("" + word.charAt(letter))));
				if (differentLetter) {
					sameFamily = false;
				}
			}

			if (sameFamily && !containPreviousGuesses(word)) {
				familyOfWords.add(word);
			}
		}

		return familyOfWords;
	}

	/*
	 * Returns true if the word contains a guess that has already been made and
	 * that is not a correct guess
	 */
	private boolean containPreviousGuesses(String word) {
		for (String guess : guessesMade) {
			if (word.contains(guess) && !correctGuesses.contains(guess)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * Converts the current pattern key from a String array to String and
	 * returns it
	 */
	private String patternsToString(String[] patterns) {
		StringBuilder sb = new StringBuilder();

		for (int letter = 0; letter < wordLen; letter++) {
			sb.append(patterns[letter]);
		}

		return sb.toString();
	}

	/*
	 * Chooses either the hardest or second hardest list of words depending on
	 * the desired difficutly
	 */
	private String chooseNextWordList(Map<String, ArrayList<String>> patternMap,
			TreeMap<String, Integer> resultMap) {
		ArrayList<ComparableHangman> comparedPatterns = new ArrayList<>();

		for (String key : patternMap.keySet()) {
			final int FAMILY_SIZE = patternMap.get(key).size();

			comparedPatterns.add(new ComparableHangman(key, FAMILY_SIZE));
			// fills the return TreeMap
			resultMap.put(key, FAMILY_SIZE);
		}
		Collections.sort(comparedPatterns);

		if (diff == HangmanDifficulty.HARD) {
			debug("Picking hardest list.");

			return comparedPatterns.get(0).PATTERN;
		} else if (diff == HangmanDifficulty.MEDIUM
				&& (guessCount % 4 != 0 || comparedPatterns.size() == 1)) {
			debug("Picking hardest list.");

			return comparedPatterns.get(0).PATTERN;
		} else if (diff == HangmanDifficulty.EASY
				&& (guessCount % 2 != 0 || comparedPatterns.size() == 1)) {
			debug("Picking hardest list.");

			return comparedPatterns.get(0).PATTERN;
		} else {
			// MEDIUM or EASY diff and doesn't return hardest pattern
			debug("Picking second hardest pattern and list.");

			return comparedPatterns.get(1).PATTERN;
		}
	}

	/*
	 * If the new prevPattern does not contain the current GUESS, then decrease
	 * the number of guesses
	 */
	private void updateNumGuesses(final String GUESS) {
		boolean correctGuess = false;

		// checks if the new prevPattern has GUESS
		if (prevPattern.contains(GUESS)) {
			correctGuess = true;
		}
		if (!correctGuess) {
			numGuesses--;
		}
	}

	/*
	 * Appends one correct letter to correctGuesses based on the new prevPattern
	 */
	private void updateCorrectGuesses() {
		for (String letter : prevPattern.split("")) {
			if (!letter.equals(BLANK_LETTER)
					&& !correctGuesses.contains(letter)) {
				correctGuesses += letter;
			}
		}
	}

	/**
	 * Return the secret word this HangmanManager finally ended up picking for
	 * this round. If there are multiple possible words left one is selected at
	 * random. <br>
	 * pre: numWordsCurrent() > 0
	 * 
	 * @return return the secret word the manager picked.
	 */
	public String getSecretWord() {
		if (numWordsCurrent() == 0) {
			throw new IllegalStateException(
					"The list of active words cannot be empty.");
		}
		Random rand = new Random();
		int randIndex = rand.nextInt(wordsLeft.size());

		return wordsLeft.get(randIndex);
	}

	/**
	 * Used for compareTo method to allow the HangmanManager to pick the correct
	 * list of words based on the desired difficulty from the user.
	 * 
	 */
	private static class ComparableHangman
			implements Comparable<ComparableHangman> {

		private final String PATTERN;
		private final int FAMILY_SIZE;

		/**
		 * Create a new HangmanDiff from the current pattern and the size of its
		 * word family. Pre: pattern != null, familySize > 0.
		 * 
		 * @param pattern    the pattern key for a word family.
		 * @param familySize the size of that word family.
		 */
		public ComparableHangman(String pattern, int familySize) {
			if (pattern == null || familySize == 0) {
				throw new IllegalArgumentException(
						"Violation of preconditions: pattern != null, familySize > 0.");
			}
			PATTERN = pattern;
			FAMILY_SIZE = familySize;
		}

		/**
		 * Compares the number of words in wordFamily, if tie, then compares the
		 * number of hidden letters in pattern, if tie, then compares the words
		 * in wordFamily using String's compareTo method
		 */
		@Override
		public int compareTo(ComparableHangman other) {
			int thisNumHidden = 0;
			int otherNumHidden = 0;
			String[] thisLetters = PATTERN.split("");
			String[] otherLetters = other.PATTERN.split("");

			// counts number of hidden letters
			for (String letter : thisLetters) {
				if (letter == BLANK_LETTER) {
					thisNumHidden++;
				}
			}
			for (String letter : otherLetters) {
				if (letter == BLANK_LETTER) {
					otherNumHidden++;
				}
			}

			// compares this and other's data
			if (this.FAMILY_SIZE != other.FAMILY_SIZE) {
				
				return other.FAMILY_SIZE - this.FAMILY_SIZE;
			} else if (thisNumHidden != otherNumHidden) {
				
				return otherNumHidden - thisNumHidden;
			} else {
				
				return this.PATTERN.compareTo(other.PATTERN);
			}
		}

		/**
		 * Overrides toString. Used for debugging.
		 */
		public String toString() {
			return "Family size: " + FAMILY_SIZE + " Pattern: " + PATTERN;
		}
	}

}