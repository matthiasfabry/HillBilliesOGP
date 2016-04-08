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
public class Log extends GameObject {
	/**
	 * initializes a Log at given position in given world.
	 * @param coordinate
	 * 			the coordinate at which the Log should be created
	 * @param world
	 * 			the world in which the Log should be created
	 * @throws ModelException
	 */
	public Log(Coordinate coordinate, World world) throws ModelException{
		super(coordinate, world);
	}
}
