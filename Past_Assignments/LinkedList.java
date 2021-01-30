package Past_Assignments;
/*
 * Student information for assignment:
 * On my honor, CHRISTOPHER CARRASCO, this programming assignment is my own work
 * and I have not provided this code to any other student.
 * UTEID: cc66496
 * email address: chris.carrasco@att.net
 * TA name: Andrew
 * Number of slip days I am using: 1
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * A linked list class that uses doubly linked nodes and a header node
 */
public class LinkedList<E> implements IList<E> {

	// eliminates the special cases of going from empty to non-empty
	private final DoubleListNode<E> HEADER;
	private int size;

	/**
	 * Constructs a new LinkedList. Uses a header node to make structure
	 * circular. HEADER has no data, HEADER'S prev reference is the last node in
	 * structure always, and the last node's next reference refers to HEADER.
	 * HEADER next reference is the first node in structure always and the first
	 * node's prev reference references to HEADER. O(1)
	 */
	public LinkedList() {
		HEADER = new DoubleListNode<>();
		HEADER.setPrev(HEADER);
		HEADER.setNext(HEADER);
	}

	/**
	 * add item to the front of the list. O(1) <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(0) = item
	 *
	 * @param item the data to add to the front of this list
	 */
	public void addFirst(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		DoubleListNode<E> newNode = new DoubleListNode<>(null, item, null);
		DoubleListNode<E> nextNode = HEADER.getNext();

		nextNode.setPrev(newNode);
		newNode.setPrev(HEADER);
		newNode.setNext(nextNode);
		HEADER.setNext(newNode);
		size++;
	}

	/**
	 * add item to the end of the list. O(1) <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(size() -1) = item
	 *
	 * @param item the data to add to the end of this list
	 */
	public void addLast(E item) {
		// preconditions in add()
		add(item);
	}

	/**
	 * remove and return the first element of this list. O(1) <br>
	 * pre: size() > 0 <br>
	 * post: size() = old size() - 1
	 *
	 * @return the old first element of this list
	 */
	public E removeFirst() {
		if (size <= 0) {
			throw new IllegalArgumentException("size must be greater than 0");
		}
		DoubleListNode<E> firstNode = HEADER.getNext();
		DoubleListNode<E> nextNode = firstNode.getNext();

		HEADER.setNext(nextNode);
		nextNode.setPrev(HEADER);
		size--;

		return firstNode.getData();
	}

	/**
	 * remove and return the last element of this list. O(1) <br>
	 * pre: size() > 0 <br>
	 * post: size() = old size() - 1
	 *
	 * @return the old last element of this list
	 */
	public E removeLast() {
		if (size <= 0) {
			throw new IllegalArgumentException("size must be greater than 0");
		}
		DoubleListNode<E> lastNode = HEADER.getPrev();
		DoubleListNode<E> prevNode = lastNode.getPrev();

		HEADER.setPrev(prevNode);
		prevNode.setNext(HEADER);
		size--;

		return lastNode.getData();
	}

	/**
	 * Return a String version of this list enclosed in square brackets, [].
	 * Elements are in order based on position in the list with the first
	 * element first. Adjacent elements are separated by comma's. O(N)
	 * 
	 * @return a String representation of this IList
	 */
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		for (E element : this) {
			sb.append(element);
			sb.append(", ");
		}
		// deletes the last comma and space char
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");

		return sb.toString();
	}

	/**
	 * Determine if this IList is equal to other. Two ILists are equal if they
	 * contain the same elements in the same order. O(N)
	 * 
	 * @return true if this IList is equal to other, false otherwise
	 */
	public boolean equals(Object other) {
		boolean result = other instanceof IList;

		if (result) {
			IList<?> otherList = (IList<?>) other;

			result = otherList.size() == this.size;
			if (result) {
				Iterator<?> otherNode = otherList.iterator();
				Iterator<E> thisNode = this.iterator();

				while (otherNode.hasNext()) {
					if (!otherNode.next().equals(thisNode.next())) {
						return false;
					}
				}
				// all elements are equal;
				return true;
			}
		}

		return result;
	}

	/**
	 * Add an item to the end of this list. O(1) <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(size() - 1) = item
	 * 
	 * @param item the data to be added to the end of this list, item != null
	 */
	@Override
	public void add(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		DoubleListNode<E> addNode = new DoubleListNode<>(HEADER.getPrev(), item,
				HEADER);
		DoubleListNode<E> lastNode = HEADER.getPrev();

		HEADER.setPrev(addNode);
		lastNode.setNext(addNode);
		size++;
	}

	/*
	 * Get the node at the desired pos. O(1) best case. O(N) for general case.
	 * Pre: 0 <= pos <= size(). Post: return the node at the desired position.
	 */
	private DoubleListNode<E> getNode(int pos) {
		if (pos < 0 || pos > size()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= pos <= size()");
		}
		if (pos == 0) {
			return HEADER.getNext();
		}
		if (pos == size - 1) {
			return HEADER.getPrev();
		}
		if (pos == size) {
			return HEADER;
		}

		return getNodeAtPos(pos);
	}

	/*
	 * Helper method for getNode(). O(N) Pre: none. Post: return the node
	 * at the desired position
	 */
	private DoubleListNode<E> getNodeAtPos(int pos) {
		DoubleListNode<E> nodeAtPos;

		// if the pos is in the second half of the list then iterate backwards
		// from the end, otherwise it in the first half and iterate forwards
		if (pos > size / 2) {
			int index = size - 1;
			nodeAtPos = HEADER.getPrev();

			while (index != pos) {
				nodeAtPos = nodeAtPos.getPrev();
				index--;
			}
		} else {
			int index = 0;
			nodeAtPos = HEADER.getNext();

			while (index != pos) {
				nodeAtPos = nodeAtPos.getNext();
				index++;
			}
		}

		return nodeAtPos;
	}

	/**
	 * Insert an item at a specified position in the list. O(N) <br>
	 * pre: 0 <= pos <= size(), item != null <br>
	 * post: size() = old size() + 1, get(pos) = item, all elements in the list
	 * with a position >= pos have a position = old position + 1
	 * 
	 * @param pos  the position to insert the data at in the list
	 * @param item the data to add to the list, item != null
	 */
	@Override
	public void insert(int pos, E item) {
		if (pos < 0 || pos > size() || item == null) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= pos <= size(), item != null");
		}
		if (pos == 0) {
			addFirst(item);
		} else if (pos == size) {
			add(item);
		} else {
			DoubleListNode<E> insertNode = new DoubleListNode<>(null, item,
					null);
			DoubleListNode<E> nodeAtPos = getNode(pos);

			insertNode.setNext(nodeAtPos);
			insertNode.setPrev(nodeAtPos.getPrev());
			nodeAtPos.setPrev(insertNode);
			insertNode.getPrev().setNext(insertNode);
			size++;
		}
	}

	/**
	 * Change the data at the specified position in the list. the old data at
	 * that position is returned. O(N) <br>
	 * pre: 0 <= pos < size(), item != null <br>
	 * post: get(pos) = item, return the old get(pos)
	 * 
	 * @param pos  the position in the list to overwrite
	 * @param item the new item that will overwrite the old item, item != null
	 * @return the old data at the specified position
	 */
	@Override
	public E set(int pos, E item) {
		if (pos < 0 || pos >= size() || item == null) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= pos < size(), item != null");
		}
		DoubleListNode<E> nodeAtPos = getNode(pos);

		E oldData = nodeAtPos.getData();
		nodeAtPos.setData(item);

		return oldData;
	}

	/**
	 * Get an element from the list. O(N) <br>
	 * pre: 0 <= pos < size() <br>
	 * post: return the item at pos
	 * 
	 * @param pos specifies which element to get
	 * @return the element at the specified position in the list
	 */
	@Override
	public E get(int pos) {
		if (pos < 0 || pos >= size()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= pos < size()");
		}

		return getNode(pos).getData();
	}

	/**
	 * Remove an element in the list based on position. O(N) <br>
	 * pre: 0 <= pos < size() <br>
	 * post: size() = old size() - 1, all elements of list with a position > pos
	 * have a position = old position - 1
	 * 
	 * @param pos the position of the element to remove from the list
	 * @return the data at position pos
	 */
	@Override
	public E remove(int pos) {
		if (pos < 0 || pos >= size()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= pos < size()");
		}
		if (pos == 0) {
			return removeFirst();
		}
		if (pos == size - 1) {
			return removeLast();
		}
		DoubleListNode<E> nodeAtPos = getNode(pos);
		DoubleListNode<E> prevNode = nodeAtPos.getPrev();
		DoubleListNode<E> nextNode = nodeAtPos.getNext();

		E removedData = nodeAtPos.getData();
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		size--;

		return removedData;
	}

	/**
	 * Remove the first occurrence of obj in this list. Return <tt>true</tt> if
	 * this list changed as a result of this call, <tt>false</tt> otherwise.
	 * O(N) <br>
	 * pre: obj != null <br>
	 * post: if obj is in this list the first occurrence has been removed and
	 * size() = old size() - 1. If obj is not present the list is not altered in
	 * any way.
	 * 
	 * @param obj The item to remove from this list. obj != null
	 * @return Return <tt>true</tt> if this list changed as a result of this
	 *         call, <tt>false</tt> otherwise.
	 */
	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("obj cannot be null");
		}
		DoubleListNode<E> curNode = HEADER.getNext();
		int index = 0;

		while (index < size) {
			if (curNode.getData().equals(obj)) {
				DoubleListNode<E> prevNode = curNode.getPrev();
				DoubleListNode<E> nextNode = curNode.getNext();

				nextNode.setPrev(prevNode);
				prevNode.setNext(nextNode);
				size--;

				return true;
			}
			curNode = curNode.getNext();
			index++;
		}

		return false;
	}

	/**
	 * Return a sublist of elements in this list from <tt>start</tt> inclusive
	 * to <tt>stop</tt> exclusive. This list is not changed as a result of this
	 * call. O(N) <br>
	 * pre: <tt>0 <= start <= size(), start <= stop <= size()</tt> <br>
	 * post: return a list whose size is stop - start and contains the elements
	 * at positions start through stop - 1 in this list.
	 * 
	 * @param start index of the first element of the sublist.
	 * @param stop  stop - 1 is the index of the last element of the sublist.
	 * @return a list with <tt>stop - start</tt> elements, The elements are from
	 *         positions <tt>start</tt> inclusive to <tt>stop</tt> exclusive in
	 *         this list. If start == stop an empty list is returned.
	 */
	@Override
	public IList<E> getSubList(int start, int stop) {
		if (start < 0 || start > size() || stop < start || stop > size()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= start <= size(),"
							+ " start <= stop <= size()");
		}
		IList<E> subList = new LinkedList<E>();

		if (start != size()) {
			DoubleListNode<E> curNode = getNode(start);

			for (int index = start; index < stop; index++) {
				subList.add(curNode.getData());
				curNode = curNode.getNext();
			}
		}

		return subList;
	}

	/**
	 * Return the size of this list. In other words the number of elements in
	 * this list. O(1) <br>
	 * pre: none <br>
	 * post: return the number of items in this list
	 * 
	 * @return the number of items in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Find the position of an element in the list. O(N) <br>
	 * pre: item != null <br>
	 * post: return the index of the first element equal to item or -1 if item
	 * is not present
	 * 
	 * @param item the element to search for in the list. item != null
	 * @return return the index of the first element equal to item or a -1 if
	 *         item is not present
	 */
	@Override
	public int indexOf(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		int pos = 0;

		for (E element : this) {
			if (element.equals(item)) {
				return pos;
			}
			pos++;
		}

		return -1;
	}

	/**
	 * Find the position of an element in the list starting at a specified
	 * position. O(N) <br>
	 * pre: 0 <= pos < size(), item != null <br>
	 * post: return the index of the first element equal to item starting at pos
	 * or -1 if item is not present from position pos onward.
	 * 
	 * @param item the element to search for in the list. Item != null
	 * @param pos  the position in the list to start searching from
	 * @return starting from the specified position return the index of the
	 *         first element equal to item or a -1 if item is not present
	 *         between pos and the end of the list
	 */
	@Override
	public int indexOf(E item, int pos) {
		if (pos < 0 || pos >= size() || item == null) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= pos < size(), item != null");
		}
		DoubleListNode<E> curNode = getNode(pos);

		int index = pos;
		// iterate until item is found
		while (index < size && !curNode.getData().equals(item)) {
			curNode = curNode.getNext();
			index++;
		}
		if (index == size) {
			return -1;
		}

		return index;
	}

	/**
	 * return the list to an empty state. O(1) <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	@Override
	public void makeEmpty() {
		HEADER.setPrev(HEADER);
		HEADER.setNext(HEADER);
		size = 0;
	}

	/**
	 * Remove all elements in this list from <tt>start</tt> inclusive to
	 * <tt>stop</tt> exclusive. O(N) <br>
	 * pre: <tt>0 <= start <= size(), start <= stop <= size()</tt> <br>
	 * post: <tt>size() = old size() - (stop - start)</tt>
	 * 
	 * @param start position at beginning of range of elements to be removed
	 * @param stop  stop - 1 is the position at the end of the range of elements
	 *              to be removed
	 */
	@Override
	public void removeRange(int start, int stop) {
		if (start < 0 || start > size() || stop < start || stop > size()) {
			throw new IllegalArgumentException(
					"Violation of preconditions: 0 <= start <= size(),"
							+ " start <= stop <= size()");
		}
		if (stop - 1 == start) {
			remove(start);
		} else if (stop - start == size) {
			makeEmpty();
		} else if (start != stop) {
			DoubleListNode<E> startNode = getNode(start).getPrev();
			DoubleListNode<E> stopNode = getNode(stop);

			startNode.setNext(stopNode);
			stopNode.setPrev(startNode);

			size = size - (stop - start);
		}
	}

	/**
	 * return an Iterator for this list. O(1) <br>
	 * pre: none <br>
	 * post: return an Iterator object for this List
	 */
	@Override
	public Iterator<E> iterator() {
		return new LLIterator();
	}

	/*
	 * Creates an Iterator for this list to be returned in iterator()
	 */
	private class LLIterator implements Iterator<E> {

		private DoubleListNode<E> curNode;
		private boolean removable;

		// Construct a new LLIterator. curNode = HEADER, removable = false. O(1)
		public LLIterator() {
			curNode = HEADER;
		}

		/*
		 * determine if reached the end of the list. O(1) Pre: none. Post:
		 * return true if the next node in the list is not the HEADER node.
		 */
		public boolean hasNext() {
			return !curNode.getNext().equals(HEADER);
		}

		/*
		 * Move down the list one and get data to return. O(1) Pre: hasNext() ==
		 * true. Post: return the next node's data
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			curNode = curNode.getNext();
			removable = true;

			return curNode.getData();
		}

		/*
		 * Remove the node just passed over using next(), size = size - 1. O(1)
		 * Pre: removable == true. Post: remove curNode from LinkedList.
		 */
		public void remove() {
			if (!removable) {
				throw new IllegalStateException();
			}
			removable = false;
			DoubleListNode<E> prevNode = curNode.getPrev();
			DoubleListNode<E> nextNode = curNode.getNext();

			prevNode.setNext(nextNode);
			nextNode.setPrev(prevNode);

			size--;
		}

	}

}
