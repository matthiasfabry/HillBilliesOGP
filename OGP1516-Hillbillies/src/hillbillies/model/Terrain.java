/**
 * 
 */
package hillbillies.model;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public enum Terrain {
	WOOD, ROCK, AIR, WORKSHOP;
	
	boolean isPassable(){
		return (this == AIR || this == WORKSHOP);
	}
	
	boolean isImpassable(){
		return (this == WOOD || this == ROCK);
	}
}