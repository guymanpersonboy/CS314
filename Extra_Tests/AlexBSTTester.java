package Extra_Tests;
/*
    This is a battery of tests written by Alex Hanlin for 
    assignment a9 - Binary Search Trees
    Comment out methods as necessary when using.
    Message me on Piazza or Discord (Gluethulhu#0117) if you find any bugs or 
    errors! If you do so, please use a test's name when calling attention to it,
    as number is only tracked at runtime.
*/

import java.util.ArrayList;

import BinarySearchTrees.BinarySearchTree;

public class AlexBSTTester {
	/*---------------------------- Main Methods ---------------------------*/

	public static void main(String[] args) {
		addTests();
		isPresentTests();
		sizeTests();
		removeTests();
		heightTests();
		getAllTests();
		maxTests();
		minTests();
		iterativeAddTests();
		getTests();
		getAllTests();
		getAllLessThanTests();
		getAllGreaterThanTests();
		numNodesAtDepthTests();

		finalResults();
	}

	// I have left in a stub for the timing tests because of time constraints.
	// Feel free to create your own to test your T(N). If you do, it is
	// recommended to run them on the CS Linux machines for accuracy
	// private static void timingTests() {
	// printHeader("timing tests", '#');
	// stubTimingTests();
	// }

	/*----------------------------- BST Tests -----------------------------*/
	private static BinarySearchTree<Integer> tree;

	// Add tests rely on size method to function
	private static void addTests() {
		printHeader("add Tests", '=');
		tree = new BinarySearchTree<>();

		// add to empty tree
		printTest("add to empty tree", tree.add(20)); // root

		// add with lower value
		printTest("add lower than root", tree.add(0)); // 20.left

		// add with higher value
		printTest("add higher than root", tree.add(40)); // 20.right

		// add between lower and root
		printTest("add between low and root", tree.add(10)); // 0.right

		// add between higher and root
		printTest("add between high and root", tree.add(30)); // 40.left

		// size is correct after adding
		printTest("size correct after add", tree.size() == 5);

		// add when already present at leaf
		printTest("add when present in leaf",
				!tree.add(10) && tree.size() == 5);

		// add when already present at root
		printTest("add when present in root",
				!tree.add(20) && tree.size() == 5);

		// add precondition
		boolean passedTest = false;
		try {
			tree.add(null);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("add precondition", passedTest);
	}

	private static void isPresentTests() {
		printHeader("isPresent Tests", '=');
		tree = new BinarySearchTree<>();
		tree.add(20); // root
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right

		// isPresent at root
		printTest("isPresent at root", tree.isPresent(20));

		// isPresent at internal
		printTest("isPresent at internal",
				tree.isPresent(0) && tree.isPresent(40));

		// isPresent at leaves
		printTest("isPresent at leaves",
				tree.isPresent(-10) && tree.isPresent(10) && tree.isPresent(30)
						&& tree.isPresent(50));

		// isPresent false too low
		printTest("isPresent false low", !tree.isPresent(-100));

		// isPresent false too high
		printTest("isPresent false high", !tree.isPresent(100));

		// isPresent false within bounds of tree
		printTest("isPresent false mid", !tree.isPresent(15));

		// isPresent precondition
		boolean passedTest = false;
		try {
			tree.isPresent(null);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("isPresent precondition", passedTest);
	}

	// size is tested implicitly in many other methods. This method is
	// basically a formality
	private static void sizeTests() {
		printHeader("size Tests", '=');
		tree = new BinarySearchTree<>();

		// size of empty tree
		printTest("size of empty", tree.size() == 0);

		// size after add
		tree.add(20); // root
		printTest("size after add", tree.size() == 1);

	}

	// remove tests use add, size, and isPresent
	private static void removeTests() {
		printHeader("remove Tests", '=');
		tree = new BinarySearchTree<>();
		tree.add(20); // root
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(35); // 40.left.right
		tree.add(50); // 40.right
		tree.add(45); // 40.right.left

		// removal of leaf
		printTest("remove at leaf", tree.remove(-10) && !tree.isPresent(-10));

		// removal of internal node
		printTest("remove at internal", tree.remove(40) && !tree.isPresent(40));

		// tree still contains children of removed
		printTest("children preserved",
				tree.isPresent(30) && tree.isPresent(50));

		// removal of root
		printTest("remove at root", tree.remove(20));

		// tree still contains remaining elements
		printTest("elements preserved",
				tree.isPresent(0) && tree.isPresent(10) && tree.isPresent(30)
						&& tree.isPresent(35) && tree.isPresent(45)
						&& tree.isPresent(50));

		// remove precondition
		boolean passedTest = false;
		try {
			tree.remove(null);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("remove precondition", passedTest);
	}

	private static void heightTests() {
		printHeader("height Tests", '=');
		tree = new BinarySearchTree<>();

		// height of empty tree
		printTest("height of empty tree", tree.height() == -1);

		// height of root-only tree
		tree.add(20); // root
		printTest("height of one-elem tree", tree.height() == 0);

		// height with one child
		tree.add(0); // 20.left
		printTest("height of two-elem tree", tree.height() == 1);

		// height with two children at same level
		tree.add(40); // 20.right
		printTest("height with same-level add", tree.height() == 1);

		// height of complete tree
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right
		printTest("height of complete tree", tree.height() == 2);

		// height stays same with failed add
		tree.add(30);
		printTest("height stays same on failed add", tree.height() == 2);

		// height of unbalanced tree
		tree.add(60); // 50.right
		tree.add(70); // 60.right
		tree.add(80); // 70.right
		tree.add(90); // 80.right
		tree.add(100); // 90.right
		tree.add(110); // 100.right
		tree.add(120); // 110.right
		printTest("height of unbalanced tree", tree.height() == 9);

		// height after removal
		tree.remove(120);
		printTest("height after leaf removed", tree.height() == 8);

		// height after removal
		tree.remove(70);
		printTest("height after internal removed", tree.height() == 7);
	}

	private static void getAllTests() {
		printHeader("getAll Tests", '=');
		tree = new BinarySearchTree<>();
		ArrayList<Integer> expList = new ArrayList<>();

		// getAll empty tree
		printTest("getAll with empty tree", tree.getAll().equals(expList));

		// getAll one-element tree
		tree.add(20); // root
		expList.add(20);
		printTest("getAll one-elem tree", tree.getAll().equals(expList));

		// getAll tree with one child
		tree.add(0); // 20.left
		expList.add(0, 0);
		printTest("getAll two-elem tree", tree.getAll().equals(expList));

		// getAll complete tree
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right
		expList.add(0, -10);
		expList.add(2, 10);
		expList.add(30);
		expList.add(40);
		expList.add(50);
		printTest("getAll complete tree", tree.getAll().equals(expList));

		// getAll after root removal
		tree.remove(20);
		expList.remove(new Integer(20)); // removes value 20, not index 20.
		printTest("getAll after root removed", tree.getAll().equals(expList));
	}

	private static void maxTests() {
		printHeader("max Tests", '=');
		tree = new BinarySearchTree<>();

		// max with one element
		tree.add(20); // root
		printTest("max with one elem", tree.max() == 20);

		// max with complete tree
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		printTest("max with complete tree", tree.max() == 40);

		// max with tall tree. Zig zags, makes sure max is conceptually right
		tree.add(100); // 40.right
		tree.add(50); // 100.left
		tree.add(60); // 50.right
		tree.add(70); // 60.right
		tree.add(90); // 70.right
		tree.add(80); // 90.left
		printTest("max with tall tree", tree.max() == 100);

		// max after remove
		tree.remove(100);
		printTest("max after remove", tree.max() == 90);

		// max precondition
		tree = new BinarySearchTree<>();
		boolean passedTest = false;
		try {
			tree.max();
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("max precondition", passedTest);
	}

	private static void minTests() {
		printHeader("min Tests", '=');
		tree = new BinarySearchTree<>();

		// min with one element
		tree.add(20); // root
		printTest("min with one elem", tree.min() == 20);

		// min with complete tree
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		printTest("min with complete tree", tree.min() == 0);

		// min with tall tree. Zig zags, makes sure min is conceptually right
		tree.add(-100); // 0.left
		tree.add(-50); // -100.right
		tree.add(-60); // -50.left
		tree.add(-70); // -60.left
		tree.add(-90); // -70.left
		tree.add(-80); // -90.right
		printTest("min with tall tree", tree.min() == -100);

		// min after remove
		tree.remove(-100);
		printTest("min after remove", tree.min() == -90);

		// min precondition
		tree = new BinarySearchTree<>();
		boolean passedTest = false;
		try {
			tree.min();
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("min precondition", passedTest);
	}

	private static void iterativeAddTests() {
		printHeader("iterativeAdd Tests", '=');
		tree = new BinarySearchTree<>();

		// iterativeAdd to empty tree
		printTest("iterativeAdd to empty tree", tree.iterativeAdd(20)); // root

		// iterativeAdd with lower value
		printTest("iterativeAdd lower than root", tree.iterativeAdd(0)); // 20.left

		// iterativeAdd with higher value
		printTest("iterativeAdd higher than root", tree.iterativeAdd(40)); // 20.right

		// iterativeAdd between lower and root
		printTest("iterativeAdd between low and root", tree.iterativeAdd(10)); // 0.right

		// iterativeAdd between higher and root
		printTest("iterativeAdd between high and root", tree.iterativeAdd(30)); // 40.left

		// size is correct after iteratively adding
		printTest("size correct after iterativeAdd", tree.size() == 5);

		// iterativeAdd when already present at leaf
		printTest("iterativeAdd when present in leaf",
				!tree.iterativeAdd(10) && tree.size() == 5);

		// iterativeAdd when already present at root
		printTest("iterativeAdd when present in root",
				!tree.iterativeAdd(20) && tree.size() == 5);

		// iterativeAdd precondition
		boolean passedTest = false;
		try {
			tree.iterativeAdd(null);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("iterativeAdd precondition", passedTest);
	}

	private static void getTests() {
		printHeader("get Tests", '=');
		tree = new BinarySearchTree<>();

		// get on one-element tree
		tree.add(20); // root
		printTest("get with one-elem tree", tree.get(0) == 20);

		// get minimum of complete tree
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right
		printTest("get minimum of complete tree", tree.get(0) == -10);

		// get maximum of complete tree
		printTest("get maximum of complete tree", tree.get(6) == 50);

		// get inner leaves
		printTest("get inner leaves of complete tree",
				tree.get(2) == 10 && tree.get(4) == 30);

		// get internal nodes
		printTest("get internal nodes of complete tree",
				tree.get(1) == 0 && tree.get(5) == 40);

		// get maximum of tall tree
		tree.add(100); // 50.right
		tree.add(60); // 100.left
		tree.add(70); // 60.right
		tree.add(90); // 70.right
		tree.add(80); // 90.left
		printTest("get maximum of tall tree", tree.get(11) == 100);

		// get internal nodes for tall tree
		printTest("get internal nodes of tall tree", tree.get(10) == 90
				&& tree.get(9) == 80 && tree.get(8) == 70 && tree.get(7) == 60);

		// get preconditions
		boolean passedTest = false;
		try {
			tree.get(-1);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("get lower precondition", passedTest);

		passedTest = false;
		try {
			tree.get(tree.size());
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("get upper precondition", passedTest);
	}

	private static void getAllLessThanTests() {
		printHeader("getAllLessThan Tests", '=');
		tree = new BinarySearchTree<>();
		ArrayList<Integer> expList = new ArrayList<>();

		// getAllLessThan on empty list
		printTest("getAllLessThan in empty tree",
				tree.getAllLessThan(Integer.MAX_VALUE).equals(expList));

		// getAllLessThan strictly less than parameter
		tree.add(20); // root
		printTest("getAllLessThan strictly less than",
				tree.getAllLessThan(20).equals(expList));

		// getAllLessThan single element
		expList.add(20);
		printTest("getAllLessThan one-elem tree",
				tree.getAllLessThan(21).equals(expList));

		// getAllLessThan complete tree
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right
		expList.clear();
		expList.add(-10);
		expList.add(0);
		expList.add(10);
		expList.add(20);
		expList.add(30);
		printTest("getAllLessThan complete tree",
				tree.getAllLessThan(31).equals(expList));

		// getAllLessThan with tall tree
		tree.add(100); // 50.right
		tree.add(60); // 100.left
		tree.add(70); // 60.right
		tree.add(90); // 70.right
		tree.add(80); // 90.left
		expList.add(40);
		expList.add(50);
		expList.add(60);
		expList.add(70);
		printTest("getAllLessThan tall tree",
				tree.getAllLessThan(71).equals(expList));

		// getAllLessThan precondition
		boolean passedTest = false;
		try {
			tree.getAllLessThan(null);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("getAllLessThan precondition", passedTest);
	}

	private static void getAllGreaterThanTests() {
		printHeader("getAllGreaterThan Tests", '=');
		tree = new BinarySearchTree<>();
		ArrayList<Integer> expList = new ArrayList<>();

		// getAllGreaterThan on empty list
		printTest("getAllGreaterThan in empty tree",
				tree.getAllGreaterThan(Integer.MIN_VALUE).equals(expList));

		// getAllGreaterThan strictly less than parameter
		tree.add(20); // root
		printTest("getAllGreaterThan strictly greater than",
				tree.getAllGreaterThan(20).equals(expList));

		// getAllGreaterThan single element
		expList.add(20);
		printTest("getAllGreaterThan one-elem tree",
				tree.getAllGreaterThan(19).equals(expList));

		// getAllGreaterThan complete tree
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right
		expList.clear();
		expList.add(10);
		expList.add(20);
		expList.add(30);
		expList.add(40);
		expList.add(50);
		printTest("getAllGreaterThan complete tree",
				tree.getAllGreaterThan(9).equals(expList));

		// getAllGreaterThan with tall tree
		tree.add(-100); // -10.left
		tree.add(-60); // -100.right
		tree.add(-70); // -60.left
		tree.add(-90); // -70.left
		tree.add(-80); // -90.right
		expList.add(0, 0);
		expList.add(0, -10);
		expList.add(0, -60);
		expList.add(0, -70);

		printTest("getAllGreaterThan tall tree",
				tree.getAllGreaterThan(-71).equals(expList));

		// getAllGreaterThan precondition
		boolean passedTest = false;
		try {
			tree.getAllGreaterThan(null);
		} catch (Exception e) {
			passedTest = true;
		}
		printTest("getAllGreaterThan precondition", passedTest);
	}

	private static void numNodesAtDepthTests() {
		printHeader("numNodesAtDepth Tests", '=');
		tree = new BinarySearchTree<>();

		// nodesAtDepth empty tree
		printTest("nodesAtDepth empty tree", tree.numNodesAtDepth(0) == 0);

		// nodesAtDepth single node
		tree.add(20);
		printTest("nodesAtDepth one-elem tree", tree.numNodesAtDepth(0) == 1);

		// nodesAtDepth tall tree
		tree.add(0); // 20.left
		tree.add(40); // 20.right
		tree.add(-10); // 0.left
		tree.add(10); // 0.right
		tree.add(30); // 40.left
		tree.add(50); // 40.right
		tree.add(100); // 50.right
		tree.add(60); // 100.left
		tree.add(70); // 60.right
		tree.add(90); // 70.right
		tree.add(80); // 90.left, should be at depth 7
		tree.add(95); // 90.right, should be at depth 7
		tree.add(-100); // -10.left
		tree.add(-60); // -100.right
		tree.add(-70); // -60.left
		tree.add(-90); // -70.left
		tree.add(-80); // -90.right should be at depth 7
		tree.add(-95); // -90.left should be at depth 7

		printTest("nodesAtDepth complete tree", tree.numNodesAtDepth(7) == 4);
	}

	/*---------------------------- Timing Tests ---------------------------*/

//    private static Stopwatch sw = new Stopwatch();
//
//    /* 
//    tests used to check time complexity of methods. TIME COMPLEXITY OF
//    THESE TEST METHODS THEMSELVES MUST BE TAKEN INTO ACCOUNT.
//    This tests should be O(N), so if the time for increases at the rate 
//    of O(N^2),your method itself is likely O(N).
//     
//    Number of tests for each method is arbitrary. Feel free to change them
//    if they take too long. It is recommended to double N each time.
//    */
//    private static void stubTimingTests() {
//        printHeader("stub timing tests", ':');
//        timingTestStub(5_000);
//        timingTestStub(10_000);
//        timingTestStub(20_000);
//        timingTestStub(40_000);
//    }
//
//    private static void timingTestStub(int numTests) {
//        // add any necessary variables or structures here
//        
//        double totalSeconds = 0;
//        for (int test = 0; test < numTests; test++) {
//            // add necessary variables here
//            sw.start();
//            // add method to test
//            sw.stop();
//            totalSeconds += sw.time();
//        }
//        printTimingTest(totalSeconds, numTests);
//    }

	/*----------------------- General Helper Methods ----------------------*/
	private static int numTests = 0; // number of total tests
	private static int numPassed = 0; // number of passed tests

	// increments numTests and prints results of a test
	private static void printTest(String tested, boolean condition) {
		numTests++;
		String status;
		if (condition) {
			status = ("[Passed]");
			numPassed++;
		} else {
			status = ("[!!!!FAILED!!!!]");
		}
		// prints test status, test number, and test description
		System.out.println(
				String.format("%s Test %02d: %s", status, numTests, tested));
	}

	// prints results of a timed test.
	private static void printTimingTest(double time, int tests) {
		// Allows for padding between time data and number of tests
		String toPrint = "Total time in seconds: %-12ftimes called: %d";
		System.out.println(String.format(toPrint, time, tests));
	}

	// prints a header by repeating symb on either side of title.
	private static void printHeader(String title, char symb) {
		// determines how many symbols to print in the header
		// use even number. Odds round down because of integer division
		final int HEADER_WIDTH = 60;
		int sideWidth = (HEADER_WIDTH - title.length()) / 2;
		StringBuilder titleSB = new StringBuilder();
		titleSB.append("\n");
		for (int i = 0; i < sideWidth; i++) {
			titleSB.append(symb);
		}
		titleSB.append(" ").append(title).append(" ");
		// modulo adjusts for odd title lengths
		for (int j = 0; j < sideWidth + title.length() % 2; j++) {
			titleSB.append(symb);
		}
		titleSB.append("\n");
		System.out.println(titleSB.toString());
	}

	// Prints number of successful tests followed by a nice inspirational
	// quote of a quote. If all tests are successful, prints a congratulation
	// message next to a little Gluethulhu emote
	private static void finalResults() {

		printHeader("FINAL RESULTS", '~');
		System.out.println("Successful tests: " + numPassed + " / " + numTests);
		if (numPassed == numTests) {
			System.out.println();
			System.out.println("^(;,;)^ Congratulations! You passed all the "
					+ "tests you ran!");

		}
		System.out.println(getRandomQuote());
	}

	// This one's just for fun. Returns a random iconic Mike Scott quote.
	private static String getRandomQuote() {
		final int NUM_QUOTES = 10;
		String[] quotes = new String[NUM_QUOTES];
		// Adjust as needed with more quotes
		quotes[0] = "\nProgram testing can be used to show the presence "
				+ "\nof bugs, but never to show their absence! "
				+ "\n    - Edsger Dijkstra" + "\n         - Mike Scott";

		quotes[1] = "\nTradeoffs, tradeoffs, tradeoffs!" + "\n    - Mike Scott";

		quotes[2] = "\nBut it's easy to code! It's just a few lines... "
				+ "\n    - Mike Scott";

		quotes[3] = "\nTrees? What are \"trees\"? Do you mean those "
				+ "\ngreen things outside, with the leaves on them?"
				+ "\n    - Mike Scott";

		quotes[4] = "\nUno Reverse card! What DOES that mean?"
				+ "\n    - Mike Scott";

		quotes[5] = "\nI don't know who said that college would be FUN. "
				+ "\nMaybe those business and liberal arts majors..."
				+ "\n    - Mike Scott";

		quotes[6] = "\nBase case! SOLVED! Pop the champagne corks! "
				+ "\nPop pop pop pop pop pop!" + "\n    - Mike Scott";

		quotes[7] = "\nGack!" + "\n    - Mike Scott";

		quotes[8] = "\nWe work on the FUN side of the wall of abstraction"
				+ "\n    - Mike Scott";

		quotes[9] = "\nI think of iterators as these robot spiders..."
				+ "\n    - Mike Scott";

		int chosen = (int) (Math.random() * NUM_QUOTES);
		return quotes[chosen];
	}
}
