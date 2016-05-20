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
public class WhileLoop implements Statement {

	public WhileLoop(Expression<Boolean> condition, Statement whileBody){
		this.condition = condition;
		this.whileBody = whileBody;
	}
	private final Expression<Boolean> condition;
	private final Statement whileBody;
	

	@Override
	public void execute(Unit unit, VarTracker tracker){
		while(condition.evaluate(unit))
			try {
				whileBody.execute(unit, tracker);
			} catch (BreakException e) {
				break;
			}
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException, FormException {
		// TODO Auto-generated method stub
		return condition.check(unit) && whileBody.check(unit, tracker, parent);
	}

}
