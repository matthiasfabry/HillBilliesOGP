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
 * @invar  The timeToCollapse of each Cube must be a valid timeToCollapse for any
 *         Cube.
 *       | isValidTimeToCollapse(getTimeToCollapse())
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class Cube {

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
	Cube(Coordinate coordinate, Grid grid) {
		this.position = coordinate;
		this.grid = grid;
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

	/**
	 * Return the timeToCollapse of this Cube.
	 */
	@Basic
	@Raw
	double getTimeToCollapse() {
		return this.timeToCollapse;
	}

	/**
	 * Check whether the given timeToCollapse is a valid timeToCollapse for
	 * any Cube.
	 *  
	 * @param  propertyname_Java
	 *         The timeToCollapse to check.
	 * @return 
	 *       | result == timeToCollapse >= 0 && timeToCollapse <= 5
	*/
	static boolean isValidTimeToCollapse(double timeToCollapse) {
		return (timeToCollapse >= 0 && timeToCollapse <= 5);
	}

	/**
	 * Set the timeToCollapse of this Cube to the given timeToCollapse.
	 * 
	 * @param  timeToCollapse
	 *         The new timeToCollapse for this Cube.
	 * @throws ModelException 
				The given time is not a valid time
	 * @post   The timeToCollapse of this Cube is equal to the given
	 *         timeToCollapse.
	 *       | new.getTimeToCollapse() == timeToCollapse
	 */
	@Raw
	void setTimeToCollapse(double timeToCollapse) throws ModelException {
		if(isValidTimeToCollapse(timeToCollapse))
			this.timeToCollapse = timeToCollapse;
		else
			throw new ModelException();
	}

	/**
	 * Variable registering the timeToCollapse of this Cube.
	 */
	private double timeToCollapse;
	
	boolean isCavingIn = false;
	
	/**
	 * Method that makes the cube at the given coordinate collapse
	 * 
	 * @param coordinate
	 * 			the position of the cave in
	 * @throws ModelException
	 * 			The terrain can't cave in at the given coordinate
	 */
	void collapse() throws ModelException {
		Terrain oldTerrain;
		try {
			oldTerrain = this.getTerrain();
		} catch (IndexOutOfBoundsException e2) {
			throw new ModelException("Outside World");
		}
		if (oldTerrain != Terrain.ROCK || oldTerrain != Terrain.TREE)
			throw new ModelException("This terrain can't cave in!");
		this.setTerrain(Terrain.AIR);
		this.getGrid().getWorld().getListener().notify();
		double random = Math.random();
		if (random < 0.25) {
			if (oldTerrain == Terrain.TREE)
				try {
					this.addGameObject(new Log(this.getPlaceInGrid(), this.getGrid().getWorld()));
				} catch (ModelException e) {
					// shouldn't happen
				}
			else if (oldTerrain == Terrain.ROCK)
				try {
					this.addGameObject(new Boulder(this.getPlaceInGrid(), this.getGrid().getWorld()));
				} catch (ModelException e1) {
					// shouldn't happen
				}
		}
		isCavingIn = false;
	}

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
	 * removes the Logs from the cube.
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
	 * removes the Boulders from the cube.
	 */
	Boulder removeBoulder() {
		Boulder theBoulder = this.getBoulders().poll();
		gameObjects.remove(theBoulder);
		this.getGrid().getWorld().removeBoulderAt(getPlaceInGrid());
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
	Queue<GameObject> getGameObjects() {
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
	public Coordinate getPlaceInGrid() {
		return this.position;
	}
	/**
	 * the in world Coordinate value of this cube
	 */
	private final Coordinate position;

	// World //

	/**
	 * Return the Grid of this Cube.
	 */
	@Basic
	@Raw
	@Immutable
	Grid getGrid() {
		return this.grid;
	}

	/**
	 * Variable registering the grid of this Cube.
	 */
	private final Grid grid;

}
