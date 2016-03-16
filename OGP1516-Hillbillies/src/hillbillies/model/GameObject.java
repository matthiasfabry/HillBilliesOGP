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
	public GameObject(Coordinate coordinate, World world) throws ModelException {
		this.world = world;
		Random decider = new Random();
		int weight = decider.nextInt(41)+10;
		this.weight = weight;
		this.setPosition(coordinate);
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
	 *       | result == (world != null)
	*/
	public static boolean isValidWorld(World world) {
		return (world != null);
	}

	/**
	 * Variable registering the World of this GameObject.
	 */
	private final World world;
	
	// Weight //
	
	/**
	 * Return the weight of this GameObject.
	 */
	@Basic @Raw @Immutable
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
	public boolean canHaveAsWeight(int weight) {
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
	@Basic @Raw
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
	public static boolean isValidPosition(Coordinate position) {
		return false;
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
	public void setPosition(Coordinate position) 
			throws ModelException {
		if (! isValidPosition(position))
			throw new ModelException();
		this.position = position;
	}
	
	/**
	 * Variable registering the Position of this GameObject.
	 */
	private Coordinate position;

}
