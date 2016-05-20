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
		while(condition.evaluate())
			try {
				whileBody.execute(unit, tracker);
			} catch (BreakException e) {
				break;
			}
	}


	/* (non-Javadoc)
	 * @see hillbillies.model.statement.Statement#check(hillbillies.model.Unit, hillbillies.model.statement.VarTracker, hillbillies.model.statement.Statement)
	 */
	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException, FormException {
		// TODO Auto-generated method stub
		return condition.check() && whileBody.check(unit, tracker, parent);
	}

}
