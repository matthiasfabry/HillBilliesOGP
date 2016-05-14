package hillbillies.model.coordinateexpression;

import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class LogExpression extends PositionExpression<Coordinate> {

	public LogExpression(Unit unit) {
		this.position = unit.getInWorldPosition();
		this.logPosition = determineLogPosition();
	}
	private final Coordinate position;
	private final Coordinate logPosition;
	
	@Override
	public Coordinate evaluate() {
		return this.logPosition;
	}

	public Coordinate determineLogPosition(){
		return null;
	}

}
