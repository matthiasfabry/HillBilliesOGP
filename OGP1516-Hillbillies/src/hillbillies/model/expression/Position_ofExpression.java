package hillbillies.model.expression;

import hillbillies.model.Coordinate;
import hillbillies.model.Unit;

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

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}
}
