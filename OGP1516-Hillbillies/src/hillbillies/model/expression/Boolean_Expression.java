
package hillbillies.model.expression;

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
	private final boolean value;
	
	@Override
	public Boolean evaluate() {
		return this.value;
	}
	
}