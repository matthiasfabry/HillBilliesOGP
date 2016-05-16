package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class NotExpression implements Expression<Boolean> {

	public NotExpression(Expression<Boolean> Value) {
		this.value = Value;
	}
	public final Expression<Boolean> value;
	
	@Override
	public Boolean evaluate() {
		return (! value.evaluate());
	}

}
