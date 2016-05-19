/**
 * 
 */
package hillbillies.model.expression;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public interface Expression<T> {
	
	public T evaluate();
	
	boolean check();

	
}
