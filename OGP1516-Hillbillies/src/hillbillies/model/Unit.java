
package hillbillies.model;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 *	A class describing the Hillbillie Unit
 *
 *
 * @invar Each Unit can have its Faction as Faction.
*       	| canHaveAsFaction(this.getFaction())
 * @invar The Coordinate must be valid for the game world configuration. 
 * 			| isValidPosition(getPosition())
 * @invar The Weight of each Unit must be a valid Weight for any Unit. 
 * 			| isValidWeight(getWeight())
 * @invar The Agility of each Unit must be a valid Agility for any Unit. 
 * 			| isValidAgility(getAgility())
 * @invar The Strength of each Unit must be a valid Strength for any Unit. 
 * 			| isValidStrength(getStrength())
 * @invar The Toughness of each Unit must be a valid Toughness for any Unit.
 * 			| isValidToughness(getToughness())
 * @invar The Hitpoints of each Unit must be a valid Hitpoints for any Unit. 
 * 			| isValidHitpoints(getHitpoints())
 * @invar The Stamina of each Unit must be a valid Stamina for any Unit. 
 * 			| isValidStamina(getStamina())
 * @invar The Name of each Unit must be a valid Name for any Unit. 
 * 			| isValidName(getName())
 * @invar  The Orientation of each Unit must be a valid Orientation for any Unit.
 * 		   	| isValidOrientation(getOrientation())
 * @invar The position of each Unit must be a valid position for any Unit.
 *        	| isValidPosition(getPosition())
 * @invar  The remaining attack time of each Unit must be a valid remaining attack time for any Unit.
 *       	| isValidRemainingAttackTime(getRemainingAttackTime())
 * @invar  The remaining work time of each Unit must be a valid remaining work time for any Unit.
 *       	| isValidRemainingWorkTime(getRemainingWorkTime())
 * @invar  The remaining attack time of each Unit must be a valid remaining attack time for any Unit.
 *       	| isValidRemainingAttackTime(getRemainingAttackTime())
 * @invar  The destination cube of each Unit must be a valid destinatination cube for any Unit
 * 			| isValidPosition(DestinationCube)
 * @invar  Each Unit can have its World as World.
 *          | canHaveAsWorld(this.getWorld())
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */

public class Unit {

	// Constructor //

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
	public Unit(String name, int[] position, int weight, int agility,
			int strength, int toughness, boolean enableDefaultBehavior)
					throws ModelException {
		if (isValidInitialAttribute(agility)) {
			this.setAgility(agility);
		} else
			this.setAgility(nearestValidInitialAttribute(agility));
		if (isValidInitialAttribute(strength)) {
			this.setStrength(strength);
		} else
			this.setStrength(nearestValidInitialAttribute(strength));
		if (isValidInitialAttribute(toughness)) {
			this.setToughness(toughness);
		} else
			this.setToughness(nearestValidInitialAttribute(toughness));
		if (this.isValidInitialWeight(weight)) {
			this.setWeight(weight);
		} else
			this.setWeight(this.nearestValidInitialWeight(weight));
		Coordinate newPosition = new Coordinate(position[0], position[1],
				position[2]).sum(centerCube());
		try {
			this.setPosition(newPosition);
		} catch (ModelException exc) {
			throw new ModelException("Can't create a unit here");
		}
		this.setName(name);
		this.setOrientation((float) (Math.PI / 2));
		this.setStamina(this.maxSecondaryAttribute());
		this.setHitpoints(this.maxSecondaryAttribute());
		this.setActivity(Activity.IDLE);
		this.setDefaultBehavior(enableDefaultBehavior);
		this.faction = this.getWorld().getFactiontoJoin();
		this.world = null;
	}

	public Unit(String name, int[] position, int weight, int agility,
			int strength, int toughness, boolean enableDefaultBehavior,
			World world) throws ModelException {
		this.setWorld(world);
		if (isValidInitialAttribute(agility)) {
			this.setAgility(agility);
		} else
			this.setAgility(nearestValidInitialAttribute(agility));
		if (isValidInitialAttribute(strength)) {
			this.setStrength(strength);
		} else
			this.setStrength(nearestValidInitialAttribute(strength));
		if (isValidInitialAttribute(toughness)) {
			this.setToughness(toughness);
		} else
			this.setToughness(nearestValidInitialAttribute(toughness));
		if (this.isValidInitialWeight(weight)) {
			this.setWeight(weight);
		} else
			this.setWeight(this.nearestValidInitialWeight(weight));
		Coordinate newPosition = new Coordinate(position[0], position[1],
				position[2]).sum(centerCube());
		try {
			this.setPosition(newPosition);
		} catch (ModelException exc) {
			throw new ModelException("Can't create a unit here");
		}
		this.setName(name);
		this.setOrientation((float) (Math.PI / 2));
		this.setStamina(this.maxSecondaryAttribute());
		this.setHitpoints(this.maxSecondaryAttribute());
		this.setActivity(Activity.IDLE);
		this.setDefaultBehavior(enableDefaultBehavior);
		this.faction = this.getWorld().getFactiontoJoin();

	}

	// World //

	void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * Return the World of this Unit.
	 */
	@Basic
	@Raw
	@Immutable
	public World getWorld() {
		return this.world;
	}
	/**
	 * Check whether this Unit can have the given World as its World.
	 *  
	 * @param  world
	 *         The World to check.
	 * @return 
	 *       | result == (world != null)
	*/
	@Raw
	public boolean canHaveAsWorld(World world) {
		return (world != null);
	}
	/**
	 * Variable registering the World of this Unit.
	 */
	private World world;

	// Position (Defensive) //

	/**
	 * Return the position of this Unit.
	 */
	@Basic
	@Raw
	public Coordinate getPosition() {
		return this.position;
	}
	/**
	 * Return the in-world position of this Unit.
	 */
	@Basic
	public Coordinate getInWorldPosition() {
		return this.getPosition().floor();
	}
	/**
	 * Set the position of this Unit to the given position.
	 * 
	 * @param  position
	 *         The new position for this Unit.
	 * @post   The position of this new Unit is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws ModelException
	 *         The given position is not a valid position for any
	 *         Unit.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(Coordinate position) throws ModelException {
		if (!isValidPosition(position))
			throw new ModelException("Invalid Position");
		this.position = new Coordinate(position.getX(), position.getY(),
				position.getZ());
	}

	private boolean isValidPosition(Coordinate coordinate) {
		return this.getWorld().isValidPosition(coordinate);
	}

	/**
	 * Variable registering the position of this Unit.
	 */
	private Coordinate position;

	/**
	 * Returns a Coordinate object which spans from the lower corner to the center of the cube
	 * @return Coordinate(0.5,0.5,0.5)
	 */
	static Coordinate centerCube() {
		return new Coordinate(0.5, 0.5, 0.5);
	}
	/**
	 * Symbolic constant registering the length of a cube
	 */
	public static final double CUBE_LENGTH = 1.0;

	// Faction //

	/**
	 * Return the Faction of this Unit.
	 */
	@Basic
	@Raw
	@Immutable
	public Faction getFaction() {
		return this.faction;
	}
	/**
	 * Check whether this Unit can have the given Faction as its Faction.
	 *  
	 * @param  faction
	 *         The Faction to check.
	 * @return 
	 *       | result == (faction != null)
	*/
	@Raw
	public static boolean canHaveAsFaction(Faction faction) {
		return faction != null;
	}
	/**
	 * Variable registering the Faction of this Unit.
	 */
	private final Faction faction;

	// Primary Attributes (Total) //

	/**
	 * Checks whether the given attribute is a valid initial value for that attribute
	 * 
	 * @param 	attribute
	 * 			the attribute to check.
	 * @return 	Return true when the value lies between 25 and 100, both inclusive
	 * 		| 	result == (attribute >= 25 && attribute <= 100);
	 */
	static boolean isValidInitialAttribute(int attribute) {
		return (attribute >= 25 && attribute <= 100);
	}
	/**
	 * Returns the nearest valid initial value of a primary attribute (toughness, strength, agility)
	 * a unit can have
	 * 
	 * @param 	attribute
	 * 			the value to determine the correct value from
	 * @return 	Returns 25 if attribute was lower than 25, 100 otherwise
	 * 		| 	if (attribute < 25)
	 *		| 		then result == 25
	 *		| 	else
	 *		| 		result == 100
	 */
	public static int nearestValidInitialAttribute(int attribute) {
		if (attribute < 25)
			return 25;
		else
			return 100;
	}
	/**
	 * Check whether the given attribute is a valid value of a primary attribute
	 * (agility, strength, toughness) for any Unit.
	 * 
	 * @param 	attribute
	 *           The value to check.
	 * @return 	Returns true if and only if the agility is bigger than or equal to MIN_ATTRIBUTE
	 * 				and smaller than or equal to MAX_ATTRIBUTE
	 * 		| 	result == attribute <= MAX_ATTRIBUTE && attribute >= MIN_ATTRIBUTE
	 */
	static boolean isValidAttribute(int attribute) {
		return (attribute <= MAX_ATTRIBUTE && attribute >= MIN_ATTRIBUTE);
	}
	/**
	 * Return the nearest valid value for a primary attribute (agility, strength, toughness)
	 * of a Unit.
	 * 
	 * @param 	attribute
	 *          The value of which the nearest should be determined
	 * @return If attribute is higher than the highest allowed value for primary attributes, the
	 *         result is that highest allowed value, otherwise, the result is
	 *         the lowest allowed value 
	 *         | if (attribute > MAX_ATTRIBUTE)
	 *         | 	then result == MAX_ATTRIBUTE 
	 *         | else 
	 *         |	result == MIN_ATTRIBUTE
	 */
	public static int nearestValidAttribute(int attribute) {
		if (attribute > MAX_ATTRIBUTE)
			return MAX_ATTRIBUTE;
		else
			return MIN_ATTRIBUTE;
	}

	/**
	 * Checks whether the given value is a valid initial value for Weight
	 * @param weight
	 * 			The weight to check
	 * @return Returns true if weight lies between the lowest allowed weight and 100
	 * 		| if ( lowestValidWeight() <= weight <= 100)
	 * 		| 	then result == true
	 * 		| else
	 * 		| 	result == false
	 */
	boolean isValidInitialWeight(int weight) {
		return (weight >= this.lowestValidWeight() && weight <= 100);
	}
	/**
	 * Returns the nearest valid initial value of weight
	 * a unit can have
	 * 
	 * @param weight
	 * 			the value to determine the correct value from
	 * @return Returns the lowest allowed weight if attribute was lower than 25, 100 otherwise
	 * 		| if (weight < 25)
	 *		| 	then result == 25
	 *		| else
	 *		| 	result == 100
	 */
	public int nearestValidInitialWeight(int weight) {
		if (weight < this.lowestValidWeight())
			return this.lowestValidWeight();
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
			return this.lowestValidWeight();
	}
	/**
	 * Return the lowest valid Weight of a Unit.
	 * 
	 * @param weight
	 *            The lowest possible weight for a Unit.
	 * @return Returns the lowest allowed weight for a Unit.
	 * 			|result = Math.ceil((this.getAgility() + this.getStrength()) / 2.0)
	 */
	public int lowestValidWeight() {
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
	 *         | weight <= 200)
	 */
	boolean isValidWeight(int weight) {
		return (weight >= this.lowestValidWeight() && weight <= MAX_ATTRIBUTE);
	}
	/**
	 * Set the Weight of this Unit to the given Weight.
	 * 
	 * @param weight
	 *            The new Weight for this Unit.
	 * @post If the given Weight is a valid Weight for any Unit, the Weight of
	 *       this new Unit is equal to the given Weight. 
	 *       |if (isValidWeight(weight)) 
	 *       | 	then new.getWeight() == weight
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
	 * Return the Agility of this Unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}
	/**
	 * Set the Agility of this Unit to the given Agility.
	 * 
	 * @param agility
	 *            The new Agility for this Unit.
	 * @post If the given Agility is a valid Agility for any Unit, the Agility
	 *       of this new Unit is equal to the given Agility, otherwise it is set to the
	 *       nearest allowed value
	 *       | if (isValidAgility(agility)) 
	 *       | 		then new.getAgility() == agility
	 *       | else 
	 *       |		new.getAgility() == nearestValidAttribute(agility)
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAttribute(agility))
			this.agility = agility;
		else
			this.agility = nearestValidAttribute(agility);
	}
	/**
	 * Variable registering the Agility of this Unit.
	 */
	private int agility;

	/**
	 * Return the Strength of this Unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}
	/**
	 * Set the Strength of this Unit to the given Strength.
	 * 
	 * @param strength
	 *            The new Strength for this Unit.
	 * @post If the given Strength is a valid Strength for any Unit, the
	 *       Strength of this new Unit is equal to the given Strength.
	 *       Otherwise, the strength will be set to the nearest valid value.
	 *       | if (isValidStrength(strength)) 
	 *       | 		then new.getStrength() == strength 
	 *       | else 
	 *       |		new.getStrength() == nearestValidAttribute(strength)
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidAttribute(strength))
			this.strength = strength;
		else
			this.strength = nearestValidAttribute(strength);
	}
	/**
	 * Variable registering the Strength of this Unit.
	 */
	private int strength;

	/**
	 * Return the Toughness of this Unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
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
	 *       | else 
	 *       |		new.getToughness() == nearestValidAttribute(toughness)
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidAttribute(toughness))
			this.toughness = toughness;
		else
			this.toughness = nearestValidAttribute(toughness);
	}
	/**
	 * Variable registering the Toughness of this Unit.
	 */
	private int toughness;

	/**
	 * Symbolic constant registering the maximum value of primary attributes
	 */
	private static final int MAX_ATTRIBUTE = 200;
	/**
	 * Symbolic constant registering the minimum value of primary attributes
	 */
	private static final int MIN_ATTRIBUTE = 1;

	// Secondary Attributes (Nominal) //

	/**
	 * Returns the maximum value for any secondary attribute of a unit
	 * @return Math.ceil(this.getWeight() * this.getToughness() / 50.0)
	 */
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
	boolean isValidSecAttribute(double attribute) {
		return attribute >= MIN_SEC_ATTRIBUTE
				&& attribute <= this.maxSecondaryAttribute();
	}

	/**
	 * Return the Hitpoints of this Unit.
	 */
	@Basic
	@Raw
	public double getHitpoints() {
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
	public void setHitpoints(double hitpoints) {
		assert this.isValidSecAttribute(hitpoints);
		this.hitpoints = hitpoints;
	}
	/**
	 * Variable registering the Hitpoints of this Unit.
	 */
	private double hitpoints;

	/**
	 * Return the Stamina of this Unit.
	 */
	@Basic
	@Raw
	public double getStamina() {
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
	public void setStamina(double stamina) {
		assert this.isValidSecAttribute(stamina);
		this.stamina = stamina;
	}
	/**
	 * Variable registering the Stamina of this Unit.
	 */
	private double stamina;

	/**
	 * Symbolic constant registering the minimum value of secondary attributes.
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
	 */
	static boolean isValidName(String name) {
		if (name == null || name.length() == 0)
			return false;
		Matcher fullName = validCharacters.matcher(name);
		boolean fullNameCorrect = fullName.matches();
		Matcher firstLetter = upperCase.matcher(name.substring(0, 1));
		boolean firstLetterCorrect = firstLetter.matches();
		return (fullNameCorrect && firstLetterCorrect && name.length() >= 2);
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
			throw new ModelException("Invalid Name");
		this.name = name;
	}
	/**
	 * Variable registering the Name of this Unit.
	 */
	private String name;

	/**
	 * Pattern containing the set of valid characters for the first letter of Unit names.
	 */
	private static final Pattern upperCase = Pattern.compile("[A-Z]");
	/**
	 * Pattern containing the set of valid characters for Unit names.
	 */
	private static final Pattern validCharacters = Pattern
			.compile("[[a-zA-Z][\"][\'][\\s]]*");

	// Orientation (total) //

	/**
	 * Return the Orientation of this Unit.
	 */
	@Basic
	@Raw
	public float getOrientation() {
		return this.orientation;
	}
	/**
	 * Check whether the given Orientation is a valid Orientation for
	 * any Unit.
	 *  
	 * @param  orientation
	 *         The Orientation to check.
	 * @return 
	 *       | result == (orientation > (float) - Math.PI && orientation <= (float) Math.PI)
	*/
	static boolean isValidOrientation(float orientation) {
		return (orientation > (float) -Math.PI
				&& orientation <= (float) Math.PI);
	}
	/**
	 * Set the Orientation of this Unit to the given Orientation.
	 * 
	 * @param  orientation
	 *         The new Orientation for this Unit.
	 * @post   If the given Orientation is a valid Orientation for any Unit,
	 *         the Orientation of this new Unit is equal to the given
	 *         Orientation.
	 *       | if (isValidOrientation(orientation))
	 *       |   then new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(float orientation) {
		if (isValidOrientation(orientation))
			this.orientation = orientation;
		else
			this.orientation = (float) (orientation % 2 * Math.PI);
	}
	/**
	 * Variable registering the Orientation of this Unit.
	 */
	private float orientation;

	// Activity variable (Total) //

	/**
	 * Method that sets the current activity of the Unit.
	 * 
	 * @param 	activity
	 * 			the activity to set
	 * @post	The Activity of this new Unit is equal to the given Activity. 
	 * 			| new.activity == activity
	 */
	@Raw
	void setActivity(Activity activity) {
		this.activity = activity;
	}
	/**
	 * Method returns the current activity of the Unit.
	 */
	@Basic
	public Activity getActivity() {
		return this.activity;
	}
	/**
	 * Variable registering the current activity.
	 */
	private Activity activity = Activity.IDLE;

	// Time control (defensive) //

	/**
	 * Method that advances the game time and carries through any activity
	 * the unit might be performing
	 * 
	 * @param deltaT
	 * 			The time to advance the game time with
	 * 
	 * @post The unit will have carried through the activity it was doing, however
	 * 			if the unit hasn't rested for the last three minutes, it tries to rest
	 * 			instead.
	 */
	public void advanceTime(double deltaT) {
		if (this.getTimeSinceLastRest() >= 180) {
			try {
				this.rest();
			} catch (ModelException e) {
				// unit not ready to rest
			}
		}
		try {
			this.doDefaultBehavior();
		} catch (ModelException e2) {
			// nothing to do here
		}
		try {
			this.working(deltaT);
		} catch (ModelException e3) {
			// nothing to do here
		}
		try {
			this.attacking(deltaT);
		} catch (ModelException e4) {
			// nothing to do here
		}
		try {
			this.updatePosition(deltaT);
		} catch (ModelException e5) {
			// nothing to do here
		}
		try {
			this.resting(deltaT);
		} catch (ModelException e6) {
			this.setTimeSinceLastRest(deltaT + this.getTimeSinceLastRest());
		}
	}

	// Moving (defensive) //

	/**
	 * Method that makes the Unit move to the specified adjacent cube.
	 * 
	 * @param	x
	 * 			the x-component of the cube the Unit will move to.
	 * @param	y
	 * 			the y-component of the cube the Unit will move to.
	 * @param	z
	 * 			the z-component of the cube the Unit will move to.
	 * @effect	The current coordinates will be the first in the unit's
	 * 			movement path
	 * 		| this.pathExtension(this.getPosition)
	 * @effect 	The given coordinates will be added to the unit's movement path
	 * 		| this.pathExtension(x,y,z)
	 * @post	The Unit will be in the moving state
	 * 		| new.getActivity == MOVING
	 * @throws	ModelException 
	 * 			the unit is already moving (or sprinting)
	 * 		| (this.getActivity() != Activity.SPRINTING)
	 *		|	&& (this.getActivity() != Activity.MOVING)
	 */
	public void moveToAdjacent(int x, int y, int z) throws ModelException {
		if ((this.getActivity() != Activity.SPRINTING)
				&& (this.getActivity() != Activity.MOVING)) {
			this.clearPath();
			this.addToPath(this.getPosition());
			this.pathExtension((int) this.getInWorldPosition().getX() + x,
					(int) this.getInWorldPosition().getY() + y,
					(int) this.getInWorldPosition().getZ() + z);
			this.setActivity(Activity.MOVING);
		} else
			throw new ModelException("Already Moving");

	}
	/**
	 * Method that makes the Unit move to a specified position.
	 * 
	 * @param	x
	 * 			the x-component of the cube the Unit will move to.
	 * @param	y
	 * 			the y-component of the cube the Unit will move to.
	 * @param	z
	 * 			the z-component of the cube the Unit will move to.
	 * @post	The unit will have a valid path added to its movement path,
	 * 			as given by the method findPath()
	 * @post	The unit will be in the moving state
	 * 		| new.getActivity == MOVING
	 * @effect	In order to find the path to the given square, findPath() is called
	 * 		| findPath()
	 * @throws	ModelException
	 * 			When the unit is already in a moving state
	 * 		| this.getActivity == MOVING || this.getActivity == STPRINTING
	 */
	public void moveTo(int x, int y, int z) throws ModelException {
		this.clearPath();
		Coordinate destination = new Coordinate(x, y, z);

		this.setDestinationCube(destination);

		this.addToPath(this.getPosition());
		this.findPath();
		this.setActivity(Activity.MOVING);

	}
	/**
	 * Method that seeks the path for the Unit to move to its destination.
	 *
	 * @effect The subsequent steps in the path to the destination will be
	 * 			added to the unit's movement path
	 * 		| pathExtension(step)
	 * @post The unit's destination will be the last coordinate in the unit's
	 * 			movement path
	 * 		| new.getPath().getLast() == this.getDestination
	 * @throws	ModelException
	 * 			the unit has no destination
	 * 		| this.getDestinationCube() == null) 
	 */
	void findPathEmptyWorld() throws ModelException {
		if (this.getDestinationCube() != null) {
			int x = 0;
			int y = 0;
			int z = 0;
			while ((int) this.getDestinationCube().getX() != (int) this
					.getPath().getLast().floor().getX()
					|| (int) this.getDestinationCube().getY() != (int) this
							.getPath().getLast().floor().getY()
					|| (int) this.getDestinationCube().getZ() != (int) this
							.getPath().getLast().floor().getZ()) {
				if ((int) this.getDestinationCube().getX() > (int) this
						.getPath().getLast().floor().getX())
					x = 1;
				else if ((int) this.getDestinationCube().getX() < (int) this
						.getPath().getLast().floor().getX())
					x = -1;
				else
					x = 0;
				if ((int) this.getDestinationCube().getY() > (int) this
						.getPath().getLast().floor().getY())
					y = 1;
				else if ((int) this.getDestinationCube().getY() < (int) this
						.getPath().getLast().floor().getY())
					y = -1;
				else
					y = 0;
				if ((int) this.getDestinationCube().getZ() > (int) this
						.getPath().getLast().floor().getZ())
					z = 1;
				else if ((int) this.getDestinationCube().getZ() < (int) this
						.getPath().getLast().floor().getZ())
					z = -1;
				else
					z = 0;

				this.pathExtensionEmptyWorld(x, y, z);
			}
		} else
			throw new ModelException("No destination!");
	}

	void findPath() throws ModelException {
		LinkedList<Coordinate> openSet = new LinkedList<>();
		LinkedList<Coordinate> closedSet = new LinkedList<>();
		openSet.add(this.getPosition());
		
		while (true){
			Coordinate current = openSet.poll();
			closedSet.add(current);
			
			if (current == this.getDestinationCube())
				return;
			
			for (Cube cube : this.getWorld().getGrid().adjacentCubes(current))
				return;
		}
			
	}

	void pathExtension(int x, int y, int z) throws ModelException {
		Coordinate target = new Coordinate(x, y, z).sum(centerCube());
		if (this.isValidPosition(target))
			this.addToPath(target);
		else
			throw new ModelException("Cannot go here");
	}

	/**
	 * Method that adds a given set of coordinates to the
	 * movement path of the unit
	 * 
	 * @param x
	 * 			the x component of the coordinate
	 * @param y
	 * 			the y component of the coordinate
	 * @param z
	 * 			the z component of the coordinate
	 * @effect	The coordinate will be added to the unit's movement path
	 * 		| addToPath(Coordinate(x,y,z))
	 * @post The unit will have the given coordinate as the last coordinate
	 * 			in its movement path
	 * 		| new.getPath().getLast() == Coordinate(x,y,z)
	 * @throws ModelException
	 * 			the the given coordinates are no valid in world position.
	 * 		| (! this.isValidPosition(target))
	 */
	void pathExtensionEmptyWorld(int x, int y, int z) throws ModelException {
		Coordinate target = new Coordinate(x, y, z).sum(centerCube())
				.sum(this.getPath().getLast().floor());
		if (this.isValidPosition(target)) {
			this.addToPath(target);
		} else
			throw new ModelException("Cannot move here");
	}

	/**
	 * Method that stops a unit from moving.
	 * 
	 * @post | this.activity == IDLE
	 */
	void stopMoving() {
		this.setActivity(Activity.IDLE);
	}
	/**
	 * Method that initiates a unit to sprint.
	 * 
	 * @post | this.activity == SPRINTING
	 * @throws ModelException
	 * 		 	The Unit is not able to sprint
	 * 		 |(! this.canSprint())
	 */
	public void startSprinting() throws ModelException {
		if (this.canSprint())
			this.setActivity(Activity.SPRINTING);
		else
			throw new ModelException("Can't sprint!");
	}
	/**
	 * Method that indicates whether a Unit is able to sprint
	 * 
	 * @retunr return true if and only if the Unit is moving and has stamina that is greater than 0
	 *	 	| if this.getActivity() == Activity.MOVING && this.getStamina() > 0
	 *	 	| 	then result == true
	 *		| else
	 *		|	result == false
	 */
	boolean canSprint() {
		return (this.getActivity() == Activity.MOVING && this.getStamina() > 0);
	}
	/**
	 * Method that stops a unit from sprinting.
	 * 
	 * @post | this.setActivity(Activity.MOVING)
	 */
	public void stopSprinting() {
		if (this.getActivity() == Activity.SPRINTING) {
			this.setActivity(Activity.MOVING);
		}
	}

	/**
	 * Method that computes the Unit's walking speed.
	 * 
	 * @return Returns the walking speed of the unit, which is its base speed
	 * 			(getAgility*getStrength/(2*getWeight))
	 * 			modified by the target cube's relative position. Walking uphill
	 * 			goes slower, walking downhill is faster.
	 * 		| if (target.getZ - getPosition.getZ < 0)
	 * 		| 	then result == 1.2*baseSpeed
	 * 		| else if (target.getZ - getPosition.getZ > 0)
	 * 		| 	then result == 0.5*baseSpeed
	 * 		| else
	 * 		|	result == baseSpeed
	 * 		
	 */
	public double walkingSpeed() {
		double walkingSpeed = 0;
		double baseSpeed = 1.5 * (this.getAgility() + this.getStrength())
				/ (2 * this.getWeight());
		if (this.getPath().get(1).floor().getZ()
				- this.getPath().get(0).floor().getZ() < 0)
			walkingSpeed = 1.2 * baseSpeed;
		else if (this.getPath().get(1).floor().getZ()
				- this.getPath().get(0).floor().getZ() > 0)
			walkingSpeed = 0.5 * baseSpeed;
		else
			walkingSpeed = baseSpeed;
		return walkingSpeed;
	}
	/**
	 * Method that returns the current speed.
	 * 
	 * @return Returns 0 if the unit isn't in a moving state,
	 * 			returns the walking speed if the unit is moving,
	 * 			return 2 times the walking speed if the unit is sprinting.
	 * 		| if getActivity != MOVING && getActivity != SPRINTING
	 * 		| 	then result == 0
	 * 		| if getActivity == MOVING
	 * 		| 	then result == walkingspeed()
	 * 			
	 */
	public double getCurrentSpeed() {
		if (this.getActivity() != Activity.SPRINTING
				&& this.getActivity() != Activity.MOVING)
			return 0;
		else if (this.getActivity() == Activity.MOVING)
			return this.walkingSpeed();
		else
			return 2 * this.walkingSpeed();
	}

	/**
	 * Method that updates the current position of the Unit.
	 * 
	 * @param DeltaT
	 * 			the time-interval to update the position with
	 * @post The unit will have moved in the correct direction with the
	 * 			appropriate distance
	 * 		| new.getPosition == this.getPosition + this.getCurrentSpeed * DeltaT
	 * @throws ModelException
	 * 			the unit is not in a moving state
	 * 		|	(this.getActivity() != Activity.MOVING && this.activity != Activity.SPRINTING)
	*/
	void updatePosition(double deltaT) throws ModelException {
		if (this.getActivity() == Activity.MOVING
				|| this.activity == Activity.SPRINTING) {
			if (this.getPath().size() >= 2) {
				if (this.getDefaultBehavior()
						&& this.getActivity() != Activity.SPRINTING
						&& this.canSprint()) {
					Random rand = new Random();
					int decider = rand.nextInt(2);
					if (decider == 1)
						this.setActivity(Activity.SPRINTING);
				}
				Coordinate start = this.getPath().get(0);
				Coordinate target = this.getPath().get(1);
				Coordinate direction = start.directionVector(target);
				Coordinate displacement = direction.scalarMult(
						(this.getCurrentSpeed() * deltaT) / CUBE_LENGTH);
				if (displacement.length() >= this.remaininglegDistance()) {
					try {
						this.AwardExperience(1);
						this.setPosition(target);
					} catch (ModelException e) {
						// shouldn't happen
					}
					this.getPath().remove(0);
					if (this.getPath().size() < 2)
						this.stopMoving();
				} else {
					try {
						this.setPosition(this.getPosition().sum(displacement));
					} catch (ModelException e) {
						// shoudn't happen
					}
					this.setOrientation((float) Math.atan2(direction.getY(),
							direction.getX()));
					if (this.getActivity() == Activity.SPRINTING) {
						if (this.getStamina() - deltaT / 0.1 > 0)
							this.setStamina(this.getStamina() - deltaT / 0.1);
						else {
							this.setStamina(MIN_SEC_ATTRIBUTE);
							this.stopSprinting();
						}
					}
				}
			} else
				this.stopMoving();
		} else
			throw new ModelException("Unit is not in a moving state");
	}
	/**
	 * Method that returns the remaining distance to reach the next target cube in the path.
	 * 
	 * @return this.getPath().get(1).difference(this.getPosition()).length()
	 */
	double remaininglegDistance() {
		Coordinate vector = this.getPath().get(1)
				.difference(this.getPosition());
		return vector.length();
	}
	/**
	 * Method that adds a next target to the movement path.
	 * 
	 * @param 	target
	 * 			the coordinate that needs to be added to the path.
	 * @post the unit will have the target as his last coordinate in his movement path
	 * 		| new.getPath().getLast() == target
	 */
	void addToPath(Coordinate target) {
		this.getPath().addLast(target);
	}
	/**
	 * Method that clears the Unit's path.
	 * 
	 * @post the Units path is empty
	 * 		| new.getPath().size() == 0;
	 */
	void clearPath() {
		this.getPath().clear();
	}

	/**
	 * Method that sets the cube the Unit will move to.
	 * 
	 * @param	destinationCube
	 * 			the cube the Unit will move to.
	 * @post the unit will have the given cube as its destination cube
	 * 		| new.getDestinationCube == destinationCube
	 * @throws ModelException
	 * 			The destination is not a valid Game position
	 * 		| ! isValidPosition(destinationCube)
	 */
	@Raw
	void setDestinationCube(Coordinate destinationCube) throws ModelException {
		if (this.isValidPosition(destinationCube)) {
			this.destinationCube = destinationCube;
		} else
			throw new ModelException("Can't go there");
	}
	/**
	 * Method that returns the cube the Unit is moving to.
	 */
	@Basic
	public Coordinate getDestinationCube() {
		return this.destinationCube;
	}
	/**
	 * Coordinate that keeps the cube the Unit is moving to.
	 */
	private Coordinate destinationCube;
	/**
	 * Method that returns the current Path.
	 */
	@Basic
	public LinkedList<Coordinate> getPath() {
		return this.path;
	}
	/**
	 * Linked list that keeps the Path the Unit is about to walk.
	 */
	private LinkedList<Coordinate> path = new LinkedList<>();

	// Working (defensive) //
	
	public void workAt(int x, int y, int z) throws ModelException{
		Coordinate targetCube = new Coordinate(x, y, z);
		
	}

	/**
	 * Method that initiates a work task for a Unit.
	 * 
	 * @post The unit will be in the working state 
	 * 		| new.getActivity() == WORKING
	 * @post The unit will have the worktime as its workting 
	 *		| new.getRemainingWorkTime == this.workTime()
	 * @throws ModelException
	 * 			Unit is executing another activity
	 * 		|	(this.getActivity() == Activity.MOVING
	 *		|	|| this.getActivity() == Activity.SPRINTING
	 *		|	|| this.getActivity() == Activity.WORKING
	 *		|	|| this.getActivity() == Activity.ATTACKING
	 *		|	|| this.getActivity() == Activity.DEFENDING)
	 */
	public void work() throws ModelException {
		if (this.getActivity() != Activity.MOVING
				&& this.getActivity() != Activity.SPRINTING
				&& this.getActivity() != Activity.WORKING
				&& this.getActivity() != Activity.ATTACKING
				&& this.getActivity() != Activity.DEFENDING) {
			this.setRemainingWorkTime(this.workTime());
			this.setActivity(Activity.WORKING);
		} else
			throw new ModelException("Unit not ready to work");
	}
	/**
	 * Method that simulates the working behavior of a Unit
	 * @param deltaT
	 * 			The time to work for
	 * @throws ModelException
	 * 			When the unit isn't in the working state
	 * 		|	(this.getActivity() == Activity.WORKING)
	 * @post   The remaining work time is lowered by deltaT, 
	 * 			if able, otherwise, it is set to 0
	 * 		| new.getRemainingWorkTime == this.getRemainingWorkTime - deltaT
	 */
	void working(double deltaT) throws ModelException {
		if (this.getActivity() != Activity.WORKING)
			throw new ModelException("The unit isn't in a working state");
		else
			try {
				this.setRemainingWorkTime(this.getRemainingWorkTime() - deltaT);
			} catch (ModelException e) {
				this.setRemainingWorkTime(0);
				this.stopWork();
			}
	}
	/**
	 * Method that stops a work task for a Unit
	 * 
	 * @post The unit will be idle 
	 * 		| new.getActivity == IDLE
	 */
	void stopWork() {
		this.AwardExperience(10);
		this.setActivity(Activity.IDLE);
	}

	/**
	 * Return the remaining work time of this Unit.
	 */
	@Basic
	@Raw
	public double getRemainingWorkTime() {
		return this.remainingWorkTime;
	}
	/**
	 * Check whether the given remaining work time is a valid remaining work time for
	 * any Unit.
	 *  
	 * @param  remaining work time
	 *         The remaining work time to check.
	 * @return true if the remaining work time is between 0 and the maximum worktime
	 *       | (remainingWorkTime <= this.workTime() && remainingWorkTime >= 0)
	*/
	boolean isValidRemainingWorkTime(double remainingWorkTime) {
		return (remainingWorkTime <= this.workTime() && remainingWorkTime >= 0);
	}
	/**
	 * Set the remaining work time of this Unit to the given remaining work time.
	 * 
	 * @param  remainingWorkTime
	 *         The new remaining work time for this Unit.
	 * @post   The remaining work time of this new Unit is equal to
	 *         the given remaining work time.
	 *       | new.getRemainingWorkTime() == remainingWorkTime
	 * @throws ModelException
	 *         The given remaining work time is not a valid remaining work time for any
	 *         Unit.
	 *       | ! isValidRemainingWorkTime(getRemainingWorkTime())
	 */
	@Raw
	void setRemainingWorkTime(double remainingWorkTime) throws ModelException {
		if (!isValidRemainingWorkTime(remainingWorkTime))
			throw new ModelException();
		this.remainingWorkTime = remainingWorkTime;
	}

	/**
	 * Method that computes how long it takes for a unit to finish a work task.
	 * 
	 * @return 500.0 / this.getStrength()
	 */
	double workTime() {
		return (500.0 / this.getStrength());
	}
	/**
	 * Variable registering the remaining work time of this Unit.
	 */
	private double remainingWorkTime;
	/**
	 * Set the units carried object to the given object
	 * 
	 * @param object
	 * 			the new object the unit is carrying
	 * @post	the carried object is the given object
	 * 		| 	this.getGameobject() == object
	 */
	void setObjectCarried(GameObject object){
		this.ObjectCarried = object;
	}
	/**
	 * Return the object the unit is carrying
	 */
	public GameObject getObjectCarried(){
		return ObjectCarried;
	}
	
	private GameObject ObjectCarried; 
	
	/**
	 * flag that registers whether a Unit is carrying a gameobject.
	 */
	public boolean isCarrying(){
		return (this.getObjectCarried() != null);
	}

	// Attacking & Defending (defensive) //

	/**
	 * Method that initiates an attack on another Unit
	 * 
	 * @param victim
	 * 			The Unit to attack
	 * @effect The attacking unit and to victim will face each other
	 * 		| this.orientWith(victim)
	 * @post The remaining attack time will be the time it takes to execute an attack
	 * 		| new.getRemainingAttackTime == attackTime()
	 * @post The attacking unit will be in the attacking state, while the defending unit
	 * 			will be in the defending state
	 * 		| new.getActivity == ATTACKING
	 * 		| victim.getActivity == DEFENDING
	 * @throws ModelException
	 * 			the target unit is too far
	 * 		|	(attackVector.length() > Math.sqrt(2))
	 * @throws ModelException
	 * 		 	the unit is executing another activity
	 * 		|	(this.getActivity() == Activity.RESTING
	 *		|	|| this.getActivity() == Activity.MOVING
	 *		|	|| this.getActivity() == Activity.SPRINTING
	 *		|	|| this.getActivity() == Activity.DEFENDING)
	 */
	public void attack(Unit victim) throws ModelException {
		if (this.getActivity() != Activity.RESTING
				&& this.getActivity() != Activity.MOVING
				&& this.getActivity() != Activity.SPRINTING
				&& this.getActivity() != Activity.DEFENDING) {
			try {
				this.setVictim(victim);
			} catch (ModelException exc) {
				// shouldn't happen
			}
			Coordinate attackVector = this.getVictim().getInWorldPosition()
					.difference(this.getInWorldPosition());
			if (attackVector.length() <= Math.sqrt(2)) {
				this.setRemainingAttackTime(attackTime);
				this.setActivity(Activity.ATTACKING);
				this.getVictim().setActivity(Activity.DEFENDING);
				this.getVictim().setDefaultBehavior(false);
				this.orientWith(this.getVictim());
			} else
				throw new ModelException("target too far away");
		} else
			throw new ModelException("Not ready");
	}
	/**
	 * Method that simulates the attacking behavior of a unit
	 * 
	 * @param DeltaT
	 *			The time to advance with
	 * @effect The attack is stopped when the remaining attack time reaches 0
	 * 		| if (this.getRemainingAttackTime - DeltaT < 0)
	 * 		| 	then this.stopAttack()
	 * @post The remaining attack time will be lowered by DeltaT, if able,
	 * 			otherwise it will be set to 0, and the attack is stopped.
	 * 		| new.getRemainingAttackTime == this.getRemainingAttackTime - DeltaT
	 * @throws ModelException
	 * 			the unit is not in ATTACKING-state
	 * 		|	(this.getActivity() != Activity.ATTACKING)
	 */
	void attacking(double DeltaT) throws ModelException {
		if (this.getActivity() == Activity.ATTACKING) {
			try {
				this.setRemainingAttackTime(
						this.getRemainingAttackTime() - DeltaT);
			} catch (ModelException exc) {
				try {
					this.setRemainingAttackTime(0);
				} catch (ModelException ex) {
					// shouldn't happen
				}
				this.stopAttack();
			}
		} else
			throw new ModelException("This unit isn't in an attacking state");
	}
	/**
	 * Method that stops an attack of a Unit, inflicting damage 
	 * when the victim doesn't successfully defend
	 * 
	 * @effect The defending unit tries to defend against the attacking unit
	 * 		| this.getVictim.defend(this)
	 * @effect The attacking unit does damage if the defending unit doesn't
	 * 			successfully defend
	 * 		| if (!this.getVictim().defend(this))
	 *		| 	then this.doesDamage();
	 * @post Both the attacker and the defender will be in the Idle state
	 * 		| this.getVictim.getActivity == IDLE
	 * 		| new.getActivity == IDLE	
	 * @post the attacking unit won't have a victim anymore
	 * 		| new.getVictim == null	
	 */
	void stopAttack() {
		if (!this.getVictim().defend(this)) {
			this.AwardExperience(20);
			this.doesDamage();
		}
		this.setActivity(Activity.IDLE);
		this.getVictim().setActivity(Activity.IDLE);
		try {
			this.setVictim(null);
		} catch (ModelException e) {
			// shouldn't happen
		}
	}

	/**
	 * Method that returns the attacked Unit.
	 */
	@Basic
	public Unit getVictim() {
		return this.victim;
	}
	/**
	 * Method that determines whether the victim is a valid victim for a unit
	 * 
	 * @param victim
	 * 			the victim to consider
	 * @return returns true: in this iteration of the game, any unit (including null) 
	 * 			is a valid victim.
	 *		| result == true
	 */
	boolean isValidVictim(Unit victim) {
		return true;
	}
	/**
	 * Set the unit's victim to the given victim
	 * 
	 * @param victim
	 * 			the new victim for this unit
	 * @post	the victim of the new unit is the given victim
	 * 		| new.getVictim() == victim
	 * @throws ModelException
	 * 			the given victim isn't a valid victim
	 * 		| ! isValidVictim()
	 */
	void setVictim(Unit victim) throws ModelException {
		if (isValidVictim(victim))
			this.victim = victim;
		else
			throw new ModelException("Not a valid victim");
	}
	/**
	 * Variable registering the Unit that is being attacked.
	 */
	private Unit victim = null;

	/**
	 * Return the remaining attack time of this Unit.
	 */
	@Basic
	@Raw
	public double getRemainingAttackTime() {
		return this.remainingAttackTime;
	}
	/**
	 * Check whether the given remaining attack time is a valid remaining attack time for
	 * any Unit.
	 *  
	 * @param  remaining attack time
	 *         The remaining attack time to check.
	 * @return returns true is the remain attack time is a valid time.
	 *       | (remainingAttackTime >= 0 && remainingAttackTime <= attackTime)
	*/
	static boolean isValidRemainingAttackTime(double remainingAttackTime) {
		return (remainingAttackTime >= 0 && remainingAttackTime <= attackTime);
	}
	/**
	 * Set the remaining attack time of this Unit to the given remaining attack time.
	 * 
	 * @param  remainingAttackTime
	 *         The new remaining attack time for this Unit.
	 * @post   The remaining attack time of this new Unit is equal to
	 *         the given remaining attack time.
	 *       | new.getRemainingAttackTime() == remainingAttackTime
	 * @throws ModelException
	 *         The given remaining attack time is not a valid remaining attack time for any
	 *         Unit.
	 *       | ! isValidRemainingAttackTime(getRemainingAttackTime())
	 */
	@Raw
	void setRemainingAttackTime(double remainingAttackTime)
			throws ModelException {
		if (!isValidRemainingAttackTime(remainingAttackTime))
			throw new ModelException();
		this.remainingAttackTime = remainingAttackTime;
	}
	/**
	 * Variable registering the remaining attack time of this Unit.
	 */
	private double remainingAttackTime;

	/**
	 * Method that simulates the defending behavior of a Unit
	 *  
	 * @param attacker
	 * 			The Unit that attacks this Unit
	 * @return Returns true when the Unit defends successfully, false when otherwise
	 * 		| if (! this.dodge(attacker))
	 *	    | 	then if (!this.block(attacker))
	 *		|  		then return false;
	 *		| return true;
	 */
	boolean defend(Unit attacker) {
		if (!this.dodge(attacker)) {
			if (!this.block(attacker)) {
				return false;
			}
			this.AwardExperience(20);
			return true;
		}
		this.AwardExperience(20);
		return true;
	}
	/**
	 * Method that simulates the dodge behavior of a Unit.
	 * 
	 * @param attacker
	 * 			The Unit that attacks this Unit
	 * @return Returns true when the Unit dodges successfully, and updates the Unit's position
	 * 			to a random position neighboring the unit's current position. Returns false when
	 * 			when the dodge is unsuccessful.
	 * 			| if (random < chance)
	 * 			| then new.getPostion() = this.getPosition() + random jump
	 * 			|  			&& return true
	 * 			| else
	 * 			|	return false
	 */
	boolean dodge(Unit attacker) {
		double chance = 0.20
				* ((double) this.getAgility() / attacker.getAgility());
		double random = Math.random();
		if (random <= chance) {
			int x = 0;
			int y = 0;
			int z = 0;
			Coordinate target = this.getInWorldPosition().sum(centerCube());
			while ((x == 0 && y == 0 && z == 0)
					|| !this.isValidPosition(target)) {
				Random posDecider = new Random();
				x = (posDecider.nextInt(3)) - 1;
				y = (posDecider.nextInt(3)) - 1;
				z = (posDecider.nextInt(3)) - 1;
				target = new Coordinate(x, y, z);
				target = target
						.sum(this.getInWorldPosition().sum(centerCube()));
			}
			try {
				this.setPosition(target);
			} catch (ModelException exc) {
				// should never happen
			}
			return true;
		}
		return false;
	}
	/**
	 * Method that simulates the blocking behavior of a Unit.
	 * 
	 * @param attacker
	 * 			The Unit that attacks this Unit
	 * @return Returns true when the Unit blocks successfully. Returns false when
	 * 			when the dodge is unsuccessful.
	 * 			| if (random < chance)
	 * 			|  	then return true
	 * 			| else
	 * 			|	return false
	 */
	boolean block(Unit attacker) {
		double chance = 0.25
				* ((double) (this.getStrength() + this.getAgility())
						/ (attacker.getAgility() + attacker.getStrength()));
		double random = Math.random();
		return (random < chance);
	}

	/**
	 * Changes the hitpoints of the victim due to an attack
	 * 
	 * @param victim
	 * 			The Unit to which damage is dealt.
	 * @post The victim's hitpoints are lowered with the attacker's strength / 10 
	 * 		| new.victim.getHitpoints = victim.getHitpoints - this.getStrength() / 10
	 */
	void doesDamage() {
		this.getVictim().setHitpoints(
				this.getVictim().getHitpoints() - this.getStrength() / 10);
	}

	/**
	 * Method that orients the attacking unit with the defending unit
	 * 
	 * @param defender
	 * 			The unit that this unit is attacking
	 * @post the Units will face each other
	 * 		| new.getOrientation == new.defender.getOrientation + Math.PI
	 */
	void orientWith(Unit defender) {
		this.setOrientation(
				(float) Math.atan2(
						defender.getPosition().getY() - this.getPosition()
								.getY(),
				(defender.getPosition().getX() - this.getPosition().getX())));
		defender.setOrientation(
				(float) Math.atan2(
						this.getPosition().getY() - defender.getPosition()
								.getY(),
				(this.getPosition().getX() - defender.getPosition().getX())));
	}

	/**
	 * Symbolic constant that contains the time it takes to conduct an attack
	 */
	public static double attackTime = 1.0;

	// Resting (defensive) //

	/**
	 * Method that initiates the unit is resting.
	 * 
	 * @post The unit will be in the Resting state
	 * 		| new.getActivity == Activity.RESTING
	 * @post the time the unit is resting will be 0
	 * 		| new.getTimeResting == 0
	 * @throws ModelException 
	 * 			the unit is executing another activity
	 * 		|	(this.getActivity() == Activity.ATTACKING
			|	|| this.getActivity() == Activity.MOVING
			|	|| this.getActivity() == Activity.SPRINTING
			|	|| this.getActivity() == Activity.DEFENDING)
	 */
	public void rest() throws ModelException {
		if (this.getActivity() != Activity.DEFENDING
				&& this.getActivity() != Activity.ATTACKING
				&& this.getActivity() != Activity.MOVING
				&& this.getActivity() != Activity.SPRINTING) {
			this.setActivity(Activity.RESTING);
			this.setTimeResting(0.0);
		} else
			throw new ModelException("Unit not ready to rest");
	}
	/**
	 * Method that governs the resting process
	 * 
	 * @param DeltaT
	 * 			the time-interval used in advanceTime()
	 * @post The time since last rest will be set to 0
	 * 		| new.getTimeSinceLastRest == 0
	 * @post The unit will gain the appropriate amount of hitpoints, if he is resting
	 * 			for at least the time it takes to gain one hit point, if able,
	 * 			then it will gain the appropriate amount of stamina, if he is resting
	 * 			for at least the time it takes to gain one hit point, if able
	 * 		| new.getHitpoints == this.getHitpoints + (this.getToughness() / 40) * DeltaT
	 * 		| OR new.getStamina == this.getStamina() + (this.getToughness() * DeltaT) / 20
	 * @effect If the unit can't gain any hitpoints or stamina, the unit stops resting
	 * 		| this.stop resting
	 * @throws ModelException
	 * 			the unit is not in RESTING-state
	 * 		|	(this.getActivity() != Activity.RESTING)
	*/
	void resting(double DeltaT) throws ModelException {
		if ((this.getActivity() != Activity.RESTING))
			throw new ModelException("Unit isn't in a resting state");
		this.setTimeSinceLastRest(0.0);
		double timeBefore = this.getTimeResting();
		this.setTimeResting(this.getTimeResting() + DeltaT);
		double timeAfter = this.getTimeResting();

		if (this.getHitpoints() + (this.getToughness() / 40) * DeltaT < this
				.maxSecondaryAttribute()) {
			if (timeBefore < this.timeToRecoverOneHP()
					&& timeAfter >= this.timeToRecoverOneHP())
				this.setHitpoints(this.getHitpoints() + 1);
			else if (this.getTimeResting() >= this.timeToRecoverOneHP())
				this.setHitpoints((this.getHitpoints()
						+ ((this.getToughness() * DeltaT) / 40)));
		} else if (this.getStamina()
				+ (this.getToughness() / 20) * DeltaT < this
						.maxSecondaryAttribute()) {
			this.setHitpoints(this.maxSecondaryAttribute());
			if (timeBefore < this.timeToRecoverOneStamina()
					&& timeAfter >= this.timeToRecoverOneStamina())
				this.setStamina(this.getStamina() + 1);
			else if (this.getTimeResting() >= this.timeToRecoverOneStamina())
				this.setStamina((this.getStamina()
						+ ((this.getToughness() * DeltaT) / 20)));
		} else {
			this.setHitpoints(this.maxSecondaryAttribute());
			this.setStamina(this.maxSecondaryAttribute());
			this.stopResting();
		}
	}
	/**
	 * Method that stops a unit from resting.
	 * 
	 * @post The unit will be in the Idle state 
	 * 		| this.setActivity(Activity.IDLE)
	 * @post The unit's time since last rest will be reset to 0
	 * 		| new.getTimeSinceLastRest == 0
	 */
	public void stopResting() {
		this.setTimeSinceLastRest(0);
		this.setActivity(Activity.IDLE);
	}
	/**
	 * Method that gets the time the Unit needs to rest in order to gain 1 Hit-point.
	 */
	double timeToRecoverOneHP() {
		return (40.0 / this.getToughness());
	}
	/**
	 * Method that gets the time the Unit needs to rest in order to gain 1 Stamina.
	 */
	double timeToRecoverOneStamina() {
		return (20.0 / this.getToughness());
	}
	/**
	 * Method that returns the time the Unit needs to rest.
	 */
	@Basic
	public double getTimeResting() {
		return timeResting;
	}
	/**
	 * Method that sets the time the Unit is resting.
	 */
	void setTimeResting(double timeResting) {
		this.timeResting = timeResting;
	}
	/**
	 * Variable registering the time a Unit is resting.
	 */
	private double timeResting = 0.0;
	/**
	 * Return the time since the Unit last had a rest.
	 */
	@Basic
	public double getTimeSinceLastRest() {
		return timeSinceLastRest;
	}
	/**
	 * Method that sets the time since the Unit last had a rest.
	 * 
	 */
	void setTimeSinceLastRest(double timeSinceLastRest) {
		this.timeSinceLastRest = timeSinceLastRest;
	}
	/**
	 * Variable registering the time since the Unit last had a rest.
	 */
	private double timeSinceLastRest = 0;

	// Default Behavior (defensive) //

	/**
	 * Method that sets the unit's default behavior.
	 * 
	 * @param flag
	 * 			The flag to set the unit's default behavior state to
	 * @post | new.activeDefaultBehaviour == flag
	 * 
	 * @throws ModelException
	 * 			the unit isn't Idle and wants to engage in
	 * 			default behavior
	 * 		 | this.getActivity != Idle
	 */
	public void setDefaultBehavior(boolean flag) throws ModelException {
		if (flag) {
			if (this.getActivity() == Activity.IDLE) {
				this.defaultBehavior = true;
			} else
				throw new ModelException("Unit isn't Idle!");
		} else
			this.defaultBehavior = false;
	}
	/**
	 * Method that governs a Unit during default behavior
	 * 
	 * @effect If the unit is Idle, this method will choose an activity at random
	 * 			and executes that activity for that unit
	 * 		| this.work
	 * 		| || this.rest
	 * 		| || this.moveTo(random,random,random)
	 * @throws ModelException
	 * 			The Unit isn't executing default behavior
	 * 		|	(! this.getDefaultBehavior())
	 */
	void doDefaultBehavior() throws ModelException {
		if (!this.getDefaultBehavior())
			throw new ModelException("Unit isn't executing default behavior");
		if (this.getActivity() == Activity.IDLE) {
			Random random = new Random();
			int decider = random.nextInt(3);
			if (decider == 0) {
				rest();
			} else if (decider == 1) {
				work();
			} else {
				int x = random.nextInt(50);
				int y = random.nextInt(50);
				int z = random.nextInt(50);
				try {
					moveTo(x, y, z);
				} catch (Exception e) {
					// shouldn't happen
				}
			}
		}
	}
	/**
	 * Method that stops a Unit from executing default behavior.
	 * 
	 * @effect sets the unit's default behavior state to false
	 * 		| this.setDefaultBehavior(false)
	 * @post the unit's unit's default behavior state will be false
	 * 		| new.getDefaultBehavior == false
	 */
	void stopDefaultBehavior() {
		try {
			this.setDefaultBehavior(false);
		} catch (ModelException e) {
			// shouldn't happen
		}
	}

	/**
	 * Return the flag indicating whether the unit is executing default
	 * behavior
	 */
	@Basic
	public boolean getDefaultBehavior() {
		return this.defaultBehavior;
	}
	/**
	 * flag registering whether a Unit is executing default behavior.
	*/
	private boolean defaultBehavior = false;

	// experience //

	void AwardExperience(int experience) {
		this.setTotalExperience(this.getTotalExperience() + experience);
		this.setCountExp(this.getCountExp() + experience);
	}

	/**
	 * Method that checks whether a primary attribute should be improved.
	 */
	public boolean shouldImproveTrait() {
		return (this.getCountExp() > 10);
	}
	/**
	 * Method that improves one of the Units primary attributes.
	 */
	public void improveTrait() {
		while (shouldImproveTrait()) {
			Random random = new Random();
			int decider = random.nextInt(3);
			if (decider == 0) {
				setAgility(this.getAgility() + 1);
			} else if (decider == 1) {
				setStrength(this.getStrength() + 1);
			} else {
				setToughness(this.getToughness() + 1);
			}
			this.setCountExp(getCountExp() - 10);
		}
	}

	public int getCountExp() {
		return countExp;
	}

	public void setCountExp(int countExp) {
		this.countExp = countExp;
	}
	private int countExp = 0;

	/**
	 * Return the current value of the Units experience.
	 */
	public int getTotalExperience() {
		return this.totalExperience;
	}

	/**
	 * Set the value of the Units experience.
	 */
	void setTotalExperience(int value) {
		this.totalExperience = value;
	}

	/**
	 * Variable registering the experience of the Unit.
	 */
	public int totalExperience;

}
