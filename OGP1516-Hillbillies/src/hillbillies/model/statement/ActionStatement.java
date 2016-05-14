/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Statement;
import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public interface ActionStatement extends Statement {

	
	@Override
	public void execute() throws ModelException;
}
