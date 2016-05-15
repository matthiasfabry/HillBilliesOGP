package hillbillies.model.coordinateexpression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expression.PositionExpression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class BoulderExpression extends PositionExpression<Coordinate> {

	public BoulderExpression(Unit unit) {
		this.thisUnit = unit;
		this.boulderPosition = determineBoulderPosition();
	}
	private final Unit thisUnit;
	private final Coordinate boulderPosition;
	
	@Override
	public Coordinate evaluate() {
		return this.boulderPosition;
	}

	public Coordinate determineBoulderPosition(){
		Coordinate position = thisUnit.getInWorldPosition();
		World world = thisUnit.getWorld();
		Boulder someBoulder = null;
		Coordinate[] coordinatelist = position.adjacentCoordinates();
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		for (Coordinate c: coordinatelist){
			coordinateSet.add(c);
		}
		while (someBoulder == null){
			for (Boulder worldBoulder: world.getBoulderSet()){
				if (coordinateSet.contains(worldBoulder.getPosition()))
					someBoulder = worldBoulder;
			} 
			for (Coordinate cc: coordinateSet){
				Coordinate[] clist = cc.adjacentCoordinates();
				for (Coordinate ccc: clist){
					if (! coordinateSet.contains(ccc))
						coordinateSet.add(ccc);
				}
			}	
		}	
		Coordinate place = someBoulder.getPosition();
		return place;
	}

}
