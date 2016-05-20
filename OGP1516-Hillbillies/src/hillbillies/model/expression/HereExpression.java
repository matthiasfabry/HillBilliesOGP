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
public class HereExpression extends PositionExpression<Coordinate> {

	public HereExpression() {
	}
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return unit.getPosition();
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (! (unit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}

}
