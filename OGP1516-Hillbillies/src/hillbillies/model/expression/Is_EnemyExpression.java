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

	public Is_EnemyExpression(Expression unit){
		this.otherUnit = unit.evaluate();
	}
	private final Unit otherUnit;
	
	@Override
	public Boolean evaluate(Unit thisUnit) {
		if (thisUnit.getFaction() != otherUnit.getFaction())
			return true;
		else 
			return false;
	}

	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (thisUnit instanceof Unit) || (! (otherUnit instanceof Unit)))
			throw new FormException();
		else 
			return true;
	}

}
