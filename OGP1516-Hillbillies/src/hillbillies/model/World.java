/**
 * 
 */
package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class World {

	// Constructor //

	public World(Terrain[][][] features, TerrainChangeListener listener)
			throws ModelException {
		this.map = new Cube[features.length][features[0].length][features[0][0].length];
		this.dimension = new int[]{features.length, features[0].length,
				features[0][0].length};
		for (int indexX = 0; indexX < map.length; indexX++){
			for (int indexY = 0; indexY < map[indexX].length; indexY++){
				for (int indexZ = 0; indexZ < map[indexX][indexY].length; indexZ++){
					this.getMap()[indexX][indexY][indexZ] = new Cube();
					this.getMap()[indexX][indexY][indexZ].
						setTerrain(features[indexX][indexY][indexZ]);
				}
			}
		}
	}

	/** TO BE ADDED TO CLASS HEADING
	* @invar  Each World can have its Dimension as Dimension.
	*       | canHaveAsDimension(this.getDimension())
	*/

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
	 * Check whether this World can have the given Dimension as its Dimension.
	 *  
	 * @param  dimension
	 *         The Dimension to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsDimension(int[] dimension) {
		return false;
	}

	/**
	 * Variable registering the Dimension of this World.
	 */
	private int[] dimension;

	@Basic
	@Raw
	public Cube[][][] getMap() {
		return this.map;
	}

	private final Cube[][][] map;
}
