package hillbillies.model.expression;

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
public class Is_PassableExpression implements Expression<Boolean> {

	public Is_PassableExpression(Unit unit, Cube cube){
		this.coordinate = cube.getPlaceInGrid();
		this.world = unit.getWorld();
	}
	private final Coordinate coordinate;
	private final World world;
	
	@Override
	public Boolean evaluate() {
		return (world.getTerrainAt(coordinate).isPassable());
	}
}
