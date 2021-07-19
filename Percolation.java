package algs15.perc;
import algs15.*;
// Uncomment the import statements above.
// You can test this using InteractivePercolationVisualizer and PercolationVisualizer
// All methods should make at most a constant number of calls to the UF data structure,
// except percolates(), which may make up to N calls to the UF data structure.
public class Percolation {
	int N;
	boolean[] open;
	UF u;

  // tells me if the slot open[k] is open
// declare a UF object here
// TODO: more fields to add here
public Percolation(int N) {
	this.N = N;
	this.open = new boolean[N*N];
	this.u = new WeightedUF(N*N + 2);
	// this.u = new CompressionUF();  of suitable size... n
	// N^2 or N^2 +2 depending on what you do
	// TODO: more to do here
	for(int i = 0; i < N*N; i++) {
		this.open[i] = false;
	}

}
// open site (row i, column j) if it is not already
public void open(int i, int j) {
	open[i*N+j] = true;
	// TODO: more to do here.
	int pos = ((i*N + j) +1);
	// union to the neighbors who are open
	// neighbors of i,j (i+1,j), (i-1,j), (i,j+1), (i,j-1)
	if (i < N-1) { 
		if (isOpen(i+1, j)) {
			u.union(pos, pos + N); 	
	    }
	}
	
	if (i > 0) { 
		if (isOpen(i-1, j)) {
			u.union(pos, pos - N);	
		}
	}
	
	if (j < N-1 && isOpen(i, j+1)) { 
		u.union(pos, pos + 1);	
	}
	
	if (j > 0 && isOpen(i, j-1)) { 
		u.union(pos, pos - 1);	
		
	}
	
	if (i == 0) {
		u.union(0, pos);		
	}
	
	if (i == (N -1)) {
		u.union(N*N + 1, pos);				
	}
}

//is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return open[i*N+j];
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		// TODO
		return u.connected(0, i*N+j+1);
	}
	// does the system percolate?
	public boolean percolates() {
		// TODO
		return u.connected(0, N*N + 1);
	}
}