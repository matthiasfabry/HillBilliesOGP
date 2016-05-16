package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class OrExpression<T> implements Expression<Boolean> {

	public OrExpression(Expression<Boolean> firstExpression, Expression<Boolean> secondExpression){
		this.first = firstExpression;
		this.second = secondExpression;
	}
	
	@Override
	public Boolean evaluate() {
		return (first.evaluate() || second.evaluate());
	}
	private final Expression<Boolean> first;
	private final Expression<Boolean> second;

}
