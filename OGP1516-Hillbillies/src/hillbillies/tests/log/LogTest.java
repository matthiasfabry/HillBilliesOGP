package hillbillies.tests.log;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Log;
import hillbillies.model.World;
import ogp.framework.util.ModelException;
/**
 * Test Suite for the class of Logs
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class LogTest {

	private static World theWorld;
	private static Coordinate theCoordinate = new Coordinate(1,1,1);
	private Log log;
	
	@Test
	public void SetUp() throws ModelException{
		log = new Log(theCoordinate, theWorld);
	}

}
