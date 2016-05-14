package hillbillies.model.unitexpression;

import hillbillies.model.Unit;
import hillbillies.model.expression.UnitExpression;

public class FriendExpression extends UnitExpression<Unit> {

	public FriendExpression(Unit unit) {
		this.thisUnit = unit;
		this.friendUnit = determineFriendUnit();
	}
	private final Unit thisUnit;
	private final Unit friendUnit;
	
	@Override
	public Unit evaluate() {
		return friendUnit;
	}

	public Unit determineFriendUnit(){
		return null;
	}

}
