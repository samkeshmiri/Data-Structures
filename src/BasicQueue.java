
public class BasicQueue<T> implements QueueInterface<T> {

	private T[] data;
	private int front;
	private int end;
	
	public BasicQueue() {
		this(1000);
	}
	
	public BasicQueue(int size) {
		this.front = -1; // empty or not
		this.end = -1;
		data = (T[]) new Object[size];
	}
	
	public int size() {
		if (front == -1 && end == -1) {
			return 0;
		} else {
			return end - front + 1;
		}
	}
	
	public void enQueue(T item) {
		if ((end+1) % data.length == front) { 
			throw new IllegalStateException("The queue is full");
		} else if (size() == 0) {
			front++;
			end++;
			data[end] = item;
		} else {
			end++;
			data[end] = item;
		}
	}
	
	public T deQueue() {
		T item = null;
		
		if (size() == 0) {
			throw new IllegalStateException("Queue is empty");
		} else if (front == end) {
			item = data[front];
			data[front] = null;
			front = -1;
			end = -1;
		} else {
			item = data[front];
			data[front] = null;
			front++;
		}
		return item;
	}
	
	public boolean contains(T item) {
		boolean found = false;
		
		if (size() == 0) {
			return found;
		}
		
		for(int i = front; i < end; i++) {
			if(data[i].equals(item)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public T access(int position) { // big O(n)
		int index = 0;
		
		
		if (size() == 0 || position > size()) {
			throw new IllegalStateException("Queue is empty");
		} 

		for (int i = front; i < end; i++) {
			if(index == position) {
				return data[i];
			}
			index++;
		}
		throw new IllegalArgumentException("Could not get queue item at position");
	}
	
	public static void main(String[] args) {
		BasicQueue<Integer> queue = new BasicQueue<Integer>();
		for(int i = 0; i < 100; i++) {
			queue.enQueue(i);
		}
		
		System.out.println(queue.contains(67));
		System.out.println(queue.access(66));
	}
	
}
