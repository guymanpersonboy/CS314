package Extra_Tests;

import java.util.Random;

import Math_Matrix.MathMatrix;
import Other.Stopwatch;

/**
 * Extra tests for the MathMatrix class, includes all provided tests.
 */
public class MMExtraTests {

	/**
	 * main method that runs simple test on the MathMatrix class
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] data1 = { { 1, 2, 3 }, { 2, 3, 4 } };
		int[][] data2 = { { 2, 1, 1 }, { 2, 3, 1 } };
		int[][] e1;

		// test 1, specify size and values constructor
		MathMatrix mat1 = new MathMatrix(2, 3, -1);
		e1 = new int[][] { { -1, -1, -1 }, { -1, -1, -1 } };
		printTestResult(get2DArray(mat1), e1, 1,
				"Constructor with size and initial val specified.\n");

		// tests 2 and 3, int[][] constructor, deep copy
		mat1 = new MathMatrix(data1);
		data1[0][0] = 2;
		// alter data1. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 2, 2, 3 }, { 2, 3, 4 } };
		printTestResult(data1, e1, 2,
				"constructor with one parameter of type int[][].\n");
		// data1 altered. mat1 should be unchanged if deep copy made
		e1 = new int[][] { { 1, 2, 3 }, { 2, 3, 4 } };
		printTestResult(get2DArray(mat1), e1, 3,
				"constructor with one parameter of type int[][]. Testing deep copy made.\n");

		// tests 4 - 6, addition
		data1[0][0] = 1;
		mat1 = new MathMatrix(data1);
		MathMatrix mat2 = new MathMatrix(data2);
		MathMatrix mat3 = mat1.add(mat2);
		e1 = new int[][] { { 1, 2, 3 }, { 2, 3, 4 } };
		printTestResult(get2DArray(mat1), e1, 4,
				"add method. Testing mat1 unchanged.\n");
		e1 = new int[][] { { 2, 1, 1 }, { 2, 3, 1 } };
		printTestResult(get2DArray(mat2), e1, 5,
				"add method. Testing mat2 unchanged.\n");
		e1 = new int[][] { { 3, 3, 4 }, { 4, 6, 5 } };
		printTestResult(get2DArray(mat3), e1, 6,
				"add method. Testing mat3 correct result.\n");

		// test 7, multiplication
		data2 = new int[][] { { 1, 2 }, { 3, 1 }, { 2, 1 } };
		mat2 = new MathMatrix(data2);
		mat1 = new MathMatrix(data1);
		mat3 = mat2.multiply(mat1);
		e1 = new int[][] { { 5, 8, 11 }, { 5, 9, 13 }, { 4, 7, 10 } };
		printTestResult(get2DArray(mat3), e1, 7, "multiply method.\n");

		// test 8, toString()
		data1 = new int[][] { { 10, 100, 101, -1000 }, { 1000, 10, 55, 4 },
				{ 1, -1, 4, 0 } };
		mat1 = new MathMatrix(data1);
		String expected = "|    10   100   101 -1000|\n|  1000    10    55     4|\n|     1    -1     4     0|\n";
		if (mat1.toString().equals(expected)) {
			System.out.println("passed test 8, toString method.");
		} else {
			System.out.println("Failed test 8, toString method.");
		}
		System.out.print(
				"Expected:\n" + expected + "Actual:\n" + mat1.toString());

		// test 9, upperTriangular
		data1 = new int[][] { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 },
				{ 0, 0, 0, 12 } };
		mat1 = new MathMatrix(data1);
		if (mat1.isUpperTriangular()) {
			System.out.println("Passed test 9, upperTriangular method.");
		} else {
			System.out.println("Failed test 9, upperTriangular method.");
		}

		// test 10, upperTriangular
		data1 = new int[][] { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 },
				{ 1, 2, 3, 4 } };
		mat1 = new MathMatrix(data1);
		if (!mat1.isUpperTriangular()) {
			System.out.println("Passed test 10, upperTriangular method.");
		} else {
			System.out.println("Failed test 10, upperTriangular method.");
		}

		// test 11 - 14, mutliply stress test (possible to get Answer by
		// Accident)
		Random randNumGen = new Random(6201919);
		final int MAGIC_SUM = -1190513360;
		final int ROWS1 = 1000;
		final int COLS1 = 500;
		final int ROWS2 = 500;
		final int COLS2 = 750;
		final int LIMIT = 1000;
		mat1 = createMat(randNumGen, ROWS1, COLS1, LIMIT);
		mat2 = createMat(randNumGen, ROWS2, COLS2, LIMIT);
		Stopwatch st = new Stopwatch();
		st.start();
		mat3 = mat1.multiply(mat2);
		st.stop();

		if (mat3.getNumRows() == ROWS1) {
			System.out.println("Passed test 11, multiply stress test numRows.");
		} else {
			System.out.println("Failed test 11, multiply stress test numRows");
		}

		if (mat3.getNumColumns() == COLS2) {
			System.out.println("Passed test 12, multiply stress test numCols.");
		} else {
			System.out.println("Failed test 12, multiply stress test numCols");
		}

		if (sumVals(mat3) == MAGIC_SUM) {
			System.out.println(
					"Passed test 13, stress test, sum of values in result.");
		} else {
			System.out.println(
					"Failed test 13, stress test, sum of values in result.");
		}
		System.out.println(" " + st.toString());

		st = new Stopwatch();
		final int MAGIC_STRING_LENGTH = 6753000;
		st.start();
		if (mat3.toString().length() == MAGIC_STRING_LENGTH) {
			System.out.println(
					"Passed test 14, stress test, length of toString result.");
		} else {
			System.out.println(
					"Failed test 14, stress test, length of toString result.");
		}
		st.stop();

		System.out.println("Expected: " + MAGIC_STRING_LENGTH + " Actual: "
				+ mat3.toString().length());
		System.out.println(" " + st.toString());

		// Begin Extra Tests

		/**
		 * Hi everyone, these are the test cases I used for MathMatrix. To test
		 * for exceptions, uncomment the 'PRE-CHECK' sections. - Nathaniel
		 * Nemenzo
		 */
		int[][] matrixArray = { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };
		int[][] matrixArray2 = { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };

		/* CONSTRUCTORS */
		MathMatrix matrix1 = new MathMatrix(3, 2, 5);
		int[][] temp = { { 5, 5 }, { 5, 5 }, { 5, 5 } };
		printTestResult(get2DArray(matrix1), temp, 1,
				"Non-Matrix" + " constructor equality");

		matrix1 = new MathMatrix(matrixArray);
		printTestResult(get2DArray(matrix1), matrixArray, 2,
				"2D-Array " + "parameter");

		temp = new int[][] { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };
		matrixArray[0][0] = 100;
		printTestResult(get2DArray(matrix1), temp, 3,
				"Deep " + "copy " + "for 2D-Array parameter");

		// CONSTRUCTOR PRE-CHECK (SHOULD THROW EXCEPTIONS)
		// temp = new int[][] {{}};
		// matrix1 = new MathMatrix(temp);
		// matrix1 = new MathMatrix(-1, 1, 1);
		// matrix1 = new MathMatrix(1, 0, 1);

		/* ADD TESTS */
		matrixArray[0][0] = 1;
		matrix1 = new MathMatrix(matrixArray);
		MathMatrix matrix2 = new MathMatrix(matrixArray2);
		temp = new int[][] { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };
		printTestResult(get2DArray(matrix1), temp, 4,
				"Unchanged left " + "side when adding matrices");

		printTestResult(get2DArray(matrix2), temp, 5,
				"Unchanged left " + "side when adding matrices");

		MathMatrix matrix3 = matrix2.add(matrix1);
		temp = new int[][] { { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 } };
		printTestResult(get2DArray(matrix3), temp, 6, "Matrix addition");

		// ADD PRE-CHECK
		// temp = new int[][]{{1, 2, 3}, {1, 2, 3}};
		// matrix2 = new MathMatrix(temp); matrix3 = matrix2.add(matrix1);

		/* EQUALS */
		matrix1 = new MathMatrix(matrixArray);
		matrix2 = new MathMatrix(matrixArray);
		if (matrix1.equals(matrix2)) {
			System.out.println("*****EQUALS TEST 7 PASSED*****");
		} else {
			System.out.println("*****EQUALS TEST 7 FAILED*****");
		}

		/* GET NUM COLS / NUM ROWS */
		matrix1 = new MathMatrix(1, 3, 1);
		if (matrix1.getNumColumns() == 3) {
			System.out.println("*****NUMCOLS TESTS 8 PASSED*****");
		} else {
			System.out.println("*****NUMCOLS TEST 8 FAILED*****");
		}

		if (matrix1.getNumRows() == 1) {
			System.out.println("*****NUMROWS TESTS 9 PASSED*****");
		} else {
			System.out.println("*****NUMROWS TEST 9 FAILED*****");
		}

		matrix1 = new MathMatrix(3, 1, 1);
		if (matrix1.getNumColumns() == 1) {
			System.out.println("*****NUMCOLS TESTS 10 PASSED*****");
		} else {
			System.out.println("*****NUMCOLS TEST 10 FAILED*****");
		}

		if (matrix1.getNumRows() == 3) {
			System.out.println("*****NUMROWS TESTS 11 PASSED*****");
		} else {
			System.out.println("*****NUMROWS TEST 11 FAILED*****");
		}

		/* SCALED MATRIX TESTS */
		matrix1 = new MathMatrix(matrixArray);
		matrix2 = matrix1.getScaledMatrix(2);
		temp = new int[][] { { 2, 4, 6 }, { 2, 4, 6 }, { 2, 4, 6 } };
		printTestResult(get2DArray(matrix2), temp, 12,
				"Scaled matrix " + "equality");

		printTestResult(get2DArray(matrix1), matrixArray, 13,
				"Left hand " + "array unchanged in scaling");

		matrix2 = matrix1.getScaledMatrix(3);
		temp = new int[][] { { 3, 6, 9 }, { 3, 6, 9 }, { 3, 6, 9 } };
		printTestResult(get2DArray(matrix2), temp, 14,
				"Scaled matrix " + "equality");

		/* TRANSPOSE */
		temp = new int[][] { { 1, 1, 1 }, { 2, 2, 2 } };
		int[][] temp1 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
		matrix1 = new MathMatrix(temp);
		matrix2 = matrix1.getTranspose();
		printTestResult(get2DArray(matrix2), temp1, 15, "Transpose test 1");

		matrix1 = matrix2.getTranspose();
		printTestResult(get2DArray(matrix1), temp, 16, "Transpose test 2");

		/* GET VAL */
		matrix1 = new MathMatrix(matrixArray);
		if (matrix1.getVal(0, 0) == 1) {
			System.out.println("*****GET VAL TEST 17 PASSED*****");
		} else {
			System.out.println("*****GET VAL TEST 17 FAILED*****");
		}

		if (matrix1.getVal(2, 2) == 3) {
			System.out.println("*****GET VAL TEST 18 PASSED*****");
		} else {
			System.out.println("*****GET VAL TEST 18 FAILED*****");
		}

		// GET VAL PRE CHECKS
		// matrix1.getVal(-1, 0);
		// matrix1.getVal(0, -1);
		// matrix1.getVal(4, 0);
		// matrix1.getVal(0, 4);

		/* UPPER RECTANGULAR */
		temp = new int[][] { { 1, 2, 3 }, { 0, 1, 2 }, { 0, 0, 1 } };
		temp1 = new int[][] { { 1, 2, 3 }, { 0, 1, 2 }, { 0, 1, 1 } };

		matrix1 = new MathMatrix(temp);
		matrix2 = new MathMatrix(temp1);

		if (matrix1.isUpperTriangular()) {
			System.out.println("*****UPPER TRIANGULAR TEST 19 PASSED*****");
		} else {
			System.out
					.println("*****UPPER TRIANGULAR TEST 19 FAILED" + "*****");
		}

		if (matrix2.isUpperTriangular()) {
			System.out.println("*****UPPER TRIANGULAR TEST 20 FAILED*****");
		} else {
			System.out.println("*****UPPER TRIANGULAR TEST 20 PASSED*****");
		}

		/*
		 * UPPER RECTANGULAR PRE CHECK temp = new int[][] { {1, 2, 3}, {1, 2, 3}
		 * } matrix1 = new MathMatrix(temp); matrix1.isUpperTriangular();
		 */

		/* MULTIPLY */
		matrix1 = new MathMatrix(matrixArray);
		matrix2 = matrix1.getTranspose();
		matrix3 = matrix1.multiply(matrix2);
		temp = new int[][] { { 14, 14, 14 }, { 14, 14, 14 }, { 14, 14, 14 } };
		printTestResult(get2DArray(matrix3), temp, 21,
				"Multiplying test " + "1");

		temp = new int[][] { { 2, 2, 2 }, { 3, 3, 3 } };
		matrix1 = new MathMatrix(temp);
		matrix3 = matrix1.multiply(matrix1.getTranspose());
		temp = new int[][] { { 12, 18 }, { 18, 27 } };
		printTestResult(get2DArray(matrix3), temp, 22,
				"Multiplying test " + "2");

		/*
		 * MULTIPLY PRE CHECKS temp = new int[][] {{1, 2}, {1, 2}, {1, 2}};
		 * temp1 = new int[][] {{1, 2}, {1, 2}, {1, 2}}; matrix1 = new
		 * MathMatrix(temp); matrix2 = new MathMatrix(temp1); matrix3 =
		 * matrix1.multiply(matrix2);
		 */

		/* SUBTRACT */
		temp = new int[][] { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };
		matrix1 = new MathMatrix(matrixArray);
		matrix2 = new MathMatrix(temp);
		matrix3 = matrix1.subtract(matrix2); // matrix2 should be unchanged
		if (equals(get2DArray(matrix2), temp)) {
			System.out.println(
					"Test number 21 tests the Subtract test 1 The test passed");
		} else {
			// if the same, deep copy wasn't made
			System.out.println(
					"Test number 21 tests the Subtract test 1 The test failed");
		}

		temp = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		printTestResult(get2DArray(matrix3), temp, 22, "Subtract test 2");

		matrix3 = matrix3.subtract(matrix1);
		temp = new int[][] { { -1, -2, -3 }, { -1, -2, -3 }, { -1, -2, -3 } };
		printTestResult(get2DArray(matrix3), temp, 23, "Subtract test 3");

		/*
		 * SUBTRACT PRE CHECKS temp = new int[][] {{1, 2}, {1, 2}, {1, 2}};
		 * matrix2 = new MathMatrix(temp); matrix3 = matrix1.subtract(matrix2);
		 */

		/* TO STRING */
		temp = new int[][] { { 300 }, { 40000 } };
		matrix1 = new MathMatrix(matrixArray);
		matrix2 = new MathMatrix(temp);
		String matrix1String = "| 1 2 3|\n" + "| 1 2 3|\n" + "| 1 2 3|\n";
		if (matrix1.toString().equals(matrix1String)) {
			System.out.println("*****TOSTRING TEST 24 PASSED*****");
		} else {
			System.out.println("*****TOSTRING TEST 24 FAILED*****");
		}

		matrix1String = "|   300|\n" + "| 40000|\n";

		if (matrix2.toString().equals(matrix1String)) {
			System.out.println("*****TOSTRING TEST 25 PASSED*****");
		} else {
			System.out.println("*****TOSTRING TEST 25 FAILED*****");
		}

	}

	// method that sums elements of mat, may overflow int!
	// pre: mat != null
	private static int sumVals(MathMatrix mat) {
		if (mat == null) {
			throw new IllegalArgumentException("mat may not be null");
		}
		int result = 0;
		final int ROWS = mat.getNumRows();
		final int COLS = mat.getNumColumns();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				result += mat.getVal(r, c); // likely to overflow, but can still
											// do simple check
			}
		}
		return result;
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

	private static void printTestResult(int[][] data1, int[][] data2,
			int testNum, String testingWhat) {
		System.out.print("Test number " + testNum + " tests the " + testingWhat
				+ " The test ");
		String result = equals(data1, data2) ? "passed" : "failed";
		System.out.println(result);
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
