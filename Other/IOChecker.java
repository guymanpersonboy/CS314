package Other;

import java.io.*;

/*
Copyright (C) 2020 Daniel Parks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class IOChecker {
    private static String CLASS_TO_TEST = "Main";
    private static String INPUT_FILE = "test.txt";
    // loose whitespace checking is not implemented
//    private static boolean WHITESPACE_STRICT = true;
    private static String[] INPUT_INDICATORS = new String[]{":", ">"};
    private static String CLASSPATH = System.getProperty("java.class.path");
    // do not set this below 150 or bad things will happen
    private static int TIMEOUT_MS = 150;
    private static final int MAX_FAILS_IN_A_ROW = 3;

    private static Process p;
    private static int failsInARow = 0;

    public static void main(String[] args) throws IOException {
        // process cli arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                //                    case "strict", "whitespace" -> WHITESPACE_STRICT = true;
                //                    case "no-strict", "no-whitespace" -> WHITESPACE_STRICT = false;
                //noinspection EnhancedSwitchMigration
                switch (args[i].substring(2)) {
                    case "class":
                        CLASS_TO_TEST = args[++i];
                        break;
                    case "file":
                    case "input":
                        INPUT_FILE = args[++i];
                        break;
                    case "classpath":
                        CLASSPATH = args[++i];
                        break;
                    case "input-indicators":
                        INPUT_INDICATORS = args[++i].split(",\\s*");
                        break;
                    case "timeout":
                        TIMEOUT_MS = Integer.parseInt(args[++i]);
                        break;
                    default:
                        usage();
                        break;
                }
            }
            else usage();
        }

        // main part
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", CLASSPATH,
                CLASS_TO_TEST);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        p = pb.start();
        StdOutReader actual = new StdOutReader(new InputStreamReader(p.getInputStream()));
        BufferedReader expected = new BufferedReader(new FileReader(INPUT_FILE));
        OutputStreamWriter pIn = new OutputStreamWriter(p.getOutputStream());
        String actualLine, expectedLine;
        boolean previousLineEndedWithInputIndicator = false;
        //noinspection InfiniteLoopStatement
        while (true) {
        	  // sorry for the spaghetti
            // java Processes are bad
            expectedLine = expected.readLine();
            actualLine = actual.readLine(TIMEOUT_MS);
            if (actualLine != null && actualLine.equals("") && actual.timedOut()) {
                if (!p.isAlive()) actualLine = null;
                // program is probably still processing, go ahead and skip it
                else continue;
            }
            if (expectedLine == null && actualLine != null) {
                System.err.println("Your program has extra output:");
                System.err.println(actualLine);
                cleanUpAndExit(2);
            }
            else if (expectedLine != null && actualLine == null) {
                System.err.println("Your program exited, but test file has additional output:");
                System.err.println(expectedLine);
                cleanUpAndExit(2);
            }
            else //noinspection ConstantConditions
                if (expectedLine == null && actualLine == null) {
                cleanUpAndExit(0);
            }
            assert expectedLine != null && actualLine != null;
            System.out.print(actualLine);
            if (actual.timedOut()) {
                //noinspection ConstantConditions
                if (expectedLine.startsWith(actualLine) || (actualLine.equals("") && previousLineEndedWithInputIndicator)) {
                    // program is waiting for input
                    // give it input
                    String input = expectedLine.substring(actualLine.length()).trim();
                    if (!input.equals("")) {
                        try {
                            pIn.write(input + '\n');
                            pIn.flush();
                        } catch (IOException e) {
                            System.err.println("Your program exited, but test file has additional output:");
                            System.err.println(expectedLine);
                            cleanUpAndExit(2);
                        }
                        System.out.print(input);
                    }
                }
                else {
                    fail(expectedLine, actualLine);
                    System.err.println("Note: did not read a full line from your program. If it has long-running operations, you may need to increase the timeout.");
                }
            }
            else if (!actualLine.equals(expectedLine)) fail(expectedLine, actualLine);
            else {
                previousLineEndedWithInputIndicator = multiEndsWith(actualLine, INPUT_INDICATORS);
                failsInARow = 0;
            }
            System.out.println();
        }
    }

    private static void fail(String expected, String actual) {
        System.err.println("\n-------- FAIL --------");
        System.err.println("Expected line: " + expected);
        final String actualLine = "Actual line:   ";
        System.err.println(actualLine + actual);
        StringBuilder indicator = new StringBuilder();
        for (int i = 0; i < actualLine.length(); i++) {
            indicator.append(' ');
        }
        for (int i = 0; i < expected.length(); i++) {
            if (i >= actual.length() || actual.charAt(i) != expected.charAt(i)) {
                indicator.setCharAt(actualLine.length() + i - 1, '-');
                indicator.append("^-");
                break;
            }
            indicator.append(' ');
        }
        if (indicator.charAt(indicator.length() - 1) != '-') {
            indicator.setCharAt(indicator.length() - 1, '-');
            indicator.append("^-");
        }
        indicator.append("\nNote: first differing character was here");
        System.err.println(indicator);
        if (++failsInARow >= MAX_FAILS_IN_A_ROW) {
            cleanUpAndExit(3);
        }
    }

    private static void cleanUpAndExit(int code) {
    	  if (p.isAlive()) {
            System.err.println("Note: your program will be terminated. This may cause an exception.");
            p.destroy();
            int i;
            for (i = 0; i < 100; i++) {
                if (! p.isAlive()) break;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
            if (i == 100) p.destroyForcibly();
        }
        System.exit(code);
    }

    private static boolean multiEndsWith(String s, String[] possibilities) {
        for (String p: possibilities) {
            if (s.endsWith(p)) return true;
        }
        return false;
    }

    private static void usage() {
        System.out.println(
                "Usage: java IOChecker [--class <class_name>] [--file <input_file>] [--input-indicators <list>] [--classpath <classpath>] [--timeout <timeout>]\n\n" +
                "--class <class_name>       Test this class\n"  +
                "--file <input_file>        Get testing info from <input_file>\n" +
//                "--strict, --no-strict      Enable/disable strict whitespace checking\n" +
                "--input-indicators <list>  Comma-separated list of strings that indicate that the program is waiting for input. Do not include spaces.\n" +
                "--classpath <classpath>    Use <classpath> to run <class_name> instead of the classpath of this program\n" +
                "--timeout <timeout>        How long to wait before deciding your program is waiting for input (in milliseconds)"
        );
        System.exit(1);
    }
}

// used OpenJDK implementation of BufferedReader as reference
class StdOutReader extends Reader {
    private InputStreamReader in;
    private char[] buf;
    private int cursor;
    private int lastRead;
    private boolean timedOut;

    private static final int DEFAULT_BUF_SIZE = 8192;
    private static final int DEFAULT_TIMEOUT = 50;

    public StdOutReader(InputStreamReader in, int sz) {
        super(in);
        this.in = in;
        buf = new char[sz];
        cursor = 0;
        lastRead = 0;
        timedOut = false;
    }
    public StdOutReader(InputStreamReader in) {
        this(in, DEFAULT_BUF_SIZE);
    }

    @Override
    public int read(char[] chars, int offset, int length) throws IOException {
        synchronized (lock) {
            return in.read(chars, offset, length);
        }
    }

    @Override
    public void close() throws IOException {
    	  synchronized (lock) {
            in.close();
        }
    }

    public String readLine(int timeoutMS) throws IOException {
    	  synchronized (lock) {
            StringBuilder result = new StringBuilder();
            while (true) {
                int cursorStart = cursor;
                for (; cursor < lastRead; cursor++) {
                    if (buf[cursor] == '\n') {
                        result.append(buf, cursorStart, cursor - cursorStart);
                        cursor++;
                        return result.toString();
                    }
                }
                // if we get here, we have a section of the buffer - from cursorStart to cursor - that does not contain a newline
                // first of all, it's part of the result (if we had a complete read before, this appends nothing)
                result.append(buf, cursorStart, cursor - cursorStart);
                timedOut = waitReady(timeoutMS);
                // if we time out here, then we should just return this section of the buffer
                if (timedOut) return result.toString();
                lastRead = in.read(buf); // re-read into the buffer
                cursor = 0;
                if (lastRead == -1) return null;
            }
        }
    }

    public boolean timedOut() {
        return timedOut;
    }

    private boolean waitReady(int timeout) throws IOException {
        final int quotient = 10;
        final int divisor = timeout / quotient;
        for (int i = 0; i < divisor; i++) {
            if (in.ready()) return false;
            try {
                Thread.sleep(quotient);
            } catch (InterruptedException e) {
                return true;
            }
        }
        return !in.ready();
    }

    public String readLine() throws IOException {
        return readLine(DEFAULT_TIMEOUT);
    }
}
