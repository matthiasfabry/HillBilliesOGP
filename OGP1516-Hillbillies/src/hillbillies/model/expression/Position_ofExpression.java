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


	public Position_ofExpression(Expression unit) {
		this.otherUnit = unit;
	}
	private final Expression otherUnit;
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return ((Unit) this.otherUnit.evaluate(unit)).getPosition();
	}

	@Override
	public boolean check(Unit thisUnit) throws FormException{
		if (! (otherUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}
}
