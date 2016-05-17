package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class AndExpression implements Expression<Boolean> {

	public AndExpression(Expression<Boolean> firstExpression, Expression<Boolean> secondExpression){
		this.first = firstExpression;
		this.second = secondExpression;
	}
	private final Expression<Boolean> first;
	private final Expression<Boolean> second;

	
	@Override
	public Boolean evaluate() {
		return (first.evaluate() && second.evaluate());
	}

}
