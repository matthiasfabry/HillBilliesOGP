/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Coordinate;
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
public class WorkAction implements Statement {

	public WorkAction(Expression<Coordinate> position){
		this.position = position;
	}
	private final Expression<Coordinate> position;
	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute(Unit unit) throws ModelException {
		unit.workAt(position.evaluate());
		
	}

}
