package Huffman_Coding;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.UIManager;

public class Diff {

    protected static JFileChooser ourChooser = new JFileChooser(".");

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message,"Diff Output",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void doDiffer(File[] files, DiffStats ds) {
        final int BITS_PER_BYTE = 8;
        try {
            ProgressMonitorInputStream stream1 =
                new ProgressMonitorInputStream(
                        null,
                        "reading " + files[0].getName(),
                        new FileInputStream(files[0]));
            ProgressMonitorInputStream stream2 =
                new ProgressMonitorInputStream(
                        null,
                        "reading " + files[1].getName(),
                        new FileInputStream(files[1]));
            BitInputStream b1 = new BitInputStream(stream1);
            BitInputStream b2 = new BitInputStream(stream2);
            ds.file1Size = files[0].length();
            ds.file2Size = files[1].length();
            int x = b1.readBits(BITS_PER_BYTE);
            int y = b2.readBits(BITS_PER_BYTE);
            int bytesRead = 1;
            while (x != -1 && y != -1) {
                if (x != y) {
                    ds.totalDifferences++;
                    if (ds.firstDiff == -1)
                        ds.firstDiff = bytesRead;
                }
                x = b1.readBits(8);
                y = b2.readBits(8);
                bytesRead++;
            }
            bytesRead--; // last read didn't work
            b1.close();
            b2.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"trouble reading","Diff Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args){
        setLookAndFeel();
        ourChooser.setMultiSelectionEnabled(true);
        ourChooser.setDialogTitle("Diff: choose two files");
        int retval = ourChooser.showOpenDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION){
                File[] files = ourChooser.getSelectedFiles();
                if (files.length != 2){
                    JOptionPane.showMessageDialog(null,"Choose Two Files",
                            "Diff Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    DiffStats ds = new DiffStats();
                    doDiffer(files, ds);
                    System.out.println("Results of comparing files: ");
                    System.out.println(ds);
                }
        }
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            System.out.println("Unable to set look at feel to local settings. " +
                    "Continuing with default Java look and feel.");
        }
    }

    private static class DiffStats {
        private long file1Size;
        private long file2Size;
        private int firstDiff;
        private int totalDifferences;

        private DiffStats() {
            firstDiff = -1;
        }

        public String toString() {
            String result =  "file 1 size in bytes: " + file1Size + "\n"
                   + "file 2 size in bytes: " + file2Size + "\n";
            if (firstDiff == -1) {
                result += "All bytes in files the same\n";
            } else {
                result += "number of bytes different: " + totalDifferences + "\n";
                result += "first difference occured after " + firstDiff + " bytes.\n";
            }
            return result;
        }
    }
}
