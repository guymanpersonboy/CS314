package Huffman_Coding;
/*  Student information for assignment:
 *
 *  On MY honor, CHRISTOPHER CARRASCO, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student: Christopher Carrasco
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 *  Grader name: Andrew
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleHuffProcessor implements IHuffProcessor {

	private IHuffViewer myViewer;
	// important values for this class
	private int[] impValues;
	// frequencies of bit-sequences in the file
	private int[] frequencies;
	// mappings of value to its node in HuffmanTree
	private String[] huffTable;
	// used for compression and decompression
	private HuffmanTree tree;

	private static final int HEADER_FORMAT = 0;
	private static final int BITS_WRITTEN = 1;
	private static final int BITS_SAVED = 2;
	private static final int NUM_IMP_VALUES = 3;

	public SimpleHuffProcessor() {
		impValues = new int[NUM_IMP_VALUES];
	}

	/**
	 * Preprocess data so that compression is possible --- count
	 * characters/create tree/store state so that a subsequent call to compress
	 * will work. The InputStream is <em>not</em> a BitInputStream, so wrap it
	 * int one as needed.
	 * 
	 * @param in           is the stream which could be subsequently compressed
	 * @param headerFormat a constant from IHuffProcessor that determines what
	 *                     kind of header to use, standard count format,
	 *                     standard tree format, or possibly some format added
	 *                     in the future.
	 * @return number of bits saved by compression or some other measure Note,
	 *         to determine the number of bits saved, the number of bits written
	 *         includes ALL bits that will be written including the magic
	 *         number, the header format number, the header to reproduce the
	 *         tree, AND the actual data.
	 * @throws IOException if an error occurs while reading from the input file.
	 */
	public int preprocessCompress(InputStream in, int headerFormat)
			throws IOException {
		showString("Preprocessing.");
		BitInputStream bitIn = new BitInputStream(in);

		impValues[HEADER_FORMAT] = headerFormat;
		// magic number + headerFormat
		impValues[BITS_WRITTEN] = BITS_PER_INT + BITS_PER_INT;

		final int sizeOfFile = readInput(bitIn);
		showString("Still processing");
		tree = new HuffmanTree(frequencies);

		// header content to be written
		if (headerFormat == STORE_COUNTS) {
			impValues[BITS_WRITTEN] += BITS_PER_INT * ALPH_SIZE;
		} else {
			// headerFormat == STORE_TREE
			impValues[BITS_WRITTEN] += BITS_PER_INT + tree.size();
		}

		// + 1 for PSEUDO_EOF
		huffTable = tree.getHuffTable();

		countBody();
		impValues[BITS_SAVED] = sizeOfFile - impValues[BITS_WRITTEN];
		showString("File processed!\n");

		return impValues[BITS_SAVED];
	}

	// Read the entire file and create an int[] of all the frequencies of each
	// value where value equals index in array
	// pre: bitIn != null, handled in calling method
	// post: frequencies is completed
	// return the size of the original file
	private int readInput(BitInputStream bitIn) throws IOException {
		frequencies = new int[ALPH_SIZE];
		int nextByte = bitIn.read();
		int sizeOfFile = 0;

		while (nextByte != -1) {
			frequencies[nextByte]++;
			// sums up bits of data in file
			sizeOfFile += BITS_PER_WORD;
			nextByte = bitIn.read();
		}

		return sizeOfFile;
	}

	// count the number of bits that will be written if the file itself were to
	// be compressed without header content
	private void countBody() {
		int value = 0;

		for (int freq : frequencies) {
			if (freq != 0) {
				// freq * length of mapping will be written for this value
				impValues[BITS_WRITTEN] += freq * huffTable[value].length();
			}
			value++;
		}
		// last bits written should be PSEUDO_EOF mapping
		impValues[BITS_WRITTEN] += huffTable[PSEUDO_EOF].length();
	}

	/**
	 * Compresses input to output, where the same InputStream has previously
	 * been pre-processed via <code>preprocessCompress</code> storing state used
	 * by this call. <br>
	 * pre: <code>preprocessCompress</code> must be called before this method
	 * 
	 * @param in    is the stream being compressed (NOT a BitInputStream)
	 * @param out   is bound to a file/stream to which bits are written for the
	 *              compressed file (not a BitOutputStream)
	 * @param force if this is true create the output file even if it is larger
	 *              than the input file. If this is false do not create the
	 *              output file if it is larger than the input file.
	 * @return the number of bits written.
	 * @throws IOException if an error occurs while reading from the input file
	 *                     or writing to the output file.
	 */
	public int compress(InputStream in, OutputStream out, boolean force)
			throws IOException {
		// check if force larger compressed file
		if (possibleErrors(force)) {
			return -1;
		} else if (impValues[BITS_SAVED] < 0 && force) {
			showString("Forcing compression.");
		}
		BitInputStream bitIn = new BitInputStream(in);
		BitOutputStream bitOut = new BitOutputStream(out);
		Compressor coder = new Compressor(bitIn, bitOut);

		bitOut.writeBits(BITS_PER_INT, MAGIC_NUMBER);
		bitOut.writeBits(BITS_PER_INT, impValues[HEADER_FORMAT]);

		if (impValues[HEADER_FORMAT] == STORE_COUNTS) {
			showString("Compressing to a new file using Count Format!");
			coder.storeCount(frequencies);
		} else {
			// impValues[HEADER_FORMAT] == STORE_TREE
			showString("Compressing to a new file using Tree Format!");
			coder.storeTree(tree);
		}
		coder.codeBody(huffTable);
		bitIn.close();
		bitOut.close();
		showString("Done!\n");

		return impValues[BITS_WRITTEN];
	}

	// check possible compression errors that may arise
	private boolean possibleErrors(boolean force) {
		// check to see if force compression leading to a larger file
		if (impValues[BITS_SAVED] < 0 && !force) {
			myViewer.update("Error\n");
			myViewer.showError("Compressed file has "
					+ -1 * impValues[BITS_SAVED]
					+ " more bits than uncompressed file. Go to"
					+ " \"Options > Force Compression\" to compress this file.");
			return true;
		}

		// HEADER_FORMAT must == either Count Format or Tree Format
		if (impValues[HEADER_FORMAT] != STORE_COUNTS
				&& impValues[HEADER_FORMAT] != STORE_TREE) {
			myViewer.update("Error\n");
			myViewer.showError(
					"Invalid header format to compress using Huffman compression");
			return true;
		}

		return false;
	}

	/**
	 * Uncompress a previously compressed stream in, writing the uncompressed
	 * bits/data to out.
	 * 
	 * @param in  is the previously compressed data (not a BitInputStream)
	 * @param out is the uncompressed file/stream
	 * @return the number of bits written to the uncompressed file/stream
	 * @throws IOException if an error occurs while reading from the input file
	 *                     or writing to the output file.
	 */
	public int uncompress(InputStream in, OutputStream out) throws IOException {
		BitInputStream bitIn = new BitInputStream(in);
		BitOutputStream bitOut = new BitOutputStream(out);
		int bitsWrittenOut = 0;

		if (bitIn.readBits(BITS_PER_INT) != MAGIC_NUMBER) {
			myViewer.update("Error");
			myViewer.showError("Error reading compressed file. \n"
					+ "File did not start with the huff magic number");
			bitIn.close();
			bitOut.close();
			return -1;
		}
		impValues[HEADER_FORMAT] = bitIn.readBits(BITS_PER_INT);
		// check header format number
		possibleErrors(true);

		if (impValues[HEADER_FORMAT] == STORE_COUNTS) {
			showString("Uncompressing file with Count format.");
			tree = new HuffmanTree(bitIn);
		} else {
			// impValues[HEADER_FORMAT] == STORE_TREE
			showString("Uncompressing file with Tree Format.");
			tree = new HuffmanTree(bitIn, bitOut);
		}
		bitsWrittenOut = tree.decompress(bitIn, bitOut);

		bitIn.close();
		bitOut.close();
		showString("Done!\n");

		return bitsWrittenOut;
	}

	public void setViewer(IHuffViewer viewer) {
		myViewer = viewer;
	}

	private void showString(String s) {
		if (myViewer != null)
			myViewer.update(s);
	}
}
