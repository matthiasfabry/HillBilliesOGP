/**
 * 
 */
package hillbillies.model.statement;

import java.util.HashMap;
import java.util.Map;

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
	
	public void assign(String key, Object value){
		thePairs.put(key, value);
	}
	
	public Object retrieve(String key){
		return thePairs.get(key);
	}
	
	private final Map<String, Object> thePairs = new HashMap<>();
}
