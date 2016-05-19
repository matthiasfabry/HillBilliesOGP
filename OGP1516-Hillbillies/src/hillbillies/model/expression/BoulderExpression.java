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
	public boolean check() throws FormException{
		// TODO Auto-generated method stub
		return false;
	}

}
