package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Terrain;
import hillbillies.model.Unit;
import hillbillies.model.World;

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
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		coordinateSet.add(position);
		while (someWorkshop == null){
			for (Coordinate c: coordinateSet){
				Coordinate[] clist = c.adjacentCoordinates();
				for (Coordinate cc: clist){
					if (! coordinateSet.contains(cc))
						coordinateSet.add(cc);
				}
			}
			for (Coordinate coordinate : coordinateSet){
				if (world.getTerrainAt(coordinate) == Terrain.WORKSHOP)
					someWorkshop = coordinate;
			} 	
		}	
		return someWorkshop;
	}

	@Override
	public boolean check() {
		return true;
	}
	
}
