package Name_Surfer;
/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work 
 * and I have not provided this code to any other student. 
 * UTEID: cc66496
 * email address: chris.carrasco@att.net
 * Number of slip days I am using: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameSurfer {

	private static final String NAME_FILE = "names.txt";

	// Explaining menu option 7 here:
	/*
	 * Prints out if an inputed name's characters converted to ascii integers
	 * sum to a prime number, or, shows a list of all names who's characters
	 * converted to ascii integers sum to a prime number. Prints in the format
	 * of "NAME is (not) prime: ASCII SUM" or "NAME: PRIME NUM"
	 */
	// Interesting search / trend here:
	/*
	 * There's a lot of prime names, 683/4438 in names.txt alone. That's about
	 * 15.4%. The average value for all the prime names was approximately 601
	 * which is also prime. Also, I looked up the name Rolf, which popped out to
	 * me because of the cartoon "Ed, Edd, and Eddy" which has a character of
	 * the same name. Rolf was ranked only once, 1950's. This German name could
	 * have seen an increase after many German immigrants began to come to
	 * America following WW2
	 */

	public static void nameRecordTester() {
		// raw data for Jake. Alter as necessary for your NameRecord
		String adamRawData = "Adam 178 200 280 376 444 407 144 38 22 39 46";
		String[] adamParsedLine = adamRawData.split("\\s+");
		String testRawData = "T3ST 0 0 978 0 0";
		String[] testParsedLine = testRawData.split("\\s+");

		// creates constructors with different base decades
		NameRecord adamRecord = new NameRecord(adamParsedLine, 1900);
		NameRecord testRecord = new NameRecord(testParsedLine, 1950);

		// test 1 and 2, getName
		String expectedString = "Adam";
		if (adamRecord.getName().equals(expectedString)) {
			System.out.println("Test 1 passed, getName");
		} else {
			System.out.println("Test 1 FAILED, getName");
		}
		expectedString = "T3ST";
		if (testRecord.getName().equals(expectedString)) {
			System.out.println("Test 2 passed, getName");
		} else {
			System.out.println("Test 2 FAILED, getName");
		}

		// test 3 and 4, getBaseDecade
		int expectedInt = 1900;
		if (adamRecord.getBaseDecade() == expectedInt) {
			System.out.println("Test 3 passed, getBaseDecade");
		} else {
			System.out.println("Test 3 FAILED, getBaseDecade");
		}
		expectedInt = 1950;
		if (testRecord.getBaseDecade() == expectedInt) {
			System.out.println("Test 4 passed, getBaseDecade");
		} else {
			System.out.println("Test 4 FAILED, getBaseDecade");
		}

		// test 5 and 6, getRank
		expectedInt = 46;
		if (adamRecord.getRank(10) == expectedInt) {
			System.out.println("Test 5 passed, getRank");
		} else {
			System.out.println("Test 5 FAILED, getRank");
		}
		expectedInt = 0;
		if (testRecord.getRank(0) == expectedInt) {
			System.out.println("Test 6 passed, getRank");
		} else {
			System.out.println("Test 6 FAILED, getRank");
		}

		// test 7 and 8, getBestDecade
		expectedInt = 1980;
		if (adamRecord.getBestDecade() == expectedInt) {
			System.out.println("Test 7 passed, getBestDecade");
		} else {
			System.out.println("Test 7 FAILED, getBestDecade");
		}
		expectedInt = 1970;
		if (testRecord.getBestDecade() == expectedInt) {
			System.out.println("Test 8 passed, getBestDecade");
		} else {
			System.out.println("Test 8 FAILED, getBestDecade");
		}

		// test 9 and 10, getDecadesRanked
		expectedInt = 11;
		if (adamRecord.getDecadesRanked() == expectedInt) {
			System.out.println("Test 9 passed, getDecadesRanked");
		} else {
			System.out.println("Test 9 FAILED, getDecadesRanked");
		}
		expectedInt = 1;
		if (testRecord.getDecadesRanked() == expectedInt) {
			System.out.println("Test 10 passed, getDecadesRanked");
		} else {
			System.out.println("Test 10 FAILED, getDecadesRanked");
		}

		// test 11 and 12, everyDecadeRanked
		boolean expectedBool = true;
		if (adamRecord.everyDecadeRanked() == expectedBool) {
			System.out.println("Test 11 passed, everyDecadeRanked");
		} else {
			System.out.println("Test 11 FAILED, everyDecadeRanked");
		}
		expectedBool = false;
		if (testRecord.everyDecadeRanked() == expectedBool) {
			System.out.println("Test 12 passed, everyDecadeRanked");
		} else {
			System.out.println("Test 12 FAILED, everyDecadeRanked");
		}

		// test 13 and 14, rankedOnce
		expectedBool = false;
		if (adamRecord.rankedOnce() == expectedBool) {
			System.out.println("Test 13 passed, rankedOnce");
		} else {
			System.out.println("Test 13 FAILED, rankedOnce");
		}
		expectedBool = true;
		if (testRecord.rankedOnce() == expectedBool) {
			System.out.println("Test 14 passed, rankedOnce");
		} else {
			System.out.println("Test 14 FAILED, rankedOnce");
		}

		testRawData = "T3ST 0 997 996 994 990";
		testParsedLine = testRawData.split("\\s+");
		testRecord = new NameRecord(testParsedLine, 1950);

		// test 15 and 16, increasingPopularity
		expectedBool = false;
		if (adamRecord.increasingPopularity() == expectedBool) {
			System.out.println("Test 15 passed, increasingPopularity");
		} else {
			System.out.println("Test 15 FAILED, increasingPopularity");
		}
		expectedBool = true;
		if (testRecord.increasingPopularity() == expectedBool) {
			System.out.println("Test 16 passed, increasingPopularity");
		} else {
			System.out.println("Test 16 FAILED, increasingPopularity");
		}

		testRawData = "T3ST 990 994 996 997 0";
		testParsedLine = testRawData.split("\\s+");
		testRecord = new NameRecord(testParsedLine, 1950);

		// test 17 and 18, decreasingPopularity
		expectedBool = false;
		if (adamRecord.decreasingPopularity() == expectedBool) {
			System.out.println("Test 17 passed, decreasingPopularity");
		} else {
			System.out.println("Test 17 FAILED, decreasingPopularity");
		}
		expectedBool = true;
		if (testRecord.decreasingPopularity() == expectedBool) {
			System.out.println("Test 18 passed, decreasingPopularity");
		} else {
			System.out.println("Test 18 FAILED, decreasingPopularity");
		}

		// test 19 and 20, toString
		expectedString = "Adam\n1900: 178\n1910: 200\n1920: 280\n1930: 376\n1940: 444"
				+ "\n1950: 407\n1960: 144\n1970: 38\n1980: 22\n1990: 39\n2000: 46\n";
		System.out.println("expected string:\n" + expectedString);
		System.out.println("actual string:\n" + adamRecord.toString());
		if (expectedString.equals(adamRecord.toString())) {
			System.out.println("Test 19 passed, Adam toString.");
		} else {
			System.out.println("Test 19 FAILED, Adam toString.");
		}
		expectedString = "T3ST\n1950: 990\n1960: 994\n1970: 996\n1980: 997\n1990: 0\n";
		System.out.println("expected string:\n" + expectedString);
		System.out.println("actual string:\n" + testRecord.toString());
		if (expectedString.equals(testRecord.toString())) {
			System.out.println("Test 20 passed, T3ST toString.");
		} else {
			System.out.println("Test 20 FAILED, T3ST toString.");
		}

		// test 21 and 22, compareTo
		expectedInt = adamRecord.compareTo(testRecord);
		if (expectedInt < 0) {
			System.out.println("Test 21 passed, compareTo.");
		} else {
			System.out.println("Test 21 FAILED, compareTo.");
		}
		expectedInt = testRecord.compareTo(adamRecord);
		if (expectedInt > 0) {
			System.out.println("Test 22 passed, compareTo.");
		} else {
			System.out.println("Test 22 FAILED, compareTo.");
		}
	}

	// A few simple tests for the Names and NameRecord class.
	public static void simpleTest() {
		// raw data for Jake. Alter as necessary for your NameRecord
		String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
		String[] jakeParsedLine = jakeRawData.split("\\s+");
		int baseDecade = 1900;
		NameRecord jakeRecord = new NameRecord(jakeParsedLine, baseDecade);
		String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
				+ "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: 117\n2000: 97\n";
		String actual = jakeRecord.toString();
		System.out.println("expected string:\n" + expected);
		System.out.println("actual string:\n" + actual);
		if (expected.equals(actual)) {
			System.out.println("passed Jake toString test.");
		} else {
			System.out.println("FAILED Jake toString test.");
		}

		// Some getName Tests
		Names names = new Names(getFileScannerForNames(NAME_FILE));
		String[] testNames = { "Isabelle", "isabelle", "sel", "X1178", "ZZ",
				"via", "kelly" };
		boolean[] expectNull = { false, false, true, true, true, true, false };
		for (int i = 0; i < testNames.length; i++) {
			performGetNameTest(names, testNames[i], expectNull[i]);
		}
	}

	private static void performGetNameTest(Names names, String name,
			boolean expectNull) {
		System.out.println("Performing test for this name: " + name);
		if (expectNull) {
			System.out.println("Expected return value is null");
		} else {
			System.out.println("Expected return value is not null");
		}
		NameRecord result = names.getName(name);
		if ((expectNull && result == null) || (!expectNull && result != null)) {
			System.out.println("PASSED TEST.");
		} else {
			System.out.println("Failed test");
		}
	}

	// main method. Driver for the whole program
	public static void main(String[] args) {
		// nameRecordTester();
		Scanner fileScanner = getFileScannerForNames(NAME_FILE);
		Names namesDatabase = new Names(fileScanner);
		fileScanner.close();
		runOptions(namesDatabase);
	}

	// pre: namesDatabase != null
	// ask user for options to perform on the given Names object.
	// Creates a Scanner connected to System.in.
	private static void runOptions(Names namesDatabase) {
		Scanner keyboard = new Scanner(System.in);
		MenuChoices[] menuChoices = MenuChoices.values();
		MenuChoices menuChoice;
		do {
			showMenu();
			int userChoice = getChoice(keyboard) - 1;
			menuChoice = menuChoices[userChoice];
			if (menuChoice == MenuChoices.SEARCH) {
				search(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.ONE_NAME) {
				oneName(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.APPEAR_ONCE) {
				appearOnce(namesDatabase);
			} else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
				appearAlways(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_MORE) {
				alwaysMore(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_LESS) {
				alwaysLess(namesDatabase);
			} else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
				prime(namesDatabase, keyboard);
			}
		} while (menuChoice != MenuChoices.QUIT);
		keyboard.close();
	}

	// pre: fileName != null
	// create a Scanner and return connected to a File with the given name.
	private static Scanner getFileScannerForNames(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println(
					"Problem reading the data file. Returning null for Scanner"
							+ "object. Problems likely to occur." + e);
		}
		return sc;
	}

	// method that shows if an inputed name's characters converted to
	// ascii integers sum to a prime number, or, shows a list of all names who's
	// characters converted to ascii integers sum to a prime number.
	// pre: n != null, keyboard != null and is connected to System.in
	// post: print out the name, if they're prime (or a list of all names that
	// are prime), and the number.
	private static void prime(Names namesDatabase, Scanner keyboard) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		System.out.print(
				"Enter a name, or leave empty to show all prime names on file: ");
		String fullName = keyboard.nextLine();
		System.out.println();

		if (fullName.isEmpty()) {
			ArrayList<String> primeNames = namesDatabase.primeName();
			System.out.println(
					primeNames.size() + " names are prime. The names are: ");

			for (String results : primeNames) {
				System.out.println(results);
			}
		} else {
			System.out.println(namesDatabase.primeName(fullName));
		}
	}

	// method that shows names that have appeared in every decade
	// pre: n != null
	// post: print out names that have appeared in every decade
	private static void appearAlways(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		ArrayList<String> rankedEveryNames = namesDatabase.rankedEveryDecade();
		System.out.println(rankedEveryNames.size()
				+ " names appear in every decade. The names are: ");

		for (int name = 0; name < rankedEveryNames.size(); name++) {
			System.out.println(rankedEveryNames.get(name));
		}
	}

	// method that shows names that have appeared in only one decade
	// pre: n != null
	// post: print out names that have appeared in only one decade
	private static void appearOnce(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		ArrayList<String> rankedOnceNames = namesDatabase.rankedOnlyOneDecade();
		System.out.println(rankedOnceNames.size()
				+ " names appear in exactly one decade. The names are: ");

		for (int name = 0; name < rankedOnceNames.size(); name++) {
			System.out.println(rankedOnceNames.get(name));
		}
	}

	// method that shows names that have gotten more popular
	// in each successive decade
	// pre: n != null
	// post: print out names that have gotten more popular in each decade
	private static void alwaysMore(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		ArrayList<String> morePopularNames = namesDatabase.alwaysMorePopular();
		System.out.println(morePopularNames.size()
				+ " names are more popular in every decade.");

		for (int name = 0; name < morePopularNames.size(); name++) {
			System.out.println(morePopularNames.get(name));
		}
	}

	// method that shows names that have gotten less popular
	// in each successive decade
	// pre: n != null
	// post: print out names that have gotten less popular in each decade
	private static void alwaysLess(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException(
					"The parameter namesDatabase cannot be null");
		}
		ArrayList<String> lessPopularNames = namesDatabase.alwaysLessPopular();
		System.out.println(lessPopularNames.size()
				+ " names are less popular in every decade.");

		for (int name = 0; name < lessPopularNames.size(); name++) {
			System.out.println(lessPopularNames.get(name));
		}
	}

	// method that shows data for one name, or states that name has never been
	// ranked
	// pre: n != null, keyboard != null and is connected to System.in
	// post: print out the data for n or a message that n has never been in the
	// top 1000 for any decade
	private static void oneName(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a name: ");
		String fullName = keyboard.nextLine();
		System.out.println();
		NameRecord nameData = namesDatabase.getName(fullName);

		if (nameData != null) {
			System.out.println(nameData.toString());
		} else {
			System.out.println(fullName + " does not appear in any decade.");
		}
	}

	// method that shows all names that contain a substring from the user
	// and the decade they were most popular in
	// pre: n != null, keyboard != null and is connected to System.in
	// post: show the correct data
	private static void search(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a partial name: ");
		String partialName = keyboard.nextLine();
		ArrayList<NameRecord> partialNameMatches = namesDatabase
				.getMatches(partialName);

		System.out.println("\nThere are " + partialNameMatches.size()
				+ " matches for " + partialName + ".");
		if (partialNameMatches.size() > 0) {
			System.out.println(
					"\nThe matches with their highest ranking decade are:");
		}

		for (NameRecord record : partialNameMatches) {
			System.out.println(record.getName() + " " + record.getBestDecade());
		}
	}

	// get choice from the user
	// keyboard != null and is connected to System.in
	// return an int that is >= SEARCH and <= QUIT
	private static int getChoice(Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (keyboard == null) {
			throw new IllegalArgumentException(
					"The parameter keyboard cannot be null");
		}
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		// add one due to zero based indexing of enums, but 1 based indexing of
		// menu
		final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
		while (choice < 1 || choice > MAX_CHOICE) {
			System.out.println();
			System.out.println(choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}

	// ensure an int is entered from the keyboard
	// pre: s != null and is connected to System.in
	private static int getInt(Scanner s, String prompt) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (s == null) {
			throw new IllegalArgumentException(
					"The parameter s cannot be null");
		}
		System.out.print(prompt);
		while (!s.hasNextInt()) {
			s.next();
			System.out.println("That was not an int.");
			System.out.print(prompt);
		}
		return s.nextInt();
	}

	// show the user the menu
	private static void showMenu() {
		System.out.println();
		System.out.println("Options:");
		System.out.println("Enter 1 to search for names.");
		System.out.println("Enter 2 to display data for one name.");
		System.out.println("Enter 3 to display all names that appear in only "
				+ "one decade.");
		System.out.println("Enter 4 to display all names that appear in all "
				+ "decades.");
		System.out.println("Enter 5 to display all names that are more popular "
				+ "in every decade.");
		System.out.println("Enter 6 to display all names that are less popular "
				+ "in every decade.");
		System.out.println(
				"Enter 7 to display if a name's characters converted to ascii "
						+ "integers sum to a prime number.");
		System.out.println("Enter 8 to quit.");
		System.out.println();
	}

}