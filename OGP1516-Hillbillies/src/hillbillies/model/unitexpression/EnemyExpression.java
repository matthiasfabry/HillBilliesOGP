package hillbillies.model.unitexpression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expression.UnitExpression;

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
		Coordinate[] coordinatelist = position.adjacentCoordinates();
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		for (Coordinate c: coordinatelist){
			coordinateSet.add(c);
		}
		while (someUnit == null){
			for (Unit worldUnit: world.getUnitSet()){
				if (coordinateSet.contains(worldUnit.getInWorldPosition()) && 
						! worldUnit.getFaction().equals(thisUnit.getFaction()))
					someUnit = worldUnit;
			} 
			for (Coordinate cc: coordinateSet){
				Coordinate[] clist = cc.adjacentCoordinates();
				for (Coordinate ccc: clist){
					if (! coordinateSet.contains(ccc))
						coordinateSet.add(ccc);
				}
			}	
		}	
		return someUnit;
	}


}
