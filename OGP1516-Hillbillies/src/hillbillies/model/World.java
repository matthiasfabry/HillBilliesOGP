/**
 * 
 */
package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

/**
 * A Class describing a Hillbillies game world.
 *
 *	
 * @invar  Each World can have its Dimension as Dimension.
 *       | canHaveAsDimension(this.getDimension())
 * @invar  Each World can have its Dimension as Dimension.
 *       | canHaveAsDimension(this.getDimension())
 * @invar  The set of Logs of each World must be a valid set of Logs for any
 *         World.
 *       | isValidLogSet(getLogSet())
 * @invar  The set of Boulders of each World must be a valid set of Boulders for any
 *         World.
 *       | isValidBoulderSet(getBoulderSet())
 * @invar   Each World must have proper GameObjects.
 *        | hasProperGameObjects()
 *
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */

public class World {

	// Constructor //

	public World(Terrain[][][] features, TerrainChangeListener listener)
			throws ModelException {
		this.map = new Cube[features.length][features[0].length][features[0][0].length];
		this.dimension = new int[]{features.length, features[0].length,
				features[0][0].length};
		for (int indexX = 0; indexX < map.length; indexX++){
			for (int indexY = 0; indexY < map[indexX].length; indexY++){
				for (int indexZ = 0; indexZ < map[indexX][indexY].length; indexZ++){
					this.getMap()[indexX][indexY][indexZ] = new Cube(indexX, indexY, indexZ);
					this.getMap()[indexX][indexY][indexZ].
						setTerrain(features[indexX][indexY][indexZ]);
				}
			}
		}
	}

	// Map //
	
	/**
	 * Return the Dimension of this World.
	 */
	@Basic
	@Raw
	@Immutable
	public int[] getDimension() {
		return this.dimension;
	}

	/**
	 * Check whether this World can have the given Dimension as its Dimension.
	 *  
	 * @param  dimension
	 *         The Dimension to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsDimension(int[] dimension) {
		return false;
	}

	/**
	 * Variable registering the Dimension of this World.
	 */
	private int[] dimension;

	@Basic
	@Raw
	public Cube[][][] getMap() {
		return this.map;
	}

	private final Cube[][][] map;
	
	// Faction //
	
	Faction getFactiontoJoin(){
		return null;
	}
	
	// Units //

	/**
	 * Return the set of Units of this World.
	 */
	@Basic @Raw
	public Set<Unit> getUnitSet() {
		return this.unitSet;
	}
	
	/**
	 * Check whether the given set of Units is a valid set of Units for
	 * any World.
	 *  
	 * @param  set of Units
	 *         The set of Units to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidUnitSet(Set<Unit> unitSet) {
		return false;
	}
	
	/**
	 * Set the set of Units of this World to the given set of Units.
	 * 
	 * @param  unitSet
	 *         The new set of Units for this World.
	 * @post   The set of Units of this new World is equal to
	 *         the given set of Units.
	 *       | new.getUnitSet() == unitSet
	 * @throws ModelException
	 *         The given set of Units is not a valid set of Units for any
	 *         World.
	 *       | ! isValidUnitSet(getUnitSet())
	 */
	@Raw
	public void setUnitSet(Set<Unit> unitSet) 
			throws ModelException {
		if (! isValidUnitSet(unitSet))
			throw new ModelException();
		this.unitSet = unitSet;
	}
	
	/**
	 * Variable registering the set of Units of this World.
	 */
	private Set<Unit> unitSet;
	
	// GameObjects //

	/**
	 * Check whether this World has the given GameObject as one of its
	 * GameObjects.
	 * 
	 * @param  gameObject
	 *         The GameObject to check.
	 */
	@Basic
	@Raw
	public boolean hasAsGameObject(
			@Raw GameObject gameObject) {
		return gameObjects.contains(gameObject);
	}

	/**
	 * Check whether this World can have the given GameObject
	 * as one of its GameObjects.
	 * 
	 * @param  gameObject
	 *         The GameObject to check.
	 * @return True if and only if the given GameObject is effective
	 *         and that GameObject is a valid GameObject for a World.
	 *       | result ==
	 *       |   (gameObject != null) &&
	 *       |   GameObject.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsGameObject(
			GameObject gameObject) {
		return (gameObject != null)
				&& (GameObject.isValidWorld(this));
	}

	/**
	 * Check whether this World has proper GameObjects attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         GameObjects attached to it as one of its GameObjects,
	 *         and if each of these GameObjects references this World as
	 *         the World to which they are attached.
	 *       | for each gameObject in GameObject:
	 *       |   if (hasAsGameObject(gameObject))
	 *       |     then canHaveAsGameObject(gameObject) &&
	 *       |          (gameObject.getWorld() == this)
	 */
	public boolean hasProperGameObjects() {
		for (GameObject gameObject : gameObjects) {
			if (!canHaveAsGameObject(gameObject))
				return false;
			if (gameObject.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of GameObjects associated with this World.
	 *
	 * @return  The total number of GameObjects collected in this World.
	 *        | result ==
	 *        |   card({gameObject:GameObject | hasAsGameObject({gameObject)})
	 */
	public int getNbGameObjects() {
		return gameObjects.size();
	}

	/**
	 * Add the given GameObject to the set of GameObjects of this World.
	 * 
	 * @param  gameObject
	 *         The GameObject to be added.
	 * @pre    The given GameObject is effective and already references
	 *         this World.
	 *       | (gameObject != null) && (gameObject.getWorld() == this)
	 * @post   This World has the given GameObject as one of its GameObjects.
	 *       | new.hasAsGameObject(gameObject)
	 */
	public void addGameObject(
			@Raw GameObject gameObject) {
		assert (gameObject != null)
				&& (gameObject.getWorld() == this);
		gameObjects.add(gameObject);
	}

	/**
	 * Remove the given GameObject from the set of GameObjects of this World.
	 * 
	 * @param  gameObject
	 *         The GameObject to be removed.
	 * @pre    This World has the given GameObject as one of
	 *         its GameObjects, and the given GameObject does not
	 *         reference any World.
	 *       | this.hasAsGameObject(gameObject) &&
	 *       | (gameObject.getWorld() == null)
	 * @post   This World no longer has the given GameObject as
	 *         one of its GameObjects.
	 *       | ! new.hasAsGameObject(gameObject)
	 */
	@Raw
	public void removeGameObject(
			GameObject gameObject) {
		assert this.hasAsGameObject(gameObject)
				&& (gameObject.getWorld() == null);
		gameObjects.remove(gameObject);
	}

	/**
	 * Variable referencing a set collecting all the GameObjects
	 * of this World.
	 * 
	 * @invar  The referenced set is effective.
	 *       | gameObjects != null
	 * @invar  Each GameObject registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each gameObject in gameObjects:
	 *       |   ( (gameObject != null) &&
	 *       |     (! gameObject.isTerminated()) )
	 */
	private final Set<GameObject> gameObjects = new HashSet<GameObject>();
	
	// Logs //

	/**
	 * Return the set of Logs of this World.
	 */
	@Basic
	@Raw
	public Set<Log> getLogSet() {
		Set<Log> logSet = new HashSet<>();
		for (GameObject gameObject : this.gameObjects)
			if (gameObject instanceof Log)
				logSet.add((Log) gameObject);
		return logSet;
	}
	
	
	// Boulders //

	/**
	 * Return the set of Boulders of this World.
	 */

	@Basic
	@Raw
	public Set<Boulder> getBoulderSet() {
		Set<Boulder> boulderSet = new HashSet<>();
		for (GameObject gameObject : this.gameObjects)
			if (gameObject instanceof Log)
				boulderSet.add((Boulder) gameObject);
		return boulderSet;
	}
	
	}
