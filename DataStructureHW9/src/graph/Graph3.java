package graph;

public class Graph3 {
	int n;
	int[][] A;
	int[] d;	//shortest distance
	/**
	 * @param args
	 */
	
	public Graph3 () {
		
	}
	
	public Graph3 (int _n, int[][] _A) {
		n = _n;
		A = _A;
		d = new int[n];
	}
	
	public void initialize_single_source(int s) {
		for (int i = 0; i < n; i++)
			d[i] = Integer.MAX_VALUE;
		d[s] = 0;
	}
	
	public void relax (int u, int v) {
		if (d[v] > (d[u] + A[u][v])) 
			d[v] = d[u] + A[u][v];
	}
	
	public boolean bellman_ford (int s) {
		
		initialize_single_source(s);
		int nvertices = A.length;
		for (int n = 1; n <= nvertices - 1; n++){
			for (int src = 0; src <= nvertices - 1; src++) {
				for (int destnode = 0; destnode <= nvertices - 1; destnode++) {
					if (A[src][destnode] != 0) {
						relax(src, destnode); //distance  src to destnode
					}
				}
					
			}
		}
		for (int srcnode = 0; srcnode <= nvertices - 1; srcnode++) {
			for (int dnode = 0; dnode <= nvertices - 1; dnode++) {
				 if (A[srcnode][dnode] != 0) {
					 if (d[dnode] > d[srcnode] + A[srcnode][dnode]) { //if neg edge short path decreases
						 return false;
					 }
				 }
			}
		}
		return true;
	}
	
	public void dijkstra (int s) {
		 int nVertices = A.length;
		 int[] y = new int[nVertices];
		 for (int i = 0; i < nVertices; i++) {
			 y[i] = 0; // edge from s to i distance 
			 if (A[s][i] != 0)
				 d[i] = A[s][i];
			 else
				 d[i] = Integer.MAX_VALUE; 
			 }
		 y[s]=1;
		 d[s]=0;
		 for (int i = 1; i < nVertices - 1; i++) {
			 int u = 0, dis = 0;
			 for (int j = 0; j < y.length; j++) {
				 if (y[j] == 0) {
					 dis = d[j];
					 u = j;
					 for (int k = j + 1; k < y.length; k++) {
						 if (dis > d[k] && y[k] == 0) {
							 dis = d[k];
							 u = k;
						 }
					 }
					 break;
					 
						 }
					 }
			 y[u] = 1;
			 for (int j = 1; j < nVertices; j++) { 
				 if (y[j] == 0 && A[u][j] != 0) {
					 relax(u, j);
				 }
			 }	 
		 }
	}
	
	public void display_distance () {
		for (int i = 0; i < n; i++)
			System.out.println(i + ": " + d[i]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int[][] A = {
		{0, 6, 0, 7, 0},
		{0, 0, 5, 8, -4},
		{0, -2, 0, 0, 0},
		{0, 0, -3, 0, 9},
		{2, 0, 7, 0, 0}
		};
		Graph3 g1 = new Graph3(n, A);
		g1.bellman_ford(0);
		g1.display_distance();
		
		System.out.println("-----------------------");
		
		int[][] B = {
		{0, 10, 0, 5, 0},
		{0, 0, 1, 2, 0},
		{0, 0, 0, 0, 4},
		{0, 3, 9, 0, 2},
		{7, 0, 6, 0, 0}
		};
		Graph3 g2 = new Graph3(n, B);
		g2.dijkstra(0);
		g2.display_distance();
	}

}
