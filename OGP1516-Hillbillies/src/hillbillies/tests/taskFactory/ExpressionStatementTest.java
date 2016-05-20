package hillbillies.tests.taskFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Cube;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.TaskFactory;
import hillbillies.model.Terrain;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expression.Boolean_Expression;
import hillbillies.model.statement.*;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;
import hillbillies.part3.programs.SourceLocation;

public class ExpressionStatementTest {

	private static Terrain[][][] theTerrain = new Terrain[50][50][50];
	private Unit unit;
	private Unit closeenemyunit;
	private Unit closefriendunit;
	private Unit farenemyunit;
	private Unit farfriendunit;
	private Statement stat;
	private static World theWorld = new World(theTerrain,
			new TerrainChangeListener() {

				@Override
				public void notifyTerrainChanged(int x, int y, int z) {
					// TODO Auto-generated method stub

				}
			});
	private static Faction theFaction1 = new Faction("Dwarves", theWorld);
	private static Faction theFaction2 = new Faction("Elves", theWorld);
	private SourceLocation source;
	private Coordinate theCoordinate;
	private Cube cube1;
	private Cube cube2;
	private Cube cube3;
	private Cube cube4;
	private Log theLog;
	private Boulder theBoulder;
	private TaskFactory theFactory = new TaskFactory();

	/**
	 * @throws ModelException 
	 * 			Should never happen, it is a legal unit
	 */
	@Before
	public void setUp() throws ModelException {
		unit = new Unit("TestUnit", new int[]{1, 0, 0}, 50, 50, 50, 50, false,
				theWorld, theFaction1);
		closeenemyunit = new Unit("TestUnitA", new int[]{3, 1, 0}, 50, 50, 50,
				50, false, theWorld, theFaction2);
		closefriendunit = new Unit("TestUnitB", new int[]{0, 1, 0}, 50, 50, 50,
				50, false, theWorld, theFaction1);
		farenemyunit = new Unit("TestUnitC", new int[]{9, 3, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction2);
		farfriendunit = new Unit("TestUnitD", new int[]{9, 3, 0}, 50, 50, 50,
				50, false, theWorld, theFaction1);
		theCoordinate = new Coordinate(1, 1, 0);
		cube1 = theWorld.getCubeAt(new Coordinate(1, 0, 0));
		cube2 = theWorld.getCubeAt(new Coordinate(2, 0, 0));
		cube3 = theWorld.getCubeAt(new Coordinate(1, 1, 0));
		cube4 = theWorld.getCubeAt(new Coordinate(2, 1, 0));
		theLog = new Log(new Coordinate(1, 0, 0), theWorld);
		theBoulder = new Boulder(new Coordinate(1, 2, 0), theWorld);
		cube1.addGameObject(theLog);
		cube2.addGameObject(theBoulder);
	}

	// Expressions //

	@Test
	public void testCreateReadVariable() {
		assertEquals(unit, theFactory
				.createReadVariable(unit, "TestUnit", source).evaluate());
		assertEquals(closeenemyunit, theFactory
				.createReadVariable(unit, "TestUnit1", source).evaluate());
	}

	@Test
	public void testCreateIsSolid() {
		assertEquals(true,
				theFactory.createIsSolid(unit, cube3, source).evaluate());
		assertEquals(true,
				theFactory.createIsSolid(unit, cube4, source).evaluate());
		assertEquals(false,
				theFactory.createIsSolid(unit, cube1, source).evaluate());
		assertEquals(false,
				theFactory.createIsSolid(unit, cube2, source).evaluate());
	}

	@Test
	public void testCreateIsPassable() {
		assertEquals(true,
				theFactory.createIsPassable(unit, cube1, source).evaluate());
		assertEquals(true,
				theFactory.createIsPassable(unit, cube2, source).evaluate());
		assertEquals(false,
				theFactory.createIsPassable(unit, cube3, source).evaluate());
		assertEquals(false,
				theFactory.createIsPassable(unit, cube4, source).evaluate());
	}

	@Test
	public void testCreateIsFriend() {
		assertEquals(true, theFactory
				.createIsFriend(unit, closefriendunit, source).evaluate());
		assertEquals(false, theFactory
				.createIsFriend(unit, closeenemyunit, source).evaluate());
	}

	@Test
	public void testCreateIsEnemy() {
		assertEquals(true, theFactory
				.createIsEnemy(unit, closeenemyunit, source).evaluate());
		assertEquals(false, theFactory
				.createIsEnemy(unit, closefriendunit, source).evaluate());
	}

	@Test
	public void testCreateIsAlive() {
		assertEquals(true, theFactory.createIsAlive(unit, source).evaluate());
	}

	@Test
	public void testCreateCarriesItem() {
		assertEquals(false,
				theFactory.createCarriesItem(unit, source).evaluate());
	}

	@Test
	public void testCreateNot() {
		assertEquals(true, theFactory
				.createNot(new Boolean_Expression(false), source).evaluate());
		assertEquals(false, theFactory
				.createNot(new Boolean_Expression(true), source).evaluate());
	}

	@Test
	public void testCreateAnd() {
		assertEquals(true,
				theFactory
						.createAnd(new Boolean_Expression(true),
								new Boolean_Expression(true), source)
						.evaluate());
		assertEquals(false,
				theFactory
						.createAnd(new Boolean_Expression(true),
								new Boolean_Expression(false), source)
						.evaluate());
		assertEquals(false,
				theFactory
						.createAnd(new Boolean_Expression(false),
								new Boolean_Expression(true), source)
						.evaluate());
		assertEquals(false,
				theFactory
						.createAnd(new Boolean_Expression(false),
								new Boolean_Expression(false), source)
						.evaluate());
	}

	@Test
	public void testCreateOr() {
		assertEquals(true,
				theFactory
						.createOr(new Boolean_Expression(true),
								new Boolean_Expression(true), source)
						.evaluate());
		assertEquals(true,
				theFactory
						.createOr(new Boolean_Expression(true),
								new Boolean_Expression(false), source)
						.evaluate());
		assertEquals(true,
				theFactory
						.createOr(new Boolean_Expression(false),
								new Boolean_Expression(true), source)
						.evaluate());
		assertEquals(false,
				theFactory
						.createOr(new Boolean_Expression(false),
								new Boolean_Expression(false), source)
						.evaluate());
	}

	@Test
	public void testCreateHerePosition() {
		assertEquals(theCoordinate,
				theFactory.createHerePosition(unit, source).evaluate());
		assertEquals(theFactory.createPositionOf(unit, source).evaluate(),
				theFactory.createHerePosition(unit, source).evaluate());
	}

	@Test
	public void testCreateLogPosition() {
		assertEquals(cube1.getPlaceInGrid(),
				theFactory.createLogPosition(unit, source).evaluate());
	}

	@Test
	public void testCreateBoulderPosition() {
		assertEquals(cube2.getPlaceInGrid(),
				theFactory.createBoulderPosition(unit, source).evaluate());
	}

	@Test
	public void testCreateWorkshopPosition() {
		assertEquals(cube2.getPlaceInGrid(),
				theFactory.createWorkshopPosition(unit, source).evaluate());
	}

	@Test
	public void testCreateSelectedPosition() {
		assertEquals(theCoordinate, theFactory
				.createSelectedPosition(unit, new Coordinate(1, 1, 0), source)
				.evaluate());
	}

	@Test
	public void testCreateNextToPosition() {
		assertEquals(theCoordinate,
				theFactory
						.createNextToPosition(unit,
								closefriendunit.getInWorldPosition(), source)
						.evaluate());
	}

	@Test
	public void testCreateLiteralPosition() {
		assertEquals(theCoordinate,
				theFactory.createLiteralPosition(1, 1, 0, source).evaluate());
	}

	@Test
	public void testCreateThis() {
		assertEquals(unit, theFactory.createThis(unit, source).evaluate());
		assertEquals(farfriendunit,
				theFactory.createThis(farfriendunit, source).evaluate());
	}

	@Test
	public void testCreateFriend() {
		assertEquals(closefriendunit,
				theFactory.createFriend(unit, source).evaluate());
		assertEquals(unit,
				theFactory.createFriend(closefriendunit, source).evaluate());
	}

	@Test
	public void testCreateEnemy() {
		assertEquals(closeenemyunit,
				theFactory.createEnemy(unit, source).evaluate());
		assertEquals(unit,
				theFactory.createEnemy(closeenemyunit, source).evaluate());

	}

	@Test
	public void testCreateAny() {
		assertEquals(closefriendunit,
				theFactory.createAny(unit, source).evaluate());
		assertEquals(unit,
				theFactory.createAny(closefriendunit, source).evaluate());
	}

	@Test
	public void testCreateTrue() {
		assertEquals(true, theFactory.createTrue(source).evaluate());
	}

	@Test
	public void testCreateFalse() {
		assertEquals(false, theFactory.createFalse(source).evaluate());
	}

	@Test
	public void testCreatePositionOf() {
		assertEquals(theCoordinate,
				theFactory.createPositionOf(unit, source).evaluate());
		assertEquals(new Coordinate(9, 3, 0),
				theFactory.createPositionOf(farenemyunit, source).evaluate());
	}

	// Statements //

	@Test
	public void testCreateAssignment() {
		stat = theFactory.createAssignment("LeVariable",
				new Boolean_Expression(true), source);
		VarTracker theTracker = stat.getTracker()
	}

	@Test
	public void testCreateWhile() {

	}

	@Test
	public void testCreateIf() {

	}

	@Test
	public void testCreateBreak() {

	}

	@Test
	public void testCreatePrint() {

	}

	@Test
	public void testCreateSequence() {

	}

	@Test
	public void testCreateMoveTo() {

	}

	@Test
	public void testCreateWorkAt() {

	}

	@Test
	public void testCreateFollow() {

	}

	@Test
	public void testCreateAttack() {

	}
}
