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
	
	public Boulder(int x, int y, int z, World world) throws ModelException{
		super(x,y,z, world);
	}
}
