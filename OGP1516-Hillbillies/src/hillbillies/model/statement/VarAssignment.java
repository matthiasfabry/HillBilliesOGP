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

	public VarAssignment(String name, Expression<T> variable){
		this.variable = variable;
		this.name = name;
	}
	private final Expression<T> variable;
	private final String name;
	
	@Override
	public void execute(){
		
	}
	
}
