/**
 * 
 */
package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */

// Invariants //

/**
 * @invar  The weight of each Unit must be a valid weight for any
 *         Unit.
 *       | isValidWeight(getWeight())
 * @invar  The agility of each Unit must be a valid agility for any
 *         Unit.
 *       | isValidAgility(getAgility())
 */

public class Unit {
	public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		
	}


	/**
	 * Initialize this new Unit with given weight.
	 * 
	 * @param  weight
	 *         The weight for this new Unit.
	 * @post   If the given weight is a valid weight for any Unit,
	 *         the weight of this new Unit is equal to the given
	 *         weight. Otherwise, the weight of this new Unit is equal
	 *         to nearestValidWeight(weight).
	 *       | if (isValidWeight(weight))
	 *       |   then new.getWeight() == weight
	 *       |   else new.getWeight() == nearestValidWeight(weight)
	 */
	public Unit(int weight) {
		if (! isValidWeight(weight))
			weight = nearestValidWeight(weight);
		setWeight(weight);
	}
	
	public int nearestValidWeight(int weight){
		return -1;
	}
	
	/**
	 * Return the weight of this Unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Check whether the given weight is a valid weight for
	 * any Unit.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidWeight(int weight) {
		return false;
	}
	
	/**
	 * Set the weight of this Unit to the given weight.
	 * 
	 * @param  weight
	 *         The new weight for this Unit.
	 * @post   If the given weight is a valid weight for any Unit,
	 *         the weight of this new Unit is equal to the given
	 *         weight.
	 *       | if (isValidWeight(weight))
	 *       |   then new.getWeight() == weight
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight))
			this.weight = weight;
	}
	
	/**
	 * Variable registering the weight of this Unit.
	 */
	private int weight;


	/**
	 * Initialize this new Unit with given agility.
	 * 
	 * @param  agility
	 *         The agility for this new Unit.
	 * @post   If the given agility is a valid agility for any Unit,
	 *         the agility of this new Unit is equal to the given
	 *         agility. Otherwise, the agility of this new Unit is equal
	 *         to nearestValidAgility(agility).
	 *       | if (isValidAgility(agility))
	 *       |   then new.getAgility() == agility
	 *       |   else new.getAgility() == nearestValidAgility(agility)
	 */
	public Unit(int agility) {
		if (! isValidAgility(agility))
			agility = nearestValidAgility(agility);
		setAgility(agility);
	}
	
	public int nearestValidAgility(int agility){
		return -1;
	}
	/**
	 * Return the agility of this Unit.
	 */
	@Basic @Raw
	public int getAgility() {
		return this.agility;
	}
	
	/**
	 * Check whether the given agility is a valid agility for
	 * any Unit.
	 *  
	 * @param  agility
	 *         The agility to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidAgility(int agility) {
		return false;
	}
	
	/**
	 * Set the agility of this Unit to the given agility.
	 * 
	 * @param  agility
	 *         The new agility for this Unit.
	 * @post   If the given agility is a valid agility for any Unit,
	 *         the agility of this new Unit is equal to the given
	 *         agility.
	 *       | if (isValidAgility(agility))
	 *       |   then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
	}
	
	/**
	 * Variable registering the agility of this Unit.
	 */
	private int agility;

}

