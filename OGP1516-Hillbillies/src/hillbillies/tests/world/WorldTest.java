/**
 * 
 */
package hillbillies.tests.world;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.common.internal.ui.viewmodel.IViewModel.NewSpriteListener;
import hillbillies.model.*;
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
@SuppressWarnings("unused")
public class WorldTest {

	private static TerrainChangeListener theListener;
	private static Terrain[][][] someTerrain = new Terrain[20][30][50];
	private static World emptyWorld;

	@BeforeClass
	public static void setUpWorld() throws ModelException {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 30; j++)
				for (int k = 0; k < 50; k++)
					someTerrain[i][j][k] = Terrain.AIR;
		emptyWorld = new World(someTerrain, theListener);
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
		World newWorld = new World(someTerrain, theListener);
		assertEquals(theListener, newWorld.getListener());
		assertEquals(20, newWorld.getDimension()[0]);
		assertEquals(30, newWorld.getDimension()[1]);
		assertEquals(50, newWorld.getDimension()[2]);
		assertEquals(Terrain.AIR, newWorld.getTerrainAt(new Coordinate(1,1,1)));
		assertEquals(new HashSet<Unit>(), newWorld.getUnitSet());
		assertNotNull(newWorld.getAlgorithm());
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
