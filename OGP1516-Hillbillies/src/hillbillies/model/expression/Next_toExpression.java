package hillbillies.model.expression;



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
public class Next_toExpression extends PositionExpression<Coordinate> {

	public Next_toExpression(Expression position){
		this.position = position;
	}
	private final Expression position;
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return determineNext_toPosition(unit);
	}
	
	public Coordinate determineNext_toPosition(Unit thisUnit){
		World world = thisUnit.getWorld();
		Coordinate pos = null;
		Coordinate[] coordinatelist = ((Coordinate) position.evaluate(thisUnit)).DirectlyAdjacentCoordinates();
		for (Coordinate coordinate: coordinatelist){
			if (world.getTerrainAt(coordinate).isPassable() && 
					(world.getTerrainAt(coordinate.difference(new Coordinate (0,0,1))).isImpassable() ||
							coordinate.getZ() == 0))
				pos = coordinate;
		}
		return pos;
		
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (!(position instanceof Coordinate))
			throw new FormException();
		else
			return true;
	}

}
