package graph;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Graph2 {
	
	public int n;	//number of vertice
	public int[][] A;	//the adjacency matrix
	
	public Graph2 () {
		n = 0;
		A = null;
	}
	
	public Graph2 (int _n, int[][] _A) {
		this.n = _n;
		this.A = _A;
	}
	
	private int minKey(int key[], Boolean msts[]) {// function to find the vertex  
		int min = Integer.MAX_VALUE, minX=-1;
		for (int v = 0; v < n; v++)
			if (msts[v] == false && key[v] < min){
				min = key[v];
				minX = v;
				}
		return minX;
		}
	
	public int prim (int r) {
		
		int y[] = new int[n];// Array to store 
		int key[] = new int [n];// pick minimum weight 
		Boolean msts[] = new Boolean[n];//not yet included 
		for (int i = 0; i < n; i++) {
			key[i] = Integer.MAX_VALUE;
			msts[i] = false;
			y[i] = -1;
			}
		
		key[r] = 0; //  key r the vertex 
	
		y[r] = -1; // root of MST

		
		for (int count = 0; count < n-1; count++) { // n vertices
			int u = minKey(key, msts);
			msts[u] = true;
		for (int v = 0; v < n; v++)
			if (A[u][v]!=0 && msts[v] == false &&A[u][v] < key[v]){ //if statment for vertices not included 
				y[v] = u;
				key[v] = A[u][v];
				}
		}
		int weight=0;
		for(int i=0;i<n;i++) {
			if(y[i]!=-1)
				weight += A[i][y[i]];// if parent[i]!=-1, value to the weight
		}		
		return weight;// returns the minimum cost 

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 9;
		int A[][] = {
				{0, 4, 0, 0, 0, 0, 0, 8, 0}, 
				{4, 0, 8, 0, 0, 0, 0, 11, 0}, 
				{0, 8, 0, 7, 0, 4, 0, 0, 2}, 
				{0, 0, 7, 0, 9, 14, 0, 0, 0}, 
				{0, 0, 0, 9, 0, 10, 0, 0, 0}, 
				{0, 0, 4, 14, 10, 0, 2, 0, 0}, 
				{0, 0, 0, 0, 0, 2, 0, 1, 6}, 
				{8, 11, 0, 0, 0, 0, 1, 0, 7}, 
				{0, 0, 2, 0, 0, 0, 6, 7, 0} 
		};
		Graph2 g = new Graph2(n, A);
		System.out.println(g.prim(0));
	}

}
	