package hillbillies.model.expression;

import hillbillies.model.Expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class AndExpression<T> extends Expression<T> {

	public AndExpression(Expression<T> firstExpression, Expression<T> secondExpression){
		this.first = firstExpression;
		this.second = secondExpression;
	}
	
	@Override
	public T evaluate() {
		return first.evaluate();
	}
	private final Expression<T> first;
	private final Expression<T> second;

}
