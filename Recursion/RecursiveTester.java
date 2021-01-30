package Recursion;
import java.util.ArrayList;
import java.util.Collections;

/*  Student information for assignment:
 *
 *  On OUR honor, Rodrigo Garcia and Christopher Carrasco,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1: Christopher Carrasco
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 *  Grader name: Andrew
 *  Section number: 50875
 *
 *  Student 2
 *  UTEID: rxg74
 *  email address: rigo.xg@utexas.edu
 *
 */

/**
 * Tester class for the methods in Recursive.java
 */
public class RecursiveTester {

	// run the tests
	public static void main(String[] args) {
		doBinaryTests();
		doReverseTests();
		doNextIsDoubleTests();
		doListMnemonicsTests();
		doCarpetTest();
		doFlowOffMapTests();
		doFairTeamsTests();
		doMazeTests();
	}

	private static void doBinaryTests() {
		String actualBinary = Recursive.getBinary(27);
		String expectedBinary = "11011";
		if (actualBinary.equals(expectedBinary))
			System.out.println("Test 1 passed. get binary.");
		else
			System.out.println("Test 1 failed. get binary. expected: "
					+ expectedBinary + ", actual: " + actualBinary);

		actualBinary = Recursive.getBinary(-47);
		expectedBinary = "-101111";
		if (actualBinary.equals(expectedBinary))
			System.out.println("Test 2 passed. get binary.");
		else
			System.out.println("Test 2 failed. get binary. expected: "
					+ expectedBinary + ", actual: " + actualBinary);

		actualBinary = Recursive.getBinary(256);
		expectedBinary = "100000000";
		if (actualBinary.equals(expectedBinary))
			System.out.println("Test 3 passed. get binary.");
		else
			System.out.println("Test 3 failed. get binary. expected: "
					+ expectedBinary + ", actual: " + actualBinary);

	}

	private static void doReverseTests() {
		System.out.println();

		String actualRev = Recursive.revString("fumble");
		String expectedRev = "elbmuf";
		if (actualRev.equals(expectedRev))
			System.out.println("Test 1 passed. reverse string.");
		else
			System.out.println("Test 1 failed. reverse string. expected: "
					+ expectedRev + ", actual: " + actualRev);

		actualRev = Recursive.revString("Blink-182");
		expectedRev = "281-knilB";
		if (actualRev.equals(expectedRev))
			System.out.println("Test 2 passed. reverse string.");
		else
			System.out.println("Test 2 failed. reverse string. expected: "
					+ expectedRev + ", actual: " + actualRev);

		actualRev = Recursive.revString("nemo");
		expectedRev = "omen";
		if (actualRev.equals(expectedRev))
			System.out.println("Test 3 passed. reverse string.");
		else
			System.out.println("Test 3 failed. reverse string. expected: "
					+ expectedRev + ", actual: " + actualRev);
		System.out.println();
	}

	private static void doNextIsDoubleTests() {
		int[] numsForDouble = { -1, 2, 6, 7, 14, 21, 84, 42 };
		int actualDouble = Recursive.nextIsDouble(numsForDouble);
		int expectedDouble = 1;
		if (actualDouble == expectedDouble)
			System.out.println("Test 1 passed. next is double.");
		else
			System.out.println("Test 1 failed. next is double. expected: "
					+ expectedDouble + ", actual: " + actualDouble);

		numsForDouble = new int[] { 5, 10, 20, 40, 8, 0, 0, 160 };
		actualDouble = Recursive.nextIsDouble(numsForDouble);
		expectedDouble = 4;
		if (actualDouble == expectedDouble)
			System.out.println("Test 2 passed. next is double.");
		else
			System.out.println("Test 2 failed. next is double. expected: "
					+ expectedDouble + ", actual: " + actualDouble);

		numsForDouble = new int[] { 124816 };
		actualDouble = Recursive.nextIsDouble(numsForDouble);
		expectedDouble = 0;
		if (actualDouble == expectedDouble)
			System.out.println("Test 3 passed. next is double.");
		else
			System.out.println("Test 3 failed. next is double. expected: "
					+ expectedDouble + ", actual: " + actualDouble);

	}

	private static void doListMnemonicsTests() {
		System.out.println();

		ArrayList<String> mnemonics = Recursive.listMnemonics("17");
		ArrayList<String> expected = new ArrayList<>();
		Collections.sort(mnemonics);

		expected.add("1P");
		expected.add("1Q");
		expected.add("1R");
		expected.add("1S");
		if (mnemonics.equals(expected)) {
			System.out.println("Test 1 passed. Phone mnemonics.");
		} else {
			System.out.println("Test1 failed. Phone mnemonics.");
		}
		expected.removeAll(expected);
		mnemonics = Recursive.listMnemonics("432");
		Collections.sort(mnemonics);

		// G
		expected.add("GDA");
		expected.add("GDB");
		expected.add("GDC");
		expected.add("GEA");
		expected.add("GEB");
		expected.add("GEC");
		expected.add("GFA");
		expected.add("GFB");
		expected.add("GFC");
		// H
		expected.add("HDA");
		expected.add("HDB");
		expected.add("HDC");
		expected.add("HEA");
		expected.add("HEB");
		expected.add("HEC");
		expected.add("HFA");
		expected.add("HFB");
		expected.add("HFC");
		// I
		expected.add("IDA");
		expected.add("IDB");
		expected.add("IDC");
		expected.add("IEA");
		expected.add("IEB");
		expected.add("IEC");
		expected.add("IFA");
		expected.add("IFB");
		expected.add("IFC");
		Collections.sort(expected);
		if (mnemonics.equals(expected)) {
			System.out.println("Test 2 passed. Phone mnemonics.");
		} else {
			System.out.println("Test 2 failed. Phone mnemonics.");
		}
	}

	private static void doFlowOffMapTests() {
		System.out.println();

		int testNum = 1;
		int[][] world = { { 10, 10, 10, 10, 10, 10 }, { 10, 9, 10, 2, 3, 10 },
				{ 10, 8, 7, 6, 5, 10 }, { 10, 7, 6, 6, 5, 10 },
				{ 10, 10, 5, 10, 10, 10 }, { 10, 10, 4, 3, 10, 10 },
				{ 10, 10, 10, 2, 1, 10 }, { 10, 10, 10, 1, 10, 10 } };

		doOneFlowTest(world, 1, 1, true, testNum++);
		doOneFlowTest(world, 6, 0, true, testNum++);
		doOneFlowTest(world, 6, 1, false, testNum++);
		doOneFlowTest(world, 3, 3, false, testNum++);

		world = new int[][] { { 52, 4, 94, 11, 10, 3, 4 },
				{ 998, 13, -50000, 8, 10, 9, 262 },
				{ 924, 5, 8320, -6, 163, 10, 98 } };

		doOneFlowTest(world, 0, 3, true, testNum++);
		doOneFlowTest(world, 1, 6, true, testNum++);
		doOneFlowTest(world, 2, 3, true, testNum++);
		doOneFlowTest(world, 1, 2, false, testNum++);

		System.out.println();
	}

	private static void doCarpetTest() {
		Recursive.drawCarpet(729, 4);
		Recursive.drawCarpet(729, 1);
	}

	private static void doOneFlowTest(int[][] world, int r, int c,
			boolean expected, int testNum) {
		// System.out.println("Can Flow Off Map Test Number " + testNum);
		boolean actual = Recursive.canFlowOffMap(world, r, c);
		if (expected == actual) {
			System.out
					.println("Test " + testNum + " passed. can flow off map.");
		} else {
			System.out.println("Start location = " + r + ", " + c);
			System.out.println("Expected result = " + expected
					+ " actual result = " + actual);
			System.out.println("FAILED TEST " + testNum + " can flow off map.");
		}
	}

	private static void doFairTeamsTests() {
		int[] abilities = { -1, 1, -1, 2 };

		int actual = Recursive.minDifference(3, abilities);
		if (actual == 2)
			System.out.println("Test 1 passed. min difference.");
		else {
			System.out.println("Test 1 FAILED. min difference.");
			System.out.println("Expected: 1");
			System.out.println("Actual: " + actual);
		}

		abilities = new int[] { 1, 20, 15, 3, -6 };
		actual = Recursive.minDifference(2, abilities);
		if (actual == 1)
			System.out.println("Test 2 passed. min difference.");
		else {
			System.out.println("Test 2 FAILED. min difference.");
			System.out.println("Expected: 3");
			System.out.println("Actual: " + actual);
		}

		actual = Recursive.minDifference(5, abilities);
		if (actual == 26)
			System.out.println("Test 3 passed. min difference.");
		else {
			System.out.println("Test 3 FAILED. min difference.");
			System.out.println("Expected: 19");
			System.out.println("Actual: " + actual);
		}

		actual = Recursive.minDifference(2, new int[] { -5, 5 });
		int expected = 10;
		if (actual == expected) {
			System.out.println("Test 4 passed. min difference");
		} else {
			System.out.println("Test 4 FAILED. min difference");
			System.out.println("Expected 10");
			System.out.println("Actual: " + actual);

		}
		System.out.println();
	}

	private static void doMazeTests() {
		int mazeTestNum = 1;
		String maze = "*YSG**Y*G*EY*Y$";
		// * Y S G *
		// * Y * G *
		// E Y * Y $
		runMazeTest(maze, 3, 1, mazeTestNum++);
		maze = "*YSG**Y*G*E**Y$";
		// * Y S G *
		// * Y * G *
		// E * * Y $
		runMazeTest(maze, 3, 0, mazeTestNum++);
		maze = "YGGYSYY";
		// Y G G Y S Y Y
		runMazeTest(maze, 1, 0, mazeTestNum++);
	}

	private static void runMazeTest(String rawMaze, int rows, int expected,
			int num) {
		char[][] maze = makeMaze(rawMaze, rows);

		System.out.println("Can escape maze test number " + num);
		printMaze(maze);
		int actual = Recursive.canEscapeMaze(maze);
		System.out.println("Expected result: " + expected);
		System.out.println("Actual result:   " + actual);
		if (expected == actual) {
			System.out.println("passed test " + num);
		} else {
			System.out.println("FAILED TEST " + num);
		}
		System.out.println();
	}

	// generate a char[][] given the raw string
	// pre: rawMaze != null, rawMaze.length() % rows == 0
	private static char[][] makeMaze(String rawMaze, int rows) {
		if (rawMaze == null || rawMaze.length() % rows != 0) {
			throw new IllegalArgumentException(
					"Violation of precondition in makeMaze."
							+ "Either raw data is null or left over values: ");
		}
		int cols = rawMaze.length() / rows;
		char[][] result = new char[rows][cols];
		int rawIndex = 0;
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = rawMaze.charAt(rawIndex);
				rawIndex++;
			}
		}
		return result;
	}

	// print the given maze
	// pre: none
	private static void printMaze(char[][] maze) {
		if (maze == null) {
			System.out.println("NO MAZE GIVEN");
		} else {
			for (char[] row : maze) {
				for (char c : row) {
					System.out.print(c);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
	}
}
