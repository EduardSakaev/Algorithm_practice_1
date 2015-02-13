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

import java.util.Comparator;

public class Point implements Comparable<Point> {


	private final int x;                              // x coordinate
	private final int y;                              // y coordinate
	
	//compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() // YOUR DEFINITION HERE
	{
		 @Override
		 public int compare(Point p0, Point p1)
		 {
		     double s0 = slopeTo(p0);
		     double s1 = slopeTo(p1);
		     return Double.compare(s0, s1);
		 }
	};
	
	// create the point (x, y)
	public Point(int x, int y) {
	    /* DO NOT MODIFY */
	    this.x = x;
	    this.y = y;
	}
	
	// plot this point to standard drawing
	public void draw() {
	    /* DO NOT MODIFY */
	    StdDraw.point(x, y);
	}
	
	// draw the line segment from this point to that point
	public void drawTo(Point that) {
	    /* DO NOT MODIFY */
	    StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	// slope between this point and that point
	public double slopeTo(Point that) {
	    /* YOUR CODE HERE */
		double slope;
		if (that.y == this.y && that.x == this.x)
			slope = -0;
		else if (that.y == this.y)
			slope = Math.abs(this.y);
		else if (that.x == this.x) 
			slope = - Math.abs(this.x);
		else
			slope = (that.y * 1.0 - this.y)/(that.x * 1.0 - this.x);
	    return slope;
	}
	
	// is this point lexicographically smaller than that one?
	// comparing x-coordinates and breaking ties by y-coordinates
	public int compareTo(Point that) {
	    if(this.y < that.y) return 1;
	    else if(this.y == that.y) {
	        if(this.x < that.x) return 1;
	    else return 0;
	    }
	    else return 0;
	} 
	
	// return string representation of this point
	public String toString() {
	    /* DO NOT MODIFY */
	    return "(" + x + ", " + y + ")";
	}
	
	// unit test
	public static void main(String[] args) {
	    /* YOUR CODE HERE */
	}
}