/**
 * 
 */
package hillbillies.tests.unit;

import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;
import ogp.framework.util.Util;
import org.junit.Before;
import org.junit.Test;

import hillbillies.model.*;
import hillbillies.part1.facade.IFacade;
import ogp.framework.util.ModelException;

/**
 * Test Suite for the class of Units
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class UnitTest {

	private Unit legalUnit;
	/**
	 * @throws ModelException 
	 * 			Should never happen, it is a legal unit
	 */
	@Before
	public void setUp() throws ModelException {
		legalUnit = new Unit("TestUnit", new int[]{2, 2, 2}, 50, 50, 50, 50,
				false);
	}

	// Constructor //

	@Test
	public void constructorLegalCase() {
		assertEquals(50, legalUnit.getAgility());
		assertEquals(50, legalUnit.getStrength());
		assertEquals(50, legalUnit.getToughness());
		assertEquals(50, legalUnit.getWeight());
		assertDoublePositionEquals(legalUnit.getPosition().getX(),
				legalUnit.getPosition().getY(), legalUnit.getPosition().getZ(),
				new double[]{2.5, 2.5, 2.5});
		assertEquals("TestUnit", legalUnit.getName());
		assertEquals(Activity.IDLE, legalUnit.getActivity());
		assertTrue(Util.fuzzyEquals((double) legalUnit.getOrientation(),
				Math.PI / 2));
		assertTrue(Util.fuzzyEquals(legalUnit.maxSecondaryAttribute(),
				legalUnit.getStamina()));
		assertTrue(Util.fuzzyEquals(legalUnit.maxSecondaryAttribute(),
				legalUnit.getHitpoints()));
		assertEquals(Activity.IDLE, legalUnit.getActivity());
		assertEquals(false, legalUnit.getDefaultBehavior());
	}

	@Test
	public void constructorIllegalAttribute() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 24, 50, 50,
				false);
		assertEquals(25, theUnit.getAgility());
		assertEquals(50, theUnit.getStrength());
		assertEquals(50, theUnit.getToughness());
		assertEquals(50, theUnit.getWeight());
		assertDoublePositionEquals(theUnit.getPosition().getX(),
				theUnit.getPosition().getY(), theUnit.getPosition().getZ(),
				new double[]{1.5, 1.5, 1.5});
		assertEquals("TestUnit", theUnit.getName());
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertTrue(Util.fuzzyEquals((double) theUnit.getOrientation(),
				Math.PI / 2));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(),
				theUnit.getStamina()));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(),
				theUnit.getHitpoints()));
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertEquals(false, theUnit.getDefaultBehavior());
	}

	@Test
	public void constructorIllegalWeight() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 2, 26, 50, 50,
				false);
		assertEquals(26, theUnit.getAgility());
		assertEquals(50, theUnit.getStrength());
		assertEquals(50, theUnit.getToughness());
		assertEquals(theUnit.lowestValidWeight(), theUnit.getWeight());
		assertDoublePositionEquals(theUnit.getPosition().getX(),
				theUnit.getPosition().getY(), theUnit.getPosition().getZ(),
				new double[]{1.5, 1.5, 1.5});
		assertEquals("TestUnit", theUnit.getName());
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertTrue(Util.fuzzyEquals((double) theUnit.getOrientation(),
				Math.PI / 2));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(),
				theUnit.getStamina()));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(),
				theUnit.getHitpoints()));
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertEquals(false, theUnit.getDefaultBehavior());
	}

	// Position //

	@Test
	public void setPosition_LegalCase() throws ModelException {
		legalUnit.setPosition(new Coordinate(2, 3, 5));
		assertDoublePositionEquals(legalUnit.getPosition().getX(),
				legalUnit.getPosition().getY(), legalUnit.getPosition().getZ(),
				new double[]{2, 3, 5});
	}

	@Test(expected = ModelException.class)
	public void setPosition_IllegalCase() throws ModelException {
		legalUnit.setPosition(new Coordinate(-3, 3, 5));
	}

	// Initial Primary Attributes //

	@Test
	public void nearestValidInitialAttribute_LowCase() {
		assertEquals(25, Unit.nearestValidInitialAttribute(12));
	}

	@Test
	public void nearestValidInitialAttribute_HighCase() {
		assertEquals(100, Unit.nearestValidInitialAttribute(152));
	}

	@Test
	public void nearestValidInitialWeight_LowCase() {
		assertEquals(legalUnit.lowestValidWeight(),
				legalUnit.nearestValidInitialWeight(20));
	}

	@Test
	public void nearestValidInitialWeight_HighCase() {
		assertEquals(100, legalUnit.nearestValidInitialWeight(200));
	}

	// Primary Attributes //

	@Test
	public void nearestValidAttribute_LowCase() {
		assertEquals(1, Unit.nearestValidAttribute(0));
	}

	@Test
	public void nearestValidAttribute_HighCase() {
		assertEquals(200, Unit.nearestValidAttribute(510));
	}

	@Test
	public void nearestValidWeight_LowCase() {
		assertEquals(legalUnit.lowestValidWeight(),
				legalUnit.nearestValidWeight(20));
	}

	@Test
	public void nearestValidWeight_HighCase() {
		assertEquals(200, legalUnit.nearestValidWeight(600));
	}

	@Test
	public void setWeight_LegalCase() {
		legalUnit.setWeight(75);
		assertEquals(75, legalUnit.getWeight());
	}

	@Test
	public void setWeight_IllegalCase() {
		legalUnit.setWeight(30);
		assertEquals(legalUnit.nearestValidWeight(30), legalUnit.getWeight());
	}

	@Test
	public void setToughness_LegalCase() {
		legalUnit.setToughness(75);
		assertEquals(75, legalUnit.getToughness());
	}

	@Test
	public void setToughness_IllegalCase() {
		legalUnit.setToughness(201);
		assertEquals(Unit.nearestValidAttribute(201), legalUnit.getToughness());
	}

	@Test
	public void setAgility_LegalCase() {
		legalUnit.setAgility(75);
		assertEquals(75, legalUnit.getAgility());
	}

	@Test
	public void setAgility_IllegalCase() {
		legalUnit.setAgility(201);
		assertEquals(Unit.nearestValidAttribute(201), legalUnit.getAgility());
	}

	@Test
	public void setStrength_LegalCase() {
		legalUnit.setStrength(75);
		assertEquals(75, legalUnit.getStrength());
	}

	@Test
	public void setStrength_IllegalCase() {
		legalUnit.setStrength(201);
		assertEquals(Unit.nearestValidAttribute(201), legalUnit.getStrength());
	}

	// Secondary Attributes //

	@Test
	public void testMaxSecondaryAttribute() {
		assertEquals(50, (int) Math
				.ceil(legalUnit.getWeight() * legalUnit.getToughness() / 50.0));
	}

	@Test
	public void setHitpoints_LegalCase() {
		legalUnit.setHitpoints(15);
		assertTrue(Util.fuzzyEquals(15, legalUnit.getHitpoints()));
	}

	@Test(expected = AssertionError.class)
	public void setHitpoints_IllegalCase() {
		legalUnit.setHitpoints(51);
	}

	@Test
	public void setStamina_LegalCase() {
		legalUnit.setStamina(15);
		assertTrue(Util.fuzzyEquals(15, legalUnit.getStamina()));
	}

	@Test(expected = AssertionError.class)
	public void setStamina_IllegalCase() {
		legalUnit.setStamina(51);
	}

	// Name //

	@Test
	public void setName_LegalCase() throws ModelException {
		legalUnit.setName("Legal \"the boring\" Name");
	}

	@Test(expected = ModelException.class)
	public void setName_IllegalCase_Capital() throws ModelException {
		legalUnit.setName("illegal name");
	}

	@Test(expected = ModelException.class)
	public void setName_IllegalCase_Character() throws ModelException {
		legalUnit.setName("illegal name $");
	}

	@Test(expected = ModelException.class)
	public void setName_IllegalCase_Empty() throws ModelException {
		legalUnit.setName("");
	}


	// Orientation //

	@Test
	public void setOrientation_LegalCase() {
		legalUnit.setOrientation((float) (Math.PI - 0.2));
		assertTrue(
				Util.fuzzyEquals((Math.PI - 0.2), legalUnit.getOrientation()));
	}

	@Test
	public void setOrientation_IllegalCase() {
		legalUnit.setOrientation((float) (Math.PI - 5));
		assertTrue(Util.fuzzyEquals((Math.PI - 5) % (2 * Math.PI),
				legalUnit.getOrientation()));
	}

	// Moving //
	
	@Test
	public void walkingSpeed_Uphill() throws ModelException{
		legalUnit.moveToAdjacent(0,0,1);
		assertTrue(Util.fuzzyEquals(legalUnit.walkingSpeed(),0.75));
	}
	
	@Test
	public void walkingSpeed_Downhill() throws ModelException{
		legalUnit.moveToAdjacent(0, 0, -1);
		assertTrue(Util.fuzzyEquals(legalUnit.walkingSpeed(),1.80));
	}
	
	@Test
	public void walkingSpeed_level() throws ModelException{
		legalUnit.moveToAdjacent(0, 1, 0);
		assertTrue(Util.fuzzyEquals(legalUnit.walkingSpeed(),1.50));
	}
	
	@Test
	public void getCurrentSpeed_Moving() throws ModelException{
		legalUnit.moveToAdjacent(0,0,1);
		assertTrue(Util.fuzzyEquals(legalUnit.walkingSpeed(), legalUnit.getCurrentSpeed()));
	}
	
	@Test
	public void getCurrentSpeed_Sprinting() throws ModelException{
		legalUnit.moveToAdjacent(0,0,1);
		legalUnit.startSprinting();
		assertTrue(Util.fuzzyEquals(2* legalUnit.walkingSpeed(), legalUnit.getCurrentSpeed()));
	}
	
	@Test
	public void getCurrentSpeed_NotMoving() throws ModelException{
		assertTrue(Util.fuzzyEquals(0, legalUnit.getCurrentSpeed()));
	}
	
	
	
	
	// Attacking //
	
	// Resting //
	
	// Default Behavior //
	
	
	
	// Time Control //

	/**
	 * Helper method to advance time for the given unit by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Unit unit, double time, double step)
			throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			unit.advanceTime(step);
		unit.advanceTime(time - n * step);
	}

}
