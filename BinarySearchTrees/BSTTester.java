package BinarySearchTrees;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import Other.Stopwatch;

/*  Student information for assignment:
 *
 *  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 *  TA name: Andrew
 *  Number of slip days I am using: 1
 */

/*
 * Experiments
// minimum possible tree height, assuming N unique values are
// 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, respectively
// calculated using log(base 2, N)
//
// The T(N) for my BinarySearchTree appears to be better than
// than TreeSet, but on subsequent runs of the experiments,
// TreeSet's T(N) was better, so in general they are the same.
//
// Now counducting the experiments for 1000 integers in ascending
// order, the BinarySearchTree times are significantly worse than
// the TreeSet. I think this because the BinarySearchTree's hieght
// is equal to N - 1 leading to O(N^2) times as the add method
// must iterate through the entire unbalanced tree to add.
// Meanwhile, the TreeSet class has guaranteed O(logN) for its add.
// 
// My predictions for subsequent N number of integers:
// size: 128000
// time: 30.68
//
// size: 256000
// time: 122.70
//
// size: 512000
// time: 490.81
//
 * BinarySearchTree vs TreeSet (averages)
//	----- bstExperiments -----		----- treeSetExperiments -----
//	N: 2000							N: 1000
//	time: 0.00141741				time: 9.026099999999999E-4
//	height: 24.2					size: 1000.0
//	size: 2000.0	
//									N: 2000
//	N: 4000							time: 0.0014421710000000001
//	time: 0.001050361				size: 2100.0
//	height: 29.41999999999999	
//	size: 4200.0					N: 4000
//									time: 0.0012445971
//	N: 8000							size: 4210.0
//	time: 0.0023607461	
//	height: 33.142					N: 8000
//	size: 8420.0					time: 0.0019064797100000002
//									size: 8421.0
//	N: 16000	
//	time: 0.00412081461				N: 16000
//	height: 35.7142					time: 0.003515907971
//	size: 16842.0					size: 16842.1
//		
//	N: 32000						N: 32000
//	time: 0.013452051461			time: 0.009250130797100001
//	height: 38.07142				size: 33683.909999999996
//	size: 33684.1	
//									N: 64000
//	N: 64000						time: 0.02586710307971
//	time: 0.02203234514610000		size: 67367.791
//	height: 41.707142	
//	size: 67368.11					N: 128000
//									time: 0.07044205030797099
//	N: 128000						size: 134735.27909999999
//	time: 0.05333323451460999	
//	height: 44.37071419999999		N: 256000
//	size: 134734.811				time: 0.1629975550307971
//									size: 269466.32791
//	N: 256000	
//	time: 0.12533770345146097		N: 512000
//	height: 47.63707142				time: 0.3705616155030797
//	size: 269467.4811				size: 538916.332791
//		
//	N: 512000						N: 1024000
//	time: 0.3208286403451461		time: 0.8462073715503079
//	height: 51.663707142			size: 1077772.5332791
//	size: 538915.1481100001	
//		
//	N: 1024000	
//	time: 0.8260047740345147	
//	height: 54.3663707142	
//	size: 1077771.214811
 * 
//	----- bstAscendingExperiments -----	----- treeSetAscendingExperiments -----
//	size: 1000							size: 1000
//	time: 0.00327253					time: 9.740200000000001E-4
//	height: 999	
//										size: 2000
//	size: 2000							time: 4.825619999999999E-4
//	time: 0.00587655300000000	
//	height: 1999						size: 4000
//										time: 8.162262E-4
//	size: 4000	
//	time: 0.0254789253					size: 8000
//	height: 3999						time: 8.0420262E-4
//		
//	size: 8000							size: 16000
//	time: 0.10689082253					time: 0.0017905602619999999
//	height: 7999	
//										size: 32000
//	size: 16000							time: 0.0043530960261999995
//	time: 0.46875518225300006	
//	height: 15999						size: 64000
//										time: 0.007810869602620001
//	size: 32000	
//	time: 1.8993321082253	
//	height: 31999	
//		
//	size: 64000	
//	time: 7.668855910822529	
//	height: 63999	
 */

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 */
public class BSTTester {

	private static int testNum = 0;

	/**
	 * The main method runs the tests.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		bstTests();
//		bstExperiments();
//		treeSetExperiments();
//		bstAscendingExperiments();
//		treeSetAscendingExperiments();
	}

	private static void bstTests() {
		BinarySearchTree<String> bst = new BinarySearchTree<>();
		ArrayList<String> list = new ArrayList<>();

		// Test 1, add one on empty, checks size
		bst.add("A");
		printTest(bst.size() == 1, "add one on empty, checks size");

		// Test 2, add and no duplicates, checks size
		bst.add("S");
		bst.add("T");
		bst.add("S");
		bst.add("T");
		printTest(bst.size() == 3, "add and no duplicates, checks size");
//		bst.printTree();

		// Test 3, remove root, checks size
		printTest(bst.remove("A") && bst.size() == 2,
				"remove root, checks size");
//		bst.printTree();

		// Test 4, remove element not present, checks size
		printTest(!bst.remove("A") && bst.size() == 2,
				"remove element not present, checks size");
//		bst.printTree();

		// Test 5, isPresent on tall tree
		bst.remove("S");
		bst.remove("T");
		bst.add("A");
		bst.add("G");
		bst.add("B");
		bst.add("F");
		bst.add("C");
		bst.add("E");
		printTest(bst.isPresent("E"), "isPresent on tall tree");

		// Test 6, isPresent is not true
		printTest(!bst.isPresent("D"), "isPresent is not true");
//		bst.printTree();

		// Test 7, size
		printTest(bst.size() == 6, "size");
//		bst.printTree();

		// Test 8, size
		bst.remove("E");
		printTest(bst.size() == 5, "size");
//		bst.printTree();

		// Test 9, height on tall tree
		printTest(bst.height() == 4, "height on tall tree");
//		bst.printTree();

		// Test 10, height of root-only tree
		bst.remove("C");
		bst.remove("F");
		bst.remove("B");
		bst.remove("G");
		printTest(bst.height() == 0, "height of root-only tree");
//		bst.printTree();

		// Test 11, getAll on empty
		bst.remove("A");
		printTest(bst.getAll().equals(list), "getAll on empty");
//		bst.printTree();

		// Test 12, getAll on tall tree
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("E");
		list.add("F");
		list.add("G");

		bst.add("A");
		bst.add("G");
		bst.add("B");
		bst.add("F");
		bst.add("C");
		bst.add("E");
		printTest(bst.getAll().equals(list), "getAll on tall tree");
//		bst.printTree();

		// Test 13, max on root-only tree
		bst.remove("E");
		bst.remove("C");
		bst.remove("F");
		bst.remove("B");
		bst.remove("G");
		printTest(bst.max().equals("A"), "max on root-only tree");
//		bst.printTree();

		// Test 14, max on full tree
		bst.remove("A");
		bst.add("50");
		bst.add("70");
		bst.add("65");
		bst.add("90");
		bst.add("63");
		bst.add("67");
		bst.add("82");
		bst.add("98");
		bst.add("48");
		bst.add("30");
		bst.add("20");
		bst.add("32");
		printTest(bst.max().equals("98"), "max on full tree");
//		bst.printTree();

		// Test 15, min on full tree
		printTest(bst.min().equals("20"), "min on full tree");
//		bst.printTree();

		// Test 16, min on root-only tree, check size
		bst.remove("70");
		bst.remove("65");
		bst.remove("90");
		bst.remove("63");
		bst.remove("67");
		bst.remove("82");
		bst.remove("98");
		bst.remove("48");
		bst.remove("30");
		bst.remove("20");
		bst.remove("32");
		printTest(bst.min().equals("50"), "min on root-only tree, check size");
//		bst.printTree();

		// Test 17, iterativeAdd and no duplicates, checks getAll and size
		bst.remove("50");
		list.clear();
		list.add("40");
		list.add("50");
		list.add("60");
		bst.iterativeAdd("50");
		bst.iterativeAdd("40");
		bst.iterativeAdd("60");
		bst.iterativeAdd("50");
		bst.iterativeAdd("40");
		bst.iterativeAdd("60");

		printTest(bst.getAll().equals(list) && bst.size() == 3,
				"iterativeAdd and no duplicates, checks getAll and size");
//		bst.printTree();

		// Test 18, iterativeAdd, checks getAll and size
		bst.iterativeAdd("20");
		bst.iterativeAdd("10");
		bst.iterativeAdd("30");
		bst.iterativeAdd("80");
		bst.iterativeAdd("70");
		bst.iterativeAdd("90");
		list.clear();
		for (int i = 1; i <= 9; i++) {
			list.add("" + (10 * i));
		}
		printTest(bst.getAll().equals(list) && bst.size() == 9,
				"iterativeAdd, checks getAll and size");
//		bst.printTree();

		// Test 19 - 27, get every element
		for (int kth = 0; kth < 9; kth++) {
			printTest(bst.get(kth).equals(list.get(kth)), "get every element");
		}

		// Test 28, getAllLessThan
		list.remove(8);
		list.remove(7);
		list.remove(6);
		printTest(bst.getAllLessThan("70").equals(list), "getAllLessThan");
//		bst.printTree();

		// Test 29, getAllLessThan value is equal to min
		list.clear();
		printTest(bst.getAllLessThan("10").equals(list),
				"getAllLessThan value is equal to min");
//		bst.printTree();

		// Test 30, getAllGreaterThan
		for (int i = 4; i <= 9; i++) {
			list.add("" + 10 * i);
		}
		printTest(bst.getAllGreaterThan("30").equals(list),
				"getAllGreaterThan");
//		bst.printTree();

		// Test 31, getAllGreaterThan value is equal to max
		list.clear();
		printTest(bst.getAllGreaterThan("90").equals(list),
				"getAllGreaterThan value is equal to max");
//		bst.printTree();

		// Test 32, numNodesAtDepth
		printTest(bst.numNodesAtDepth(2) == 2, "numNodesAtDepth ");
//		bst.printTree();

		// Test 33, numNodesAtDepth past max depth of this tree
		printTest(bst.numNodesAtDepth(5) == 0,
				"numNodesAtDepth past max depth of this tree");
//		bst.printTree();

	}

	private static void printTest(boolean passed, String testDescription) {
		testNum++;
		if (passed) {
			System.out.println(
					"Passed test " + testNum + ", " + testDescription + "\n");
		} else {
			System.out.println(
					"[FAILED] test" + testNum + ", " + testDescription + "\n");
		}
	}

	private static void bstExperiments() {
		final int n = 1000;
		double time = 0;
		double height = 0;
		double size = 0;
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Stopwatch timer = new Stopwatch();
		Random r = new Random();

		System.out.println("----- bstExperiments -----");
		for (int mult = 2; mult <= Math.pow(2, 10); mult *= 2) {
			System.out.println(n * mult);
			for (int test = 0; test < 10; test++) {
				timer.start();
				for (int i = 0; i < n * mult; i++) {
					bst.add(new Integer(r.nextInt()));
				}
				timer.stop();
				time += timer.time();
				height += bst.height();
				size += bst.size();
				bst = new BinarySearchTree<>();
				timer = new Stopwatch();
			}
			time /= 10;
			height /= 10;
			size /= 10;
			System.out.println("time: " + time);
			System.out.println("height: " + height);
			System.out.println("size: " + size);
			System.out.println();
		}
	}

	private static void treeSetExperiments() {
		final int n = 1000;
		double time = 0;
		double size = 0;
		TreeSet<Integer> ts = new TreeSet<>();
		Stopwatch timer = new Stopwatch();
		Random r = new Random();

		System.out.println("----- treeSetExperiments -----");
		for (int mult = 1; mult <= Math.pow(2, 10); mult *= 2) {
			System.out.println(n * mult);
			for (int test = 0; test < 10; test++) {
				timer.start();
				for (int i = 0; i < n * mult; i++) {
					ts.add(new Integer(r.nextInt()));
				}
				timer.stop();
				time += timer.time();
				size += ts.size();
				ts = new TreeSet<>();
				timer = new Stopwatch();
			}
			time /= 10;
			size /= 10;
			System.out.println("time: " + time);
			System.out.println("size: " + size);
			System.out.println();
		}
	}

	private static void bstAscendingExperiments() {
		final int n = 1000;
		double time = 0;
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Stopwatch timer = new Stopwatch();

		System.out.println("----- bstAscendingExperiments -----");
		for (int mult = 1; mult <= Math.pow(2, 6); mult *= 2) {
			System.out.println("size: " + n * mult);
			for (int test = 0; test < 10; test++) {
				timer.start();
				for (int i = 0; i < n * mult; i++) {
					bst.iterativeAdd(new Integer(i));
				}
				timer.stop();
				time += timer.time();
				bst = new BinarySearchTree<>();
				timer = new Stopwatch();
			}
			time /= 10;
			System.out.println("time: " + time);
			System.out.println("height: " + (n * mult - 1));
			System.out.println();
		}
	}

	private static void treeSetAscendingExperiments() {
		final int n = 1000;
		double time = 0;
		TreeSet<Integer> ts = new TreeSet<>();
		Stopwatch timer = new Stopwatch();

		System.out.println("----- treeSetAscendingExperiments -----");
		for (int mult = 1; mult <= Math.pow(2, 6); mult *= 2) {
			System.out.println("size: " + n * mult);
			for (int test = 0; test < 10; test++) {
				timer.start();
				for (int i = 0; i < n * mult; i++) {
					ts.add(new Integer(i));
				}
				timer.stop();
				time += timer.time();
				ts = new TreeSet<>();
				timer = new Stopwatch();
			}
			time /= 10;
			System.out.println("time: " + time);
			System.out.println();
		}
	}

}
