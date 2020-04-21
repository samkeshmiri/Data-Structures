import java.util.Queue;

public class BasicStack<T> {
	private T[] data; // don't want outside classes to use this data structure directly
	private int stackPointer; // only used internally in this class=
	
	public BasicStack() {
		data = (T[]) new Object[1000];
		stackPointer = 0; 
	}
	
	public void push(T newItem) {
		data[stackPointer++] = newItem; // stack pointer gets incremented after association 
	}
	
	public T pop() {
		if(stackPointer == 0) {
			throw new IllegalStateException("No more items on the stack");
		}
		return data[stackPointer--];
	}
	
	public boolean contains(T item) {
		boolean found = false;
		for (T element : data) {
			if (item.equals(element)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public T access(T item) {
		while(stackPointer > 0) {
			T myObj;
			myObj = pop();
			if (item.equals(myObj)) {
				return myObj;
			}
		}		
		throw new IllegalArgumentException("Item was not found on stack: " + item);
	}
	
	public int size() { // size of stack
		return stackPointer;
	}
	
	public static void main(String[] args) {
		BasicStack<String> stack = new BasicStack<String>();
		for (int i = 0; i < 100; i++) {
			stack.push(Integer.toString(i));
		}
		
		System.out.println(stack.contains("99"));
		System.out.println(stack.access("4"));
		System.out.println(stack.size());
	}
}
