/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class FollowAction implements Statement {

	public FollowAction(Expression<Unit> unit){
		this.toFollow = unit;
	}
	private final Expression<Unit> toFollow;
	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute(Unit unit) {
		unit.follow(toFollow.evaluate());
		
	}

}
