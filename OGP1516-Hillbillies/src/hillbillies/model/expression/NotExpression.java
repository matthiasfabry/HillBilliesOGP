package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class NotExpression implements Expression<Boolean> {

	public NotExpression(Expression<Boolean> Value) {
		this.value = Value;
	}
	public final Expression<Boolean> value;
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (! value.evaluate(unit));
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (!(value.evaluate(unit) instanceof Boolean))
			throw new FormException();
		else 
			return true;
	}

}
