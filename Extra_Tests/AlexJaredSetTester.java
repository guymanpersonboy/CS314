package Extra_Tests;
/*
    This is a battery of tests written by Alex Hanlin and Jared Kahl for 
    assignment a8 - Sets 
    Comment out methods as necessary when using.
    Message Alex on Piazza or Discord (Gluethulhu#0117) if you find any bugs or 
    errors! If you do so, please use a test's name when calling attention to it,
    as number is only tracked at runtime.
*/

import java.util.ArrayList;
import java.util.Iterator;

import Set_Classes.AbstractSet;
import Set_Classes.ISet;
import Set_Classes.SortedSet;
import Set_Classes.UnsortedSet;

public class AlexJaredSetTester {

    public static void main(String[] args) {
        runUnsortedTests();
        runSortedTests();
        runComboTests();
        finalResults();
    }

    // tests for the unsorted set class
    public static void runUnsortedTests() {
        unsortedAddTests();
        unsortedContainsTests();
        unsortedAddAllTests();
        unsortedContainsAllTests();
        unsortedSizeTests();
        unsortedClearTests();
        unsortedDifferenceTests();
        unsortedUnionTests();
        unsortedIntersectionTests();
        unsortedEqualsTests();
        unsortedIteratorTests();
        unsortedRemoveTests();
    }

    // tests for the sorted set class
    public static void runSortedTests() {
        sortedAddTests();
        sortedContainsTests();
        sortedAddAllTests();
        sortedContainsAllTests();
        sortedSizeTests();
        sortedClearTests();
        sortedDifferenceTests();
        sortedUnionTests();
        sortedIntersectionTests();
        sortedEqualsTests();
        sortedIteratorTests();
        sortedRemoveTests();
        sortedMinAndMaxTests();
    }

    // tests that require both sorted and unsorted 
    public static void runComboTests() {
        sortedConstructorTests();
        comboAddAllTests();
        comboContainsAllTests();
        comboDifferenceTests();
        comboIntersectionTests();
        comboUnionTests();
        comboEqualsTests();
    }

    // Timing tests have currently not been written. There is a timing method
    // template below the other tests, if you choose to fill it in.
    // methods for testing time complexity of methods
    // more details in Timing Tests section of this class
    // private static void timingTests() {
    //     printHeader("timing tests", '#');
    //     stubTimingTests();
    // }

    /*------------------------ UnsortedSet Tests ---------------------------*/
    private static AbstractSet<Integer> set = new UnsortedSet<>();
    private static AbstractSet<Integer> set2 = new UnsortedSet<>();

    // Add tests and contains test rely on each other to function properly
    public static void unsortedAddTests() {
        printHeader("unsorted add tests", '=');

        // added element is contained in the set
        set.add(1);
        printTest("add contains", set.contains(1));

        // add returns true on successful add
        printTest("add returns true", set.add(0));

        // add returns false on unsuccessful add
        printTest("add returns false", !set.add(1));

        // contains first value after others added
        printTest("add consistency", set.contains(1));

        // add cannot take in a null value
        boolean hasFailed = false;
        try {
            set.add(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("add precon", !hasFailed);
    }

    public static void unsortedContainsTests() {
        printHeader("unsorted contains tests", '=');

        // returns true when element is in set
        printTest("contains returns true",
                set.contains(1) && set.contains(0));

        // returns false when element is not in set
        printTest("contains returns false", !set.contains(Integer.MAX_VALUE));

        // contains cannot take in a null value
        boolean hasFailed = false;
        try {
            set.contains(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("contains precon", !hasFailed);
    }

    public static void unsortedAddAllTests() {
        printHeader("unsorted addAll tests", '=');
        set2 = new UnsortedSet<>();
        set2.add(5);
        set2.add(6);
        set2.add(7);
        set2.add(8);

        // addAll returns true with all new elements
        printTest("addAll only new elements", set.addAll(set2));

        // addAll adds elements to set
        printTest("addAll adds elements",
                set.contains(5) && set.contains(6) &&
                        set.contains(7) && set.contains(8));


        set2.add(9);
        set2.add(10);

        // addAll returns true with some new elements
        printTest("addAll some new elements", set.addAll(set2));

        // addAll adds elements to set
        printTest("addAll adds new elements",
                set.contains(9) && set.contains(10));

        // addAll returns false with no new elements
        printTest("addAll no new elements", !set.addAll(set2));

        // addAll returns false with an empty set
        printTest("addAll empty set", !set.addAll(new UnsortedSet<>()));


        // addAll cannot take in a null value
        boolean hasFailed = false;
        try {
            set.addAll(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("addAll precon", !hasFailed);
    }


    public static void unsortedContainsAllTests() {
        printHeader("unsorted containsAll tests", '=');

        // returns true if list contains itself
        printTest("containsAll itself", set.containsAll(set));

        // returns true when given empty set
        printTest("containsAll empty set",
                set.containsAll(new UnsortedSet<>()));

        // returns true with other set
        set2 = new UnsortedSet<>();
        set2.add(9);
        set2.add(10);
        printTest("containsAll other set all shared elems",
                set.containsAll(set2));

        // returns false with other set
        set2.add(81);
        set2.add(7000000);
        printTest("containsAll other set some shared elems",
                !set.containsAll(set2));

        // returns false with other set
        set2 = new UnsortedSet<>();
        set2.add(Integer.MAX_VALUE);
        set2.add(Integer.MIN_VALUE);
        printTest("containsAll other set no shared elems",
                !set.containsAll(set2));

        // containsAll cannot take in a null value
        boolean hasFailed = false;
        try {
            set.containsAll(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("containsAll precon", !hasFailed);
    }

    public static void unsortedSizeTests() {
        printHeader("unsorted size tests", '=');

        // size is zero with no elements
        set = new UnsortedSet<>();
        printTest("size with no elements", set.size() == 0);

        // size updated after add
        set.add(1);
        set.add(2);
        printTest("size after add", set.size() == 2);

        // size updated after addAll
        set2 = new UnsortedSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set.addAll(set2);
        printTest("size after addAll", set.size() == 5);


    }

    public static void unsortedClearTests() {
        printHeader("unsorted clear tests", '=');

        set = new UnsortedSet<>();
        set.add(1);
        set.add(2);
        // clear removes all elements
        set.clear();
        printTest("clear removes elements",
                !set.contains(1) && !set.contains(2));

        // size updated after clear
        set.clear();
        printTest("size after clear", set.size() == 0);

        // works on empty set
        set.clear();
        printTest("clear empty set", set.size() == 0);
    }

    public static void unsortedDifferenceTests() {
        printHeader("unsorted difference tests", '=');

        set = new UnsortedSet<>();
        set2 = new UnsortedSet<>();
        ISet<Integer> diff;

        // check difference between 2 empty sets
        diff = set.difference(set2);
        printTest("difference of 2 empty sets", diff.size() == 0);

        // check difference btwn elems and empty set
        set.add(1);
        set.add(2);
        set.add(3);
        diff = set.difference(set2);
        printTest("difference of set and empty set", diff.containsAll(set));
        System.out.println("Set: " + set.toString());
        System.out.println("Set2: " + set2.toString());
        System.out.println("Diff: " + diff.toString()); // todo delete
        // check commutativity of empty set
        diff = set2.difference(set);
        printTest("commutativity of empty set", diff.size() == 0);

        // check difference between 2 completely different sets
        set2.add(4);
        set2.add(5);
        diff = set.difference(set2);
        printTest("difference of completely different sets",
                diff.containsAll(set) && !diff.contains(4) && !diff.contains(5));

        // check difference between 2 overlapping sets
        set2.add(2);
        set2.add(3);
        diff = set.difference(set2);
        printTest("difference of overlapping sets",
                diff.contains(1) && diff.size() == 1);
        System.out.println("Set: " + set.toString());
        System.out.println("Set2: " + set2.toString());
        System.out.println("Diff: " + diff.toString()); // todo delete
        // check commutativity
        diff = set2.difference(set);
        printTest("commutativity of difference of overlapping sets",
                diff.contains(4) && diff.contains(5) && diff.size() == 2);
        System.out.println("Set: " + set.toString());
        System.out.println("Set2: " + set2.toString());
        System.out.println("Diff: " + diff.toString()); // todo delete
        // check difference between 2 equal sets
        diff = set.difference(set);
        printTest("difference of identical sets", diff.size() == 0);

        // check precondition
        // difference cannot take in a null value
        boolean hasFailed = false;
        try {
            set.difference(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("difference precon", !hasFailed);
    }

    public static void unsortedUnionTests() {
        printHeader("unsorted union tests", '=');

        set = new UnsortedSet<>();
        set2 = new UnsortedSet<>();
        ISet<Integer> union;

        // check union between 2 empty sets
        union = set.union(set2);
        printTest("union between empty sets", union.size() == 0);

        // check union btwn elems and empty set
        set.add(1);
        set.add(2);
        set.add(3);
        union = set.union(set2);
        printTest("union of set and empty set", union.containsAll(set));

        // check commutativity of empty set
        union = set2.union(set);
        printTest("commutativity of empty set", union.containsAll(set));

        // check union between 2 completely different sets
        set2.add(4);
        set2.add(5);
        union = set.union(set2);
        printTest("union of completely different sets",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // check union between 2 overlapping sets
        set2.add(2);
        set2.add(3);
        union = set.union(set2);
        printTest("union of overlapping sets",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // check commutativity
        union = set2.union(set);
        printTest("commutativity of union",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // check union between 2 equal sets
        set.add(4);
        set.add(5);
        set2.add(1);
        printTest("union of identical sets",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // check precondition
        // union cannot take in a null value
        boolean hasFailed = false;
        try {
            set.union(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("union precon", !hasFailed);

    }

    public static void unsortedIntersectionTests() {
        printHeader("unsorted intersection tests", '=');

        set = new UnsortedSet<>();
        set2 = new UnsortedSet<>();
        ISet<Integer> intersection;

        // check intersection between 2 empty sets
        intersection = set.intersection(set2);
        printTest("intersection between empty sets", intersection.size() == 0);

        // check intersection btwn elems and empty set
        set.add(1);
        set.add(2);
        set.add(3);
        intersection = set.intersection(set2);
        printTest("intersection of set and empty set", intersection.size() == 0);

        // check commutativity of empty set
        intersection = set2.intersection(set);
        printTest("commutativity of empty set", intersection.size() == 0);

        // check intersection between 2 completely different sets
        set2.add(4);
        set2.add(5);
        intersection = set.intersection(set2);
        printTest("intersection of completely different sets", intersection.size() == 0);

        // check intersection between 2 overlapping sets
        set2.add(2);
        set2.add(3);
        intersection = set.intersection(set2);
        printTest("intersection of overlapping sets",
                intersection.contains(2) && intersection.contains(3) && intersection.size() == 2);

        // check commutativity
        intersection = set2.intersection(set);
        printTest("commutativity of intersection",
                intersection.contains(2) && intersection.contains(3) && intersection.size() == 2);

        // check intersection between 2 equal sets
        set.add(4);
        set.add(5);
        set2.add(1);
        intersection = set.intersection(set2);
        printTest("intersection of identical sets",
                intersection.containsAll(set) && intersection.containsAll(set2) && intersection.size() == 5);

        // check precondition
        // intersection cannot take in a null value
        boolean hasFailed = false;
        try {
            set.intersection(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("intersection precon", !hasFailed);
    }

    public static void unsortedEqualsTests() {
        printHeader("unsorted equals tests", '=');

        set = new UnsortedSet<>();
        set2 = new UnsortedSet<>();

        // check 2 empty sets
        printTest("equals 2 empty sets", set.equals(set2));

        // check empty set and full set
        set.add(1);
        set.add(2);
        set.add(3);
        printTest("equals set and empty set", !set.equals(set2));

        // check commutativity of empty set
        printTest("commutativity of empty set", !set2.equals(set));

        // check completely different sets
        set2.add(4);
        set2.add(5);
        printTest("equals completely different sets", !set.equals(set2));

        // check overlapping sets
        set2.add(2);
        set2.add(3);
        printTest("equals overlapping sets", !set.equals(set2));

        // check equal sets
        set.add(4);
        set.add(5);
        set2.add(1);
        printTest("equals same sets", set.equals(set2));

        // check false if null
        printTest("equals null case", !set.equals(null));

        // check false if wrong type
        ArrayList<Integer> arrList = new ArrayList<>();
        arrList.add(1);
        arrList.add(2);
        arrList.add(3);
        arrList.add(4);
        arrList.add(5);
        printTest("equals wrong type", !set.equals(arrList));
    }

    public static void unsortedIteratorTests() {
        printHeader("unsorted iterator tests", '=');

        set = new UnsortedSet<>();
        Iterator<Integer> setIt = set.iterator();

        // check hasNext for empty set
        printTest("iterator empty set hasNext", !setIt.hasNext());

        // check next for empty set
        boolean hasFailed = false;
        try {
            setIt.next();
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("iterator empty set next", !hasFailed);

        // iterator for full set
        set.add(1);
        set.add(2);
        set.add(3);
        setIt = set.iterator();
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 2; i >= 0; i--) {
            printTest("iterator full set hasNext", setIt.hasNext());
            int next = setIt.next();
            values.add(next);
            setIt.remove();
            printTest("iterator full set remove",
                    set.size() == i && !set.contains(next));
        }
        printTest("iterator next loops through elements",
                values.contains(1) && values.contains(2) && values.contains(3));
    }

    public static void unsortedRemoveTests() {
        printHeader("unsorted remove tests", '=');

        set = new UnsortedSet<>();

        // removing on empty set returns false
        printTest("remove on empty set", !set.remove(1) && set.size() == 0);

        // removing when item not present
        set.add(1);
        set.add(2);
        set.add(3);
        printTest("remove when not present", !set.remove(4) && set.size() == 3);

        // removing when item is present
        printTest("remove when present",
                set.remove(3) && !set.contains(3) && set.size() == 2);

        // removing when item already removed
        printTest("remove when present", !set.remove(3));

        // remove until empty
        set.remove(2);
        set.remove(1);
        printTest("remove until empty",
                !set.contains(1) && !set.contains(2) && set.size() == 0);

        // check precondition
        // remove cannot take in null
        boolean hasFailed = false;
        try {
            set.remove(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("check precon", !hasFailed);
    }


    /*------------------------ SortedSet Tests ---------------------------*/
    private static String expectedStr;


    public static void sortedAddTests() {
        printHeader("sorted add tests", '=');
        set = new SortedSet<>();
        // added element is contained in the set
        set.add(1);
        expectedStr = "(1)";
        printTest("add contains", set.toString().equals(expectedStr));

        // add returns true on successful add
        printTest("add returns true", set.add(0));


        // add remains sorted with new element
        expectedStr = "(0, 1)";
        printTest("add remains sorted", set.toString().equals(expectedStr));


        // add returns false on unsuccessful add
        printTest("add returns false", !set.add(1));

        // add cannot take in a null value
        boolean hasFailed = false;
        try {
            set.add(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("add precon", !hasFailed);
    }

    public static void sortedContainsTests() {
        printHeader("sorted contains tests", '=');

        // returns true when element is in set
        printTest("contains returns true",
                set.contains(1) && set.contains(0));

        // returns false when element is not in set
        printTest("contains returns false", !set.contains(Integer.MAX_VALUE));

        // contains cannot take in a null value
        boolean hasFailed = false;
        try {
            set.contains(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("contains precon", !hasFailed);
    }


    public static void sortedAddAllTests() {
        printHeader("sorted addAll tests", '=');
        set2 = new SortedSet<>();
        set2.add(5);
        set2.add(6);
        set2.add(7);
        set2.add(8);

        // 83
        // addAll returns true with all new elements
        printTest("addAll only new elements", set.addAll(set2));

        // 84
        // addAll adds elements to set
        expectedStr = "(0, 1, 5, 6, 7, 8)"; // I don't know why I did this
        printTest("addAll adds elements", set.toString().equals(expectedStr));

        set2.add(9);
        set2.add(10);

        // addAll returns true with some new elements
        printTest("addAll some new elements", set.addAll(set2));

        // addAll adds elements to set
        expectedStr = "(0, 1, 5, 6, 7, 8, 9, 10)";
        printTest("addAll adds new elements", set.toString().equals(expectedStr));

        // addAll returns false with no new elements
        printTest("addAll no new elements", !set.addAll(set2));

        // addAll returns false with an empty set
        printTest("addAll empty set", !set.addAll(new SortedSet<>()));


        // addAll cannot take in a null value
        boolean hasFailed = false;
        try {
            set.addAll(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("addAll precon", !hasFailed);
    }


    public static void sortedContainsAllTests() {
        printHeader("sorted containsAll tests", '=');

        // returns true if list contains itself
        printTest("containsAll itself", set.containsAll(set));

        // returns true when given empty set
        printTest("containsAll empty set",
                set.containsAll(new SortedSet<>()));

        // returns true with other set
        set2 = new SortedSet<>();
        set2.add(9);
        set2.add(10);
        printTest("containsAll other set all shared elems",
                set.containsAll(set2));

        // returns false with other set
        set2.add(81);
        set2.add(7000000);
        printTest("containsAll other set some shared elems",
                !set.containsAll(set2));

        // returns false with other set
        set2 = new SortedSet<>();
        set2.add(Integer.MAX_VALUE);
        set2.add(Integer.MIN_VALUE);
        printTest("containsAll other set no shared elems",
                !set.containsAll(set2));

        // containsAll cannot take in a null value
        boolean hasFailed = false;
        try {
            set.containsAll(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("containsAll precon", !hasFailed);
    }

    public static void sortedClearTests() {
        printHeader("sorted clear tests", '=');

        set = new SortedSet<>();
        set.add(1);
        set.add(2);
        // clear removes all elements
        set.clear();
        printTest("clear removes elements",
                !set.contains(1) && !set.contains(2));

        // size updated after clear
        set.clear();
        printTest("size after clear", set.size() == 0);

        // works on empty set
        set.clear();
        printTest("clear empty set", set.size() == 0);
    }

    public static void sortedSizeTests() {
        printHeader("unsorted size tests", '=');

        // size is zero with no elements
        set = new SortedSet<>();
        printTest("size with no elements", set.size() == 0);

        // size updated after add
        set.add(1);
        set.add(2);
        printTest("size after add", set.size() == 2);

        // size updated after addAll
        set2 = new SortedSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set.addAll(set2);
        printTest("size after addAll", set.size() == 5);


    }

    public static void sortedDifferenceTests() {
        printHeader("sorted difference tests", '=');

        set = new SortedSet<>();
        set2 = new SortedSet<>();
        ISet<Integer> diff;

        // check difference between 2 empty sets
        diff = set.difference(set2);
        printTest("difference of 2 empty sets", diff.size() == 0);

        // check difference btwn elems and empty set
        set.add(1);
        set.add(3);
        set.add(2);
        expectedStr = "(1, 2, 3)";
        diff = set.difference(set2);
        printTest("difference of set and empty set",
                diff.toString().equals(expectedStr));

        // check commutativity of empty set
        diff = set2.difference(set);
        printTest("commutativity of empty set", diff.size() == 0);

        // check difference between 2 completely different sets
        set2.add(5);
        set2.add(4);
        System.out.println(set);
        System.out.println(set2);
        diff = set.difference(set2);
        System.out.println(diff);
        printTest("difference of completely different sets",
                diff.toString().equals(expectedStr));

        // check difference between 2 overlapping sets
        set2.add(3);
        set2.add(2);
        diff = set.difference(set2);
        expectedStr = "(1)";
        printTest("difference of overlapping sets",
                diff.toString().equals(expectedStr) && diff.size() == 1);

        // check commutativity
        diff = set2.difference(set);
        expectedStr = "(4, 5)";
        printTest("commutativity of difference of overlapping sets",
                diff.toString().equals(expectedStr) && diff.size() == 2);

        // check difference between 2 equal sets
        diff = set.difference(set);
        printTest("difference of same set", diff.size() == 0);

        // check precondition
        // difference cannot take in a null value
        boolean hasFailed = false;
        try {
            set.difference(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("difference precon", !hasFailed);
    }

    public static void sortedUnionTests() {
        printHeader("sorted union tests", '=');

        set = new SortedSet<>();
        set2 = new SortedSet<>();
        ISet<Integer> union;

        // check union between 2 empty sets
        union = set.union(set2);
        printTest("union between empty sets", union.size() == 0);

        // check union btwn elems and empty set
        set.add(1);
        set.add(3);
        set.add(2);
        expectedStr = "(1, 2, 3)";
        union = set.union(set2);
        printTest("union of set and empty set", union.toString().equals(expectedStr));

        // check commutativity of empty set
        union = set2.union(set);
        printTest("commutativity of empty set", union.toString().equals(expectedStr));

        // check union between 2 completely different sets
        set2.add(5);
        set2.add(4);
        union = set.union(set2);
        expectedStr = "(1, 2, 3, 4, 5)";
        printTest("union of completely different sets",
                union.toString().equals(expectedStr) && union.size() == 5);

        // check union between 2 overlapping sets
        set2.add(2);
        set2.add(3);
        union = set.union(set2);
        printTest("union of overlapping sets",
                union.toString().equals(expectedStr) && union.size() == 5);

        // check commutativity
        union = set2.union(set);
        printTest("commutativity of union",
                union.toString().equals(expectedStr) && union.size() == 5);

        // check union between 2 equal sets
        set.add(4);
        set.add(5);
        set2.add(1);
        union = set.union(set2);
        printTest("union of identical sets",
                union.toString().equals(expectedStr) && union.size() == 5);

        // check precondition
        // union cannot take in a null value
        boolean hasFailed = false;
        try {
            set.union(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("union precon", !hasFailed);
    }

    public static void sortedIntersectionTests() {
        printHeader("sorted intersection tests", '=');

        set = new SortedSet<>();
        set2 = new SortedSet<>();
        ISet<Integer> intersection;

        // check intersection between 2 empty sets
        intersection = set.intersection(set2);
        printTest("intersection between empty sets", intersection.size() == 0);

        // check intersection btwn elems and empty set
        set.add(1);
        set.add(3);
        set.add(2);
        intersection = set.intersection(set2);
        printTest("intersection of set and empty set", intersection.size() == 0);

        // check commutativity of empty set
        intersection = set2.intersection(set);
        printTest("commutativity of empty set", intersection.size() == 0);

        // check intersection between 2 completely different sets
        set2.add(5);
        set2.add(4);
        intersection = set.intersection(set2);
        printTest("intersection of completely different sets", intersection.size() == 0);

        // check intersection between 2 overlapping sets
        set2.add(2);
        set2.add(3);
        intersection = set.intersection(set2);
        expectedStr = "(2, 3)";
        printTest("intersection of overlapping sets",
                intersection.toString().equals(expectedStr) && intersection.size() == 2);

        // check commutativity
        intersection = set2.intersection(set);
        printTest("commutativity of intersection",
                intersection.toString().equals(expectedStr) && intersection.size() == 2);

        // check intersection between 2 equal sets
        set.add(4);
        set.add(5);
        set2.add(1);
        intersection = set.intersection(set2);
        expectedStr = "(1, 2, 3, 4, 5)";
        printTest("intersection of completely different sets",
                intersection.toString().equals(expectedStr) && intersection.size() == 5);

        // check precondition
        // intersection cannot take in a null value
        boolean hasFailed = false;
        try {
            set.intersection(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("intersection precon", !hasFailed);
    }

    @SuppressWarnings("unlikely-arg-type")
	public static void sortedEqualsTests() {
        printHeader("sorted equals tests", '=');

        set = new SortedSet<>();
        set2 = new SortedSet<>();

        // check 2 empty sets
        printTest("equals 2 empty sets", set.equals(set2));

        // check empty set and full set
        set.add(1);
        set.add(2);
        set.add(3);
        printTest("equals set and empty set", !set.equals(set2));

        // check commutativity of empty set
        printTest("commutativity of empty set", !set2.equals(set));

        // check completely different sets
        set2.add(4);
        set2.add(5);
        printTest("equals completely different sets", !set.equals(set2));

        // check overlapping sets
        set2.add(2);
        set2.add(3);
        printTest("equals overlapping sets", !set.equals(set2));

        // check equal sets
        set.add(4);
        set.add(5);
        set2.add(1);
        printTest("equals same sets", set.equals(set2));

        // check false if null
        printTest("equals null case", !set.equals(null));

        // check false if wrong type
        ArrayList<Integer> arrList = new ArrayList<>();
        arrList.add(1);
        arrList.add(2);
        arrList.add(3);
        arrList.add(4);
        arrList.add(5);
        printTest("equals wrong type", !set.equals(arrList));
    }

    public static void sortedIteratorTests() {
        printHeader("sorted iterator tests", '=');

        set = new SortedSet<>();
        Iterator<Integer> setIt = set.iterator();

        // check hasNext for empty set
        printTest("iterator empty set hasNext", !setIt.hasNext());

        // check next for empty set
        boolean hasFailed = false;
        try {
            setIt.next();
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("iterator empty set next", !hasFailed);

        // iterator for full set
        set.add(1);
        set.add(3);
        set.add(2);
        setIt = set.iterator();
        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            printTest("iterator full set hasNext", setIt.hasNext());
            int next = setIt.next();
            printTest("iterator full set next", next == i);
            values.add(next);
            setIt.remove();
            printTest("iterator full set remove",
                    set.size() == 3 - i && !set.contains(next));
        }
    }

    public static void sortedRemoveTests() {
        printHeader("sorted remove tests", '=');

        set = new SortedSet<>();

        // removing on empty set returns false
        printTest("remove on empty set", !set.remove(1) && set.size() == 0);

        // removing when item not present
        set.add(1);
        set.add(3);
        set.add(2);
        printTest("remove when not present", !set.remove(4) && set.size() == 3);

        // removing when item is present
        expectedStr = "(1, 3)";
        printTest("remove when present",
                set.remove(2) && set.toString().equals(expectedStr) && set.size() == 2);

        // removing when item already removed
        printTest("remove when present", !set.remove(2));

        // remove until empty
        set.remove(3);
        set.remove(1);
        printTest("remove until empty",
                !set.contains(3) && !set.contains(1) && set.size() == 0);

        // check precondition
        // remove cannot take in null
        boolean hasFailed = false;
        try {
            set.remove(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("check precon", !hasFailed);
    }

    public static void sortedMinAndMaxTests() {
        SortedSet<Integer> intSet = new SortedSet<>();
        SortedSet<String> strSet = new SortedSet<>();
        // empty set, check precondition
        boolean hasFailed = false;
        try {
            intSet.min();
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("min check precon", !hasFailed);
        hasFailed = false;

        try {
            intSet.max();
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("max check precon", !hasFailed);

        // singleton set of Integers
        intSet.add(5);
        printTest("min integer singleton", intSet.min() == 5);
        printTest("max integer singleton", intSet.max() == 5);

        // full set of Integers
        intSet.add(0);
        intSet.add(10);
        printTest("min integer full set", intSet.min() == 0);
        printTest("max integer full set", intSet.max() == 10);

        // singleton set of Strings
        strSet.add("this counts as one");
        printTest("min String singleton", strSet.min().equals("this counts as one"));
        printTest("max String singleton", strSet.max().equals("this counts as one"));

        // full set of Strings
        strSet.add("this counts as too");
        strSet.add("this counts as tree");
        printTest("min String full set", strSet.min().equals("this counts as one"));
        printTest("max String full set", strSet.max().equals("this counts as tree"));

    }

    /*------------------------ Combination Tests --------------------------*/
    public static void sortedConstructorTests() {
        printHeader("sorted constructor tests", '=');
        set2 = new UnsortedSet<>();
        set2.add(4);
        set2.add(1);
        set2.add(3);
        set2.add(2);
        set = new SortedSet<>(set2);
        expectedStr = "(1, 2, 3, 4)";
        printTest("sorted constructor with unsorted arg",
                set.toString().equals(expectedStr));


        set2 = new SortedSet<>();
        set2.add(4);
        set2.add(1);
        set2.add(3);
        set2.add(2);
        set = new SortedSet<>(set2);
        expectedStr = "(1, 2, 3, 4)";
        printTest("sorted constructor with sorted arg",
                set.toString().equals(expectedStr));

        // precondition check
        boolean hasFailed = false;
        try {
            set = new SortedSet<>(null);
            hasFailed = true;
        } catch (Exception ignored) {
        }
        printTest("check precon", !hasFailed);
    }

    public static void comboAddAllTests() {
        printHeader("combo addAll tests", '=');

        set = new SortedSet<>();
        set2 = new UnsortedSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set2.add(2);
        set2.add(4);

        System.out.println(set);
        System.out.println(set2);
        // addAll to sorted remains sorted
        set.addAll(set2);
        System.out.println(set);
        expectedStr = "(1, 2, 3, 4, 5)";
        printTest("addAll to sorted remains sorted", set.toString().equals(expectedStr));

        // addAll to unsorted
        set2.addAll(set);
        printTest("addAll to unsorted",
                set2.contains(1) && set2.contains(2) && set2.contains(3) &&
                        set2.contains(4) && set2.contains(5));
    }

    public static void comboContainsAllTests() {
        printHeader("combo containsAll tests", '=');

        set = new SortedSet<>();
        set2 = new UnsortedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set2.add(1);
        set2.add(2);
        set2.add(3);

        // true on sorted
        printTest("containsAll with sorted calling", set.containsAll(set2));

        // true on unsorted
        printTest("containsAll with unsorted calling", set2.containsAll(set));

        set.remove(1);
        set2.remove(2);
        // false on sorted
        printTest("containsAll false with sorted calling",
                !set.containsAll(set2));

        // false on unsorted
        printTest("containsAll false with unsorted calling",
                !set2.containsAll(set));
    }

    public static void comboDifferenceTests() {
        printHeader("combo difference tests", '=');

        set = new SortedSet<>();
        set2 = new UnsortedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set2.add(4);
        set2.add(5);

        // no overlap sorted calling
        expectedStr = "(1, 2, 3)";
        ISet<Integer> diff = set.difference(set2);
        printTest("difference no overlap with sorted calling",
                diff.toString().equals(expectedStr));

        // no overlap unsorted calling
        diff = set2.difference(set);
        printTest("difference no overlap with unsorted calling",
                diff.containsAll(set2) && diff.size() == 2);

        // some overlap sorted calling
        set2.add(2);
        set2.add(3);
        expectedStr = "(1)";
        diff = set.difference(set2);
        printTest("difference some overlap with sorted calling",
                diff.toString().equals(expectedStr));

        // some overlap unsorted calling
        diff = set2.difference(set);
        printTest("difference some overlap with unsorted calling",
                diff.contains(4) && diff.contains(5) && diff.size() == 2);

        // identical set sorted calling
        set2.add(1);
        set.add(4);
        set.add(5);
        diff = set.difference(set2);
        printTest("difference with sorted calling", diff.size() == 0);

        // identical set unsorted calling
        diff = set2.difference(set);
        printTest("difference with unsorted calling", diff.size() == 0);

        // sorted return type
        diff = set.difference(set2);
        printTest("difference sorted return type", diff instanceof SortedSet);

        // sorted return type
        diff = set2.difference(set);
        printTest("difference unsorted return type", diff instanceof UnsortedSet);
    }

    public static void comboIntersectionTests() {
        printHeader("combo intersection tests", '=');

        set = new SortedSet<>();
        set2 = new UnsortedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set2.add(4);
        set2.add(5);

        // no overlap sorted calling
        expectedStr = "()";
        ISet<Integer> intersection = set.intersection(set2);
        printTest("intersection no overlap with sorted calling",
                intersection.toString().equals(expectedStr));

        // no overlap unsorted calling
        intersection = set2.intersection(set);
        printTest("intersection no overlap with unsorted calling", intersection.size() == 0);

        // some overlap sorted calling
        set2.add(2);
        set2.add(3);
        expectedStr = "(2, 3)";
        intersection = set.intersection(set2);
        printTest("intersection some overlap with sorted calling",
                intersection.toString().equals(expectedStr));

        // some overlap unsorted calling
        intersection = set2.intersection(set);
        printTest("intersection some overlap with unsorted calling",
                intersection.contains(2) && intersection.contains(3) && intersection.size() == 2);

        // identical set sorted calling
        set2.add(1);
        set.add(4);
        set.add(5);
        expectedStr = "(1, 2, 3, 4, 5)";
        intersection = set.intersection(set2);
        printTest("intersection with sorted calling", intersection.toString().equals(expectedStr));

        // identical set unsorted calling
        intersection = set2.intersection(set);
        printTest("intersection with unsorted calling",
                intersection.containsAll(set));

        // sorted return type
        intersection = set.intersection(set2);
        printTest("intersection sorted return type", intersection instanceof SortedSet);

        // sorted return type
        intersection = set2.intersection(set);
        printTest("intersection unsorted return type", intersection instanceof UnsortedSet);
    }

    public static void comboUnionTests() {
        printHeader("combo union tests", '=');

        set = new SortedSet<>();
        set2 = new UnsortedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set2.add(4);
        set2.add(5);

        // no overlap sorted calling
        expectedStr = "(1, 2, 3, 4, 5)";
        ISet<Integer> union = set.union(set2);
        printTest("union no overlap with sorted calling",
                union.toString().equals(expectedStr));

        // no overlap unsorted calling
        union = set2.union(set);
        printTest("union no overlap with unsorted calling",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // some overlap sorted calling
        set2.add(2);
        set2.add(3);
        union = set.union(set2);
        printTest("union some overlap with sorted calling",
                union.toString().equals(expectedStr));

        // some overlap unsorted calling
        union = set2.union(set);
        printTest("union some overlap with unsorted calling",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // identical set sorted calling
        set2.add(1);
        set.add(4);
        set.add(5);
        union = set.union(set2);
        printTest("union with sorted calling", union.toString().equals(expectedStr));

        // identical set unsorted calling
        union = set2.union(set);
        printTest("union with unsorted calling",
                union.containsAll(set) && union.containsAll(set2) && union.size() == 5);

        // sorted return type
        union = set.union(set2);
        printTest("union sorted return type", union instanceof SortedSet);

        // sorted return type
        union = set2.union(set);
        printTest("union unsorted return type", union instanceof UnsortedSet);
    }

    public static void comboEqualsTests() {
        printHeader("combo equals tests", '=');

        set = new SortedSet<>();
        set2 = new UnsortedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set2.add(4);
        set2.add(5);

        // no overlap sorted calling
        printTest("equals no overlap with sorted calling",
                !set.equals(set2));

        // no overlap unsorted calling
        printTest("equals no overlap with unsorted calling",
                !set2.equals(set));

        // some overlap sorted calling
        set2.add(2);
        set2.add(3);
        printTest("equals some overlap with sorted calling",
                !set.equals(set2));

        // some overlap unsorted calling
        printTest("equals some overlap with unsorted calling",
                !set2.equals(set));

        // equal sorted calling
        set2.add(1);
        set.add(4);
        set.add(5);
        printTest("equals with sorted calling",
                set.equals(set2));

        // equal unsorted calling
        printTest("equals with unsorted calling",
                set2.equals(set));
    }

//    /*---------------------------- Timing Tests ---------------------------*/
//    private static Stopwatch sw = new Stopwatch();
//    // add any necessary variables here
//
//    // tests used to check time complexity of methods. TIME COMPLEXITY OF
//    // THESE TEST METHODS THEMSELVES MUST BE TAKEN INTO ACCOUNT.
//    // This tests should be O(N), so if the time for increases at the rate 
//    of O(N^2), your method itself is likely O(N).
//    // Number of tests for each method is arbitrary. Feel free to change them
//    // if they take too long. It is recommended to double N each time.
//
//
//    // rename with real method and test amounts
//    private static void stubTimingTests() {
//        printHeader("stub timing tests", ':');
//        timingTestStub(5_000);
//        timingTestStub(10_000);
//        timingTestStub(20_000);
//        timingTestStub(40_000);
//    }
//
//    // This method should be O(N) where N is numTests. Take this into account 
//    // while determining time complexity of equals method
//    private static void timingTestStub(int numTests) {
//
//        double totalSeconds = 0;
//
//        for (int test = 0; test < numTests; test++) {
//            // add necessary variables here
//
//            sw.start();
//
//            // add method to test
//
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
        System.out.println(String.format("%s Test %02d: %s",
                status, numTests, tested));
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

        printHeader("FINAL RESULTS", '@');
        System.out.println("Successful tests: " + numPassed + " / " + numTests);
        if (numPassed == numTests) {
            System.out.println();
            System.out.println("^(;,;)^ Congratulations! You passed all the " +
                    "tests you ran!");

        }
        System.out.println(getRandomQuote());
    }

    // This one's just for fun. Returns a random iconic Mike Scott quote.
    private static String getRandomQuote() {
        final int NUM_QUOTES = 10;
        String[] quotes = new String[NUM_QUOTES];
        // Adjust as needed with more quotes
        quotes[0] = "\nProgram testing can be used to show the presence " +
                "\nof bugs, but never to show their absence! " +
                "\n    - Edsger Dijkstra" +
                "\n         - Mike Scott";

        quotes[1] = "\nTradeoffs, tradeoffs, tradeoffs!" +
                "\n    - Mike Scott";

        quotes[2] = "\nBut it's easy to code! It's just a few lines... " +
                "\n    - Mike Scott";

        quotes[3] = "\nTrees? What are \"trees\"? Do you mean those " +
                "\ngreen things outside, with the leaves on them?" +
                "\n    - Mike Scott";

        quotes[4] = "\nUno Reverse card! What DOES that mean?" +
                "\n    - Mike Scott";

        quotes[5] = "\nI don't know who said that college would be FUN. " +
                "\nMaybe those business and liberal arts majors..." +
                "\n    - Mike Scott";

        quotes[6] = "\nBase case! SOLVED! Pop the champagne corks! " +
                "\nPop pop pop pop pop pop!" +
                "\n    - Mike Scott";

        quotes[7] = "\nGack!" +
                "\n    - Mike Scott";

        quotes[8] = "\nWe work on the FUN side of the wall of abstraction" +
                "\n    - Mike Scott";

        quotes[9] = "\nI think of iterators as these robot spiders..." +
                "\n    - Mike Scott";


        int chosen = (int) (Math.random() * NUM_QUOTES);
        return quotes[chosen];
    }
}
