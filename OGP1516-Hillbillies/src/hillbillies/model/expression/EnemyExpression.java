package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;
import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class EnemyExpression extends UnitExpression<Unit> {

	public EnemyExpression() {
	}
	
	@Override
	public Unit evaluate(Unit unit) {
		return determineEnemyUnit(unit);
	}

	public Unit determineEnemyUnit(Unit thisUnit){
		Coordinate position = thisUnit.getInWorldPosition();
		World world = thisUnit.getWorld();
		Unit someUnit = null;
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		coordinateSet.add(position);
		while (someUnit == null){
			for (Coordinate c: coordinateSet){
				Coordinate[] clist = c.adjacentCoordinates();
				for (Coordinate cc: clist){
					if (! coordinateSet.contains(cc))
						coordinateSet.add(cc);
				}
			}
			for (Unit worldUnit: world.getUnitSet()){
				if (coordinateSet.contains(worldUnit.getInWorldPosition()) && 
						! worldUnit.getFaction().equals(thisUnit.getFaction()))
					someUnit = worldUnit;
			} 	
		}	
		return someUnit;
	}

	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (thisUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}


}
