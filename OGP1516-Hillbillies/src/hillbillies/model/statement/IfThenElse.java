/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Expression;
import hillbillies.model.Statement;

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
	public void execute() {
		if (condition.evaluate())
			thenBody.execute();
		else
			elseBody.execute();	
	}
}
