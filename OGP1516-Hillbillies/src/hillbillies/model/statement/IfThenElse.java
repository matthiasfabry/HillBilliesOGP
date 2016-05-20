/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.FormException;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class IfThenElse implements Statement {

	public IfThenElse(Expression<Boolean> condition, Statement thenBody,
			Statement elseBody) {
		this.condition = condition;
		this.thenBody = thenBody;
		this.elseBody = elseBody;
	}

	private final Statement thenBody;
	private final Statement elseBody;
	private final Expression<Boolean> condition;

	@Override
	public void execute(Unit unit, VarTracker tracker) {
		if (condition.evaluate(unit))
			try {
				thenBody.execute(unit, tracker);
			} catch (BreakException e) {
				// shoudn't happen
			}
		else {
			if (elseBody != null) {
				try {
					elseBody.execute(unit, tracker);
				} catch (BreakException e) {
					// shoudn't happen
				}
			}
		}
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException, FormException {
		return condition.check(unit) && thenBody.check(unit, tracker, parent)
				&& elseBody.check(unit, tracker, parent);
	}

}
