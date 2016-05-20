package hillbillies.model.expression;

import hillbillies.model.Coordinate;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Is_PassableExpression implements Expression<Boolean> {

	public Is_PassableExpression(Expression position){
		this.coordinate = position.evaluate();
	}
	private final Coordinate coordinate;
	
	@Override
	public Boolean evaluate(Unit unit) {
		World world = unit.getWorld();
		return (world.getTerrainAt(coordinate).isPassable());
	}

	@Override
	public boolean check(Unit unit) throws FormException{
		if (! (coordinate instanceof Coordinate))
			throw new FormException();
		else 
			return true;
	}
}
