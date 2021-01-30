package Extra_Tests;
import java.util.ArrayList;
import java.util.Iterator;

import Linked_List.IList;
import Linked_List.LinkedList;

public class AlexLinkedListTester {

    private static int testsPassed = 0;

    public static void main(String[] args) {
        int test = 0;
        LinkedList<Object> list = new LinkedList<>();
        ArrayList<Object> exp = new ArrayList<>();
        exp.add("A");
        exp.add("B");
        exp.add("C");
        exp.add(1);


        // add
        System.out.println("\nLinkedList Tests:");
        System.out.println("\nadd() Tests:");

        // 0-3
        for (int i = 0; i < exp.size(); i++) {
            list.add(exp.get(i));
            printTest(test++, "add", list.get(i).equals(exp.get(i)));
        }

        // add + size
        // 4
        printTest(test++, "add size", list.size() == exp.size());

        //insert
        System.out.println("\ninsert() Tests:");

        // 5
        exp.add(0, "D");
        list.insert(0, "D");
        printTest(test++, "insert front", list.get(0).equals(exp.get(0)));

        // 6
        exp.add(2, "E");
        list.insert(2, "E");
        printTest(test++, "insert mid", list.get(2).equals(exp.get(2)));

        // 7
        exp.add(4, "F");
        list.insert(4, "F");
        printTest(test++, "insert mid", list.get(4).equals(exp.get(4)));

        // 8
        exp.add(7, 2);
        list.insert(7, 2);
        printTest(test++, "insert end", list.get(7).equals(exp.get(7)));


        // 9
        printTest(test++, "insert size", list.size() == exp.size());

        // set
        System.out.println("\nset() Tests:");

        // 10
        exp.set(0, "G");
        list.set(0, "G");
        printTest(test++, "set front", list.get(0).equals(exp.get(0)));

        // 11
        exp.set(3, "H");
        list.set(3, "H");
        printTest(test++, "set mid", list.get(3).equals(exp.get(3)));

        // 12
        exp.set(4, "I");
        list.set(4, "I");
        printTest(test++, "set mid", list.get(4).equals(exp.get(4)));

        // 13
        exp.set(7, "J");
        list.set(7, "J");
        printTest(test++, "set end", list.get(7).equals(exp.get(7)));

        // remove by pos
        System.out.println("\nremove(pos) Tests:");

        // 14
        Object tempObj = list.remove(0);
        Object tempExp = exp.remove(0);
        printTest(test++, "remove at front", list.toString().equals(exp.toString()));
        list.insert(0, tempObj);
        exp.add(0, tempExp);

        // 15
        tempObj = list.remove(3);
        tempExp = exp.remove(3);
        printTest(test++, "remove at mid", list.toString().equals(exp.toString()));
        list.insert(3, tempObj);
        exp.add(3, tempExp);

        // 16
        tempObj = list.remove(4);
        tempExp = exp.remove(4);
        printTest(test++, "remove at mid", list.toString().equals(exp.toString()));
        list.insert(4, tempObj);
        exp.add(4, tempExp);

        // 17
        tempObj = list.remove(7);
        tempExp = exp.remove(7);
        printTest(test++, "remove at end", list.toString().equals(exp.toString()));
        list.insert(7, tempObj);
        exp.add(tempExp);

        // 18
        printTest(test++, "remove returns value", list.remove(1).equals("A"));
        list.insert(1, "A");

        // remove by value
        System.out.println("\nremove(value) Tests:");

        // 19
        list.remove("G");
        exp.remove("G");
        printTest(test++, "remove 1st val", list.toString().equals(exp.toString()));
        list.insert(0, "G");
        exp.add(0, "G");

        // 20
        list.remove("H");
        exp.remove("H");
        printTest(test++, "remove mid val", list.toString().equals(exp.toString()));
        list.insert(3, "H");
        exp.add(3, "H");

        // 21
        list.remove("I");
        exp.remove("I");
        printTest(test++, "remove mid val", list.toString().equals(exp.toString()));
        list.insert(4, "I");
        exp.add(4, "I");

        // 22
        list.remove("J");
        exp.remove("J");
        printTest(test++, "remove end val", list.toString().equals(exp.toString()));
        list.insert(7, tempObj);
        exp.add(7, tempExp);

        // 23
        printTest(test++, "remove returns true appropriately", list.remove("I"));
        list.insert(4, "I");

        // 24
        printTest(test++, "remove returns false appropriately",
                !list.remove("GAEHIC"));

        //getSubList
        System.out.println("\ngetSubList() Tests:");

        // 25
        IList<Object> subList = list.getSubList(2, 4);
        printTest(test++, "getSubList from front",
                subList.toString().equals("[E, H]"));

        // 26
        subList = list.getSubList(3, 5);
        printTest(test++, "getSubList from end",
                subList.toString().equals("[H, I]"));

        // 27
        subList = list.getSubList(0, 0);
        printTest(test++, "empty subList", subList.toString().equals("[]"));

        // 28
        subList = list.getSubList(0, list.size());
        printTest(test++, "full subList", subList.toString().equals(list.toString()));

        // indexOf
        System.out.println("\nindexOf(val) Tests:");

        // 29
        printTest(test++, "indexOf", list.indexOf("I") == 4);

        // 30
        printTest(test++, "indexOf at end", list.indexOf("J") == 7);

        // 31
        printTest(test++, "indexOf at start", list.indexOf("G") == 0);

        // 32
        printTest(test++, "indexOf not in list", list.indexOf("O") == -1);

        // indexOf with start pos
        System.out.println("\nindexOf(pos, val) Tests:");

        // 33
        printTest(test++, "indexOf w/ start pos", list.indexOf("G", 1) == -1);

        // 34
        printTest(test++, "indexOf w/ start pos", list.indexOf("E", 2) == 2);

        // 35
        printTest(test++, "indexOf w/ start pos",
                list.indexOf("J", list.size() - 1) == 7);

        // makeEmpty
        System.out.println("\nmakeEmpty() Tests:");

        // 36
        subList.makeEmpty();
        printTest(test++, "makeEmpty", subList.toString().equals("[]"));

        // 37
        subList.makeEmpty();
        printTest(test++, "makeEmpty while empty",
                subList.toString().equals("[]"));

        // 38
        subList = list.getSubList(2, 5);
        subList.makeEmpty();
        printTest(test++, "makeEmpty subList",
                subList.toString().equals("[]"));

        //removeRange
        System.out.println("\nremoveRange() Tests:");

        // 39
        subList = list.getSubList(0, list.size());
        subList.removeRange(0, 3);
        printTest(test++, "removeRange at start",
                subList.toString().equals("[H, I, C, 1, J]"));

        // 40
        subList = list.getSubList(0, list.size());
        subList.removeRange(2, 4);
        printTest(test++, "removeRange in mid",
                subList.toString().equals("[G, A, I, C, 1, J]"));

        // 41
        subList = list.getSubList(0, list.size());
        subList.removeRange(4, 6);
        printTest(test++, "removeRange in mid",
                subList.toString().equals("[G, A, E, H, 1, J]"));

        // 42
        subList = list.getSubList(0, list.size());
        subList.removeRange(6, list.size());
        printTest(test++, "removeRange at end",
                subList.toString().equals("[G, A, E, H, I, C]"));

        // 43
        subList.removeRange(0, subList.size());
        printTest(test++, "removeRange everything",
                subList.toString().equals("[]"));

        // 44
        subList = list.getSubList(0, list.size());
        subList.removeRange(0, 0);
        printTest(test++, "removeRange nothing",
                subList.toString().equals("[G, A, E, H, I, C, 1, J]"));

        // addFirst
        System.out.println("\naddFirst() Tests:");

        // 45
        list.addFirst("K");
        printTest(test++, "addFirst", list.indexOf("K") == 0);

        // 46
        list.addFirst("L");
        printTest(test++, "addFirst", list.indexOf("L") == 0);

        // 47
        list.addFirst("M");
        printTest(test++, "addFirst", list.indexOf("M") == 0);

        // addLast
        System.out.println("\naddLast() Tests:");

        // 48
        list.addLast("N");
        printTest(test++, "addLast", list.indexOf("N") == list.size() - 1);

        // 49
        list.addLast("O");
        printTest(test++, "addLast", list.indexOf("O") == list.size() - 1);

        // 50
        list.addLast("P");
        printTest(test++, "addLast", list.indexOf("P") == list.size() - 1);

        // removeFirst
        System.out.println("\nremoveFirst() Tests:");

        // 51
        list.removeFirst();
        printTest(test++, "removeFirst", list.indexOf("M") == -1);

        // 52
        list.removeFirst();
        printTest(test++, "removeFirst", list.indexOf("L") == -1);

        // 53
        list.removeFirst();
        printTest(test++, "removeFirst", list.indexOf("K") == -1);

        // removeLast
        System.out.println("\nremoveLast() Tests:");

        // 54
        list.removeLast();
        printTest(test++, "removeLast", list.indexOf("P") == -1);

        // 55
        list.removeLast();
        printTest(test++, "removeLast", list.indexOf("O") == -1);

        list.removeLast();
        // 56
        printTest(test++, "removeLast", list.indexOf("N") == -1);

        // equals
        System.out.println("\nequals() Tests:");

        // 57
        printTest(test++, "equals true", list.equals(subList));

        // 58
        subList.remove(0);
        subList.insert(0, "M");
        printTest(test++, "equals wrong front", !list.equals(subList));

        // 59
        subList = list.getSubList(0, list.size());
        subList.remove(7);
        subList.insert(7, "N");
        printTest(test++, "equals wrong back", !list.equals(subList));

        // 60
        subList.removeRange(5, list.size());
        printTest(test++, "equals wrong size", !list.equals(subList));

        // 61
        printTest(test++, "equals wrong type", !list.equals(exp));

        // iterator methods
        System.out.println("\nIterator Tests:");

        // hasNext and next
        System.out.println("\nhasNext() and next() Tests:");

        // 62-77
        subList = list.getSubList(0, list.size());
        Iterator<Object> listIter = subList.iterator();
        for (int i = 0; i < list.size() - 1; i++) {
            printTest(test++, "Iterator: hasNext", listIter.hasNext());
            printTest(test++, "Iterator: next",
                    listIter.next().equals(subList.get(i)));
        }
        printTest(test++, "Iterator: hasNext", listIter.hasNext());
        printTest(test++, "Iterator: next",
                listIter.next().equals(list.get(list.size() - 1)));

        // 78
        printTest(test++, "Iterator: hasNext false", !listIter.hasNext());

        // remove
        System.out.println("\nremove() Tests:");

        // 79
        subList = list.getSubList(0, list.size());
        listIter = subList.iterator();
        listIter.next();
        listIter.remove();
        printTest(test++, "Iterator: remove first",
                subList.toString().equals("[A, E, H, I, C, 1, J]"));

        // 80
        listIter.next();
        listIter.next();
        listIter.next();
        listIter.remove();
        printTest(test++, "Iterator: remove mid",
                subList.toString().equals("[A, E, I, C, 1, J]"));

        // 81
        listIter.next();
        listIter.next();
        listIter.next();
        listIter.next();
        listIter.remove();
        printTest(test++, "Iterator: remove last",
                subList.toString().equals("[A, E, I, C, 1]"));

        System.out.println("\nNumber of tests passed: " + testsPassed + "/" + test);

    }

    // Helper method that prints test results. Code from my NameSurfer in a3
    private static void printTest(int test, String tested, boolean passed) {
        System.out.print("Test " + test + ", " + tested + ": ");
        if (passed) {
            System.out.println("Passed!");
            testsPassed++;
        } else {
            System.out.println("!!!!!!!!FAILED!!!!!!!!");
        }
    }
}


