package hillbillies.model.coordinateexpression;

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
public class SelectedExpression extends PositionExpression<Coordinate> {

	public SelectedExpression(Unit unit, Coordinate thisCoordinate){
		this.world = unit.getWorld();
		this.coordinate = thisCoordinate;
	}
	private final World world;
	private final Coordinate coordinate;
	
	@Override
	public Coordinate evaluate() {
		return coordinate;
	}
	
}
