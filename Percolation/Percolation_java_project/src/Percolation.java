
/****************************************************************************
 *  Compilation:  javac InteractivePercolationVisualizer.java
 *  Execution:    java InteractivePercolationVisualizer N
 *  Dependencies: PercolationVisualizer.java Percolation.java
 *                StdDraw.java StdOut.java
 *
 *  This program takes the grid size N as a command-line argument.
 *  Then, the user repeatedly clicks sites to open with the mouse.
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black.
 *
 ****************************************************************************/

public class Percolation 
{
   private int grid[];
   private int igridSize;
   private WeightedQuickUnionUF weightedquf;
	
   public Percolation(int N) // create N-by-N grid, with all sites blocked
   {
	   grid = new int[N * N + 1];
	   for (int i = 0; i < N * N; ++i)
		   grid[i] = 0;                                    //check if is open
	   
	   igridSize = N;                                     //Numbers of raw/columns
	   
	   weightedquf = new WeightedQuickUnionUF(N * N + 2); // +2 means 0 - top  N + 1 - bottom
   }      
   
   public void open(int i, int j) // open site (row i, column j) if it is not open already
   { 
	   int id    = GetGridIndex(i, j);
	   
	   if (j + 1 <= igridSize) //right 
	   {
		   int iright = GetGridIndex(i, j + 1);
		   if (!isOpen(i, j + 1) && !weightedquf.connected(id, iright))
			   weightedquf.union(id, iright);
	   }
	   
	   if (j - 1 > 0) //left
	   {
		   int ileft = GetGridIndex(i, j - 1);
		   if (!isOpen(i, j - 1) && !weightedquf.connected(id, ileft))
			   weightedquf.union(id, ileft);
	   }
	   
	   if (i - 1 > 0) //top
	   {
		   int itop = GetGridIndex(i - 1, j);
		   if (!isOpen(i - 1, j) && !weightedquf.connected(id, itop))
			   weightedquf.union(id, itop);
	   }
	   
	   if (i + 1 <= igridSize) //bottom
	   {
		   int ibottom = GetGridIndex(i + 1, j);
		   if (!isOpen(i + 1, j) && !weightedquf.connected(id, ibottom))
			   weightedquf.union(id, ibottom);
	   }
	   
	   grid[id] = 1;
	   
	   if (i == 1)
		   weightedquf.union(0, id);
	   
	   for (i = 1; i <= igridSize; ++i)
	   {
		   id = GetGridIndex(igridSize, i);
		   if (weightedquf.connected(0, id))
		   {
			   weightedquf.union(id, igridSize * igridSize + 1);
			   break;
		   }
		   
	   }
		   
   }          
   public boolean isOpen(int i, int j) // is site (row i, column j) open?
   {
	   int id = GetGridIndex(i, j);
	   boolean isOpen;
	   isOpen = (grid[id] != 0) ? false : true;
	   return isOpen;
   }     
   
   public boolean isFull(int i, int j) // is site (row i, column j) full?
   {
	   int id = GetGridIndex(i, j);
	   boolean is_connected = false;
	   if (weightedquf.connected(0, id))
		   is_connected =  true;
	   
	   return is_connected;
   } 
   
   public boolean percolates() // does the system percolate?
   {
	   int ibottom = igridSize * igridSize + 1;
	   boolean is_percolate = false;
	   if (weightedquf.connected(0, ibottom) || weightedquf.connected(ibottom, 0))
			   is_percolate = true;
	   return is_percolate;
   }             

   private int GetGridIndex(int i, int j)
   {
	   return (i - 1) * igridSize + j;
   }
   //public static void main(String[] args);   // test client (optional)
}