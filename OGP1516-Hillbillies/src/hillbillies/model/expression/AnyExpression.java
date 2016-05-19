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
public class AnyExpression extends UnitExpression<Unit> {

	public AnyExpression(Unit unit) {
		this.thisUnit = unit;
		this.anyUnit = determineAnyUnit();
	}
	private final Unit thisUnit;
	private final Unit anyUnit;
	
	@Override
	public Unit evaluate() {
		return anyUnit;
	}

	public Unit determineAnyUnit(){
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
				if (coordinateSet.contains(worldUnit.getInWorldPosition()))
					someUnit = worldUnit;
			} 	
		}	
		return someUnit;
	}

	@Override
	public boolean check() throws FormException {
		// TODO Auto-generated method stub
		return false;
	}

}
