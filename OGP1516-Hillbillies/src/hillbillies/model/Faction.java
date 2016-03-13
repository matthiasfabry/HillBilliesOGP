/**
 * 
 */
package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class describing a hillbillie faction of units
 * 
 * @invar  Each Faction can have its World as World.
 *       | canHaveAsWorld(this.getWorld())
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class Faction {

	public Faction(World world) {
		this.world = world;
	}

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
	public boolean canHaveAsWorld(World world) {
		return (world != null);
	}

	/**
	 * Variable registering the World of this Faction.
	 */
	private final World world;
	
	// Units //
	
	/** TO BE ADDED TO THE CLASS INVARIANTS
	 * @invar   Each Fction must have proper Units.
	 *        | hasProperUnits()
	 */

	/**
	 * Initialize this new Fction as a non-terminated Fction with 
	 * no Units yet.
	 * 
	 * @post   This new Fction has no Units yet.
	 *       | new.getNbUnits() == 0
	 */
	@Raw
	public Faction() {
	}

	/**
	 * Check whether this Fction has the given Unit as one of its
	 * Units.
	 * 
	 * @param  Unit
	 *         The Unit to check.
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(
			@Raw Unit Unit) {
		return Units.contains(Unit);
	}

	/**
	 * Check whether this Fction can have the given Unit
	 * as one of its Units.
	 * 
	 * @param  Unit
	 *         The Unit to check.
	 * @return True if and only if the given Unit is effective
	 *         and that Unit is a valid Unit for a Fction.
	 *       | result ==
	 *       |   (Unit != null) &&
	 *       |   Unit.isValidFaction(this)
	 */
	@Raw
	public boolean canHaveAsUnit(
			Unit Unit) {
		return (Unit != null)
				&& (Unit.isValidFaction(this));
	}

	/**
	 * Check whether this Fction has proper Units attached to it.
	 * 
	 * @return True if and only if this Fction can have each of the
	 *         Units attached to it as one of its Units,
	 *         and if each of these Units references this Fction as
	 *         the Fction to which they are attached.
	 *       | for each Unit in Unit:
	 *       |   if (hasAsUnit(Unit))
	 *       |     then canHaveAsUnit(Unit) &&
	 *       |          (Unit.getFaction() == this)
	 */
	public boolean hasProperUnits() {
		for (Unit Unit : Units) {
			if (!canHaveAsUnit(Unit))
				return false;
			if (Unit.getFaction() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Units associated with this Fction.
	 *
	 * @return  The total number of Units collected in this Fction.
	 *        | result ==
	 *        |   card({Unit:Unit | hasAsUnit({Unit)})
	 */
	public int getNbUnits() {
		return Units.size();
	}

	/**
	 * Add the given Unit to the set of Units of this Fction.
	 * 
	 * @param  Unit
	 *         The Unit to be added.
	 * @pre    The given Unit is effective and already references
	 *         this Fction.
	 *       | (Unit != null) && (Unit.getFaction() == this)
	 * @post   This Fction has the given Unit as one of its Units.
	 *       | new.hasAsUnit(Unit)
	 */
	public void addUnit(
			@Raw Unit Unit) {
		assert (Unit != null)
				&& (Unit.getFaction() == this);
		Units.add(Unit);
	}

	/**
	 * Remove the given Unit from the set of Units of this Fction.
	 * 
	 * @param  Unit
	 *         The Unit to be removed.
	 * @pre    This Fction has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any Fction.
	 *       | this.hasAsUnit(Unit) &&
	 *       | (Unit.getFaction() == null)
	 * @post   This Fction no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(Unit)
	 */
	@Raw
	public void removeUnit(
			Unit Unit) {
		assert this.hasAsUnit(Unit)
				&& (Unit.getFaction() == null);
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
}
