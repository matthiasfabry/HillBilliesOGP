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
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class AttackAction implements Statement {

	public AttackAction(Expression<Unit> unit){
		this.victim = unit;
	}
	private final Expression<Unit> victim;
	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute(Unit unit) throws ModelException {
		unit.attack(victim.evaluate());
	}

}
