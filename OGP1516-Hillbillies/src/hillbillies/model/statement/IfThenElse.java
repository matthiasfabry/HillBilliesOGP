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
		if (condition.evaluate())
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
	public boolean check(Unit unit, VarTracker tracker) {
		return condition.check() && thenBody.check(unit, tracker)
				&& elseBody.check(unit, tracker);
	}

}
