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
public class WhileLoop implements Statement {

	public WhileLoop(Expression<Boolean> condition, Statement whileBody){
		this.condition = condition;
		this.whileBody = whileBody;
	}
	private final Expression<Boolean> condition;
	private final Statement whileBody;
	
	private boolean buzy = true;

	@Override
	public void execute(Unit unit) throws ModelException {
		while(condition.evaluate() && buzy)
			whileBody.execute(unit);
		
	}

}
