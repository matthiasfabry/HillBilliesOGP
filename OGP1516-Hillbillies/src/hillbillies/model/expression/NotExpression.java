package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class NotExpression<T> implements Expression<T> {

	public NotExpression(Expression<T> Value) {
		this.value = Value;
	}
	public final Expression<T> value;
	
	@Override
	public T evaluate() {
		return (value.evaluate());
	}

}
