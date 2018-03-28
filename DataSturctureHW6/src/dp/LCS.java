package dp;

public class LCS {
	
	public static int lcs_length (String X, String Y) {
		/*
		 * fill in your code here
		 * Note: return the length of LCS, instead of c and b
		 */
	       int lcs = 0;
		   int m = X.length();
	       int n = Y.length();
	 
	       char[][] b = new char[m][n];
	       int[][] c = new int[m+1][n+1];
	       for(int i = 1; i < m; i++)
	       {
	         c[i][0]= 0;
	       }
	       for(int j = 0; j < n; j++)
	       {
	         c[0][j] = 0;
	       }
	       for (int i = 1; i < m; i++)
	       {
	         for (int j = 1; j < n; j++)
	         {
	            if (X.charAt(i) == Y.charAt(j))
	            {
	               c[i][j] = c[i - 1][j - 1] + 1;
	               b[i][j] = '(';
	            }
	            else if (c[i - 1][j] >= c[i][j - 1])
	            {
	               c[i][j] = c[i - 1][j];
	               b[i][j] = '|';
	            }
	            else
	            {
	               c[i][j] = c[i][j - 1];
	               b[i][j] = '-';
	            }
	         }   
	       }
	       int x1 = m - 1;
	       int y1 = n - 1;
	       while (x1 != 0 || y1 != 0)
	       {
	         if (b[x1][y1] == '(')
	         {
	        	 x1--;
	        	 y1--; 
	            lcs++;
	         }
	         else if (b[x1][y1] == '|')
	         {
	        	 	x1--;
	         }
	         else{
	        	 	y1--;
	            }
	       }
	       return lcs;

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LCS.lcs_length("ABCBDAB", "BDCABA"));
		System.out.println(LCS.lcs_length("ACCGGTCGAGTGCGCGGAAGCCGGCCGAA", "GTCGTTCGGAATGCCGTTGCTCTGTAAA"));
	}

}
