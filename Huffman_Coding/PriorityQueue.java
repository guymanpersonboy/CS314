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

import java.util.Iterator;
import java.util.LinkedList;

/*
 * A LinkedList implementation of a PriorityQueue. Operations perform as could
 * be expected of a LinkedList, except for enqueue. Dequeue is synonymous with
 * remove for LinkedList.
 * 
 */
public class PriorityQueue<E extends Comparable<? super E>> {

	private LinkedList<E> con;

	/**
	 * Construct a PriorityQueue with an empty storage container.
	 */
	public PriorityQueue() {
		con = new LinkedList<>();
	}

	/**
	 * Append in ascending order based on the number represented by the chunk of
	 * bits. Handles ties fairly by enqueueing the item behind items already
	 * present of equal value
	 * 
	 * @param item The item to be enqueued
	 * @return true if item was added to this queue
	 */
	public boolean enqueue(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}
		if (size() == 0) {
			return con.add(item);
		}
		// item has the new highest freq, add to the end
		if (item.compareTo(con.getLast()) >= 0) {
			return con.add(item);
		}
		Iterator<E> listIt = con.iterator();
		int index = 0;
		E val;

		// append item to the correct position
		while (listIt.hasNext()) {
			val = listIt.next();
			int comp = item.compareTo(val);

			if (comp < 0) {
				con.add(index, item);
				return true;
			} else if (comp == 0) {
				// break ties fairly
				index += goToBack(listIt, val, item);
				// adds to the end of equal elements
				con.add(index, item);
				return true;
			}

			index++;
		}

		return false;
	}

	// gets the offset to add to index so that index is positioned at the end of
	// equal elements
	// pre: listIt != null, val != null, item != null
	// post: return the index to add to index in enqueue to get correct position
	// to append the item
	private int goToBack(Iterator<E> listIt, E val, E item) {
		if (listIt == null || val == null || item == null) {
			throw new IllegalArgumentException(
					"Violation of of preconditions: listIt != null, val != null, item != null");
		}
		int index = 0;

		while (val.compareTo(item) == 0 && listIt.hasNext()) {
			val = listIt.next();
			index++;
		}

		return index;

	}

	/**
	 * Retrieve and remove the head (first element of this queue.
	 * 
	 * @return The head of this queue
	 */
	public E dequeue() {
		return con.remove();
	}

	/**
	 * Removes the first occurent of the specified element from this queue if it
	 * is present. If the element is not present, the queue is unchanged.
	 * 
	 * @param item Element to be remove
	 * @return true if this list contained the specified element
	 */
	public boolean dequeue(E item) {
		return con.remove(item);
	}

	/**
	 * Returns the element at the specified position in this queue.
	 * 
	 * @param index Index of the element to return.
	 * @return the element at the specified position.
	 */
	public E get(int index) {
		return con.get(index);
	}

	/**
	 * Return the first element in this list.
	 * 
	 * @return the first element in this list.
	 */
	public E getFirst() {
		return con.getFirst();
	}

	/**
	 * Return the last element in this list.
	 * 
	 * @return the last element in this list.
	 */
	public E getLast() {
		return con.getLast();
	}

	/**
	 * Return the number of elements in this list.
	 * 
	 * @return the number of elements in this list.
	 */
	public int size() {
		return con.size();
	}

	/**
	 * Returns an iterator over the elements in this queue.
	 * 
	 * @return an iterator over the elements in this queue (in proper sequence).
	 */
	public Iterator<E> iterator() {
		return con.iterator();
	}

	/**
	 * Returns a string representation of this queue. The string representation
	 * consists of a list of the queue's elements in the order they are returned
	 * by its iterator, enclosed in square brackets("[]").
	 * 
	 * @return a string representation of this queue.
	 */
	public String toString() {
		return con.toString();
	}
}
