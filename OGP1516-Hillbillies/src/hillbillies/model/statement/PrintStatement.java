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
public class PrintStatement<T> implements Statement {

	public PrintStatement(Expression<T> printBody){
		this.printBody = printBody;
	}
	
	private final Expression<T> printBody;

	@Override
	public void execute() {
		System.out.println(this.printBody.evaluate().toString());
		
	}

	
	
}
