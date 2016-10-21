//Student ID: 14009228
//Date: 20 November 2015

package W10Practical;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTreeIterator implements Iterator<BinaryTreeNode> {
	
	//stack to hold nodes of the tree
	Stack<BinaryTreeNode> nodes = new Stack<BinaryTreeNode>();
	
	//constructor
	public BinarySearchTreeIterator(BinaryTreeNode root) {
		pushLeftChildren(root);
	}
	
	//travels from parent node to leftmost leaf node
	//looks only at left children
	//pushes nodes onto stack as they are passed
	private void pushLeftChildren(BinaryTreeNode parent) {
		while(parent != null) {
			nodes.push(parent);
			parent = parent.getLeft();
		}
	}
	
	//returns true if there are nodes left in the stack
	public boolean hasNext() {
		return !nodes.isEmpty();
	}

	//performs an in-order traversal on the binary search tree
	public BinaryTreeNode next() {
		
		//if there are no more nodes left in the tree, throw an exception
		if(!hasNext()) {
			throw new NoSuchElementException();
		} else {
			
			//pop the most recently pushed node off the stack to be returned
			BinaryTreeNode n = nodes.pop();
			
			//push the right child of the popped node onto the stack and any of its left children
			pushLeftChildren(n.getRight());
			
			//returns the next node
			return n;
		}
	}
}