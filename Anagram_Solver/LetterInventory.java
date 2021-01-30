package Anagram_Solver;
import java.util.Arrays;

/* 
 * Student information for assignment:
 *
 *  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 *  TA name: Andrew
 *  Number of slip days I am using: 1
 */

public class LetterInventory {

	private final String lowercasePhrase;
	private final int[] inventory;
	private final int size;

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private static final int ABC_LENGTH = 26;
	private static final int LOWERCASE_OFFSET = 97;

	/**
	 * Constructs a new LetterInventory object that stroes the number of times
	 * each English letter, a through z occur in a word or phrase. 'a'
	 * corresponds to index 1 as 'z' does 26 for the inventory storage
	 * container. O(N + M) Pre: str != null. Post: int[] inventory stores the
	 * letter inventory of phrase.
	 * 
	 * @param phrase The string to make a letter inventory on
	 */
	public LetterInventory(String phrase) {
		if (phrase == null) {
			throw new IllegalArgumentException(
					"Violation of preconditions: phrase != null");
		}
		lowercasePhrase = buildValidPhrase(phrase);
		inventory = buildInventory();
		size = setSize();
	}

	/*
	 * Removes all non-English letters from the given phrase in the constructor. O(N)
	 * Pre: handled in constructor. Post: returns a lower case version of a
	 * valid phrase.
	 */
	private String buildValidPhrase(String phrase) {
		StringBuilder lowerCaseBuilder = new StringBuilder();

		for (int letter = 0; letter < phrase.length(); letter++) {

			if (inbounds(phrase.charAt(letter))) {
				// append each valid char
				lowerCaseBuilder.append(phrase.charAt(letter));
			}
		}

		return lowerCaseBuilder.toString().toLowerCase();
	}

	/*
	 * O(1) Pre: none. Post: true if param letter is an english letter.
	 */
	private boolean inbounds(char letter) {
		return (letter >= 'a' && letter <= 'z')
				|| (letter >= 'A' && letter <= 'Z');
	}

	/*
	 * Creates an inventory for the constructor by counting the amount of every O(N+M)
	 * letter in lowercasePhrase. Case insensitive. Pre:
	 * none. Post: return a completed inventory.
	 */
	private int[] buildInventory() {
		int[] inventory = new int[ABC_LENGTH];

		for (int phraseIndex = 0; phraseIndex < lowercasePhrase
				.length(); phraseIndex++) {
			// gets the inventory index for the char at phraseIndex
			final int abcIndex = ALPHABET
					.indexOf(lowercasePhrase.charAt(phraseIndex));

			inventory[abcIndex]++;
		}

		return inventory;
	}

	/*
	 * Counts every letter that has a frequency in the phrase. O(M)
	 * Pre: none. Post: size of this LetterInventory.
	 */
	private int setSize() {
		int lettersCount = 0;

		for (int letter = 0; letter < ABC_LENGTH; letter++) {
			lettersCount += inventory[letter];
		}

		return lettersCount;
	}

	/**
	 * Gets the frequency of the param letter. Letter may be upper case or lower
	 * case O(M) Pre: letter must be an English letter.
	 * 
	 * @param letter The char to get its frequency
	 * @return The frequency of letter in inventory
	 */
	public int get(char letter) {
		if (!inbounds(letter)) {
			throw new IllegalArgumentException(
					"Violation of preconditions: letter must be an English letter");
		}
		final String lower = ("" + letter).toLowerCase();

		return inventory[ALPHABET.indexOf(lower)];
	}

	/**
	 * O(1) Pre: none. Post: size of this LetterInventory
	 * 
	 * @return The total number of letters in this LetterInventory
	 */
	public int size() {
		return size;
	}

	/**
	 * O(1) Pre: none.
	 * 
	 * @return true if the size of this LetterInventory is 0
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * All letters in the inventory are listed in alphabetical order. O(N) Pre: none.
	 * 
	 * @return The letters of LetterInventory sorted in alphabetical order.
	 */
	public String toString() {
		final char[] inventoryChars = lowercasePhrase.toCharArray();
		Arrays.sort(inventoryChars);

		StringBuilder sb = new StringBuilder();
		sb.append(inventoryChars);

		return sb.toString();
	}

	/**
	 * Creates a new LetterInventory by adding the frequencies of this and
	 * another LetterInventory. Pre: other != null. Post: Neither this or other
	 * LetterInventory are altered as a result of this method.
	 * 
	 * @param other The other LetterInventory to add to this one.
	 * @return A new LetterInventory creating bye summing this and the other
	 *         LetterInventory.
	 */
	public LetterInventory add(LetterInventory other) {
		if (other == null) {
			throw new IllegalArgumentException(
					"Other LetterInventory cannot be null");
		}

		return new LetterInventory(this.toString() + other.toString());
	}

	/**
	 * Creates a new LetterInventory by subtracting the frequencies of another
	 * LetterInventory from this one. Pre: other != null. Post: Neither this or
	 * other LetterInventory are altered as a result of this method.
	 * 
	 * @param other The other LetterInventory to subtract with this one.
	 * @return A new LetterInventory created by subtracting this and the other
	 *         LetterInventory.
	 */
	public LetterInventory subtract(LetterInventory other) {
		if (other == null) {
			throw new IllegalArgumentException(
					"Other LetterInventory cannot be null");
		}
		StringBuilder sb = new StringBuilder();

		// gets the freq of a letter in inventory
		for (int letter = 0; letter < ABC_LENGTH; letter++) {
			final int newFreq = inventory[letter] - other.inventory[letter];

			// cannot have a negative amount of letters
			if (newFreq < 0) {
				return null;
			}

			final char copy = (char) (letter + LOWERCASE_OFFSET);
			// appends newFreq amount of letters remaining to sb
			for (int count = 0; count < newFreq; count++) {
				// converts int 0 - 26 to its lower case letter ascii
				sb.append(copy);
			}
		}

		return new LetterInventory(sb.toString());
	}

	/**
	 * Overrides Objects equals method. Two LetterInventorys are equal if the
	 * frequency for each letter in the alphabet is the same.
	 */
	public boolean equals(Object obj) {
		boolean result = obj instanceof LetterInventory;

		if (result) {
			LetterInventory other = (LetterInventory) obj;
			result = size == other.size;

			if (result) {
				for (int freq = 0; freq < ABC_LENGTH; freq++) {
					if (inventory[freq] != other.inventory[freq]) {
						return false;
					}
				}
			}
		}

		return result;
	}
}
