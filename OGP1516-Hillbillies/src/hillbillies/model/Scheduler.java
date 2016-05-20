/**
 * 
 */
package hillbillies.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * Class Describing a factions task scheduler
 *
 * @invar  Each Scheduler can have its Faction as Faction.
 *       | canHaveAsFaction(this.getFaction())
 * @invar   Each Scheduler must have proper Tasks.
 *        | hasProperTasks()
 *       
 * @author Matthias Fabry and lukas Van Riel
 * @version 1.0
 *
 */
public class Scheduler {

	/**
	 * Initialize this new Scheduler with given Faction.
	 * 
	 * @param  faction
	 *         The Faction for this new Scheduler.
	 * @post   The Faction of this new Scheduler is equal to the given
	 *         Faction.
	 *       | new.getFaction() == faction
	 * @throws ModelException
	 *         This new Scheduler cannot have the given Faction as its Faction.
	 *       | ! canHaveAsFaction(this.getFaction())
	 */
	public Scheduler(Faction faction) {
		this.faction = faction;
	}

	// Faction //

	/**
	 * Return the Faction of this Scheduler.
	 * 
	 * @return | result = this.faction
	 */
	@Basic
	@Raw
	@Immutable
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Variable registering the Faction of this Scheduler.
	 */
	private final Faction faction;

	// Tasks //

	/**
	 * Return the Task associated with this Scheduler at the
	 * given index.
	 * 
	 * @param  index
	 *         The index of the Task to return.
	 * @throws IndexOutOfBoundsException
	 *         The given index is not positive or it exceeds the
	 *         number of Tasks for this Scheduler.
	 *       | (index < 1) || (index > getNbTasks())
	 */
	@Basic
	@Raw
	public Task getTaskAt(int index) throws IndexOutOfBoundsException {
		return tasks.get(index - 1);
	}

	/**
	 * Return the number of Tasks associated with this Scheduler.
	 */
	@Basic
	@Raw
	public int getNbTasks() {
		return tasks.size();
	}

	/**
	 * Check whether this Scheduler can have the given Task
	 * as one of its Tasks.
	 * 
	 * @param  Task
	 *         The Task to check.
	 * @return True if and only if the given Task is effective
	 *         and that Task can have this Scheduler as its Scheduler.
	 *       | result ==
	 *       |   (Task != null) &&
	 *       |   Task.isValidScheduler(this)
	 */
	@Raw
	public boolean canHaveAsTask(Task Task) {
		return (Task != null) && (Task.canHaveAsScheduler(this));
	}

	/**
	 * Check whether this Scheduler can have the given Task
	 * as one of its Tasks at the given index.
	 * 
	 * @param  Task
	 *         The Task to check.
	 * @return False if the given index is not positive or exceeds the
	 *         number of Tasks for this Scheduler + 1.
	 *       | if ( (index < 1) || (index > getNbTasks()+1) )
	 *       |   then result == false
	 *         Otherwise, false if this Scheduler cannot have the given
	 *         Task as one of its Tasks.
	 *       | else if ( ! this.canHaveAsTask(Task) )
	 *       |   then result == false
	 *         Otherwise, true if and only if the given Task is
	 *         not registered at another index than the given index.
	 *       | else result ==
	 *       |   for each I in 1..getNbTasks():
	 *       |     (index == I) || (getTaskAt(I) != Task)
	 */
	@Raw
	public boolean canHaveAsTaskAt(Task Task, int index) {
		if ((index < 1) || (index > getNbTasks() + 1))
			return false;
		if (!this.canHaveAsTask(Task))
			return false;
		for (int i = 1; i < getNbTasks(); i++)
			if ((i != index) && (getTaskAt(i) == Task))
				return false;
		return true;
	}

	/**
	 * Check whether this Scheduler has proper Tasks attached to it.
	 * 
	 * @return True if and only if this Scheduler can have each of the
	 *         Tasks attached to it as a Task at the given index,
	 *         and if each of these Tasks references this Scheduler as
	 *         the Scheduler to which they are attached.
	 *       | result ==
	 *       |   for each I in 1..getNbTasks():
	 *       |     ( this.canHaveAsTaskAt(getTaskAt(I) &&
	 *       |       (getTaskAt(I).getScheduler() == this) )
	 */
	public boolean hasProperTasks() {
		for (int i = 1; i <= getNbTasks(); i++) {
			if (!canHaveAsTaskAt(getTaskAt(i), i))
				return false;
			if (!getTaskAt(i).hasAsScheduler(this))
				return false;
		}
		return true;
	}

	/**
	 * Check whether this Scheduler has the given Task as one of its
	 * Tasks.
	 * 
	 * @param  Task
	 *         The Task to check.
	 * @return The given Task is registered at some position as
	 *         a Task of this Scheduler.
	 *       | for some I in 1..getNbTasks():
	 *       |   getTaskAt(I) == Task
	 */
	public boolean hasAsTask(@Raw Task Task) {
		return tasks.contains(Task);
	}

	/**
	 * Checks whether this Schedular has the given tasks as its tasks.
	 * 
	 * @param tasks
	 * @return true is all tasks are member of this scheduler, false otherwize
	 * 		| If (for all task in tasks : this.hasAsTask(task)) result == true
	 * 		| else result == false
	 */
	public boolean hasAsTasks(Task... tasks) {
		for (Task task : tasks) {
			if (!hasAsTask(task))
				return false;
		}
		return true;
	}

	/**
	 * Add the given Task to the list of Tasks of this Scheduler.
	 * 
	 * @param  Task
	 *         The Task to be added.
	 * @pre    The given Task is effective and already references
	 *         this Scheduler, and this Scheduler does not yet have the given
	 *         Task as one of its Tasks.
	 *       | (Task != null) && (Task.getScheduler() == this) &&
	 *       | (! this.hasAsTask(Task))
	 * @post   The number of Tasks of this Scheduler is
	 *         incremented by 1.
	 *       | new.getNbTasks() == getNbTasks() + 1
	 * @post   This Scheduler has the given Task as its very last Task.
	 *       | new.getTaskAt(getNbTasks()+1) == Task
	 */
	public void addTask(@Raw Task Task) {
		assert (Task != null) && (Task.hasAsScheduler(this))
				&& (!this.hasAsTask(Task));
		tasks.add(Task);
	}

	/**
	 * Add the given Task to the list of Tasks of this Scheduler.
	 * 
	 * @param  Tasks
	 *         The Tasks to be added.
	 * @pre    The given Tasks are effective and already references
	 *         this Scheduler, and this Scheduler does not yet have the given
	 *         Tasks as one of its Tasks.
	 *       | for all Task in tasks (Task != null) && (Task.getScheduler() == this) &&
	 *       | (! this.hasAsTask(Task))
	 * @post   The number of Tasks of this Scheduler is
	 *         incremented by tasks.size().
	 *       | new.getNbTasks() == getNbTasks() + tasks.size()
	 * @post   This Scheduler has the given Tasks as its last Tasks.
	 *       | for all i<tasks.size() : new.getTaskAt(getNbTasks()+i) == Tasks[i]
	 */
	public void addTasks(@Raw Task... tasks) {
		for (Task task : tasks)
			if (canHaveAsTask(task))
				this.addTask(task);
	}

	/**
	 * Replaces a task with another task
	 * 
	 * @param original
	 * 		the original task
	 * @param replacement
	 * 		the new task
	 * @throws ModelException
	 * 		The original task is not member of this scheduler
	 * 		| ! hasAsTask(original)
	 * 		The new task is not a valid task for this scheduler
	 * 		| ! canHaveAsTask(replacement)
	 */
	public void replace(Task original, Task replacement) throws ModelException {
		if (!hasAsTask(original))
			throw new ModelException("Original not in Scheduler");
		if (!canHaveAsTask(replacement))
			throw new ModelException("replacement is not valid");
		else {
			this.removeTask(original);
			this.addTask(replacement);
		}
	}

	/**
	 * Remove the given Task from the list of Tasks of this Scheduler.
	 * 
	 * @param  Task
	 *         The Task to be removed.
	 * @pre    The given Task is effective, this Scheduler has the
	 *         given Task as one of its Tasks, and the given
	 *         Task does not reference any Scheduler.
	 *       | (Task != null) &&
	 *       | this.hasAsTask(Task) &&
	 *       | (Task.getScheduler() == null)
	 * @post   The number of Tasks of this Scheduler is
	 *         decremented by 1.
	 *       | new.getNbTasks() == getNbTasks() - 1
	 * @post   This Scheduler no longer has the given Task as
	 *         one of its Tasks.
	 *       | ! new.hasAsTask(Task)
	 * @post   All Tasks registered at an index beyond the index at
	 *         which the given Task was registered, are shifted
	 *         one position to the left.
	 *       | for each I,J in 1..getNbTasks():
	 *       |   if ( (getTaskAt(I) == Task) and (I < J) )
	 *       |     then new.getTaskAt(J-1) == getTaskAt(J)
	 */
	@Raw
	public void removeTask(Task Task) {
		assert (Task != null) && this.hasAsTask(Task)
				&& (!Task.hasAsScheduler(this));
		tasks.remove(Task);
	}

	/**
	 * Variable referencing a list collecting all the Tasks
	 * of this Scheduler.
	 * 
	 * @invar  The referenced list is effective.
	 *       | Tasks != null
	 * @invar  Each Task registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Task in Tasks:
	 *       |   ( (Task != null) &&
	 *       |     (! Task.isTerminated()) )
	 * @invar  No Task is registered at several positions
	 *         in the referenced list.
	 *       | for each I,J in 0..Tasks.size()-1:
	 *       |   ( (I == J) ||
	 *       |     (Tasks.get(I) != Tasks.get(J))
	 */
	private final ArrayList<Task> tasks = new ArrayList<Task>();

	/**
	 * Returns a task from this scheduler that has the highest priority value
	 * 
	 * @return
	 * 		| For all task in Tasks: task.getPriority <= result.getPriority
	 */
	public Task getHighestPriority(){
		Task theTask = tasks.get(0);
		for (Task task : tasks) 
			if (task.getPriority() >= theTask.getPriority())
				theTask = task;
		return theTask;
	}
	
	/**
	 * Returns the list of tasks that have a higher priority than a given priority
	 * @param priority
	 * 		the priority to exceed
	 * @effect
	 * 		| this.getTasksFromPredicate(task -> task.getPriority > priority)
	 */
	public ArrayList<Task> getTasksHigherThan(int priority) {
		return getTasksFromPredicate(task -> task.getPriority() > priority);
	}

	/**
	 * Returns the list of tasks that are in execution
	 * @effect
	 * 		| this.getTasksFromPredicate(task -> task.inExecution)
	 */
	public ArrayList<Task> getTasksInExecution() {
		return getTasksFromPredicate(task -> task.inExecution);
	}

	/**
	 * Return the list of tasks of this scheduler satisfying a predicate
	 * @param t
	 * 		The predicate to satisfy
	 * @return
	 * 		The list of tasks such that t.test(Task) == true
	 * 		| for all Tasks in result :
	 * 		|   t.test(Task) = true;
	 */
	public ArrayList<Task> getTasksFromPredicate(Predicate<Task> t) {
		return (ArrayList<Task>) tasks.stream().filter(t)
				.collect(Collectors.toList());
	}

//	public Iterator<Task> getIterator(){
//		return new Iterator<Task>() {
//
//			private Task next = null;
//			
//			@Override
//			public boolean hasNext() {
//				if (next == null)
//					next = tasks.condition
//				return false;
//			}
//
//			@Override
//			public Task next() {
//				if (! hasNext()){
//					throw new NoSuchElementException();
//				}
//				Task theTask = next;
//				next = null;
//				return theTask;
//			}
//		};
//	}

	/**
	 * Returns the Simple iterator of the list of Tasks
	 * @return tasks.iterator()
	 */
	public Iterator<Task> getSimpleIterator(){
		return tasks.iterator();
	}
	
}
