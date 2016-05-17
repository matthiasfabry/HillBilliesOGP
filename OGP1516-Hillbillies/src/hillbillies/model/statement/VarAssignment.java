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
public class VarAssignment<T> implements Statement {

	public VarAssignment(String name, Expression<T> variable){
		this.variable = variable;
		this.name = name;
	}
	private final Expression<T> variable;
	private final String name;
	
	@Override
	public void execute(Unit unit, VarTracker tracker){
		tracker.assign(name, variable);
	}

	/* (non-Javadoc)
	 * @see hillbillies.model.statement.Statement#check(hillbillies.model.Unit, hillbillies.model.statement.VarTracker, hillbillies.model.statement.Statement)
	 */
	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException {
		return true;
	}
	
}
