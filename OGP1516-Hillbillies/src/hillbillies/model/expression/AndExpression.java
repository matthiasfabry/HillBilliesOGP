package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
 * @param <T>
*
*/
public class AndExpression<T> implements Expression<Expression[]> {

	public AndExpression(Expression<T> firstExpression, Expression<T> secondExpression){
		this.first = firstExpression;
		this.second = secondExpression;
	}
	
	@Override
	public Expression[] evaluate() {
		Expression[] list = Expression[2];
		list[0] = first;
		list[1] = second;
		return list;
	}
	private final Expression<T> first;
	private final Expression<T> second;

}
