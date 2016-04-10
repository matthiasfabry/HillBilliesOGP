/**
 * 
 */
package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class describing gameobjects in a hillbillie world
 *
 * @invar  The World of each GameObject must be a valid World for any
 *         GameObject.
 *       | isValidWorld(getWorld())
 * @invar  Each GameObject can have its weight as weight. 
 *       | canHaveAsWeight(this.getWeight())
 * @invar  The Position of each GameObject must be a valid Position for any
 *         GameObject.
 *       | isValidPosition(getPosition())
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */

public abstract class GameObject {

	/**
	 * Initialize this new GameObject with given World.
	 *
	 * @param  world
	 *         The World for this new GameObject.
	 * @effect The World of this new GameObject is set to
	 *         the given World.
	 *       | this.setWorld(world)
	 */
	public GameObject(Coordinate coordinate, World world)
			throws ModelException {
		this.setWorld(world);
		Random decider = new Random();
		int weight = decider.nextInt(41) + 10;
		this.weight = weight;
		this.setPosition(coordinate);
		this.getWorld().addGameObject(this, coordinate);
	}

	// Time Control //

	void advanceTime(double deltaT) {
		if (isFalling)
			try {
				this.setPosition(getPosition().difference(
						new Coordinate(0, 0, 1).scalarMult(deltaT)));
			} catch (ModelException e) {
				try {
					this.setPosition(new Coordinate(getPosition().getX(),
							getPosition().getY(), getPosition().getZ() - 1));
					this.isFalling = false;
				} catch (ModelException e1) {
					// shoudn't happen
				}
			}

	}

	// World //

	/**
	 * Sets the World of this gameObject to the given world
	 * 
	 * @param world
	 * 		The World to set
	 */
	void setWorld(World world) {
		if (isValidWorld(world))
			this.world = world;
	}

	/**
	 * Return the World of this GameObject.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return this.world;
	}

	/**
	 * Check whether the given World is a valid World for
	 * any GameObject.
	 *  
	 * @param  world
	 *         The World to check.
	 * @return 
	 *       | if (isBeingCarried)
	 *		 |    return world == null;
	 *	     | else
	 *		 |    return world != null;
	 */
	boolean isValidWorld(World world) {
		if (isBeingCarried)
			return world == null;
		else
			return world != null;
	}

	/**
	 * Variable registering the World of this GameObject.
	 */
	private World world;

	// Weight //

	/**
	 * Return the weight of this GameObject.
	 */
	@Basic
	@Raw
	@Immutable
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether this GameObject can have the given weight as its weight.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return 
	 *       | result == weight <= 50 && weight >= 10
	*/
	@Raw
	boolean canHaveAsWeight(int weight) {
		return (weight <= 50 && weight >= 10);
	}

	/**
	 * Variable registering the weight of this GameObject.
	 */
	private final int weight;

	// Position //

	/**
	 * Return the Position of this GameObject.
	 */
	@Basic
	@Raw
	public Coordinate getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given Position is a valid Position for
	 * any GameObject.
	 *  
	 * @param  Position
	 *         The Position to check.
	 * @return 
	 *       | result == 
	*/
	boolean isValidPosition(Coordinate position) {
		if (!isFalling) {
			return this.getWorld().isValidSpawnPosition(position);
		} else {
			Coordinate flooredCoordinate = position.floor();
			if (flooredCoordinate.getX() >= 0
					&& flooredCoordinate.getX() <= this.getWorld().getGrid()
							.getDimension()[0]
					&& flooredCoordinate.getY() >= 0
					&& flooredCoordinate.getY() <= this.getWorld().getGrid()
							.getDimension()[1]
					&& flooredCoordinate.getZ() >= 0
					&& flooredCoordinate.getZ() <= this.getWorld().getGrid()
							.getDimension()[2])
				return true;
			else
				return false;
		}
	}

	/**
	 * Set the Position of this GameObject to the given Position.
	 * 
	 * @param  position
	 *         The new Position for this GameObject.
	 * @post   The Position of this new GameObject is equal to
	 *         the given Position.
	 *       | new.getPosition() == position
	 * @throws ModelException
	 *         The given Position is not a valid Position for any
	 *         GameObject.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	void setPosition(Coordinate position) throws ModelException {
		if (!isValidPosition(position))
			throw new ModelException();
		this.position = position;
	}

	/**
	 * Variable registering the Position of this GameObject.
	 */
	private Coordinate position;
	/**
	 * Method that simulates the picking up of a gameObject
	 * 
	 * @post The gameObject shall be in no world
	 * 		| This.getWorld() == null
	 */
	void isPickedUp() {
		this.isBeingCarried = true;
		this.setWorld(null);
	}
	/**
	 * Method that simulates the dropping of a gameObject onto a cube of the gameWorld
	 * 
	 * @param coordinate
	 * 		The place to drop the gameObject
	 * @param world
	 * 		The world to drop the gameObject into
	 * @throws ModelException
	 * 		When the given coordinate is outside of the given world
	 * @post The gameObject shall be member of the given world
	 * 		| this.getWorld() == world
	 */
	void isDropped(Coordinate coordinate, World world) throws ModelException {
		this.setWorld(world);
		try {
			this.setPosition(coordinate);
			this.isBeingCarried = false;
		} catch (ModelException e) {
			throw new ModelException("Can't drop object here!");
		}
	}
	/**
	 * Flag registering whether the gameObject is being carried by a unit or not
	 */
	private boolean isBeingCarried = false;
	
	void shouldFall(){
		if (this.getWorld().getTerrainAt(this.getPosition().difference(new Coordinate(0,0,1))).isPassable())
			this.isFalling = true;
	}
	/**
	 * flag registering whether the gameObject is falling or not
	 */
	private boolean isFalling = false;

}
