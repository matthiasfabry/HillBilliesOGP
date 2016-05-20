package hillbillies.tests.gameobject;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.GameObject;
import hillbillies.model.Log;
import hillbillies.model.Terrain;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

/**
 * Test Suite for the class of GameObjects, being logs and boulders.
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class GameObjectTest {

	private static World theWorld;
	private static Coordinate theCoordinate1 = new Coordinate(1,1,0);
	private static Coordinate theCoordinate2 = new Coordinate(1,1,0);
	private Log log;
	private Boulder boulder;
	private static TerrainChangeListener thelistener = new TerrainChangeListener() {
		
		@Override
		public void notifyTerrainChanged(int x, int y, int z) {
			// TODO Auto-generated method stub
			
		}
	};
	private static Terrain[][][] testTerrain = new Terrain[50][50][50];

	
	@BeforeClass
	public static void setUpWorld() throws ModelException {
		for (int i = 0; i < 50; i++)
			for (int j = 0; j < 50; j++)
				for (int k = 0; k < 50; k++)
					testTerrain[i][j][k] = Terrain.AIR;
		theWorld = new World(testTerrain, thelistener);
	}
	
	@Test
	public void SetUp() throws ModelException {
		log = new Log(theCoordinate1, theWorld);
		boulder = new Boulder(theCoordinate2, theWorld);
	}
	
	// Constructor //
	
	@Test
	public void ConstructorTest_Legal() throws ModelException {
		GameObject newObject = new Log(new Coordinate(1,5,0),theWorld);
		assertNotNull(newObject);
	}
	
	
	
	
	
	
	
	
	
	
//	
//	
//	
//	@Test
//	public void testSetWorld() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetPosition() {
//		fail("Not yet implemented");
//	}

}
