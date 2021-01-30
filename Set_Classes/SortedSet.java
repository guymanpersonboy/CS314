package Set_Classes;
/*  Student information for assignment:
 *
 *  On OUR honor, EMMANUEL IHIM and CHRISTOPHER CARRASCO), 
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: eui59
 *  email address: ihim_emmanuel@utexas.edu
 *  TA name: Andrew Smith
 *  
 *  Student 2: Christopher Carrasco
 *  UTEID: cc66496
 *  email address: chris.carrasco@att.net  
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must be
 * used as the internal storage container. For methods involving two set, if
 * that method can be done more efficiently if the other set is also a SortedSet
 * do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
	
	private ArrayList<E> myCon;

	/** O(1)
	 * create an empty SortedSet
	 */
	public SortedSet() {
		myCon = new ArrayList<>();
	}

	/** O(NLogN)
	 * create a SortedSet out of an unsorted set. O(NLogN) <br>
	 * 
	 * @param other != null
	 */
	public SortedSet(ISet<E> other) {
		this();
		if (other == null) {
			throw new IllegalArgumentException("other != null");
		}

		for (E val : other) { // O(N)
			myCon.add(val); // O(1)
		}

		quicksort(0, myCon.size() - 1); // O(NLogN)
	}

	// O(1) Creates an instance of SortedSet with a specified container
	private SortedSet(ArrayList<E> list) {
		if (list == null) {
			throw new IllegalArgumentException("list != null");
		}

		myCon = list;
	}

	// Sorting Ascending order O(NlogN) - Inspiration from Mike Scott
	private void quicksort(int start, int stop) {
		if (start < stop) {
			E pivotVal = myCon.get(start);
			int pivotIndex = start + 1;

			for (int i = pivotIndex; i <= stop; i++) {
				if (myCon.get(i).compareTo(pivotVal) < 0) {
					swapReference(i, pivotIndex);
					pivotIndex++;
				}
			}

			swapReference(pivotIndex - 1, start);
			quicksort(start, pivotIndex - 1);
			quicksort(pivotIndex, stop);
		}
	}

	// Swaps values in list
	private void swapReference(int pivot, int start) {
		E temp = myCon.get(start);
		myCon.set(start, myCon.get(pivot));
		myCon.set(pivot, temp);
	}

	/** O(1)
	 * Return the smallest element in this SortedSet. <br>
	 * pre: size() != 0
	 * 
	 * @return the smallest element in this SortedSet.
	 */
	public E min() {
		if (myCon.size() == 0) {
			throw new IllegalArgumentException("size != 0");
		}

		return myCon.get(0);
	}

	/** O(1)
	 * Return the largest element in this SortedSet. <br>
	 * pre: size() != 0
	 * 
	 * @return the largest element in this SortedSet.
	 */
	public E max() {
		if (myCon.size() == 0) {
			throw new IllegalArgumentException("size != 0");
		}

		return myCon.get(myCon.size() - 1);
	}

	/** O(N)
	 * Add an item to this set. <br>
	 * item != null
	 * 
	 * @param item the item to be added to this set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean add(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item != null");
		}

		int index = binarySearch(item);

		if (index < 0) {
			myCon.add(-(index + 1), item);
			return true;
		}

		return false;
	}

	// Find the index of the target. If it's not in the list,
	// returns the negative index of where it should be inserted
	// Inspiration from Mike Scott
	private int binarySearch(E tgt) {
		int low = 0;
		int high = myCon.size() - 1;

		while (low <= high) {
			int mid = low + ((high - low)) / 2;
			int compareResult = tgt.compareTo(myCon.get(mid));

			if (compareResult == 0) {
				return mid;
			} else if (compareResult > 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -low - 1;
	}

	/** O(N) - Sorted; O(NLogN) - Unsorted
	 * A union operation. Add all items of otherSet that are not already present
	 * in this set to this set.
	 * 
	 * @param otherSet != null
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet != null");
		}

		if (otherSet instanceof SortedSet) {
			return addAllSortedSet((SortedSet<E>) otherSet);
		} else {
			return addAllUnsortedSet(otherSet);
		}
	}

	// O(N)
	// Adds all elements from otherSet to this set
	private boolean addAllSortedSet(SortedSet<E> otherSet) {
		ArrayList<E> temp = new ArrayList<>();
		int leftPos = 0;
		int rightPos = 0;
		int leftEnd = myCon.size() - 1;
		int rightEnd = otherSet.myCon.size() - 1;

		// main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd) {	
			if (myCon.get(leftPos).compareTo(otherSet.myCon.get(rightPos)) < 0) {
				temp.add(myCon.get(leftPos));
				leftPos++;
			} else if (myCon.get(leftPos).compareTo(otherSet.myCon.get(rightPos)) > 0){
				temp.add(otherSet.myCon.get(rightPos));
				rightPos++;
			} else {
				temp.add(myCon.get(leftPos));
				leftPos++;
				rightPos++;
			}
			
		}

		copyRemainder(temp, leftPos, leftEnd, myCon);
		copyRemainder(temp, rightPos, rightEnd, otherSet.myCon);

		if (!temp.isEmpty() && !myCon.equals(temp)) {
			myCon = temp;
			return true;
		}

		return false;
	}

	// O(NLogN)
	// Adds all elements from otherSet to this set
	private boolean addAllUnsortedSet(ISet<E> otherSet) {
		boolean changed = false;

		for (E val : otherSet) { // O(N)
			if (!this.contains(val)) { // O(LogN)
				myCon.add(val); // O(1)
				changed = true;
			}
		}

		quicksort(0, myCon.size() - 1); // O(NLogN)
		return changed;
	}

	/** O(N)
	 * Make this set empty. <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	public void clear() {
		myCon.clear();
	}

	/** O(LogN)
	 * Determine if item is in this set. <br>
	 * pre: item != null
	 * 
	 * @param item element whose presence is being tested. Item may not equal
	 *             null.
	 * @return true if this set contains the specified item, false otherwise.
	 */
	public boolean contains(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Item may not equal null");
		}

		return binarySearch(item) >= 0; // O(LogN)
	}

	/** O(N) - Sorted; O(NLogN) - Unsorted
	 * Determine if all of the elements of otherSet are in this set. <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return true if this set contains all of the elements in otherSet, false
	 *         otherwise.
	 */
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet may not equal null");
		}

		if (otherSet instanceof SortedSet) {
			return containsAllSorted(otherSet);
		} else {
			return containsAllUnsorted(otherSet);
		}
	}

	// O(N)
	// Returns true if all elements in other are also in this false otherwise
	private boolean containsAllSorted(ISet<E> otherSet) {
		Iterator<E> thisIt = this.iterator();
		Iterator<E> otherIt = otherSet.iterator();

		if (!thisIt.hasNext() && otherIt.hasNext()) {
			// if this empty then return false because the null set cannot
			// contain elements
			return false;
		} else if (thisIt.hasNext() && !otherIt.hasNext()) {
			// if other set is empty return because the this contains the null
			// set
			return true;
		} else { // both sets have elements
			E thisCur = thisIt.next();
			E otherCur = otherIt.next();
			boolean identical = thisCur.equals(otherCur);

			// find an identical element in this set with the first element in
			// other set
			while (thisIt.hasNext() && !identical) {
				thisCur = thisIt.next();
				identical = thisCur.equals(otherCur);
			}

			// check that all after first identical are still identical for all
			// of otherSet
			while (thisIt.hasNext() && otherIt.hasNext() && identical) {
				thisCur = thisIt.next();
				otherCur = otherIt.next();
				identical = thisCur.equals(otherCur);
			}

			return !otherIt.hasNext() && identical;
		}
	}

	// O(NLogN)
	// Returns true if all elements in other are also in this false otherwise
	private boolean containsAllUnsorted(ISet<E> otherSet) {
		for (E val : otherSet) { // O(N)
			if (!this.contains(val)) { // O(LogN)
				return false;
			}
		}

		return true;
	}

	/** O(N) - Sorted; O(NLogN) - Unsorted
	 * Create a new set that is the difference of this set and otherSet. Return
	 * an ISet of elements that are in this Set but not in otherSet. Also called
	 * the relative complement. <br>
	 * Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
	 * A.difference(B) would return an ISet with elements [X, Y] while
	 * B.difference(A) would return an ISet with elements [W]. <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the difference of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return a set that is the difference of this set and otherSet
	 */
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet may not equal null");
		}

		if (otherSet instanceof SortedSet) {
			return differenceSorted((SortedSet<E>) otherSet);
		} else {
			return differenceUnsorted(otherSet);
		}
	}

	// O(N)
	// Returns a set of elements in this set that are not in otherSet
	private ISet<E> differenceSorted(SortedSet<E> otherSet) {
		ArrayList<E> temp = new ArrayList<>();
		int leftPos = 0;
		int rightPos = 0;
		int leftEnd = myCon.size() - 1;
		int rightEnd = otherSet.myCon.size() - 1;

		// main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd) {	
			if (myCon.get(leftPos).compareTo(otherSet.myCon.get(rightPos)) < 0) {
				temp.add(myCon.get(leftPos));
				leftPos++;
			} else if (myCon.get(leftPos).compareTo(otherSet.myCon.get(rightPos)) > 0){
				rightPos++;
			} else {
				leftPos++;
				rightPos++;
			}
		}

		copyRemainder(temp, leftPos, leftEnd, myCon);
		return new SortedSet<>(temp);
	}

	// Copies the remaining elements from myCon into temp list
	private void copyRemainder(ArrayList<E> temp, int pos, int end, ArrayList<E> myCon) {
		// copy rest of left half
		while (pos <= end) {
			temp.add(myCon.get(pos));
			pos++;
		}
	}

	// O(NlogN)
	// Returns a set of elements in this set that are not in otherSet
	private ISet<E> differenceUnsorted(ISet<E> otherSet) {
		ArrayList<E> result = new ArrayList<>();

		for (E val : this) { // O(N)
			if (!otherSet.contains(val)) { // O(LogN)
				result.add(val); // O(1)
			}
		}

		return new SortedSet<>(result);
	}

	/** O(N) - Sorted; O(N^2) - Unsorted
	 * Determine if this set is equal to other. Two sets are equal if they have
	 * exactly the same elements. The order of the elements does not matter.
	 * <br>
	 * pre: none
	 * 
	 * @param other the object to compare to this set
	 * @return true if other is a Set and has the same elements as this set
	 */
	public boolean equals(Object other) {
		if (other instanceof SortedSet) {
			return sortedEquals((SortedSet<?>) other);
		} else if (other instanceof UnsortedSet) {
			return super.equals(other);
		} else {
			return false;
		}
	}

	// O(N)
	// Checks if two sorted sets are equal
	private boolean sortedEquals(SortedSet<?> other) {
		if (myCon.size() == other.size()) {
			Iterator<E> thisIt = this.iterator();
			Iterator<?> otherIt = other.iterator();

			while (thisIt.hasNext()) { // O(N)
				if (!thisIt.next().equals(otherIt.next())) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	/** O(N) - Sorted; O(NLogN) - Unsorted
	 * create a new set that is the intersection of this set and otherSet. <br>
	 * pre: otherSet != null<br>
	 * <br>
	 * post: returns a set that is the intersection of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return a set that is the intersection of this set and otherSet
	 */
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet may not equal null");
		}

		if (otherSet instanceof SortedSet) {
			return intersectionSorted((SortedSet<E>) otherSet);
		} else {
			return intersectionUnsorted(otherSet);
		}
	}

	// O(N)
	// Returns a new sorted set whose elements are common between
	// the two sets
	private ISet<E> intersectionSorted(SortedSet<E> otherSet) {
		ArrayList<E> otherCon = otherSet.myCon;
		ArrayList<E> result = new ArrayList<>();
		int leftPos = 0;
		int rightPos = 0;
		int leftEnd = myCon.size() - 1;
		int rightEnd = otherCon.size() - 1;

		// main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (myCon.get(leftPos).compareTo(otherCon.get(rightPos)) < 0) {
				leftPos++;
			} else if (myCon.get(leftPos).compareTo(otherCon.get(rightPos)) > 0){
				rightPos++;
			} else {
				result.add(myCon.get(leftPos));
				leftPos++;
				rightPos++;
			}
		}

		return new SortedSet<>(result);
	}

	// O(NlogN)
	// Combines a sorted and unsorted set. Returns a new sorted set
	// whose elements are common between the two sets
	private ISet<E> intersectionUnsorted(ISet<E> otherSet) {
		ArrayList<E> result = new ArrayList<>();

		for (E val : this) { // O(N)
			if (otherSet.contains(val)) { // O(LogN)
				result.add(val); // O(1)
			}
		}

		return new SortedSet<>(result);
	}

	/** O(1)
	 * Return an Iterator object for the elements of this set. pre: none
	 * 
	 * @return an Iterator object for the elements of this set
	 */
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	/** O(1)
	 * Return the number of elements of this set. pre: none
	 * 
	 * @return the number of items in this set
	 */
	public int size() {
		return myCon.size();
	}

	/** O(N)
	 * Create a new set that is the union of this set and otherSet. <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the union of this set and otherSet. Neither
	 * this set or otherSet are altered as a result of this operation. <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return a set that is the union of this set and otherSet
	 */
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet may not equal null");
		}

		ISet<E> result = new SortedSet<>();
		result.addAll(this); // O(N)
		result.addAll(otherSet); // O(N)
		return result;
	}
}
