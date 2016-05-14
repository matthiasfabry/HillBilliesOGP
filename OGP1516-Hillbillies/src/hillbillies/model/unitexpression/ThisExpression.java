package hillbillies.model.unitexpression;

import hillbillies.model.Unit;
import hillbillies.model.expression.UnitExpression;

public class ThisExpression extends UnitExpression<Unit> {

	public ThisExpression(Unit unit) {
		this.thisUnit = unit;
	}
	private final Unit thisUnit;
	
	@Override
	public Unit evaluate() {
		return thisUnit;
	}

}
