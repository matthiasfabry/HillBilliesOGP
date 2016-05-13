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
public class WhileLoop implements Statement {

	public WhileLoop(Expression<Boolean> condition, Statement whileBody){
		this.condition = condition;
		this.whileBody = whileBody;
	}
	private final Expression<Boolean> condition;
	private final Statement whileBody;
	
	/* (non-Javadoc)
	 * @see hillbillies.model.Statement#execute()
	 */
	@Override
	public void execute() {
		while(condition.evaluate())
			whileBody.execute();
		
	}

}
