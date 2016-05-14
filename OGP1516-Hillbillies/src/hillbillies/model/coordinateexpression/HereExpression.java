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
public class HereExpression<T> extends PositionExpression<Coordinate> {

	public HereExpression(T unit) {
		this.position = unit;
	}
	
	@Override
	public Coordinate evaluate() {
		return (Coordinate) this.position;
	}

	private final T position;
}
