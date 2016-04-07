/**
 * 
 */
package hillbillies.tests.world;

import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Activity;
import hillbillies.model.Terrain;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class WorldTest {

	private static TerrainChangeListener theListener;
	private static Terrain[][][] testTerrain = new Terrain[50][50][50];
	private static World emptyWorld;

	@BeforeClass
	public static void setUpWorld() throws ModelException {
		for (int i = 0; i < 50; i++)
			for (int j = 0; j < 50; j++)
				for (int k = 0; k < 50; k++)
					testTerrain[i][j][k] = Terrain.AIR;
		emptyWorld = new World(testTerrain, theListener);
	}

	/**
	 * @throws ModelException 
	 * 			
	 */
	@Before
	public void setUp() throws ModelException {

	}

	// Constructor //

	@Test
	public void constructorLegalCase() {
		Terrain[][][] someTerrain = new Terrain[20][30][50];
		World newWorld = new World(someTerrain, theListener);
		assertEquals(theListener, newWorld.getListener());
	}


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
