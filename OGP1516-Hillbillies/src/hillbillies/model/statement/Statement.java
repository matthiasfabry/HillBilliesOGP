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
public interface Statement {

	void execute(Unit unit, VarTracker tracker) throws BreakException;
	
	boolean check(Unit unit, VarTracker tracker, Statement parent) throws ModelException, BreakException;
}
