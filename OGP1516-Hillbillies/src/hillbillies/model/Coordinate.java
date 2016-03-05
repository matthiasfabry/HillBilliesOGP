package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * Value class that handles coordinates in the gam eworld
 * 
 * 
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 */
@Value
public class Coordinate {

	/**
	 * Initialize a Coordinate with given x-, y-, and z-component.
	 * 
	 * @param x
	 *          The x-component of the Coordinate
	 * @param y
	 *          The y-component of the Coordinate
	 * @param z
	 *          The z-component of the Coordinate
	 */
	public Coordinate(double x, double y, double z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	/**
	 * Method that computes the sum of 2 coordinates.
	 * 
	 * @param	other
	 * 			the coordinate that will be added to the current coordinate.
	 */
	public Coordinate sum(Coordinate other){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()+other.getX());
		result.setY(this.getY()+other.getY());
		result.setZ(this.getZ()+other.getZ());
		return result;
	}
	/**
	 * Method that computes the length of the vector pointing 
	 * to the current coordinate.
	 */
	public double length(){
		return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY() + this.getZ()*this.getZ());
	}
	/**
	 * Method that normalizes the vector pointing to the current coordinate.
	 */
	public Coordinate normalize(){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()/this.length());
		result.setY(this.getY()/this.length());
		result.setZ(this.getZ()/this.length());
		return result; 
	}
	/**
	 * Method that computes the difference between 2 coordinates.
	 * 
	 * @param	other
	 * 			the coordinate that will be subtracted from the
	 * 			current coordinate.
	 */
	public Coordinate difference(Coordinate other){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()-other.getX());
		result.setY(this.getY()-other.getY());
		result.setZ(this.getZ()-other.getZ());
		return result;
	}
	/**
	 * Method that returns the unit vector in the direction of a given
	 * coordinate, when starting from the current.
	 * @param	goal
	 * 			the coordinate to which the unit vector will point.
	 */
	public Coordinate directionVector(Coordinate goal){
		Coordinate direction = goal.difference(this);
		direction.normalize();
		return direction;
		
	}
	/**
	 * Method that multiplies all coordinate components with 
	 * a given number
	 * @param	p
	 * 			the number by which all components will be multiplied.
	 */
	public Coordinate scalarMult(double p){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()*p);
		result.setY(this.getY()*p);
		result.setZ(this.getZ()*p);
		return result;
	}
	/**
	 * Method that sets the coordinate to a new coordinate of which 
	 * all components are rounded down to the nearest integer.
	 */
	public Coordinate floor(){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(Math.floor(this.getX()));
		result.setY(Math.floor(this.getY()));
		result.setZ(Math.floor(this.getZ()));
		return result;
	}
	/**
	 * Return the x-value of the coordinate.
	 */
	public double getX(){
		return this.x;
	}
	/**
	 * sets the x-value of the Coordinate to a given value.
	 * @param	x
	 * 			the new x-position
	 */
	public void setX(double x){
		this.x = x;
	}
	/**
	 * Return the y-value of the coordinate.
	 */
	public double getY(){
		return this.y;
	}
	/**
	 * sets the y-value of the Coordinate to a given value.
	 * @param	y
	 * 			the new y-position
	 */
	public void setY(double y){
		this.y = y;
	}
	/**
	 * Return the z-value of the coordinate.
	 */
	public double getZ(){
		return this.z;
	}
	/**
	 * sets the z-value of the Coordinate to a given value.
	 * @param	z
	 * 			the new z-position
	 */
	public void setZ(double z){
		this.z = z;
	}
	/**
	 * Variables registering the x-, y- and z-component of the coordinate.
	 */
	private double x,y,z;

}
