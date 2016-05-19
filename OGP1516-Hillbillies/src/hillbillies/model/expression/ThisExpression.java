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

	public ThisExpression(Unit unit) {
		this.thisUnit = unit;
	}
	private final Unit thisUnit;
	
	@Override
	public Unit evaluate() {
		return thisUnit;
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

}
