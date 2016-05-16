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

	public IfThenElse(Expression<Boolean> condition, Statement thenBody, Statement elseBody){
		this.condition = condition;
		this.thenBody = thenBody;
		this.elseBody = elseBody;
	}
	
	private final Statement thenBody;
	private final Statement elseBody;
	private final Expression<Boolean> condition;
	
	@Override
	public void execute(Unit unit) throws ModelException {
		if (condition.evaluate())
			thenBody.execute(unit);
		else {
			if (elseBody != null) {
				elseBody.execute(unit);
			}
		}	
	}
}
