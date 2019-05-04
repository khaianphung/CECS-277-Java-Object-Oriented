import java.util.*;
/** 
 * @author Aingty Eung
 * This class is used to sort out the task/song in order to get the parent value.
 */
public class HeapTree <T extends Comparable<T>>{
	/**
	 * heap is the generic arraylist to serve as the heaptree for this program
	 */
	private ArrayList<T> heap;
	
	/**
	 * This is the constructor for the HeapTree class
	 */
	public HeapTree(){
		heap = new ArrayList<T>();
	}
	
	/**
	 * This method will return the size of the heap aka the HeapTree
	 * @return heap's size
	 */
	public int getSize(){
		return heap.size();
	}
	
	/**
	 * This method will tell the user if the heap is empty or not
	 * @return true or false
	 */
	public boolean isEmpty(){
		return heap.isEmpty();
	}
	
	/**
	 * This method is called in order to find the parent location of a child Node.
	 * @param i is the index of the child node
	 * @return the integer index of the parent's location
	 */
	public int getPLoc(int i){
		return (i-1)/2;
	}
	
	/**
	 * This method is used to get the left child location from any parent
	 * @param i is the integer index of the parent
	 * @return the ineter index of the left child's location
	 */
	public int getLCLoc(int i){
		return 2*i+1;
	}
	
	/**
	 * This method is used to get the right child location from any parent
	 * @param i is the integer index of the parent
	 * @return the ineter index of the right child's location
	 */
	public int getRCLoc(int i){
		return 2*i+2;
	}
	
	/**
	 * This method is used to get the node at any given integer index
	 * @param i is the integer index of the node 
	 * @return the generic type of what is within the node at integer index i
	 */
	public T getNodeAt(int i){
		if(heap.get(i)==null){
			System.out.println("Item does not exist.");
			return null;
		}else{
			return heap.get(i);
		}
	}
	
	/**
	 * This method is used to add a new node to the heaptree
	 * @param n is the generic type object that is to be added to the tree
	 */
	public void addNode(T n){
		heap.add(null);
		int index = heap.size()-1;
		/**
		 * when the child needs to swap with the parent, this while statement is executed.
		 * The ".compareTo()" here is the overridden method from the class Song and Job
		 */
		while(index > 0 && getNodeAt(getPLoc(index)).compareTo(n) < 0){
			heap.set(index, getNodeAt(getPLoc(index)));
			index = getPLoc(index);
		}
		
		heap.set(index, n);
	}
	
	/**
	 * This method is used to remove the root of the heaptree and replace it with the next desirable node.
	 * @return the root of the tree before the replacement.
	 */
	public T removeMin(){
		/**
		 * min is used to get the root of the heaptree
		 */
		T min = heap.get(0);
		int index = heap.size()-1;
		/**
		 * last is to get the last node of the tree
		 */
		T last = heap.remove(index);
		if(index > 0){
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size()-1;
			index = 0;
			boolean done = false;
			while(!done){
				if(getLCLoc(index)<=end){//left exists
					T child = getNodeAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if(getRCLoc(index)<=end){//rt exists
						if(getNodeAt(getRCLoc(index)).compareTo(child) > 0){
							child = getNodeAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if(child.compareTo(root) > 0){
						heap.set(index, child);
						index = childLoc;
					}else{
						done = true;
					}
				}else{//no children
					done = true;
				}
			}
			heap.set(index, root);
		}
		return min;
	}
	
	/**
	 * This method is used to print the entire tree by getting the overridden "toString()" method.
	 */
	public void printHeap(){
		for(int i=0;i<heap.size();i++){
			System.out.print(heap.get(i).toString());
			System.out.println();
		}
	
	}
}

