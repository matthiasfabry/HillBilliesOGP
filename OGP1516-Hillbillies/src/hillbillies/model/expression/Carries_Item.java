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

	public Carries_Item(Unit unit){
		this.thisUnit = unit;
	}
	private final Unit thisUnit;
	
	@Override
	public Boolean evaluate() {
		return (thisUnit.isCarrying());
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

}
