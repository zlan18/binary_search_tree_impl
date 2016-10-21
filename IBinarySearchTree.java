//Student ID: 140009228
//Date: 20 November 2015

package W10Practical;

/**
 * ADT providing operations on binary search trees.
 * 
 * @author 140009228
 */

public interface IBinarySearchTree {

	/**
	 * Returns the number of nodes contained in the binary search tree.
	 * 
	 * @return the size of the binary search tree
	 */
	int size();
	
	/**
	 * Adds an integer element to the binary search tree as a new node.
	 * 
	 * @param integer element of node to be added to the tree
	 */
	void add(int elem);
	
	/**
	 * Removes a given integer element from the binary search tree.
	 * 
	 * @param integer element to be removed from the tree
	 */
	void remove(int elem);
	
	/**
	 * Checks whether the binary search tree contains the given integer element.
	 * 
	 * @param integer element to be tested
	 * @return true if the list contains the given integer element, false if not
	 */
	boolean contains(int elem);
	
	/**
	 * Performs a set union on two binary search trees of integer sets.
	 * 
	 * @param a binary search tree implementation of a set of integers
	 */
	void union(BinarySearchTree set2);
	
	/**
	 * Performs a set intersection on two binary search trees of integer sets.
	 *  
	 * @param a binary search tree implementation of a set of integers
	 */
	void intersection(BinarySearchTree set2);
}