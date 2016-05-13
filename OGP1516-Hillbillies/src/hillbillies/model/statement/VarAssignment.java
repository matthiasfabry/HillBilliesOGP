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
public class VarAssignment<T> implements Statement {

	public VarAssignment(Expression<T> variable){
		this.variable = variable;
	}
	private final Expression<T> variable;
	
	@Override
	public void execute(){
		
	}
	
}
