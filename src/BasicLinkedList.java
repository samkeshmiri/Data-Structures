
public class BasicLinkedList<T> {
	
	private Node first;
	private Node last;
	private int nodeCount;
	
	public BasicLinkedList() {
		first = null;
		last = null;
		nodeCount = 0;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public void add(T item) {
		if (first == null) {
			first = new Node(item); 
			last = first;
		} else {
			Node tempLastNode = new Node(item);
			last.setNextNode(tempLastNode);
			last = tempLastNode;
		}
		nodeCount++;
	}
	
	public T remove() {
		if (first == null) {
			throw new IllegalStateException("No nodes in list");
		} else {
			T item = first.getNodeItem();
			nodeCount--;
			return item;
		}
	}
	
	public void insert(T item, int position) {
		if (size() < position) {
			throw new IllegalStateException("The list is smaller than the position");
		}
		
		Node currentNode = first;
		
		for(int i = 1; i < position && currentNode != null; i++) {
			currentNode = currentNode.getNextNode();
		}
		
		Node newNode = new Node(item);
		Node nextNode = currentNode.getNextNode();
		currentNode.setNextNode(newNode);
		newNode.setNextNode(nextNode);
		nodeCount++;
	}
	
	
	public T removeAt(int position) {
		if (first == null) {
			throw new IllegalStateException("Not a position in the list");
		}
		
		Node currentNode = first;
		Node prevNode = first;
		
		for(int i = 1; i < position && currentNode != null; i++) {
			prevNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		T item = currentNode.getNodeItem();
		prevNode.setNextNode(currentNode.getNextNode());
		nodeCount--;
		return item;
	}
	
	public T get(int position) {
		if (first == null) {
			throw new IllegalStateException("Not a position in the list");
		}
		
		Node currentNode = first;
		
		for(int i = 1; i < position && currentNode != null; i++) {
			if (i == position) {
				return currentNode.getNodeItem();
			}
			currentNode = currentNode.getNextNode();
		}
		
		return null;
	}
	
	public int find(T item) {
		if (first == null) {
			throw new IllegalStateException("Not a position in the list");
		}
		
		Node currentNode = first;
		for(int i = 1; i < size() && currentNode != null; i++) {
			if(currentNode.getNodeItem().equals(item)) {
				return i;
			}
			currentNode = currentNode.getNextNode();
		}
		
		return -1;
	}
	
	public String toString() {
		StringBuffer contents = new StringBuffer();
		Node currentNode = first;
		
		while (currentNode != null) {
			contents.append(currentNode.getNodeItem());
			currentNode = currentNode.getNextNode();
			if(currentNode != null) {
				contents.append(", ");
			}
		}
		
		return contents.toString();
	}

	private class Node {
		private Node nextNode;
		private T nodeItem;
		
		public Node(T item) {
			this.nextNode = null;
			this.nodeItem = item;
		}
		
		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}
		
		public Node getNextNode() {
			return nextNode;
		}
		
		public T getNodeItem() {
			return nodeItem;
		}
	}
	
	
	public static void main(String[] args) {
		BasicLinkedList<Integer> list = new BasicLinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.size());
		System.out.println(list.removeAt(50));
		System.out.println(list.size());
		System.out.println(list.find(40)); 
	}
	
	
}
