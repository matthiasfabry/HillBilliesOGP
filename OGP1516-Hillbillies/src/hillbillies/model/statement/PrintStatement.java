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
public class PrintStatement<T> implements Statement {

	public PrintStatement(Expression<T> printBody){
		this.printBody = printBody;
	}
	
	private final Expression<T> printBody;

	@Override
	public void execute(Unit unit, VarTracker tracker) {
		System.out.println(printBody.evaluate().toString());
		
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
