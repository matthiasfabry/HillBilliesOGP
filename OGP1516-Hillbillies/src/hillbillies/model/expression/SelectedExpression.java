package hillbillies.model.expression;

import hillbillies.model.Coordinate;

import hillbillies.model.Unit;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class SelectedExpression extends PositionExpression<Coordinate> {

	public SelectedExpression(){
	}
	
	private Coordinate coordinate = new Coordinate(0,0,0);
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return coordinate;
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (!(coordinate instanceof Coordinate))
			throw new FormException();
		else
			return true;
	}
	
}
