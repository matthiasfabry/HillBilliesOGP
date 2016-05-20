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


	public Position_ofExpression(Expression expression) {
		
	}
	private final Coordinate position;
	private final Unit thisUnit;
	
	@Override
	public Coordinate evaluate() {
		return this.position;
	}

	@Override
	public boolean check() throws FormException{
		if (! (thisUnit instanceof Unit))
			throw new FormException();
		else 
			return true;
	}
}
