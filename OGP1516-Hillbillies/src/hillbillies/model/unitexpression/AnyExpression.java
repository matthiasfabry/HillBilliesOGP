package hillbillies.model.unitexpression;

import hillbillies.model.Unit;
import hillbillies.model.expression.UnitExpression;

public class AnyExpression extends UnitExpression<Unit> {

	public AnyExpression(Unit unit) {
		this.thisUnit = unit;
		this.anyUnit = determineAnyUnit();
	}
	private final Unit thisUnit;
	private final Unit anyUnit;
	
	@Override
	public Unit evaluate() {
		return anyUnit;
	}

	public Unit determineAnyUnit(){
		return null;
	}

}
