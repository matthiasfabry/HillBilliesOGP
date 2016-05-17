package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.model.World;

public class BracketExpression<T> implements Expression<Unit> {

	public BracketExpression(Unit thisUnit, String NameToMatch){
		this.name = NameToMatch;
		this.unit = thisUnit;
	}
	private final String name;
	private final Unit unit;
	
	@Override
	public Unit evaluate() {
		Unit searchedUnit = null;
		World world = unit.getWorld();
		Set<Unit> unitSet = world.getUnitSet();
		for (Unit u: unitSet)
			if (u.getName().equals(name))
				searchedUnit = u;
		return searchedUnit;
	}

}
