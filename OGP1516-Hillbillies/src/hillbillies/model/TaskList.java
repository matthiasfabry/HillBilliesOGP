/**
 * 
 */
package hillbillies.model;

import java.util.List;

import hillbillies.model.statement.Statement;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class TaskList {

	public TaskList(String name, int priority, Statement activity, List<int[]> cubes){
		for (int[] cube : cubes){
			Task theTask = new Task(name, priority, activity, cube);
			tasks.add(theTask);		
		}	
	}
	
	public List<Task> getTasks(){
		return this.tasks;
	}
	private List<Task> tasks;
}
