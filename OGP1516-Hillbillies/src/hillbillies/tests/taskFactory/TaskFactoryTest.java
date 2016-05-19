package hillbillies.tests.taskFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Coordinate;
import hillbillies.model.Cube;
import hillbillies.model.Faction;
import hillbillies.model.TaskFactory;
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
	}

	
	@Test
	public void testCreateReadVariable() {
		assertEquals(unit, TaskFactory.createReadVariable(unit, "TestUnit", source).evaluate());
		assertEquals(closeenemyunit, TaskFactory.createReadVariable(unit, "TestUnit1", source).evaluate());
	}

	@Test
	public void testCreateIsSolid() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateIsPassable() {
		fail("Not yet implemented");
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
	}

	@Test
	public void testCreateLogPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateBoulderPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateWorkshopPosition() {
		fail("Not yet implemented");
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
	}

	@Test
	public void testCreateFriend() {
		assertEquals(closefriendunit, TaskFactory.createFriend(unit, source).evaluate());
	}

	@Test
	public void testCreateEnemy() {
		assertEquals(closeenemyunit, TaskFactory.createEnemy(unit, source).evaluate());
	}

	@Test
	public void testCreateAny() {
		assertEquals(closefriendunit, TaskFactory.createAny(unit, source).evaluate());
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
		assertEquals(new Coordinate(1,1,0), TaskFactory.createPositionOf(unit, source).evaluate());
	}

}
