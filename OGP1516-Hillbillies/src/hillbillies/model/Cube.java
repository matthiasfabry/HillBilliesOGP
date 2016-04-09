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
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
class Cube {

	// Constructor //

	/**
	 * Initialize this new Cube with given World.
	 * 
	 * @param coordinate
	 * 			The position of the cube in the game world
	 * @param  world
	 *         The World for this new Cube.
	 * @post   The World of this new Cube is equal to the given
	 *         World.
	 *       | new.getWorld() == world
	 * @throws ModelException
	 *         This new Cube cannot have the given World as its World.
	 *       | ! canHaveAsWorld(this.getWorld())
	 */
	Cube(Coordinate coordinate, World world) {
		this.position = coordinate;
		this.world = world;
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
	/**
	 * removes a Log from the cube.
	 */
	Log removeLog() {
		Log theLog = this.getLogs().poll();
		gameObjects.remove(theLog);
		return theLog;
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
	/**
	 * removes a Boulder from the cube.
	 */
	Boulder removeBoulder() {
		Boulder theBoulder = this.getBoulders().poll();
		gameObjects.remove(theBoulder);
		this.getWorld().removeBoulderAt(getPlaceInGrid());
		return theBoulder;
	}

	/**
	 * adds a given GameObject to the cube.
	 * @param gameObject
	 * 			the object that needs to be added
	 */
	void addGameObject(GameObject gameObject) {
		this.getGameObjects().offer(gameObject);
	}

	/**
	 * Return the gameObjects present on this cube
	 * 
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

	/**
	 * gives the position of the cube.
	 * @return	the position
	 * 		|	this.position
	 */
	Coordinate getPlaceInGrid() {
		return this.position;
	}
	/**
	 * the in world Coordinate value of this cube
	 */
	private final Coordinate position;

	// World //
	
	/**
	 * Return the World of this Cube.
	 */
	@Basic
	@Raw
	@Immutable
	public World getWorld() {
		return this.world;
	}

	/**
	 * Variable registering the World of this Cube.
	 */
	private final World world;

}
