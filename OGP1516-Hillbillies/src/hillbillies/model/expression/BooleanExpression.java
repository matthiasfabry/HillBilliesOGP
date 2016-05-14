package hillbillies.model.expression;

import hillbillies.model.Expression;
/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class BooleanExpression<T> extends Expression<T> {

	public BooleanExpression(T Value) {
		this.value = Value;
	}
	
	@Override
	public T evaluate() {
		return value;
	}
	private final T value;

}
