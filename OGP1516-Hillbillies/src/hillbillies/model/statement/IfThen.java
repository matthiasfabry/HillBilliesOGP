/**
 * 
 */
package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

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
