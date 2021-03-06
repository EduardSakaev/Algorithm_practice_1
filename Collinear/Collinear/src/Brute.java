/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

public class Brute {
   public static void main(String[] args) {
	   In in = new In(args[0]);      // input file
       int N = in.readInt();         // N points

       if (N > 4)
       {
    	   int Count = 0;
	       Point [] point = new Point[N];
	       for (int i = 0; i < N; i++) {
	            int x = in.readInt();
	            int y = in.readInt();
	            point[Count++] = new Point(x, y);
	        }
	       
	       //check on collinearity
	        for (int i = 0; i < N-3; ++i)
	        	for (int j = i + 1; j < N-2; ++j)
	        		for (int k = j + 1; k < N-1; k++ )
	        			if (point[j].SLOPE_ORDER.compare(point[i], point[k]) == 0) // значит их 3
	        				for (int l = k + 1; l < N; ++l)
	        					if (point[k].SLOPE_ORDER.compare(point[j], point[l]) == 0){ // значит их 4
	        						StdOut.println( point[i].toString() + " -> " + 
			        								point[j].toString() + " -> " + 
			        								point[k].toString() + " -> " + 
			        								point[l].toString());
	        					}
       }
   }
}