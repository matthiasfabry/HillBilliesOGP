/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

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

	
	
}
