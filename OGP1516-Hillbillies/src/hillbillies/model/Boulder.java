/**
 * 
 */
package hillbillies.model;

import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class Boulder extends GameObject {
	/**
	 * initializes a Boulder at given position in given world.
	 * @param coordinate
	 * 			the coordinate at which the Boulder should be created
	 * @param world
	 * 			the world in which the Boulder should be created
	 * @throws ModelException
	 */
	public Boulder(Coordinate coordinate, World world) throws ModelException{
		super(coordinate, world);
	}
}
