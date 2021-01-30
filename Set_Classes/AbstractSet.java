package Set_Classes;
/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), 
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID:
 *  email address:
 *  TA name:
 *  
 *  Student 2 
 *  UTEID:
 *  email address:   
 */

import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {
    
	// default constructor
    public AbstractSet() { }

    /* NO INSTANCE VARIABLES ALLOWED.
     * 
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * 
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     * 
     * NO METHODS ADDED other than those in ISet and Object.
     */

	/**
	 * A union operation. Add all items of otherSet that are not already present
	 * in this set to this set.
	 * 
	 * @param otherSet != null
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
		}
		
        boolean changed = false;
        
        for(E val : otherSet) {
            if (this.add(val)) {
                changed = true;
            }
        }

        return changed;
	}

	/**
	 * Make this set empty. O(N) <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	public void clear() {
        Iterator<E> it = this.iterator();

        while(it.hasNext()) {
            it.next();
            it.remove();
        }
	}

	/**
	 * Determine if item is in this set. <br>
	 * pre: item != null
	 * 
	 * @param item element whose presence is being tested. Item may not equal
	 *             null.
	 * @return true if this set contains the specified item, false otherwise.
	 */
	public boolean contains(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot equal null");
		}

        for(E val : this) {
            if(val.equals(item)) {
                return true;
            }
        }

        return false;
	}

	/**
	 * Determine if all of the elements of otherSet are in this set. O(N^2) <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return true if this set contains all of the elements in otherSet, false
	 *         otherwise.
	 */
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot equal null");
		}

        for(E val : otherSet) {
            if(!this.contains(val)) {
                return false;
            }
        }

        return true;
	}

	/**
	 * Determine if this set is equal to other. Two sets are equal if they have
	 * exactly the same elements. The order of the elements does not matter.
	 * <br>
	 * pre: none
	 * 
	 * @param other the object to compare to this set
	 * @return true if other is a Set and has the same elements as this set
	 */
	public boolean equals(Object other) { 
        // Checks that other is an Iset and that size matches this set
        if(other instanceof ISet && this.size() == ((ISet<?>) other).size()) {

			// for each element in this set
			for (E thisVal : this) {
				boolean found = false;
				Iterator<?> otherIt = ((ISet<?>) other).iterator();

				// Loops through other set until find a value that equals
				while (!found && otherIt.hasNext()) {
					if (thisVal.equals(otherIt.next())) {
						found = true;
					}
				}

				// Returns false because value in this set isn't in other
				if (!found) {
					return false;
				}
			}
			// all elements in this set have been found in otherSet
            return true;
        }

        return false;
    }
	

	/** O(N)
	 * Return a String version of this set. Format is (e1, e2, ... en)
	 * 
	 * @return A String version of this set.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		String seperator = ", ";
		result.append("(");

		for (E e : this) {
			result.append(e);
			result.append(seperator);
		}
		// get rid of extra separator
		if (this.size() > 0)
			result.setLength(result.length() - seperator.length());

		result.append(")");
		return result.toString();
	}

	/** O(N)
	 * Remove the specified item from this set if it is present. pre: item !=
	 * null
	 * 
	 * @param item the item to remove from the set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise
	 */
	public boolean remove(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot equal null");
		}

		Iterator<E> it = this.iterator();

        while(it.hasNext()) {
            if (it.next().equals(item)) {
                it.remove();
                return true;
            }
        }

        return false;
	}

	/** O(N)
	 * Return the number of elements of this set. pre: none
	 * 
	 * @return the number of items in this set
	 */
	public int size() {
        int size = 0;

		for (E item : this) {
			size++;
		}

        return size;
	}

	/** O(N^2) - UnsortedSet
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
		if(otherSet == null) {
			throw new IllegalArgumentException("otherSet != null");
		}
		
		ISet<E> set = this.difference(otherSet);

		for(E val: otherSet) {
			set.add(val);
		}

		return set;
	}
}
