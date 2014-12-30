
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
   private int [] grid;
   private int igridSize;
   private WeightedQuickUnionUF weightedquf;

   /*---------------------------------------------------------
    *  // create N-by-N grid, with all sites blocked
    *  
    *---------------------------------------------------------*/
   public Percolation(int N) 
   {
       grid = new int[N * N + 1];
       for (int i = 0; i < N * N; ++i)
            grid[i] = 0;          //check if is open
         
       igridSize = N;        //Numbers of raw/columns   
       // +2 means 0 - top  N + 1 - bottom
       weightedquf = new WeightedQuickUnionUF(N * N + 2);
   }      

   /*---------------------------------------------------------
    *  open site (row i, column j) if it is not open already
    *  
    *---------------------------------------------------------*/
   public void open(int i, int j) 
   {  
       int id    = getGridIndex(i, j);
       
       if (j + 1 <= igridSize) //right 
       {
           int iright = getGridIndex(i, j + 1);
           if (!isOpen(i, j + 1) && !weightedquf.connected(id, iright))
           weightedquf.union(id, iright);
       }
       if (j - 1 > 0) //left
       {
           int ileft = getGridIndex(i, j - 1);
           if (!isOpen(i, j - 1) && !weightedquf.connected(id, ileft))
           weightedquf.union(id, ileft);
       }

       if (i - 1 > 0) //top
       {
           int itop = getGridIndex(i - 1, j);
           if (!isOpen(i - 1, j) && !weightedquf.connected(id, itop))
           weightedquf.union(id, itop);
       }

       if (i + 1 <= igridSize) //bottom
       {
           int ibottom = getGridIndex(i + 1, j);
           if (!isOpen(i + 1, j) && !weightedquf.connected(id, ibottom))
           weightedquf.union(id, ibottom);
       }

       grid[id] = 1;

       if (i == 1)
       weightedquf.union(0, id);

       for (int k = 1; k <= igridSize; ++k)
       {
           id = getGridIndex(igridSize, k);
           if (weightedquf.connected(0, id))
           {
               weightedquf.union(id, igridSize * igridSize + 1);
               break;
           }
       }

   }          
   
   /*---------------------------------------------------------
    *  is site (row i, column j) open?
    *  
    *---------------------------------------------------------*/
   public boolean isOpen(int i, int j) 
   {
       int id = getGridIndex(i, j);
       boolean bOpen = true;
       if (grid[id] != 0)
       bOpen =  false;
       return bOpen;
   }     
   
   /*---------------------------------------------------------
    *  // is site (row i, column j) full?
    *  
    *---------------------------------------------------------*/
   public boolean isFull(int i, int j) 
   {
       int id = getGridIndex(i, j);
       boolean bConnected = false;
       if (weightedquf.connected(0, id))
       bConnected =  true;
       return bConnected;
   } 
   
   
   /*---------------------------------------------------------
    *  // does the system percolate?
    *  
    *---------------------------------------------------------*/
   public boolean percolates() 
   {
       int ibottom = igridSize * igridSize + 1;
       boolean bPercolate = false;
       if (weightedquf.connected(0, ibottom) 
           || weightedquf.connected(ibottom, 0))
           bPercolate = true;
       return bPercolate;
   }             

   /*---------------------------------------------------------
    *  translate i, j raw into sequence.
    *  
    *---------------------------------------------------------*/
   private int getGridIndex(int i, int j)
   {
       return (i - 1) * igridSize + j;
   }
   //public static void main(String[] args);   // test client (optional)
}