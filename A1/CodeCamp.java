package A1;
//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 *  replace <NAME> with your name.
 *
 *  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Christopher Carrasco
 *  email address: chris.carrasco@att.net
 *  UTEID: cc66496
 *  Section 5 digit ID: 50875
 *  Grader name: Andrew
 *  Number of slip days used on this assignment: 0
 */

import java.util.Random;

public class CodeCamp {

	/**
	 * Determine the Hamming distance between two arrays of ints. Neither the
	 * parameter <tt>aData</tt> or <tt>bData</tt> are altered as a result of
	 * this method.<br>
	 * 
	 * @param aData != null, aData.length == bData.length
	 * @param bData != null
	 * @return the Hamming Distance between the two arrays of ints.
	 */
	public static int hammingDistance(int[] aData, int[] bData) {
		// check preconditions
		if (aData == null || bData == null || aData.length != bData.length) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "hammingDistance. neither parameter may equal null, arrays"
					+ " must be equal length.");
		}
		int hamming = 0;

		// compares the int at every same index for aData and bData
		for (int index = 0; index < aData.length; index++) {
			if (aData[index] != bData[index]) {
				hamming++;
			}
		}

		return hamming;
	}

	/**
	 * Determine if one array of ints is a permutation of another. Neither the
	 * parameter <tt>aData</tt> or the parameter <tt>bData</tt> are altered as a
	 * result of this method.<br>
	 * 
	 * @param aData != null
	 * @param bData != null
	 * @return <tt>true</tt> if aData is a permutation of bData, <tt>false</tt>
	 *         otherwise
	 * 
	 */
	public static boolean isPermutation(int[] aData, int[] bData) {
		// check preconditions
		if (aData == null || bData == null) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "isPermutation. neither parameter may equal null.");
		}
		// compares length of both
		if (aData.length != bData.length) {
			return false;
		}

		// loops through each index in aData
		for (int currentIndex = 0; currentIndex < aData.length; currentIndex++) {
			int aCheck = 0;
			int bCheck = 0;

			// counts how many times the current int in aData is found in both
			// arrays
			for (int bothIndex = 0; bothIndex < aData.length; bothIndex++) {
				if (aData[currentIndex] == aData[bothIndex]) {
					aCheck++;
				}
				if (aData[currentIndex] == bData[bothIndex]) {
					bCheck++;
				}
			}

			// if there is a different amount of this int in both,
			// then they are not a permutation of each other
			if (aCheck != bCheck) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Determine the index of the String that has the largest number of vowels.
	 * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
	 * 'U', and 'u'</tt>. The parameter <tt>arrayOfStrings</tt> is not altered
	 * as a result of this method.
	 * <p>
	 * pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>,
	 * there is an least 1 non null element in arrayOfStrings.
	 * <p>
	 * post: return the index of the non-null element in arrayOfStrings that has
	 * the largest number of characters that are vowels. If there is a tie
	 * return the index closest to zero. The empty String, "", has zero vowels.
	 * It is possible for the maximum number of vowels to be 0.<br>
	 * 
	 * @param arrayOfStrings the array to check
	 * @return the index of the non-null element in arrayOfStrings that has the
	 *         largest number of vowels.
	 */
	public static int mostVowels(String[] arrayOfStrings) {
		// check preconditions
		if (arrayOfStrings == null || arrayOfStrings.length == 0
				|| !atLeastOneNonNull(arrayOfStrings)) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "mostVowels. parameter may not equal null and must contain "
					+ "at least one none null value.");
		}
		final String VOWELS = "AEIOU";
		int currentMostVowels = -1;
		int indexOfString = 0;

		// for each String in the array
		for (int stringIndex = 0; stringIndex < arrayOfStrings.length; stringIndex++) {

			// if the current string is not null
			if (arrayOfStrings[stringIndex] != null) {
				String currentString = arrayOfStrings[stringIndex].toUpperCase();
				int vowelsInString = 0;

				// for each char in the current String
				for (int charIndex = 0; charIndex < currentString.length(); charIndex++) {
					// if the current char is a vowel, then increment
					// vowelsInString
					if (VOWELS.contains("" + currentString.charAt(charIndex))) {
						vowelsInString++;
					}
				}

				// update variables if the current String has more vowels than
				// the last
				if (vowelsInString > currentMostVowels) {
					currentMostVowels = vowelsInString;
					indexOfString = stringIndex;
				}
			}
		}

		return indexOfString;
	}

	/**
	 * Perform an experiment simulating the birthday problem. Pick random
	 * birthdays for the given number of people. Return the number of pairs of
	 * people that share the same birthday.<br>
	 * 
	 * @param numPeople     The number of people in the experiment. This value
	 *                      must be > 0
	 * @param numDaysInYear The number of days in the year for this experiment.
	 *                      This value must be > 0
	 * @return The number of pairs of people that share a birthday after running
	 *         the simulation.
	 */
	public static int sharedBirthdays(int numPeople, int numDaysInYear) {
		// check preconditions
		if (numPeople <= 0 || numDaysInYear <= 0) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "sharedBirthdays. both parameters must be greater than 0. "
					+ "numPeople: " + numPeople + ", numDaysInYear: "
					+ numDaysInYear);
		}
		Random day = new Random();
		int[] personsBirthday = new int[numPeople];
		int pairs = 0;

		// gives every person a birthday
		for (int person = 0; person < numPeople; person++) {
			personsBirthday[person] = day.nextInt(numDaysInYear);
		}
		// for the current person except the last person
		for (int currentPerson = 0; currentPerson < numPeople
				- 1; currentPerson++) {
			// for each person after the current person (includes the last
			// person)
			for (int person = currentPerson + 1; person < numPeople; person++) {
				// if they share a birthday, increment pairs
				if (personsBirthday[currentPerson] == personsBirthday[person]) {
					pairs++;
				}
			}
		}

		return pairs;
	}

	/**
	 * Determine if the chess board represented by board is a safe set up.
	 * <p>
	 * pre: board != null, board.length > 0, board is a square matrix. (In other
	 * words all rows in board have board.length columns.), all elements of
	 * board == 'q' or '.'. 'q's represent queens, '.'s represent open
	 * spaces.<br>
	 * <p>
	 * post: return true if the configuration of board is safe, that is no queen
	 * can attack any other queen on the board. false otherwise. the parameter
	 * <tt>board</tt> is not altered as a result of this method.<br>
	 * 
	 * @param board the chessboard
	 * @return true if the configuration of board is safe, that is no queen can
	 *         attack any other queen on the board. false otherwise.
	 */
	public static boolean queensAreSafe(char[][] board) {
		char[] validChars = { 'q', '.' };
		// check preconditions
		if (board == null || board.length == 0 || !isSquare(board)
				|| !onlyContains(board, validChars)) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "queensAreSafe. The board may not be null, must be square, "
					+ "and may only contain 'q's and '.'s");
		}
		final char QUEEN = 'q';
		final int[] ROW_DIRECTIONS = { 0, 1, 1, 1 };
		final int[] COL_DIRECTIONS = { 1, 1, 0, -1 };

		// finds a 'q'
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {

				if (board[row][column] == QUEEN) {
					// checks down the entire E, SE, S, then SW direction from
					// that 'q'
					for (int direction = 0; direction < ROW_DIRECTIONS.length; direction++) {
						for (int step = 1; step < board.length; step++) {
							int nextRow = row + ROW_DIRECTIONS[direction] * step;
							int nextColumn = column + COL_DIRECTIONS[direction] * step;

							// if there is another 'q' in that direction, then
							// return false
							if (withinBoard(nextRow, board.length)
									&& withinBoard(nextColumn, board.length)
									&& board[nextRow][nextColumn] == QUEEN) {
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	/*
	 * Pre: rowOrColumn != null, boardLength != null. Post: return true if the
	 * next row/column is within the board, false otherwise
	 */
	private static boolean withinBoard(int rowOrColumn, int boardLength) {
		return (rowOrColumn >= 0 && rowOrColumn < boardLength);
	}

	/**
	 * Given a 2D array of ints return the value of the most valuable contiguous
	 * sub rectangle in the 2D array. The sub rectangle must be at least 1 by 1.
	 * <p>
	 * pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
	 * mat</tt> is a rectangular matrix.
	 * <p>
	 * post: return the value of the most valuable contiguous sub rectangle in
	 * <tt>city</tt>.<br>
	 * 
	 * @param city The 2D array of ints representing the value of each block in
	 *             a portion of a city.
	 * @return return the value of the most valuable contiguous sub rectangle in
	 *         <tt>city</tt>.
	 */
	public static int getValueOfMostValuablePlot(int[][] city) {
		// check preconditions
		if (city == null || city.length == 0 || city[0].length == 0
				|| !isRectangular(city)) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "getValueOfMostValuablePlot. The parameter may not be null,"
					+ " must value at least one row and at least"
					+ " one column, and must be rectangular.");
		}
		int value = city[0][0];

		// traverses every point in the array to start submatrices at
		for (int row = 0; row < city.length; row++) {
			for (int column = 0; column < city[0].length; column++) {
				// subtracted in allRows/allColumns to traverse all submatrices
				for (int inRow = 0; inRow < city.length - row; inRow++) {
					for (int inColumn = 0; inColumn < city[0].length
							- column; inColumn++) {

						int cumulative = 0;

						// traverses every submatrix
						for (int allRows = row; allRows < city.length - inRow; allRows++) {
							for (int allColumns = column; allColumns < city[0].length
									- inColumn; allColumns++) {
								cumulative += city[allRows][allColumns];

							}
							// checks after the row to get a rectangle
							if (cumulative > value) {
								value = cumulative;
							}
						}
					}
				}
			}
		}

		return value;
	}

	/*
	 * Performs 1,000,000 experiments with 365 days per year and 182 people per
	 * experiment. Post: prints the average number of pairs of people with
	 * shared birthdays.
	 */
	public static void birthdayProblem() {
		final int EXPERIMENTS = 1000000;
		final int NUM_PEOPLE = 182;
		final int NUM_DAYS_IN_YEAR = 365;
		double totalPairs = 0;

		for (int test = 0; test < EXPERIMENTS; test++) {
			totalPairs += sharedBirthdays(NUM_PEOPLE, NUM_DAYS_IN_YEAR);
		}

		System.out.print(
				"The average number of pairs of people with shared birthdays,"
						+ " given 182 people and a 365 day year: ");
		System.out.printf("%.2f%n%n",
				100 * (totalPairs / (NUM_PEOPLE * EXPERIMENTS)));
	}

	/*
	 * Performs 50,000 experiments with 365 days per year and varies the number
	 * of people from 2 to 100. For each of the given number of people,
	 * determines the percentage of experiments where at least one pair of
	 * people shared a birthday. Post: prints a table of the results.
	 */
	public static void birthdayProblemVaryPeople() {
		final int EXPERIMENTS = 50000;
		final int NUM_DAYS_IN_YEAR = 365;
		int pairs = 0;
		double experimentsWithSharedBirtday = 0;

		// for each person in the varied number of people
		for (int people = 2; people <= 100; people++) {
			// run this many experiments
			for (int test = 0; test < EXPERIMENTS; test++) {
				pairs = sharedBirthdays(people, NUM_DAYS_IN_YEAR);
				if (pairs > 0) {
					experimentsWithSharedBirtday++;
				}
			}
			System.out.print("Num people: " + people
					+ ", number of experiments with one or more shared birthday: "
					+ experimentsWithSharedBirtday + ", percentage: ");
			System.out.printf("%.2f%n%n",
					100 * (experimentsWithSharedBirtday / EXPERIMENTS));

			experimentsWithSharedBirtday = 0;
		}
	}

	/*
	 * pre: arrayOfStrings != null post: return true if at least one element in
	 * arrayOfStrings is not null, otherwise return false.
	 */
	private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
		// check precondition
		if (arrayOfStrings == null) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "atLeastOneNonNull. parameter may not equal null.");
		}
		boolean foundNonNull = false;
		int i = 0;
		while (!foundNonNull && i < arrayOfStrings.length) {
			foundNonNull = arrayOfStrings[i] != null;
			i++;
		}
		return foundNonNull;
	}

	/*
	 * pre: mat != null post: return true if mat is a square matrix, false
	 * otherwise
	 */
	private static boolean isSquare(char[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "isSquare. Parameter may not be null.");
		}
		final int numRows = mat.length;
		int row = 0;
		boolean isSquare = true;
		while (isSquare && row < numRows) {
			isSquare = (mat[row] != null) && (mat[row].length == numRows);
			row++;
		}
		return isSquare;
	}

	/*
	 * pre: mat != null, valid != null post: return true if all elements in mat
	 * are one of the characters in valid
	 */
	private static boolean onlyContains(char[][] mat, char[] valid) {
		// check preconditions
		if (mat == null || valid == null) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "onlyContains. Parameters may not be null.");
		}
		String validChars = new String(valid);
		int row = 0;
		boolean onlyContainsValidChars = true;
		while (onlyContainsValidChars && row < mat.length) {
			int col = 0;
			while (onlyContainsValidChars && col < mat[row].length) {
				int indexOfChar = validChars.indexOf(mat[row][col]);
				onlyContainsValidChars = indexOfChar != -1;
				col++;
			}
			row++;
		}
		return onlyContainsValidChars;
	}

	/*
	 * pre: mat != null, mat.length > 0 post: return true if mat is rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		// check preconditions
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ "isRectangular. Parameter may not be null and must contain"
					+ " at least one row.");
		}
		boolean correct = mat[0] != null;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == mat[0].length);
			row++;
		}
		return correct;
	}

	// make constructor private so instances of CodeCamp can not be created
	private CodeCamp() {

	}
}