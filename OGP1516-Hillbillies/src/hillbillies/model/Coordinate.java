package hillbillies.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * Class that handles coordinates in the gameworld
 * 
 * @author Matthias Fabry & Lukas Van Riel
 *
 */
@Value
public class Coordinate {
	public Coordinate(double x, double y, double z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	public Coordinate sum(Coordinate other){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()+other.getX());
		result.setY(this.getY()+other.getY());
		result.setZ(this.getZ()+other.getZ());
		return result;
	}
	
	public double length(){
		return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY() + this.getZ()*this.getZ());
	}
	
	public Coordinate normalize(){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()/this.length());
		result.setY(this.getY()/this.length());
		result.setZ(this.getZ()/this.length());
		return result; 
	}
	
	public Coordinate difference(Coordinate other){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()-other.getX());
		result.setY(this.getY()-other.getY());
		result.setZ(this.getZ()-other.getZ());
		return result;
	}
	public Coordinate directionVector(Coordinate goal){
		Coordinate direction = goal.difference(this);
		direction.normalize();
		return direction;
		
	}
	
	public Coordinate scalarMult(double p){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(this.getX()*p);
		result.setY(this.getY()*p);
		result.setZ(this.getZ()*p);
		return result;
	}
	
	public Coordinate floor(){
		Coordinate result = new Coordinate(0,0,0);
		result.setX(Math.floor(this.getX()));
		result.setY(Math.floor(this.getY()));
		result.setZ(Math.floor(this.getZ()));
		return result;
	}
	/**
	 * Return the x-position of this Unit.
	 */
	public double getX(){
		return this.x;
	}
	public void setX(double x){
		this.x = x*CUBE_LENGTH;
	}
	/**
	 * Return the y-position of this Unit.
	 */
	public double getY(){
		return this.y;
	}
	public void setY(double y){
		this.y = y*CUBE_LENGTH;
	}
	/**
	 * Return the z-position of this Unit.
	 */
	public double getZ(){
		return this.z;
	}
	public void setZ(double z){
		this.z = z*CUBE_LENGTH;
	}
	
	private double x,y,z;
	static final double CUBE_LENGTH = 1.0;
}
