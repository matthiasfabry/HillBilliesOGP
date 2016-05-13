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
public class IfThen implements Statement{

	public IfThen(Expression<Boolean> condition, Statement thenBody){
		this.thenBody = thenBody;
		this.condition = condition;
	}
	

	private final Statement thenBody;
	private final Expression<Boolean> condition;
		
	@Override
	public void execute(){
		if(condition.evaluate())
			thenBody.execute();
	}
}
