package hillbillies.model.coordinateexpression;

import hillbillies.model.Coordinate;
import hillbillies.model.expression.PositionExpression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class SpecifiedExpression extends PositionExpression<Coordinate> {

	public SpecifiedExpression(double x, double y, double z){
		this.specifiedPosition = new Coordinate(x, y, z);
	}
	private final Coordinate specifiedPosition;
	
	@Override
	public Coordinate evaluate() {
		return specifiedPosition;
	}
}