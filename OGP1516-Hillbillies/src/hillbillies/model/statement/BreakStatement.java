/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class BreakStatement implements Statement {

	@Override
	public void execute(Unit unit, VarTracker tracker) throws BreakException {
		throw new BreakException();
	}

	/* (non-Javadoc)
	 * @see hillbillies.model.statement.Statement#check(hillbillies.model.Unit, hillbillies.model.statement.VarTracker)
	 */
	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException {
		if (parent instanceof WhileLoop)
			return true;
		else
			return false;
	}
}
