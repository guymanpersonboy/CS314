package Extra_Tests;
import java.util.ArrayList;
import java.util.Collections;

import Recursion.Recursive;

/*
    This is a battery of tests made by Alex Hanlin for assignment a6: recursion.
    Comment out methods as necessary when using.
    Message me on Piazza or Discord if you find any bugs or errors!
 */
public class AlexRecursiveTester {
    // keeps track of test number
    private static int numTests = 0;
    private static int passed = 0;

    public static void main(String[] args) {
        getBinaryTests();
        revStringTests();
        nextIsDoubleTest();
        listMnemonicsTester();
        canFlowOffMapTest();
        minDifferenceTest();
        canEscapeMazeTest();

        finalResults();
    }

    private static void getBinaryTests() {
        printHeader("getBinary", '#');

        // small positive
        String actual = Recursive.getBinary(9);
        String expected = "1001";
        printTest("getBinary small positive", actual.equals(expected));

        // small negative
        actual = Recursive.getBinary(-9);
        expected = "-1001";
        printTest("getBinary small negative", actual.equals(expected));

        // large positive
        actual = Recursive.getBinary(1_000);
        expected = "1111101000";
        printTest("getBinary large positive", actual.equals(expected));

        // large negative
        actual = Recursive.getBinary(-1_000);
        expected = "-1111101000";
        printTest("getBinary large negative", actual.equals(expected));

        // huge positive
        actual = Recursive.getBinary(Integer.MAX_VALUE);
        expected = "1111111111111111111111111111111";
        printTest("getBinary huge positive", actual.equals(expected));

        // huge negative. Barely passes precondition
        actual = Recursive.getBinary(Integer.MIN_VALUE + 1);
        expected = "-1111111111111111111111111111111";
        printTest("getBinary huge negative", actual.equals(expected));

        // zero only
        actual = Recursive.getBinary(0);
        expected = "0";
        printTest("getBinary zero", actual.equals(expected));

        // one only
        actual = Recursive.getBinary(1);
        expected = "1";
        printTest("getBinary one", actual.equals(expected));
    }

    private static void revStringTests() {
        printHeader("revString", '#');

        // single character
        String actual = Recursive.revString("A");
        String expected = "A";
        printTest("revString one character", actual.equals(expected));

        // palindrome test. If you fail this, something very bad has happened
        actual = Recursive.revString("talenelat");
        expected = "talenelat";
        printTest("revString palindrome", actual.equals(expected));

        // single word
        actual = Recursive.revString("HollowKnight");
        expected = "thginKwolloH";
        printTest("revString single word", actual.equals(expected));

        // alphanumerics and spaces
        actual = Recursive.revString("1 wond3r 1f 4nyon3 g3ts th1s r3f3r3nc3");
        expected = "3cn3r3f3r s1ht st3g 3noyn4 f1 r3dnow 1";
        printTest("revString alphanumeric sentence", actual.equals(expected));

        // non-alphanumeric characters
        actual = Recursive.revString("`~!@#$%^&*()_+-=[]{}|';:/?<>,.");
        expected = ".,><?/:;'|}{][=-+_)(*&^%$#@!~`";
        printTest("revString non-alphanumeric", actual.equals(expected));

        // escape sequences. Shouldn't be a special case, but can't hurt.
        actual = Recursive.revString("\t\b\n\r\f\'\"\\");
        expected = "\\\"\'\f\r\n\b\t";
        printTest("revString escape sequences", actual.equals(expected));
    }

    private static void nextIsDoubleTest() {
        printHeader("nextIsDouble", '#');

        // empty array
        int actual = Recursive.nextIsDouble(new int[]{});
        int expected = 0;
        printTest("nextIsDouble empty array", actual == expected);

        // single element array
        actual = Recursive.nextIsDouble(new int[]{8});
        expected = 0;
        printTest("nextIsDouble single element", actual == expected);

        // sequential doubles
        actual = Recursive.nextIsDouble(new int[]{2, 4, 8, 16, 100, 150});
        expected = 3;
        printTest("nextIsDouble sequential doubles", actual == expected);

        // non-sequential doubles
        actual = Recursive.nextIsDouble(new int[]{2, 4, 100, 8, 16, 150});
        expected = 2;
        printTest("nextIsDouble non-sequential doubles", actual == expected);

        // negative doubles
        actual = Recursive.nextIsDouble(new int[]{-2, -4, -100, -8, -16, -150});
        expected = 2;
        printTest("nextIsDouble negative doubles", actual == expected);
    }

    private static void listMnemonicsTester() {
        printHeader("listMnemonics", '#');

        // single character with 1 possible number
        ArrayList<String> actual = Recursive.listMnemonics("0");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("0");
        printTest("listMnemonics zero only", actual.equals(expected));

        // single character with 3 possible letters
        actual = Recursive.listMnemonics("5");
        expected.clear();
        expected.add("J");
        expected.add("K");
        expected.add("L");
        Collections.sort(actual);
        printTest("listMnemonics 3-letter number", actual.equals(expected));

        // single character with 4 possible letters
        actual = Recursive.listMnemonics("7");
        expected.clear();
        expected.add("P");
        expected.add("Q");
        expected.add("R");
        expected.add("S");
        Collections.sort(actual);
        printTest("listMnemonics 4-letter number", actual.equals(expected));

        // same number
        actual = Recursive.listMnemonics("66");
        expected.clear();
        expected.add("MM");
        expected.add("MN");
        expected.add("MO");
        expected.add("NM");
        expected.add("NN");
        expected.add("NO");
        expected.add("OM");
        expected.add("ON");
        expected.add("OO");
        Collections.sort(actual);
        printTest("listMnemonics same number", actual.equals(expected));

        // different numbers
        actual = Recursive.listMnemonics("48");
        expected.clear();
        expected.add("GT");
        expected.add("GU");
        expected.add("GV");
        expected.add("HT");
        expected.add("HU");
        expected.add("HV");
        expected.add("IT");
        expected.add("IU");
        expected.add("IV");
        Collections.sort(actual);
        printTest("listMnemonics different numbers", actual.equals(expected));

        // different numbers with different size
        actual = Recursive.listMnemonics("47");
        expected.clear();
        expected.add("GP");
        expected.add("GQ");
        expected.add("GR");
        expected.add("GS");
        expected.add("HP");
        expected.add("HQ");
        expected.add("HR");
        expected.add("HS");
        expected.add("IP");
        expected.add("IQ");
        expected.add("IR");
        expected.add("IS");
        Collections.sort(actual);
        printTest("listMnemonics different size numbers",
                actual.equals(expected));

        // "binary" string. 1 and 0 can only return themselves. should only
        // have 1 result
        actual = Recursive.listMnemonics("110111010100010101");
        expected.clear();
        expected.add("110111010100010101");
        printTest("listMnemonics ones and zeroes", actual.equals(expected));
    }

    private static void canFlowOffMapTest() {
        printHeader("canFlowOffMap", '#');

        // 8x8 square with multiple dead ends I can add to the path
        int[][] map = new int[][]{
                {300, 300, 300, 300, 300, 300, 300, 193},
                {300, 300, 300, 300, 300, 300, 194, 300},
                {300, 300, 199, 198, 300, 196, 195, 300},
                {300, 300, 300, 197, 300, 300, 300, 300},
                {300, 194, 300, 196, 300, 300, 300, 300},
                {300, 193, 300, 195, 194, 300, 300, 300},
                {300, 300, 300, 300, 193, 192, 191, 300},
                {300, 300, 300, 300, 300, 300, 300, 300}
        };

        // Has no solution
        printTest("canFlowOffMap impossible", !Recursive.canFlowOffMap(map, 2, 2));

        // Has a solution
        map[7][7] = 190;
        printTest("canFlowOffMap impossible diagonal",
                !Recursive.canFlowOffMap(map, 2, 2));

        // single possible pathway
        map[7][7] = 300;
        map[7][6] = 190;
        printTest("canFlowOffMap possible", Recursive.canFlowOffMap(map, 2, 2));

        // starts a ways "downstream"
        printTest("canFlowOffMap mid-path", Recursive.canFlowOffMap(map, 5, 4));

        // various dead end paths added but still passable
        map[2][4] = 197;
        map[4][2] = 195;
        map[1][2] = 198;
        map[2][1] = 198;
        printTest("canFlowOffMap dead ends", Recursive.canFlowOffMap(map, 2, 2));

        // starts on the "riverbank". Should be possible
        printTest("canFlowOffMap riverbank", Recursive.canFlowOffMap(map, 5, 6));

        // starts at edge
        printTest("canFlowOffMap at edge", Recursive.canFlowOffMap(map, 4, 7));

        // starts with nowhere to go
        printTest("canFlowOffMap stranded", !Recursive.canFlowOffMap(map, 4, 6));


    }

    private static void minDifferenceTest() {
        printHeader("minDifference", '#');

        // 2 teams that have a configuration that's perfectly matched
        int actual = Recursive.minDifference(2, new int[]{5, 10, 15, 20});
        int expected = 0;
        printTest("minDifference 2 perfect teams", actual == expected);

        // 2 teams with uneven matching
        actual = Recursive.minDifference(2, new int[]{7, 10, 15, 20});
        expected = 2;
        printTest("minDifference 2 imperfect teams", actual == expected);

        // 4 teams with perfect matching
        actual = Recursive.minDifference(3, new int[]{5, 10, 15, 15, 10, 5});
        expected = 0;
        printTest("minDifference 3 perfect teams", actual == expected);

        // 3 teams with imperfect matching
        actual = Recursive.minDifference(3, new int[]{5, 10, 30, 85});
        expected = 70;
        printTest("minDifference 3 imperfect teams", actual == expected);

        // 3 teams with negatives
        actual = Recursive.minDifference(3, new int[]{-5, 10, 15, -30, 85,});
        expected = 40;
        printTest("minDifference with negative values", actual == expected);
    }

    private static void canEscapeMazeTest() {
        printHeader("canEscapeMaze", '#');

        // Simple one-dimensional maze to start with
        char[][] maze = new char[][]{
                {'$', 'G', 'G', 'S', '$', 'E'},
        };

        // easy 1D solvable maze
        int actual = Recursive.canEscapeMaze(maze);
        int expected = 2;
        printTest("canEscapeMaze 1D easy", actual == expected);

        // Same maze but can't get the money without trapping yourself
        maze[0][2] = 'Y';
        actual = Recursive.canEscapeMaze(maze);
        expected = 1;
        printTest("canEscapeMaze 1D without money", actual == expected);

        // impossible 1D maze
        maze[0][4] = '*';
        actual = Recursive.canEscapeMaze(maze);
        expected = 0;
        printTest("canEscapeMaze 1D impossible", actual == expected);

        // Time for the interesting one
        // fully possible 2D maze with booby-traps (places you can get stuck)
        maze = new char[][]{
                {'$', '$', '*', '*', '*', '*'},
                {'Y', 'G', 'S', 'G', 'Y', 'G'},
                {'*', '*', '*', 'Y', '*', '*'},
                {'*', '$', '$', 'G', '$', 'Y'},
                {'*', 'E', '*', '*', '*', 'G'},
        };
        actual = Recursive.canEscapeMaze(maze);
        expected = 2;
        printTest("canEscapeMaze 2D with traps", actual == expected);

        // Possible 2D maze with unreachable money
        maze[1][5] = '$';
        maze[4][5] = '$';
        actual = Recursive.canEscapeMaze(maze);
        expected = 1;
        printTest("canEscapeMaze 2D without money", actual == expected);

        // Impossible 2D maze. Effectively checks that you can't move diagonally
        maze[1][3] = '*';
        actual = Recursive.canEscapeMaze(maze);
        expected = 0;
        printTest("canEscapeMaze 2D impossible", actual == expected);
    }

    private static void printTest(String tested, boolean condition) {
        numTests++;
        if (condition) {
            System.out.print("[Passed] ");
            passed++;
        } else {
            System.out.print("[!!!!FAILED!!!!] ");
        }
        System.out.println("Test " + numTests + ": " + tested);
    }

    private static void printHeader(String title, char symb) {
        // use even number for consistent results. int division is limited
        final int NUM_SYMBOLS = 60 - title.length();
        System.out.println();
        for (int i = 0; i < NUM_SYMBOLS / 2; i++) {
            System.out.print(symb);
        }
        System.out.print(" " + title + " ");
        // accounts for odd title length
        for (int j = 0; j < NUM_SYMBOLS / 2 + NUM_SYMBOLS % 2; j++) {
            System.out.print(symb);
        }
        System.out.println("\n");
    }

    private static void finalResults() {
        printHeader("FINAL RESULTS", '@');
        System.out.println("Successful tests: " + passed + " / " + numTests);
        if (passed == numTests) {
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
