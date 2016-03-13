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
	TREE, ROCK, AIR, WORKSHOP;
	
	public boolean isPassable(){
		return (this == AIR || this == WORKSHOP);
	}
	
	public boolean isImpassable(){
		return (this == TREE || this == ROCK);
	}
}
