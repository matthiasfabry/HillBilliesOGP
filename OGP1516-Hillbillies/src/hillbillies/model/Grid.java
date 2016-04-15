/**
 * 
 */
package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.ModelException;

/**
 * Background class representing a game world map
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
class Grid {

	// Constructor //
	
	/**
	 * Initializes a Grid
	 * @param features
	 * @param world
	 */
	Grid(Terrain[][][] features, World world){
		this.map = new Cube[features.length][features[0].length][features[0][0].length];
		this.dimension = new int[]{features.length, features[0].length,
				features[0][0].length};
		this.world = world;
		for (int indexX = 0; indexX < features.length; indexX++) {
			for (int indexY = 0; indexY < features[indexX].length; indexY++) {
				for (int indexZ = 0; indexZ < features[indexX][indexY].length; indexZ++) {
					this.getMap()[indexX][indexY][indexZ] = new Cube(
							new Coordinate(indexX, indexY, indexZ), world);
					try {
						this.getMap()[indexX][indexY][indexZ]
								.setTerrain(features[indexX][indexY][indexZ]);
					} catch (ModelException e) {
						// shouldn't happen
					}
				}
			}
		}
	}

	// World link //
	/**
	 * @return this.world
	 */
	@Basic
	@Raw
	World getWorld() {
		return this.world;
	}
	/**
	 * the World where the grid is the representation of.
	 */
	private final World world;

	// Map //
	
	/**
	 * Makes a list of the terrains at the adjacent cubes of 
	 * the given coordinate
	 * @param coordinate
	 * 			of which the terreain of the adjacent cubes is required
	 * @return	A list of the adjacent terrains
	 */
	Terrain[] terrainAtAdjacentCubes(Coordinate coordinate) {
		Terrain[] result = new Terrain[6];
		result[0] = this.getTerrainAt(coordinate.sum(new Coordinate(1, 0, 0)));
		result[1] = this
				.getTerrainAt(coordinate.difference(new Coordinate(1, 0, 0)));
		result[2] = this.getTerrainAt(coordinate.sum(new Coordinate(0, 1, 0)));
		result[3] = this
				.getTerrainAt(coordinate.difference(new Coordinate(0, 1, 0)));
		result[4] = this.getTerrainAt(coordinate.sum(new Coordinate(0, 0, 1)));
		result[5] = this
				.getTerrainAt(coordinate.difference(new Coordinate(0, 0, 1)));
		return result;
	}
	/**
	 * @param coordinate
	 * 			the coordinate of the cube of which we require the terrain
	 * @return	the terrain of the cube of the given coordinate
	 */
	Terrain getTerrainAt(Coordinate coordinate) {
		try {
			return this.getMapAt(coordinate).getTerrain();
		} catch (NullPointerException e) {
			return null;
		}
	}
	/**
	 * Method that sets the terrain at the given coordinate to the given terrain type
	 * @param coordinate
	 * 			the position of the cubes that needs alteration
	 * @param terrain
	 * 			the type to which the terrain will change
	 * @throws ModelException
	 * 			If the given coordinate lies outside this grid
	 */
	void setTerrainAt(Coordinate coordinate, Terrain terrain) throws ModelException {
		try {
			this.getMapAt(coordinate).setTerrain(terrain);
		} catch (IndexOutOfBoundsException e) {
			throw new ModelException("Outside World");
		}
	}
	
	Cube getMapAt(Coordinate coordinate){
		try {
			return this.getMap()[(int) coordinate.floor().getX()][(int) coordinate
					.floor().getY()][(int) coordinate.floor().getZ()];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Basic
	@Raw
	Cube[][][] getMap() {
		return this.map;
	}
	/**
	 * Variable referencing the map of this game world grid.
	 */
	private final Cube[][][] map;

	// Dimension //

	/**
	 * Return the Dimension of this World.
	 */
	@Basic
	@Raw
	@Immutable
	int[] getDimension() {
		return this.dimension;
	}
	/**
	 * Variable registering the Dimension of this World.
	 */
	private final int[] dimension;

	// Additional methods //
	
	/**
	 * Lists all cubes that are directly adjacent to the given coordinate
	 * @param coordinate
	 * 			the coordinate from which we require the directly adjacent cubes
	 * @return	a list of Cubes that are directly adjacent to coordinate
	 */
	Cube[] directAdjCube(Coordinate coordinate) {
		Cube[] result = new Cube[6];
		result[0] = this.getMapAt(coordinate.sum(new Coordinate(1, 0, 0)));
		result[1] = this
				.getMapAt(coordinate.difference(new Coordinate(1, 0, 0)));
		result[2] = this.getMapAt(coordinate.sum(new Coordinate(0, 1, 0)));
		result[3] = this
				.getMapAt(coordinate.difference(new Coordinate(0, 1, 0)));
		result[4] = this.getMapAt(coordinate.sum(new Coordinate(0, 0, 1)));
		result[5] = this
				.getMapAt(coordinate.difference(new Coordinate(0, 0, 1)));
		return result;
	}
	/**
	 * Method that lists all Cubes that are adjacent to the given coordinate
	 * @param coordinate
	 * 			the coordinate from which we require the adjacent cubes
	 * @return	a list of Cubes that are adjacent to coordinate
	 * 			
	 */
	Cube[] adjacentCubes(Coordinate coordinate) {
		Cube[] result = new Cube[26];
		result[0]  = this.getMapAt(coordinate.sum(new Coordinate(1, 0, 0)));
		result[1]  = this.getMapAt(coordinate.sum(new Coordinate(-1, 0, 0)));
		result[2]  = this.getMapAt(coordinate.sum(new Coordinate(0, 1, 0)));
		result[3]  = this.getMapAt(coordinate.sum(new Coordinate(0, -1, 0)));
		result[4]  = this.getMapAt(coordinate.sum(new Coordinate(0, 0, 1)));
		result[5]  = this.getMapAt(coordinate.sum(new Coordinate(0, 0, -1)));
		result[6]  = this.getMapAt(coordinate.sum(new Coordinate(1, 1, 0)));
		result[7]  = this.getMapAt(coordinate.sum(new Coordinate(-1, -1, 0)));
		result[8]  = this.getMapAt(coordinate.sum(new Coordinate(1, 1, 1)));
		result[9]  = this.getMapAt(coordinate.sum(new Coordinate(-1, -1, -1)));
		result[10] = this.getMapAt(coordinate.sum(new Coordinate(1, 0, 1)));
		result[11] = this.getMapAt(coordinate.sum(new Coordinate(-1, 0, -1)));
		result[12] = this.getMapAt(coordinate.sum(new Coordinate(0, 1, 1)));
		result[13] = this.getMapAt(coordinate.sum(new Coordinate(0, -1, -1)));
		result[14] = this.getMapAt(coordinate.sum(new Coordinate(1, -1, 0)));
		result[15] = this.getMapAt(coordinate.sum(new Coordinate(-1, 1, 0)));
		result[16] = this.getMapAt(coordinate.sum(new Coordinate(1, 0, -1)));
		result[17] = this.getMapAt(coordinate.sum(new Coordinate(-1, 0, 1)));
		result[18] = this.getMapAt(coordinate.sum(new Coordinate(0, 1, -1)));
		result[19] = this.getMapAt(coordinate.sum(new Coordinate(0, -1, 1)));
		result[20] = this.getMapAt(coordinate.sum(new Coordinate(1, 1, -1)));
		result[21] = this.getMapAt(coordinate.sum(new Coordinate(-1, -1, 1)));
		result[22] = this.getMapAt(coordinate.sum(new Coordinate(1, -1, 1)));
		result[23] = this.getMapAt(coordinate.sum(new Coordinate(-1, 1, -1)));
		result[24] = this.getMapAt(coordinate.sum(new Coordinate(-1, 1, 1)));
		result[25] = this.getMapAt(coordinate.sum(new Coordinate(1, -1, -1)));
		return result;

	}

}
