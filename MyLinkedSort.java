package algs13;


import stdlib.*;

// PROBLEM 2.2.17
public class MyLinkedSort {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinkedSort () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    private static void print (String s, Node b) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println ();
    }
    private static void print (String s, Node b, double i) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println (": " + i);
    }

		 
    static public Node sort(Node l){ //
        if (l.next == null) {
            return l;
        }
        Node[] half = split(l);
        half[0] = sort(half[0]);
        half[1] = sort(half[1]);
        Node n = merge(half[0], half[1]);
        return n;

    }

    static public Node[] split(Node l){
        Node r = l.next;
        l.next = null;
        Node c = r.next;
        r.next = null;
        while (c != null) {
            Node t = c.next;
            c.next = l;
            Node temp = r;
            l = c;
            r = l;
            l = temp;
            c = t;
        }
        return new Node[] {l, r};
    }

    static public Node merge(Node lt, Node rt){
    	Node res;
    	if(lt ==null)
    		return rt;
    	if(rt == null)
    		return lt;
    	if(lt.item<rt.item) {
    		res = lt;
    		res.next = merge(lt.next, rt);
    	} else {
    		res = rt;
    		res.next =merge(lt, rt.next);
    	}
    	return res;
    }
    	
    
   

    public static void main (String args[]) {
        int[] a1 = new int[20];
        for (int i = 0; i < a1.length; i++)
            a1[i] = i;
        StdRandom.shuffle (a1);
        MyLinkedSort b1 = new MyLinkedSort ();
        for (int i:a1) b1.add(i);
        MyLinkedSort.print("before sort",b1.first);
        Node res1 = MyLinkedSort.sort(b1.first);
        MyLinkedSort.print("after sort",res1);

        int[] a2 = new int[20];
        for (int i = 0; i < a2.length; i++)
            a2[i] = i;
        StdRandom.shuffle (a2);
        MyLinkedSort b2 = new MyLinkedSort ();
        for (int i:a2) b2.add(i);
        MyLinkedSort.print("before sort",b2.first);
        Node res2 = MyLinkedSort.sort(b2.first);
        MyLinkedSort.print("after sort",res2);

    }
}



