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
public class Next_toExpression extends PositionExpression<Coordinate> {

	public Next_toExpression(Coordinate place){
		this.position = place;
		this.nexttoposition = determineNext_toPosition();
	}
	private final Coordinate position;
	private final Coordinate nexttoposition;
	
	@Override
	public Coordinate evaluate() {
		return this.nexttoposition;
	}
	
	public Coordinate determineNext_toPosition(){
		return null;
	}

}
