package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class BracketExpression implements Expression<Unit> {

	public BracketExpression(Unit unit, String NameToMatch){
		this.name = NameToMatch;
		this.thisUnit = unit;
	}
	private final String name;
	private final Unit thisUnit;
	
	@Override
	public Unit evaluate() {
		Unit searchedUnit = null;
		World world = thisUnit.getWorld();
		Set<Unit> unitSet = world.getUnitSet();
		for (Unit u: unitSet)
			if (u.getName().equals(name))
				searchedUnit = u;
		return searchedUnit;
	}

	@Override
	public boolean check() throws FormException{
		// TODO Auto-generated method stub
		return false;
	}

}
