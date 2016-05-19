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

	public HereExpression(Unit unit) {
		this.position = unit.getInWorldPosition();
		this.unit = unit;
	}
	private final Coordinate position;
	private final Unit unit;
	
	@Override
	public Coordinate evaluate() {
		return this.position;
	}

	@Override
	public boolean check() throws FormException{
		if (! (unit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}

}
