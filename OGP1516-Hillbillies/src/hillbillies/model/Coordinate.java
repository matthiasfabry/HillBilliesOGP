package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class that handles coordinates, built out of three components.
 * 
 * @author Matthias Fabry & Lukas Van Riel
 * @version 2.0
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
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * Method that computes the sum of 2 coordinates.
	 * @param	other
	 * 			the coordinate that will be added to the current coordinate.
	 * @return | this.Coordinate() + this.length()
	 */
	public Coordinate sum(Coordinate other) {
		return new Coordinate(this.getX() + other.getX(),
				this.getY() + other.getY(), this.getZ() + other.getZ());
	}
	/**
	 * Method that computes the length of the vector pointing 
	 * to the current coordinate.
	 * @return | Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY() + this.getZ()*this.getZ())
	 */
	public double length() {
		return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY()
				+ this.getZ() * this.getZ());
	}
	/**
	 * Method that normalizes the vector pointing to the current coordinate.
	 * 
	 * @return | this.Coordinate() / this.length()
	 */
	public Coordinate normalize() {
		return new Coordinate(this.getX() / this.length(),
				this.getY() / this.length(), this.getZ() / this.length());
	}
	/**
	 * Method that computes the difference between 2 coordinates.
	 * 
	 * @param	other
	 * 			the coordinate that will be subtracted from the
	 * 			current coordinate.
	 * @return | this.Coordinate - other
	 */
	public Coordinate difference(Coordinate other) {
		return new Coordinate(this.getX() - other.getX(),
				this.getY() - other.getY(), this.getZ() - other.getZ());
	}
	/**
	 * Method that returns the unit vector in the direction of a given
	 * coordinate, when starting from the current.
	 * 
	 * @param	goal
	 * 			the coordinate to which the unit vector will point.
	 * @return | goal.difference(this).normalize()
	 */
	public Coordinate directionVector(Coordinate goal) {
		Coordinate direction = goal.difference(this);
		direction.normalize();
		return direction;

	}
	/**
	 * Method that multiplies all coordinate components with 
	 * a given number
	 * 
	 * @param	p
	 * 			the number by which all components will be multiplied.
	 * @return |(this.Coordinate*p)
	 */
	public Coordinate scalarMult(double p) {
		return new Coordinate(this.getX() * p, this.getY() * p,
				this.getZ() * p);
	}
	/**
	 * Method that sets the coordinate to a new coordinate of which 
	 * all components are rounded down to the nearest integer.
	 * 
	 * @return |(Math.floor(this.Coordinate))
	 */
	public Coordinate floor() {
		return new Coordinate(Math.floor(this.getX()), Math.floor(this.getY()),
				Math.floor(this.getZ()));
	}
	/**
	 * Return the x-value of the coordinate.
	 */
	@Basic
	@Raw
	public double getX() {
		return this.x;
	}
	/**
	 * Return the y-value of the coordinate.
	 */
	@Basic
	@Raw
	public double getY() {
		return this.y;
	}
	/**
	 * Return the z-value of the coordinate.
	 */
	@Basic
	@Raw
	public double getZ() {
		return this.z;
	}
	/**
	 * Variables registering the x-, y- and z-component of the coordinate.
	 */
	private double x, y, z;
}
