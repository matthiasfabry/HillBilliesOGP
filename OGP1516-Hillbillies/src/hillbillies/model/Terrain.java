/**
 * 
 */
package hillbillies.model;

/**
 * An enumeration of all possible terrain types of a world
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public enum Terrain {
	TREE, ROCK, AIR, WORKSHOP;
	/**
	 * @return (this == AIR || this == WORKSHOP)
	 */
	public boolean isPassable(){
		return (this == AIR || this == WORKSHOP);
	}
	/**
	 * @return (this == TREE || this == ROCK)
	 */
	public boolean isImpassable(){
		return (this == TREE || this == ROCK);
	}
}
