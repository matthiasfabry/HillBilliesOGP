
package hillbillies.model.expression;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class Boolean_Expression implements Expression<Boolean> {

	public Boolean_Expression(boolean condition) {
		value = condition;
	}
	private final Boolean value;
	
	@Override
	public Boolean evaluate() {
		return this.value;
	}

	@Override
	public boolean check() throws FormException{
		if (!(value instanceof Boolean))
			throw new FormException();
		else 
			return true;
	}

}