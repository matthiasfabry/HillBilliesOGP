package hillbillies.model.coordinateexpression;

import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class HereExpression extends PositionExpression<Coordinate> {

	public HereExpression(Unit unit) {
		this.position = unit.getInWorldPosition();
	}
	private final Coordinate position;
	
	@Override
	public Coordinate evaluate() {
		return this.position;
	}

}
