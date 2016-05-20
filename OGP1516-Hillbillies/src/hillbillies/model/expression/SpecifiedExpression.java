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
public class SpecifiedExpression extends PositionExpression<Coordinate> {

	public SpecifiedExpression(double x, double y, double z){
		this.specifiedPosition = new Coordinate(x, y, z);
	}
	private final Coordinate specifiedPosition;
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return specifiedPosition;
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		return true;
	}
}