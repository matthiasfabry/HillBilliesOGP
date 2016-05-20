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

	public MoveAction(Expression<Coordinate> position) {
		this.position = position;
	}
	private final Expression<Coordinate> position;

	@Override
	public void execute(Unit unit, VarTracker tracker) {
		if (unit.isValidPosition(position.evaluate(unit))) {
			try {
				unit.moveTo((int) position.evaluate(unit).getX(),
						(int) position.evaluate(unit).getY(),
						(int) position.evaluate(unit).getZ());
			} catch (ModelException e) {
				// shoudn't happen
			}
		}
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException {
		// TODO Auto-generated method stub
		return unit.isValidPosition(position.evaluate(unit));
	}
}
