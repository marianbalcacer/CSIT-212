package graph;
import ds.Queue;


public class Graph {
	public int n;	//number of vertice
	public int[][] A;	//the adjacency matrix
	private final int WHITE = 2;
	private final int GRAY = 3;
	private final int BLACK = 4;
	
	public Graph () {
		n = 0;
		A = null;
	}
	
	public Graph (int _n, int[][] _A) {
		this.n = _n;
		this.A = _A;
	}
	
	/*
	 * Input: s denotes the index of the source node
	 * Output: the array dist, where dist[i] is the distance between the i-th node to s
	 */
	public int[] bfs (int s) {
		int V = n;
		int dist[][] = new int[V][V]; //storing the distance from each node
	

		for (int i = 0; i < V; i++) 
		for (int j = 0; j < V; j++) 
		dist[i][j] = A[i][j];
		for (int i = 0; i < V; i++) { 
			for (int j = 0; j < V; j++) { //vertices as dest 
				if (dist[i][s] + dist[s][j] < dist[i][j]) //this will update the value of dis from short path
					dist[i][j] = dist[i][s] + dist[s][j];
				}
			}
		int[] distS = new int[V];
		for(int x=0;x<V;x++) {
			distS [x] = dist[x][s];
			}

		return distS;

		}
		
	
	public void print_array (int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.println(i + ": " + array[i]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 8;
		int[][] A = 
			{{0, 1, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 1, 0},
			{0, 0, 1, 0, 0, 0, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 1, 0}};
		Graph g = new Graph(n, A);
		g.print_array(g.bfs(1));
	}

}
