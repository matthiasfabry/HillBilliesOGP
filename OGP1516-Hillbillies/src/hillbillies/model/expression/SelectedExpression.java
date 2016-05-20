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
		this.world = unit.getWorld();
		this.coordinate = place;
	}
	
	@Override
	public Coordinate evaluate() {
		return coordinate;
	}

	@Override
	public boolean check() throws FormException{
		if (!(coordinate instanceof Coordinate))
			throw new FormException();
		else
			return true;
	}
	
}
