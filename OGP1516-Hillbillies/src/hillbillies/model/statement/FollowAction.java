/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class FollowAction implements Statement {

	public FollowAction(Expression<Unit> unit){
		this.toFollow = unit;
	}
	private final Expression<Unit> toFollow;
	
	@Override
	public void execute(Unit unit, VarTracker tracker) {
		try {
			unit.follow(toFollow.evaluate(unit));
		} catch (ModelException e) {
			// shouldn't happen
		}
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException {
		// TODO Auto-generated method stub
		return toFollow.evaluate(unit) != null;
	}
	
}
