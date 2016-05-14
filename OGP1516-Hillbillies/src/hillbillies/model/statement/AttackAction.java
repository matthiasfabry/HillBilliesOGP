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
		Method theMethod;
		try {
			theMethod = Unit.class.getMethod("attack", new Class[]{Unit.class});
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theMethod;
	}

}
