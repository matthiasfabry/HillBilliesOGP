package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Coordinate;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Next_toExpression extends PositionExpression<Coordinate> {

	public Next_toExpression(Unit unit, Cube cube){
		this.position = cube.getPlaceInGrid();
		this.world = unit.getWorld();
		this.nexttoposition = determineNext_toPosition();
	}
	private final Coordinate position;
	private final Coordinate nexttoposition;
	private final World world;
	
	@Override
	public Coordinate evaluate() {
		return this.nexttoposition;
	}
	
	public Coordinate determineNext_toPosition(){
		Coordinate pos = null;
		Coordinate[] coordinatelist = position.DirectlyAdjacentCoordinates();
		for (Coordinate coordinate: coordinatelist){
			if (world.getTerrainAt(coordinate).isPassable() && 
					(world.getTerrainAt(coordinate.difference(new Coordinate (0,0,1))).isImpassable() ||
							coordinate.getZ() == 0))
				pos = coordinate;
		}
		return pos;
		
	}

}
