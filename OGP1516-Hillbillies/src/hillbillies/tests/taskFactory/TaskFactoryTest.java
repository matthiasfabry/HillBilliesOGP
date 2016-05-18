package hillbillies.tests.taskFactory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Coordinate;
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
	private Unit enemyunit;
	private Unit friendunit;
	private static World theWorld;
	private static Faction theFaction1;
	private static Faction theFaction2;
	private SourceLocation source;
	private Coordinate theCoordinate;
	/**
	 * @throws ModelException 
	 * 			Should never happen, it is a legal unit
	 */
	@Before
	public void setUp() throws ModelException {
		unit = new Unit("TestUnit", new int[]{1, 1, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction1);
		enemyunit = new Unit("TestUnit", new int[]{3, 1, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction2);
		friendunit = new Unit("TestUnit", new int[]{0, 1, 0}, 50, 50, 50, 50,
				false, theWorld, theFaction1);
		theCoordinate = new Coordinate(1,1,0);
	}

	
	@Test
	public void testCreateReadVariable() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testCreateIsEnemy() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateIsAlive() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateCarriesItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNot() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAnd() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOr() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateHerePosition() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNextToPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateLiteralPosition() {
		AssertEquals(theCoordinate, TaskFactory.createLiteralPosition(1,1,0, source).evaluate);
	}

	@Test
	public void testCreateThis() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFriend() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateEnemy() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAny() {
		;
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
		fail("Not yet implemented");
	}

}
