
package hillbillies.model;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.ranges.RangeException;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 *	A class describing the Hillbillie Unit
 *
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
 * *      	| isValidOrientation(getOrientation())
 * @invar The position of each Unit must be a valid position for any Unit.
 *        	| isValidPosition(getPosition())
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
	public Unit(String name, int[] position, int weight, int agility,
			int strength, int toughness, boolean enableDefaultBehavior)
					throws ModelException {
		this.setAgility(agility);
		this.setStrength(strength);
		this.setToughness(toughness);
		this.setWeight(weight);
		Coordinate newPosition = new Coordinate(position[0]+0.5,position[1]+0.5, position[2]+0.5);
		this.setPosition(newPosition);
		this.setName(name);
		this.setOrientation((float) (Math.PI/2));
		this.defaultBehaviour = enableDefaultBehavior;

	}

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
	public Coordinate getInWorldPosition() {
		Coordinate inworldposition = new Coordinate(0,0,0);
		inworldposition.setX(this.getPosition().floor().getX());
		inworldposition.setX(this.getPosition().floor().getY());
		inworldposition.setX(this.getPosition().floor().getZ());;
		return inworldposition;
	}

	/**
	 * Set the position of this Unit to the given position.
	 * 
	 * @param  position
	 *         The new position for this Unit.
	 * @post   The position of this new Unit is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws RangeException
	 *         The given position is not a valid position for any
	 *         Unit.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(Coordinate position) throws ModelException {
		if (! position.isValidCoordinate())
			 throw new ModelException("Invalid Position");
		this.position = new Coordinate(position.getX(),position.getY(),position.getZ());
	}

	/**
	 * Variable registering the position of this Unit.
	 */
	private Coordinate position;

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
	 */
	public static boolean isValidName(String name){
		if (name == null)
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
	 * variable containing the pattern of valid characters for the first letter of Unit names.
	 */
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

	// Time control (defensive) //

	public void advanceTime(double deltaT) throws ModelException {
		if (this.getActivity() == Activity.WORKING) {
			this.setRemainingWorkTime(this.getRemainingWorkTime() - deltaT);
			if (this.getRemainingWorkTime() < 0) {
				this.stopWork();
			}
		}
		if (this.getActivity() == Activity.ATTACKING) {
			this.setRemainingAttackTime(this.getRemainingAttackTime() - deltaT);
			if (this.getRemainingAttackTime() < 0) {
				this.stopAttack();
			}
		}
		if (this.getActivity() == Activity.MOVING || this.activity == Activity.SPRINTING) {
			this.updatePosition(deltaT);
		}
		if (this.getActivity() == Activity.RESTING){
			this.resting(deltaT);
		}
	}
	
	// Moving (defensive) //
	
	public void moveToAdjacent(int x, int y, int z) throws ModelException {
		if ((this.getActivity() != Activity.SPRINTING) && (this.getActivity() != Activity.MOVING)){
			this.clearPath();
			this.addToPath(this.getPosition());
		}
		Coordinate displacement = new Coordinate(x,y,z);
		Coordinate goal = displacement.sum(this.getPath().getLast());
		if (goal.isValidCoordinate()) {
			this.addToPath(goal);
			this.setActivity(Activity.MOVING);
		}
		else
			throw new ModelException("invalid move");
		

	}
	
	public void addToPath(Coordinate target){
		this.getPath().addLast(target);
	}
	

	public void clearPath(){
		this.getPath().clear();
	}
	
	
	public LinkedList<Coordinate> getPath(){
		return this.path;
	}
	private LinkedList<Coordinate> path = new LinkedList<>();
	

	
	public void moveTo(int x, int y, int z) throws ModelException{
		if ((this.getActivity() != Activity.MOVING) && (this.getActivity() != Activity.SPRINTING)){
			this.clearPath();
			Coordinate destinationCube = new Coordinate(x,y,z);
			this.setDestination(destinationCube);
			this.addToPath(this.getPosition());
			this.setActivity(Activity.MOVING);
			this.findPath();
			}
	}
	
	public void setDestination(Coordinate destinationCube){
		this.destinationCube = destinationCube;
	}
	public Coordinate getDestinationCube(){
		return this.destinationCube;
	}
	private Coordinate destinationCube;
	
	public void findPath() throws ModelException{
		int x = 0;
		int y = 0;
		int z = 0;
		while ( (int) this.getDestinationCube().getX() != (int) this.getPath().getLast().getX() || 
				(int) this.getDestinationCube().getY() != (int) this.getPath().getLast().getY() ||
				(int) this.getDestinationCube().getZ() != (int) this.getPath().getLast().getZ()){
			if ((int) this.getDestinationCube().getX() > (int) this.getPath().getLast().getX())
				x = 1;
			else if ((int) this.getDestinationCube().getX() < (int) this.getPath().getLast().getX())
				x = -1;
			else
				x = 0;
			if ((int) this.getDestinationCube().getY() > (int) this.getPath().getLast().getY())
				y = 1;
			else if ((int) this.getDestinationCube().getY() < (int) this.getPath().getLast().getY())
				y = -1;
			else
				y = 0;
			if ((int) this.getDestinationCube().getZ() > (int) this.getPath().getLast().getZ())
				z = 1;
			else if ((int) this.getDestinationCube().getZ() < (int) this.getPath().getLast().getZ())
				z = -1;
			else
				z = 0;
		this.moveToAdjacent(x, y, z);
		}
	}

	/**
	 * Method that stops a unit from moving.
	 * 
	 * @post | this.activity == IDLE
	 */
	public void stopMoving(){
		this.setActivity(Activity.IDLE);
	}

	public void startSprinting(){
		if (this.canSprint())
			this.setActivity(Activity.SPRINTING);
	}
	/**
	 * Method that indicates whether a Unit is able to sprint
	 * 
	 * result == 
	 */
 	public boolean canSprint(){
		return (this.getActivity() == Activity.MOVING && this.getStamina() > 0);
	}
	/**
	 * Method that stops a unit from sprinting.
	 * 
	 * @post | this.isSprinting == false
	 */
	public void stopSprinting(){
		this.setActivity(Activity.MOVING);
	}
	
	public double walkingSpeed(int z){
		double walkingSpeed = 0;
		double baseSpeed = 1.5*(this.getAgility()+this.getStrength())/(2*this.getWeight());
		if (z-(int)this.getPath().get(0).getZ() == -1)
			walkingSpeed = 1.2*baseSpeed;
		else if (z-(int)this.getPath().get(0).getZ() == 1)
			walkingSpeed = 0.5*baseSpeed;
		else
			walkingSpeed = baseSpeed;
		return walkingSpeed;
	}
	
	public double getCurrentSpeed(){
		if (this.getActivity() != Activity.SPRINTING && this.getActivity() != Activity.MOVING)
			return 0;
		int targetZ = (int) Math.floor(this.getPath().get(1).getZ());
		if (this.getActivity() == Activity.MOVING)
			return this.walkingSpeed(targetZ);
		else if (this.getActivity() == Activity.SPRINTING)
			return 2*this.walkingSpeed(targetZ);
		else
			return 0;
			
			
	}
	public void updatePosition(double deltaT) throws ModelException{
		if (this.getPath().size() >= 2){
			Coordinate start = this.getPath().get(0);
			Coordinate target = this.getPath().get(1);
			Coordinate direction = start.directionVector(target);
			Coordinate displacement = direction
					.scalarMult(this.getCurrentSpeed() * deltaT);
			if (displacement.length() >= this.remaininglegDistance()) {
				this.setPosition(target);
				this.getPath().remove(0);
				if (this.getPath().size() < 2)
					this.stopMoving();
			} else {
				this.setPosition(this.getPosition().sum(displacement));
				this.setOrientation((float) Math.atan2(direction.getY(),
						direction.getX()));
				}
			
			}
		
		else
			this.stopMoving();
	}
	

	private double remaininglegDistance(){
		Coordinate vector = this.getPath().get(1).difference(this.getPosition());
		return vector.length();
	}
	
	// Activity variable //
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public Activity getActivity(){
		return this.activity;
	}
	private Activity activity = Activity.IDLE;

	// Working (defensive) //
	
	/**
	 * Method that initiates a work task for a Unit.
	 * 
	 * @post | this.isWorking == true
	 * @post | this.getRemainingWorkTime == this.workTime()
	 * @throws ModelException
	 */
  	public void work() throws ModelException {
		this.setRemainingWorkTime(this.workTime());
		this.setActivity(Activity.WORKING);

	}
	/**
	 * Method that stops a work task for a Unit
	 * 
	 * @post | this.isWorking == false
	 */
	public void stopWork() {
		this.setActivity(Activity.IDLE);
	}
	
	/**
	 * Method that computes how long it takes for a unit to finish a work task.
	 * @return 500.0 / this.getStrength()
	 */
	public double workTime() {
		return (500.0d / this.getStrength());
	}

	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The remaining work time of each Unit must be a valid remaining work time for any
	 *         Unit.
	 *       | isValidRemainingWorkTime(getRemainingWorkTime())
	 */

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
	 * @return 
	 *       | result == remainingWorkTime < this.workTime()
	*/
	public boolean isValidRemainingWorkTime(double remainingWorkTime) {
		return (remainingWorkTime <= this.workTime());
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
	public void setRemainingWorkTime(double remainingWorkTime)
			throws ModelException {
		if (!isValidRemainingWorkTime(remainingWorkTime))
			throw new ModelException();
		this.remainingWorkTime = remainingWorkTime;
	}

	/**
	 * Variable registering the remaining work time of this Unit.
	 */
	private double remainingWorkTime;
	

	// Fighting (defensive) //
	
	/**
	 * Method that initiates an attack on another Unit
	 * @param victim
	 * 			The Unit to attack
	 * @throws ModelException
	 */
	public void attack(Unit victim) throws ModelException {
		this.setRemainingAttackTime(this.attackTime());
		this.victim = victim;
		this.setActivity(Activity.ATTACKING);
		victim.setActivity(Activity.DEFENDING);
	}
	
	private Unit victim;
	
	/**
	 * Method that stops an attack of a Unit, inflicting damage 
	 * when the victim doesn't successfully defend
	 * @throws ModelException 
	 */
	public void stopAttack() throws ModelException {
		if (! this.victim.defend(this) && this.getRemainingAttackTime() < 0)
			this.doesDamage(this.victim);
		this.setActivity(Activity.IDLE);
		this.victim = null;
	}
	
	/**
	 * Method that returns the time it takes to conduct an attack
	 * @return 1
	 */
	public double attackTime() {
		return 1;
	}
	
	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The remaining attack time of each Unit must be a valid remaining attack time for any
	 *         Unit.
	 *       | isValidRemainingAttackTime(getRemainingAttackTime())
	 */

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
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidRemainingAttackTime(
			double remainingAttackTime) {
		return false;
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
	public void setRemainingAttackTime(double remainingAttackTime)
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
	 * 			| if (! this.dodge(attacker))
	 *		    | 	then if (!this.block(attacker))
	 *			|  		then return false;
	 *			| return true;
	 * @throws ModelException
	 */
	public boolean defend(Unit attacker) throws ModelException {
		if (! this.dodge(attacker))
			if (! this.block(attacker))
				return false;
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
	 * @throws ModelException
	 */
	public boolean dodge(Unit attacker) throws ModelException {
		double chance = 0.20
				* ((double) this.getAgility() / attacker.getAgility());
		double random = Math.random();
		if (random < chance) {
			//
			return true;
		};
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
	 * @throws ModelException
	 */
	public boolean block(Unit attacker) {
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
	public void doesDamage(Unit victim) {
		victim.setHitpoints(victim.getHitpoints() - this.getStrength() / 10);
	}
	
	/**
	 * Method that orients the attacking unit with the defending unit
	 * @param defender
	 * 			The unit that this unit is attacking
	 * @post the Units will face each other
	 */
	public void orientWith(Unit defender) {
		this.setOrientation((float) Math.atan2(defender.getPosition().getY()-this.getPosition().getY(),
				(defender.getPosition().getX()-this.getPosition().getX())));
		defender.setOrientation((float) Math.atan2(this.getPosition().getY()-defender.getPosition().getY(),
				(this.getPosition().getX()-defender.getPosition().getX())));
	}

	// Resting (defensive) //
	/**
	 * Method that initiates the unit is resting.
	 * 
	 * @post | this.isResting == true
	 */
	public void rest() {
		if (! (this.getActivity() == Activity.DEFENDING))
			this.setActivity(Activity.RESTING);
	}
	/**
	 * Method that governs the resting process
	 * @param DeltaT
	 * 			the time-interval used in advanceTime()
	 * @throws ModelException
	*/
	public void resting(double DeltaT) throws ModelException{
		if ((this.getActivity() != Activity.RESTING))
			throw new ModelException();
		if (this.getHitpoints() < this.maxSecondaryAttribute()){
			this.setHitpoints((int) (this.getHitpoints() + (this.getToughness()/200)*(DeltaT/0.2)));}
		else if (this.getStamina() < this.maxSecondaryAttribute()){
			this.setStamina((int) (this.getStamina() + (this.getToughness()/100)*(DeltaT/0.2)));}
		else {
			this.stopResting();}
	}
	/**
	 * Method that stops a unit from resting.
	 * 
	 * @post | this.isResting == false
	 */
	public void stopResting(){
		this.setActivity(Activity.IDLE);
	}
	
	// Default behavior (defensive) //
	
	/**
	 * Method that initiates the unit is executing defaultbehaviour.
	 * 
	 * @post | this.activeDefaultBehaviour == true
	 */
	public void startDefaultBehaviour() {
		this.setDefaultBehavior(true);
	}
	/**
	 * Method that governs a Unit during defaultbehaviour
	 * @param deltaT
	 * 			the time-interval used in advanceTime()
	 * @throws ModelException
	*/
	public void doDefaultBehaviour(double deltaT) throws ModelException{
		if (this.getDefaultBehavior());
			int defaultsetter = -1;
			//defaultsetter = randomintgenerator(0..2)
			if (defaultsetter == 0)
			resting(deltaT);
			if (defaultsetter == 1)
			work();
			else
			moveTo(-1,-1,-1);
			//three random coordinates
	}

	public void stopDefaultBehaviour() {
		this.setDefaultBehavior(false);
	}
	/**
	 * flag registering whether a Unit is executing defaultbehaviour.
	*/
	public void setDefaultBehavior(boolean flag){
		this.defaultBehaviour = flag;
	}
	public boolean getDefaultBehavior(){
		return this.defaultBehaviour;
	}
	
	private boolean defaultBehaviour = false;

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
	 *       | result == 
	*/
	public static boolean isValidOrientation(float orientation) {
		return (orientation >= 0 && orientation < 2*Math.PI);
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
			this.orientation = (float) (orientation % 2*Math.PI);
	}

	/**
	 * Variable registering the Orientation of this Unit.
	 */
	private float orientation;


}
