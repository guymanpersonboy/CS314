package Recursion;
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

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods.
 */
public class Recursive {

	private static final String NEG_SIGN = "-";
	// N E S W
	private static final int[] ROW_DIR = { -1, 0, 1, 0 };
	private static final int[] COL_DIR = { 0, 1, 0, -1 };
	private static final char START = 'S';
	private static final char COIN = '$';
	private static final char GREEN = 'G';
	private static final char YELLOW = 'Y';
	private static final char WALL = '*';
	private static final char EXIT = 'E';
	private static final char BASIS = ' ';
	private static final String VALID_CHARS = "S$GY*E";
	private static final int S_ROW = 0;
	private static final int S_COL = 1;
	private static final int S_FOUND = 2;
	private static final int ONLY_ONE_S = 3;
	private static final int ARE_VALID_CHARS = 4;
	private static final int NUM_COINS = 5;
	private static final int FOUND_EXIT = 6;
	private static final int PRECON_LENGTH = 7;
	private static final int TRUE = 1;
	private static final int FALSE = 2;
	private static final int THIRD = 3;
	private static final int HALF = 2;
	private static final int FINAL_RESULT = 0;
	private static final int COINS_LEFT = 1;
	private static final int EXIT_REACHABLE = 2;
	private static final int RESULT_LENGTH = 3;

	/**
	 * Problem 1: convert a base 10 int to binary recursively. <br>
	 * pre: n != Integer.MIN_VALUE<br>
	 * <br>
	 * post: Returns a String that represents N in binary. All chars in returned
	 * String are '1's or '0's. Most significant digit is at position 0
	 * 
	 * @param n the base 10 int to convert to base 2
	 * @return a String that is a binary representation of the parameter n
	 */
	public static String getBinary(int n) {
		if (n == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "getBinary. n cannot equal "
							+ "Integer.MIN_VALUE. n: " + n);
		}
		// used to add "-" to the front of the final binary
		boolean negative = false;
		if (n < 0) {
			negative = true;
		}
		// calculates binary with positive numbers
		final int ABS_NUM = Math.abs(n);
		// base case
		if (ABS_NUM == 1) {
			return "1";
		}
		// special case
		if (ABS_NUM == 0) {
			return "0";
		}
		StringBuilder binaryBuilder = new StringBuilder();

		// appends remainder of mod 2 in proper order until binary
		binaryBuilder.append(getBinary(ABS_NUM / 2) + ABS_NUM % 2);

		// appends negative sign to the front if n is negative
		if (negative) {
			binaryBuilder.insert(0, NEG_SIGN);
		}

		return binaryBuilder.toString();
	}

	/**
	 * Problem 2: reverse a String recursively.<br>
	 * pre: stringToRev != null<br>
	 * post: returns a String that is the reverse of stringToRev
	 * 
	 * @param stringToRev the String to reverse.
	 * @return a String with the characters in stringToRev in reverse order.
	 */
	public static String revString(String stringToRev) {
		if (stringToRev == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "revString. parameter may not be null.");
		}
		StringBuilder revBuilder = new StringBuilder();

		// if haven't gone through entire original stringToRev
		if (stringToRev.length() != 0) {
			final int LAST_INDEX = stringToRev.length() - 1;

			// append last char to resultString
			revBuilder.append(stringToRev.charAt(LAST_INDEX));
			final String SMALLER_STRING = stringToRev.substring(0, LAST_INDEX);

			// call this method with the current stringToRev without the last
			// character
			revBuilder.append(revString(SMALLER_STRING));
		}

		return revBuilder.toString();
	}

	/**
	 * Problem 3: Returns the number of elements in data that are followed
	 * directly by value that is double that element. pre: data != null post:
	 * return the number of elements in data that are followed immediately by
	 * double the value
	 * 
	 * @param data The array to search.
	 * @return The number of elements in data that are followed immediately by a
	 *         value that is double the element.
	 */
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "revString. parameter may not be null.");
		}
		if (data.length > 0) {
			return doubleHelper(data, 0);
		}
		return 0;
	}

	/*
	 * Uses recursion to count the number of elements followed by its double
	 * 
	 * @param data The array to search
	 * @param pos The current position in data
	 * @return The number of doubles
	 */
	private static int doubleHelper(int[] data, int pos) {
		int count = 0;

		// base case
		if (pos + 1 == data.length) {
			return 0;
		}
		final int CUR = data[pos];
		final int NEXT = data[pos + 1];
		
		// if the next number is twice the current
		if (CUR != 0 && NEXT / CUR == 2 || CUR == 0 && CUR == NEXT) {
			count = doubleHelper(data, pos + 1) + 1;
		} else {
			count = doubleHelper(data, pos + 1);
		}

		return count;
	}

	/**
	 * Problem 4: Find all combinations of mnemonics for the given number.<br>
	 * pre: number != null, number.length() > 0, all characters in number are
	 * digits<br>
	 * post: see tips section of assignment handout
	 * 
	 * @param number The number to find mnemonics for
	 * @return An ArrayList<String> with all possible mnemonics for the given
	 *         number
	 */
	public static ArrayList<String> listMnemonics(String number) {
		if (number == null || number.length() == 0 || !allDigits(number)) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "listMnemonics");
		}
		ArrayList<String> result = new ArrayList<>();
		recursiveMnemonics(result, "", number);

		// removes mnemonics of the incorrect size
		for (int mn = result.size() - 1; mn >= 0; mn--) {
			if (result.get(mn).length() != number.length()) {
				result.remove(mn);
			}
		}

		return result;
	}

	/*
	 * Helper method for listMnemonics to run the recursion
	 * 
	 * @param mnemonics ArrayList to fill with all possible mnemonics
	 * @param mnemonicSoFar The mnemonic being built recursively
	 * @param digitsLeft The digits being mapped over
	 */
	private static void recursiveMnemonics(ArrayList<String> mnemonics,
			String mnemonicSoFar, String digitsLeft) {
		// base case is if digitsLeft is length 0, we do nothing
		if (digitsLeft.length() != 0) {
			String combo = digitLetters(digitsLeft.charAt(0));
			
			for (int index = 0; index < combo.length(); index++) {
				String newMnSoFar = mnemonicSoFar + combo.charAt(index);

				recursiveMnemonics(mnemonics, newMnSoFar,
						digitsLeft.substring(1));
				mnemonics.add(newMnSoFar);
			}
		}
	}

	// used by method digitLetters
	private static final String[] letters = { "0", "1", "ABC", "DEF", "GHI",
			"JKL", "MNO", "PQRS", "TUV", "WXYZ" };

	/*
	 * helper method for recursiveMnemonics pre: ch is a digit '0' through '9'
	 * post: return the characters associated with this digit on a phone keypad
	 */
	private static String digitLetters(char ch) {
		if (ch < '0' || ch > '9') {
			throw new IllegalArgumentException("parameter "
					+ "ch must be a digit, 0 to 9. Given value = " + ch);
		}
		int index = ch - '0';
		return letters[index];
	}

	/*
	 * helper method for listMnemonics pre: s != null post: return true if every
	 * character in s is a digit ('0' through '9')
	 */
	private static boolean allDigits(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "allDigits. String s cannot be null.");
		}
		boolean allDigits = true;
		int i = 0;
		while (i < s.length() && allDigits) {
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}

	/**
	 * Problem 5: Draw a Sierpinski Carpet.
	 * 
	 * @param size  the size in pixels of the window
	 * @param limit the smallest size of a square in the carpet.
	 */
	public static void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}

	/*
	 * Helper method for drawCarpet Draw the individual squares of the carpet.
	 * 
	 * @param g The Graphics object to use to fill rectangles
	 * @param size The size of the current square
	 * @param limit The smallest allowable size of squares
	 * @param x The x coordinate of the upper left corner of the current square
	 * @param y The y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, double x,
			double y) {
		// if size is still larger than limit, draw the squares recursively
		if (size > limit) {
			// fill the middle square at a third of the size
			g.fillRect((int) x + size / THIRD, (int) y + size / THIRD,
					size / THIRD, size / THIRD);
			// recursively add the remaining 8 squares
			// at their specified cooredinates
			drawSquares(g, size / THIRD, limit, x, y);
			drawSquares(g, size / THIRD, limit, x, y + size / THIRD);
			drawSquares(g, size / THIRD, limit, x, y + size * HALF / THIRD);
			drawSquares(g, size / THIRD, limit, x + size * HALF / THIRD, y);
			drawSquares(g, size / THIRD, limit, x + size / THIRD, y);
			drawSquares(g, size / THIRD, limit, x + size / THIRD,
					y + size * HALF / THIRD);
			drawSquares(g, size / THIRD, limit, x + size * HALF / THIRD,
					y + size / THIRD);
			drawSquares(g, size / THIRD, limit, x + size * HALF / THIRD,
					y + size * HALF / THIRD);
		}
	}

	/**
	 * Problem 6: Determine if water at a given point on a map can flow off the
	 * map. <br>
	 * pre: map != null, map.length > 0, map is a rectangular matrix, 0 <= row <
	 * map.length, 0 <= col < map[0].length <br>
	 * post: return true if a drop of water starting at the location specified
	 * by row, column can reach the edge of the map, false otherwise.
	 * 
	 * @param map The elevations of a section of a map.
	 * @param row The starting row of a drop of water.
	 * @param col The starting column of a drop of water.
	 * @return return true if a drop of water starting at the location specified
	 *         by row, column can reach the edge of the map, false otherwise.
	 */
	public static boolean canFlowOffMap(int[][] map, int row, int col) {
		// check preconditions
		if (map == null || map.length == 0 || !isRectangular(map)
				|| !inbounds(row, col, map)) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "canFlowOffMap");
		}
		// loop through direcions and incrememnt in possible directions
		for (int dir = 0; dir < ROW_DIR.length; dir++) {
			// base case
			if (row == map.length - 1 || col == map[0].length - 1 || row == 0
					|| col == 0) {
				return true;
			}
			// found lower elevation, continue on
			if (map[row + ROW_DIR[dir]][col + COL_DIR[dir]] < map[row][col]) {
				if (canFlowOffMap(map, row + ROW_DIR[dir],
						col + COL_DIR[dir])) {
					return true;
				}
			}
		}
		// did not find a way off map
		return false;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to
	 * call this method, pre: mat != null,
	 */
	private static boolean inbounds(int r, int c, int[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "inbounds. The 2d array mat may not be null.");
		}
		return r >= 0 && r < mat.length && mat[r] != null && c >= 0
				&& c < mat[r].length;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to
	 * call this method, pre: mat != null, mat.length > 0 post: return true if
	 * mat is rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "inbounds. The 2d array mat may not be null "
					+ "and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}

	/**
	 * Problem 7: Find the minimum difference possible between teams based on
	 * ability scores. The number of teams may be greater than 2. The goal is to
	 * minimize the difference between the team with the maximum total ability
	 * and the team with the minimum total ability. <br>
	 * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams <br>
	 * post: return the minimum possible difference between the team with the
	 * maximum total ability and the team with the minimum total ability.
	 * 
	 * @param numTeams  the number of teams to form. Every team must have at
	 *                  least one member
	 * @param abilities the ability scores of the people to distribute
	 * @return return the minimum possible difference between the team with the
	 *         maximum total ability and the team with the minimum total
	 *         ability. The return value will be greater than or equal to 0.
	 */
	public static int minDifference(int numTeams, int[] abilities) {
		// precondtions
		if (numTeams < HALF || abilities == null
				|| abilities.length < numTeams) {
			throw new IllegalArgumentException("Number of teams must be >"
					+ " 1, abilites cannot be null and must be > number of teams");
		}
		int[] teams = new int[numTeams];
		// return helper method result
		return minDiffHelper(teams, Integer.MAX_VALUE, 0, abilities);
	}

	/*
	 * Helper Method to recursively find the minimum difference from all
	 * permutations of abilities and teams.
	 * 
	 * @param teams: Array of size numTeams
	 * @param oldDiff: The Old difference from last recursion
	 * @param currentAbility: Current index of abilities
	 * @param abilities: Array of abilities
	 * @return: return the difference for this current recursion
	 */
	public static int minDiffHelper(int[] teams, int oldDiff,
			int currentAbility, int[] abilities) {
		int diffResult = oldDiff;
		boolean usedAllAbilities = false;
		// added all abilities to teams
		if (currentAbility == abilities.length) {
			usedAllAbilities = true;
			// findDifference checks if valid
			return findDifference(teams, usedAllAbilities, currentAbility,
					abilities.length);
		} else if (currentAbility != abilities.length) {
			for (int t = 0; t < teams.length; t++) {
				teams[t] += abilities[currentAbility];
				int result = minDiffHelper(teams, oldDiff, currentAbility + 1,
						abilities);
				// if result is less than the previous best
				if (result < oldDiff) {
					diffResult = result;
					oldDiff = diffResult;
				}
				// we've gone through all the options
				// and valid so return the current best diff
				if (currentAbility == abilities.length) {
					usedAllAbilities = true;
					if (!invalid(teams, usedAllAbilities)) {
						return diffResult;
					}
				}
				// still invalid, so remove last addititon
				teams[t] -= abilities[currentAbility];
			}
			// go back an ability and try the next permutation
			currentAbility--;
			usedAllAbilities = false;
		}
		return diffResult;
	}

	/*
	 * Helper Method that finds the difference between the current permutation
	 * of teams.
	 * 
	 * @param teams: the array of size numTeams that represents all teams
	 * @param usedAllAbilities: makes sure the current permutation has gone through
	 * all people in abilities
	 * @param currentAbility: the index representation of abilities
	 * @param totalAbilities: abilities.length used to co check that all people
	 * in team
	 * @return: the difference in the current permutation of teams
	 */
	private static int findDifference(int[] teams, boolean usedAllAbilities,
			int currentAbility, int totalAbilities) {
		// if havent put everyone into a team or invalid, return Integer.MAX
		if (currentAbility != totalAbilities || invalid(teams, usedAllAbilities)) {
			return Integer.MAX_VALUE;
		} else { // everyone placed in a team
			int min = teams[0];
			int max = teams[0];
			// search for min and max
			for (int i = 0; i < teams.length; i++) {
				if (teams[i] < min) {
					min = teams[i];
				}
				if (teams[i] > max) {
					max = teams[i];
				}
			}
			// caclulate difference and return
			int difference = max - min;
			return difference;
		}
	}

	/*
	 * Helper method that makes sure the current permutation of abilities in
	 * teams is valid, meaning there are no zeros and all people in abilities
	 * are in a team
	 * 
	 * @param teams: the array that represents the teams
	 * @param usedAllAbilities: the boolean that makes sure all people are in a team
	 */
	private static boolean invalid(int[] teams, boolean usedAllAbilities) {
		// check if teams has a 0
		for (int i = 0; i < teams.length; i++) {
			if (teams[i] == 0 && usedAllAbilities) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Problem 8: Maze solver. <br>
	 * pre: board != null <br>
	 * pre: board is a rectangular matrix <br>
	 * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*' <br>
	 * pre: there is a single 'S' character present <br>
	 * post: rawMaze is not altered as a result of this method. Return 2 if it
	 * is possible to escape the maze after collecting all the coins. Return 1
	 * if it is possible to escape the maze but without collecting all the
	 * coins. Return 0 if it is not possible to escape the maze. More details in
	 * the assignment handout.
	 * 
	 * @param rawMaze represents the maze we want to escape. rawMaze is not
	 *                altered as a result of this method.
	 * @return per the post condition
	 */
	public static int canEscapeMaze(char[][] rawMaze) {
		final int[] PRECONS = mazePrecons(rawMaze); // must call before checking
		if (rawMaze == null || !mazeIsRectangular(rawMaze)
				|| PRECONS[S_FOUND] == FALSE || PRECONS[ONLY_ONE_S] == FALSE
				|| PRECONS[ARE_VALID_CHARS] == FALSE) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "canEscapeMaze");
		}
		if (PRECONS[FOUND_EXIT] == FALSE) {
			return 0;
		}
		char[][] maze = new char[rawMaze.length][rawMaze[0].length];

		// copy rawMaze
		for (int row = 0; row < rawMaze.length; row++) {
			for (int col = 0; col < rawMaze[0].length; col++) {
				maze[row][col] = rawMaze[row][col];
			}
		}
		int[] result = new int[RESULT_LENGTH];
		// 0 1 2
		// { the result, coins, exit is reachable}
		result[COINS_LEFT] = PRECONS[NUM_COINS];

		result = mazeHelper(PRECONS[S_ROW], PRECONS[S_COL], maze, BASIS,
				result);

		if (result[FINAL_RESULT] == 0 && result[COINS_LEFT] != 0 && result[EXIT_REACHABLE] == TRUE) {
			return 1;
		}
		return result[FINAL_RESULT];
	}

	/*
	 * Helper Method for canEscapeMaze, analyzes each possible cell in the maze
	 * array, replaces tiles, and checks to find coins and exits.
	 * 
	 * @param startRow: current Row we are looking at
	 * @param startCol: current Col we are looking at
	 * @param maze: the maze represented as an array of chars
	 * @param lastChanged: the last char we changed in the previous recursion
	 * @param result: the array of results from the current maze
	 * @return: The param result array 
	 */
	private static int[] mazeHelper(int startRow, int startCol, char[][] maze,
			char lastChanged, int[] result) {
		char changed = lastChanged;

		// base case
		if (maze[startRow][startCol] == EXIT && result[COINS_LEFT] == 0) {
			result[FINAL_RESULT] = 2; // can exit maze with all coins
			return result;
		} else if ((maze[startRow][startCol] == EXIT && result[COINS_LEFT] != 0)) {
			result[FINAL_RESULT] = 1; // can exit maze but not with all coins
			return result;
		} else if (!trapped(startRow, startCol, maze)) {
			// check directions
			for (int next = 0; next < ROW_DIR.length; next++) {
				// make sure not out of bounds, and not going into a wall
				if (!outOfBounds(startRow + ROW_DIR[next],
						startCol + COL_DIR[next], maze)
						&& maze[startRow + ROW_DIR[next]][startCol
								+ COL_DIR[next]] != WALL) {

					changed = changeTile(maze, startRow, startCol, result);
					
					// if the next cell is an exit, record in results
					if (maze[startRow + ROW_DIR[next]][startCol
							+ COL_DIR[next]] == EXIT) {
						result[EXIT_REACHABLE] = TRUE;
					}
					// go to next cell if not a wall
					mazeHelper(startRow + ROW_DIR[next],
							startCol + COL_DIR[next], maze, changed, result);
					// return the result if can exit with all coins
					if (result[FINAL_RESULT] == 2) {
						return result;
					}
					if (result[FINAL_RESULT] == 1 && next == ROW_DIR.length - 1) {
						// if found exit and checked all directions
						return result;
					}
					if (changed == COIN) {
						result[COINS_LEFT]++;
					}
					// make current row and col as changed
					maze[startRow][startCol] = changed;
				}
			}
		}
		// return result from trying all possibilities
		result[FINAL_RESULT] = 0;
		return result;
	}

	/*
	 * Helper method that changes the cells in the maze to what ever the rules
	 * of the maze dictate.
	 * 
	 * @param maze: The maze as a 2d array of chars
	 * @param startRow: The current row we are on
	 * @param startCol: The current column we are on
	 * @param result: the results array that passses the new parameters
	 * @return The char that was changed
	 */
	private static char changeTile(char[][] maze, int startRow, int startCol,
			int[] result) {
		char changed = BASIS;

		if (maze[startRow][startCol] == START) {
			changed = START;
			// after leaving start, make green
			maze[startRow][startCol] = GREEN;

		} else if (maze[startRow][startCol] == GREEN) {
			changed = GREEN;
			// after leaving green, make yellow
			maze[startRow][startCol] = YELLOW;

		} else if (maze[startRow][startCol] == COIN) {
			changed = COIN;
			// make yellow and decrement remaining coins
			maze[startRow][startCol] = YELLOW;
			result[COINS_LEFT]--;

		} else if (maze[startRow][startCol] == YELLOW) {
			changed = YELLOW;
			// after leaving yellow, make wall
			maze[startRow][startCol] = WALL;
		}
		// return changed char
		return changed;
	}

	/*
	 * Helper method determines whether the currect row and col is surrounded by
	 * walls, meaning it is trapped
	 *
	 * @param curRow: The current row we are on
	 * @param curCol: The current column we are on
	 * @param maze: the maze represented as a 2d array of chars
	 * @return Whether the current space is surrounded by walls
	 */
	private static boolean trapped(int curRow, int curCol, char[][] maze) {
		// index this current row and column by the N, S, E, W directions
		for (int next = 0; next < ROW_DIR.length; next++) {
			// if out of bounds or the next cell is a wall, it is trapped
			if (!outOfBounds(curRow + ROW_DIR[next], curCol + COL_DIR[next],
					maze)
					&& maze[curRow + ROW_DIR[next]][curCol
							+ COL_DIR[next]] != WALL) {
				return false;
			}
		}
		// this spot is not trapped
		return true;
	}

	/*
	 * Checks if a position is outside of the maze
	 * 
	 * @param row Current row to check
	 * @param col Current column to check
	 * @param maze Current maze
	 * @return true if (row, col) position is in bounds
	 */
	private static boolean outOfBounds(int row, int col, char[][] maze) {
		if (row >= maze.length || row < 0 || col >= maze[0].length || col < 0) {
			return true;
		}
		return false;
	}

	/*
	 * Private helper method to find the starting row, starting column, whether
	 * start was found, if theres only one s, all chars are valid, and the
	 * number of money cells
	 *
	 * @param rawMaze
	 * 
	 * @return an int array containing all preconditions and bases
	 */
	private static int[] mazePrecons(char[][] rawMaze) {
		int[] precons = new int[PRECON_LENGTH];
		precons[S_FOUND] = FALSE;
		precons[ONLY_ONE_S] = TRUE;
		precons[ARE_VALID_CHARS] = TRUE;
		precons[FOUND_EXIT] = FALSE;

		for (int row = 0; row < rawMaze.length
				&& precons[ONLY_ONE_S] == TRUE; row++) {
			for (int col = 0; col < rawMaze[0].length
					&& precons[ONLY_ONE_S] == TRUE; col++) {
				if (rawMaze[row][col] == START) {
					if (precons[S_FOUND] == TRUE) {
						// found more than one start
						precons[ONLY_ONE_S] = FALSE;
					}
					precons[S_FOUND] = TRUE;
					// row and col are location of S
					precons[S_ROW] = row;
					precons[S_COL] = col;
				} else if (rawMaze[row][col] == COIN) {
					// incrememnt num money cells
					precons[NUM_COINS]++;
				} else if (rawMaze[row][col] == EXIT) {
					precons[FOUND_EXIT] = TRUE;
				} else if (!VALID_CHARS.contains("" + rawMaze[row][col])) {
					// found an invalid char
					precons[ARE_VALID_CHARS] = FALSE;
				}
			}
		}
		// 0 1 2 3 4 5 6
		// {s row, s col, s found, only one s, valid chars,
		// initial num coins, found an exit}
		return precons;
	}

	/*
	 * helper method for canEscapeMaze pre: mat != null, mat.length > 0 post:
	 * return true if mat is rectangular
	 */
	private static boolean mazeIsRectangular(char[][] maze) {
		if (maze == null || maze.length == 0) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "inbounds. The 2d array mat may not be null "
					+ "and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = maze[0].length;
		int row = 0;

		while (correct && row < maze.length) {
			correct = (maze[row] != null) && (maze[row].length == numCols);
			row++;
		}
		return correct;
	}
}
