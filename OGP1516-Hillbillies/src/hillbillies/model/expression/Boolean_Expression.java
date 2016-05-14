
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
public class Boolean_Expression extends Expression<Boolean> {

	public Boolean_Expression(Boolean condition) {
		this.value = condition;
	}

	
	@Override
	public Boolean evaluate() {
		return this.value;
	}
	private final boolean value;
	
}