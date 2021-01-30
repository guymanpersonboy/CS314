package Graph_Algorithms;
/*  Student information for assignment:
 *
 *  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: cc66496
 *  email address: chris.carrasco@utexas.edu
 *  TA name: Andrew
 */

/*
 * Questions.
 *
 * 1. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur. Explain the unexpected results. You may
 * have to do some research on the various college football divisions to
 * make an informed answer.
 * 
 *    The reason is that football divisions split teams based on certain
 * qualifications to prevent unfair (and possibly boring) matches
 * throughout the season. If all results from all college football teams
 * were included, the statistics of a great team in one division could
 * interfere with the rankings of teams from other divisions which
 * usually follow different trends throughout the season.
 *
 * 2. Suggest another way of method of ranking teams using the results
 * from the graph. Thoroughly explain your method. The method can build
 * on one of the three existing algorithms.
 * 
 *    An interesting way to to rank teams could be to use the result from
 * method 3 and then divide by the number of matches played at home
 * to factor in home field advantage. The more a team one without playing
 * at home, the better they are. This would involve storing the number of
 * matches played at home.
 *
 */

public class GraphAndRankTester {

	private static Graph G;

	static {
		String[][] edges = new String[][] { { "A", "B", "9" },
				{ "A", "E", "2" }, { "A", "D", "1" }, { "D", "E", "4" },
				{ "E", "B", "5" }, { "B", "C", "1" }, { "C", "E", "2" } };

		G = getGraph(edges, true);
	}

	/**
	 * Runs tests on Graph classes and FootballRanker class. Experiments involve
	 * results from college football teams. Central nodes in the graph are
	 * compared to human rankings of the teams.
	 * 
	 * @param args None expected.
	 */
	public static void main(String[] args) {
		dijkstraTests();
		findAllPathsTests();

		String actual = "2014ap_poll.txt";
		String gameResults = "div12014.txt";

		FootballRanker ranker = new FootballRanker(gameResults, actual);

		ranker.doUnweighted(true);
		ranker.doWeighted(true);
		ranker.doWeightedAndWinPercentAdjusted(true);
		System.out.println();

		rootMeanSquareErrorTests(ranker);

	}

	private static void dijkstraTests() {
		System.out.println("TESTING DIJKSTRA\n");
		G.dijkstra("A");
		String actualPath = G.findPath("C").toString();
		String expected = "[A, E, B, C]";
		if (actualPath.equals(expected)) {
			System.out.println("Passed dijkstra path test 1.");
		} else {
			System.out.println("Failed dijkstra path test 1. Expected: "
					+ expected + " actual " + actualPath);
		}

		G.dijkstra("C");
		actualPath = G.findPath("D").toString();
		expected = "[]";
		if (actualPath.equals(expected)) {
			System.out.println("Passed dijkstra path test 2.");
		} else {
			System.out.println("Failed dijkstra path test 2. Expected: "
					+ expected + " actual " + actualPath);
		}
	}

	private static void findAllPathsTests() {
		// now do all paths unweighted
		String[] expectedPaths = {
				"Name: A                    cost per path: 1.2500, num paths: 4",
				"Name: D                    cost per path: 2.0000, num paths: 3",
				"Name: B                    cost per path: 1.5000, num paths: 2",
				"Name: C                    cost per path: 1.5000, num paths: 2",
				"Name: E                    cost per path: 1.5000, num paths: 2", };
		doAllPathsTests("Graph 1", G, false, 3, 3.0, expectedPaths);

		// now do all paths weighted
		expectedPaths = new String[] {
				"Name: A                    cost per path: 4.5000, num paths: 4",
				"Name: D                    cost per path: 7.6667, num paths: 3",
				"Name: B                    cost per path: 2.0000, num paths: 2",
				"Name: C                    cost per path: 4.5000, num paths: 2",
				"Name: E                    cost per path: 5.5000, num paths: 2", };
		doAllPathsTests("Graph 1", G, true, 3, 3.0, expectedPaths);
	}

	private static void rootMeanSquareErrorTests(FootballRanker ranker) {
		System.out.println(
				"\nTESTS ON FOOTBALL TEAM GRAPH WITH FootBallRanker CLASS: \n");
		double actualError = ranker.doUnweighted(false);
		if (actualError == 10.1) {
			System.out.println("Passed unweighted test");
		} else {
			System.out.println(
					"FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 10.1, actual: "
							+ actualError);
		}

		actualError = ranker.doWeighted(false);
		if (actualError == 8.5) {
			System.out.println("Passed weigthed test");
		} else {
			System.out.println(
					"FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 8.5, actual: "
							+ actualError);
		}

		actualError = ranker.doWeightedAndWinPercentAdjusted(false);
		if (actualError == 4.2) {
			System.out.println("Passed unweighted win percent test");
		} else {
			System.out.println(
					"FAILED WEIGHTED AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST. Expected 4.2, actual: "
							+ actualError);
		}
	}

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

	// Tests the all paths method. Run each set of tests twice to ensure the
	// Graph
	// is correctly reseting each time
	private static void doAllPathsTests(String graphNumber, Graph g,
			boolean weighted, int expectedDiameter,
			double expectedCostOfLongestShortestPath, String[] expectedPaths) {

		System.out.println("\nTESTING ALL PATHS INFO ON " + graphNumber + ". ");
		for (int i = 0; i < 2; i++) {
			System.out.println("Test run = " + (i + 1));
			System.out.println("Find all paths weighted = " + weighted);
			g.findAllPaths(weighted);
			int actualDiameter = g.getDiameter();
			double actualCostOfLongestShortesPath = g
					.costOfLongestShortestPath();
			if (actualDiameter == expectedDiameter) {
				System.out.println("Passed diameter test.");
			} else {
				System.out.println("FAILED diameter test. " + "Expected = "
						+ expectedDiameter + " Actual = " + actualDiameter);
			}
			if (actualCostOfLongestShortesPath == expectedCostOfLongestShortestPath) {
				System.out
						.println("Passed cost of longest shortest path. test.");
			} else {
				System.out.println("FAILED cost of longest shortest path. "
						+ "Expected = " + expectedCostOfLongestShortestPath
						+ " Actual = " + actualCostOfLongestShortesPath);
			}
			testAllPathsInfo(g, expectedPaths);
			System.out.println();
		}

	}

	// Compare results of all paths info on Graph to expected results.
	private static void testAllPathsInfo(Graph g, String[] expectedPaths) {
		int index = 0;

		for (AllPathsInfo api : g.getAllPaths()) {
			if (expectedPaths[index].equals(api.toString())) {
				System.out.println(expectedPaths[index] + " is correct!!");
			} else {
				System.out.println("ERROR IN ALL PATHS INFO: ");
				System.out.println("index: " + index);
				System.out.println("EXPECTED: " + expectedPaths[index]);
				System.out.println("ACTUAL: " + api.toString());
				System.out.println();
			}
			index++;
		}
		System.out.println();
	}

}
