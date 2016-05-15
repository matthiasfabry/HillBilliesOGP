package hillbillies.model.expression;

import hillbillies.model.Unit;

public class Carries_Item implements Expression<Boolean> {

	public Carries_Item(Unit unit){
		this.thisUnit = unit;
	}
	private final Unit thisUnit;
	
	@Override
	public Boolean evaluate() {
		if (thisUnit.isCarrying())
			return true;
		else 
			return false;
	}

}
