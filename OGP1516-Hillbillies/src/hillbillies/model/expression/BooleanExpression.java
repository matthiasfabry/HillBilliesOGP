package hillbillies.model.expression;

import hillbillies.model.Expression;
/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class BooleanExpression extends Expression<Boolean> {

	public BooleanExpression(boolean Value) {
		this.value = Value;
	}
	
	@Override
	public Boolean evaluate() {
		return value;
	}
	private final boolean value;

}
