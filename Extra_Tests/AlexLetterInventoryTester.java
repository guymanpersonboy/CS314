package Extra_Tests;

import java.util.ArrayList;
import java.util.Random;

import Anagram_Solver.LetterInventory;
import Other.Stopwatch;

/*
    This is a battery of tests made by Alex Hanlin for assignment a7: Anagrams
    Comment out methods as necessary when using.
    Message me on Piazza or Discord (Gluethulhu#0117) if you find any bugs or 
    errors! If you do so, please use a test's name when calling attention to it,
    as number is only tracked at runtime.
    
    Time complexity tests require Prof. Mike Scott's Stopwatch.java class to 
    function.
 */
public class AlexLetterInventoryTester {
    private static int numTests = 0; // number of total tests
    private static int numPassed = 0; // number of passed tests

    public static void main(String[] args) {
        constructorTests();
        getTests();
        sizeTests();
        isEmptyTests();
        toStringTests();
        addTests();
        subtractTests();
        equalsTests();
        finalResults();

        // Recommended to run this separately on CS lab machines.
        // read method header for timingTests for more detail
        // timingTests();
    }


    /*----------------------- LetterInventory Tests -----------------------*/
    private static LetterInventory inv;
    private static LetterInventory other;
    private static final String nonLetters = "1234567890`~!@#$%^&*()-=_+[]{};" +
            "':|/?.>,<\\\n\b\t";

    // Tests for the constructor of LetterInventory.
    // Constructor tests and get tests are STRONGLY dependent on each other to 
    // function. Problems with one generally imply problems with the other.
    private static void constructorTests() {
        // ----- constructor tests -----
        printHeader("constructor", '=');

        // empty inventory - all frequencies should be zero
        inv = new LetterInventory("");
        boolean hasFailed = false;
        char ch = 'a';
        while (ch <= 'z' && !hasFailed) {
            if (inv.get(ch) != 0) {
                hasFailed = true;
            }
            ch++;
        }
        printTest("constructor empty string", !hasFailed);
        hasFailed = false;

        // each letter - makes sure that the program sees each possible letter.
        inv = new LetterInventory(
                "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ");
        ch = 'a';
        while (ch <= 'z' && !hasFailed) {
            if (inv.get(ch) != 2) {
                hasFailed = true;
            }
            ch++;
        }
        printTest("constructor all letters and cases", !hasFailed);
        hasFailed = false;

        // non-letters
        inv = new LetterInventory(nonLetters);
        ch = 'a';
        while (ch <= 'z' && !hasFailed) {
            if (inv.get(ch) != 0) {
                hasFailed = true;
            }
            ch++;
        }
        printTest("constructor all non-letters", !hasFailed);
        hasFailed = false;

        // precondition check for constructor - should throw exception on 
        // null parameter string
        try {
            inv = new LetterInventory(null);
            hasFailed = true;
        } catch (Exception ignored) {
            // if exception thrown, isFailed already false (test is passed)
        }
        printTest("constructor precondition", !hasFailed);
        hasFailed = false;
    }

    // Tests for the get method of LetterInventory
    private static void getTests() {
        printHeader("get", '=');

        // ignore lowercase - inv takes in 'G'
        inv = new LetterInventory("GluethulHu");
        int expected = 1;
        int actual = inv.get('g');
        printTest("get ignore lowercase", actual == expected);

        // ignore uppercase - inv took in 't'
        actual = inv.get('T');
        printTest("get ignore uppercase", actual == expected);

        // mixed cases - inv took in 'h' and 'H'
        expected = 2;
        actual = inv.get('H');
        printTest("get mixed cases", actual == expected);

        // some non-letter characters and spaces, some letter characters. 
        // Samples a few letters to check, but isn't exhaustive
        inv = new LetterInventory("~=# M4ke Vide0 Game2! Jo1n EGaDS @ UT! #=~");
        printTest("get sentence with junk characters",
                (inv.get('m') == 2) && (inv.get('e') == 4 && (inv.get('v') == 1)));

        // precondition check for get - should throw exception on 
        // any non-letter characters
        boolean hasFailed = false;
        for (char nonLetter : nonLetters.toCharArray()) {
            try {
                inv.get(nonLetter);
                hasFailed = true;
            } catch (Exception e) {
                // if exception thrown, isFailed already false (test is passed)
            }
        }
        printTest("get precondition", !hasFailed);
    }

    // Tests for the size method of LetterInventory
    private static void sizeTests() {
        printHeader("size tests", '=');

        // empty string
        LetterInventory inv = new LetterInventory("");
        int expected = 0;
        int actual = inv.size();
        printTest("size empty string", actual == expected);

        // single word
        inv = new LetterInventory("HornetIsNotVoid");
        expected = 15;
        actual = inv.size();
        printTest("size one word", actual == expected);

        // sentence with numbers and non-letter characters
        inv = new LetterInventory("You'll g3t th3s3 m3thods work1ng :]");
        expected = 22;
        actual = inv.size();
        printTest("size sentence with non-letters", actual == expected);
    }

    // Tests for the isEmpty method of LetterInventory
    private static void isEmptyTests() {
        printHeader("isEmpty tests", '=');
        
        // isEmpty with no input - should return true
        inv = new LetterInventory("");
        printTest("isEmpty empty string", inv.isEmpty());

        // isEmpty with non-letters only - should return true
        inv = new LetterInventory(nonLetters);
        printTest("isEmpty non-letters only", inv.isEmpty());

        // isEmpty with one letter - should return false
        inv = new LetterInventory("A");
        printTest("isEmpty single letter", !inv.isEmpty());

        // isEmpty with one letter - should return false
        inv = new LetterInventory("Gluethulhu");
        printTest("isEmpty single word", !inv.isEmpty());

        // isEmpty with multi-word phrase + non-letters
        inv = new LetterInventory("I wrote this test at 1:58 AM. Help.");
        printTest("isEmpty single word", !inv.isEmpty());
    }

    // Tests for the toString method of LetterInventory
    private static void toStringTests() {
        printHeader("toString tests", '=');

        // empty string - should return an empty string
        inv = new LetterInventory("");
        String expected = "";
        String actual = inv.toString();
        printTest("toString empty string", actual.equals(expected));

        // string with all non-letters - should return an empty string
        inv = new LetterInventory(nonLetters);
        printTest("toString all non-letters", actual.equals(expected));

        // single word with varied case
        inv = new LetterInventory("FlourlessChocolateCake");
        expected = "aaccceeefhklllooorsstu";
        actual = inv.toString();
        printTest("toString one word varied case", actual.equals(expected));

        // phrase with non-letters - should ignore non-letters
        inv = new LetterInventory("-=# Play Gem Setter by 1f1n1ty! #=-");
        expected = "abeeefglmnprstttyyy";
        actual = inv.toString();
        printTest("toString words and non-letters",
                actual.equals(expected));

        // phrase with every letter in the alphabet (AKA pangram)
        inv = new LetterInventory("Sphinx of black quartz, judge my vow.");
        expected = "aabcdefghijklmnoopqrstuuvwxyz";
        actual = inv.toString();
        printTest("toString pangram", actual.equals(expected));
    }

    // Tests for the add method of LetterInventory. Uses size and toString 
    // methods to function properly
    private static void addTests() {
        printHeader("add tests", '=');

        // adding empty LetterInventory to empty LetterInventory 
        inv = new LetterInventory("");
        other = new LetterInventory("");
        String expectedSumStr = "";
        runAddTest(inv, other, expectedSumStr, "empty + empty");

        // adding empty LetterInventory to filled LetterInventory
        inv = new LetterInventory("Check your time complexities!");
        expectedSumStr = "ccceeeehiiiklmmooprsttuxy";
        runAddTest(inv, other, expectedSumStr, "empty + filled");

        // adding filled LetterInventory to empty LetterInventory
        runAddTest(other, inv, expectedSumStr, "filled + empty");

        // adding two filled LetterInventories with no overlap
        inv = new LetterInventory("abcdefghijklm");
        other = new LetterInventory("nopqrstuvwxyz");
        expectedSumStr = "abcdefghijklmnopqrstuvwxyz";
        runAddTest(inv, other, expectedSumStr, "with no overlap");

        // adding two filled LetterInventories with overlap
        inv = new LetterInventory("I wrote this during a Jerma985 stream.");
        other = new LetterInventory("Or maybe it was Vinesauce");
        expectedSumStr = "aaaaaabcdeeeeeeghiiiiijmmmnnoorrrrrssssttttuuvwwy";
        runAddTest(inv, other, expectedSumStr, "with overlap");

        // adding a LetterInventory to itself
        expectedSumStr = "aaaaaaddeeeeeegghhiiiiiijjmmmmnnoorrrrrrrrssssttttttuuww";
        runAddTest(inv, inv, expectedSumStr, "to self");

        // check precondition - add should throw exception when passed null
        boolean hasFailed = false;
        try {
            inv.add(null);
            hasFailed = true;
        } catch (Exception ignored) {
            // hasFailed false by default
        }
        printTest("add precondition", !hasFailed);
    }

    // Runs an accuracy test, a size test, and a postcondition test for each 
    // add trial. 
    // left: the calling object of the add method
    // right: the parameterized object of the add method
    // expectedSumStr: the output expected from left.add(right).toString()
    // tested: partial description of the test to print
    private static void runAddTest(LetterInventory left,
                                   LetterInventory right,
                                   String expectedSumStr,
                                   String tested) {

        String expectedLeftStr = left.toString();
        String expectedRightStr = right.toString();
        LetterInventory sum = left.add(right);
        String actualSumStr = sum.toString();

        // accuracy test
        printTest("add " + tested, actualSumStr.equals(expectedSumStr));

        // size test
        int expectedSumSize = expectedSumStr.length();
        printTest("add size " + tested, expectedSumSize == sum.size());

        // postcondition test - neither left or right are altered
        printTest("add postcondition " + tested,
                (left.toString().equals(expectedLeftStr) &&
                        right.toString().equals(expectedRightStr)));
    }

    // Tests for the subtract method of LetterInventory. Uses size and toString 
    // methods to function properly
    private static void subtractTests() {
        printHeader("subtract tests", '=');

        // subtracting empty LetterInventory from empty LetterInventory 
        inv = new LetterInventory("");
        other = new LetterInventory("");
        String expectedDiffStr = "";
        runSubtractTest(inv, other, expectedDiffStr, "empty - empty");

        // subtracting empty LetterInventory from filled LetterInventory
        inv = new LetterInventory("the giant enemy spider");
        expectedDiffStr = "adeeeeghiimnnprstty";
        runSubtractTest(inv, other, expectedDiffStr, "filled - empty");

        // subtracting two filled LetterInventories
        other = new LetterInventory("the enemy spider");
        expectedDiffStr = "agint";
        runSubtractTest(inv, other, expectedDiffStr, "filled - filled");

        // subtracting a letterInventory from itself
        inv = new LetterInventory("Sphinx of black quartz, judge my vow.");
        // it's a cool pangram, okay?
        expectedDiffStr = "";
        runSubtractTest(inv, inv, expectedDiffStr, "from self");

        // invalid subtraction - results in negative frequencies
        inv = new LetterInventory("What's this gonna return?");
        other = new LetterInventory("null, duh.");
        printTest("invalid subtraction", inv.subtract(other) == null);

        // check precondition - subtract should throw exception when passed null
        boolean hasFailed = false;
        try {
            inv.add(null);
            hasFailed = true;
        } catch (Exception ignored) {
            // hasFailed false by default
        }
        printTest("subtract precondition", !hasFailed);
    }

    // Runs an accuracy test, a size test, and a postcondition test for each 
    // subtract trial. No invalid (null returning) subtractions
    // left: the calling object of the subtract method
    // right: the parameterized object of the subtract method
    // expectedDiffStr: the output expected from left.subtract(right).toString()
    // tested: description of test to print
    private static void runSubtractTest(LetterInventory left,
                                        LetterInventory right,
                                        String expectedDiffStr,
                                        String tested) {

        String expectedLeftStr = left.toString();
        String expectedRightStr = right.toString();
        LetterInventory diff = left.subtract(right);
        String actualDiffStr = diff.toString();

        // accuracy test
        printTest("subtract " + tested, actualDiffStr.equals(expectedDiffStr));

        // size test
        int expectedSumSize = expectedDiffStr.length();
        printTest("subtract size " + tested, expectedSumSize == diff.size());

        // postcondition test - neither left or right are altered
        printTest("subtract postcondition " + tested,
                (left.toString().equals(expectedLeftStr) &&
                        right.toString().equals(expectedRightStr)));
    }

    // Tests for the equals method of LetterInventory
    private static void equalsTests() {
        printHeader("equals tests", '=');

        // two empty LetterInventories - should return true
        inv = new LetterInventory("");
        other = new LetterInventory("");
        printTest("equals empty and empty", inv.equals(other));

        // one empty, one full - should return false
        inv = new LetterInventory("This has characters in it");
        printTest("equals empty and full", !inv.equals(other));

        // same phrase -should return true
        other = new LetterInventory("This has characters in it");
        printTest("equals same phrase", inv.equals(other));

        // same phrase jumbled - should return true
        other = new LetterInventory("it is characters has thin");
        printTest("equals same phrase", inv.equals(other));

        // one character difference - should return false
        other = new LetterInventory("Thic has characters in it");
        printTest("equals one different character", !inv.equals(other));

        // different phrases = should return false
        other = new LetterInventory("This is a different phrase entirely");
        printTest("equals different phrase", !inv.equals(other));

        // wrong datatype
        printTest("equals wrong datatype", !inv.equals(new ArrayList()));
    }

    /*---------------------------- Timing Tests ---------------------------*/
    private static Stopwatch sw = new Stopwatch();
    private final static int LETTERS_IN_ALPHABET = 26;
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    // tests used to check time complexity of methods. TIME COMPLEXITY OF
    // THESE TEST METHODS THEMSELVES MUST BE TAKEN INTO ACCOUNT.
    // All of these tests should be O(N), so if the time for getTimingTests 
    // increases at the rate of O(N^2), your add method itself is likely O(N).
    // Number of tests for each method is arbitrary. Feel free to change them
    // if they take too long. It is recommended to double N each time.
    private static void timingTests() {
        printHeader("timing tests", '#');

        getTimingTests();
        sizeTimingTests();
        isEmptyTimingTests();
        toStringTimingTests();
        addTimingTests();
        subtractTimingTests();
        equalsTimingTests();
    }

    private static void getTimingTests() {
        printHeader("get timing tests", ':');
        performGetTimingTest(5_000);
        performGetTimingTest(10_000);
        performGetTimingTest(20_000);
        performGetTimingTest(40_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performGetTimingTest(int numTests) {

        Random rand = new Random();
        long totalNanoseconds = 0;
        inv = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // retrieves a pseudorandom letter for variety
            char ch = alphabet.charAt(rand.nextInt(LETTERS_IN_ALPHABET));
            // times the get method alone so other operations don't throw time off
            sw.start();
            inv.get(ch);
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    private static void sizeTimingTests() {
        printHeader("size timing tests", ':');
        performSizeTimingTest(5_000);
        performSizeTimingTest(10_000);
        performSizeTimingTest(20_000);
        performSizeTimingTest(40_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performSizeTimingTest(int numTests) {

        long totalNanoseconds = 0;
        inv = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // times the size method alone so other operations don't throw time off
            sw.start();
            inv.size();
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    private static void isEmptyTimingTests() {
        printHeader("isEmpty timing tests", ':');
        performIsEmptyTimingTest(5_000);
        performIsEmptyTimingTest(10_000);
        performIsEmptyTimingTest(20_000);
        performIsEmptyTimingTest(40_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performIsEmptyTimingTest(int numTests) {

        long totalNanoseconds = 0;
        inv = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // times the isEmpty method alone so other operations don't throw time off
            sw.start();
            inv.isEmpty();
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    private static void toStringTimingTests() {
        printHeader("toString timing tests", ':');
        performToStringTimingTest(1_000);
        performToStringTimingTest(2_000);
        performToStringTimingTest(4_000);
        performToStringTimingTest(8_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performToStringTimingTest(int numTests) {

        long totalNanoseconds = 0;
        inv = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // times the toString method alone so other operations don't throw time off
            sw.start();
            inv.toString();
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    private static void addTimingTests() {
        printHeader("add timing tests", ':');
        performAddTimingTest(1_000);
        performAddTimingTest(2_000);
        performAddTimingTest(4_000);
        performAddTimingTest(8_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performAddTimingTest(int numTests) {

        long totalNanoseconds = 0;
        inv = new LetterInventory(alphabet);
        other = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // times the add method alone so other operations don't throw time off
            sw.start();
            inv.add(other);
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
            if (test % 100 == 0) {
                // resets inv every 100 tests just to make sure it doesn't 
                // get too big
                inv = new LetterInventory(alphabet);
            }
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    private static void subtractTimingTests() {
        printHeader("subtract timing tests", ':');
        performSubtractTimingTest(1_000);
        performSubtractTimingTest(2_000);
        performSubtractTimingTest(4_000);
        performSubtractTimingTest(8_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performSubtractTimingTest(int numTests) {

        long totalNanoseconds = 0;
        // This is here to assure that subtract doesn't underflow and start 
        // returning null. This test doesn't want to have early returns
        StringBuilder invResetter = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            invResetter.append(alphabet);
        }
        inv = new LetterInventory(invResetter.toString());
        other = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // times the subtract method alone so other operations don't throw 
            // time off
            sw.start();
            inv.subtract(other);
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
            if (test % 100 == 0) {
                // resets inv every 100 tests just to make sure it doesn't 
                // get too big
                inv = new LetterInventory(invResetter.toString());
            }
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    private static void equalsTimingTests() {
        printHeader("equals timing tests", ':');
        performEqualsTimingTest(5_000);
        performEqualsTimingTest(10_000);
        performEqualsTimingTest(20_000);
        performEqualsTimingTest(40_000);
    }

    // This method should be O(N) where N is numTests. Take this into account 
    // while determining time complexity of equals method
    private static void performEqualsTimingTest(int numTests) {

        long totalNanoseconds = 0;
        inv = new LetterInventory(alphabet);
        other = new LetterInventory(alphabet);
        for (int test = 0; test < numTests; test++) {
            // times the equals method alone so other operations don't throw time off
            sw.start();
            inv.equals(other);
            sw.stop();
            totalNanoseconds += sw.timeInNanoseconds();
        }
        printTimingTest(totalNanoseconds, numTests);
    }

    /*----------------------- General Helper Methods ----------------------*/

    // prints results of a test
    private static void printTest(String tested, boolean condition) {
        numTests++;
        if (condition) {
            System.out.print("[Passed] ");
            numPassed++;
        } else {
            System.out.print("[!!!!FAILED!!!!] ");
        }
        System.out.println("Test " + numTests + ": " + tested);
    }

    // prints results of a timed test
    private static void printTimingTest(long numNanoseconds, int numTests) {
        // max number of spaces between the number of nanoseconds and times called
        final int PRINT_BUFFER = 12;
        // should space total time and times called equally regardless of 
        // number of nanoseconds
        int numSpaces = PRINT_BUFFER - ("" + numNanoseconds).length();

        System.out.print("Total time in nanoseconds: " + numNanoseconds);
        for (int i = 0; i < numSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println("times called: " + numTests);
    }

    // prints a header by repeating symb on either side of title.
    private static void printHeader(String title, char symb) {
        // determines how many symbols to print in the header 
        // use even number for consistent results. Odds round down (int div)
        final int NUM_SYMBOLS = (60 - title.length());
        System.out.println();
        for (int i = 0; i < NUM_SYMBOLS / 2; i++) {
            System.out.print(symb);
        }
        System.out.print(" " + title + " ");
        // modulo adjusts for odd title lengths
        for (int j = 0; j < NUM_SYMBOLS / 2 + NUM_SYMBOLS % 2; j++) {
            System.out.print(symb);
        }
        System.out.println("\n");
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
        System.out.println("\nAlways Remember: " +
                "\nProgram testing can be used to show the presence " +
                "\nof bugs, but never to show their absence! " +
                "\n    - Edsger Dijkstra" +
                "\n         - Mike Scott");
    }

}
