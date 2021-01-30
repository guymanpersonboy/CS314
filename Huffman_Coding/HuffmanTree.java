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
 * A full binary tree using TreeNode for its vertices. Used to derive per-chunk
 * encodings, then write bits based on these encodings.
 * 
 */
public class HuffmanTree implements IHuffConstants {

	private TreeNode root;
	private int size;

	/**
	 * Create a HuffmanTree using the frequencies of the values read in from the
	 * file Used in the compression of the file.
	 * 
	 * @param frequencies The frequncies of the values read in from the file
	 */
	public HuffmanTree(int[] frequencies) {
		if (frequencies == null) {
			throw new IllegalArgumentException("frequencies cannot be null");
		}
		PriorityQueue<TreeNode> queue = buildQueue(frequencies);
		root = buildTree(queue);
		calculateSize(root);
	}

	/**
	 * Create a HuffmanTree using the header of a compressed file made with the
	 * Count Header Format. pre: bitIn != null.
	 * 
	 * @param bitIn is the BitInputStream connected to a file
	 * @throws IOException
	 */
	public HuffmanTree(BitInputStream bitIn) throws IOException {
		if (bitIn == null) {
			throw new IllegalArgumentException("bitIn != null, bitOut != null");
		}
		int[] frequencies = new int[ALPH_SIZE];

		// read the header content and reconstruct frequencies array
		for (int index = 0; index < ALPH_SIZE; index++) {
			frequencies[index] = bitIn.readBits(BITS_PER_INT);
		}

		PriorityQueue<TreeNode> queue = buildQueue(frequencies);
		root = buildTree(queue);
		calculateSize(root);
	}

	/**
	 * Create a HuffmanTree using the header of a compressed file made with the
	 * Tree Header Format. pre: bitIn != null, bitOut != null.
	 * 
	 * @param bitIn  is the BitInputStream connected to a file
	 * @param bitOut is the BitOutputStream connected to a file
	 * @throws IOException
	 */
	public HuffmanTree(BitInputStream bitIn, BitOutputStream bitOut)
			throws IOException {
		if (bitIn == null || bitOut == null) {
			throw new IllegalArgumentException("bitIn != null, bitOut != null");
		}
		final int treeSize = bitIn.readBits(BITS_PER_INT);
		root = decodeTreeHelper(bitIn, treeSize);
		size = treeSize;
	}

	// builds a PriorityQueue<TreeNode> with all values of freq > 0.
	// pre: queue != null, data != null, handled in calling method.
	// post: return PriorityQueue filled so that all the elements are in
	// ascending order.
	private PriorityQueue<TreeNode> buildQueue(int[] frequencies) {
		PriorityQueue<TreeNode> queue = new PriorityQueue<>();
		int value = 0;

		for (int freq : frequencies) {
			if (freq != 0) {
				queue.enqueue(new TreeNode(value, freq));
			}
			value++;
		}
		queue.enqueue(new TreeNode(PSEUDO_EOF, 1));

		return queue;
	}

	// Construct the Huffman Tree be dequeueing two TreeNodes then creating a
	// parent from the two, enqueue the subtree, repeat
	// queue != null, handled by calling method
	// one TreeNode is left in the queue which acts as the root of the tree
	private TreeNode buildTree(PriorityQueue<TreeNode> queue) {
		if (queue.size() == 0) {
			throw new IllegalStateException("queue cannot be empty");
		}
		TreeNode first;
		TreeNode second;
		TreeNode parent;

		// build the tree until there is one tree node which will be the root
		while (queue.size() != 1) {
			first = queue.dequeue();
			second = queue.dequeue();
			parent = new TreeNode(-1,
					first.getFrequency() + second.getFrequency());

			parent.setLeft(first);
			parent.setRight(second);
			queue.enqueue(parent);
		}

		return queue.getFirst();
	}

	// create a binary-tree and return the root to create a HuffmanTree
	// pre: queue != null,
	// post return the root node of the constructed full binary tree
	// throws IOException if there was some unexpected header content
	private TreeNode decodeTreeHelper(BitInputStream bitIn, int treeSize)
			throws IOException {
		final int bit = bitIn.readBits(1);
		TreeNode node;

		if (bit == -1) {
			throw new IOException("Error reading compressed file. \n"
					+ "Unexpected header content");
		}

		if (bit == 0) {
			node = new TreeNode(-1, -1);

			node.setLeft(decodeTreeHelper(bitIn, treeSize));
			node.setRight(decodeTreeHelper(bitIn, treeSize));
		} else {
			// bit == 1
			final int value = bitIn.readBits(BITS_PER_WORD + 1);

			node = new TreeNode(value, -1);
		}

		return node;
	}

	// recursively traverse the tree and count the bits required to encode the
	// tree into the header of a compressed file
	// pre: node != null, data != null, handled in calling method
	// post: data[0] holds the size for this tree
	private void calculateSize(TreeNode node) {
		if (node.getLeft() != null) {
			calculateSize(node.getLeft());
		}

		// passed under the node
		// add bits for node (1 bit to show if leaf or not)
		size++;
		if (node.getLeft() == null && node.getRight() == null) {
			// a leaf node, add bits for value (9 bits to represent 1-257)
			size += BITS_PER_WORD + 1;
		}

		if (node.getRight() != null) {
			calculateSize(node.getRight());
		}
	}

	/**
	 * Used in the compression of a file. Stores this HuffmanTree into the
	 * header of file being written to using recursion. pre: bitOut != null
	 * 
	 * @param bitOut Outputting to the new compressed file
	 */
	public void writeTree(BitOutputStream bitOut) {
		if (bitOut == null) {
			throw new IllegalArgumentException("bitOut != null");
		}
		writeTreeHelper(root, bitOut);
	}

	// write tree format header content recursively
	// pre: node != null, bitOut != null, handled in calling method
	// post: the entire tree is coded out to the file
	private void writeTreeHelper(TreeNode node, BitOutputStream bitOut) {
		// passed to the left of the node
		if (node.getLeft() == null && node.getRight() == null) {
			// a leaf node
			bitOut.writeBits(1, 1);
			// 9 bits to account for PSEUDO_EOF 9
			bitOut.writeBits(BITS_PER_WORD + 1, node.getValue());
		} else {
			// non-leaf node
			bitOut.writeBits(1, 0);
		}

		// continue traversing
		if (node.getLeft() != null) {
			writeTreeHelper(node.getLeft(), bitOut);
		}
		if (node.getRight() != null) {
			writeTreeHelper(node.getRight(), bitOut);
		}
	}

	/**
	 * Decode the compressed data after the header content of the file
	 * 
	 * @param bitIn  Connected to the compressed file.
	 * @param bitOut Outputting to the new decompressed file.
	 * @throws IOException No PSEUDO_EOF value at end of file.
	 */
	public int decompress(BitInputStream bitIn, BitOutputStream bitOut)
			throws IOException {
		TreeNode node = root;
		boolean done = false;
		int bitsWritten = 0;
		int bit = -1;

		while (!done) {
			bit = bitIn.readBits(1);

			if (bit == -1) {
				throw new IOException("Error reading compressed file. \n"
						+ "Unexpected end of input. No PSEUDO_EOF value.");
			} else {
				// move left or right in tree based on value of bit (0 or 1)
				node = (bit == 0) ? node.getLeft() : node.getRight();

				if (node.getLeft() == null && node.getRight() == null) {
					// a leaf node, determine if done, else write value and
					// reset node
					if (node.getValue() == PSEUDO_EOF) {
						done = true;
					} else {
						bitOut.writeBits(BITS_PER_WORD, node.getValue());
						bitsWritten += BITS_PER_WORD;
						node = root;
					}
				}
			}
		}

		return bitsWritten;
	}

	/**
	 * Create a String array of mappings for a value to its respective leaf node
	 * in this tree
	 * 
	 * @return A huffTable of mappings for a value to its node in this tree
	 */
	public String[] getHuffTable() {
		String[] huffTable = new String[ALPH_SIZE + 1];
		buildTable(root, huffTable, new StringBuilder());

		return huffTable;
	}

	// Builds the table of mappings to the leaf nodes in huffmanTree using
	// in-order traversal.
	// pre: node!= null, path != null, handled in calling method.
	// post: huffTable is filled with all values and frequencies
	// including 0 frequencies as null Strings.
	private void buildTable(TreeNode node, String[] huffTable,
			StringBuilder path) {
		if (node.getLeft() != null) {
			path.append("0");
			buildTable(node.getLeft(), huffTable, path);
			// remove the last path digit to continue mapping
			path.deleteCharAt(path.length() - 1);
		}

		// passed under the node
		if (node.getLeft() == null && node.getRight() == null) {
			// a leaf node
			huffTable[node.getValue()] = path.toString();
		}

		if (node.getRight() != null) {
			path.append("1");
			buildTable(node.getRight(), huffTable, path);
			// remove the last path digit to continue mapping
			path.deleteCharAt(path.length() - 1);
		}
	}

	/**
	 * Gets the size of this Huffman tree
	 * 
	 * @return size is amount of bits needed to encode this tree
	 */
	public int size() {
		return size;
	}

	// Credit: Mike Scott, CS 314 Assignment 9, modified
	/**
	 * Prints a vertical representation of this tree. The tree has been rotated
	 * counter clockwise 90 degrees. The root is on the left. Each node is
	 * printed out on its own row. A node's children will not necessarily be at
	 * the rows directly above and below a row. They will be indented three
	 * spaces from the parent. Nodes indented the same amount are at the same
	 * depth. <br>
	 * pre: none
	 */
	public void printTree() {
		printTree(root, "");
	}

	// Recursively print the tree
	private void printTree(TreeNode n, String spaces) {
		if (n != null) {
			printTree(n.getRight(), spaces + "     ");
			System.out.println(spaces + n.getValue() + "/" + n.getFrequency());
			printTree(n.getLeft(), spaces + "     ");
		}
	}

}
