/**
 * 
 */
package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public interface Expression<T> {
	
	public T evaluate(Unit unit);
	
	boolean check() throws FormException;

	
}
