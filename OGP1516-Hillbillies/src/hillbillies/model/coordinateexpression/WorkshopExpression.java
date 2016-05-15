package hillbillies.model.coordinateexpression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Terrain;
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
public class WorkshopExpression extends PositionExpression<Coordinate> {

	public WorkshopExpression(Unit unit) {
		this.thisUnit = unit;
		this.workshopPosition = determineWorkshopPosition();
	}
	private final Unit thisUnit;
	private final Coordinate workshopPosition;
	
	@Override
	public Coordinate evaluate() {
		return this.workshopPosition;
	}

	public Coordinate determineWorkshopPosition(){
		Coordinate position = thisUnit.getInWorldPosition();
		World world = thisUnit.getWorld();
		Coordinate someWorkshop = null;
		Coordinate[] coordinatelist = position.adjacentCoordinates();
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		for (Coordinate c: coordinatelist){
			coordinateSet.add(c);
		}
		while (someWorkshop == null){
			for (Coordinate coordinate : coordinateSet){
				if (world.getTerrainAt(coordinate) == Terrain.WORKSHOP)
					someWorkshop = coordinate;
			} 
			for (Coordinate cc: coordinateSet){
				Coordinate[] clist = cc.adjacentCoordinates();
				for (Coordinate ccc: clist){
					if (! coordinateSet.contains(ccc))
						coordinateSet.add(ccc);
				}
			}	
		}	
		return someWorkshop;
	}
	
}
