/**
 * 
 */
package hillbillies.model.statement;

import java.lang.reflect.Method;

import ogp.framework.util.ModelException;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public interface Statement {

	Method execute() throws ModelException;
}
