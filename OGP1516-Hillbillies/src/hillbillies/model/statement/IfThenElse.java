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

	public IfThenElse(Expression condition, Statement thenBody, Statement elseBody){
		
	}
	
	private Statement thenBody;
	private Statement elseBody;
	private Expression condition;
	
}
