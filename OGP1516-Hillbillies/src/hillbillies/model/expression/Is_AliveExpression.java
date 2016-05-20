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

	public Is_AliveExpression(Expression unit){
	}
	
	@Override
	public Boolean evaluate(Unit thisUnit) {
		return (thisUnit.isDead());
	}
	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (thisUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}

}
