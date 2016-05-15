package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class AndExpression<T> implements Expression<T> {

	public AndExpression(Expression<T> firstExpression, Expression<T> secondExpression){
		this.first = firstExpression;
		this.second = secondExpression;
	}
	
	@Override
	public T evaluate() {
		return first.evaluate();
		//return second.evaluate();
	}
	private final Expression<T> first;
	private final Expression<T> second;

}
