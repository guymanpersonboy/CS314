package Past_Assignments;
/*  Student information for assignment:
 *
 *  On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net
 *  TA name: Andrew
 *  Number of slip days I am using: 1
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * 
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree. Must
 *            implement Comparable or inherit from a class that implements
 *            Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

	private BSTNode<E> root;
	private int size;

	// default constructor

	/**
	 * Add the specified item to this Binary Search Tree if it is not already
	 * present. <br>
	 * pre: <tt>value</tt> != null<br>
	 * post: Add value to this tree if not already present. Return true if this
	 * tree changed as a result of this method call, false otherwise.
	 * 
	 * @param value the value to add to the tree
	 * @return false if an item equivalent to value is already present in the
	 *         tree, return true if value is added to the tree and size() = old
	 *         size() + 1
	 */
	public boolean add(E value) {
		// code from lecture
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		if (root == null) {
			root = new BSTNode<>(value);
			size++;

			return true;
		}
		final int oldSize = size;
		// general case
		recursiveAddHelp(root, value);

		return size == oldSize + 1;
	}

	// code from lecture
	// pre: node != null, val != null, handled in calling method.
	// post: add the data to the tree as a new node, size++
	private BSTNode<E> recursiveAddHelp(BSTNode<E> node, E val) {
		if (node == null) {
			// base case, create and return new leaf
			size++;
			return new BSTNode<>(val);
		}
		// recursive case, n != null
		int dir = val.compareTo(node.data);

		if (dir < 0) {
			// val belongs in the left subtree of n
			node.left = recursiveAddHelp(node.left, val);
		} else if (dir > 0) {
			// val belongs in the left subree of n
			node.right = recursiveAddHelp(node.right, val);
		}
		// already here, do nothing

		return node;
	}

	/**
	 * Remove a specified item from this Binary Search Tree if it is present.
	 * <br>
	 * pre: <tt>value</tt> != null<br>
	 * post: Remove value from the tree if present, return true if this tree
	 * changed as a result of this method call, false otherwise.
	 * 
	 * @param value the value to remove from the tree if present
	 * @return false if value was not present returns true if value was present
	 *         and size() = old size() - 1
	 */
	public boolean remove(E value) {
		// code from lecture
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		final int oldSize = size;
		root = removeHelp(root, value);

		return size == oldSize - 1;
	}

	// code from lecture
	// pre: node != null, val != null, handled in calling method
	// post: remove the node with val, size--
	private BSTNode<E> removeHelp(BSTNode<E> node, E val) {
		if (node == null) {
			// node not found
			return node;
		}
		int dir = val.compareTo(node.data);

		if (dir < 0) {
			// val belongs in the left subtree of n
			node.left = removeHelp(node.left, val);
		} else if (dir > 0) {
			// val belongs in the left subree of n
			node.right = removeHelp(node.right, val);
		} else {
			// found the node to remove
			size--;

			if (node.left == null && node.right == null) {
				// node is a leaf, return null to parent
				node = null;
			} else if (node.right == null) {
				// single child to the left
				node = node.left;
			} else if (node.left == null) {
				// single child to the right
				node = node.right;
			} else {
				// two children
				node.data = maxFromNode(node.left);
				node.left = removeHelp(node.left, node.data);
				size++;
			}
		}

		return node;
	}

	/**
	 * Check to see if the specified element is in this Binary Search Tree. <br>
	 * pre: <tt>value</tt> != null<br>
	 * post: return true if value is present in tree, false otherwise
	 * 
	 * @param value the value to look for in the tree
	 * @return true if value is present in this tree, false otherwise
	 */
	public boolean isPresent(E value) {
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}

		return findNode(root, value) != null;
	}

	// pre: val != null, handled in calling method.
	// post: returns a reference to the desired node or null if not present
	private BSTNode<E> findNode(BSTNode<E> node, E val) {
		if (node == null) {
			// node not found
			return null;
		}
		int dir = val.compareTo(node.data);

		if (dir == 0) {
			// found the node
			return node;
		}
		if (dir < 0) {
			// val belongs in the left subtree of n
			node = findNode(node.left, val);
		} else if (dir > 0) {
			// val belongs in the left subree of n
			node = findNode(node.right, val);
		}

		return node;
	}

	/**
	 * Return how many elements are in this Binary Search Tree. <br>
	 * pre: none<br>
	 * post: return the number of items in this tree
	 * 
	 * @return the number of items in this Binary Search Tree
	 */
	public int size() {
		return size;
	}

	/**
	 * return the height of this Binary Search Tree. <br>
	 * pre: none<br>
	 * post: return the height of this tree. If the tree is empty return -1,
	 * otherwise return the height of the tree
	 * 
	 * @return the height of this tree or -1 if the tree is empty
	 */
	public int height() {
		if (size == 0) {
			return -1;
		}

		return heightHelper(root, 0, 0);
	}

	// calculates the height recursively
	// pre: node != null, handled in the calling method
	// post: the height of the overall tree
	private int heightHelper(BSTNode<E> node, int depth, int max) {
		if (node.left != null) {
			max = heightHelper(node.left, depth + 1, max);

		}
		// passed under the node
		if (depth > max) {
			max = depth;
		}
		if (node.right != null) {
			max = heightHelper(node.right, depth + 1, max);
		}

		return max;
	}

	/**
	 * Return a list of all the elements in this Binary Search Tree. <br>
	 * pre: none<br>
	 * post: return a List object with all data from the tree in ascending
	 * order. If the tree is empty return an empty List
	 * 
	 * @return a List object with all data from the tree in sorted order if the
	 *         tree is empty return an empty List
	 */
	public List<E> getAll() {
		List<E> list = new ArrayList<>();

		if (root == null) {
			return list;
		}
		getAllHelper(list, root);

		return list;
	}

	// fills list recursively
	// pre: list != null, node != null, handled in calling method
	// post: filled list with all data in this BST in ascending order
	private void getAllHelper(List<E> list, BSTNode<E> node) {
		if (list.size() != size) {
			if (node.left != null) {
				getAllHelper(list, node.left);
			}
			// passed under the node
			list.add(node.data);
			if (node.right != null) {
				getAllHelper(list, node.right);
			}
		}
	}

	/**
	 * return the maximum value in this binary search tree. <br>
	 * pre: <tt>size()</tt> > 0<br>
	 * post: return the largest value in this Binary Search Tree
	 * 
	 * @return the maximum value in this tree
	 */
	public E max() {
		if (size == 0) {
			throw new IllegalArgumentException("size cannot be 0");
		}
		return maxFromNode(root);
	}

	// code from lecture
	// pre: node != null, handled in calling methods
	// post: returns the max value in this BST from the given node
	private E maxFromNode(BSTNode<E> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node.data;
	}

	/**
	 * return the minimum value in this binary search tree. <br>
	 * pre: <tt>size()</tt> > 0<br>
	 * post: return the smallest value in this Binary Search Tree
	 * 
	 * @return the minimum value in this tree
	 */
	public E min() {
		if (size == 0) {
			throw new IllegalArgumentException("size cannot be 0");
		}
		return minFromNode(root);
	}

	// pre: node != null, handled in calling methods
	// post: returns the min value in this BST from the given node
	private E minFromNode(BSTNode<E> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node.data;
	}

	/**
	 * An add method that implements the add algorithm iteratively instead of
	 * recursively. <br>
	 * pre: data != null <br>
	 * post: if data is not present add it to the tree, otherwise do nothing.
	 * 
	 * @param data the item to be added to this tree
	 * @return true if data was not present before this call to add, false
	 *         otherwise.
	 */
	public boolean iterativeAdd(E data) {
		if (data == null) {
			throw new IllegalArgumentException("data cannot be null");
		}
		if (root == null) {
			root = new BSTNode<>(data);
			size++;

			return true;
		}
		// general case
		return iterativeHelp(data);
	}

	// pre: node != null, data != null, handled in calling method.
	// post: add the data to the tree as a new node, size++
	private boolean iterativeHelp(E data) {
		final int oldSize = size;
		BSTNode<E> node = root;
		int dir = 0;

		while (size != oldSize + 1) {
			dir = data.compareTo(node.data);

			if (dir == 0) {
				// already here, do nothing
				return false;
			} else if (dir < 0) {
				// data belongs in the left subtree of node
				if (node.left == null) {
					node.left = new BSTNode<>(data);
					size++;
				} else {
					node = node.left;
				}
			} else { // dir > 0
				// data belongs in the right subtree of node
				if (node.right == null) {
					node.right = new BSTNode<>(data);
					size++;
				} else {
					node = node.right;
				}
			}
		}

		return true;
	}

	/**
	 * Return the "kth" element in this Binary Search Tree. If kth = 0 the
	 * smallest value (minimum) is returned. If kth = 1 the second smallest
	 * value is returned, and so forth. <br>
	 * pre: 0 <= kth < size()
	 * 
	 * @param kth indicates the rank of the element to get
	 * @return the kth value in this Binary Search Tree
	 */
	public E get(int kth) {
		if (kth < 0 || kth >= size()) {
			throw new IllegalArgumentException("0 <= kth < size()");
		}
		if (kth == size - 1) {
			return maxFromNode(root);
		}
		int[] cur = new int[1];
		BSTNode<E> result = getHelper(root, kth, cur);

		return result.data;
	}

	// recursively find the kth element in this BST
	// pre: node != null, handled in calling method
	// post: return the kth node
	private BSTNode<E> getHelper(BSTNode<E> node, int kth, int[] cur) {
		BSTNode<E> temp = null;

		if (node.left != null) {
			temp = getHelper(node.left, kth, cur);
			// return when found to avoid more work
			if (temp != null) {
				return temp;
			}

		}

		// passed under the node, check if its is kth node
		if (cur[0] == kth) {
			return node;
		}
		cur[0]++;

		if (node.right != null) {
			temp = getHelper(node.right, kth, cur);
		}

		return temp;
	}

	/**
	 * Return a List with all values in this Binary Search Tree that are less
	 * than the parameter <tt>value</tt>. <tt>value</tt> != null<br>
	 * 
	 * @param value the cutoff value
	 * @return a List with all values in this tree that are less than the
	 *         parameter value. If there are no values in this tree less than
	 *         value return an empty list. The elements of the list are in
	 *         ascending order.
	 */
	public List<E> getAllLessThan(E value) {
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		List<E> list = new LinkedList<>();

		if (size == 0) {
			return list;
		}
		allLessThanHelper(list, root, value);

		return list;
	}

	// recursively traverses this BST until reaching a node
	// with data greater than or equal to the given value
	// pre: params != null, handled in calling method
	// post: fills list to return in ascending order
	private void allLessThanHelper(List<E> list, BSTNode<E> node, E value) {
		if (node.left != null) {
			allLessThanHelper(list, node.left, value);
		}

		// passed under the node
		if (node.data.compareTo(value) < 0) {
			list.add(node.data);
		} else {
			// node.data >= value, finished
			return;
		}

		if (node.right != null) {
			allLessThanHelper(list, node.right, value);
		}
	}

	/**
	 * Return a List with all values in this Binary Search Tree that are greater
	 * than the parameter <tt>value</tt>. <tt>value</tt> != null<br>
	 * 
	 * @param value the cutoff value
	 * @return a List with all values in this tree that are greater than the
	 *         parameter value. If there are no values in this tree greater than
	 *         value return an empty list. The elements of the list are in
	 *         ascending order.
	 */
	public List<E> getAllGreaterThan(E value) {
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		List<E> list = new LinkedList<>();

		if (size == 0) {
			return list;
		}
		allGreaterThanHelper((LinkedList<E>) list, root, value);

		return list;
	}

	// recursively traverses this BST backwards until reaching a node
	// with data less than or equal to the given value
	// pre: params != null, handled in calling method
	// post: fills list to return in ascending order
	private void allGreaterThanHelper(LinkedList<E> list, BSTNode<E> node,
			E value) {
		if (node.right != null) {
			allGreaterThanHelper(list, node.right, value);
		}

		// passed under the node
		if (node.data.compareTo(value) > 0) {
			// add to beginning because traversing backwards
			list.addFirst(node.data);
		} else {
			// node.data <= value, finished
			return;
		}

		if (node.left != null) {
			allGreaterThanHelper(list, node.left, value);
		}
	}

	/**
	 * Find the number of nodes in this tree at the specified depth. <br>
	 * pre: none
	 * 
	 * @param d The target depth.
	 * @return The number of nodes in this tree at a depth equal to the
	 *         parameter d.
	 */
	public int numNodesAtDepth(int d) {
		if (d < 0 || size == 0) {
			return 0;
		}

		return nodesAtDepthHelper(d, root, 0);
	}

	// calculates the depth recursively
	// pre: node != null, handled in the calling method
	// post: return the total amount of node at depth d
	private int nodesAtDepthHelper(int d, BSTNode<E> node, int depth) {
		int count = 0;

		if (depth <= d) {
			if (node.left != null) {
				count += nodesAtDepthHelper(d, node.left, depth + 1);
			}
			// passed under the node
			if (depth == d) {
				count++;
			}
			if (node.right != null) {
				count += nodesAtDepthHelper(d, node.right, depth + 1);
			}
		}

		return count;
	}

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

	private void printTree(BSTNode<E> n, String spaces) {
		if (n != null) {
			printTree(n.getRight(), spaces + "  ");
			System.out.println(spaces + n.getData());
			printTree(n.getLeft(), spaces + "  ");
		}
	}

	private static class BSTNode<E extends Comparable<? super E>> {
		private E data;
		private BSTNode<E> left;
		private BSTNode<E> right;

		public BSTNode() {
			this(null);
		}

		public BSTNode(E initValue) {
			this(null, initValue, null);
		}

		public BSTNode(BSTNode<E> initLeft, E initValue, BSTNode<E> initRight) {
			data = initValue;
			left = initLeft;
			right = initRight;
		}

		public E getData() {
			return data;
		}

		public BSTNode<E> getLeft() {
			return left;
		}

		public BSTNode<E> getRight() {
			return right;
		}

		public void setData(E theNewValue) {
			data = theNewValue;
		}

		public void setLeft(BSTNode<E> theNewLeft) {
			left = theNewLeft;
		}

		public void setRight(BSTNode<E> theNewRight) {
			right = theNewRight;
		}
	}
}
