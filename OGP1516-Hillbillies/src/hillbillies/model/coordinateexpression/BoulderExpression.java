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
public class BoulderExpression extends PositionExpression<Coordinate> {

	public BoulderExpression(Unit unit) {
		this.position = unit.getInWorldPosition();
		this.boulderPosition = determineBoulderPosition();
	}
	private final Coordinate position;
	private final Coordinate boulderPosition;
	
	@Override
	public Coordinate evaluate() {
		return this.boulderPosition;
	}

	public Coordinate determineBoulderPosition(){
		return null;
	}

}
