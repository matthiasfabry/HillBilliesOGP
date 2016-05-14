package hillbillies.model.coordinateexpression;

import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class WorkshopExpression extends PositionExpression<Coordinate> {

	public WorkshopExpression(Unit unit) {
		this.position = unit.getInWorldPosition();
		this.workshopPosition = determineWorkshopPosition();
	}
	private final Coordinate position;
	private final Coordinate workshopPosition;
	
	@Override
	public Coordinate evaluate() {
		return this.workshopPosition;
	}

	public Coordinate determineWorkshopPosition(){
		return null;
	}
	
}
