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
	public void execute(Unit unit, VarTracker tracker) {
		try {
			unit.attack(victim.evaluate(unit));
		} catch (ModelException e) {
			// shoudn't happen
		}
	}

	/* (non-Javadoc)
	 * @see hillbillies.model.statement.Statement#check(hillbillies.model.Unit, hillbillies.model.statement.VarTracker)
	 */
	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent){
		return unit.getFaction() != victim.evaluate(unit).getFaction();
	}
	
	

}
