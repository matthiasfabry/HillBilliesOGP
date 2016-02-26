
package hillbillies.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 *	A class describing the Hillbillie Unit
 *
 * @invar The Weight of each Unit must be a valid Weight for any Unit. |
 *        isValidWeight(getWeight())
 * @invar The Agility of each Unit must be a valid Agility for any Unit. |
 *        isValidAgility(getAgility())
 * @invar The Strength of each Unit must be a valid Strength for any Unit. |
 *        isValidStrength(getStrength())
 * @invar The Toughness of each Unit must be a valid Toughness for any Unit. |
 *        isValidToughness(getToughness())
 * @invar The Hitpoints of each Unit must be a valid Hitpoints for any Unit. |
 *        isValidHitpoints(getHitpoints())
 * @invar The Stamina of each Unit must be a valid Stamina for any Unit. |
 *        isValidStamina(getStamina())
 * @invar The Name of each Unit must be a valid Name for any Unit. |
 *        isValidName(getName())
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */

public class Unit {
	/**
	 * Initialize a Unit with given name, initial position, weight, agility, toughness, strength
	 * 		and default behavior
	 * 
	 * @param name
	 *            The Name of the Unit
	 * @param initialPosition
	 *            the initial position of the Unit
	 * @param weight
	 *            the Weight of the Unit
	 * @param agility
	 *            the Agility of the Unit
	 * @param strength
	 *            the Strength of the Unit
	 * @param toughness
	 *            the Toughness of the Unit
	 * @param enableDefaultBehavior
	 *            Flag to signal whether the Unit performs default behavior.
	 * @throws ModelException
	 */
	public Unit(String name, int[] initialPosition, int weight, int agility,
			int strength, int toughness, boolean enableDefaultBehavior)
					throws ModelException {
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
		if (isValidInitialAttribute(weight) && this.isValidWeight(weight))
			this.weight = weight;
		else
			this.weight = nearestValidInitialAttribute(weight);
		if (isValidName(name))
			this.name = name;
		else
			throw new ModelException();

	}

	// Primary Attributes (Total) //
	/**
	 * Checks whether the given attribute is a valid initial value for that attribute
	 * 
	 * @param attribute
	 * 			the attribute to check.
	 * @return
	 */
	public static boolean isValidInitialAttribute(int attribute) {
		return (attribute >= 25 && attribute <= 100);
	}

	public static int nearestValidInitialAttribute(int attribute) {
		if (attribute < 25)
			return 25;
		else
			return 100;
	}

	/**
	 * Return the nearest valid Weight of a Unit.
	 * 
	 * @param weight
	 *            The Weight of which the nearest should be determined
	 * @return If weight is higher than the highest allowed Weight, the
	 *         result is that highest allowed Weight, otherwise, the result is
	 *         the lowest allowed Weight 
	 *         | if (weight > MAX_ATTRIBUTE)
	 *         | 	then result == MAX_ATTRIBUTE 
	 *         | else 
	 *         |	result == lowestValidWeight(weight)
	 */
	public int nearestValidWeight(int weight) {
		if (weight > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return lowestValidWeigth(weight);
	}
	public int lowestValidWeigth(int weight) {
		return (int) Math.ceil((this.getAgility() + this.getStrength()) / 2.0);
	}

	/**
	 * Return the Weight of this Unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether the given Weight is a valid Weight for any Unit.
	 * 
	 * @param weight
	 *            The Weight to check.
	 * @return | result == (this.getStrength()+this.getAgility())/2 > weight &&
	 *         weight <= 200)
	 */
	public boolean isValidWeight(int weight) {
		return ((this.getStrength() + this.getAgility()) / 2 > weight
				&& weight <= MAX_ATTRIBUTE);
	}

	/**
	 * Set the Weight of this Unit to the given Weight.
	 * 
	 * @param weight
	 *            The new Weight for this Unit.
	 * @post If the given Weight is a valid Weight for any Unit, the Weight of
	 *       this new Unit is equal to the given Weight. | if
	 *       (isValidWeight(weight)) | then new.getWeight() == weight
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

	/**
	 * Return the nearest valid agility of a Unit.
	 * 
	 * @param agility
	 *            The Agility of which the nearest should be determined
	 * @return If agility is higher than the highest allowed Agility, the
	 *         result is that highest allowed Agility, otherwise, the result is
	 *         the lowest allowed Agility 
	 *         | if (agility > MAX_ATTRIBUTE)
	 *         | 	then result == MAX_ATTRIBUTE 
	 *         | else 
	 *         |	result == MIN_ATTRIBUTE
	 */
	public static int nearestValidAgility(int agility) {
		if (agility > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}

	/**
	 * Return the Agility of this Unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given Agility is a valid Agility for any Unit.
	 * 
	 * @param agility
	 *            The Agility to check.
	 * @return Returns true if and only if the agility is bigger than or equal to MIN_ATTRIBUTE
	 * 				and smaller than or equal to MAX_ATTRIBUTE
	 * 			| result == agility <= MAX_ATTRIBUTE && agility >= MIN_ATTRIBUTE
	 */
	public static boolean isValidAgility(int agility) {
		return (agility <= MAX_ATTRIBUTE && agility >= MIN_ATTRIBUTE);
	}

	/**
	 * Set the Agility of this Unit to the given Agility.
	 * 
	 * @param agility
	 *            The new Agility for this Unit.
	 * @post If the given Agility is a valid Agility for any Unit, the Agility
	 *       of this new Unit is equal to the given Agility. 
	 *       | if (isValidAgility(agility)) 
	 *       | 		then new.getAgility() == agility
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

	/**
	  * Return the nearest valid strength of a Unit.
	 * 
	 * @param strength
	 *            The Strength of which the nearest should be determined
	 * @return If strength is higher than the highest allowed Strength, the
	 *         result is that highest allowed Strength, otherwise, the result is
	 *         the lowest allowed Strength 
	 *         | if (strength > MAX_ATTRIBUTE)
	 *         | 	then result == MAX_ATTRIBUTE 
	 *         | else 
	 *         |	result == MIN_ATTRIBUTE
	 */
	public static int nearestValidStrength(int strength) {
		if (strength > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}

	/**
	 * Return the Strength of this Unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Check whether the given Strength is a valid Strength for any Unit.
	 * 
	 * @param strength
	 *            The Strength to check.
	 * @return Returns true if and only if the strength is bigger than or equal to MIN_ATTRIBUTE
	 * 				and smaller than or equal to MAX_ATTRIBUTE
	 * 			| result == (strength > MIN_ATTRIBUTE && strength <= MAX_ATTRIBUTE)
	 */
	public static boolean isValidStrength(int strength) {
		return strength >= MIN_ATTRIBUTE && strength <= MAX_ATTRIBUTE;
	}

	/**
	 * Set the Strength of this Unit to the given Strength.
	 * 
	 * @param strength
	 *            The new Strength for this Unit.
	 * @post If the given Strength is a valid Strength for any Unit, the
	 *       Strength of this new Unit is equal to the given Strength.
	 *       Otherwise, the strength will be set to the nearest valid Strength.
	 *       | if (isValidStrength(strength)) 
	 *       | 		then new.getStrength() == strength 
	 *       | else 
	 *       |		new.getStrength() == nearestValidStrength(strength)
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

	/**
	 * Return the nearest valid toughness of a Unit.
	 * 
	 * @param toughness
	 *            The Toughness of which the nearest should be determined
	 * @return If toughness is higher than the highest allowed Toughness, the
	 *         result is that highest allowed Toughness, otherwise, the result is
	 *         the lowest allowed Toughness 
	 *         | if (toughness > MAX_ATTRIBUTE)
	 *         | 	then result == MAX_ATTRIBUTE 
	 *         | else 
	 *         |	result == MIN_ATTRIBUTE
	 */
	public static int nearestValidToughness(int toughness) {
		if (toughness > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}

	/**
	 * Return the Toughness of this Unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given Toughness is a valid Toughness for any Unit.
	 * 
	 * @param toughness
	 *            The Toughness to check.
	 * @return | result == (toughness > 0 && toughness <= 200)
	 */
	public static boolean isValidToughness(int toughness) {
		return toughness > 0 && toughness <= 200;
	}

	/**
	 * Set the Toughness of this Unit to the given Toughness.
	 * 
	 * @param toughness
	 *            The new Toughness for this Unit.
	 * @post If the given Toughness is a valid Toughness for any Unit, the
	 *       Toughness of this new Unit is equal to the given Toughness. 
	 *       | if (isValidToughness(toughness)) 
	 *       | 	then new.getToughness() == toughness
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

	/**
	 * Variable registering the maximum value of primary attributes
	 */
	private static final int MAX_ATTRIBUTE = 200;

	/**
	 * Variable registering the minimum value of primary attributes
	 */
	private static final int MIN_ATTRIBUTE = 1;

	// Secondary Attributes (Nominal) //

	public int maxSecondaryAttribute() {
		return (int) Math.ceil(this.getWeight() * this.getToughness() / 50.0);
	}

	/**
	 * Check whether the given attribute is a valid Secondary Attribute
	 * (Hitpoints or Stamina) for any Unit.
	 * 
	 * @param attribute
	 *            The Secondary Attribute to check.
	 * @return Returns true if and only if the attribute is bigger than MIN_SEC_ATTRIBUTE
	 * 					and smaller than the units maxSecondaryAttribute().
	 * 			| result == (attribute >= MIN_SEC_ATTRIBUTE && attribute 
	 * 							<= getStrength()*getToughness()/50)
	 */
	public boolean isValidSecAttribute(int attribute) {
		return attribute >= MIN_SEC_ATTRIBUTE
				&& attribute <= this.maxSecondaryAttribute();
	}

	/**
	 * Return the Hitpoints of this Unit.
	 */
	@Basic
	@Raw
	public int getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * Set the Hitpoints of this Unit to the given Hitpoints.
	 * 
	 * @param hitpoints
	 *            The new Hitpoints for this Unit.
	 * @pre The given Hitpoints must be a valid Hitpoints for any Unit. 
	 * 		| isValidHitpoints(hitpoints)
	 * @post The Hitpoints of this Unit is equal to the given Hitpoints. 
	 * 		| new.getHitpoints() == hitpoints
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

	/**
	 * Return the Stamina of this Unit.
	 */
	@Basic
	@Raw
	public int getStamina() {
		return this.stamina;
	}

	/**
	 * Set the Stamina of this Unit to the given Stamina.
	 * 
	 * @param stamina
	 *            The new Stamina for this Unit.
	 * @pre The given Stamina must be a valid Stamina for any Unit. |
	 *      isValidStamina(stamina)
	 * @post The Stamina of this Unit is equal to the given Stamina. 
	 * 		| new.getStamina() == stamina
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

	/**
	 * Variable registering the minimum value of secondary attributes.
	 */
	private static final int MIN_SEC_ATTRIBUTE = 0;

	// Name (defensive) //

	/**
	 * Return the Name of this Unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given Name is a valid Name for any Unit.
	 * 
	 * @param name
	 *            The Name to check.
	 * @return Returns true if and only if all the characters in name 
	 * 			appear in the pattern validCharacters
	 * 			| if (name matches validCharacters)
	 * 			|	result == true
	 * 			| else
	 * 			|	result == false
	 * 
	 */
	public static boolean isValidName(String name) throws ModelException {
		Matcher fullName = validCharacters.matcher(name);
		boolean fullNameCorrect = fullName.matches();
		Matcher firstLetter = upperCase.matcher(name.substring(0,1));
		boolean firstLetterCorrect = firstLetter.matches();
		return (fullNameCorrect && firstLetterCorrect && name.length() >=2) ;
	}

	/**
	 * Set the Name of this Unit to the given Name.
	 * 
	 * @param name
	 *            The new Name for this Unit.
	 * @post The Name of this new Unit is equal to the given Name. 
	 * 			|new.getName() == name
	 * @throws ModelException
	 *      	The given Name is not a valid Name for any Unit. 
	 *      	| ! isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws ModelException {
		if (!isValidName(name))
			throw new ModelException();
		this.name = name;
	}

	private static final Pattern upperCase = Pattern.compile("[A-Z]");
	/**
	 * variable containing the pattern of valid characters for Unit names.
	 */
	private static final Pattern validCharacters = Pattern
			.compile("[[a-zA-Z][\"][\'][\\s]]*");
	/**
	 * Variable registering the Name of this Unit.
	 */
	private String name;
}
