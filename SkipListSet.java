package SkipList;

import java.util.*; // Import all java.util classes to cover required interfaces

public class SkipListSet<T extends Comparable<T>> implements SortedSet<T> {
	final public static int NEG_INF = Integer.MIN_VALUE;
	final public static int POS_INF = Integer.MAX_VALUE;
	
	
	private SkipListSetItem head; // Sentinel node at the lowest level
	private int level;
	private Random rand;
	
	// Item-wrapper class
	private class SkipListSetItem {
		T payload;
		SkipListSetItem left, right, up, down;
		
		// Creates a node item for SkipList
		public SkipListSetItem(T nPayload) {
			this.payload = nPayload;
			this.left = null;
			this.right = null;
			this.up =  null;
			this.down = null;
		}		
	} // End of SkipListSetItem
	
    // Traverses the skipList's items (nodes).
	// private SkipListSetItem traverse(T target) - REMOVE LATER
    private SkipListSetItem traverse() {
    	SkipListSetItem current = head;
    	
    	// Traverse through the BASE level
    	while(current != null) {
    		current = current.right;
    	}
    	
    	return current;
    }
	
    // Internal iterator class for the SkipListSet
    private class SkipListSetIterator<T extends Comparable<T>> implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

    } // End of SkipListSetIterator class
    
	// Constructor - No Argument
    public SkipListSet() {
    	this.head = new SkipListSetItem(null);
    	this.level = 0;
    	this.rand = new Random();
    } // End of Constructor 
    
    // Constructor with Collection Argument
    public SkipListSet(Collection <? extends T> collection) {
    	this();
    	for(T element : collection) {
    		add(element);
    	}
    } // End of Constructor

    // METHODS
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	// Suppress unchecked cast warning
	@Override 
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T newVal) {
		SkipListSetItem prev = traverse();
		SkipListSetItem newNode = new SkipListSetItem(newVal);
		if(prev == null) {
			
		}
		
		prev.right = newNode;
		newNode.left = prev;
		
		return false;
	}

	// Suppress unchecked cast warning
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comparator<? super T> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> headSet(T toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> tailSet(T fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T last() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public void reBalance() {
        // Logic for rebalancing, if needed (for now, you can leave this empty)
    }
    
} // End of SkipListSet class
