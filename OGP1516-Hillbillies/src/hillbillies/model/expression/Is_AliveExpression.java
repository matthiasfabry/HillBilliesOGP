package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Is_AliveExpression implements Expression<Boolean> {

	public Is_AliveExpression(Expression unit){
		otherUnit = unit;
	}
	private Expression otherUnit;
	
	@Override
	public Boolean evaluate(Unit thisUnit) {
		return (((Unit) otherUnit.evaluate(thisUnit)).isDead());
	}
	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (otherUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}

}
