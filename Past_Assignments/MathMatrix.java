package Past_Assignments;
import java.util.Arrays;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: cc66496
*  email address: chris.carrasco@att.net
*  Unique section number: 50875
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices) as used in
 * linear algebra.
 */
public class MathMatrix {

	private int[][] values;
	private int formatLength;

	/**
	 * create a MathMatrix with cells equal to the values in mat. A "deep" copy
	 * of mat is made. Changes to mat after this constructor do not affect this
	 * Matrix and changes to this MathMatrix do not affect mat
	 * 
	 * @param mat mat != null, mat.length > 0, mat[0].length > 0, mat is a
	 *            rectangular matrix
	 */
	public MathMatrix(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0
				|| !rectangularMatrix(mat)) {
			throw new IllegalArgumentException(
					"Violation of preconditions: mat != null, mat.length > 0,"
							+ " mat[0].length > 0, mat is a rectangular matrix");
		}
		values = new int[mat.length][mat[0].length];
		formatLength = 0;

		// copies mat into values and gets the length of the longest number in
		// the 2D array
		for (int row = 0; row < getNumRows(); row++) {
			for (int col = 0; col < getNumColumns(); col++) {
				values[row][col] = mat[row][col];

				String val = "" + values[row][col];

				if (val.length() > formatLength) {
					formatLength = val.length();
				}
			}
		}
	}

	/**
	 * create a MathMatrix of the specified size with all cells set to the
	 * intialValue. <br>
	 * pre: numRows > 0, numCols > 0 <br>
	 * post: create a matrix with numRows rows and numCols columns. All elements
	 * of this matrix equal initialVal. In other words after this method has
	 * been called getVal(r,c) = initialVal for all valid r and c.
	 * 
	 * @param numRows    numRows > 0
	 * @param numCols    numCols > 0
	 * @param initialVal all cells of this Matrix are set to initialVal
	 */
	public MathMatrix(int numRows, int numCols, int initialVal) {
		if (numRows <= 0 || numCols <= 0) {
			throw new IllegalArgumentException(
					"Violation of preconditions: numRows > 0, numCols > 0");
		}
		values = new int[numRows][numCols];
		String val = "" + initialVal;
		formatLength = val.length();

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				values[row][col] = initialVal;
			}
		}
	}

	/**
	 * Get the number of rows.
	 * 
	 * @return the number of rows in this MathMatrix
	 */
	public int getNumRows() {
		return values.length;
	}

	/**
	 * Get the number of columns.
	 * 
	 * @return the number of columns in this MathMatrix
	 */
	public int getNumColumns() {
		return values[0].length;
	}

	/**
	 * get the value of a cell in this MathMatrix. <br>
	 * pre: row 0 <= row < getNumRows(), col 0 <= col < getNumColumns()
	 * 
	 * @param row 0 <= row < getNumRows()
	 * @param col 0 <= col < getNumColumns()
	 * @return the value at the specified position
	 */
	public int getVal(int row, int col) {
		if (row < 0 || row >= getNumRows() || col < 0
				|| col >= getNumColumns()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= row < getNumRows(),"
							+ " 0 <= col < getNumColumns()");
		}
		return values[row][col];
	}

	/**
	 * implements MathMatrix addition, (this MathMatrix) + rightHandSide. <br>
	 * pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() =
	 * getNumColumns() <br>
	 * post: This method does not alter the calling object or rightHandSide
	 * 
	 * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
	 *                      rightHandSide.numCols() = getNumColumns()
	 * @return a new MathMatrix that is the result of adding this Matrix to
	 *         rightHandSide. The number of rows in the returned Matrix is equal
	 *         to the number of rows in this MathMatrix. The number of columns
	 *         in the returned Matrix is equal to the number of columns in this
	 *         MathMatrix.
	 */
	public MathMatrix add(MathMatrix rightHandSide) {
		if (rightHandSide.getNumRows() != getNumRows()
				|| rightHandSide.getNumColumns() != getNumColumns()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: rightHandSide.getNumRows() = getNumRows(),"
							+ " rightHandSide.getNumColumns() = getNumColumns()");
		}
		MathMatrix addValues = new MathMatrix(this.values);

		// sums the value in each respective cell in the two matrices
		for (int row = 0; row < getNumRows(); row++) {
			for (int col = 0; col < getNumColumns(); col++) {
				addValues.values[row][col] += rightHandSide.values[row][col];
			}
		}

		return addValues;
	}

	/**
	 * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
	 * <br>
	 * pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() =
	 * getNumColumns() <br>
	 * post: This method does not alter the calling object or rightHandSide
	 * 
	 * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
	 *                      rightHandSide.numCols() = getNumColumns()
	 * @return a new MathMatrix that is the result of subtracting rightHandSide
	 *         from this MathMatrix. The number of rows in the returned
	 *         MathMatrix is equal to the number of rows in this MathMatrix. The
	 *         number of columns in the returned MathMatrix is equal to the
	 *         number of columns in this MathMatrix.
	 */
	public MathMatrix subtract(MathMatrix rightHandSide) {
		if (rightHandSide.getNumRows() != getNumRows()
				|| rightHandSide.getNumColumns() != getNumColumns()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: rightHandSide.getNumRows() = getNumRows(),"
							+ " rightHandSide.getNumColumns() = getNumColumns()");
		}
		MathMatrix subtractValues = new MathMatrix(this.values);

		// gets the difference of this MathMatrix and rightHandSide for each
		// respective cell in the two matrices
		for (int row = 0; row < getNumRows(); row++) {
			for (int col = 0; col < getNumColumns(); col++) {
				subtractValues.values[row][col] -= rightHandSide.values[row][col];
			}
		}

		return subtractValues;
	}

	/**
	 * implements matrix multiplication, (this MathMatrix) * rightHandSide. <br>
	 * pre: rightHandSide.getNumRows() = getNumColumns() <br>
	 * post: This method should not alter the calling object or rightHandSide
	 * 
	 * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
	 * @return a new MathMatrix that is the result of multiplying this
	 *         MathMatrix and rightHandSide. The number of rows in the returned
	 *         MathMatrix is equal to the number of rows in this MathMatrix. The
	 *         number of columns in the returned MathMatrix is equal to the
	 *         number of columns in rightHandSide.
	 */
	public MathMatrix multiply(MathMatrix rightHandSide) {
		if (rightHandSide.getNumRows() != this.getNumColumns()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: rightHandSide.getNumRows() = getNumColumns()");
		}
		MathMatrix multiplyValues = new MathMatrix(
				new int[this.getNumRows()][rightHandSide.getNumColumns()]);

		// gets the dot product for each cell in the new MathMatrix
		for (int row = 0; row < multiplyValues.getNumRows(); row++) {
			for (int col = 0; col < multiplyValues.getNumColumns(); col++) {
				multiplyValues.values[row][col] = dotProduct(row, col,
						rightHandSide);
			}
		}

		return multiplyValues;
	}

	/*
	 * Pre: 0 <= row < this.getNumRows(), 0 <= col <
	 * rightHandSide.getNumColumns(), rightHandSide != null. Post: return the
	 * dot product for the current cell of multiplyValues.
	 */
	private int dotProduct(int row, int col, MathMatrix rightHandSide) {
		int product = 0;

		// traverses down the entire column of this MathMatrix and the
		// entire row of rightHandSide to calculate the dot product
		for (int rowOrCol = 0; rowOrCol < this.getNumColumns(); rowOrCol++) {
			product += this.values[row][rowOrCol]
					* rightHandSide.values[rowOrCol][col];
		}

		return product;
	}

	/**
	 * Create and return a new Matrix that is a copy of this matrix, but with
	 * all values multiplied by a scale value. <br>
	 * pre: none <br>
	 * post: returns a new Matrix with all elements in this matrix multiplied by
	 * factor. In other words after this method has been called
	 * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor for
	 * all valid r and c.
	 * 
	 * @param factor the value to multiply every cell in this Matrix by.
	 * @return a MathMatrix that is a copy of this MathMatrix, but with all
	 *         values in the result multiplied by factor.
	 */
	public MathMatrix getScaledMatrix(int factor) {
		MathMatrix scaledValues = new MathMatrix(this.values);

		// multiplies all values in this matrix with factor
		for (int row = 0; row < getNumRows(); row++) {
			for (int col = 0; col < getNumColumns(); col++) {
				scaledValues.values[row][col] *= factor;
			}
		}

		return scaledValues;
	}

	/**
	 * accessor: get a transpose of this MathMatrix. This Matrix is not changed.
	 * <br>
	 * pre: none
	 * 
	 * @return a transpose of this MathMatrix
	 */
	public MathMatrix getTranspose() {
		MathMatrix transposedValues = new MathMatrix(getNumColumns(),
				getNumRows(), 0);

		// copies every value in this Matrix into the appropriate cell in
		// transposeValues
		for (int row = 0; row < getNumRows(); row++) {
			for (int col = 0; col < getNumColumns(); col++) {
				transposedValues.values[col][row] = this.values[row][col];
			}
		}

		return transposedValues;
	}

	/**
	 * override equals.
	 * 
	 * @return true if rightHandSide is the same size as this MathMatrix and all
	 *         values in the two MathMatrix objects are the same, false
	 *         otherwise
	 */
	public boolean equals(Object rightHandSide) {
		boolean result = false;

		if (rightHandSide != null
				&& this.getClass() == rightHandSide.getClass()) {
			// rightHandSide is a non null MathMatrix
			MathMatrix otherMatrix = (MathMatrix) rightHandSide;

			if (otherMatrix.getNumRows() == this.getNumRows()
					&& otherMatrix.getNumColumns() == this.getNumColumns()) {
				// same number of rows and columns
				for (int row = 0; row < getNumRows(); row++) {
					for (int col = 0; col < getNumColumns(); col++) {
						if (otherMatrix.values[row][col] != this.values[row][col]) {
							// false if any value is not equivalent
							return result;
						}
					}
				}
				result = true;
			}
		}

		return result;
	}

	/**
	 * override toString.
	 * 
	 * @return a String with all elements of this MathMatrix. Each row is on a
	 *         separate line. Spacing based on longest element in this Matrix.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < getNumRows(); row++) {
			sb.append("|");
			for (int col = 0; col < getNumColumns(); col++) {
				sb.append(String.format(" %" + formatLength + "s",
						values[row][col]));
			}
			sb.append("|");
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * Return true if this MathMatrix is upper triangular. To be upper
	 * triangular all elements below the main diagonal must be 0.<br>
	 * pre: this is a square matrix. getNumRows() == getNumColumns()
	 * 
	 * @return <tt>true</tt> if this MathMatrix is upper triangular,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean isUpperTriangular() {
		if (getNumRows() != getNumColumns()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: getNumRows() == getNumColumns()");
		}

		// for each value
		for (int row = 0; row < getNumRows(); row++) {
			int pivotCol = row;

			for (int col = 0; col < getNumColumns(); col++) {
				// Checks if there are only 0s before the pivotCol of each row
				if (col < pivotCol && values[row][col] != 0) {
					return false;
				}
			}
		}

		return true;
	}

	// method to ensure mat is rectangular
	// pre: mat != null, mat has at least one row
	// return true if all rows in mat have the same
	// number of columns false otherwise.
	public static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException(
					"argument mat may not be null and must "
							+ " have at least one row. mat = "
							+ Arrays.toString(mat));
		}
		boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;
		while (isRectangular && row < mat.length) {
			isRectangular = (mat[row].length == COLUMNS);
			row++;
		}
		return isRectangular;
	}

}
