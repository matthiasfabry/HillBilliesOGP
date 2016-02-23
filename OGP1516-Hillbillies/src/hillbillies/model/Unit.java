/**
 * 
 */
package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 *
 *	A class describing the Hillbillie Unit
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */

// Invariants //

/**
 * @invar  The Weight of each Unit must be a valid Weight for any
 *         Unit.
 *       | isValidWeight(getWeight())
 * @invar  The Agility of each Unit must be a valid Agility for any
 *         Unit.
 *       | isValidAgility(getAgility())
 * @invar  The Strength of each Unit must be a valid Strength for any
 *         Unit.
 *       | isValidStrength(getStrength())
 * @invar  The Toughness of each Unit must be a valid Toughness for any
 *         Unit.
 *       | isValidToughness(getToughness())
 * @invar  The Hitpoints of each Unit must be a valid Hitpoints for any
 *         Unit.
 *       | isValidHitpoints(getHitpoints())
 * @invar  The Stamina of each Unit must be a valid Stamina for any
 *         Unit.
 *       | isValidStamina(getStamina())
 * @invar  The Name of each Unit must be a valid Name for any
 *         Unit.
 *       | isValidName(getName())   
 */

public class Unit {
	/**
	 * 
	 * @param name
	 * 			The Name of the Unit
	 * @param initialPosition
	 * 			the initial position of the Unit
	 * @param weight
	 * 			the Weight of the Unit
	 * @param agility
	 * 			the Agility of the Unit
	 * @param strength
	 * 			the Strength of the Unit
	 * @param toughness
	 * 			the Toughness of the Unit
	 * @param enableDefaultBehavior
	 *			Flag to signal whether the Unit can perform default behavior.
	 * @throws ModelException
	 */
	public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		if (isValidInitialAttribute(agility))
			this.agility = agility;
		else
			this.agility = nearestValidInitialAttribute(agility);
		if (isValidInitialAttribute(strength))
			this.strength = strength;
		else
			this.strength = nearestValidInitialAttribute(strength);
		if (isValidInitialAttribute(toughness))
			this.toughness = toughness;
		else
			this.toughness = nearestValidInitialAttribute(toughness);
		if (isValidInitialAttribute(weight)&& this.isValidWeight(weight))
			this.weight = weight;
		else
			this.weight = nearestValidInitialAttribute(weight);
			
		
	}

	
// Primary Attributes (Total) //
	private static final int MAX_ATTRIBUTE = 200;
	private static final int MIN_ATTRIBUTE = 1;
	
	public static boolean isValidInitialAttribute(int attribute){
		return (attribute >= 25 && attribute <= 100);
	}
	public static int nearestValidInitialAttribute(int attribute){
		if (attribute < 25)
			return 25;
		else
			return 100;
	}
	
//	/**
//	 * Initialize this new Unit with given Weight.
//	 * 
//	 * @param  weight
//	 *         The Weight for this new Unit.
//	 * @post   If the given Weight is a valid Weight for any Unit,
//	 *         the Weight of this new Unit is equal to the given
//	 *         Weight. Otherwise, the Weight of this new Unit is equal
//	 *         to nearestValidWeight(weight).
//	 *       | if (isValidWeight(weight))
//	 *       |   then new.getWeight() == weight
//	 *       |   else new.getWeight() == nearestValidWeight(weight)
//	 */
//	public Unit(int weight) {
//		if (! isValidWeight(weight))
//			weight = nearestValidWeight(weight);
//		setWeight(weight);
//	}
	
	public int nearestValidWeight(int weight){
		if (weight > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return (int) Math.ceil((this.getAgility() + this.getStrength()) / 2.0);
	}
	
	/**
	 * Return the Weight of this Unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Check whether the given Weight is a valid Weight for
	 * any Unit.
	 *  
	 * @param  weight
	 *         The Weight to check.
	 * @return 
	 *       | result == (this.getStrength()+this.getAgility())/2 > weight && weight <= 200)
	*/
	public boolean isValidWeight(int weight) {
		return ((this.getStrength()+this.getAgility())/2 > weight && weight <= MAX_ATTRIBUTE);
	}
	
	/**
	 * Set the Weight of this Unit to the given Weight.
	 * 
	 * @param  weight
	 *         The new Weight for this Unit.
	 * @post   If the given Weight is a valid Weight for any Unit,
	 *         the Weight of this new Unit is equal to the given
	 *         Weight.
	 *       | if (isValidWeight(weight))
	 *       |   then new.getWeight() == weight
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight))
			this.weight = weight;
		else
			this.weight = this.nearestValidWeight(weight);
	}
	
	/**
	 * Variable registering the Weight of this Unit.
	 */
	private int weight;


//	/**
//	 * Initialize this new Unit with given Agility.
//	 * 
//	 * @param  agility
//	 *         The Agility for this new Unit.
//	 * @post   If the given Agility is a valid Agility for any Unit,
//	 *         the Agility of this new Unit is equal to the given
//	 *         Agility. Otherwise, the Agility of this new Unit is equal
//	 *         to nearestValidAgility(agility).
//	 *       | if (isValidAgility(agility))
//	 *       |   then new.getAgility() == agility
//	 *       |   else new.getAgility() == nearestValidAgility(agility)
//	 */
//	public Unit(int agility) {
//		if (! isValidAgility(agility))
//			agility = nearestValidAgility(agility);
//		setAgility(agility);
//	}
	
	/**
	 * Return the nearest valid agility of a Unit.
	 * 
	 * @param 	agility
	 * 		  	The agility of which the nearest should be determined
	 * @return	If the agility is higher than the highest allowed agility, the result 
	 * 			is that highest allowed agility, otherwise, the result is the lowest allowed agility
	 * 			| if agility > 200
	 * 			|	then result == 200
	 * 			|	else result == 1
	 */
	public static int nearestValidAgility(int agility){
		if (agility > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}
	
	/**
	 * Return the Agility of this Unit.
	 */
	@Basic @Raw
	public int getAgility() {
		return this.agility;
	}
	
	/**
	 * Check whether the given Agility is a valid Agility for
	 * any Unit.
	 *  
	 * @param  agility
	 *         The Agility to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidAgility(int agility) {
		return (agility <= MAX_ATTRIBUTE && agility >= MIN_ATTRIBUTE);
	}
	
	/**
	 * Set the Agility of this Unit to the given Agility.
	 * 
	 * @param  agility
	 *         The new Agility for this Unit.
	 * @post   If the given Agility is a valid Agility for any Unit,
	 *         the Agility of this new Unit is equal to the given
	 *         Agility.
	 *       | if (isValidAgility(agility))
	 *       |   then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		else
			this.agility = nearestValidAgility(agility);
	}
	
	/**
	 * Variable registering the Agility of this Unit.
	 */
	private int agility;

//	/**
//	 * Initialize this new Unit with given Strength.
//	 * 
//	 * @param  strength
//	 *         The Strength for this new Unit.
//	 * @post   If the given Strength is a valid Strength for any Unit,
//	 *         the Strength of this new Unit is equal to the given
//	 *         Strength. Otherwise, the Strength of this new Unit is equal
//	 *         to nearestValidStrength(strength).
//	 *       | if (isValidStrength(strength))
//	 *       |   then new.getStrength() == strength
//	 *       |   else new.getStrength() == nearestValidStrength(strength)
//	 */
//	public Unit(int strength) {
//		if (! isValidStrength(strength))
//			strength = nearestValidStrength(strength);
//		setStrength(strength);
//	}
	
	/**
	 * 
	 * @param strength
	 * @return
	 */
	public static int nearestValidStrength(int strength){
		if (strength > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}
	
	/**
	 * Return the Strength of this Unit.
	 */
	@Basic @Raw
	public int getStrength() {
		return this.strength;
	}
	
	/**
	 * Check whether the given Strength is a valid Strength for
	 * any Unit.
	 *  
	 * @param  strength
	 *         The Strength to check.
	 * @return 
	 *       | result == (strength > 0 && strength <= 200)
	*/
	public static boolean isValidStrength(int strength) {
		return strength >= MIN_ATTRIBUTE && strength <= MAX_ATTRIBUTE;
	}
	
	/**
	 * Set the Strength of this Unit to the given Strength.
	 * 
	 * @param  strength
	 *         The new Strength for this Unit.
	 * @post   If the given Strength is a valid Strength for any Unit,
	 *         the Strength of this new Unit is equal to the given
	 *         Strength. Otherwise, the strength will be set to the nearest
	 *         valid Strength.
	 *       | if (isValidStrength(strength))
	 *       |   then new.getStrength() == strength
	 *       |   else new.getStrength() == nearestValidStrength(strength)
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
		else
			this.strength = nearestValidStrength(strength);
	}
	
	/**
	 * Variable registering the Strength of this Unit.
	 */
	private int strength;


//	/**
//	 * Initialize this new Unit with given Toughness.
//	 * 
//	 * @param  toughness
//	 *         The Toughness for this new Unit.
//	 * @post   If the given Toughness is a valid Toughness for any Unit,
//	 *         the Toughness of this new Unit is equal to the given
//	 *         Toughness. Otherwise, the Toughness of this new Unit is equal
//	 *         to nearestValidToughness(toughness).
//	 *       | if (isValidToughness(toughness))
//	 *       |   then new.getToughness() == toughness
//	 *       |   else new.getToughness() == nearestValidToughness(toughness)
//	 */
//	public Unit(int toughness) {
//		if (! isValidToughness(toughness))
//			toughness = nearestValidToughness(toughness);
//		setToughness(toughness);
//	}
	
	
	/**
	 * 
	 * @param toughness
	 * @return
	 */
	public static int nearestValidToughness(int toughness){
		if (toughness > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}
	
	/**
	 * Return the Toughness of this Unit.
	 */
	@Basic @Raw
	public int getToughness() {
		return this.toughness;
	}
	
	/**
	 * Check whether the given Toughness is a valid Toughness for
	 * any Unit.
	 *  
	 * @param  toughness
	 *         The Toughness to check.
	 * @return 
	 *       | result == (toughness > 0 && toughness <= 200) 
	*/
	public static boolean isValidToughness(int toughness) {
		return toughness > 0 && toughness <= 200;
	}
	
	/**
	 * Set the Toughness of this Unit to the given Toughness.
	 * 
	 * @param  toughness
	 *         The new Toughness for this Unit.
	 * @post   If the given Toughness is a valid Toughness for any Unit,
	 *         the Toughness of this new Unit is equal to the given
	 *         Toughness.
	 *       | if (isValidToughness(toughness))
	 *       |   then new.getToughness() == toughness
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
	}
	
	/**
	 * Variable registering the Toughness of this Unit.
	 */
	private int toughness;

// Secondary Attributes (Nominal)//
	
	public int maxSecondaryAttribute(){
		return (int) Math.ceil(this.getWeight()*this.getToughness()/50.0);
	}
	private static final int MIN_SEC_ATTRIBUTE=0;
	
	/**
	 * Check whether the given attribute is a valid Secondary Attribute (Hitpoints or Stamina) for
	 * any Unit.
	 *  
	 * @param  attribute
	 *         The Secondary Attribute to check.
	 * @return 
	 *       | result == (attribute >= 0 && attribute <= getStrength()*getToughness()/50)
	*/
	public boolean isValidSecAttribute(int attribute) {
		return attribute >= MIN_SEC_ATTRIBUTE && attribute <= this.maxSecondaryAttribute();
	}
	
//	/**
//	 * Initialize this new Unit with given Hitpoints.
//	 * 
//	 * @param  hitpoints
//	 *         The Hitpoints for this new Unit.
//	 * @pre    The given Hitpoints must be a valid Hitpoints for any Unit.
//	 *       | isValidHitpoints(Hitpoints)
//	 * @post   The Hitpoints of this new Unit is equal to the given
//	 *         Hitpoints.
//	 *       | new.getHitpoints() == hitpoints
//	 */
//	public Unit(int hitpoints) {
//		this.setHitpoints(hitpoints);
//	}
	
	/**
	 * Return the Hitpoints of this Unit.
	 */
	@Basic @Raw
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	/**
	 * Set the Hitpoints of this Unit to the given Hitpoints.
	 * 
	 * @param  hitpoints
	 *         The new Hitpoints for this Unit.
	 * @pre    The given Hitpoints must be a valid Hitpoints for any
	 *         Unit.
	 *       | isValidHitpoints(hitpoints)
	 * @post   The Hitpoints of this Unit is equal to the given
	 *         Hitpoints.
	 *       | new.getHitpoints() == hitpoints
	 */
	@Raw
	public void setHitpoints(int hitpoints) {
		assert this.isValidSecAttribute(hitpoints);
		this.hitpoints = hitpoints;
	}
	
	/**
	 * Variable registering the Hitpoints of this Unit.
	 */
	private int hitpoints;
	
//	/**
//	 * Initialize this new Unit with given Stamina.
//	 * 
//	 * @param  stamina
//	 *         The Stamina for this new Unit.
//	 * @pre    The given Stamina must be a valid Stamina for any Unit.
//	 *       | isValidStamina(Stamina)
//	 * @post   The Stamina of this new Unit is equal to the given
//	 *         Stamina.
//	 *       | new.getStamina() == stamina
//	 */
//	public Unit(int stamina) {
//		this.setStamina(stamina);
//	}
	
	/**
	 * Return the Stamina of this Unit.
	 */
	@Basic @Raw
	public int getStamina() {
		return this.stamina;
	}
	
	/**
	 * Set the Stamina of this Unit to the given Stamina.
	 * 
	 * @param  stamina
	 *         The new Stamina for this Unit.
	 * @pre    The given Stamina must be a valid Stamina for any
	 *         Unit.
	 *       | isValidStamina(stamina)
	 * @post   The Stamina of this Unit is equal to the given
	 *         Stamina.
	 *       | new.getStamina() == stamina
	 */
	@Raw
	public void setStamina(int stamina) {
		assert this.isValidSecAttribute(stamina);
		this.stamina = stamina;
	}
	
	/**
	 * Variable registering the Stamina of this Unit.
	 */
	private int stamina;
	
// Name (defensive) //
	/** TO BE ADDED TO CLASS HEADING

	 */


/**
 * Initialize this new Unit with given Name.
 *
 * @param  name
 *         The Name for this new Unit.
 * @effect The Name of this new Unit is set to
 *         the given Name.
 *       | this.setName(name)
 */
public Unit(String name)
		throws ModelException {
	this.setName(name);
}


/**
 * Return the Name of this Unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
}

/**
 * Check whether the given Name is a valid Name for
 * any Unit.
 *  
 * @param  Name
 *         The Name to check.
 * @return 
 *       | result == 
*/
public static boolean isValidName(String name) {
	return false;
}

/**
 * Set the Name of this Unit to the given Name.
 * 
 * @param  name
 *         The new Name for this Unit.
 * @post   The Name of this new Unit is equal to
 *         the given Name.
 *       | new.getName() == name
 * @throws ModelException
 *         The given Name is not a valid Name for any
 *         Unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) 
		throws ModelException {
	if (! isValidName(name))
		throw new ModelException();
	this.name = name;
}

/**
 * Variable registering the Name of this Unit.
 */
private String name;
}

