/**
 * 
 */
package hillbillies.model;

import java.util.List;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class TaskList {

	public TaskList(List<Task> tasks){
		for (Task task : tasks)
			tasks.add(task);
	}
	
	private List<Task> tasks;
}
