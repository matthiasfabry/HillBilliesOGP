package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class ThisExpression extends UnitExpression<Unit> {

	public ThisExpression() {
	}

	
	@Override
	public Unit evaluate(Unit unit) {
		return unit;
	}

	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (thisUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}

}
