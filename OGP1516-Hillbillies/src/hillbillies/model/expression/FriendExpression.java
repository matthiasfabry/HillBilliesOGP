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
public class FriendExpression extends UnitExpression<Unit> {

	public FriendExpression(Unit unit) {
		this.thisUnit = unit;
		this.friendUnit = determineFriendUnit();
	}
	private final Unit thisUnit;
	private final Unit friendUnit;
	
	@Override
	public Unit evaluate() {
		return friendUnit;
	}

	public Unit determineFriendUnit(){
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
						worldUnit.getFaction().equals(thisUnit.getFaction()))
					someUnit = worldUnit;
			} 	
		}	
		return someUnit;
	}

	@Override
	public boolean check() throws FormException{
		if (! (thisUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}

}
