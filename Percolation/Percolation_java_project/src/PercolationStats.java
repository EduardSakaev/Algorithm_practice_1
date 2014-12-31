/****************************************************************************
 * Percolation Stats, calculates mean, standard deviation, conf low, conf hight 95 %
 *  This program takes the grid size N as a command-line argument.
 *  Then, the user repeatedly clicks sites to open with the mouse.
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black.
 *
 ****************************************************************************/


public class PercolationStats 
{
   private double [] dFractionArray;
   private int iExperiments;

   /*---------------------------------------------------------
    *  // perform T independent experiments on an N-by-N grid
    *  
    *---------------------------------------------------------*/
   public PercolationStats(int N, int T)  
   {
       if (N <= 0 || T <= 0)
       throw new java.lang.IllegalArgumentException();

       dFractionArray = new double[T];
       iExperiments   = T;

       for (int i = 0; i < T; ++i)
       {
           Percolation perc = new Percolation(N);
           int iRaw, iColumn, iOpened = 0;

           while (!perc.percolates())
           {
               iRaw    = StdRandom.uniform(1, N + 1);
               iColumn = StdRandom.uniform(1, N + 1);
               if (!perc.isOpen(iRaw, iColumn))
               {
                   perc.open(iRaw, iColumn);
                   ++iOpened;
               }
           }

           dFractionArray[i] = (double) iOpened/(N * N);
       }
   }

   /*---------------------------------------------------------
    *  // sample mean of percolation threshold
    *  
    *---------------------------------------------------------*/
   public double mean()  
   {
       double dmean, dsum = 0.0;

       for (int i = 0; i < iExperiments; ++i)
       {
          dsum = dsum + dFractionArray[i];
       }

       dmean = dsum/iExperiments;
       return dmean;
   }
   
   /*---------------------------------------------------------
    * // sample standard deviation of percolation threshold
    *  
    *---------------------------------------------------------*/
   public double stddev()
   {
       double dmean = mean();
       double dquadrdiff = 0.0, dstddev = 0.0;

       for (int i = 0; i < iExperiments; ++i)
       {
          dquadrdiff = dquadrdiff + (dFractionArray[i] - dmean)
                       * (dFractionArray[i] - dmean);
       }

       dstddev = Math.sqrt(dquadrdiff / (iExperiments - 1));
       return dstddev;
   }
   
   /*---------------------------------------------------------
    * // low  endpoint of 95% confidence interval
    *  
    *---------------------------------------------------------*/
   public double confidenceLo()
   {
       double dConfLow = 0.0;
       double dmean = mean();
       double dStdDev = stddev();

       dConfLow = dmean - 1.96 * dStdDev/Math.sqrt(iExperiments);
       return dConfLow;
   }

   /*---------------------------------------------------------
    * // high endpoint of 95% confidence interval
    *  
    *---------------------------------------------------------*/
   public double confidenceHi()  
   {
       double dConfHigh = 0.0;
       double dmean = mean();
       double dStdDev = stddev();

       dConfHigh = dmean + 1.96 * dStdDev/Math.sqrt(iExperiments);
       return dConfHigh;
   }

   public static void main(String[] args)// test client (described below)
   {
       PercolationStats percstats = new PercolationStats(200, 100);
       StdOut.println("Mean()                  = " + percstats.mean());
       StdOut.println("StdDev()                = " + percstats.stddev());
       StdOut.println("95% confidence interval = " + percstats.confidenceLo()
                     +  " " + percstats.confidenceHi());
   }
}