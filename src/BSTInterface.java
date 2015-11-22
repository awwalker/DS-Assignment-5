/**
 * This interface defines the methods that 
 * must be implemented in any class that 
 * uses it. 
 * @author Aaron Walker
 *
 * @param <E>
 */
public interface BSTInterface <E extends Comparable <E> > {

	/**
	 * Adds an item to this binary search tree.
	 * @param item the item to be added
	 */
	void insert ( E item );
	
	/**
	 * Removes an item from this binary search tree.
	 * If item is not in the tree, the structure is unchanged.
	 * @param item the item to be removed
	 */
	void remove ( E item );
	
	/**
	 * Determines if an item is located in this binary search tree.
	 * @param item the item to be located
	 * @return true if the item is in the tree, false otherwise
	 */
	boolean contains ( E item );
}
