package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Is_EnemyExpression implements Expression<Boolean> {

	public Is_EnemyExpression(Unit thisunit, Unit otherunit){
		this.thisUnit = thisunit;
		this.otherUnit = otherunit;
	}
	private final Unit thisUnit;
	private final Unit otherUnit;
	
	@Override
	public Boolean evaluate() {
		if (thisUnit.getFaction() != otherUnit.getFaction())
			return true;
		else 
			return false;
	}

}
