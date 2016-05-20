package hillbillies.model.expression;

import hillbillies.model.Unit;

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
	public Boolean evaluate(Unit unit) {
		return (first.evaluate(unit) && second.evaluate(unit));
	}


	@Override
	public boolean check(Unit unit) throws FormException{
		if (!(first.evaluate(unit) instanceof Boolean) || (!(second.evaluate(unit) instanceof Boolean)))
			throw new FormException();
		else 
			return true;
	}

}
