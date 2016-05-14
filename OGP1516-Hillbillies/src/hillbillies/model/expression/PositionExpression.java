package hillbillies.model.expression;

import hillbillies.model.Coordinate;
/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public abstract class PositionExpression<T> extends Expression<Coordinate> {

	public abstract Coordinate evaluate();
	
}

