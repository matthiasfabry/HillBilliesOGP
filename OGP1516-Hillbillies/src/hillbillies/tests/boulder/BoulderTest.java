package hillbillies.tests.boulder;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;

import hillbillies.model.World;
import ogp.framework.util.ModelException;
/**
 * Test Suite for the class of Boulders
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class BoulderTest {

	private static World theWorld;
	private static Coordinate theCoordinate = new Coordinate(1,1,1);
	private Boulder boulder;
	
	@Test
	public void SetUp() throws ModelException{
		boulder = new Boulder(theCoordinate, theWorld);
	}
	
}

