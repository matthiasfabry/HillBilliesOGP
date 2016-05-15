package hillbillies.model.expression;

import hillbillies.model.Unit;

public class Is_AliveExpression implements Expression<Boolean> {

	public Is_AliveExpression(Unit unit){
		this.thisUnit = unit;
	}
	
	private final Unit thisUnit;
	@Override
	public Boolean evaluate() {
		if(thisUnit.isDead())
			return false;
		else
			return true;
	}

}
