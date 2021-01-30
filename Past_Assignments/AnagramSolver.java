package Past_Assignments;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class AnagramSolver {

	private final Set<String> dictionary;
	private ArrayList<String> words;

	private static int callsCount = 0;

	/*
	 * pre: list != null
	 * 
	 * @param list Contains the words to form anagrams from.
	 */
	public AnagramSolver(Set<String> dictionary) {
		if (dictionary == null) {
			throw new IllegalArgumentException("the list cannot be null");
		}
		this.dictionary = dictionary;
		words = new ArrayList<>();
	}

	/*
	 * pre: maxWords >= 0, s != null, s contains at least one English letter.
	 * Return a list of anagrams that can be formed from s with no more than
	 * maxWords, unless maxWords is 0 in which case there is no limit on the
	 * number of words in the anagram
	 */
	public List<List<String>> getAnagrams(String s, int maxWords) {
		// checks preconditions and creates inventory for given String s
		final LetterInventory sInv = metPreconditions(s, maxWords);

		if (maxWords == 0) {
			maxWords = Integer.MAX_VALUE;
		}
		List<List<String>> anagrams = new ArrayList<>();
		limitDictionary(sInv);

		fillAnagrams(anagrams, sInv, new Stack<String>(), 0, maxWords);
		Collections.sort(anagrams, new AnagramComparator());

		return anagrams;
	}

	/*
	 * Checks the preconditions for getAnagrams. Pre: maxWords >= 0, s != null,
	 * s contains at least one English letter. Post: a valid LetterInventory.
	 */
	private LetterInventory metPreconditions(String s, int maxWords) {
		if (maxWords < 0 || s == null) {
			throw new IllegalArgumentException("Violation of preconditions:"
					+ " maxWords >= 0, s != null, s contains at least one English letter.");
		}
		final LetterInventory sInv = new LetterInventory(s);

		if (sInv.isEmpty()) {
			throw new IllegalArgumentException("Violation of preconditions:"
					+ " maxWords >= 0, s != null, s contains at least one English letter.");
		}

		return sInv;
	}

	/*
	 * Recursively fills the anagrams List using instance variable words as its
	 * dictionary. Pre: handeled in calling method. Post: anagrams is filled.
	 */
	private void fillAnagrams(List<List<String>> anagrams, LetterInventory sInv,
			Stack<String> resultSoFar, int prevIndex, int maxWords) {
		final LetterInventory currentInv = new LetterInventory(
				resultSoFar.toString());

		// base case
		if (currentInv.equals(sInv)) {
			// deep copy to add list to final list
			ArrayList<String> completeOne = new ArrayList<>();

			for (String word : resultSoFar) {
				completeOne.add(word);
			}
			anagrams.add(completeOne);

			// reset resultSoFar to start next mnemonic
			resultSoFar = new Stack<>();
		} else if (maxWords > 0 && sInv.subtract(currentInv) != null) {
			
			// build ArrayList until found an anagram
			for (int index = prevIndex; index < words.size(); index++) {
				final String word = words.get(index);
				resultSoFar.add(word);

				callsCount++;
				fillAnagrams(anagrams, sInv, resultSoFar, index, maxWords - 1);
				resultSoFar.pop();
			}
		}
	}

	/*
	 * Creates an ArrayList to fill with words that are possible for the
	 * anagram. Pre: handled in calling method. Post: instance variable words is
	 * sorted with usable words.
	 */
	private void limitDictionary(LetterInventory sInv) {
		ArrayList<String> newDictionary = new ArrayList<>();

		for (String word : dictionary) {
			LetterInventory inv = new LetterInventory(word);
			LetterInventory result = sInv.subtract(inv);

			// if word does not contains letters not found in sInv or too many
			// of the same letters
			if (result != null) {
				newDictionary.add(word);
			}
		}

		words = newDictionary;
		Collections.sort(words);
	}

	/*
	 * Used to sort Anagrams
	 */
	private static class AnagramComparator implements Comparator<List<String>> {

		@Override
		public int compare(List<String> list1, List<String> list2) {
			if (list1.size() != list2.size()) {
				// least amount of words first
				return list1.size() - list2.size();
			} else {
				// comapres the words themselves if equal length
				for (int index = 0; index < list1.size(); index++) {
					String word1 = list1.get(index);
					String word2 = list2.get(index);

					if (!word1.equals(word2)) {
						return word1.compareTo(word2);
					}
				}

				return 0;
			}
		}
	}
}
