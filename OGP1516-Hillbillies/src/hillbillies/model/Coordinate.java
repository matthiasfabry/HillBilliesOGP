package hillbillies.model;


/**
 * Class that handles coordinates in the gameworld
 * @author admin
 *
 */
public class Coordinate {
	public Coordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Coordinate sum(Coordinate other){
		Coordinate result = new Coordinate(0,0,0);
		result.x = this.x + other.x;
		result.y = this.y + other.y;
		result.z = this.z + other.z;
		return result;
	}
	
	public double length(){
		return Math.sqrt(this.x*this.x + this.y * this.y + this.z* this.z);
	}
	
	public Coordinate normalize(){
		Coordinate result = new Coordinate(0,0,0);
		result.x = this.x / this.length();
		result.y = this.y / this.length();
		result.z = this.z / this.length();
		return result; 
	}
	
	public Coordinate difference(Coordinate other){
		Coordinate result = new Coordinate(0,0,0);
		result.x = this.x - other.x;
		result.y = this.y - other.y;
		result.z = this.z - other.z;
		return result;
	}
	/**
	 * Return the x-position of this Unit.
	 */
	public double getX(){
		return this.x;
	}
	public void setX(double x){
		this.x = x;
	}
	/**
	 * Return the y-position of this Unit.
	 */
	public double getY(){
		return this.y;
	}
	public void setY(double y){
		this.y = y;
	}
	/**
	 * Return the z-position of this Unit.
	 */
	public double getZ(){
		return this.z;
	}
	public void setZ(double z){
		this.z = z;
	}
	
	private double x,y,z;
	private static final double MAX_POSITION = 50.0;
	private static final double MIN_POSITION = 0.0;
	
	/**
	 * Check whether the given position component is a valid position component for
	 * any Unit.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return True if the given position component is valid for this Unit
	 *       | if (position >= MIN_POSITION && result <= MAX_POSITION)
	 *       | 		return True
	 *       | else
	 *       | 		return False
	*/
	public boolean isValidCoordinate() {
		return (this.getX() >= MIN_POSITION && this.getX() <= MAX_POSITION &&
				this.getY() >= MIN_POSITION && this.getY() <= MAX_POSITION &&
				this.getZ() >= MIN_POSITION && this.getZ() <= MAX_POSITION);
	}
}
