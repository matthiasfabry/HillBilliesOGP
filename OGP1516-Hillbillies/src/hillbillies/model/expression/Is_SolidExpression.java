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
public class Is_SolidExpression implements Expression<Boolean> {

	public Is_SolidExpression(Unit unit, Coordinate thiscoordinate){
		this.coordinate = thiscoordinate;
		this.world = unit.getWorld();
	}
	private final Coordinate coordinate;
	private final World world;
	
	@Override
	public Boolean evaluate() {
		return (world.getTerrainAt(coordinate).isImpassable());
	}

}
