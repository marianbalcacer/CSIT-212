import java.util.Scanner;
class FloydWarshall{
   static int n;
   static int [][]graph;
   public static void main(String []arg) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter number of vertices: ");
       //Number of vertices
       n = sc.nextInt();
       //given graph
       graph = new int[n][n];
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               if(i != j){
                   graph[i][j] = 1000000;
               }
           }
       }
       System.out.println("Enter number of directed edges");
       //number of edges
       int e = sc.nextInt();
       System.out.println("Enter the edges with weights: ");
       for(int i=0;i<e;i++){
           //starting node
           int x = sc.nextInt();
           //destination node
           int y = sc.nextInt();
           //weight
           int w = sc.nextInt();
           graph[x-1][y-1] = w;
       }

       //display given graph
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               System.out.print(String.format("%10d",graph[i][j]));
           }
           System.out.println();
       }

       //floyd warshall
       for(int k=0;k<n;k++){
           for(int i=0;i<n;i++){
               for(int j=0;j<n;j++){
                   if(graph[i][k] + graph[k][j] < graph[i][j]){
                       graph[i][j] = graph[i][k] + graph[k][j];
                   }
               }
           }
       }

       //display all pair shortest paths
       System.out.println("All pair shortest paths are : ");
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               System.out.print(String.format("%10d",graph[i][j]));
           }
           System.out.println();
       }
   }
}