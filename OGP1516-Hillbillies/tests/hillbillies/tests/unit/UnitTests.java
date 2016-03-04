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
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class UnitTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void constructorLegalCase() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertEquals(50, theUnit.getAgility());
		assertEquals(50, theUnit.getStrength());
		assertEquals(50, theUnit.getToughness());
		assertEquals(50, theUnit.getWeight());
		assertDoublePositionEquals(theUnit.getPosition().getX(),
				theUnit.getPosition().getY(), theUnit.getPosition().getZ(),
				new double[]{1.5, 1.5, 1.5});
		assertEquals("TestUnit", theUnit.getName());
		assertEquals(Activity.IDLE,theUnit.getActivity());
		assertTrue(Util.fuzzyEquals((double) theUnit.getOrientation(), Math.PI /2));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(), theUnit.getStamina()));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(), theUnit.getHitpoints()));
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertEquals(false,theUnit.getDefaultBehavior());
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
		assertEquals(Activity.IDLE,theUnit.getActivity());
		assertTrue(Util.fuzzyEquals((double) theUnit.getOrientation(), Math.PI /2));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(), theUnit.getStamina()));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(), theUnit.getHitpoints()));
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertEquals(false,theUnit.getDefaultBehavior());
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
		assertEquals(Activity.IDLE,theUnit.getActivity());
		assertTrue(Util.fuzzyEquals((double) theUnit.getOrientation(), Math.PI /2));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(), theUnit.getStamina()));
		assertTrue(Util.fuzzyEquals(theUnit.maxSecondaryAttribute(), theUnit.getHitpoints()));
		assertEquals(Activity.IDLE, theUnit.getActivity());
		assertEquals(false,theUnit.getDefaultBehavior());
	}
	
	@Test
	public void setPosition_LegalCase() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		theUnit.setPosition(new Coordinate(2, 3, 5));
		assertDoublePositionEquals(theUnit.getPosition().getX(),
				theUnit.getPosition().getY(), theUnit.getPosition().getZ(),
				new double[]{2, 3, 5});
	}
	
	@Test(expected = ModelException.class)
	public void setPosition_IllegalCase() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		theUnit.setPosition(new Coordinate(-3, 3, 5));
	}
	
	@Test
	public void isValidPostion_TrueCase(){
		assertTrue(Unit.isValidPosition(new Coordinate(1,1,1)));
	}
	
	@Test
	public void isValidPostion_FalseCase(){
		assertFalse(Unit.isValidPosition(new Coordinate(-1,1,1)));
	}
	
	@Test
	public void isValidInitialAttribute_TrueCase() {
		assertTrue(Unit.isValidInitialAttribute(37));
	}
	
	@Test
	public void isValidInitialAttribute_FalseCase() {
		assertFalse(Unit.isValidInitialAttribute(12));
	}
	
	@Test
	public void nearestValidInitialAttribute_LowCase() {
		assertEquals(25, Unit.nearestValidInitialAttribute(12));
	}
	
	@Test
	public void nearestValidInitialAttribute_HighCase() {
		assertEquals(100, Unit.nearestValidInitialAttribute(152));
	}
	
	@Test
	public void isValidInitialWeight_TrueCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertTrue(theUnit.isValidInitialWeight(75));
	}
	
	@Test
	public void isValidInitialWeight_FalseCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertFalse(theUnit.isValidInitialWeight(30));
	}

	@Test
	public void nearestValidInitialWeight_LowCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertEquals(theUnit.lowestValidWeight(), theUnit.nearestValidInitialWeight(20));
	}
	
	@Test
	public void nearestValidInitialWeight_HighCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertEquals(100, theUnit.nearestValidInitialWeight(200));
	}

	@Test
	public void nearestValidWeight_LowCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertEquals(theUnit.lowestValidWeight(), theUnit.nearestValidWeight(20));
	}

	@Test
	public void nearestValidWeight_HighCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertEquals(200, theUnit.nearestValidWeight(600));
	}

	@Test
	public void testlowestValidWeight() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 30, 30, 50,
				false);
		assertEquals(30, theUnit.lowestValidWeight());
	}
	
	@Test
	public void isValidWeight_TrueCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertTrue(theUnit.isValidWeight(150));
	}
	
	@Test
	public void isValidWeight_FalseCase() throws ModelException{
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		assertFalse(theUnit.isValidWeight(30));
	}

	@Test
	public void setWeight_LegalCase() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		theUnit.setWeight(75);
		assertEquals(75,theUnit.getWeight());
	}
	
	@Test
	public void setWeight_IllegalCase() throws ModelException {
		Unit theUnit = new Unit("TestUnit", new int[]{1, 1, 1}, 50, 50, 50, 50,
				false);
		theUnit.setWeight(30);
		assertEquals(theUnit.nearestValidWeight(30),theUnit.getWeight());
	}














}
