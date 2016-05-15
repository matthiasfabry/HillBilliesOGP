/**
 * 
 */
package hillbillies.model.statement;

import java.lang.reflect.Method;

import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;


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
	public Method execute(){
		Method theMethod = null;
		try {
			theMethod = Unit.class.getMethod("attack", new Class[]{Unit.class});
		} catch (NoSuchMethodException e) {
			// shouldn't happen
			e.printStackTrace();
		} catch (SecurityException e) {
			// shoudn't happen
			e.printStackTrace();
		}
		return theMethod;
	}

}
