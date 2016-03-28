/**
 * 
 */
package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class holding all information of a game world cube
 * 
 * @invar  The Terrain of each Cube must be a valid Terrain for any
 *         Cube.
 *       | isValidTerrain(getTerrain())
 * @invar  The set of Logs of each Cube must be a valid set of Logs for any
 *         Cube.
 *       | isValidLogs(getLogs())
 * @invar  The set of Boulders of each Cube must be a valid set of Boulders for any
 *         Cube.
 *       | isValidBoulders(getBoulders())
 * @invar  The Position of each Cube must be a valid Position for any
 *         Cube.
 *       | isValidPosition(getPosition())
 *       
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */

public class Cube {
	
	// Constructor //
	
	/**
	 * Default Constructor
	 */
	public Cube(Coordinate coordinate){
		this.position = coordinate;
	}
	
	// Terrain //
	
	/**
	 * Return the Terrain of this Cube.
	 */
	@Basic
	@Raw
	Terrain getTerrain() {
		return this.terrain;
	}

	/**
	 * Check whether the given Terrain is a valid Terrain for
	 * any Cube.
	 *  
	 * @param  Terrain
	 *         The Terrain to check.
	 * @return 
	 *       | result == 
	*/
	static boolean isValidTerrain(Terrain terrain) {
		return true;
	}

	/**
	 * Set the Terrain of this Cube to the given Terrain.
	 * 
	 * @param  terrain
	 *         The new Terrain for this Cube.
	 * @post   The Terrain of this new Cube is equal to
	 *         the given Terrain.
	 *       | new.getTerrain() == terrain
	 * @throws ModelException
	 *         The given Terrain is not a valid Terrain for any
	 *         Cube.
	 *       | ! isValidTerrain(getTerrain())
	 */
	@Raw
	void setTerrain(Terrain terrain) throws ModelException {
		if (!isValidTerrain(terrain))
			throw new ModelException();
		this.terrain = terrain;
	}

	/**
	 * Variable registering the Terrain of this Cube.
	 */
	private Terrain terrain = Terrain.AIR;
	
	// Logs //

	/**
	 * Return the set of Logs of this Cube.
	 */
	@Basic @Raw
	Set<Log> getLogs() {
		return this.logs;
	}
	
	/**
	 * Check whether the given set of Logs is a valid set of Logs for
	 * any Cube.
	 *  
	 * @param  set of Logs
	 *         The set of Logs to check.
	 * @return 
	 *       | result == 
	*/
	static boolean isValidLogs(Set<Log> logs) {
		return false;
	}
	
	/**
	 * Set the set of Logs of this Cube to the given set of Logs.
	 * 
	 * @param  logs
	 *         The new set of Logs for this Cube.
	 * @post   The set of Logs of this new Cube is equal to
	 *         the given set of Logs.
	 *       | new.getLogs() == logs
	 * @throws ModelException
	 *         The given set of Logs is not a valid set of Logs for any
	 *         Cube.
	 *       | ! isValidLogs(getLogs())
	 */
	@Raw
	void setLogs(Set<Log> logs) 
			throws ModelException {
		if (! isValidLogs(logs))
			throw new ModelException();
		this.logs = logs;
	}
	
	/**
	 * Variable registering the set of Logs of this Cube.
	 */
	private Set<Log> logs = new HashSet<>();
	
	// Boulders //
	
	/**
	 * Return the set of Boulders of this Cube.
	 */
	@Basic @Raw
	Set<Boulder> getBoulders() {
		return this.boulders;
	}
	
	/**
	 * Check whether the given set of Boulders is a valid set of Boulders for
	 * any Cube.
	 *  
	 * @param  set of Boulders
	 *         The set of Boulders to check.
	 * @return 
	 *       | result == 
	*/
	static boolean isValidBoulders(Set<Boulder> boulders) {
		return false;
	}
	
	/**
	 * Set the set of Boulders of this Cube to the given set of Boulders.
	 * 
	 * @param  boulders
	 *         The new set of Boulders for this Cube.
	 * @post   The set of Boulders of this new Cube is equal to
	 *         the given set of Boulders.
	 *       | new.getBoulders() == boulders
	 * @throws ModelException
	 *         The given set of Boulders is not a valid set of Boulders for any
	 *         Cube.
	 *       | ! isValidBoulders(getBoulders())
	 */
	@Raw
	void setBoulders(Set<Boulder> boulders) 
			throws ModelException {
		if (! isValidBoulders(boulders))
			throw new ModelException();
		this.boulders = boulders;
	}
	
	/**
	 * Variable registering the set of Boulders of this Cube.
	 */
	private Set<Boulder> boulders = new HashSet<>();
	
	// Position //
	
	Coordinate getPlaceInGrid(){
		return this.position;
	}
	
	private final Coordinate position;
}
