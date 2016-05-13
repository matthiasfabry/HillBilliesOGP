/**
 * 
 */
package hillbillies.model.actionstatement;

import hillbillies.model.Coordinate;
import hillbillies.model.Expression;
import hillbillies.model.statement.ActionStatement;

/**
 *
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class WorkAction implements ActionStatement {

	public WorkAction(Expression<Coordinate> position){
		this.position = position;
	}
	private final Expression<Coordinate> position;
	
	/* (non-Javadoc)
	 * @see hillbillies.model.statement.ActionStatement#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
