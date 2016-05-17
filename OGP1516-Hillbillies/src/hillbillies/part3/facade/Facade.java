/**
 * 
 */
package hillbillies.part3.facade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import hillbillies.model.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.programs.ITaskFactory;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class Facade implements IFacade{

	public Facade(){
		
	}
	/* (non-Javadoc)
	 * @see hillbillies.part2.facade.IFacade#createWorld(int[][][], hillbillies.part2.listener.TerrainChangeListener)
	 */
	@Override
	public World createWorld(int[][][] terrainTypes,
			TerrainChangeListener modelListener) throws ModelException {
		Terrain[][][] features = new Terrain[terrainTypes.length][terrainTypes[0].length][terrainTypes[0][0].length];
		for (int indexX = 0; indexX < features.length; indexX++)
			for (int indexY = 0; indexY < features[indexX].length; indexY++)
				for (int indexZ = 0; indexZ < features[indexX][indexY].length; indexZ++) {
					if (terrainTypes[indexX][indexY][indexZ] == 0)
						features[indexX][indexY][indexZ] = Terrain.AIR;
					else if (terrainTypes[indexX][indexY][indexZ] == 1)
						features[indexX][indexY][indexZ] = Terrain.ROCK;
					else if (terrainTypes[indexX][indexY][indexZ] == 2)
						features[indexX][indexY][indexZ] = Terrain.TREE;
					else if (terrainTypes[indexX][indexY][indexZ] == 3)
						features[indexX][indexY][indexZ] = Terrain.WORKSHOP;
					else
						throw new ModelException("Terrain type not existent");
				}
		return new World(features, modelListener);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part2.facade.IFacade#getNbCubesX(hillbillies.model.World)
	 */
	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getDimension()[0];
	}

	/* (non-Javadoc)
	 * @see hillbillies.part2.facade.IFacade#getNbCubesY(hillbillies.model.World)
	 */
	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getDimension()[1];
	}

	/* (non-Javadoc)
	 * @see hillbillies.part2.facade.IFacade#getNbCubesZ(hillbillies.model.World)
	 */
	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getDimension()[2];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#advanceTime(hillbillies.model.World,
	 * double)
	 */
	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		world.advanceTime(dt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#getCubeType(hillbillies.model.World,
	 * int, int, int)
	 */
	@Override
	public int getCubeType(World world, int x, int y, int z)
			throws ModelException {
		Terrain theTerrain = world.getTerrainAt(new Coordinate(x,y,z));
		if (theTerrain == Terrain.AIR)
			return 0;
		else if (theTerrain == Terrain.WORKSHOP)
			return 3;
		else if (theTerrain == Terrain.ROCK)
			return 1;
		else if (theTerrain == Terrain.TREE)
			return 2;
		else
			throw new ModelException("Terrain type not existent");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#setCubeType(hillbillies.model.World,
	 * int, int, int, int)
	 */
	@Override
	public void setCubeType(World world, int x, int y, int z, int value)
			throws ModelException {
		if (value == 0)
			world.setTerrainAt(new Coordinate(x,y,z), Terrain.AIR);
		else if (value == 1)
			world.setTerrainAt(new Coordinate(x,y,z), Terrain.ROCK);
		else if (value == 2)
			world.setTerrainAt(new Coordinate(x,y,z), Terrain.TREE);
		else if (value == 3)
			world.setTerrainAt(new Coordinate(x,y,z), Terrain.WORKSHOP);
		else
			throw new ModelException("Terrain type not existent");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#isSolidConnectedToBorder(hillbillies.
	 * model.World, int, int, int)
	 */
	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z)
			throws ModelException {
		world.updateAlgorithm();
		return world.getAlgorithm().isSolidConnectedToBorder(x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#spawnUnit(hillbillies.model.World,
	 * boolean)
	 */
	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior)
			throws ModelException {
		return world.spawnUnit(enableDefaultBehavior);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#addUnit(hillbillies.model.Unit,
	 * hillbillies.model.World)
	 */
	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		world.addUnit(unit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#getUnits(hillbillies.model.World)
	 */
	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getUnitSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#isCarryingLog(hillbillies.model.Unit)
	 */
	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return (unit.getObjectCarried() instanceof Log);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#isCarryingBoulder(hillbillies.model.
	 * Unit)
	 */
	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return (unit.getObjectCarried() instanceof Boulder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#isAlive(hillbillies.model.Unit)
	 */
	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return unit.getHitpoints() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#getExperiencePoints(hillbillies.model.
	 * Unit)
	 */
	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return unit.getTotalExperience();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#workAt(hillbillies.model.Unit, int,
	 * int, int)
	 */
	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		unit.workAt(new Coordinate(x,y,z));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#getFaction(hillbillies.model.Unit)
	 */
	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		return unit.getFaction();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#getUnitsOfFaction(hillbillies.model.
	 * Faction)
	 */
	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.getUnitSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#getActiveFactions(hillbillies.model.
	 * World)
	 */
	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		List<Faction> theList = world.getFactionList();
		Set<Faction> theSet = new HashSet<>();
		theSet.addAll(theList);
		return theSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#getPosition(hillbillies.model.Boulder)
	 */
	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		double[] result = {0,0,0};
		result[0] = boulder.getPosition().getX();
		result[1] = boulder.getPosition().getY();
		result[2] = boulder.getPosition().getZ();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part2.facade.IFacade#getBoulders(hillbillies.model.World)
	 */
	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.getBoulderSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#getPosition(hillbillies.model.Log)
	 */
	@Override
	public double[] getPosition(Log log) throws ModelException {
		double[] result = {0,0,0};
		result[0] = log.getPosition().getX();
		result[1] = log.getPosition().getY();
		result[2] = log.getPosition().getZ();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part2.facade.IFacade#getLogs(hillbillies.model.World)
	 */
	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.getLogSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#createUnit(java.lang.String, int[],
	 * int, int, int, int, boolean)
	 */
	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight,
			int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		return new Unit(name, initialPosition, weight, agility, strength,
				toughness, enableDefaultBehavior);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#getPosition(hillbillies.model.Unit)
	 */
	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		double[] result = {0, 0, 0};
		result[0] = unit.getPosition().getX();
		result[1] = unit.getPosition().getY();
		result[2] = unit.getPosition().getZ();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getCubeCoordinate(hillbillies.model.
	 * Unit)
	 */
	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		int[] result = {0, 0, 0};
		result[0] = (int) unit.getInWorldPosition().getX();
		result[1] = (int) unit.getInWorldPosition().getY();
		result[2] = (int) unit.getInWorldPosition().getZ();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#getName(hillbillies.model.Unit)
	 */
	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#setName(hillbillies.model.Unit,
	 * java.lang.String)
	 */
	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		unit.setName(newName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#getWeight(hillbillies.model.Unit)
	 */
	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#setWeight(hillbillies.model.Unit,
	 * int)
	 */
	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#getStrength(hillbillies.model.Unit)
	 */
	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#setStrength(hillbillies.model.Unit,
	 * int)
	 */
	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#getAgility(hillbillies.model.Unit)
	 */
	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#setAgility(hillbillies.model.Unit,
	 * int)
	 */
	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getToughness(hillbillies.model.Unit)
	 */
	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#setToughness(hillbillies.model.Unit,
	 * int)
	 */
	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getMaxHitPoints(hillbillies.model.Unit)
	 */
	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return (int) unit.maxSecondaryAttribute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getCurrentHitPoints(hillbillies.model.
	 * Unit)
	 */
	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return (int) Math.floor(unit.getHitpoints());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getMaxStaminaPoints(hillbillies.model.
	 * Unit)
	 */
	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return (int) unit.maxSecondaryAttribute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getCurrentStaminaPoints(hillbillies.
	 * model.Unit)
	 */
	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return (int) Math.floor(unit.getStamina());
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#advanceTime(hillbillies.model.Unit,
	 * double)
	 */
	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		unit.advanceTime(dt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#moveToAdjacent(hillbillies.model.Unit,
	 * int, int, int)
	 */
	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz)
			throws ModelException {
		unit.moveToAdjacent(dx, dy, dz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getCurrentSpeed(hillbillies.model.Unit)
	 */
	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getCurrentSpeed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#isMoving(hillbillies.model.Unit)
	 */
	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.MOVING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#startSprinting(hillbillies.model.Unit)
	 */
	@Override
	public void startSprinting(Unit unit) throws ModelException {
		unit.startSprinting();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#stopSprinting(hillbillies.model.Unit)
	 */
	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		unit.stopSprinting();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#isSprinting(hillbillies.model.Unit)
	 */
	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.SPRINTING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#getOrientation(hillbillies.model.Unit)
	 */
	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#moveTo(hillbillies.model.Unit,
	 * int[])
	 */
	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		unit.moveTo(cube[0], cube[1], cube[2]);;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#work(hillbillies.model.Unit)
	 */
	@Override
	public void work(Unit unit) throws ModelException {
		unit.workAt(unit.getInWorldPosition());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#isWorking(hillbillies.model.Unit)
	 */
	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.WORKING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#fight(hillbillies.model.Unit,
	 * hillbillies.model.Unit)
	 */
	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		attacker.attack(defender);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#isAttacking(hillbillies.model.Unit)
	 */
	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.ATTACKING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#rest(hillbillies.model.Unit)
	 */
	@Override
	public void rest(Unit unit) throws ModelException {
		unit.rest();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hillbillies.part1.facade.IFacade#isResting(hillbillies.model.Unit)
	 */
	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return (unit.getActivity() == Activity.RESTING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#setDefaultBehaviorEnabled(hillbillies.
	 * model.Unit, boolean)
	 */
	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value)
			throws ModelException {
		unit.setDefaultBehavior(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hillbillies.part1.facade.IFacade#isDefaultBehaviorEnabled(hillbillies.
	 * model.Unit)
	 */
	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.getDefaultBehavior();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#createTaskFactory()
	 */
	@Override
	public ITaskFactory<?, ?, Task> createTaskFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#isWellFormed(hillbillies.part3.facade.Task)
	 */
	@Override
	public boolean isWellFormed(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getScheduler(hillbillies.model.Faction)
	 */
	@Override
	public Scheduler getScheduler(Faction faction) throws ModelException {
		return faction.getScheduler();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#schedule(hillbillies.part3.facade.Scheduler, hillbillies.part3.facade.Task)
	 */
	@Override
	public void schedule(Scheduler scheduler, Task task) throws ModelException {
		scheduler.addTask(task);
		
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#replace(hillbillies.part3.facade.Scheduler, hillbillies.part3.facade.Task, hillbillies.part3.facade.Task)
	 */
	@Override
	public void replace(Scheduler scheduler, Task original, Task replacement)
			throws ModelException {
		scheduler.replace(original, replacement);
		
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#areTasksPartOf(hillbillies.part3.facade.Scheduler, java.util.Collection)
	 */
	@Override
	public boolean areTasksPartOf(Scheduler scheduler, Collection<Task> tasks)
			throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getAllTasksIterator(hillbillies.part3.facade.Scheduler)
	 */
	@Override
	public Iterator<Task> getAllTasksIterator(Scheduler scheduler)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getSchedulersForTask(hillbillies.part3.facade.Task)
	 */
	@Override
	public Set<Scheduler> getSchedulersForTask(Task task)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getAssignedUnit(hillbillies.part3.facade.Task)
	 */
	@Override
	public Unit getAssignedUnit(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getAssignedTask(hillbillies.model.Unit)
	 */
	@Override
	public Task getAssignedTask(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getName(hillbillies.part3.facade.Task)
	 */
	@Override
	public String getName(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.facade.IFacade#getPriority(hillbillies.part3.facade.Task)
	 */
	@Override
	public int getPriority(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}


}
