public class BasicBinaryTree<T extends Comparable<T>> {
	private Node root;
	private int size;

	public BasicBinaryTree() {
		this.root = null;
	}

	public int size() {
		return size;
	}

	public void add(T item) {
		Node node = new Node(item);
		if (root == null) {
			this.root = node; // if root is null, set new node to root (first run)
			System.out.println("Set root: " + node.getItem());
			this.size++;
		} else {
			insert(this.root, node);
		}
	}

	public boolean contains(T item) {
		// can do null check too
		Node nodeItem = getNode(item);
		if (nodeItem.getItem() == item) {
			return true;
		}
		return false;
	}
	
	public boolean delete(T item) {
		boolean deleted = false;
		
		if (this.root == null) {
			return false;
		}
		
		Node currentNode = getNode(item);
		
		if (currentNode != null) {
			//if the node has no children, delete it
			if (currentNode.getLeft() == null && currentNode.getRight() == null) {
				unlink(currentNode, null);
				deleted = true;
			} else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
				// if the node only has a right child, remove it in hierarchy
				unlink(currentNode, currentNode.getRight()); // getting right child and replacing it where it was
				deleted = true;
			} else if (currentNode.getRight() == null && currentNode.getLeft() != null) {
				// if the node only has a left child, remove it in hierarchy
				unlink(currentNode, currentNode.getLeft()); // getting right child and replacing it where it was
				deleted = true;	
			} else {
				// the node has both children, do a node swap to delete
				// swap out right most node on the left side
				Node child = currentNode.getLeft();
				while(child.getRight() != null && child.getLeft() != null) {
					child = child.getRight();
				}
				
				child.getParent().setRight(null); // remove from current position
				child.setLeft(currentNode.getLeft());
				child.setRight(currentNode.getRight());
				unlink(currentNode, child);
				deleted = true;
			}
		}
		
		if (deleted) {
			size--;
		}
		
		
		return deleted;
	}
	
	private void unlink(Node currentNode, Node newNode) {
		if(currentNode == this.root) {
			newNode.setLeft(currentNode.getLeft());
			newNode.setRight(currentNode.getRight());
			this.root = newNode;
		} else if (currentNode.getParent().getRight() == currentNode) {
			currentNode.getParent().setRight(newNode);
		} else {
			currentNode.getParent().setLeft(newNode);
		}
	}

	public Node getNode(T item) {
		Node currentNode = this.root;

		while (currentNode != null) {
			int val = item.compareTo(currentNode.getItem());

			// see if node matches item
			if (val == 0) {
				return currentNode;
			} else if (val < 0) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}
		return null;
	}

	private void insert(Node parent, Node child) {
		if (child.getItem().compareTo(parent.getItem()) < 0) {
			if (parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				this.size++;
			} else {
				insert(parent.getLeft(), child);
			}
		} else if (child.getItem().compareTo(parent.getItem()) > 0) {
			if (parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				this.size++;
			} else {
				insert(parent.getRight(), child);
			}
		} else {
			child.setCount(child.getCount() + 1);
			this.size++;
		}
	}

	private class Node { // current node that I'm on
		private Node left; // child
		private Node right; // child
		private Node parent;
		private int count;
		private T item; // value of the node

		public Node(T item) {
			this.item = item;
			this.setCount(1);
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public T getItem() {
			return item;
		}

		public void setItem(T item) {
			this.item = item;

		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

}
