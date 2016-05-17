/**
 * 
 */
package hillbillies.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class describing a hillbillie faction of units
 * 
 * @invar  Each Faction can have its World as World.
 *       | canHaveAsWorld(this.getWorld())
 * @invar   Each Fction must have proper Units.
 *        | hasProperUnits()
 * @invar  Each Faction can have its Name as Name.
 *       | canHaveAsName(this.getName())
 * @invar  Each Faction can have its Scheduler as Scheduler.
 *       | canHaveAsScheduler(this.getScheduler())
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class Faction {
	/**
	 * Initializes a Faction with the given name in the given world.
	 * @param name
	 * 			the name of the faction
	 * @param world
	 * 			the world where the faction needs to be created.
	 * @throws ModelException 
	 */
	public Faction(String name, World world) {
		this.world = world;
		this.name = name;
		try {
			this.setScheduler(new Scheduler(this));
		} catch (ModelException e) {
			// shouldn't happen
		}
	}

	// Name //

	/**
	 * Return the Name of this Faction.
	 */
	@Basic
	@Raw
	@Immutable
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether this Faction can have the given Name as its Name.
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
	@Raw
	public boolean canHaveAsName(String name) {
		if (name == null || name.length() == 0)
			return false;
		Matcher fullName = validCharacters.matcher(name);
		boolean fullNameCorrect = fullName.matches();
		Matcher firstLetter = upperCase.matcher(name.substring(0, 1));
		boolean firstLetterCorrect = firstLetter.matches();
		return (fullNameCorrect && firstLetterCorrect && name.length() >= 2);
	}

	/**
	 * Variable registering the Name of this Faction.
	 */
	private final String name;

	/**
	 * Pattern containing the set of valid characters for the first letter of Unit names.
	 */
	private static final Pattern upperCase = Pattern.compile("[A-Z]");
	/**
	 * Pattern containing the set of valid characters for Unit names.
	 */
	private static final Pattern validCharacters = Pattern
			.compile("[[a-zA-Z][\"][\'][\\s]]*");

	// World //

	/**
	 * Return the World of this Faction.
	 */
	@Basic
	@Raw
	@Immutable
	public World getWorld() {
		return this.world;
	}

	/**
	 * Check whether this Faction can have the given World as its World.
	 *  
	 * @param  world
	 *         The World to check.
	 * @return 
	 *       | result == (world != null)
	*/
	@Raw
	public static boolean canHaveAsWorld(World world) {
		return (world != null);
	}

	/**
	 * Variable registering the World of this Faction.
	 */
	private final World world;

	// Units //

	/**
	 * @return	the Units of this faction.
	 */
	public Set<Unit> getUnitSet() {
		return this.Units;
	}

	/**
	 * Check whether this Faction has the given Unit as one of its
	 * Units.
	 * 
	 * @param  Unit
	 *         The Unit to check.
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(@Raw Unit Unit) {
		return Units.contains(Unit);
	}

	/**
	 * Check whether this Faction can have the given Unit
	 * as one of its Units.
	 * 
	 * @param  unit
	 *         The Unit to check.
	 * @return True if and only if the given Unit is effective
	 *         and that unit is a valid Unit for a Faction.
	 *       | result ==
	 *       |   (unit != null) &&
	 *       |   unit.isValidFaction(this)
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit) {
		return (unit != null) && (unit.canHaveAsFaction(this));
	}

	/**
	 * Check whether this Faction has proper Units attached to it.
	 * 
	 * @return True if and only if this Faction can have each of the
	 *         Units attached to it as one of its Units,
	 *         and if each of these Units references this Faction as
	 *         the Faction to which they are attached.
	 *       | for each Unit in Unit:
	 *       |   if (hasAsUnit(Unit))
	 *       |     then canHaveAsUnit(Unit) &&
	 *       |          (Unit.getFaction() == this)
	 */
	public boolean hasProperUnits() {
		for (Unit unit : Units) {
			if (!canHaveAsUnit(unit))
				return false;
			if (unit.getFaction() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Units associated with this Faction.
	 *
	 * @return  The total number of Units collected in this Faction.
	 *        | result ==
	 *        |   card({Unit:Unit | hasAsUnit({Unit)})
	 */
	public int getNbUnits() {
		return Units.size();
	}

	/**
	 * Add the given Unit to the set of Units of this Faction.
	 * 
	 * @param  Unit
	 *         The Unit to be added.
	 * @pre    The given Unit is effective and already references
	 *         this Faction.
	 *       | (Unit != null) && (Unit.getFaction() == this)
	 * @post   This Faction has the given Unit as one of its Units.
	 *       | new.hasAsUnit(Unit)
	 */
	public void addUnit(@Raw Unit Unit) {
		assert (Unit != null) && (Unit.getFaction() == this);
		Units.add(Unit);
	}

	/**
	 * Remove the given Unit from the set of Units of this Faction.
	 * 
	 * @param  Unit
	 *         The Unit to be removed.
	 * @pre    This Faction has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any Faction.
	 *       | this.hasAsUnit(Unit) &&
	 *       | (Unit.getFaction() == null)
	 * @post   This Faction no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(Unit)
	 */
	@Raw
	public void removeUnit(Unit Unit) {
		assert this.hasAsUnit(Unit) && (Unit.getFaction() == null);
		Units.remove(Unit);
	}

	/**
	 * Variable referencing a set collecting all the Units
	 * of this Fction.
	 * 
	 * @invar  The referenced set is effective.
	 *       | Units != null
	 * @invar  Each Unit registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Unit in Units:
	 *       |   ( (Unit != null) &&
	 *       |     (! Unit.isTerminated()) )
	 */
	private final Set<Unit> Units = new HashSet<Unit>();

	// Scheduler //

	void setScheduler(Scheduler scheduler) {
		if (canHaveAsScheduler(scheduler))
			this.scheduler = scheduler;
	}

	/**
	 * Return the Scheduler of this Faction.
	 */
	@Basic
	@Raw
	@Immutable
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * Check whether this Faction can have the given Scheduler as its Scheduler.
	 *  
	 * @param  scheduler
	 *         The Scheduler to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return false;
	}

	/**
	 * Variable registering the Scheduler of this Faction.
	 */
	private Scheduler scheduler;

	// Overrides from Object //

	@Override
	public String toString() {
		return ("Name: " + this.getName() + "Members: " + this.getNbUnits());
	}
}
