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
	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute(Unit unit, VarTracker tracker) {
		try {
			unit.follow(toFollow.evaluate());
		} catch (ModelException e) {
			// shouldn't happen
		}
	}

	/* (non-Javadoc)
	 * @see hillbillies.model.statement.Statement#check(hillbillies.model.Unit, hillbillies.model.statement.VarTracker, hillbillies.model.statement.Statement)
	 */
	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
