/**
 * 
 */
package hillbillies.model.statement;

import java.util.HashMap;
import java.util.Map;

import hillbillies.model.expression.Expression;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class VarTracker {

	public VarTracker(){
		
	}
	
	public void assign(String key, Expression<?> value){
		thePairs.put(key, value);
	}
	
	public Expression<?> retrieve(String key){
		return thePairs.get(key);
	}
	
	public int size(){
		return thePairs.size();
	}
	private final Map<String, Expression<?>> thePairs = new HashMap<>();
}
