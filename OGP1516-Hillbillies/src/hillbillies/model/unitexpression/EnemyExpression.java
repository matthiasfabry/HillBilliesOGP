package hillbillies.model.unitexpression;

import hillbillies.model.Unit;
import hillbillies.model.expression.UnitExpression;

public class EnemyExpression extends UnitExpression<Unit> {

	public EnemyExpression(Unit unit) {
		this.thisUnit = unit;
		this.enemyUnit = determineEnemyUnit();
	}
	private final Unit thisUnit;
	private final Unit enemyUnit;
	
	@Override
	public Unit evaluate() {
		return enemyUnit;
	}

	public Unit determineEnemyUnit(){
		return null;
	}


}
