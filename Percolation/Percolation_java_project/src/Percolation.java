
/****************************************************************************
 *  Compilation:  java Percolation.java
 *  Execution:    java Percoaltion.class N
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
   private byte [] grid;
   private int igridSize;
   private WeightedQuickUnionUF weightedquf1;
   private WeightedQuickUnionUF weightedquf2;

   /*---------------------------------------------------------
    *  // create N-by-N grid, with all sites blocked
    *
    *---------------------------------------------------------*/
   public Percolation(int N)
   {
       if (N <= 0)
          throw new java.lang.IllegalArgumentException("illegal argument N <= 0");
       grid = new byte[N * N + 1];
       for (int i = 0; i < N * N; ++i)
            grid[i] = 0;          //check if is open

       igridSize = N;        //Numbers of raw/columns   
       // +2 means 0 - top  N + 1 - bottom
       weightedquf1 = new WeightedQuickUnionUF(N * N + 2);
       weightedquf2 = new WeightedQuickUnionUF(N * N + 2);
   }

   /*---------------------------------------------------------
    *  open site (row i, column j) if it is not open already
    *  
    *---------------------------------------------------------*/
   public void open(int i, int j) 
   {
       if (i <= 0 || i > igridSize)
          throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > igridSize) 
          throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");

       int id, iNearIndex;
       id = getGridIndex(i, j);

       if (j + 1 <= igridSize) //right 
       {
            iNearIndex = getGridIndex(i, j + 1);
           if (isOpen(i, j + 1))
           {
              weightedquf1.union(id, iNearIndex);
              weightedquf2.union(id, iNearIndex);
           }
       }

       if (j - 1 > 0) //left
       {
           iNearIndex = getGridIndex(i, j - 1);
           if (isOpen(i, j - 1))
           {
              weightedquf1.union(id, iNearIndex);
              weightedquf2.union(id, iNearIndex);
           }
       }

       if (i - 1 > 0) //top
       {
           iNearIndex = getGridIndex(i - 1, j);
           if (isOpen(i - 1, j))
           {
              weightedquf1.union(id, iNearIndex);
              weightedquf2.union(id, iNearIndex);
           }
       }

       if (i + 1 <= igridSize) //bottom
       {
           iNearIndex = getGridIndex(i + 1, j);
           if (isOpen(i + 1, j))
           {
              weightedquf1.union(id, iNearIndex);
              weightedquf2.union(id, iNearIndex);
           }
       }
       grid[id] = 1;

       if (i == 1)
       {
          weightedquf1.union(0, id);
          weightedquf2.union(0, id);
       }

       if (i == igridSize)
           weightedquf2.union(id, igridSize * igridSize + 1);
   }

    /*---------------------------------------------------------
    *     *  is site (row i, column j) open?
    *
    *---------------------------------------------------------*/
   public boolean isOpen(int i, int j)
   {
       if (i <= 0 || i > igridSize)
         throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > igridSize)
         throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");
       boolean bOpen = false;
       if (grid[getGridIndex(i, j)] != 0)
          bOpen =  true;
       return bOpen;
   }

   /*---------------------------------------------------------
    *  // is site (row i, column j) full?
    *  
    *---------------------------------------------------------*/
   public boolean isFull(int i, int j) 
   {
       if (i <= 0 || i > igridSize) 
         throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > igridSize)
         throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");
       boolean bConnected = false;
       if (weightedquf1.connected(getGridIndex(i, j), 0))
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
       if (weightedquf2.connected(ibottom, 0))
           bPercolate = true;
       return bPercolate;
   }

   /*---------------------------------------------------------
    *  translate i, j raw into sequence.
    *  
    *---------------------------------------------------------*/
   private int getGridIndex(int i, int j)
   {
       int id = (i - 1) * igridSize + j;
       return id;
   }
   //public static void main(String[] args);   // test client (optional)
}