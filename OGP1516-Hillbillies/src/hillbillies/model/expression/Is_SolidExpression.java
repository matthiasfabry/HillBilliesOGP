package hillbillies.model.expression;

import hillbillies.model.Coordinate;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Is_SolidExpression implements Expression<Boolean> {

	public Is_SolidExpression(Coordinate thiscoordinate, World thisworld){
		this.coordinate = thiscoordinate;
		this.world = thisworld;
	}
	private final Coordinate coordinate;
	private final World world;
	
	@Override
	public Boolean evaluate() {
		if (world.getTerrainAt(coordinate).isImpassable())
			return true;
		else 
			return false;
	}

}
