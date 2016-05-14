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
public class Position_ofExpression extends PositionExpression<Coordinate> {


	public Position_ofExpression(Unit unit) {
		this.position = unit.getInWorldPosition();
	}
	private final Coordinate position;
	
	@Override
	public Coordinate evaluate() {
		return this.position;
	}
}
