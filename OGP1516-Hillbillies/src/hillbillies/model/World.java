/**
 * 
 */
package hillbillies.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;
import ogp.framework.util.ModelException;

/**
 * A Class describing a Hillbillies game world.
 *
 * @invar  Each World can have its Dimension as Dimension.
 *        | canHaveAsDimension(this.getDimension())
 * @invar  The set of Logs of each World must be a valid set of Logs for any World.
 *        | isValidLogSet(getLogSet())
 * @invar  The set of Boulders of each World must be a valid set of Boulders for any World.
 *        | isValidBoulderSet(getBoulderSet())
 * @invar   Each World must have proper GameObjects.
 *        | hasProperGameObjects()
 * @invar   Each World must have proper Factions.
 *        | hasProperFactions()
 * @invar   Each World must have proper Units.
 *        | hasProperUnits()
 *
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */

public class World {

	// Constructor //

	/**
	 * Initialize a game world with given terrain features and a terrain
	 * change listener
	 * 
	 * @param features
	 * 		The terrain features to set
	 * @param listener
	 * 		The terrain change listener to set
	 */
	public World(Terrain[][][] features, TerrainChangeListener listener){
		this.grid = new Grid(features, this);
		this.algorithm = new ConnectedToBorder(this.getGrid().getDimension()[0],
				this.getGrid().getDimension()[1],
				this.getGrid().getDimension()[2]);
		this.listener = listener;
	}

	// Time Control //

	/**
	 * Method that advances gametime.
	 * @post	all units in this World have positive amount of 
	 * 			hitpoints.
	 */
	public void advanceTime(double deltaT) {
		for (Unit unit : this.getUnitSet())
			unit.advanceTime(deltaT);
		for (Unit unit : this.getUnitSet())
			shouldDie(unit);
		for(int i=0; i<this.getDimension()[0]; i++)
			for(int j=0; j<this.getDimension()[1] ; j++)
				for(int k=0; k<this.getDimension()[2]; k++)
					if(getAlgorithm().isSolidConnectedToBorder(i, j, k))
						try {
							this.caveIn(new Coordinate(i,j,k));
						} catch (ModelException e) {
							// shouldn't happen
						}
	}

	// Terrain //
	
	/**
	 * Method that returns the terrain at the given coordinate
	 * @param coordinate
	 * 			the coordinate at which we require the terrain
	 * @return	the terrain at coordinate
	 * 		|	this.getGrid().getTerrainAt(coordinate)
	 */
	public Terrain getTerrainAt(Coordinate coordinate){
		return this.getGrid().getTerrainAt(coordinate);
	}
	/**
	 * Method that sets the terrain at the given coordinate
	 * @param coordinate
	 * 			the coordinate at which define the terrain
	 * @post	the terrain at the coordinate is the given terrain
	 */
	public void setTerrainAt(Coordinate coordinate, Terrain terrain)
			throws ModelException {
		this.getGrid().setTerrainAt(coordinate, terrain);
	}

	/**
	 * Method that makes the cube at the given coordinate cave in
	 * @param coordinate
	 * 			the position of the cave in
	 * @throws ModelException
	 * 			The terrain can't cave in at the given coordinate
	 */
	void caveIn(Coordinate coordinate) throws ModelException {
		Terrain oldTerrain;
		try {
			oldTerrain = this.getGrid().getMapAt(coordinate).getTerrain();
		} catch (IndexOutOfBoundsException e2) {
			throw new ModelException("Outside World");
		}
		this.getGrid().getMapAt(coordinate).setTerrain(Terrain.AIR);
		this.getListener().notify();
		double random = Math.random();
		if (random < 0.25) {
			if (oldTerrain == Terrain.TREE)
				try {

					this.addGameObject(new Log(coordinate, this), coordinate);
				} catch (ModelException e) {
					// shouldn't happen
				}
			else if (oldTerrain == Terrain.ROCK)
				try {
					this.addGameObject(new Boulder(coordinate, this), coordinate);
				} catch (ModelException e1) {
					// shouldn't happen
				}
			else
				throw new ModelException("This terrain can't cave in!");
		}
	}

	// Terrain listener //

	/**
	 * Return the Listener of this World.
	 */
	@Basic
	@Raw
	@Immutable
	public TerrainChangeListener getListener() {
		return this.listener;
	}

	/**
	 * Variable registering the Listener of this World.
	 */
	private final TerrainChangeListener listener;

	// Border connection //

	/**
	 * Method that returns the current state of the connected
	 * to border algorithm
	 * 
	 * @return algorithm
	 */
	public ConnectedToBorder getAlgorithm() {
		return this.algorithm;
	}
	/**
	 * Method that updates the connected to border algorithm for
	 * every cube in the gameworld
	 * 
	 * @effect Calls changeSolidToPassable() on every cube
	 * 		| algorithm.changeSolidToPassable()
	 */
	public void updateAlgorithm() {
		for (int indexX = 0; indexX < this.getGrid()
				.getMap().length; indexX++) 
			for (int indexY = 0; indexY < this.getGrid()
					.getMap()[indexX].length; indexY++) 
				for (int indexZ = 0; indexZ < this.getGrid()
						.getMap()[indexX][indexY].length; indexZ++) 
					if (this.getGrid().getMap()[indexX][indexY][indexZ]
							.getTerrain().isPassable())
						algorithm.changeSolidToPassable(indexX, indexY, indexZ);
	}
	/**
	 * Variable containing the connected to border algorithm state
	 */
	private final ConnectedToBorder algorithm;
	
	// Map //
	
	/**
	 * Returns the cube in the world at the given coordinate
	 * 
	 * @param coordinate
	 * 			the coordinate of the cube required
	 * @return the cube at given coordinate
	 */
	Cube getCubeAt(Coordinate coordinate){
		return this.getGrid().getMapAt(coordinate);
	}
	
	/**
	 * Determines whether the given coordinate is a valid spawn 
	 * position
	 * 
	 * @param coordinate
	 * 			the position that needs to be examined
	 * @return	true when possible to spawn there
	 */
	boolean isValidSpawnPosition(Coordinate coordinate) {
		Coordinate flooredCoordinate = coordinate.floor();
		if (!(flooredCoordinate.getX() >= 0
				&& flooredCoordinate.getX() <= this.getGrid().getDimension()[0]
				&& flooredCoordinate.getY() >= 0
				&& flooredCoordinate.getY() <= this.getGrid().getDimension()[1]
				&& flooredCoordinate.getZ() >= 0 && flooredCoordinate
						.getZ() <= this.getGrid().getDimension()[2]))
			return false;
		if (this.getGrid().getMapAt(flooredCoordinate).getTerrain()
				.isImpassable())
			return false;
		else {
			if (flooredCoordinate.getZ() == 0)
				return true;
			if (this.getGrid()
					.getMapAt(flooredCoordinate
							.difference(new Coordinate(0, 0, 1)))
					.getTerrain().isImpassable())
				return true;
			return false;
		}
	}

	/**
	 * Check whether the given position is a valid position for
	 * any a unit to be on.
	 *  
	 * @return True if the given position is valid for this world
	 */
	boolean isValidPosition(Coordinate coordinate) {
		Coordinate flooredCoordinate = coordinate.floor();
		if (!(flooredCoordinate.getX() >= 0
				&& flooredCoordinate.getX() <= this.getGrid().getDimension()[0]
				&& flooredCoordinate.getY() >= 0
				&& flooredCoordinate.getY() <= this.getGrid().getDimension()[1]
				&& flooredCoordinate.getZ() >= 0 && flooredCoordinate
						.getZ() <= this.getGrid().getDimension()[2]))
			return false;
		if (this.getGrid().getMapAt(flooredCoordinate).getTerrain()
				.isImpassable())
			return false;
		if (flooredCoordinate.getZ() == 0)
			return true;
		else {

			for (Terrain cube : this.getGrid()
					.terrainAtAdjacentCubes(flooredCoordinate)) {
				if (cube.isImpassable())
					return true;
			}
			return false;
		}
	}
	/**
	 * @return the dimension of the World
	 */
	public int[] getDimension(){
		return this.getGrid().getDimension();
	}
	/**
	 * @return the grid of the current world
	 */
	@Basic
	@Raw
	public Grid getGrid() {
		return this.grid;
	}
	/**
	 * Variable that keeps the grid of this World.
	 */
	private final Grid grid;

	// Faction //
	/**
	 * Return the set of Factions of this World.
	 */
	public ArrayList<Faction> getFactionList() {
		return this.factions;
	}
	/**
	 * Method that decides what Faction the Unit will join. If the maximum amount 
	 * of factions is reached, the smallest is chosen. If not, a new Faction is created
	 * @return	the appropriate faction to join
	 */
	Faction getFactiontoJoin() {
		if (this.getFactionList().size() < 5) {
			Faction theNew = new Faction("Dwarfs", this);
			this.addFaction(theNew);
			return theNew;
		} else {
			Faction smallest = this.getFactionList().get(0);
			for (Faction faction : this.getFactionList()) {
				if (faction.getNbUnits() < smallest.getNbUnits())
					smallest = faction;
			}
			return smallest;
		}
	}

	/**
	 * Check whether this World has the given Faction as one of its
	 * Factions.
	 * 
	 * @param  faction
	 *         The Faction to check.
	 */
	@Basic
	@Raw
	boolean hasAsFaction(@Raw Faction faction) {
		return factions.contains(faction);
	}

	/**
	 * Check whether this World can have the given Faction
	 * as one of its Factions.
	 * 
	 * @param  faction
	 *         The Faction to check.
	 * @return True if and only if the given Faction is effective
	 *         and that Faction is a valid Faction for a World.
	 *       | result ==
	 *       |   (faction != null) &&
	 *       |   Faction.isValidWorld(this)
	 */
	@Raw
	boolean canHaveAsFaction(Faction faction) {
		return (faction != null) && (Faction.canHaveAsWorld(this));
	}

	/**
	 * Check whether this World has proper Factions attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         Factions attached to it as one of its Factions,
	 *         and if each of these Factions references this World as
	 *         the World to which they are attached.
	 *       | for each faction in Faction:
	 *       |   if (hasAsFaction(faction))
	 *       |     then canHaveAsFaction(faction) &&
	 *       |          (faction.getWorld() == this)
	 */
	boolean hasProperFactions() {
		for (Faction faction : factions) {
			if (!canHaveAsFaction(faction))
				return false;
			if (faction.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Factions associated with this World.
	 *
	 * @return  The total number of Factions collected in this World.
	 *        | result ==
	 *        |   card({faction:Faction | hasAsFaction({faction)})
	 */
	int getNbFactions() {
		return factions.size();
	}

	/**
	 * Add the given Faction to the set of Factions of this World.
	 * 
	 * @param  faction
	 *         The Faction to be added.
	 * @pre    The given Faction is effective and already references
	 *         this World.
	 *       | (faction != null) && (faction.getWorld() == this)
	 * @post   This World has the given Faction as one of its Factions.
	 *       | new.hasAsFaction(faction)
	 */
	void addFaction(@Raw Faction faction) {
		if (getNbFactions() < 5)
			assert (faction != null) && (faction.getWorld() == this);
		factions.add(faction);
	}

	/**
	 * Remove the given Faction from the set of Factions of this World.
	 * 
	 * @param  faction
	 *         The Faction to be removed.
	 * @pre    This World has the given Faction as one of
	 *         its Factions, and the given Faction does not
	 *         reference any World.
	 *       | this.hasAsFaction(faction) &&
	 *       | (faction.getWorld() == null)
	 * @post   This World no longer has the given Faction as
	 *         one of its Factions.
	 *       | ! new.hasAsFaction(faction)
	 */
	@Raw
	void removeFaction(Faction faction) {
		assert this.hasAsFaction(faction) && (faction.getWorld() == null);
		factions.remove(faction);
	}

	/**
	 * Variable referencing a set collecting all the Factions
	 * of this World.
	 * 
	 * @invar  The referenced set is effective.
	 *       | factions != null
	 * @invar  Each Faction registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each faction in factions:
	 *       |   ( (faction != null) &&
	 *       |     (! faction.isTerminated()) )
	 */
	private final ArrayList<Faction> factions = new ArrayList<Faction>();

	// Units //
	/**
	 * Method that creates a Unit at a random location in this World, 
	 * with or without DefaultBehaviour enabled.
	 * @param enableDefaultBehavior
	 * 			whether or not the unit has DefaultBehaviour enabled 
	 * 			when being spawned
	 * @return	A Unit at a random location
	 * @throws ModelException
	 */
	public Unit spawnUnit(boolean enableDefaultBehavior) throws ModelException {
		Random decider = new Random();
		int strength = decider.nextInt(76) + 25;
		int agility = decider.nextInt(76) + 25;
		int toughness = decider.nextInt(76) + 25;
		int weight = decider.nextInt(101 - ((strength + agility) / 2))
				+ (strength + agility) / 2;
		int[] box = {0, 0, 0};
		Coordinate target = new Coordinate(box[0], box[1], box[2]);
		do {
			box[0] = decider.nextInt(this.getGrid().getDimension()[0]);
			box[1] = decider.nextInt(this.getGrid().getDimension()[1]);
			box[2] = decider.nextInt(this.getGrid().getDimension()[2]);
			target = new Coordinate(box[0], box[1], box[2]);
		} while (!this.isValidSpawnPosition(target));
		if (getNbUnits() < 100) {
			Unit theNewUnit = new Unit("Billie", box, weight, agility, strength,
					toughness, enableDefaultBehavior, this, this.getFactiontoJoin());
			this.addUnit(theNewUnit);
			return theNewUnit;
		}
		else {
			Unit theNewUnit = null;
			return theNewUnit;
		}
	}

	/**
	 * Check whether the given Unit should die.
	 * @param	the Unit that needs to be checked. 
	 */
	void shouldDie(Unit unit) {
		if (unit.getHitpoints() <= 0) {
			unit.die();
			this.getUnitSet().remove(unit);
		}
	}

	/**
	 * Check whether this World has the given Unit as one of its
	 * Units.
	 * 
	 * @param  unit
	 *         The Unit to check.
	 */
	@Basic
	@Raw
	boolean hasAsUnit(@Raw Unit unit) {
		return unitSet.contains(unit);
	}

	/**
	 * Check whether this World can have the given Unit
	 * as one of its Units.
	 * 
	 * @param  unit
	 *         The Unit to check.
	 * @return True if and only if the given Unit is effective
	 *         and that Unit is a valid Unit for a World.
	 *       | result ==
	 *       |   (unit != null) &&
	 *       |   Unit.isValidWorld(this)
	 */
	@Raw
	boolean canHaveAsUnit(Unit unit) {
		return (unit != null) && (unit.canHaveAsWorld(this)
				&& this.getUnitSet().size() < 100);
	}

	/**
	 * Check whether this World has proper Units attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         Units attached to it as one of its Units,
	 *         and if each of these Units references this World as
	 *         the World to which they are attached.
	 *       | for each unit in Unit:
	 *       |   if (hasAsUnit(unit))
	 *       |     then canHaveAsUnit(unit) &&
	 *       |          (unit.getWorld() == this)
	 */
	boolean hasProperUnits() {
		for (Unit unit : unitSet) {
			if (!canHaveAsUnit(unit))
				return false;
			if (unit.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Units associated with this World.
	 *
	 * @return  The total number of Units collected in this World.
	 *        | result ==
	 *        |   card({unit:Unit | hasAsUnit({unit)})
	 */
	int getNbUnits() {
		return unitSet.size();
	}

	/**
	 * Add the given Unit to the set of Units of this World.
	 * 
	 * @param  unit
	 *         The Unit to be added.
	 * @pre    The given Unit is effective and already references
	 *         this World.
	 *       | (unit != null) && (unit.getWorld() == this)
	 * @post   This World has the given Unit as one of its Units.
	 *       | new.hasAsUnit(unit)
	 */
	public void addUnit(@Raw Unit unit) {
		assert (unit != null) && (unit.getWorld() == this);
		unitSet.add(unit);
	}

	/**
	 * Remove the given Unit from the set of Units of this World.
	 * 
	 * @param  unit
	 *         The Unit to be removed.
	 * @pre    This World has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any World.
	 *       | this.hasAsUnit(unit) &&
	 *       | (unit.getWorld() == null)
	 * @post   This World no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(unit)
	 */
	@Raw
	void removeUnit(Unit unit) {
		assert this.hasAsUnit(unit) && (unit.getWorld() == null);
		unitSet.remove(unit);
	}
	/**
	 * Return the set of Units of this World.
	 */
	@Basic
	@Raw
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
	static boolean isValidUnitSet(Set<Unit> unitSet) {
		return false;
	}

	/**
	 * Variable registering the set of Units of this World.
	 */
	private final Set<Unit> unitSet = new HashSet<>();

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
	boolean hasAsGameObject(@Raw GameObject gameObject) {
		return this.getGameObjects().contains(gameObject);
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
	boolean canHaveAsGameObject(GameObject gameObject) {
		return (gameObject != null) && (gameObject.isValidWorld(this));
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
	boolean hasProperGameObjects() {
		for (GameObject gameObject : getGameObjects()) {
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
	int getNbGameObjects() {
		return getGameObjects().size();
	}

	/**
	 * Add the given GameObject to this World.
	 * 
	 * @param  gameObject
	 *         The GameObject to be added.
	 * @pre    The given GameObject is effective and already references
	 *         this World.
	 *       | (gameObject != null) && (gameObject.getWorld() == this)
	 * @post   This World has the given GameObject as one of its GameObjects.
	 *       | new.hasAsGameObject(gameObject)
	 */
	void addGameObject(@Raw GameObject gameObject, Coordinate coordinate) {
		assert (gameObject != null) && (gameObject.getWorld() == this);
		this.getCubeAt(coordinate).addGameObject(gameObject);
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
	void removeGameObject(GameObject gameObject) {
		assert this.hasAsGameObject(gameObject)
				&& (gameObject.getWorld() == null);
		getGameObjects().remove(gameObject);
	}
	/**
	 * Return the set of GameObjects of this World.
	 */
	Set<GameObject> getGameObjects(){
		Set<GameObject> theSet = new HashSet<>();
		for(Cube[][] cubePlane : this.getGrid().getMap())
			for(Cube[] cubeRow : cubePlane)
				for(Cube cube : cubeRow)
					for(GameObject gameObject : cube.getGameObjects())
						theSet.add(gameObject);
		return theSet;
	}

	// Logs //
	
	/**
	 * removes the log at the given coordinate
	 * @param coordinate
	 * 			the coordinate where the log will be removed
	 */
	void removeLogAt(Coordinate coordinate){
		Log theLog = this.getCubeAt(coordinate).removeLog();
		this.removeGameObject(theLog);
	}

	/**
	 * Return the set of Logs of this World.
	 */
	public Set<Log> getLogSet() {
		Set<Log> logSet = new HashSet<>();
		for (GameObject gameObject : this.getGameObjects())
			if (gameObject instanceof Log)
				logSet.add((Log) gameObject);
		return logSet;
	}
	
	// Boulders //
	
	/**
	 * removes the boulder at the given coordinate
	 * @param coordinate
	 * 			the coordinate where the boulder will be removed
	 */
	void removeBoulderAt(Coordinate coordinate){
		Boulder theBoulder = this.getCubeAt(coordinate).removeBoulder();
		this.removeGameObject(theBoulder);
	}

	/**
	 * Return the set of Boulders of this World.
	 */
	public Set<Boulder> getBoulderSet() {
		Set<Boulder> boulderSet = new HashSet<>();
		for (GameObject gameObject : this.getGameObjects())
			if (gameObject instanceof Log)
				boulderSet.add((Boulder) gameObject);
		return boulderSet;
	}

}
