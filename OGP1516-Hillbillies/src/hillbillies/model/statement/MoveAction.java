/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Coordinate;
import ogp.framework.util.ModelException;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class MoveAction implements Statement {

	public MoveAction(Expression<Coordinate> position){
		this.position = position;
	}
	private final Expression<Coordinate> position;

	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute(Unit unit, VarTracker tracker) throws ModelException {
		if (unit.isValidPosition(position.evaluate())){
			unit.moveTo((int) position.evaluate().getX(), (int) position.evaluate().getY(),
					(int) position.evaluate().getZ());
		}
	}

}
