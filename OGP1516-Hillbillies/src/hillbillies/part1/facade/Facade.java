/**
 * 
 */
package hillbillies.part1.facade;

import hillbillies.model.Activity;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class Facade implements IFacade {

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#createUnit(java.lang.String, int[], int, int, int, int, boolean)
	 */
	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		return new Unit(name,initialPosition,weight, agility,  strength,  toughness,
			enableDefaultBehavior);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getPosition(hillbillies.model.Unit)
	 */
	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		double[] result = {0,0,0};
		result[0] = unit.getPosition().getX();
		result[1] = unit.getPosition().getY();
		result[2] = unit.getPosition().getZ();
		return result;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getCubeCoordinate(hillbillies.model.Unit)
	 */
	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		int [] result = {0,0,0};
		result[0] = (int) unit.getInWorldPosition().getX();
		result[1] = (int) unit.getInWorldPosition().getY();
		result[2] = (int) unit.getInWorldPosition().getZ();
		return result;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getName(hillbilliesmodel.Unit)
	 */
	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#setName(hillbillies.model.Unit, java.lang.String)
	 */
	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		unit.setName(newName);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getWeight(hillbillies.model.Unit)
	 */
	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#setWeight(hillbillies.model.Unit, int)
	 */
	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getStrength(hillbillies.model.Unit)
	 */
	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#setStrength(hillbillies.model.Unit, int)
	 */
	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getAgility(hillbillies.model.Unit)
	 */
	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#setAgility(hillbillies.model.Unit, int)
	 */
	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getToughness(hillbillies.model.Unit)
	 */
	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#setToughness(hillbillies.model.Unit, int)
	 */
	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getMaxHitPoints(hillbillies.model.Unit)
	 */
	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return (int) unit.maxSecondaryAttribute();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getCurrentHitPoints(hillbillies.model.Unit)
	 */
	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return (int) Math.floor(unit.getHitpoints());
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getMaxStaminaPoints(hillbillies.model.Unit)
	 */
	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return (int) unit.maxSecondaryAttribute();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getCurrentStaminaPoints(hillbillies.model.Unit)
	 */
	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return (int) Math.floor(unit.getStamina());
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#advanceTime(hillbillies.model.Unit, double)
	 */
	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		unit.advanceTime(dt);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#moveToAdjacent(hillbillies.model.Unit, int, int, int)
	 */
	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		unit.moveToAdjacent(dx, dy, dz);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getCurrentSpeed(hillbillies.model.Unit)
	 */
	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getCurrentSpeed();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#isMoving(hillbillies.model.Unit)
	 */
	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.MOVING);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#startSprinting(hillbillies.model.Unit)
	 */
	@Override
	public void startSprinting(Unit unit) throws ModelException {
		unit.startSprinting();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#stopSprinting(hillbillies.model.Unit)
	 */
	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		unit.stopSprinting();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#isSprinting(hillbillies.model.Unit)
	 */
	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.SPRINTING);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#getOrientation(hillbillies.model.Unit)
	 */
	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#moveTo(hillbillies.model.Unit, int[])
	 */
	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		unit.moveTo(cube[0], cube[1], cube[2]);;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#work(hillbillies.model.Unit)
	 */
	@Override
	public void work(Unit unit) throws ModelException {
		unit.work();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#isWorking(hillbillies.model.Unit)
	 */
	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.WORKING);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#fight(hillbillies.model.Unit, hillbillies.model.Unit)
	 */
	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		attacker.attack(defender);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#isAttacking(hillbillies.model.Unit)
	 */
	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.ATTACKING);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#rest(hillbillies.model.Unit)
	 */
	@Override
	public void rest(Unit unit) throws ModelException {
		unit.rest();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#isResting(hillbillies.model.Unit)
	 */
	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.RESTING);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#setDefaultBehaviorEnabled(hillbillies.model.Unit, boolean)
	 */
	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		unit.setDefaultBehavior(value);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part1.facade.IFacade#isDefaultBehaviorEnabled(hillbillies.model.Unit)
	 */
	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.getDefaultBehavior();
	}

}
