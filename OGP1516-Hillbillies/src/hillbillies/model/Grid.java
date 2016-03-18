/**
 * 
 */
package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class Grid {
	
	// Constructor //

	public Grid(Terrain[][][] features, World world) throws ModelException {
		this.map = new Cube[features.length][features[0].length][features[0][0].length];
		this.dimension = new int[]{features.length, features[0].length,
				features[0][0].length};
		this.world = world;
		for (int indexX = 0; indexX < features.length; indexX++) {
			for (int indexY = 0; indexY < features[indexX].length; indexY++) {
				for (int indexZ = 0; indexZ < features[indexX][indexY].length; indexZ++) {
					this.getMap()[indexX][indexY][indexZ] = new Cube();
					this.getMap()[indexX][indexY][indexZ]
							.setTerrain(features[indexX][indexY][indexZ]);
				}
			}
		}
	}
	
	// World //
	
	public World getWorld(){
		return this.world;
	}
	
	private final World world;
	
	// Map //
	
	public Cube[][][] getMap() {
		return map;
	}

	private final Cube[][][] map;
	
	// Dimension //

	/**
	 * Return the Dimension of this World.
	 */
	@Basic
	@Raw
	@Immutable
	public int[] getDimension() {
		return this.dimension;
	}
	
	/**
	 * Variable registering the Dimension of this World.
	 */
	private final int[] dimension;

	Cube[] adjacentCubes(Coordinate coordinate) {
		Cube[] result = new Cube[26];

		return result;
	}




	
	
}
