package Set_Classes;
/*  Student information for assignment:
 *
 *  On OUR honor, EMMANUEL IHIM and CHRISTOPHER CARRASCO,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 1
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
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
    private ArrayList<E> myCon;

    // O(1)
    public UnsortedSet() {
        super();
        myCon = new ArrayList<>();
    }
    
    /** O(N)
     * Add an item to this set. O(N)
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise.
     */
    public boolean add(E item) {
        if(item == null) {
            throw new IllegalArgumentException("item cannot equal null");
        }
        
        if(!this.contains(item)){ // O(N)
            myCon.add(item); // O(N)
            return true;
        }
        
        return false;
    }

    /** O(N)
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        myCon.clear();
    }

    /** O(N^2)
     * Create a new set that is the difference of this set and otherSet. 
     * Return an ISet of elements that are in this Set but not in otherSet. 
     * Also called the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] 
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        
        ISet<E> result = new UnsortedSet<>();

        for(E val : this) { // O(N)
            if(!otherSet.contains(val)) { // O(N)
                result.add(val); // O(N)
            }
        }

        return result;
    }
    
    /** O(N^2)
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set 
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }

        ISet<E> result = new UnsortedSet<>();

        for(E val: this) { // O(N)
            if(otherSet.contains(val)) { // O(N)
                result.add(val); // O(N)
            }
        }

        return result;
    }

    /** O(1)
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return myCon.iterator();
    }
    
    /** O(1)
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size() {
        return myCon.size();
    }
}
