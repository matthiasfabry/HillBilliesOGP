package hillbillies.model.expression;

import hillbillies.model.Unit;
/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public abstract class UnitExpression<T> implements Expression<Unit> {

	public abstract Unit evaluate();
	
	public abstract boolean check();
}
