/**
 * 
 */
package hillbillies.model;

import java.util.PriorityQueue;
import java.util.Queue;

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

class Cube {

	// Constructor //

	Cube(Coordinate coordinate) {
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
	 * @return Returns true, since in this iteration, every item of the Enum
	 * 			Terrain is valid.
	 *       | result == true
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

	// GameObjects //

	/**
	 * Return the set of Logs of this Cube.
	 */
	@Raw
	Queue<Log> getLogs() {
		Queue<Log> theQueue = new PriorityQueue<>();
		for (GameObject gameObject : gameObjects)
			if (gameObject instanceof Log)
				theQueue.offer((Log) gameObject);
		return theQueue;
	}

	Log removeLog() {
		return this.getLogs().poll();
	}

	/**
	 * Return the set of Boulders of this Cube.
	 */
	@Raw
	Queue<Boulder> getBoulders() {
		Queue<Boulder> theQueue = new PriorityQueue<>();
		for (GameObject gameObject : gameObjects)
			if (gameObject instanceof Boulder)
				theQueue.offer((Boulder) gameObject);
		return theQueue;
	}

	Boulder removeBoulder() {
		return this.getBoulders().poll();
	}

	void addGameObject(GameObject gameObject){
		this.getGameObjects().offer(gameObject);
	}
	
	/**
	 * @return the gameObjects
	 */
	public Queue<GameObject> getGameObjects() {
		return gameObjects;
	}

	/**
	 * Variable registering the set of Boulders of this Cube.
	 */
	private Queue<GameObject> gameObjects = new PriorityQueue<>();

	// Position //

	Coordinate getPlaceInGrid() {
		return this.position;
	}

	private final Coordinate position;
}
