package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Is_AliveExpression implements Expression<Boolean> {

	public Is_AliveExpression(Unit unit){
		this.thisUnit = unit;
	}
	
	private final Unit thisUnit;
	@Override
	public Boolean evaluate() {
		return (thisUnit.isDead());
	}

}
