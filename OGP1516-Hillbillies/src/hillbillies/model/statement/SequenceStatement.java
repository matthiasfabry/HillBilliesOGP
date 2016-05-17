/**
 * 
 */
package hillbillies.model.statement;

import java.util.List;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class SequenceStatement implements Statement {

	public SequenceStatement(List<Statement> statements){
		this.statements = statements;
	}
	
	private final List<Statement> statements;
	
	public VarTracker getTracker(){
		return this.tracker;
	}
	
	private VarTracker tracker = new VarTracker();
	
	
	@Override
	public void execute(Unit unit, VarTracker tracker){
		for (Statement statement : statements){
			try {
				statement.execute(unit, tracker);
			} catch (BreakException e) {
				// shoudn't happen
			}
		}
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent) {
		for (Statement statement : statements){
			try {
				statement.check(unit, tracker, (SequenceStatement) this);
			} catch (BreakException | ModelException e) {
				return false;
			}
		}
		return true;
			
			
	}
}
