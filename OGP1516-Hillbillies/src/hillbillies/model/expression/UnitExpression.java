package hillbillies.model.expression;

import hillbillies.model.Expression;
import hillbillies.model.Unit;
/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public abstract class UnitExpression<T> extends Expression<Unit> {

	public abstract Unit evaluate();
}
