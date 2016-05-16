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
	
	private List<Statement> statements;
	
	public VarTracker getTracker(){
		return this.tracker;
	}
	
	private VarTracker tracker = new VarTracker();
	
	
	@Override
	public void execute(Unit unit) throws ModelException{
		for (Statement statement : statements){
			statement.execute(unit);
		}
	}
}
