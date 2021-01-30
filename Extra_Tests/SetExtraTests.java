package Extra_Tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import Other.Stopwatch;
import Set_Classes.ISet;
import Set_Classes.SortedSet;
import Set_Classes.UnsortedSet;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 */

public class SetExtraTests {

    public static void main(String[] args) {

        ISet<String> s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        // test 1
        showTestResults(s1.contains("A"), 1, "add and contains methods SortedSet");

        // test 2
        s1.remove("A");
        showTestResults(!s1.contains("A"), 2, "remove method UnsortedSet");

        // test 3
        showTestResults(s1.size() == 2, 3, "size method UnsortedSet");

        ISet<String> s2 = new UnsortedSet<>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        // test 4
        showTestResults(s2.containsAll(s1), 4, "containsAll method UnsortedSet");

        // test 5
        showTestResults(!s1.containsAll(s2), 5, "containsAll method UnsortedSet");

        // test 6
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<>();
        expected.add("A");
        showTestResults(s3.equals(expected), 6, "difference and equals methods UnsortedSet");

        // test 7
        s3 = s2.union(s1);
        expected.add("B");
        expected.add("C");
        showTestResults(s3.equals(expected), 7, "union and equals methods UnsortedSet");

        // test 8
        s3 = s2.intersection(s1);
        expected.remove("A");
        showTestResults(s3.equals(expected), 8, "intersection and equals methods UnsortedSet");

        // sorted sets
        s1 = new SortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        // test 9
        showTestResults(s1.contains("A"), 9, "add and contains methods SortedSet");

        // test 10
        s1.remove("A");
        showTestResults(!s1.contains("A"), 10, "remove and contains methods SortedSet");

        // test 11
        showTestResults(s1.size() == 2, 11, "size method SortedSet");

        s2 = new SortedSet<>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        // test 12
        showTestResults(s2.containsAll(s1), 12, "containsAll method SortedSet");

        // test 13
        showTestResults(!s1.containsAll(s2), 13, "containsAll method SortedSet");

        // test 14
        s3 = s2.difference(s1);
        expected = new SortedSet<>();
        expected.add("A");
        showTestResults(s3.equals(expected), 14, "difference and equals methods SortedSet");

        // test 15
        s3 = s1.difference(s2);
        expected = new SortedSet<>();
        showTestResults(s3.equals(expected), 15, "difference and equals methods SortedSet");

        // test 16
        s3 = s1.union(s2);
        expected = new SortedSet<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        showTestResults(s3.equals(expected), 16, "union and equals methods SortedSet");

        // test 17
        s3 = s1.intersection(s2);
        expected.remove("A");
        showTestResults(s3.equals(expected), 17, "intersection and equals methods SortedSet");

        // test 18
        s1.add("A");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while (good && it1.hasNext()) {
            good = it1.next().equals(it2.next());
        }
        showTestResults(good, 18, "iterator and add methods SortedSet");

        // test 19
        s1 = new UnsortedSet<>();
        UnsortedSet<Integer> si1 = new UnsortedSet<>();
        showTestResults(si1.equals(s1), 19, "equals methods UnsortedSet");

        // test 20
        s1.add("is");
        s1.add("a");
        si1.add(12);
        si1.add(13);
        si1.add(12);
        showTestResults(!si1.equals(s1), 20, "equals methods UnsortedSet");

        // test 21
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(12);
        ar.add(13);
        showTestResults(!si1.equals(ar), 21, "equals methods UnsortedSet");

        // test 22
        Object obj1 = s1;
        s2 = new UnsortedSet<>();
        s2.add("a");
        s2.add("is");
        Object obj2 = s2;
        showTestResults(obj1.equals(obj2), 22, "equals methods UnsortedSet");

        // test 23
        s1 = new SortedSet<>();
        s1.add("A");
        s1.add("A");
        s1.add("B");
        ISet<Integer> ss2 = new SortedSet<>();
        ss2.add(12);
        ss2.add(15);
        ss2.add(12);
        ss2.add(15);
        showTestResults(!s1.equals(ss2), 23, "equals methods SortedSet - different types");

        // test 24
        showTestResults(!ss2.equals(s1), 24, "equals methods SortedSet - different types");

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
         try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         }
         catch(Exception e) {
         System.out.println("Unable to change look and feel");
         }
         Scanner sc = new Scanner(System.in);
         String response = "";
         do {
         largeTest();
         System.out.print("Another file? Enter y to do another file: ");
         response = sc.next();
         } while( response != null && response.length() > 0
         && response.substring(0,1).equalsIgnoreCase("y") );

    }

    // print out results of test
    private static void showTestResults(boolean passed, int testNumber, String testDescription) {
        if (passed) {
            System.out.print("Passed test ");
        } else {
            System.out.print("Failed test ");
        }
        System.out.println(testNumber + ": " + testDescription);
    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out
                .println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
        keyboard.close();
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set)
                System.out.println(o);
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evalutatin the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}