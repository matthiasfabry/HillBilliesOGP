/**
 * 
 */
package hillbillies.model.statement;

import java.util.List;

import hillbillies.model.Statement;

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
	
	public void execute(){
		for (Statement statement : statements){
			statement.execute();
		}
	}
}
