/**
 * 
 */
package hillbillies.model.actionstatement;

import hillbillies.model.Coordinate;
import hillbillies.model.Expression;
import hillbillies.model.statement.ActionStatement;
import ogp.framework.util.ModelException;
import hillbillies.model.Unit;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class MoveAction implements ActionStatement {

	public MoveAction(Expression<Unit> unit, Expression<Coordinate> position){
		this.position = position;
		this.unit = unit;
	}
	private final Expression<Coordinate> position;
	private final Expression<Unit> unit;
	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute() throws ModelException {
		if (unit.evaluate().isValidPosition(position.evaluate())){
			unit.evaluate().moveTo((int) position.evaluate().getX(), (int) position.evaluate().getY(),
					(int) position.evaluate().getZ());
		}
	}

}
