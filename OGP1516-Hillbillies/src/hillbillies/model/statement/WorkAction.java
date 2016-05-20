/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Coordinate;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.FormException;
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
	
	@Override
	public void execute(Unit unit, VarTracker tracker) {
		try {
			unit.workAt(position.evaluate());
		} catch (ModelException e) {
			// shoudn't happen
		}
		
	}

	@Override
	public boolean check(Unit unit, VarTracker tracker, Statement parent)
			throws ModelException, BreakException, FormException {
		// TODO Auto-generated method stub
		return unit.isValidPosition(position.evaluate());
	}

}
