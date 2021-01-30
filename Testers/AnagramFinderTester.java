package Testers;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Past_Assignments.AnagramMain;
import Past_Assignments.AnagramSolver;
import Past_Assignments.LetterInventory;
import Past_Assignments.Stopwatch;

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

public class AnagramFinderTester {

	private static final String testCaseFileName = "testCaseAnagrams.txt";
	private static final String dictionaryFileName = "d3.txt";
	private static int testNum = 0;

	/**
	 * main method that executes tests.
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args) {

		cs314StudentTestsForLetterInventory();

		// tests on the anagram solver itself
		boolean displayAnagrams = getChoiceToDisplayAnagrams();
		AnagramSolver solver = new AnagramSolver(
				AnagramMain.readWords(dictionaryFileName));
		runAnagramTests(solver, displayAnagrams);
	}

	private static void cs314StudentTestsForLetterInventory() {
		LetterInventory inv;

		// Test 1, Constuctor String param is not null -----
		testNum++;
		try {
			inv = new LetterInventory(null);
		} catch (IllegalArgumentException e) {
			printTest(true, "Constuctor String param is not null");
		}

		// Test 2, Constructor ignores non-English characters -----
		testNum++;
		inv = new LetterInventory(
				" <>?:\"{}|~!@#$%^&*()_+,./;'[]\\`123456789-=");
		printTest(inv.isEmpty(), "Constructor ignores non-English characters");

		// Test 3, Constructor is case insensitive -----
		testNum++;
		String fullAlphabet = " bcdefghijklmnopqrstuvwxyzBCDEFGHIJKLMNOPQRSTUVWXYZ";
		inv = new LetterInventory(fullAlphabet);
		printTest(inv.size() == 50, "Constructor is case insensitive");

		// Test 4, get char param is an English letter -----
		testNum++;
		try {
			inv.get(' ');
		} catch (IllegalArgumentException e) {
			printTest(true, "get char param is an English letter");
		}

		// Test 5, get is case insensitive -----
		testNum++;
		int lower = inv.get('z');
		int upper = inv.get('Z');
		printTest(lower == upper, "get is case insensitive");

		// Test 6, get an exluded letter freq is 0 -----
		testNum++;
		lower = inv.get('a');
		upper = inv.get('A');
		printTest(lower == upper, "get an exluded letter freq is 0");

		// Test 7, size of non-English letters -----
		testNum++;
		inv = new LetterInventory(
				" <>?:\"{}|~!@#$%^&*()_+,./;'[]\\`123456789-=");
		printTest(inv.size() == 0, "size of non-English letters");

		// Test 8, size of arbitrary LetterInventory -----
		testNum++;
		inv = new LetterInventory("ajdnSAIiohfIFnfi");
		printTest(inv.size() == 16, "size of arbitrary LetterInventory");

		// Test 9, isEmpty is false -----
		testNum++;
		printTest(!inv.isEmpty(), "isEmpty is false");

		// Test 10, isEmpty is true -----
		testNum++;
		inv = new LetterInventory("");
		printTest(inv.isEmpty(), "isEmpty is true");

		// Test 11, toString non-English letters -----
		testNum++;
		inv = new LetterInventory(
				" <>?:\"{}|~!@#$%^&*()_+,./;'[]\\`123456789-=");
		printTest(inv.toString().equals(""), "toString non-English letters");

		// Test 12, toString of arbitrary LetterInventory -----
		testNum++;
		inv = new LetterInventory("Tack Mee Mack Loff");
		String expectedString = "aacceeffkklmmot";
		printTest(inv.toString().equals(expectedString),
				"toString of arbitrary LetterInventory");

		// Test 13, add empty LetterInventory's -----
		testNum++;
		inv = new LetterInventory("");
		LetterInventory inv2 = new LetterInventory("\t\n");
		LetterInventory inv3 = inv.add(inv2);
		int size = 0;
		expectedString = "";
		printTest(inv3.size() == 0 && inv.toString().equals(expectedString),
				"add empty LetterInventory's");

		// Test 14, add valid LetterInventory's -----
		testNum++;
		inv = new LetterInventory("Tack Mee Mack Loff zzz");
		inv2 = new LetterInventory("bam123pow");
		inv3 = inv.add(inv2);
		size = 24;
		expectedString = "aaabcceeffkklmmmooptwzzz";
		printTest(inv3.size() == size && inv3.toString().equals(expectedString),
				"add valid LetterInventory's");

		// Test 15, subtract valid LetterInventory's -----
		testNum++;
		inv3 = inv3.subtract(inv2);
		size = 18;
		expectedString = "aacceeffkklmmotzzz";
		printTest(inv3.toString().equals(expectedString) && inv3.size() == size,
				"subtract");

		// Test 16, subtract nothing -----
		testNum++;
		inv3 = inv3.subtract(new LetterInventory(""));
		printTest(inv3.toString().equals(expectedString) && inv3.size() == size,
				"subtract nothing");

		// Test 17, subtract returns null -----
		testNum++;
		inv2 = inv3.subtract(inv2);
		printTest(inv2 == null, "subract returns null");

		// Test 18, equals is true -----
		testNum++;
		printTest(inv3.equals(inv), "equals is true");

		// Test 19, equals is false -----
		testNum++;
		inv2 = new LetterInventory("");
		printTest(!inv3.equals(inv2), "equals is false");
	}

	public static void printTest(boolean passed, String testDescription) {
		if (passed) {
			System.out.println(
					"Passed test " + testNum + ", " + testDescription + "\n");
		} else {
			System.out.println(
					"[FAILED] test" + testNum + ", " + testDescription + "\n");
		}
	}

	private static boolean getChoiceToDisplayAnagrams() {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter y or Y to display anagrams during tests: ");
		String response = console.nextLine();
		console.close();
		return response.length() > 0 && response.toLowerCase().charAt(0) == 'y';
	}

	/**
	 * Method to run tests on Anagram solver itself. pre: the files d3.txt and
	 * testCaseAnagrams.txt are in the local directory
	 * 
	 * assumed format for file is <NUM_TESTS> <TEST_NUM> <MAX_WORDS> <PHRASE>
	 * <NUMBER OF ANAGRAMS> <ANAGRAMS>
	 */
	private static void runAnagramTests(AnagramSolver solver,
			boolean displayAnagrams) {

		int solverTestCases = 0;
		int solverTestCasesPassed = 0;
		Stopwatch st = new Stopwatch();
		try {
			Scanner sc = new Scanner(new File(testCaseFileName));
			final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
			System.out.println(NUM_TEST_CASES);
			for (int i = 0; i < NUM_TEST_CASES; i++) {
				// expected results
				TestCase currentTest = new TestCase(sc);
				solverTestCases++;
				st.start();
				// actual results
				List<List<String>> actualAnagrams = solver
						.getAnagrams(currentTest.phrase, currentTest.maxWords);
				st.stop();
				if (displayAnagrams) {
					displayAnagrams("actual anagrams", actualAnagrams);
					displayAnagrams("expected anagrams", currentTest.anagrams);
				}

				if (checkPassOrFailTest(currentTest, actualAnagrams))
					solverTestCasesPassed++;
				System.out.println("Time to find anagrams: " + st.time());
//				System.out
//						.println("Number of calls to recursive helper method: "
//								+ NumberFormat.getNumberInstance(Locale.US)
//										.format(AnagramSolver.callsCount));
			}
			sc.close();
		} catch (IOException e) {
			System.out.println(
					"\nProblem while running test cases on AnagramSolver. Check"
							+ " that file testCaseAnagrams.txt is in the correct location.");
			System.out.println(e);
			System.out.println(
					"AnagramSolver test cases run: " + solverTestCases);
			System.out.println("AnagramSolver test cases failed: "
					+ (solverTestCases - solverTestCasesPassed));
		}
		System.out
				.println("\nAnagramSolver test cases run: " + solverTestCases);
		System.out.println("AnagramSolver test cases failed: "
				+ (solverTestCases - solverTestCasesPassed));
	}

	// print out all of the anagrams in a list of anagram
	private static void displayAnagrams(String type,
			List<List<String>> anagrams) {

		System.out.println("Results for " + type);
		System.out.println("num anagrams: " + anagrams.size());
		System.out.println("anagrams: ");
		for (List<String> singleAnagram : anagrams) {
			System.out.println(singleAnagram);
		}
	}

	// determine if the test passed or failed
	private static boolean checkPassOrFailTest(TestCase currentTest,
			List<List<String>> actualAnagrams) {

		boolean passed = true;
		System.out.println();
		System.out.println("Test number: " + currentTest.testCaseNumber);
		System.out.println("Phrase: " + currentTest.phrase);
		System.out.println("Word limit: " + currentTest.maxWords);
		System.out.println(
				"Expected Number of Anagrams: " + currentTest.anagrams.size());
		if (actualAnagrams.equals(currentTest.anagrams)) {
			System.out.println("Passed Test");
		} else {
			System.out.println("\n!!! FAILED TEST CASE !!!");
			System.out.println("Recall MAXWORDS = 0 means no limit.");
			System.out.println("Expected number of anagrams: "
					+ currentTest.anagrams.size());
			System.out.println(
					"Actual number of anagrams:   " + actualAnagrams.size());
			if (currentTest.anagrams.size() == actualAnagrams.size()) {
				System.out.println("Sizes the same, "
						+ "but either a difference in anagrams or"
						+ " anagrams not in correct order.");
			}
			System.out.println();
			passed = false;
		}
		return passed;
	}

	// class to handle the parameters for an anagram test
	// and the expected result
	private static class TestCase {

		private int testCaseNumber;
		private String phrase;
		private int maxWords;
		private List<List<String>> anagrams;

		// pre: sc is positioned at the start of a test case
		private TestCase(Scanner sc) {
			testCaseNumber = Integer.parseInt(sc.nextLine().trim());
			maxWords = Integer.parseInt(sc.nextLine().trim());
			phrase = sc.nextLine().trim();
			anagrams = new ArrayList<>();
			readAndStoreAnagrams(sc);
		}

		// pre: sc is positioned at the start of the resulting anagrams
		// read in the number of anagrams and then for each anagram:
		// - read in the line
		// - break the line up into words
		// - create a new list of Strings for the anagram
		// - add each word to the anagram
		// - add the anagram to the list of anagrams
		private void readAndStoreAnagrams(Scanner sc) {
			int numAnagrams = Integer.parseInt(sc.nextLine().trim());
			for (int j = 0; j < numAnagrams; j++) {
				String[] words = sc.nextLine().split("\\s+");
				ArrayList<String> anagram = new ArrayList<>();
				for (String st : words) {
					anagram.add(st);
				}
				anagrams.add(anagram);
			}
			assert anagrams
					.size() == numAnagrams : "Wrong number of angrams read or expected";
		}
	}
}
