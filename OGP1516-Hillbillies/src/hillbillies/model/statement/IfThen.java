/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.Expression;
import hillbillies.model.Statement;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class IfThen extends IfThenElse{

	public IfThen(Expression<Boolean> condition, Statement thenBody){
		super(condition,thenBody,null);
	}
		
}
