/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Expression;
import hillbillies.model.Statement;
import ogp.framework.util.ModelException;

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
	

	@Override
	public void execute() throws ModelException {
		while(condition.evaluate())
			whileBody.execute();
		
	}

}
