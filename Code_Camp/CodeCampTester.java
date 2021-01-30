package Code_Camp;

// CodeCamp.java - CS314 Assignment 1 - Tester class

/*
 * Student information for assignment: 
 * Name: Christopher Carrasco
 * email address: chris.carrasco@att.net
 * UTEID: cc66496
 * Section 5 digit ID: 50875
 * Grader name: Andrew
 * Number of slip days used on this assignment: 0
 */

/*
 * - The average number of pairs of people with shared birthdays, given 182
 * people and a 365 day year: 24.79
 * 
 * - I guessed that it would take ~20 people for a 50% chance of there being at
 * least 1 shared birthday, given a 365 day year. I found that it would require
 * >>> 23 people <<< to have a 50% chance of there being at least 1 shared
 * birthday, given a 365 day year.
 * 
 * - Table:
Num people: 2, number of experiments with one or more shared birthday: 122.0, percentage: 0.24

Num people: 3, number of experiments with one or more shared birthday: 405.0, percentage: 0.81

Num people: 4, number of experiments with one or more shared birthday: 800.0, percentage: 1.60

Num people: 5, number of experiments with one or more shared birthday: 1434.0, percentage: 2.87

Num people: 6, number of experiments with one or more shared birthday: 1987.0, percentage: 3.97

Num people: 7, number of experiments with one or more shared birthday: 2726.0, percentage: 5.45

Num people: 8, number of experiments with one or more shared birthday: 3702.0, percentage: 7.40

Num people: 9, number of experiments with one or more shared birthday: 4755.0, percentage: 9.51

Num people: 10, number of experiments with one or more shared birthday: 5763.0, percentage: 11.53

Num people: 11, number of experiments with one or more shared birthday: 7163.0, percentage: 14.33

Num people: 12, number of experiments with one or more shared birthday: 8201.0, percentage: 16.40

Num people: 13, number of experiments with one or more shared birthday: 9599.0, percentage: 19.20

Num people: 14, number of experiments with one or more shared birthday: 11161.0, percentage: 22.32

Num people: 15, number of experiments with one or more shared birthday: 12701.0, percentage: 25.40

Num people: 16, number of experiments with one or more shared birthday: 14270.0, percentage: 28.54

Num people: 17, number of experiments with one or more shared birthday: 15829.0, percentage: 31.66

Num people: 18, number of experiments with one or more shared birthday: 17331.0, percentage: 34.66

Num people: 19, number of experiments with one or more shared birthday: 18938.0, percentage: 37.88

Num people: 20, number of experiments with one or more shared birthday: 20729.0, percentage: 41.46

Num people: 21, number of experiments with one or more shared birthday: 22386.0, percentage: 44.77

Num people: 22, number of experiments with one or more shared birthday: 23761.0, percentage: 47.52

Num people: 23, number of experiments with one or more shared birthday: 25199.0, percentage: 50.40

Num people: 24, number of experiments with one or more shared birthday: 26854.0, percentage: 53.71

Num people: 25, number of experiments with one or more shared birthday: 28456.0, percentage: 56.91

Num people: 26, number of experiments with one or more shared birthday: 29867.0, percentage: 59.73

Num people: 27, number of experiments with one or more shared birthday: 31225.0, percentage: 62.45

Num people: 28, number of experiments with one or more shared birthday: 32532.0, percentage: 65.06

Num people: 29, number of experiments with one or more shared birthday: 34070.0, percentage: 68.14

Num people: 30, number of experiments with one or more shared birthday: 35226.0, percentage: 70.45

Num people: 31, number of experiments with one or more shared birthday: 36625.0, percentage: 73.25

Num people: 32, number of experiments with one or more shared birthday: 37720.0, percentage: 75.44

Num people: 33, number of experiments with one or more shared birthday: 38725.0, percentage: 77.45

Num people: 34, number of experiments with one or more shared birthday: 39776.0, percentage: 79.55

Num people: 35, number of experiments with one or more shared birthday: 40796.0, percentage: 81.59

Num people: 36, number of experiments with one or more shared birthday: 41644.0, percentage: 83.29

Num people: 37, number of experiments with one or more shared birthday: 42515.0, percentage: 85.03

Num people: 38, number of experiments with one or more shared birthday: 43196.0, percentage: 86.39

Num people: 39, number of experiments with one or more shared birthday: 43938.0, percentage: 87.88

Num people: 40, number of experiments with one or more shared birthday: 44539.0, percentage: 89.08

Num people: 41, number of experiments with one or more shared birthday: 45171.0, percentage: 90.34

Num people: 42, number of experiments with one or more shared birthday: 45718.0, percentage: 91.44

Num people: 43, number of experiments with one or more shared birthday: 46249.0, percentage: 92.50

Num people: 44, number of experiments with one or more shared birthday: 46687.0, percentage: 93.37

Num people: 45, number of experiments with one or more shared birthday: 47092.0, percentage: 94.18

Num people: 46, number of experiments with one or more shared birthday: 47370.0, percentage: 94.74

Num people: 47, number of experiments with one or more shared birthday: 47822.0, percentage: 95.64

Num people: 48, number of experiments with one or more shared birthday: 48023.0, percentage: 96.05

Num people: 49, number of experiments with one or more shared birthday: 48352.0, percentage: 96.70

Num people: 50, number of experiments with one or more shared birthday: 48551.0, percentage: 97.10

Num people: 51, number of experiments with one or more shared birthday: 48711.0, percentage: 97.42

Num people: 52, number of experiments with one or more shared birthday: 48913.0, percentage: 97.83

Num people: 53, number of experiments with one or more shared birthday: 49051.0, percentage: 98.10

Num people: 54, number of experiments with one or more shared birthday: 49255.0, percentage: 98.51

Num people: 55, number of experiments with one or more shared birthday: 49310.0, percentage: 98.62

Num people: 56, number of experiments with one or more shared birthday: 49447.0, percentage: 98.89

Num people: 57, number of experiments with one or more shared birthday: 49496.0, percentage: 98.99

Num people: 58, number of experiments with one or more shared birthday: 49583.0, percentage: 99.17

Num people: 59, number of experiments with one or more shared birthday: 49637.0, percentage: 99.27

Num people: 60, number of experiments with one or more shared birthday: 49693.0, percentage: 99.39

Num people: 61, number of experiments with one or more shared birthday: 49716.0, percentage: 99.43

Num people: 62, number of experiments with one or more shared birthday: 49814.0, percentage: 99.63

Num people: 63, number of experiments with one or more shared birthday: 49841.0, percentage: 99.68

Num people: 64, number of experiments with one or more shared birthday: 49863.0, percentage: 99.73

Num people: 65, number of experiments with one or more shared birthday: 49895.0, percentage: 99.79

Num people: 66, number of experiments with one or more shared birthday: 49900.0, percentage: 99.80

Num people: 67, number of experiments with one or more shared birthday: 49906.0, percentage: 99.81

Num people: 68, number of experiments with one or more shared birthday: 49936.0, percentage: 99.87

Num people: 69, number of experiments with one or more shared birthday: 49934.0, percentage: 99.87

Num people: 70, number of experiments with one or more shared birthday: 49964.0, percentage: 99.93

Num people: 71, number of experiments with one or more shared birthday: 49966.0, percentage: 99.93

Num people: 72, number of experiments with one or more shared birthday: 49972.0, percentage: 99.94

Num people: 73, number of experiments with one or more shared birthday: 49970.0, percentage: 99.94

Num people: 74, number of experiments with one or more shared birthday: 49988.0, percentage: 99.98

Num people: 75, number of experiments with one or more shared birthday: 49991.0, percentage: 99.98

Num people: 76, number of experiments with one or more shared birthday: 49993.0, percentage: 99.99

Num people: 77, number of experiments with one or more shared birthday: 49991.0, percentage: 99.98

Num people: 78, number of experiments with one or more shared birthday: 49991.0, percentage: 99.98

Num people: 79, number of experiments with one or more shared birthday: 49997.0, percentage: 99.99

Num people: 80, number of experiments with one or more shared birthday: 49996.0, percentage: 99.99

Num people: 81, number of experiments with one or more shared birthday: 49994.0, percentage: 99.99

Num people: 82, number of experiments with one or more shared birthday: 49996.0, percentage: 99.99

Num people: 83, number of experiments with one or more shared birthday: 49998.0, percentage: 100.00

Num people: 84, number of experiments with one or more shared birthday: 49999.0, percentage: 100.00

Num people: 85, number of experiments with one or more shared birthday: 49998.0, percentage: 100.00

Num people: 86, number of experiments with one or more shared birthday: 49999.0, percentage: 100.00

Num people: 87, number of experiments with one or more shared birthday: 49999.0, percentage: 100.00

Num people: 88, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 89, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 90, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 91, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 92, number of experiments with one or more shared birthday: 49999.0, percentage: 100.00

Num people: 93, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 94, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 95, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 96, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 97, number of experiments with one or more shared birthday: 49999.0, percentage: 100.00

Num people: 98, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 99, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00

Num people: 100, number of experiments with one or more shared birthday: 50000.0, percentage: 100.00
 */

public class CodeCampTester {

	public static void main(String[] args) {
		final String newline = System.getProperty("line.separator");

		//CodeCamp.birthdayProblem();
		//CodeCamp.birthdayProblemVaryPeople();

		// new test 1, hamming distance, empty arrays
		int[] h1 = {};
		int[] h2 = {};
		int expected = 0;
		int actual = CodeCamp.hammingDistance(h1, h2);
		System.out.println("Test 1 hamming distance: expected value: "
				+ expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 1, hamming distance");
		} else {
			System.out.println(" ***** FAILED ***** test 1, hamming distance");
		}

		// new test 2, hamming distance
		h1 = new int[] { 0, 0, 0 };
		h2 = new int[] { 0, 0, 0 };
		expected = 0;
		actual = CodeCamp.hammingDistance(h1, h2);
		System.out.println(newline + "Test 2 hamming distance: expected value: "
				+ expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 2, hamming distance");
		} else {
			System.out.println(" ***** FAILED ***** test 2, hamming distance");
		}

		// new test 3, hamming distance
		h1 = new int[] { 1, 3, 2, 1 };
		h2 = new int[] { 3, 2, 1, -1 };
		expected = 4;
		actual = CodeCamp.hammingDistance(h1, h2);
		System.out.println(newline + "Test 3 hamming distance: expected value: "
				+ expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 3, hamming distance");
		} else {
			System.out.println(" ***** FAILED ***** test 3, hamming distance");
		}

		// new test 4, isPermutation, empty arrays
		int[] a = new int[] {};
		int[] b = new int[] {};
		boolean expectedBool = true;
		boolean actualBool = CodeCamp.isPermutation(a, b);
		System.out.println(newline + "Test 4 isPermutation: expected value: "
				+ expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 4, isPermutation");
		} else {
			System.out.println(" ***** FAILED ***** test 4, isPermutation");
		}

		// new test 5, isPermutation
		a = new int[] { -1, 0, -100, 0, 2, 100 };
		b = new int[] { 0, 2, 100, -100, -1, 0 };
		expectedBool = true;
		actualBool = CodeCamp.isPermutation(a, b);
		System.out.println(newline + "Test 5 isPermutation: expected value: "
				+ expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 5, isPermutation");
		} else {
			System.out.println(" ***** FAILED ***** test 5, isPermutation");
		}

		// new test 6, isPermutation
		b = new int[] { -1 };
		expectedBool = false;
		actualBool = CodeCamp.isPermutation(a, b);
		System.out.println(newline + "Test 6 isPermutation: expected value: "
				+ expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 6, isPermutation");
		} else {
			System.out.println(" ***** FAILED ***** test 6, isPermutation");
		}

		// new test 7, mostVowels
		String[] arrayOfStrings = new String[] { null, "bat", "banana", null,
				"bongo" };
		int expectedResult = 2;
		int actualResult = CodeCamp.mostVowels(arrayOfStrings);
		System.out.println(newline + "Test 7 mostVowels: expected result: "
				+ expectedResult + ", actual result: " + actualResult);
		if (actualResult == expectedResult) {
			System.out.println("passed test 7, mostVowels");
		} else {
			System.out.println("***** FAILED ***** test 7, mostVowels");
		}

		// new test 8, mostVowels
		arrayOfStrings = new String[] { "null, bat, banana, null, bongo" };
		expectedResult = 0;
		actualResult = CodeCamp.mostVowels(arrayOfStrings);
		System.out.println(newline + "Test 8 mostVowels: expected result: "
				+ expectedResult + ", actual result: " + actualResult);
		if (actualResult == expectedResult) {
			System.out.println("passed test 8, mostVowels");
		} else {
			System.out.println("***** FAILED ***** test 8, mostVowels");
		}

		// new test 9, sharedBirthdays
		int pairs = CodeCamp.sharedBirthdays(7, 1);
		System.out.println(newline + "Test 9 shared birthdays: expected: 21"
				+ ", actual: " + pairs);
		if (pairs == 21) {
			System.out.println("passed test 9, shared birthdays");
		} else {
			System.out.println("***** FAILED ***** test 9, shared birthdays. "
					+ "Expected pairs to be 21. Value returned: " + pairs);
		}

		// new test 10, sharedBirthdays
		pairs = CodeCamp.sharedBirthdays(10, 1);
		System.out.println(newline + "Test 10 shared birthdays: expected: 45"
				+ ", actual: " + pairs);
		if (pairs == 45) {
			System.out.println("passed test 10, shared birthdays");
		} else {
			System.out.println("***** FAILED ***** test 10, shared birthdays. "
					+ "Expected pairs to be 45. Value returned: " + pairs);
		}

		// new test 11, queensAreASafe, 1x1 no queen
		char[][] board = new char[][] { { '.' } };
		expectedBool = true;
		actualBool = CodeCamp.queensAreSafe(board);
		System.out.println(newline + "Test 11 queensAreSafe: expected value: "
				+ expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 11, queensAreSafe");
		} else {
			System.out.println(" ***** FAILED ***** test 11, queensAreSafe");
		}

		// new test 12, queensAreASafe, 1x1 queen
		board = new char[][] { { 'q' } };
		expectedBool = true;
		actualBool = CodeCamp.queensAreSafe(board);
		System.out.println(newline + "Test 12 queensAreSafe: expected value: "
				+ expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 12, queensAreSafe");
		} else {
			System.out.println(" ***** FAILED ***** test 12, queensAreSafe");
		}

		// new test 13, queensAreASafe
		board = new char[][] { { 'q', '.', '.' },
							   { '.', '.', 'q' },
							   { '.', 'q', '.' } };
		expectedBool = false;
		actualBool = CodeCamp.queensAreSafe(board);
		System.out.println(newline + "Test 13 queensAreSafe: expected value: "
				+ expectedBool + ", actual value: " + actualBool);
		if (expectedBool == actualBool) {
			System.out.println(" passed test 13, queensAreSafe");
		} else {
			System.out.println(" ***** FAILED ***** test 13, queensAreSafe");
		}

		// new test 14, getValueOfMostValuablePlot
		int[][] city = { { -9, 1, 1, -9, 1 },
						 { -9, -9, 1, -9, 1 } };
		expected = 2;
		actual = CodeCamp.getValueOfMostValuablePlot(city);
		System.out.println(
				newline + "Test 14 getValueOfMostValuablePlot: expected value: "
						+ expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 14, getValueOfMostValuablePlot");
		} else {
			System.out.println(
					" ***** FAILED ***** test 14, getValueOfMostValuablePlot");
		}

		// new test 15, getValueOfMostValuablePlot
		city = new int[][] { { -9, 1, 1, -9, 1 },
							 { -9, 1, -9, -9, 1 } };
		expected = 2;
		actual = CodeCamp.getValueOfMostValuablePlot(city);
		System.out.println(
				newline + "Test 15 getValueOfMostValuablePlot: expected value: "
						+ expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 15, getValueOfMostValuablePlot");
		} else {
			System.out.println(
					" ***** FAILED ***** test 15, getValueOfMostValuablePlot");
		}

		// new test 16, getValueOfMostValuablePlot
		city = new int[][] { { -5, -5, -5 },
							 { -5, 90, -5 },
							 { -5, 10, -5 },
							 { -5, -5, -5, } };
		expected = 100;
		actual = CodeCamp.getValueOfMostValuablePlot(city);
		System.out.println(
				newline + "Test 16 getValueOfMostValuablePlot: expected value: "
						+ expected + ", actual value: " + actual);
		if (expected == actual) {
			System.out.println(" passed test 16, getValueOfMostValuablePlot");
		} else {
			System.out.println(
					" ***** FAILED ***** test 16, getValueOfMostValuablePlot");
		}
		
	} // end of main method
}