package hillbillies.model.expression;

import hillbillies.model.Unit;

public class Is_FriendExpression implements Expression<Boolean> {

	public Is_FriendExpression(Unit thisunit, Unit otherunit){
		this.thisUnit = thisunit;
		this.otherUnit = otherunit;
	}
	private final Unit thisUnit;
	private final Unit otherUnit;
	
	@Override
	public Boolean evaluate() {
		if (thisUnit.getFaction() == otherUnit.getFaction())
			return true;
		else 
			return false;
	}
}
