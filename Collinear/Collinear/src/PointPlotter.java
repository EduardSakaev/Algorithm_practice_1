/*************************************************************************
 *  Compilation:  javac PointPlotter.java
 *  Execution:    java PointPlotter input.txt
 *  Dependencies: Point.java, In.java, StdDraw.java
 *
 *  Takes the name of a file as a command-line argument.
 *  Reads in an integer N followed by N pairs of points (x, y)
 *  with coordinates between 0 and 32,767, and plots them using
 *  standard drawing.
 *
 *************************************************************************/

import java.lang.Object;
import java.util.Arrays;

public class PointPlotter {
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.002);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        
        //save point in the array
        if (N > 4)
        {
	        int Count = 0;
	        Point [] point = new Point[N];
	        for (int i = 0; i < N; i++) {
	            int x = in.readInt();
	            int y = in.readInt();
	            point[Count] = new Point(x, y);
	            point[Count++].draw();
	        }
	        
	        //check on collinearity
	        for (int i = 0; i < N-3; ++i)
	        	for (int j = i + 1; j < N-2; ++j)
	        		for (int k = j + 1; k < N-1; k++ )
	        			if (point[j].SLOPE_ORDER.compare(point[i], point[k]) == 0) // значит их 3
	        				for (int l = k + 1; l < N; ++l)
	        					if (point[k].SLOPE_ORDER.compare(point[j], point[l]) == 0){ // значит их 4
			        				point[i].drawTo(point[j]);
			        				point[j].drawTo(point[k]);
			        				point[k].drawTo(point[l]);
	        					}
	        
	        // display to screen all at once
	        StdDraw.show(0);
	
	        // reset the pen radius
	        StdDraw.setPenRadius();
        
        }
    }
}
