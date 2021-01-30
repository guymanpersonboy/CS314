package Math_Matrix;
import java.util.Random;

import Other.Stopwatch;

/*  Student information for assignment:
 *
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 *  Grader name: Andrew
 *  Number of slip days I am using: 0
 */

/* 
 * Run on CS Lab Machines:
Experiment 1
rows: 250 columns: 250 elapsed time: 1.01883625 seconds.
rows: 500 columns: 500 elapsed time: 3.494696677 seconds.
rows: 1000 columns: 1000 elapsed time: 14.219779151 seconds.
Experiment 2
rows: 240 columns: 240 elapsed time: 1.232210217 seconds.
rows: 480 columns: 480 elapsed time: 14.471928007 seconds.
rows: 960 columns: 960 elapsed time: 172.935654426 seconds.
 * 
 * - Question 1
 * If I doubled the dimensions of my MathMatrix objects again,
 * I expect it would take about 56 seconds for the add method.
 * - Question 2
 * The Big O of my add operation appears to be O(N^2) which is
 * supported by my data because the ratio nears 4x.
 * - Question 3
 * If I doubled the dimensions of my MathMatrix objects again,
 * I expect it would take about 2076 seconds for the multiply method.
 * - Question 4
 * The Big O of my multiply operation appears to be O(N^3) which
 * is supported by my data because the ratio nears 12x.
 * - Question 5
 * Java runs out of heap memory for a MathMatrix over about 21925x21925.
 * About 1922.822 Megabytes can be allocated for MathMatrix
*/

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

	private static final int ADD_ROWS_COLS = 250;
	private static final int ADD_LIMIT = 1000;
	private static final int MULT_ROWS_COLS = 240;
	private static final int MULT_LIMIT = 100;

	/**
	 * main method that runs simple test on the MathMatrix class
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		/*
		Random randNumGen = new Random(6201919);
		Stopwatch st = new Stopwatch();
		// MathMatrix heap = new MathMatrix(new int[21925][21925]);

		System.out.println("Experiment 1");
		MathMatrix test1 = createMat(randNumGen, ADD_ROWS_COLS, ADD_ROWS_COLS,
				ADD_LIMIT);
		MathMatrix test2 = createMat(randNumGen, ADD_ROWS_COLS, ADD_ROWS_COLS,
				ADD_LIMIT);
		experiment1(test1, test2, st, 1);

		test1 = createMat(randNumGen, ADD_ROWS_COLS * 2, ADD_ROWS_COLS * 2,
				ADD_LIMIT);
		test2 = createMat(randNumGen, ADD_ROWS_COLS * 2, ADD_ROWS_COLS * 2,
				ADD_LIMIT);
		experiment1(test1, test2, st, 2);

		test1 = createMat(randNumGen, ADD_ROWS_COLS * 4, ADD_ROWS_COLS * 4,
				ADD_LIMIT);
		test2 = createMat(randNumGen, ADD_ROWS_COLS * 4, ADD_ROWS_COLS * 4,
				ADD_LIMIT);
		experiment1(test1, test2, st, 4);

		System.out.println("Experiment 2");
		test1 = createMat(randNumGen, MULT_ROWS_COLS, MULT_ROWS_COLS,
				MULT_LIMIT);
		test2 = createMat(randNumGen, MULT_ROWS_COLS, MULT_ROWS_COLS,
				MULT_LIMIT);
		experiment2(test1, test2, st, 1);

		test1 = createMat(randNumGen, MULT_ROWS_COLS * 2, MULT_ROWS_COLS * 2,
				MULT_LIMIT);
		test2 = createMat(randNumGen, MULT_ROWS_COLS * 2, MULT_ROWS_COLS * 2,
				MULT_LIMIT);
		experiment2(test1, test2, st, 2);

		test1 = createMat(randNumGen, MULT_ROWS_COLS * 4, MULT_ROWS_COLS * 4,
				MULT_LIMIT);
		test2 = createMat(randNumGen, MULT_ROWS_COLS * 4, MULT_ROWS_COLS * 4,
				MULT_LIMIT);
		experiment2(test1, test2, st, 4);
		*/

		int[][] data1 = { { 5, 5, 5, 5, 5 } };
		int[][] data2 = { { -1 }, { 1 } };
		int[][] e1;

		// test 1, specify size and values constructor
		MathMatrix mat1 = new MathMatrix(5, 1, -1);
		e1 = new int[][] { { -1 }, { -1 }, { -1 }, { -1 }, { -1 } };
		printTestResult(get2DArray(mat1), e1, 1,
				"Constructor with size and initial val specified.\n");
		// tests 2 and 3, int[][] constructor, deep copy
		mat1 = new MathMatrix(data1);
		data1[0][0] = -1;
		// alter data1. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { -1, 5, 5, 5, 5 } };
		printTestResult(data1, e1, 2,
				"constructor with one parameter of type int[][].\n");
		// data1 altered. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 5, 5, 5, 5, 5 } };
		printTestResult(get2DArray(mat1), e1, 3,
				"constructor with one parameter of type int[][]. Testing deep copy made.\n");

		// tests 4 and 5, getNumRows
		int actual = mat1.getNumRows();
		int expectedInt = 1;
		printGetterTestResult(actual, expectedInt, 4, "getNumRows method");

		MathMatrix mat2 = new MathMatrix(data2);
		actual = mat2.getNumRows();
		expectedInt = 2;
		printGetterTestResult(actual, expectedInt, 5, "getNumRows method");

		// tests 6 and 7, getNumColumns
		actual = mat1.getNumColumns();
		expectedInt = 5;
		printGetterTestResult(actual, expectedInt, 6, "getNumColumns method");

		actual = mat2.getNumColumns();
		expectedInt = 1;
		printGetterTestResult(actual, expectedInt, 7, "getNumColumns method");

		// tests 8 and 9, getVal
		actual = mat2.getVal(0, 0);
		expectedInt = -1;
		printGetterTestResult(actual, expectedInt, 8, "getVal method");

		actual = mat2.getVal(1, 0);
		expectedInt = 1;
		printGetterTestResult(actual, expectedInt, 9, "getVal method");

		// tests 10 - 12, addition
		mat2 = new MathMatrix(new int[][] { { 4, 4, 4, 4, 4 } });
		MathMatrix mat3;
		mat3 = mat1.add(mat2);
		printTestResult(get2DArray(mat1), e1, 10,
				"add method. Testing mat1 unchanged.\n");

		e1 = new int[][] { { 4, 4, 4, 4, 4 } };
		printTestResult(get2DArray(mat2), e1, 11,
				"add method. Testing mat2 unchanged.\n");

		e1 = new int[][] { { 9, 9, 9, 9, 9 } };
		printTestResult(get2DArray(mat3), e1, 12,
				"add method. Testing mat3 correct result.\n");

		// tests 13 - 15, subtract
		mat3 = mat1.subtract(mat2);
		e1 = new int[][] { { 5, 5, 5, 5, 5 } };
		printTestResult(get2DArray(mat1), e1, 13,
				"subtract method. Testing mat1 unchanged.\n");

		e1 = new int[][] { { 4, 4, 4, 4, 4 } };
		printTestResult(get2DArray(mat2), e1, 14,
				"subtract method. Testing mat2 unchanged.\n");

		e1 = new int[][] { { 1, 1, 1, 1, 1 } };
		printTestResult(get2DArray(mat3), e1, 15,
				"subtract method. Testing mat3 correct result.\n");

		// test 16 and 17, multiplication
		data2 = new int[][] { { 5 } };
		mat2 = new MathMatrix(data2);
		mat3 = mat2.multiply(mat1);
		e1 = new int[][] { { 25, 25, 25, 25, 25 } };
		printTestResult(get2DArray(mat3), e1, 16, "multiply method.\n");
		System.out.println("Expected: { { 25, 25, 25, 25, 25 } }");

		data1 = new int[][] { { 1, 3 }, { 1, 3 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(new int[][] { { 3, 3 }, { 1, 1 }, { 3, 3 } });
		mat3 = mat2.multiply(mat1);
		e1 = new int[][] { { 6, 18 }, { 2, 6 }, { 6, 18 } };
		printTestResult(get2DArray(mat3), e1, 17, "multiply method.\n");
		System.out.println("Expected: { { 6, 18 }, { 2, 6 }, { 6, 18 } }");

		// test 18 and 19, getScaledMatrix
		mat3 = mat2.getScaledMatrix(5);
		e1 = new int[][] { { 15, 15 }, { 5, 5 }, { 15, 15 } };
		printTestResult(get2DArray(mat3), e1, 18, "getScaledMatrix method.\n");
		System.out.println("Expected: { { 15, 15 }, { 5, 5 }, { 15, 15 } }");

		mat3 = mat2.getScaledMatrix(0);
		e1 = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 } };
		printTestResult(get2DArray(mat3), e1, 19, "getScaledMatrix method.\n");
		System.out.println("Expected: { { 0, 0 }, { 0, 0 }, { 0, 0 } }");

		// test 20 and 21, getTranspose
		mat3 = mat2.getTranspose();
		e1 = new int[][] { { 3, 1, 3 }, { 3, 1, 3 } };
		printTestResult(get2DArray(mat3), e1, 20, "getTranspose method.\n");
		System.out.println("Expected: { { 3, 1, 3 }, { 3, 1, 3 } }");

		mat3 = mat3.getTranspose();
		e1 = new int[][] { { 3, 3 }, { 1, 1 }, { 3, 3 } };
		printTestResult(get2DArray(mat3), e1, 21, "getTranspose method.\n");
		System.out.println("Expected: { { 3, 1, 3 }, { 3, 1, 3 } }");

		// test 22 - 24, equals
		boolean actualBool = mat3.equals(mat2);
		boolean expectedBool = true;
		System.out.println("Test number 22, equals method. Expected: "
				+ expectedBool + ". Acutal: " + actualBool);

		actualBool = mat3.equals(mat1);
		expectedBool = false;
		System.out.println("Test number 23, equals method. Expected: "
				+ expectedBool + ". Acutal: " + actualBool);

		actualBool = mat3.equals(e1);
		System.out.println(
				"Test number 24, equals method, passes in 2D array. Expected: "
						+ expectedBool + ". Acutal: " + actualBool);

		// test 25 and 26, toString()
		data1[0][0] = -10;
		mat1 = new MathMatrix(data1);
		String expectedString = "| -10   3|\n|   1   3|\n";
		if (mat1.toString().equals(expectedString)) {
			System.out.println("passed test 25, toString method.");
		} else {
			System.out.println("Failed test 25 , toString method.");
		}
		System.out.print(
				"Expected:\n" + expectedString + "Actual:\n" + mat1.toString());

		mat2 = new MathMatrix(data2);
		expectedString = "| 5|\n";
		if (mat2.toString().equals(expectedString)) {
			System.out.println("passed test 26, toString method.");
		} else {
			System.out.println("Failed test 26, toString method.");
		}
		System.out.print(
				"Expected:\n" + expectedString + "Actual:\n" + mat2.toString());

		// test 27 and 28, upperTriangular
		data1[1][0] = 0;
		mat1 = new MathMatrix(data1);
		if (mat1.isUpperTriangular()) {
			System.out.println("Passed test 27, upperTriangular method.");
		} else {
			System.out.println("Failed test 27, upperTriangular method.");
		}

		if (mat2.isUpperTriangular()) {
			System.out.println("Passed test 28, upperTriangular method, 1x1.");
		} else {
			System.out.println("Failed test 28, upperTriangular method, 1x1");
		}

	}

	private static void experiment1(MathMatrix test1, MathMatrix test2,
			Stopwatch st, final int FACTOR) {
		st = new Stopwatch();
		st.start();
		for (int experiment = 0; experiment < ADD_LIMIT; experiment++) {
			test1.add(test2);
		}
		st.stop();
		System.out.println("rows: " + ADD_ROWS_COLS * FACTOR + " columns: "
				+ ADD_ROWS_COLS * FACTOR + " " + st.toString());
	}

	private static void experiment2(MathMatrix test1, MathMatrix test2,
			Stopwatch st, final int FACTOR) {
		st = new Stopwatch();
		st.start();
		for (int experiment = 0; experiment < MULT_LIMIT; experiment++) {
			test1.multiply(test2);
		}
		st.stop();
		System.out.println("rows: " + MULT_ROWS_COLS * FACTOR + " columns: "
				+ MULT_ROWS_COLS * FACTOR + " " + st.toString());
	}

	// create a matrix with random values
	// pre: rows > 0, cols > 0, randNumGen != null
	private static MathMatrix createMat(Random randNumGen, int rows, int cols,
			final int LIMIT) {

		if (randNumGen == null) {
			throw new IllegalArgumentException(
					"randomNumGen variable may no be null");
		} else if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException(
					"rows and columns must be greater than 0. " + "rows: "
							+ rows + ", cols: " + cols);
		}

		int[][] temp = new int[rows][cols];
		final int SUB = LIMIT / 4;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
			}
		}

		return new MathMatrix(temp);
	}

	// Pre: data1 != null, data2 != null, int testNum != null, String
	// testingWhat != null. Post: Test result pass or fail
	private static void printTestResult(int[][] data1, int[][] data2,
			int testNum, String testingWhat) {
		System.out.print(
				"Test number " + testNum + ", " + testingWhat + " The test ");
		String result = equals(data1, data2) ? "passed" : "failed";
		System.out.println(result);
	}

	// pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1
	// matrices data1 and data2 are rectangular matrices
	// post: return true if data1 and data2 are the same size and all elements
	// are the same
	private static boolean equals(int[][] data1, int[][] data2) {
		// check precondition
		if ((data1 == null) || (data1.length == 0) || (data1[0].length == 0)
				|| !rectangularMatrix(data1) || (data2 == null)
				|| (data2.length == 0) || (data2[0].length == 0)
				|| !rectangularMatrix(data2)) {
			throw new IllegalArgumentException(
					"Violation of precondition: equals check on 2d arrays of ints");
		}
		boolean result = (data1.length == data2.length)
				&& (data1[0].length == data2[0].length);
		int row = 0;
		while (result && row < data1.length) {
			int col = 0;
			while (result && col < data1[0].length) {
				result = (data1[row][col] == data2[row][col]);
				col++;
			}
			row++;
		}

		return result;
	}

	// Pre: actual != null, expected != null, int testNum != null, String
	// testingWhat != null. Post: Test result pass or fail
	private static void printGetterTestResult(int actual, int expected,
			int testNum, String testingWhat) {
		System.out.println("Test number " + testNum + ", " + testingWhat
				+ ". Expected = " + expected + ". Actual = " + actual);
		if (actual == expected) {
			System.out.println(" The test passed");
		} else {
			System.out.println(" The test failed");
		}
	}

	// pre: m != null, m is at least 1 by 1 in size
	// return a 2d array of ints the same size as m and with
	// the same elements
	private static int[][] get2DArray(MathMatrix m) {
		// check precondition
		if ((m == null) || (m.getNumRows() == 0) || (m.getNumColumns() == 0)) {
			throw new IllegalArgumentException(
					"Violation of precondition: get2DArray");
		}

		int[][] result = new int[m.getNumRows()][m.getNumColumns()];
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = m.getVal(r, c);
			}
		}
		return result;
	}

	// method to ensure mat is rectangular
	// pre: mat != null, mat is at least 1 by 1
	private static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ " Parameter mat may not be null"
					+ " and must be at least 1 by 1");
		}
		return MathMatrix.rectangularMatrix(mat);
	}
}
