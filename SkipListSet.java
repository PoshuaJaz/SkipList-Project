  package SkipList;

import java.util.*;

public class SkipListSet<T extends Comparable<T>> implements SortedSet<T> {
	//final public static int NEG_INF = Integer.MIN_VALUE;
	//final public static int POS_INF = Integer.MAX_VALUE;
	public static Random randObj = new Random();
		
	private SkipListSetItem head;
	private SkipListSetItem tail;
	private int level;

	
	// Item-wrapper class
	private class SkipListSetItem {
		T data;
		boolean isSentinel;
		SkipListSetItem left, right, up, down;
		
		// Creates a node item for SkipList
		public SkipListSetItem(T nData) {
			this.data = nData;
			this.left = null;
			this.right = null;
			this.up =  null;
			this.down = null;
			this.isSentinel = false;
		}
		
		// Creates min/max infinity node.
		public SkipListSetItem(boolean flag) {
			this.data = null;
			this.left = null;
			this.right = null;
			this.up =  null;
			this.down = null;
			this.isSentinel = flag;
		}
	} // End of SkipListSetItem
	
    // Traverses the skiplist's items (nodes).
    private SkipListSetItem traverse(T source) {
    	
    	SkipListSetItem current = head;
    	
    	// Traverse through the BASE level
    	while(current.right != null && !current.right.isSentinel && 
    			current.right.data.compareTo(source) < 0) {
    		current = current.right;
    	}
    	
    	return current;
    } // End of traverse method
	
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
    	this.head = new SkipListSetItem(true);
    	this.tail = new SkipListSetItem(true);
    	
    	this.head.right = this.tail;
    	this.tail.left = this.head;

    	this.level = 0;
    } // End of Constructor 
    
    // Constructor with Collection Argument
    public SkipListSet(Collection <? extends T> collection) {
    	this();
    	for(T element : collection) {
    		add(element);
    	}
    } // End of Constructor

    // METHODS
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	SkipListSetItem current = head.right;
    	while(current != null && !current.isSentinel ) {
    		System.out.print(current.data + " ");
    		current = current.right;
    	}
    	return sb.toString();
    } // End of toString method
    
    public String printReverse() {
    	StringBuffer sb = new StringBuffer();
    	SkipListSetItem current = tail.left;
    	while(current != null && !current.isSentinel ) {
    		System.out.print(current.data + " ");
    		current = current.left;
    	}
    	return sb.toString();
    	
    } // End of printReverse method
    
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
		SkipListSetItem prev = traverse(newVal);
		SkipListSetItem newNode = new SkipListSetItem(newVal);
		
		newNode.right = prev.right;
		newNode.left = prev;
		
		if(prev.right != null) {
			prev.right.left = newNode;
		}
		
		prev.right = newNode;
		
		if(newNode.right == tail) {
			tail.left = newNode;
		}		
		return true;
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
