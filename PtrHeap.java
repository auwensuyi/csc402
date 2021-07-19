package algs24;


import java.util.NoSuchElementException;

import stdlib.StdIn;
import stdlib.StdOut;


/**
 *  The <tt>PtrHeap</tt> class is the priorityQ class from Question 2.4.24.
 *  It represents a priority queue of generic keys.
 *  
 *  It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class PtrHeap<K extends Comparable<? super K>> {
	
	
	static class Node<K> {
		int N;
		K value;
		Node<K> parent;
		Node<K> lchild;
		Node<K> rchild;
		public Node(K value, int N) {
			this.value = value;
			this.N = N;
		}
		public void displayNode() {
	        System.out.println(value + " ");
	    }
	}

	
	
	private Node<K> root;
	private Node<K> leaf;
	private Node<K> lastIn;
	
    boolean isRoot(Node<K> n){ return n == root; }
    boolean isLeaf(Node<K> n) { return n == leaf;}
    
    Node<K> find(int n){ return null; } 
	
    void exch(Node<K> n1, Node<K> n2) { 
    	// only swap items of nodes 
    	Node<K> temp = n1;
    	n1 = n2;
    	n2 = temp;
    	}
	
   
	private boolean less(K u, K v) { return (u.compareTo(v) < 0);}
	
	private int size(Node x){
        if(x == null) return 0;
        return x.N;
	}
	
	private void swim(Node n){
		Node curr =n;
		while (curr != root && less(n.parent.value,n.value))
		           exch(n,n.parent);
		           n = n.parent;
	}
	
	
	private void sink(Node n) {
	Node curr = n;
	     while (curr != leaf)
	                  if(!less(n.parent, n.rchild)) break;
	                  Node big = n.rchild;
	                  exch(n,big);
	                  n = big;
	}
	
	private boolean less(Object value, Object value2) {
		// TODO Auto-generated method stub
		return false;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node insert(Node x, K value){
        if(x == null){
            lastIn = new Node(value, 1);
            return lastIn;
        }
        // compare left and right sizes see where to go
        int leftSize = size(x.lchild);
        int rightSize = size(x.rchild);

        if(leftSize <= rightSize){
            // go to left
            Node inserted = insert(x.lchild, value);
            x.lchild = inserted;
            inserted.parent = x;
        } else{
            // go to right
            Node inserted = insert(x.rchild, value);
            x.rchild = inserted;
            inserted.parent = x;
        }
        x.N = size(x.lchild) + size(x.rchild) + 1;
        return x;
    }
	
	@SuppressWarnings("rawtypes")
	private Node resetLastIn(Node x){
        if(x == null) return null;
        if(x.lchild == null && x.rchild == null) return x;
        if(size(x.rchild) < size(x.lchild))return resetLastIn(x.lchild);
        else                            return resetLastIn(x.rchild);
    }
	
	private void printHeap(Node<K> n){
		if(n == null)  return ;
		System.out.print(n.value+" ");
		printHeap(n.lchild);
		printHeap(n.rchild);
		}
		private void showHeap() {
			printHeap(root);
			System.out.println();
			}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/** Create an empty priority queue  */
	public PtrHeap() {
		
		K data = null;
		Node parent = null;
		Node left = null;
		Node right = null;
		Node root = null;
		
	}
    
	
	/** Is the priority queue empty? */
	public boolean isEmpty() { 
		return size() == 0; }
	

	/** Return the number of items on the priority queue. */
	public int size() { 
		return size(root); }

	/**
	 * Return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K max() {
		if (isEmpty()) throw new Error("Priority queue underflow");
        return lastIn.value;
		
	}

	/** Add a new key to the priority queue. */
	@SuppressWarnings("unchecked")
	public void insert(K x) { 
		
		root = insert(root, x);
        swim(lastIn);}

	/**
	 * Delete and return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K delMax() {
			K max = lastIn.value;
			exch(root, lastIn);
            root = null;
            sink(lastIn);
            return max;
	}


	public static void main(String[] args) {
		PtrHeap<String> pq = new PtrHeap<>();
		StdIn.fromString("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print ("pq:  "); pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-")) StdOut.println("max: " + pq.delMax());
			else pq.insert(item);
		}
		StdOut.println("(" + pq.size() + " left on pq)");

	}

}



