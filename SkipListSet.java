  package SkipList;

import java.util.*;

public class SkipListSet<T extends Comparable<T>> implements SortedSet<T> {
	
	// SkipListSet Attributes
	final public static int MAX_LEVEL = 32; // Ask Gio how he got this number.
	public static Random randObj = new Random();
	private SkipListSetItem head;
	private SkipListSetItem tail;
	public int curLevel = 0;
	private int totElements = 0;
	private int lastBC = 0;
	
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
		
		// Creates sentinel node.
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
    private SkipListSetItem searchItem(T source) {	
    	SkipListSetItem current = getTopNode(head);
    	
        // Traverse levels and nodes
        while (current != null) {
            
        	// Traverse to the right
            while (current.right != null && !current.right.isSentinel &&
                   current.right.data.compareTo(source) < 0) {
                current = current.right;
            } // End horizontal traversal
            
            if(current.down == null) {
            	break;
            }
            
            current = current.down;
        } // End of vertical traversal
        
        // Duplicate Check
        if (current.right != null && !current.right.isSentinel &&
                current.right.data.compareTo(source) == 0) {
        	return current.right;
        }
        return current;
    } // End of searchItem method
    
    // Gets the node at the base level.
    private SkipListSetItem getBottomNode(SkipListSetItem node) {
    	while(node.down != null) {
    		node = node.down;
    	}
    	return node;
    } // End of getBottomNode method
    
    // Gets the node at the top level.
    private SkipListSetItem getTopNode(SkipListSetItem node) {
    	while(node.up != null) {
    		node = node.up;
    	}
    	return node;
    } // End of getTopNode method
    
    // Creates a random height for an item.
    private int generateHeight() {
    	
    	int randHeight = 1;
    	 
    	// FLips a coin on whether to add a level or not to list.
    	while(randHeight < MAX_LEVEL && randObj.nextBoolean()) {
    		randHeight++;
    	}
    	return randHeight;
    } // End of generateHeight method
    
    // Builds a new level for skiplist. Connects new head and tail to new node.
    private void buildLevel(SkipListSetItem newN) {
    	// Variables
    	SkipListSetItem predH = getTopNode(head);
    	SkipListSetItem predT = getTopNode(tail);
    	SkipListSetItem newH = new SkipListSetItem(true);
    	SkipListSetItem newT = new SkipListSetItem(true);
    	
    	// Update head references
    	newH.down = predH;
    	newH.right = newN;
    	predH.up = newH;
    	newN.left = newH;
    	
    	// Update tail references
    	newT.down = predT;
    	newT.left = newN;
    	predT.up = newT;  	
    	newN.right = newT;
    	
    	// update class references
    			head = head.up;
    			tail = tail.up;
    	
    	curLevel++;
    } // End of buildLevel method
    
    // Searches level within skiplist. Returns either the predecessor node or a node with an existing item.
    private SkipListSetItem searchLevel(SkipListSetItem curHead, T target) {
    	SkipListSetItem current = curHead;
    	
    	while(current != null && current.right != null && !current.right.isSentinel 
    			&& current.right.data.compareTo(target) < 0) {
    		current = current.right;
    	}
    	
    	if (current != null && current.right != null && current.right.data.compareTo(target) == 0){
    		return current.right;
    	}
		return current;
    } // End of searchLevel method
    
    // Creates a tower in skiplist by connecting nodes vertically.
    private void createTower(SkipListSetItem gNode, int height) {
    	// Variables
    	SkipListSetItem towerN = null;
    	SkipListSetItem lastBN = gNode;
    	SkipListSetItem predN = gNode.left;
    	
    	// Loop through item's random height.
    	for(int i = 1; i <= height; i++) {
    		
    		// Create tower node and connect to bottom node.
    		towerN = new SkipListSetItem (gNode.data);
    		lastBN.up = towerN;
    		towerN.down = lastBN;
    		
    		// Move to left, if predecessor doesn't have duplicate above.
    		while(predN != null && predN.up == null) {
    			predN = predN.left;
    		}
    		
    		// Build new level check
    		if (predN == null || predN.isSentinel && predN.up == null) {
    			buildLevel(towerN);
    		}
    		// Insert at level above
    		else {
    			predN = predN.up;
    			towerN.right = predN.right;
    			predN.right = towerN;
    			towerN.left = predN;
    			towerN.right.left = towerN;
    		}
    	}
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
    	this.head = new SkipListSetItem(true);
    	this.tail = new SkipListSetItem(true);
    	
    	this.head.right = this.tail;
    	this.tail.left = this.head;

    	this.curLevel = 1;
    } // End of Constructor 
    
    // Constructor with Collection Argument
    public SkipListSet(Collection <? extends T> collection) {
    	this();
    	for(T element : collection) {
    		add(element);
    	}
    } // End of Constructor

    // METHODS
    
    // Prints out the bottom level of the list. Follows ascending order.
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	SkipListSetItem current = head.right;
    	while(current != null && !current.isSentinel ) {
    		System.out.print(current.data + " ");
    		current = current.right;
    	}
    	return sb.toString();
    } // End of toString method
    
    // Prints out the bottom level of the list in reverse order.
    public String printReverse() {
    	StringBuffer sb = new StringBuffer();
    	SkipListSetItem current = tail.left;
    	while(current != null && !current.isSentinel ) {
    		System.out.print(current.data + " ");
    		current = current.left;
    	}
    	return sb.toString();
    	
    } // End of printReverse method
    
    // Prints every layer of the skip list.
    public void printLayers() {
        SkipListSetItem currentLevel = getTopNode(head); // Start at the topmost level
        int level = curLevel; // Track the current level

        while (currentLevel != null) {
            System.out.print("Level " + level + ": ");
            
            SkipListSetItem currentNode = currentLevel.right; // Skip the sentinel head
            while (currentNode != null && !currentNode.isSentinel) {
                System.out.print(currentNode.data + " ");
                currentNode = currentNode.right;
            }
            
            System.out.println(); // Move to the next line for the next level
            currentLevel = currentLevel.down; // Move down one level
            level--; // Decrement the level count
        }
    }
    
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
		// Null check
		if(newVal == null) {
			return false;
		}
		
		// Get predecessor/duplicate item.
		SkipListSetItem pred = searchItem(newVal);
		
		// Duplication Check
		if(pred.data != null && pred.data.compareTo(newVal) == 0) {
			return false;
		}
		
		// Insert new node at base level.
		SkipListSetItem newNode = new SkipListSetItem(newVal);
		newNode.right = pred.right;
		pred.right = newNode;
		newNode.left = pred;
		newNode.right.left = newNode;

		// Generate a random height for new node.
		int randH = generateHeight();
		
		// Create tower of new nodes.
		createTower(newNode, randH);
		
		totElements++;		
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
		boolean flag = false;
		
		for(T t: c) {
			if(add(t)) {
				flag = true;
			}// if collection changes, reflect that.
		}// end loop
		
		// if false collection did not change.
		return flag;
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
