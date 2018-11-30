package maman13;

/**
 * This class Represents 2 dimensional points
 *
 * @author Eliyahu Mashiah
 * @version 24.11.2018
 */
public class Point {
private double _radius;//representation of the radius in the polar system  
private double _alpha;//representation of the alpha(theta) in the polar system 

final private double RADIANS_TO_DEGREES=180/Math.PI;//converts radians to degrees
final private double DEGREES_TO_RADIANS=Math.PI/180;//converts degrees to radians
final private double MIN_X=0;//the minimum of the x coordinates
final private double MIN_Y=0;//the minimum of the y coordinates
final private double MIN_ALPHA=0;//the minimum of alpha 
final private int POWER_BY_2=2;


/**
* Constructor for objects of class Point. Construct a new point with the specified x y coordinates. If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
* @param x  The coordinate of x
* @param y  The coordinate of y
*/
public Point(double x,double y)
{
	if (x<MIN_X)//checks if the given x is smaller than the minimum x 
		x=MIN_X;//sets x to be the minimum x
	if(y<MIN_Y)//checks if the given y is smaller than the minimum y
		y=MIN_Y;//sets y to be the minimum y
	
	if (y==MIN_Y && x==MIN_X)//checks if both x and y are equal to the minimum x and y respectively
	{
		_alpha=MIN_ALPHA;//sets alpha to be the minimum alpha 
	}
	
	else
	{
		_alpha=Math.atan(y/x)*RADIANS_TO_DEGREES;//calculates alpha using the given X and Y coordinates and converts alpha from radians to degrees
	}
	_radius=Math.sqrt(Math.pow(x,POWER_BY_2)+Math.pow(y,POWER_BY_2));//calculates the radius using the given X and Y coordinates
	
}

/**
 * Constructor for objects of class Point. Copy constructor, construct a point using another point.
 * @param other The point from which to construct the new object
 */
public Point(Point other)
{
	_radius=other._radius;
	_alpha=other._alpha;
}
/**
 * This method returns the x coordinate of the point.
 * @return The x coordinate of the point
 */
public double getX()
{
	double x=Math.cos(_alpha*DEGREES_TO_RADIANS)*_radius;//calculates the X coordinates using alpha and radius
	x=Math.round(x*10000.0)/(double)10000;//rounds up the X coordinates
	return x;
}

/**
 * This method returns the y coordinate of the point.
 * @return The x coordinate of the point
 */
public double getY()
{
	double y=Math.sin(_alpha*DEGREES_TO_RADIANS)*_radius;//calculates the Y coordinates using alpha and radius
	y=Math.round(y*10000.0)/(double)10000;//rounds up the Y coordinates
	return y;
}

/**
 * This method sets the x coordinate of the point. If the new x coordinate is negative the old x coordinate will remain unchanged.
 * @param num The new x coordinate
 */
public void setX (double num)
{
	if (num>=MIN_X)
	{
	double y=getY();//getting the Y position of the coordinate
	_alpha=Math.atan(y/num)*RADIANS_TO_DEGREES;//calculates the new alpha using the given number
	_radius=Math.sqrt(Math.pow(num,POWER_BY_2)+Math.pow(y,POWER_BY_2));//calculates the new radius using the given number 
	}
}

/**
 * This method sets the y coordinate of the point. If the new y coordinate is negative the old y coordinate will remain unchanged.

 * @param num The new y coordinate
 */
public void setY (double num)
{
	if (num>=MIN_Y)
	{
	double x=getX();//getting the X position of the coordinate
	_alpha=Math.atan(num/x)*RADIANS_TO_DEGREES;//calculates the new alpha using the given number
	_radius=Math.sqrt(Math.pow(x,POWER_BY_2)+Math.pow(num,POWER_BY_2));//calculates the new radius using the given number 
	}
}
/**
 * Returns a string representation of Point in the format (x,y).
 * @return A String representation of the Point
 */
public String toString()
{
	return "("+getX()+","+getY()+")";
}

/**
 * Check if the given point is equal to this point.
 * @param other  The point to check equality with
 * @return True if the given point is equal to this point
 */
public boolean equals (Point other)
{
	return (this.getX()==other.getX() && this.getY()==other.getY());//checks if the current points is equal to the other points by comparing the X and Y coordinates of both points
}

/**
 * Check if this point is above a received point.
 * @param other The point to check if this point is above
 * @return True if this point is above the other point
 */
public boolean isAbove (Point other)
{

	return this.getY()>other.getY();//checks and returns true if the current point is above the other point by checking the Y coordinates of both points
}

/**
 * Check if this point is below a received point.
 * @param other The point to check if this point is below
 * @return True if this point is below the other point
 */
public boolean isUnder (Point other)
{
	return other.isAbove(this);//checks if the current point is under the other point using IsAbove method 
}

/**
 * Check if this point is left of a received point.
 * @param other The point to check if this point is left of
 * @return True if this point is left of the other point
 */
public boolean isLeft (Point other)
{
	return this.getX()<other.getX();//checks and returns true if the current point is on the left of the other point by checking the X coordinates of both points
}

/**
 * Check if this point is right of a received point.
 * @param other The point to check if this point is right of
 * @return True if this point is right of the other point
 */
public boolean isRight (Point other) 
{
	return other.isLeft(this);//checks if the current point is on the right of the other point using IsLeft method 
}

/**
 * Check the distance between this point and a given point.
 * @param p The point to check the distance from
 * @return The distance between two points
 */
public double distance (Point p)
{
	return Math.round(Math.sqrt(Math.pow((p.getY()-this.getY()),POWER_BY_2)+Math.pow((p.getX()-this.getX()),POWER_BY_2))*10000.0)/(double)10000;//calculates and returns the distance between 2 points
}

/**
 * Moves a point. If either coordinate becomes negative the point remains unchanged.
 * @param dx The difference to add to x
 * @param dy The difference to add to y
 */
public void move (double dx, double dy)
{
	if(this.getX()+dx>=MIN_X && this.getY()+dy>=MIN_Y)//checks if the current X plus dx and the current Y plus dy are bigger than the minimum X and Y respectively 
	{
		setX((getX()+dx));//sets X to be moved by dx from it's current position
		setY((getY()+dy));//sets y to be moved by dy from it's current position
	}
}


}//end class
