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

/*
 * Used for Huffman coding to compress a file with the desired header format.
 * Supports storing either the count of a bit-sequence that occurs in a file
 * or the HuffmanTree of the counts itself.
 * 
 */
public class Compressor {

	private BitInputStream bitIn;
	private BitOutputStream bitOut;

	/**
	 * Construct a Compressor with the given BitInputStream and BitOutputStream.
	 * 
	 * @param in  is a BitInputStream connected to a file for input
	 * @param out is a BitOutputStream connected to a file for output
	 */
	public Compressor(BitInputStream in, BitOutputStream out) {
		bitIn = in;
		bitOut = out;
	}

	/**
	 * Store the frequencies of values from the file connected to bitIn read in
	 * as 8-bit chunks. pre: frequencies != null post: write out the frequencies
	 * to the file connected to bitOut. pre: frequencies != null
	 * 
	 * @param frequencies The frequencies of values from the file connected to
	 *                    bitIn.
	 */
	public void storeCount(int[] frequencies) {
		if (frequencies == null) {
			throw new IllegalArgumentException("frequencies != null");
		}
		// write count format header
		for (int freq : frequencies) {
			bitOut.writeBits(IHuffConstants.BITS_PER_INT, freq);
		}
	}

	/**
	 * Store the HuffmanTree constructed from the frequencies of values from
	 * this file read in as 8-bit chunks. pre: tree != null. post: write out the
	 * size of the tree followed by the tree itself using a pre-order traversal.
	 * pre: tree != null
	 * 
	 * @param tree The HuffmanTree representing the file connected to bitIn.
	 */
	public void storeTree(HuffmanTree tree) {
		if (tree == null) {
			throw new IllegalArgumentException("tree != null");
		}
		// write size of tree to know when to stop decoding tree
		bitOut.writeBits(IHuffConstants.BITS_PER_INT, tree.size());
		tree.writeTree(bitOut);
	}

	/**
	 * Reads in and codes the file data connected to the BitInputStream and
	 * writes it out to the file connected to bitOut.
	 * 
	 * @param huffTable Mappings from value to its node in the HuffmanTree
	 * @throws IOException
	 */
	public void codeBody(String[] huffTable) throws IOException {
		int nextByte = bitIn.read();
		String mapping;

		while (nextByte != -1) {
			mapping = huffTable[nextByte];
			bitOut.writeBits(mapping.length(), Integer.parseInt(mapping, 2));

			nextByte = bitIn.read();
		}
		// write PEOF to signal end of the compressed file
		mapping = huffTable[IHuffConstants.PSEUDO_EOF];
		bitOut.writeBits(mapping.length(), Integer.parseInt(mapping, 2));
	}
}
