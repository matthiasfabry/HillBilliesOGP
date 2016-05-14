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
public class NotExpression<T> extends Expression<T> {

	public NotExpression(Expression<T> Value) {
		this.value = Value;
	}
	public final Expression<T> value;
	
	@Override
	public T evaluate() {
		return (value.evaluate());
	}
	public int a;

}
