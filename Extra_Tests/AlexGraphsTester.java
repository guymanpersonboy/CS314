package Extra_Tests;
/*
    This is a battery of tests written by Alex Hanlin for 
    assignment a11 - Graphs
    Comment out methods as necessary when using.
    Message me on Piazza or Discord (Gluethulhu#0117) if you find any bugs or 
    errors! If you do so, please use a test's name when calling attention to it,
    as number is only tracked at runtime.
*/

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import Graph_Algorithms.AllPathsInfo;
import Graph_Algorithms.Graph;

public class AlexGraphsTester {
	/*---------------------------- Main Methods ---------------------------*/
	public static void main(String[] args) {
		printHeader("Graph Tests", '#');
		dijkstraTests();
		findAllPathsTests();

		printHeader("FootballRanker Tests", '#');
		rootMeanSquareErrorTests();

		finalResults();
	}

	/*---------------------------- Graph Tests ----------------------------*/
	private static Graph g1; // connected graph
	private static Graph g2; // disconnected graph
	private static Graph g3; // single-vertex graph

	static {
		String[][] edges = new String[][] { { "A", "B", "2" },
				{ "B", "C", "2" }, { "C", "D", "2" }, { "D", "E", "2" },
				{ "E", "A", "2" }, { "A", "E", "9" }, { "D", "B", "1" },
				{ "E", "B", "5" } };

		g1 = getGraph(edges, true); // directed graph with given edges

		edges = new String[][] { { "A", "B", "1" }, { "B", "C", "2" },
				{ "C", "A", "3" }, { "D", "E", "1" }, { "E", "F", "1" },
				{ "D", "F", "3" }, };

		g2 = getGraph(edges, true); // directed graph with given edges

		g3 = new Graph();
		g3.addVertex("A");
	}

	private static void dijkstraTests() {
		printHeader("Dijkstra Tests", ':');

		// Tests for all of graph 1
		// weights and lengths used for vertex weights and numEdges.
		printHeader("Graph 1", '=');

		g1.dijkstra("A");
		String[] names = new String[] { "A", "B", "C", "D", "E" };
		double[] weights = new double[] { 0, 2, 4, 6, 8 };
		boolean succeeded = checkWeights(g1, names, weights);
		printTest("Dijkstra(A) path costs", succeeded);

		double[] lengths = new double[] { 0, 1, 2, 3, 4 };
		succeeded = checkLengths(g1, names, lengths);
		printTest("Dijkstra(A) path lengths", succeeded);

		g1.dijkstra("B");
		weights = new double[] { 8, 0, 2, 4, 6 };
		succeeded = checkWeights(g1, names, weights);
		printTest("Dijkstra(B) path costs", succeeded);

		lengths = new double[] { 4, 0, 1, 2, 3 };
		succeeded = checkLengths(g1, names, lengths);
		printTest("Dijkstra(B) path lengths", succeeded);

		g1.dijkstra("C");
		weights = new double[] { 6, 3, 0, 2, 4 };
		succeeded = checkWeights(g1, names, weights);
		printTest("Dijkstra(C) path costs", succeeded);

		lengths = new double[] { 3, 2, 0, 1, 2 };
		succeeded = checkLengths(g1, names, lengths);
		printTest("Dijkstra(C) path lengths", succeeded);

		g1.dijkstra("D");
		weights = new double[] { 4, 1, 3, 0, 2 };
		succeeded = checkWeights(g1, names, weights);
		printTest("Dijkstra(D) path costs", succeeded);

		lengths = new double[] { 2, 1, 2, 0, 1 };
		succeeded = checkLengths(g1, names, lengths);
		printTest("Dijkstra(D) path lengths", succeeded);

		g1.dijkstra("E");
		weights = new double[] { 2, 4, 6, 8, 0 };
		succeeded = checkWeights(g1, names, weights);
		printTest("Dijkstra(E) path costs", succeeded);

		lengths = new double[] { 1, 2, 3, 4, 0 };
		succeeded = checkLengths(g1, names, lengths);
		printTest("Dijkstra(E) path lengths", succeeded);

		// finding explicit paths in g1
		g1.dijkstra("D");
		LinkedList<String> expPath = new LinkedList<>();
		expPath.add("D");
		expPath.add("E");
		expPath.add("A");
		printTest("explicit path D -> A", g1.findPath("A").equals(expPath));

		expPath = new LinkedList<>();
		expPath.add("D");
		expPath.add("B");
		printTest("explicit path D -> B", g1.findPath("B").equals(expPath));

		expPath.add("C");
		printTest("explicit path D -> C", g1.findPath("C").equals(expPath));

		expPath = new LinkedList<>();
		expPath.add("D");
		printTest("explicit path D -> D", g1.findPath("D").equals(expPath));

		expPath = new LinkedList<>();
		expPath.add("D");
		expPath.add("E");
		printTest("explicit path D -> E", g1.findPath("E").equals(expPath));

		// tests for each vertex of graph 2 (disconnected graph)
		printHeader("Graph 2", '=');

		names = new String[] { "A", "B", "C", "D", "E", "F" };
		g2.dijkstra("A");
		weights = new double[] { 0, 1, 3, -1, -1, -1 };
		succeeded = checkWeights(g2, names, weights);
		printTest("Dijkstra(A) path costs", succeeded);

		lengths = new double[] { 0, 1, 2, -1, -1, -1 };
		succeeded = checkLengths(g2, names, lengths);
		printTest("Dijkstra(A) path lengths", succeeded);

		g2.dijkstra("B");
		weights = new double[] { 5, 0, 2, -1, -1, -1 };
		succeeded = checkWeights(g2, names, weights);
		printTest("Dijkstra(B) path costs", succeeded);

		lengths = new double[] { 2, 0, 1, -1, -1, -1 };
		succeeded = checkLengths(g2, names, lengths);
		printTest("Dijkstra(B) path lengths", succeeded);

		g2.dijkstra("C");
		weights = new double[] { 3, 4, 0, -1, -1, -1 };
		succeeded = checkWeights(g2, names, weights);
		printTest("Dijkstra(C) path costs", succeeded);

		lengths = new double[] { 1, 2, 0, -1, -1, -1 };
		succeeded = checkLengths(g2, names, lengths);
		printTest("Dijkstra(C) path lengths", succeeded);

		g2.dijkstra("D");
		weights = new double[] { -1, -1, -1, 0, 1, 2 };
		succeeded = checkWeights(g2, names, weights);
		printTest("Dijkstra(D) path costs", succeeded);

		lengths = new double[] { -1, -1, -1, 0, 1, 2 };
		succeeded = checkLengths(g2, names, lengths);
		printTest("Dijkstra(D) path lengths", succeeded);

		g2.dijkstra("E");
		weights = new double[] { -1, -1, -1, -1, 0, 1 };
		succeeded = checkWeights(g2, names, weights);
		printTest("Dijkstra(E) path costs", succeeded);

		lengths = new double[] { -1, -1, -1, -1, 0, 1 };
		succeeded = checkLengths(g2, names, lengths);
		printTest("Dijkstra(E) path lengths", succeeded);

		g2.dijkstra("F");
		weights = new double[] { -1, -1, -1, -1, -1, 0 };
		succeeded = checkWeights(g2, names, weights);
		printTest("Dijkstra(F) path costs", succeeded);

		lengths = new double[] { -1, -1, -1, -1, -1, 0 };
		succeeded = checkLengths(g2, names, lengths);
		printTest("Dijkstra(F) path lengths", succeeded);

		// finding explicit paths in g2
		g2.dijkstra("D");
		expPath = new LinkedList<>();
		printTest("explicit path D -/> A", g2.findPath("A").equals(expPath));
		printTest("explicit path D -/> B", g2.findPath("B").equals(expPath));
		printTest("explicit path D -/> C", g2.findPath("C").equals(expPath));

		expPath.add("D");
		printTest("explicit path D -> D", g2.findPath("D").equals(expPath));

		expPath.add("E");
		printTest("explicit path D -> E", g2.findPath("E").equals(expPath));

		expPath.add("F");
		printTest("explicit path D -> F", g2.findPath("F").equals(expPath));

		// test with single-vertex graph
		printHeader("Single-vertex graph", '=');

		g3.dijkstra("A");
		names = new String[] { "A" };
		weights = new double[] { 0 };
		succeeded = checkWeights(g3, names, weights);
		printTest("path cost", succeeded);

		lengths = new double[] { 0 };
		succeeded = checkLengths(g3, names, lengths);
		printTest("path length", succeeded);

		expPath = new LinkedList<>();
		expPath.add("A");
		printTest("explicit path A -> A", g3.findPath("A").equals(expPath));

	}

	// Helper for dijkstra tests. Checks all path costs
	// pre: names.length == weights.length == number of vertices in g
	private static boolean checkWeights(Graph g, String[] names,
			double[] weights) {
		for (int i = 0; i < weights.length; i++) {
			if (g.getWeightedCostFromStart(names[i]) != weights[i]) {
				return false;
			}
		}
		return true;
	}

	// Helper for dijkstra tests. Checks all path lengths
	// pre: names.length == lengths.length == number of vertices in g
	private static boolean checkLengths(Graph g, String[] names,
			double[] lengths) {
		for (int i = 0; i < lengths.length; i++) {
			if (g.getNumEdgesFromStart(names[i]) != lengths[i]) {
				return false;
			}
		}
		return true;
	}

	private static void findAllPathsTests() {
		printHeader("findAllPaths Tests", ':');

		// graph one tests
		printHeader("Graph 1", '=');

		// unweighted
		g1.findAllPaths(false);

		printTest("unweighted diameter", g1.getDiameter() == 4);
		printTest("unweighted longest shortest cost",
				g1.costOfLongestShortestPath() == 4);

		String[] expPathInfos = new String[] {
				"Name: D                    cost per path: 1.5000, num paths: 4",
				"Name: A                    cost per path: 1.7500, num paths: 4",
				"Name: E                    cost per path: 1.7500, num paths: 4",
				"Name: C                    cost per path: 2.0000, num paths: 4",
				"Name: B                    cost per path: 2.5000, num paths: 4" };
		printTest("unweighted AllPathInfos", checkPaths(g1, expPathInfos));

		g1.findAllPaths(true);

		printTest("weighted diameter", g1.getDiameter() == 4);
		printTest("weighted longest shortest cost",
				g1.costOfLongestShortestPath() == 8);

		expPathInfos = new String[] {
				"Name: D                    cost per path: 2.5000, num paths: 4",
				"Name: C                    cost per path: 3.7500, num paths: 4",
				"Name: A                    cost per path: 5.0000, num paths: 4",
				"Name: B                    cost per path: 5.0000, num paths: 4",
				"Name: E                    cost per path: 5.0000, num paths: 4" };
		printTest("weighted AllPathInfos", checkPaths(g1, expPathInfos));

		// graph two tests
		printHeader("Graph 2", '=');

		// unweighted
		g2.findAllPaths(false);

		printTest("unweighted diameter", g2.getDiameter() == 2);
		printTest("unweighted longest shortest cost",
				g2.costOfLongestShortestPath() == 2);

		expPathInfos = new String[] {
				"Name: D                    cost per path: 1.0000, num paths: 2",
				"Name: A                    cost per path: 1.5000, num paths: 2",
				"Name: B                    cost per path: 1.5000, num paths: 2",
				"Name: C                    cost per path: 1.5000, num paths: 2",
				"Name: E                    cost per path: 1.0000, num paths: 1",
				"Name: F                    cost per path: 0.0000, num paths: 0" };
		printTest("unweighted AllPathInfos", checkPaths(g2, expPathInfos));

		g2.findAllPaths(true);

		printTest("weighted diameter", g2.getDiameter() == 2);
		printTest("weighted longest shortest cost",
				g2.costOfLongestShortestPath() == 5);

		expPathInfos = new String[] {
				"Name: D                    cost per path: 1.5000, num paths: 2",
				"Name: A                    cost per path: 2.0000, num paths: 2",
				"Name: B                    cost per path: 3.5000, num paths: 2",
				"Name: C                    cost per path: 3.5000, num paths: 2",
				"Name: E                    cost per path: 1.0000, num paths: 1",
				"Name: F                    cost per path: 0.0000, num paths: 0" };
		printTest("weighted AllPathInfos", checkPaths(g2, expPathInfos));

		// graph three tests
		printHeader("Single-vertex graph", '=');

		// unweighted
		g3.findAllPaths(false);

		printTest("unweighted diameter", g3.getDiameter() == -1);
		printTest("unweighted longest shortest cost",
				g3.costOfLongestShortestPath() == 0);

		expPathInfos = new String[] {
				"Name: A                    cost per path: 0.0000, num paths: 0" };
		printTest("unweighted AllPathInfos", checkPaths(g3, expPathInfos));

		// weighted
		g3.findAllPaths(true);

		printTest("weighted diameter", g3.getDiameter() == -1);
		printTest("weighted longest shortest cost",
				g3.costOfLongestShortestPath() == 0);

		expPathInfos = new String[] {
				"Name: A  cost per path: 0.0000, num paths: 0" };
		printTest("weighted AllPathInfos", checkPaths(g3, expPathInfos));
	}

	// Helper for findAllPaths tests. checks AllPathsInfo
	// pre: pathStrings.length == g.getAllPaths.size()
	private static boolean checkPaths(Graph g, String[] pathStrings) {
		int i = 0;
		for (AllPathsInfo info : g.getAllPaths()) {
			if (!pathStrings[i].equals(info.toString())) {
				return false;
			}
			i++;
		}
		return true;
	}

	/*----------------------- Football Ranker Tests -----------------------*/

	private static void rootMeanSquareErrorTests() {
		// these are not used. They are here so that the constructor works.
//        String actual = "2008ap_poll.txt";
//        String gameResults = "div12008.txt";
//        FootballRanker ranker = new FootballRanker(gameResults, actual);
//
//        List<String> ranks = new ArrayList<>();
//        TreeSet<AllPathsInfo> paths = new TreeSet<>();
//
//        ranks.add("Knights");
//        ranks.add("Hornets");
//        ranks.add("Quirrels");
//        ranks.add("Cornifers");
//        ranks.add("Zotes");
//
//        paths.add(new AllPathsInfo("Knights", 6, 6)); // avg = 1
//        paths.add(new AllPathsInfo("Hornets", 6, 12)); //avg = 2
//        paths.add(new AllPathsInfo("Quirrels", 6, 18)); //avg = 3
//        paths.add(new AllPathsInfo("Cornifers", 6, 24)); //avg = 4
//        paths.add(new AllPathsInfo("Zotes", 6, 30)); //avg = 5
//
//        // all paths are perfect
//        paths = rankByScore(paths);
//        double error = ranker.printRootMeanSquareError(ranks, paths, false);
//        printTest("printRMSE all values perfect", error == 0.0);
//
//        // one path is missing
//        paths.remove(new AllPathsInfo("Cornifers", 6, 24));
//        error = ranker.printRootMeanSquareError(ranks, paths, false);
//        printTest("printRMSE with missing value", error == 0.6);
//
//        // all paths are wrong
//        paths = new TreeSet<>();
//        paths.add(new AllPathsInfo("Knights", 6, 12)); // avg = 2
//        paths.add(new AllPathsInfo("Hornets", 6, 30)); //avg = 5
//        paths.add(new AllPathsInfo("Quirrels", 6, 24)); //avg = 4
//        paths.add(new AllPathsInfo("Cornifers", 6, 6)); //avg = 1
//        paths.add(new AllPathsInfo("Zotes", 6, 18)); //avg = 3
//
//        paths = rankByScore(paths);
//        error = ranker.printRootMeanSquareError(ranks, paths, false);
//        printTest("printRMSE all values scrambled", error == 2.2);
	}

	/*----- Borrowed Helpers From GraphAndRankTester & FootballRanker -----*/
	/*---------------------- Credit Prof. Mike Scott ----------------------*/

	// return a Graph based on the given edges
	private static Graph getGraph(String[][] edges, boolean directed) {
		Graph result = new Graph();
		for (String[] edge : edges) {
			result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
			// If edges are for an undirected graph add edge in other direction
			// too.
			if (!directed) {
				result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
			}
		}
		return result;
	}

	private static TreeSet<AllPathsInfo> rankByScore(Set<AllPathsInfo> paths) {

		TreeSet<AllPathsInfo> result = new TreeSet<>(new AveCostComparator());
		for (AllPathsInfo teamPaths : paths) {
			boolean added = result.add(teamPaths);
		}
		return result;
	}

	private static class AveCostComparator implements Comparator<AllPathsInfo> {

		public int compare(AllPathsInfo a, AllPathsInfo b) {
			int result = (a.getAveCost() < b.getAveCost()) ? -1
					: (a.getAveCost() == b.getAveCost()) ? 0 : 1;
			if (result == 0) {
				result = a.getName().compareTo(b.getName());
			}
			return result;
		}
	}

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
		// titleSB.append("\n");
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
