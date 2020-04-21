
public class BasicHashTable<K, V> {
	private HashEntry[] data;
	private int capacity;
	private int size;

	public BasicHashTable(int tableSize) {
		this.capacity = tableSize;
		this.data = new HashEntry[this.capacity];
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public V get(K key) {
		int hash = calculateHash(key);

		if (data[hash] == null) {
			return null;
		} else {
			return (V) data[hash].getValue();
		}
	}

	public void put(K key, V value) {
		int hash = calculateHash(key);

		data[hash] = new HashEntry<>(key, value);
		size++;
	}

	private int calculateHash(K key) {
		int hash = (key.hashCode() & this.capacity);

		while (data[hash] != null && !data[hash].getKey().equals(key)) {
			hash = (hash + 1) % this.capacity;
		}
		return hash;
	}

	public V delete(K key) { // big O depends on collisions, could be possibly quadratic
		V value = get(key);

		if (value != null) {
			int hash = calculateHash(key);
			data[hash] = null;
			size--;
			hash = (hash + 1) % this.capacity; // if we have collision we should have data in the next slot
			// if we do we need to rehash the table
			while (data[hash] != null) {
				HashEntry he = data[hash];
				data[hash] = null;
				put((K) he.getKey(), (V) he.getValue()); // this rehashes the item
				size--;
				hash = (hash + 1) % this.capacity; // run until empty slot i.e. no collisions
			}
		}
		return value;
	}
	
	public boolean hasKey(K key) {
		int hash = calculateHash(key);
		
		if (data[hash] == null) {
			return false;
		} else {
			if (data[hash].getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasValue(V value) { // O(n) linear
		for (int i = 0; i < this.capacity; i++) {
			HashEntry item = data[i];
			if (item != null && item.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	private class HashEntry<K, V> {
		private K key;
		private V value;

		public HashEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}

}
