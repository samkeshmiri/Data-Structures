
public interface QueueInterface<T> {
	
	public int size();
	
	public void enQueue(T item);
	
	public T deQueue();
	
	public boolean contains(T item);
	
	public T access(int position);

}
