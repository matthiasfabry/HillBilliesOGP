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
	
	@Override
	public Coordinate evaluate(Unit unit) {
		return 
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (!(coordinate instanceof Coordinate))
			throw new FormException();
		else
			return true;
	}
	
}
