package hillbillies.model.expression;

import hillbillies.model.Unit;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Carries_Item implements Expression<Boolean> {

	public Carries_Item(Expression unit){
	}
	
	@Override
	public Boolean evaluate(Unit thisUnit) {
		return (thisUnit.isCarrying());
	}

	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (thisUnit instanceof Unit))
			throw new FormException();
		else
			return true;
	}

}
