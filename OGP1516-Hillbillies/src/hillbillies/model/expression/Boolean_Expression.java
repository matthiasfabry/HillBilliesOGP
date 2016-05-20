
package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Boolean_Expression implements Expression<Boolean> {

	public Boolean_Expression(boolean condition) {
		value = condition;
	}
	private final Boolean value;
	
	@Override
	public Boolean evaluate(Unit unit) {
		return this.value;
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (!(value instanceof Boolean))
			throw new FormException();
		else 
			return true;
	}

}