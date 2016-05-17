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

	public EnemyExpression(Unit unit) {
		this.thisUnit = unit;
		this.enemyUnit = determineEnemyUnit();
	}
	private final Unit thisUnit;
	private final Unit enemyUnit;
	
	@Override
	public Unit evaluate() {
		return enemyUnit;
	}

	public Unit determineEnemyUnit(){
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


}
