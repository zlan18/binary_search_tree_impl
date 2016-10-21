package BinaryTreeImpl;

public class BinarySearchTree implements IBinarySearchTree {
	private BinaryTreeNode root = null;

	//returns the number of nodes in the tree
	public int size() {
		return size(root);
	}

	//returns the number of nodes contained in a binary search subtree
	//recursive method
	private int size(BinaryTreeNode subtree) {
		if(subtree == null) {
			return 0;
		} else {
			return 1 + size(subtree.getLeft()) + size(subtree.getRight());
		}
	}

	//finds a node containing the given element value in the binary search tree
	private BinaryTreeNode findNode(int elem) {
		return findNode(elem, root);
	}

	//finds a node containing the given element value in the binary search subtree
	//returns null if end of tree is reached before finding the matching element value
	//recursive method
	private BinaryTreeNode findNode(int elem, BinaryTreeNode parent) {

		//if the end of the tree has not been reached
		if(parent != null) {

			//if the given element matches the element of the parent node
			if(elem == parent.getElem()) {
				return parent;

				//if the given element is less than the element of the parent node
				//travel down the left subtree of the parent
			} else if(elem < parent.getElem()) {
				return findNode(elem, parent.getLeft());

				//the given element must be greater than the element of the parent node
				//travel down the right subtree of the parent
			} else {
				return findNode(elem, parent.getRight());
			}
		} else {
			return null;
		}
	}

	//finds the parent node of the node containing the given element value
	//returns null if the root contains the given element value or if tree is empty
	private BinaryTreeNode findParent(int elem) {
		if(root != null) {
			if (elem == root.getElem()) {
				return null;
			} else {
				return findParent(elem, root);
			}
		} else {
			return null;
		}
	}

	//finds the parent node of the node containing the given element value
	//returns null if the end of the tree is reached before finding node with child matching given element
	//recursive method
	private BinaryTreeNode findParent(int elem, BinaryTreeNode parent) {

		//if the end of the tree has not been reached
		if(parent != null) {

			//if parent has a left child
			if(parent.getLeft() != null) {

				//if the left child has the matching element value
				//return the current node
				if(elem == parent.getLeft().getElem()) {
					return parent;

					//if given element is less than the parent's element
					//travel down the left subtree
				} else if(elem < parent.getElem()) {
					return findParent(elem, parent.getLeft());
				}
			} 

			//if the parent has a right child
			if(parent.getRight() != null) {

				//if the right child has the matching element value
				//return the current node
				if (elem == parent.getRight().getElem()) {
					return parent;

					//if given element is greater than the parent's element
					//travel down the right subtree
				} else if (elem > parent.getElem()) {
					return findParent(elem, parent.getRight());
				}
			}
		}
		return null;
	}

	//adds an element to the binary tree as a new node
	//AVL operation
	public void add(int elem) {
		BinaryTreeNode node = new BinaryTreeNode(elem);

		if(root == null) {
			root = node;
		} else {
			add(node, root);
		}
	}

	//adds an element to the binary tree as a new node, rebalancing tree as necessary
	//recursive method
	private void add(BinaryTreeNode subtree, BinaryTreeNode parent) {

		//if the new node's element is less than the parent element
		if(subtree.getElem() < parent.getElem()) {

			//if the parent node already has a left child
			//travel down the left subtree of the original tree
			if(parent.getLeft() != null) {
				this.add(subtree, parent.getLeft());

			//make the new node the left child of the parent
			} else {
				parent.setLeft(subtree);
				rebalance(subtree);
			}

			//if the new node's element is greater than the parent element
		} else if (subtree.getElem() > parent.getElem()) {

			//if the parent node already has a right child
			//travel down the right subtree of the original tree
			if(parent.getRight() != null) {
				this.add(subtree, parent.getRight());

				//make the new node the right child of the parent
			} else {
				parent.setRight(subtree);
				rebalance(subtree);
			}
		}
	}

	//removes a node containing the given element from the tree, rebalancing tree as necessary
	//recursive method in case of removing a node with two children
	//AVL operation
	public void remove(int elem) {
		BinaryTreeNode parent = findParent(elem);
		BinaryTreeNode node = findNode(elem);

		//if the tree contains the element in the first place
		if(node != null) {

			//if the node has no children
			//set original pointer to node to null
			if (node.getLeft() == null && node.getRight() == null) {
				if(parent == null) {
					root = null;
				} else if(node == parent.getLeft()) {
					parent.setLeft(null);
				} else if(node == parent.getRight()){
					parent.setRight(null);
				}

				//if the node has only a left child
				//set original pointer to node to node's left subtree
			} else if (node.getLeft() == null) {
				if(parent == null) {
					root = node.getRight();
				} else if(node == parent.getLeft()) {
					parent.setLeft(node.getRight());
				} else if(node == parent.getRight()){
					parent.setRight(node.getRight());
				}

				//if the node has only a right child
				//set original pointer to node to node's right subtree
			} else if (node.getRight() == null) {
				if(parent == null) {
					root = node.getLeft();
				} else if(node == parent.getLeft()) {
					parent.setLeft(node.getLeft());
				} else if(node == parent.getRight()){
					parent.setRight(node.getLeft());
				}

				//the node must have a left and right child
				//replaces node to be removed with its largest value smaller
			} else {
				//gets left subtree of node to be removed
				BinaryTreeNode temp = node.getLeft();

				//continues down right subtree of temp until largest value smaller is reached
				while(temp.getRight() != null) {
					temp = temp.getRight();
				}

				//remove the element that will be used to replace the element given by user
				remove(temp.getElem());

				//replace the node element to be removed with the largest value smaller
				node.setElem(temp.getElem());
			}
			
			//rebalance tree
			if(parent != null) {
				rebalance(parent);
			} else {
				rebalance(root);
			}
		}
	}

	//returns true if the tree contains the given element
	public boolean contains(int elem) {
		return contains(elem, root);		
	}

	//returns true if subtree contains the given element
	//recursive method
	private boolean contains(int elem, BinaryTreeNode parent) {

		//if the end of the tree has not been reached
		if(parent != null) {

			//if the given element matches the parent element, return true
			if (elem == parent.getElem()) {
				return true;

				//if the given element is less than the parent element
				//travel down the left subtree
			} else if (elem < parent.getElem()) {
				return contains(elem, parent.getLeft());

				//if the given element is greater than the parent element
				//travel down the right subtree
			} else if (elem > parent.getElem()) {
				return contains(elem, parent.getRight());
			}
		}
		return false;
	}

	//performs set union on the binary search tree and the one given in the parameters
	//updates current binary search tree with result of union
	public void union(BinarySearchTree set2) {
		BinarySearchTreeIterator iter = new BinarySearchTreeIterator(set2.root);

		//while there are nodes left
		while(iter.hasNext()) {
			//add its element to the current tree
			int curr = iter.next().getElem();
			add(curr);
		}
	}

	//performs set intersection on the binary search tree and the one given in the parameters
	//updates current binary search tree with result of intersection
	public void intersection(BinarySearchTree set2) {
		BinarySearchTreeIterator iter = new BinarySearchTreeIterator(root);

		//while there are nodes left to compare
		while(iter.hasNext()) {

			//if the comparing tree does not have this element, remove it from the current tree
			int curr = iter.next().getElem();
			if(!set2.contains(curr)) {
				remove(curr);
			}
		}
	}

	//rebalances tree using AVL rotations
	private void rebalance(BinaryTreeNode node) {
		
		System.out.print("Before Rebalancing ");
		prettyPrint();
		
		BinaryTreeNode parent = findParent(node.getElem());

		//while root is not reached
		while (parent != null) {
			if(node == parent.getLeft()) {
				
				//if unbalanced
				if(getHeight(parent) > 1) {
					
					//left-right case
					if(getHeight(node) <= -1) {
						//reduces to left-left case
						rotateLeft(node, node.getRight());
					}
					
					//left-left case
					//reduces to balanced tree
					rotateRight(parent, parent.getLeft());
				}
			} else {
				
				//if unbalanced
				if(getHeight(parent) < -1) {
					
					//right-left case
					if(getHeight(node) >= 1) {
						//reduces to right-right case
						rotateRight(node, node.getLeft());
					}
					
					//right-right case
					//reduces to balanced tree
					rotateLeft(parent, parent.getRight());
				}
			}
			node = parent;
			parent = findParent(node.getElem());
		}
		System.out.print("After Rebalancing ");
		prettyPrint();
		System.out.println();
	}
	
	//rotates the nodes so that the left child node takes the place of the given parent node
	//given parent node becomes the right child of the given child node
	private void rotateRight(BinaryTreeNode parent, BinaryTreeNode child) {
		BinaryTreeNode subtreeRoot = findParent(parent.getElem());
		BinaryTreeNode childRightSubtree = child.getRight();
		
		//if parent is root
		if(subtreeRoot == null) {
			root = child;
		} else {
			//put child node in parent node's place
			if(subtreeRoot.getLeft() == parent) {
				subtreeRoot.setLeft(child);
			} else {
				subtreeRoot.setRight(child);
			}
		}
		//set parent node to child node's right child
		//assign child node's original right subtree to be parent's left child
		child.setRight(parent);
		parent.setLeft(childRightSubtree);
	}
	
	//rotates the nodes so that the right child node takes the place of the given parent node
	//given parent node becomes left child of given child node
	private void rotateLeft(BinaryTreeNode parent, BinaryTreeNode child) {
		BinaryTreeNode subtreeRoot = findParent(parent.getElem());
		BinaryTreeNode childLeftSubtree = child.getLeft();
		
		//if parent is root
		if(subtreeRoot == null) {
			root = child;
		} else {
			//put child node in parent node's place
			if(subtreeRoot.getLeft() == parent) {
				subtreeRoot.setLeft(child);
			} else {
				subtreeRoot.setRight(child);
			}
		}
		//set parent node to child node's left child
		//assign child node's original left subtree to be parent's left child
		child.setLeft(parent);
		parent.setRight(childLeftSubtree);
	}

	//gets the height of the given subtree
	//recursive method
	private int getHeight(BinaryTreeNode subtree) {
		
		if (subtree == null) {
			return -1;
		} else {
			int leftHeight = getHeight(subtree.getLeft());
			int rightHeight = getHeight(subtree.getRight());
			
			if(leftHeight > rightHeight) {
				return leftHeight + 1;
			} else {
				return rightHeight + 1;
			}
		}
	}
	
	//prints out an in-order traversal of the binary search tree to the console
	public void prettyPrint() {
		BinarySearchTreeIterator iter = new BinarySearchTreeIterator(root); 
				
		System.out.print("In-Order Traversal: ");
		while(iter.hasNext()) {
			System.out.print(iter.next().getElem() + " ");
		}
		
		System.out.println();
	}
} 
