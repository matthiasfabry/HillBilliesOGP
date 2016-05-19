package hillbillies.tests.taskFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Cube;
import hillbillies.model.Faction;
import hillbillies.model.Grid;
import hillbillies.model.Log;
import hillbillies.model.TaskFactory;
import hillbillies.model.Terrain;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expression.Boolean_Expression;
import hillbillies.model.expression.Expression;
import ogp.framework.util.ModelException;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactoryTest {

	private Unit unit;
	private Unit closeenemyunit;
	private Unit closefriendunit;
	private Unit farenemyunit;
	private Unit farfriendunit;
	private static World theWorld;
	private static Faction theFaction1;
	private static Faction theFaction2;
	private SourceLocation source;
	private Coordinate theCoordinate;
	private Cube cube1;
	private Cube cube2;
	private Cube cube3;
	private Cube cube4;
	private Log theLog;
	private Boulder theBoulder;
	
	
	/**
	 * @throws ModelException 
	 * 			Should never happen, it is a legal unit
	 */
	@Before
	public void setUp() throws ModelException {
		unit = new Unit("TestUnit", new int[]{1, 1, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction1);
		closeenemyunit = new Unit("TestUnit1", new int[]{3, 1, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction2);
		closefriendunit = new Unit("TestUnit2", new int[]{0, 1, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction1);
		farenemyunit = new Unit("TestUnit3", new int[]{9, 3, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction2);
		farfriendunit = new Unit("TestUnit4", new int[]{9, 3, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction1);
		theCoordinate = new Coordinate(1,1,0);
		cube1 = new Cube(new Coordinate(1,1,1), theWorld.getGrid());
		cube2 = new Cube(new Coordinate(1,0,0), theWorld.getGrid());
		cube3 = new Cube(new Coordinate(0,0,1), theWorld.getGrid());
		cube4 = new Cube(new Coordinate(1,0,1), theWorld.getGrid());
		cube1.setTerrain(Terrain.AIR);
		cube2.setTerrain(Terrain.WORKSHOP);
		cube3.setTerrain(Terrain.ROCK);
		cube4.setTerrain(Terrain.TREE);
		theLog =  new Log(new Coordinate(1,1,1), theWorld);
		theBoulder = new Boulder(new Coordinate(1,0,0), theWorld);
		cube1.addGameObject(theLog);
		cube2.addGameObject(theBoulder);
	}

	
	@Test
	public void testCreateReadVariable() {
		assertEquals(unit, TaskFactory.createReadVariable(unit, "TestUnit", source).evaluate());
		assertEquals(closeenemyunit, TaskFactory.createReadVariable(unit, "TestUnit1", source).evaluate());
	}

	@Test
	public void testCreateIsSolid() {
		assertEquals(true, TaskFactory.createIsSolid(unit, cube3, source).evaluate());
		assertEquals(true, TaskFactory.createIsSolid(unit, cube4, source).evaluate());
		assertEquals(false, TaskFactory.createIsSolid(unit, cube1, source).evaluate());
		assertEquals(false, TaskFactory.createIsSolid(unit, cube2, source).evaluate());
	}

	@Test
	public void testCreateIsPassable() {
		assertEquals(true, TaskFactory.createIsPassable(unit, cube1, source).evaluate());
		assertEquals(true, TaskFactory.createIsPassable(unit, cube2, source).evaluate());
		assertEquals(false, TaskFactory.createIsPassable(unit, cube3, source).evaluate());
		assertEquals(false, TaskFactory.createIsPassable(unit, cube4, source).evaluate());
	}

	@Test
	public void testCreateIsFriend() {
		assertEquals(true, TaskFactory.createIsFriend(unit, closefriendunit, source).evaluate());
		assertEquals(false, TaskFactory.createIsFriend(unit, closeenemyunit, source).evaluate());
	}

	@Test
	public void testCreateIsEnemy() {
		assertEquals(true, TaskFactory.createIsEnemy(unit, closeenemyunit, source).evaluate());
		assertEquals(false, TaskFactory.createIsEnemy(unit, closefriendunit, source).evaluate());
	}

	@Test
	public void testCreateIsAlive() {
		assertEquals(true, TaskFactory.createIsAlive(unit, source).evaluate());
	}

	@Test
	public void testCreateCarriesItem() {
		assertEquals(false, TaskFactory.createCarriesItem(unit, source).evaluate());
	}

	@Test
	public void testCreateNot() {
		assertEquals(true, TaskFactory.createNot(new Boolean_Expression(false), source).evaluate());
		assertEquals(false, TaskFactory.createNot(new Boolean_Expression(true), source).evaluate());
	}

	@Test
	public void testCreateAnd() {
		assertEquals(true, TaskFactory.createAnd(new Boolean_Expression(true), new Boolean_Expression(true),
				source).evaluate());
		assertEquals(false, TaskFactory.createAnd(new Boolean_Expression(true), new Boolean_Expression(false),
				source).evaluate());
		assertEquals(false, TaskFactory.createAnd(new Boolean_Expression(false), new Boolean_Expression(true),
				source).evaluate());
		assertEquals(false, TaskFactory.createAnd(new Boolean_Expression(false), new Boolean_Expression(false),
				source).evaluate());
	}

	@Test
	public void testCreateOr() {
		assertEquals(true, TaskFactory.createOr(new Boolean_Expression(true), new Boolean_Expression(true),
						source).evaluate());
		assertEquals(true, TaskFactory.createOr(new Boolean_Expression(true), new Boolean_Expression(false),
				source).evaluate());
		assertEquals(true, TaskFactory.createOr(new Boolean_Expression(false), new Boolean_Expression(true),
				source).evaluate());
		assertEquals(false, TaskFactory.createOr(new Boolean_Expression(false), new Boolean_Expression(false),
				source).evaluate());
	}

	@Test
	public void testCreateHerePosition() {
		assertEquals(theCoordinate, TaskFactory.createHerePosition(unit, source).evaluate());
		assertEquals(TaskFactory.createPositionOf(unit, source).evaluate(), 
				TaskFactory.createHerePosition(unit,source).evaluate());
	}

	@Test
	public void testCreateLogPosition() {
		assertEquals(cube1.getPlaceInGrid(), TaskFactory.createLogPosition(unit, source).evaluate());
	}

	@Test
	public void testCreateBoulderPosition() {
		assertEquals(cube2.getPlaceInGrid(), TaskFactory.createBoulderPosition(unit, source).evaluate());
	}

	@Test
	public void testCreateWorkshopPosition() {
		assertEquals(cube2.getPlaceInGrid(), TaskFactory.createWorkshopPosition(unit, source).evaluate()); 
	}

	@Test
	public void testCreateSelectedPosition() {
		assertEquals(theCoordinate, TaskFactory.createSelectedPosition(unit, new Coordinate (1,1,0), source)
						.evaluate());
	}

	@Test
	public void testCreateNextToPosition() {
		assertEquals(theCoordinate, TaskFactory.createNextToPosition(unit, closefriendunit.getInWorldPosition(),
						source).evaluate());
	}

	@Test
	public void testCreateLiteralPosition() {
		assertEquals(theCoordinate, TaskFactory.createLiteralPosition(1, 1, 0, source).evaluate());
	}

	@Test
	public void testCreateThis() {
		assertEquals(unit, TaskFactory.createThis(unit, source).evaluate());
		assertEquals(farfriendunit, TaskFactory.createThis(farfriendunit, source).evaluate());
	}

	@Test
	public void testCreateFriend() {
		assertEquals(closefriendunit, TaskFactory.createFriend(unit, source).evaluate());
		assertEquals(unit, TaskFactory.createFriend(closefriendunit, source).evaluate());
	}

	@Test
	public void testCreateEnemy() {
		assertEquals(closeenemyunit, TaskFactory.createEnemy(unit, source).evaluate());
		assertEquals(unit, TaskFactory.createEnemy(closeenemyunit, source).evaluate());
		
	}

	@Test
	public void testCreateAny() {
		assertEquals(closefriendunit, TaskFactory.createAny(unit, source).evaluate());
		assertEquals(unit, TaskFactory.createAny(closefriendunit, source).evaluate());
	}

	@Test
	public void testCreateTrue() {
		assertEquals(true, TaskFactory.createTrue(source).evaluate());
	}

	@Test
	public void testCreateFalse() {
		assertEquals(false, TaskFactory.createFalse(source).evaluate()); 
	}

	@Test
	public void testCreatePositionOf() {
		assertEquals(theCoordinate, TaskFactory.createPositionOf(unit, source).evaluate());
		assertEquals(new Coordinate(9,3,0), TaskFactory.createPositionOf(farenemyunit, source).evaluate());
	
	}

}
