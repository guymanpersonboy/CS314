package Linked_List;
/*  Student information for assignment:
*
*  On my honor, CHRISTOPHER CARRASCO this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name: Christopher Carrasco
*  email address: chris.carrasco@att.net
*  UTEID: cc66496
*  TA name: Andrew
*  Number of slip days used on this assignment: 1
*/

//Experiment results. CS314 students, place your experiment
//results here:
/*
 * Methods that are faster with LinkedList:
 * - Adding at front
 *   - LinkedList is O(1) because it does not need to shift N elements over to make room
 *   - ArrayList is O(N) because it does need to shift N elements over
 * 
 * - Removing from front
 *   - LinkedList is O(1) because it does not need to shift N elements over to make room
 *   - ArrayList is O(N) because it does need to shift N elements over
 *  
 * Methods that are slower with LinkedList:
 * - Getting random
 *   - LinkedList is O(N) because it must iterate through N to find the element
 *   - ArrayList is O(1) because it has easy access to an element at an index
 * 
 * - Getting all using get method
 *   - LinkedList is O(N) because it must iterate through N to find the element
 *   - ArrayList is O(1) because it has easy access to an element at an index
 * 
 * Methods that are about the same in both:
 * - Adding at the end.
 *   - LinkedList is O(1) because it has easy access to the end to add.
 *   - ArrayList is also O(1) because it has easy access to the end to add.
 * 
 * - getting all using iterator
 *   - LinkedList is O(N) because it must iterate through all N.
 *   - ArrayList is also O(N) because it must iterate through all N.
 */

import java.util.Iterator;
import java.util.Arrays;

public class LinkedListTester {

	public static void main(String[] args) {

		LinkedList<Integer> list = new LinkedList<>();
		Object[] actualObj;
		Object[] expectedObj;

		// Test 0, empty list
		System.out.println("\nTest 0: initial list is empty");
		if (list.toString().equals("[]")) {
			System.out.println("Passed test 0");
		} else {
			System.out.println("FAILED test 0");
		}

		// Test 1, addFirst
		System.out.println("\nTest 1, addFirst");
		list.addFirst(5);
		System.out.println("Expected: " + "[5]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[5]")) {
			System.out.println("Passed test 1");
		} else {
			System.out.println("FAILED test 1");
		}

		// Test 2, addFirst
		System.out.println("\nTest 2, addFirst");
		list.addFirst(-9);
		if (list.toString().equals("[-9, 5]")) {
			System.out.println("Passed test 2");
		} else {
			System.out.println("FAILED test 2");
		}

		// Test 3, addFirst
		System.out.println("\nTest 3, addFirst");

		list.addFirst(25);
		if (list.toString().equals("[25, -9, 5]")) {
			System.out.println("Passed test 3");
		} else {
			System.out.println("FAILED test 3");
		}

		// Test 4, addLast
		System.out.println("\nTest 4, addLast");
		list.addLast(1);
		System.out.println("Expected: " + "[25, -9, 5, 1]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[25, -9, 5, 1]")) {
			System.out.println("Passed test 4");
		} else {
			System.out.println("FAILED test 4");
		}

		// Test 5, addLast
		System.out.println("\nTest 5, addLast");
		list.addLast(-2);
		if (list.toString().equals("[25, -9, 5, 1, -2]")) {
			System.out.println("Passed test 5");
		} else {
			System.out.println("FAILED test 5");
		}

		// Test 6, addLast
		System.out.println("\nTest 6, addLast");
		list.addLast(3);
		if (list.toString().equals("[25, -9, 5, 1, -2, 3]")) {
			System.out.println("Passed test 2");
		} else {
			System.out.println("FAILED test 6");
		}

		// Test 7, removeFirst
		System.out.println("\nTest 7, removeFirst");
		list.removeFirst();
		System.out.println("Expected: " + "[-9, 5, 1, -2, 3]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[-9, 5, 1, -2, 3]")) {
			System.out.println("Passed test 7");
		} else {
			System.out.println("FAILED test 7");
		}

		// Test 8, removeFirst
		System.out.println("\nTest 8, removeFirst");
		list.removeFirst();
		if (list.toString().equals("[5, 1, -2, 3]")) {
			System.out.println("Passed test 8");
		} else {
			System.out.println("FAILED test 8");
		}

		// Test 9, removeFirst
		System.out.println("\nTest 9, removeFirst");
		list.removeFirst();
		if (list.toString().equals("[1, -2, 3]")) {
			System.out.println("Passed test 9");
		} else {
			System.out.println("FAILED test 9");
		}

		// Test 10, removeLast
		System.out.println("\nTest 10, removeLast");
		list.removeLast();
		System.out.println("Expected: " + "[1, -2]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[1, -2]")) {
			System.out.println("Passed test 10");
		} else {
			System.out.println("FAILED test 10");
		}

		// Test 11, removeLast
		System.out.println("\nTest 11, removeLast");
		list.removeLast();
		if (list.toString().equals("[1]")) {
			System.out.println("Passed test 11");
		} else {
			System.out.println("FAILED test 11");
		}

		// Test 12, removeLast
		System.out.println("\nTest 12, removeLast");
		list.removeLast();
		if (list.toString().equals("[]")) {
			System.out.println("Passed test 12");
		} else {
			System.out.println("FAILED test 12");
		}

		// Test 13, toString
		System.out.println("\nTest 13, toString");
		list.addLast(42);
		System.out.println("Expected: " + "[42]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[42]")) {
			System.out.println("Passed test 13");
		} else {
			System.out.println("FAILED test 13");
		}

		// Test 14, toString
		System.out.println("\nTest 14, toString");
		list.removeFirst();
		System.out.println("Expected: " + "[]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[]")) {
			System.out.println("Passed test 14");
		} else {
			System.out.println("FAILED test 14");
		}

		// Test 15, toString
		System.out.println("\nTest 15, toString");
		list.addLast(24);
		System.out.println("Expected: " + "[24]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[24]")) {
			System.out.println("Passed test 15");
		} else {
			System.out.println("FAILED test 15");
		}

		// Test 16, equals
		System.out.println("\nTest 16, equals");
		LinkedList<Integer> otherList = new LinkedList<>();
		otherList.addFirst(24);
		System.out.println("list: " + Arrays.toString(listToArray(list)));
		System.out.println(
				"otherList: " + Arrays.toString(listToArray(otherList)));
		if (list.equals(otherList)) {
			System.out.println("Passed test 16");
		} else {
			System.out.println("FAILED test 16");
		}

		// Test 17, equals
		System.out.println("\nTest 17, equals");
		list.removeFirst();
		otherList.removeLast();
		System.out.println("list: " + Arrays.toString(listToArray(list)));
		System.out.println(
				"otherList: " + Arrays.toString(listToArray(otherList)));
		if (list.equals(otherList)) {
			System.out.println("Passed test 17");
		} else {
			System.out.println("FAILED test 17");
		}

		// Test 18, equals
		System.out.println("\nTest 18, equals");
		LinkedList<String> stringList = new LinkedList<>();
		stringList.addFirst("_");
		System.out.println("list: " + Arrays.toString(listToArray(list)));
		System.out
				.println("strList: " + Arrays.toString(strToArray(stringList)));
		if (!(list.equals(stringList))) {
			System.out.println("Passed test 18");
		} else {
			System.out.println("FAILED test 18");
		}

		// Test 19, add
		System.out.println("\nTest 19, add");
		list.add(66);
		if (list.toString().equals("[66]")) {
			System.out.println("Passed test 19");
		} else {
			System.out.println("FAILED test 19");
		}

		// Test 20, add
		System.out.println("\nTest 20, add");
		list.add(99);
		if (list.toString().equals("[66, 99]")) {
			System.out.println("Passed test 20");
		} else {
			System.out.println("FAILED test 20");
		}

		// Test 21, add
		System.out.println("\nTest 21, add");
		stringList.removeFirst();
		stringList.add("24");
		stringList.add("A C");
		System.out.println("Expected: " + "[24, A C]");
		System.out
				.println("Actual: " + Arrays.toString(strToArray(stringList)));
		if (stringList.toString().equals("[24, A C]")) {
			System.out.println("Passed test 21");
		} else {
			System.out.println("FAILED test 21");
		}

		// Test 22, insert
		System.out.println("\nTest 22, insert");
		list.insert(1, 22);
		System.out.println("Expected: " + "[66, 22, 99]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[66, 22, 99]")) {
			System.out.println("Passed test 22");
		} else {
			System.out.println("FAILED test 22");
		}

		// Test 23, insert
		System.out.println("\nTest 23, insert");
		list.insert(0, 11);
		if (list.toString().equals("[11, 66, 22, 99]")) {
			System.out.println("Passed test 23");
		} else {
			System.out.println("FAILED test 23");
		}

		// Test 24, insert
		list.insert(4, 33);
		System.out.println("\nTest 24, insert");
		if (list.toString().equals("[11, 66, 22, 99, 33]")) {
			System.out.println("Passed test 24");
		} else {
			System.out.println("FAILED test 24");
		}

		// Test 25, set
		System.out.println("\nTest 25, set");
		list.set(0, 0);
		if (list.toString().equals("[0, 66, 22, 99, 33]")) {
			System.out.println("Passed test 25");
		} else {
			System.out.println("FAILED test 25");
		}

		// Test 26, set
		System.out.println("\nTest 26, set");
		list.set(2, 0);
		if (list.toString().equals("[0, 66, 0, 99, 33]")) {
			System.out.println("Passed test 26");
		} else {
			System.out.println("FAILED test 26");
		}

		// Test 27, set
		System.out.println("\nTest 27, set");
		list.set(4, 0);
		System.out.println("Expected: " + "[0, 66, 0, 99, 0]");
		System.out.println("Actual: " + Arrays.toString(listToArray(list)));
		if (list.toString().equals("[0, 66, 0, 99, 0]")) {
			System.out.println("Passed test 27");
		} else {
			System.out.println("FAILED test 27");
		}

		// Test 28, get
		System.out.println("\nTest 28, get");
		if (list.get(0).equals(0)) {
			System.out.println("Passed test 28");
		} else {
			System.out.println("FAILED test 28");
		}

		// Test 29, get
		System.out.println("\nTest 29, get");
		if (list.get(2).equals(0)) {
			System.out.println("Passed test 29");
		} else {
			System.out.println("FAILED test 29");
		}

		// Test 30, get
		System.out.println("\nTest 30, get");
		int actualInt = list.get(4);
		int expectedInt = 0;
		System.out.println("Expected: " + expectedInt);
		System.out.println("Actual: " + actualInt);
		if (actualInt == expectedInt) {
			System.out.println("Passed test 30");
		} else {
			System.out.println("FAILED test 30");
		}

		// Test 31, remove(int pos)
		System.out.println("\nTest 31, remove(int pos)");
		int removed = list.remove(0);
		if (list.toString().equals("[66, 0, 99, 0]") && removed == 0) {
			System.out.println("Passed test 31");
		} else {
			System.out.println("FAILED test 31");
		}

		// Test 32, remove(int pos)
		System.out.println("\nTest 32, remove(int pos)");
		removed = list.remove(1);
		if (list.toString().equals("[66, 99, 0]") && removed == 0) {
			System.out.println("Passed test 32");
		} else {
			System.out.println("FAILED test 32");
		}

		// Test 33, remove(int pos)
		System.out.println("\nTest 33, remove(int pos)");
		removed = list.remove(2);
		System.out.println("Expected: " + "[66, 99]. Return value should be 0");
		System.out.println("Actual: " + Arrays.toString(listToArray(list))
				+ ". Return value was " + removed);
		if (list.toString().equals("[66, 99]") && removed == 0) {
			System.out.println("Passed test 33");
		} else {
			System.out.println("FAILED test 33");
		}

		// Test 34, remove(E obj)
		System.out.println("\nTest 34, remove(E obj)");
		list.addFirst(33);
		boolean expectedBool = true;
		boolean actualBool = list.remove((Integer) 33);
		if (list.toString().equals("[66, 99]") && expectedBool == actualBool) {
			System.out.println("Passed test 34");
		} else {
			System.out.println("FAILED test 34");
		}

		// Test 35, remove(E obj)
		System.out.println("\nTest 35, remove(E obj)");
		expectedBool = false;
		actualBool = list.remove((Integer) 17);
		if (list.toString().equals("[66, 99]") && expectedBool == actualBool) {
			System.out.println("Passed test 35");
		} else {
			System.out.println("FAILED test 35");
		}

		// Test 36, remove(E obj)
		System.out.println("\nTest 36, remove(E obj)");
		expectedBool = true;
		actualBool = list.remove((Integer) 99);
		System.out.println(
				"Expected: " + "[66]. Returned boolean should be true");
		System.out.println("Actual: " + Arrays.toString(listToArray(list))
				+ ". Returned boolean was " + actualBool);
		if (list.toString().equals("[66]") && expectedBool == actualBool) {
			System.out.println("Passed test 36");
		} else {
			System.out.println("FAILED test 36");
		}

		// Test 37, getSubList
		System.out.println("\nTest 37, getSubList");
		list.add(55);
		list.addLast(44);
		list.addFirst(77);
		IList<Integer> al = list.getSubList(0, 4);
		actualObj = listToArray(al);
		expectedObj = new Object[] { 77, 66, 55, 44 };
		System.out.println("Expected result: " + Arrays.toString(expectedObj));
		System.out.println("Actual result: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 37");
		} else {
			System.out.println("FAILED test 37");
		}

		// Test 38, getSubList
		System.out.println("\nTest 38, getSubList");
		al = list.getSubList(0, 0);
		actualObj = listToArray(al);
		expectedObj = new Object[] {};
		System.out.println("Expected result: " + Arrays.toString(expectedObj));
		System.out.println("Actual result: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 38");
		} else {
			System.out.println("FAILED test 38");
		}

		// Test 39, getSubList
		System.out.println("\nTest 39, getSubList");
		al = list.getSubList(4, 4);
		actualObj = listToArray(al);
		expectedObj = new Object[] {};
		System.out.println("Expected result: " + Arrays.toString(expectedObj));
		System.out.println("Actual result: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 39");
		} else {
			System.out.println("FAILED test 39");
		}

		// Test 37-39, getSubList does not change list
		System.out.println("\nTest 37-39, getSubList does not change list");
		actualObj = listToArray(list);
		expectedObj = new Object[] { 77, 66, 55, 44 };
		System.out.println("Expected result: " + Arrays.toString(expectedObj));
		System.out.println("Actual result: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 37-39");
		} else {
			System.out.println("FAILED test 37-39");
		}

		// Test 40, size
		System.out.println("\nTest 40, size");
		actualInt = list.size();
		expectedInt = 4;
		if (actualInt == expectedInt) {
			System.out.println("Passed test 40");
		} else {
			System.out.println("FAILED test 40");
		}

		// Test 41, size
		System.out.println("\nTest 41, size");
		actualInt = stringList.size();
		expectedInt = 2;
		if (actualInt == expectedInt) {
			System.out.println("Passed test 41");
		} else {
			System.out.println("FAILED test 41");
		}

		// Test 42, size
		System.out.println("\nTest 42, size");
		stringList.remove(1);
		stringList.remove(0);
		actualInt = stringList.size();
		expectedInt = 0;
		System.out.println("Expected size: 0");
		System.out.println("Actual size: " + actualInt);
		if (actualInt == expectedInt) {
			System.out.println("Passed test 42");
		} else {
			System.out.println("FAILED test 42");
		}

		// Test 43, indexOf(E item)
		System.out.println("\nTest 43, indexOf(E item)");
		actualInt = list.indexOf((Integer) 77);
		expectedInt = 0;
		if (actualInt == expectedInt) {
			System.out.println("Passed test 43");
		} else {
			System.out.println("FAILED test 43");
		}

		// Test 44, indexOf(E item)
		System.out.println("\nTest 44, indexOf(E item)");
		actualInt = list.indexOf((Integer) 44);
		expectedInt = 3;
		System.out.println("Expected index: " + expectedInt);
		System.out.println("Actual index: " + actualInt);
		if (actualInt == expectedInt) {
			System.out.println("Passed test 44");
		} else {
			System.out.println("FAILED test 44");
		}

		// Test 45, indexOf(E item)
		System.out.println("\nTest 45, indexOf(E item)");
		actualInt = list.indexOf((Integer) 11);
		expectedInt = -1;
		if (actualInt == expectedInt) {
			System.out.println("Passed test 45");
		} else {
			System.out.println("FAILED test 45");
		}

		// Test 46, indexOf(E item, int pos)
		System.out.println("\nTest 46, indexOf(E item, int pos)");
		list.add(77);
		list.add(66);
		list.addLast(55);
		list.addLast(44);
		actualInt = list.indexOf(77, 0);
		expectedInt = 0;
		if (actualInt == expectedInt) {
			System.out.println("Passed test 46");
		} else {
			System.out.println("FAILED test 46");
		}

		// Test 47, indexOf(E item, int pos)
		System.out.println("\nTest 47, indexOf(E item, int pos)");
		actualInt = list.indexOf(77, 1);
		expectedInt = 4;
		if (actualInt == expectedInt) {
			System.out.println("Passed test 47");
		} else {
			System.out.println("FAILED test 47");
		}

		// Test 48, indexOf(E item, int pos)
		System.out.println("\nTest 48, indexOf(E item, int pos)");
		actualInt = list.indexOf(66, 3);
		expectedInt = 5;
		System.out.println("Expected index: 5");
		System.out.println("Actual index: " + actualInt);
		if (actualInt == expectedInt) {
			System.out.println("Passed test 48");
		} else {
			System.out.println("FAILED test 48");
		}

		// Test 49, makeEmpty
		System.out.println("\nTest 49, makeEmpty");
		list.makeEmpty();
		actualObj = listToArray(list);
		expectedObj = new Object[] {};
		System.out.println("Expected: " + Arrays.toString(expectedObj));
		System.out.println("Actual: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 49");
		} else {
			System.out.println("FAILED test 49");
		}

		// Test 50, makeEmpty on an empty list
		System.out.println("\nTest 50, makeEmpty on an empty list");
		list.add(11);
		list.makeEmpty();
		list.makeEmpty();
		actualObj = listToArray(list);
		System.out.println("Expected: " + Arrays.toString(expectedObj));
		System.out.println("Actual: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 50");
		} else {
			System.out.println("FAILED test 50");
		}

		// Test 51, makeEmpty
		System.out.println("\nTest 51, makeEmpty");
		for (int add = 0; add < 10; add++) {
			stringList.add("America runs on Dunkin'");
		}
		stringList.makeEmpty();
		actualObj = strToArray(stringList);
		System.out.println("Expected: " + Arrays.toString(expectedObj));
		System.out.println("Actual: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 51");
		} else {
			System.out.println("FAILED test 51");
		}

		// Test 52, removeRange
		System.out.println("\nTest 52, removeRange");
		for (int add = 0; add < 8; add++) {
			stringList.add("America runs on Dunkin'");
		}
		stringList.removeRange(0, 4);
		actualObj = strToArray(stringList);
		expectedObj = new Object[] { "America runs on Dunkin'",
				"America runs on Dunkin'", "America runs on Dunkin'",
				"America runs on Dunkin'" };
		System.out.println("Expected: " + Arrays.toString(expectedObj));
		System.out.println("Actual: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 52");
		} else {
			System.out.println("FAILED test 52");
		}

		// Test 53, removeRange 0 to size
		System.out.println("\nTest 53, removeRange 0 to size");
		stringList.add("America runs on Dunkin'");
		stringList.removeRange(0, 5);
		actualObj = strToArray(stringList);
		expectedObj = new Object[] {};
		System.out.println("Expected: " + Arrays.toString(expectedObj));
		System.out.println("Actual: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 53");
		} else {
			System.out.println("FAILED test 53");
		}

		// Test 54, removeRange start == stop
		System.out.println("\nTest 54, removeRange start == stop");
		stringList.add("America runs on Dunkin'");
		stringList.removeRange(0, 0);
		actualObj = strToArray(stringList);
		expectedObj = new Object[] { "America runs on Dunkin'" };
		System.out.println("(Added an element to string list)");
		System.out.println("Expected: " + Arrays.toString(expectedObj));
		System.out.println("Actual: " + Arrays.toString(actualObj));
		if (arraysSame(actualObj, expectedObj)) {
			System.out.println("Passed test 54");
		} else {
			System.out.println("FAILED test 54");
		}

		// Test 55, iterator
		System.out.println("\nTest 55, iterator");
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		Iterator<Integer> listIt = list.iterator();
		actualInt = 0;
		while (listIt.hasNext()) {
			actualInt += listIt.next();
		}
		expectedInt = 55;
		System.out.println("Expected sum of all Integers: 55");
		System.out.println("Actual sum of all Integers: " + actualInt);
		if (actualInt == expectedInt) {
			System.out.println("Passed test 55");
		} else {
			System.out.println("FAILED test 55");
		}

		// Test 56, iterator
		System.out.println("\nTest 56, iterator");
		if (!listIt.hasNext()) {
			System.out.println("Passed test 56");
		} else {
			System.out.println("FAILED test 56");
		}

		// Test 57, iterator
		System.out.println("\nTest 57, iterator remove()");
		stringList.makeEmpty();
		stringList.add("should be removed");
		Iterator<String> strIt = stringList.iterator();
		strIt.next();
		strIt.remove();
		actualInt = stringList.size();
		expectedInt = 0;
		System.out.println("Expected string list: []. Expected size == 0");
		System.out.println(
				"Actual string list: " + Arrays.toString(strToArray(stringList))
						+ ". Expected size == " + actualInt);
		if (actualInt == expectedInt) {
			System.out.println("Passed test 57");
		} else {
			System.out.println("FAILED test 57");
		}
	}

	private static Object[] listToArray(IList<Integer> list) {
		Object[] result = new Object[list.size()];
		Iterator<Integer> it = list.iterator();
		int index = 0;
		while (it.hasNext()) {
			result[index] = it.next();
			index++;
		}
		return result;
	}

	private static Object[] strToArray(IList<String> stringList) {
		Object[] result = new Object[stringList.size()];
		Iterator<String> it = stringList.iterator();
		int index = 0;
		while (it.hasNext()) {
			result[index] = it.next();
			index++;
		}
		return result;
	}

	// pre: none
	private static boolean arraysSame(Object[] one, Object[] two) {
		boolean same;
		if (one == null || two == null) {
			same = (one == two);
		} else {
			// neither one or two are null
			assert one != null && two != null;
			same = one.length == two.length;
			if (same) {
				int index = 0;
				while (index < one.length && same) {
					same = (one[index].equals(two[index]));
					index++;
				}
			}
		}
		return same;
	}
}
