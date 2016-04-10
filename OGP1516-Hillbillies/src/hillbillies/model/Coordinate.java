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
	
	// Constructor //

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
	
	// Utility Methods //
	
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

	// Getters //

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

	// Neighbouring//
	/**
	 * Method that lists all Coordinate that are adjacent to this coordinate
	 * @return	a list of Coordinates that are adjacent this coordinate
	 * 			
	 */
	Coordinate[] adjacentCoordinates() {
		Coordinate[] result = new Coordinate[26];
		result[0]  = this.sum(new Coordinate(1, 0, 0));
		result[1]  = this.sum(new Coordinate(-1, 0, 0));
		result[2]  = this.sum(new Coordinate(0, 1, 0));
		result[3]  = this.sum(new Coordinate(0, -1, 0));
		result[4]  = this.sum(new Coordinate(0, 0, 1));
		result[5]  = this.sum(new Coordinate(0, 0, -1));
		result[6]  = this.sum(new Coordinate(1, 1, 0));
		result[7]  = this.sum(new Coordinate(-1, -1, 0));
		result[8]  = this.sum(new Coordinate(1, 1, 1));
		result[9]  = this.sum(new Coordinate(-1, -1, -1));
		result[10] = this.sum(new Coordinate(1, 0, 1));
		result[11] = this.sum(new Coordinate(-1, 0, -1));
		result[12] = this.sum(new Coordinate(0, 1, 1));
		result[13] = this.sum(new Coordinate(0, -1, -1));
		result[14] = this.sum(new Coordinate(1, -1, 0));
		result[15] = this.sum(new Coordinate(-1, 1, 0));
		result[16] = this.sum(new Coordinate(1, 0, -1));
		result[17] = this.sum(new Coordinate(-1, 0, 1));
		result[18] = this.sum(new Coordinate(0, 1, -1));
		result[19] = this.sum(new Coordinate(0, -1, 1));
		result[20] = this.sum(new Coordinate(1, 1, -1));
		result[21] = this.sum(new Coordinate(-1, -1, 1));
		result[22] = this.sum(new Coordinate(1, -1, 1));
		result[23] = this.sum(new Coordinate(-1, 1, -1));
		result[24] = this.sum(new Coordinate(-1, 1, 1));
		result[25] = this.sum(new Coordinate(1, -1, -1));
		return result;

	}
	
	// Fields //
	
	/**
	 * Variables registering the x-, y- and z-component of the coordinate.
	 */
	private double x, y, z;
	
	// Overrides from Object //
	
	@Override
	public boolean equals(Object object){
		if (object.getClass() != Coordinate.class)
			return false;
		Coordinate casted = (Coordinate) object;
		return (this.getX() == casted.getX() &&
				this.getY() == casted.getY() && 
				this.getZ() == casted.getZ());
		
	}
}
