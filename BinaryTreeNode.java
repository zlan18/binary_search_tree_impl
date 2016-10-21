//Student ID: 14009228
//Date: 20 November 2015

package W10Practical;

public class BinaryTreeNode {
	private int elem;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	//constructor
	public BinaryTreeNode(int elem) {
		this.elem = elem;
		left = null;
		right = null;
	}

	//constructor
	public BinaryTreeNode(int elem, BinaryTreeNode left, BinaryTreeNode right) {
		this.elem = elem;
		this.left = left;
		this.right = right;
	}

	//returns the element value of the node
	public int getElem() {
		return elem;
	}

	//returns the left child node
	public BinaryTreeNode getLeft() {
		return left;
	}

	//returns the right child node
	public BinaryTreeNode getRight() {
		return right;
	}

	//changes the element value of the node
	public void setElem(int newElem) {
		elem = newElem;
	}

	//changes the left child pointer of the node
	public void setLeft(BinaryTreeNode newLeft) {
		left = newLeft;
	}

	//changes the right child pointer of the node
	public void setRight(BinaryTreeNode newRight) {
		right = newRight;
	}
}
