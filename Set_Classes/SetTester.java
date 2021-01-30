package Set_Classes;
/*  Student information for assignment:
 *
 *  On OUR honor, EMMANUEL IHIM and CHRISTOPHER CARRASCO,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: eui59
 *  email address: ihim_emmanuel@utexas.edu
 *  TA name: Andrew Smith
 *
 *  Student 2: Christopher Carrasco
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 */

/*
File Name			File1	File2	File3	File4					File1	File2	File3	File4
Size				8		156		851		2200					1		19.5	5.5		2.6
Number of words		1240	25050	142639	352990					1		20.2	5.7		2.5
Distinct words		582		4911	23326	42405					1		8.4		4.7		1.8
On Emmanuel's v												
Time (Sorted Set)	0.00946393	0.057932176	0.243533813	0.547873028	1		6.1		4.2		2.2
Time (Unsorted Set)	0.011007577	0.211114914	3.709476717	17.51516704	1		19.2	17.6	4.7
Time (HashSet)		0.002686834	0.033046576	0.104688045	0.23067948	1		12.3	3.2		2.2
Time (TreeSet)		0.003776471	0.027635898	0.119930582	0.271749549	1		7.3		4.3		2.3
On Chris's v												
Time (Sorted Set)	0.0176402	0.1289838	0.2459075	0.5195546	1		7.3		1.9		2.1
Time (Unsorted Set)	0.0091987	0.1713547	3.9766796	15.3073322	1		18.6	23.2	3.849
Time (HashSet)		0.0021223	0.0228855	0.1355262	0.272687	1		10.8	5.9		2	
Time (TreeSet)		0.0049545	0.0350227	0.1338442	0.3851142	1		7		3.82	2.877

Class			Output Look							add Big O	processText Big O
SortedSet:		Sorted lexographically				O(N)		O(N)
Unsorted Set:	Unsorted in order of appearance		O(N)		O(N^2)
HashSet:		Unsorted random						O(N)		O(N)
TreeSet:		Sorted lexographically				O(N)		O(N)
 *
 * Why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * Because the AbstractSet class does not have an internal storage container,
 * intersection, union, and difference will need to call each other
 * to perform the set operation, however, if one implements all three,
 * there will be an infinite loop as the three methods call each other
 * endlessly trying to complete their respectinve operations.
 */

import java.util.Iterator;

public class SetTester {

	private static int testNum = 0;

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {

		// Test 1, UnsortedSet constructor
		testNum++;
		UnsortedSet<String> unsorted = new UnsortedSet<>();
		String expected = "()";
		String actual = unsorted.toString();
		printTest(unsorted.size() == 0 && expected.equals(actual), "UnsortedSet constructor");

		// Test 2, SortedSet contructor empty Set
		testNum++;
		SortedSet<String> sorted = new SortedSet<>();
		expected = "()";
		actual = sorted.toString();
		printTest(sorted.size() == 0 && expected.equals(actual), "SortedSet contructor empty Set");

		// Test 3, UnsortedSet add
		testNum++;
		unsorted.add("c");
		unsorted.add("b");
		unsorted.add("a");
		expected = "(c, b, a)";
		actual = unsorted.toString();
		printTest(unsorted.size() == 3 && expected.equals(actual), "UnsortedSet add");

		// Test 4, SortedSet add
		testNum++;
		sorted.add("c");
		sorted.add("b");
		sorted.add("a");
		expected = "(a, b, c)";
		actual = sorted.toString();
		printTest(sorted.size() == 3 && expected.equals(actual), "SortedSet add");

		// Test 5, SortedSet min
		testNum++;
		expected = "a";
		actual = sorted.min();
		printTest(expected.equals(actual), "SortedSet min");

		// Test 6, SortedSet max
		testNum++;
		expected = "c";
		actual = sorted.max();
		printTest(expected.equals(actual), "SortedSet max");

		// Test 7, SortedSet constructor parameterized
		testNum++;
		SortedSet<String> sorted2 = new SortedSet<>(sorted);
		sorted2.add("z");
		expected = "(a, b, c, z)";
		actual = sorted2.toString();
		printTest(sorted2.size() == 4 && expected.equals(actual), "SortedSet constructor parameterized");

		// Test 8, UnsortedSet addAll, extends AbstractSet
		testNum++;
		unsorted.addAll(sorted2);
		expected = "(c, b, a, z)";
		actual = unsorted.toString();
		printTest(unsorted.size() == 4 && expected.equals(actual), "UnsortedSet addAll, extends AbstractSet");

		// Test 9, SortedSet addAll
		testNum++;
		sorted.addAll(unsorted);
		expected = "(a, b, c, z)";
		actual = sorted.toString();
		printTest(sorted.size() == 4 && expected.equals(actual), "SortedSet addAll");

		// Test 10, UnsortedSet clear
		testNum++;
		unsorted.clear();
		expected = "()";
		actual = unsorted.toString();
		printTest(unsorted.size() == 0 && expected.equals(actual), "UnsortedSet clear");

		// Test 11, SortedSet clear
		testNum++;
		sorted.clear();
		actual = sorted.toString();
		printTest(sorted.size() == 0 && expected.equals(actual), "SortedSet clear");

		// Test 12, UnsortedSet contains, extends AbstractSet
		testNum++;
		unsorted.addAll(sorted2);
		printTest(unsorted.contains("z") && !unsorted.contains("(a, b, c, z)"),
				"UnsortedSet contains, extends AbstractSet");

		// Test 13, SortedSet contains
		testNum++;
		sorted.addAll(sorted2);
		printTest(sorted.contains("z") && !sorted.contains("(a, b, c, z)"), "SortedSet contains");

		// Test 14, UnsortedSet containsAll, extends AbstractSet
		testNum++;
		unsorted.add("d");
		sorted2.add("d");
		boolean bool1 = unsorted.containsAll(sorted2);
		sorted2.add("y");
		boolean bool2 = unsorted.containsAll(sorted2);
		printTest(bool1 && !bool2, "UnsortedSet containsAll, extends AbstractSet");

		// Test 15, SortedSet containsAll
		testNum++;
		sorted.add("d");
		sorted.add("y");
		bool1 = sorted.containsAll(sorted2);
		sorted2.add("x");
		bool2 = sorted.containsAll(sorted2);
		printTest(bool1 && !bool2, "SortedSet containsAll");

		// current unsorted is "a b c z d"
		// current sorted is "a b c d y z"
		// current sorted2 is "a b c d x y z"

		// Test 16, UnsortedSet difference
		testNum++;
		UnsortedSet<String> unsorted2 = new UnsortedSet<>();
		unsorted2.add("b");
		unsorted2.add("d");
		ISet<String> unsorted3 = unsorted.difference(unsorted2);
		expected = "(a, c, z)";
		actual = unsorted3.toString();
		printTest(expected.equals(actual), "UnsortedSet difference");

		// Test 17, SortedSet difference
		testNum++;
		ISet<String> sorted3 = sorted2.difference(sorted);
		expected = "(x)";
		actual = sorted3.toString();
		printTest(expected.equals(actual), "SortedSet difference");

		// Test 18, UnsortedSet equals, extends AbstractSet
		testNum++;
		unsorted.add("y");
		bool1 = unsorted.equals(sorted);
		unsorted.add("x");
		bool2 = unsorted.equals(sorted2);
		printTest(
				bool1 && bool2 && !unsorted.equals(unsorted3),
				"UnsortedSet equals, extends AbstractSet");

		// Test 19, SortedSet equals
		testNum++;
		printTest(sorted2.equals(unsorted) && !sorted.equals(sorted2)
				&& !sorted3.equals(unsorted2), "SortedSet equals");

		// Test 20, SortedSet and UnsortedSet toString, extend AbstractSet
		testNum++;
		expected = "(a, b, c, z, d, y, x)";
		actual = unsorted.toString();
		bool1 = expected.equals(actual);
		expected = "(a, b, c, d, x, y, z)";
		actual = sorted2.toString();
		bool2 = expected.equals(actual);
		printTest(bool1 && bool2, "SortedSet and UnsortedSet toString, extend AbstractSet");

		// current unsorted is "a b c z d y x"
		// current unsorted2 is "b d"
		// current sorted is "a b c d y z"
		// current sorted2 is "a b c d x y z"
		
		// Test 21, UnsortedSet intersection
		testNum++;
		unsorted2.add("e");
		unsorted3 = unsorted.intersection(unsorted2);
		expected = "(b, d)";
		actual = unsorted3.toString();
		printTest(expected.equals(actual), "UnsortedSet intersection");

		// Test 22, SortedSet intersection
		testNum++;
		sorted.add("e");
		sorted3 = sorted.intersection(sorted2);
		expected = "(a, b, c, d, y, z)";
		actual = sorted3.toString();
		printTest(expected.equals(actual), "SortedSet intersection");

		// Test 23, UnsortedSet iterator
		testNum++;
		Iterator<String> unsortedIt = unsorted2.iterator();
		expected = "bde";
		actual = "";
		while (unsortedIt.hasNext()) {
			actual += unsortedIt.next();
		}
		printTest(expected.equals(actual), "UnsortedSet iterator");

		// Test 24, SortedSet iterator
		testNum++;
		Iterator<String> sortedIt = sorted.iterator();
		expected = "abcdeyz";
		actual = "";
		while (sortedIt.hasNext()) {
			actual += sortedIt.next();
		}
		printTest(expected.equals(actual), "SortedSet iterator");

		// Test 25, UnsortedSet remove
		testNum++;
		unsorted.remove("b");
		unsorted.remove("a");
		bool1 = unsorted.remove("x");
		bool2 = unsorted.remove("o");
		expected = "(c, z, d, y)";
		actual = unsorted.toString();
		printTest(bool1 && !bool2 && expected.equals(actual), "UnsortedSet remove");

		// Test 26, SortedSet remove
		testNum++;
		sorted.remove("b");
		sorted.remove("a");
		bool1 = sorted.remove("y");
		bool2 = sorted.remove("o");
		expected = "(c, d, e, z)";
		actual = sorted.toString();
		printTest(bool1 && !bool2 && expected.equals(actual), "SortedSet remove");

		// Test 27, UnsortedSet size
		testNum++;
		printTest(unsorted.size() == 4, "UnsortedSet size");

		// Test 28, SortedSet size
		testNum++;
		printTest(sorted.size() ==  4, "SortedSet size");

		// Test 28, UnsortedSet union
		testNum++;
		unsorted3 = unsorted.union(unsorted2);
		expected = "(c, z, d, y, b, e)";
		actual = unsorted3.toString();
		printTest(unsorted3.size() == 6 && expected.equals(actual), "UnsortedSet union");

		// Test 28, SortedSet union
		testNum++;
		sorted3 = sorted.union(sorted2);
		expected = "(a, b, c, d, e, x, y, z)";
		actual = sorted3.toString();
		printTest(sorted3.size() == 8 && expected.equals(actual), "SortedSet union");

	}

	public static void printTest(boolean passed, String testDescription) {
		if (passed) {
			System.out.println(
					"Passed test " + testNum + ", " + testDescription + "\n");
		} else {
			System.out.println(
					"[FAILED] test" + testNum + ", " + testDescription + "\n");
		}
	}
	
}
