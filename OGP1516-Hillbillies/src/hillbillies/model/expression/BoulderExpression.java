package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
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
public class BoulderExpression extends PositionExpression<Coordinate> {

	public BoulderExpression() {

	}
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return determineBoulderPosition(unit);
	}

	public Coordinate determineBoulderPosition(Unit thisUnit){
		Coordinate position = thisUnit.getInWorldPosition();
		World world = thisUnit.getWorld();
		Boulder someBoulder = null;
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		coordinateSet.add(position);
		while (someBoulder == null){
			for (Coordinate c: coordinateSet){
				Coordinate[] clist = c.adjacentCoordinates();
				for (Coordinate cc: clist){
					if (! coordinateSet.contains(cc))
						coordinateSet.add(cc);
				}
			}
			for (Boulder worldBoulder: world.getBoulderSet()){
				if (coordinateSet.contains(worldBoulder.getPosition()))
					someBoulder = worldBoulder;
			} 	
		}	
		Coordinate place = someBoulder.getPosition();
		return place;
	}

	@Override
	public boolean check(Unit thisUnit) throws FormException{
		return true;
	}

}
