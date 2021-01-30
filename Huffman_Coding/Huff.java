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

/**
 * Main/launch program for Huff assignment. Create and launch a GUI to perform
 * Huffman coding, compression, or decompression on files of any type.
 *
 */
public class Huff {

	public static void main(String[] args) {
		IHuffViewer sv = new GUIHuffViewer("Huffman Compression");
		IHuffProcessor proc = new SimpleHuffProcessor();
		sv.setModel(proc);
	}
}
