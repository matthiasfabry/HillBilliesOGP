/**
 * 
 */
package hillbillies.model;

import java.util.HashSet;
import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.FormException;
import hillbillies.model.statement.BreakException;
import hillbillies.model.statement.Statement;
import hillbillies.model.statement.VarTracker;
import ogp.framework.util.ModelException;

/**
 * Class representing a Hillbillie Task
 *
 * @invar   Each Task must have proper Schedulers.
 *        | hasProperSchedulers()
 * @invar  Each Task can have its Name as Name .
 *       | canHaveAsName(this.getName())
 * @invar  Each Task can have its Priority as Priority .
 *       | canHaveAsPriority(this.getPriority())
 * @invar  The Unit of each Task must be a valid Unit for any
 *         Task.
 *       | isValidUnit(getUnit())
 * @invar  Each Task can have its Activities as Activities.
 *       | canHaveAsActivities(this.getActivities())
 *
 * @author Matthias Fabry and Lukas Van Riel
 * @version 1.0
 *
 */
public class Task {

	/**
	 * Initialize this new Task with given Name.
	 * 
	 * @param  name
	 *         The Name for this new Task.
	 * @post   This new Task has no Schedulers yet.
	 *       | new.getNbSchedulers() == 0
	 * @post   If the given Priority is a valid Priority for any Task,
	 *         the Priority of this new Task is equal to the given
	 *         Priority. Otherwise, the Priority of this new Task is equal
	 *         to 0.
	 *       | if (isValidPriority(priority))
	 *       |   then new.getPriority() == priority
	 *       |   else new.getPriority() == 0
	 * @post   If the given Name is a valid Name for any Task,
	 *         the Name of this new Task is equal to the given
	 *         Name. Otherwise, the Name of this new Task is equal
	 *         to "task".
	 *       | if (isValidName(name))
	 *       |   then new.getName() == name
	 *       |   else new.getName() == "task"
	 *       
	 */
	public Task(String name, int priority, Statement activity, int[] cube) {
		if (!canHaveAsName(name))
			name = "task";
		this.name = name;
		if (!canHaveAsPriority(priority))
			priority = 0;
		this.priority = priority;
		this.activities = activity;
		location = new Coordinate(cube[1],cube[2],cube[3]);
	}

	// Scheduler //

	/**
	 * Check whether this Task has the given Scheduler as one of its
	 * Schedulers.
	 * 
	 * @param  Scheduler
	 *         The Scheduler to check.
	 */
	@Basic
	@Raw
	public boolean hasAsScheduler(@Raw Scheduler Scheduler) {
		return Schedulers.contains(Scheduler);
	}

	/**
	 * Check whether this Task can have the given Scheduler
	 * as one of its Schedulers.
	 * 
	 * @param  Scheduler
	 *         The Scheduler to check.
	 * @return True if and only if the given Scheduler is effective
	 *         and that Scheduler is a valid Scheduler for a Task.
	 *       | result ==
	 *       |   (Scheduler != null) &&
	 *       |   Scheduler.isValidTask(this)
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler Scheduler) {
		return (Scheduler != null) && (Scheduler.canHaveAsTask(this));
	}

	/**
	 * Check whether this Task has proper Schedulers attached to it.
	 * 
	 * @return True if and only if this Task can have each of the
	 *         Schedulers attached to it as one of its Schedulers,
	 *         and if each of these Schedulers references this Task as
	 *         the Task to which they are attached.
	 *       | for each Scheduler in Scheduler:
	 *       |   if (hasAsScheduler(Scheduler))
	 *       |     then canHaveAsScheduler(Scheduler) &&
	 *       |          (Scheduler.getTask() == this)
	 */
	public boolean hasProperSchedulers() {
		for (Scheduler Scheduler : Schedulers) {
			if (!canHaveAsScheduler(Scheduler))
				return false;
			if (!Scheduler.hasAsTask(this))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Schedulers associated with this Task.
	 *
	 * @return  The total number of Schedulers collected in this Task.
	 *        | result ==
	 *        |   card({Scheduler:Scheduler | hasAsScheduler({Scheduler)})
	 */
	public int getNbSchedulers() {
		return Schedulers.size();
	}

	/**
	 * Add the given Scheduler to the set of Schedulers of this Task.
	 * 
	 * @param  Scheduler
	 *         The Scheduler to be added.
	 * @pre    The given Scheduler is effective and already references
	 *         this Task.
	 *       | (Scheduler != null) && (Scheduler.getTask() == this)
	 * @post   This Task has the given Scheduler as one of its Schedulers.
	 *       | new.hasAsScheduler(Scheduler)
	 */
	public void addScheduler(@Raw Scheduler Scheduler) {
		assert (Scheduler != null) && (Scheduler.hasAsTask(this));
		Schedulers.add(Scheduler);
	}

	/**
	 * Remove the given Scheduler from the set of Schedulers of this Task.
	 * 
	 * @param  Scheduler
	 *         The Scheduler to be removed.
	 * @pre    This Task has the given Scheduler as one of
	 *         its Schedulers, and the given Scheduler does not
	 *         reference any Task.
	 *       | this.hasAsScheduler(Scheduler) &&
	 *       | (Scheduler.getTask() == null)
	 * @post   This Task no longer has the given Scheduler as
	 *         one of its Schedulers.
	 *       | ! new.hasAsScheduler(Scheduler)
	 */
	@Raw
	public void removeScheduler(Scheduler Scheduler) {
		assert this.hasAsScheduler(Scheduler) && (!Scheduler.hasAsTask(this));
		Schedulers.remove(Scheduler);
	}

	/**
	 * Variable referencing a set collecting all the Schedulers
	 * of this Task.
	 * 
	 * @invar  The referenced set is effective.
	 *       | Schedulers != null
	 * @invar  Each Scheduler registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Scheduler in Schedulers:
	 *       |   ( (Scheduler != null) &&
	 *       |     (! Scheduler.isTerminated()) )
	 */
	private final HashSet<Scheduler> Schedulers = new HashSet<Scheduler>();

	// Name //

	/**
	 * Return the Name of this Task.
	 */
	@Basic
	@Raw
	@Immutable
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether this Task can have the given Name as its Name.
	 *  
	 * @param  name
	 *         The Name to check.
	 * @return 
	 *       | result == name != null
	*/
	@Raw
	public boolean canHaveAsName(String name) {
		return name != null;
	}

	/**
	 * Variable registering the Name of this Task.
	 */
	private final String name;

	// Priority //

	/**
	 * Return the Priority of this Task.
	 */
	@Basic
	@Raw
	@Immutable
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Check whether this Task can have the given Priority as its Priority.
	 *  
	 * @param  priority
	 *         The Priority to check.
	 * @return 
	 *       | result == true
	*/
	@Raw
	public boolean canHaveAsPriority(int priority) {
		return true;
	}

	/**
	 * Variable registering the Priority of this Task.
	 */
	private final int priority;

	// Unit //

	/**
	 * Return the Unit of this Task.
	 */
	@Basic
	@Raw
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Check whether the given Unit is a valid Unit for
	 * any Task.
	 *  
	 * @param  Unit
	 *         The Unit to check.
	 * @return 
	 *       | result == 
	*/
	public boolean isValidUnit(Unit unit) {
		if (this.inExecution) {
			return unit != null;
		}
		else
			return unit == null;
	}

	/**
	 * Set the Unit of this Task to the given Unit.
	 * 
	 * @param  unit
	 *         The new Unit for this Task.
	 * @post   The Unit of this new Task is equal to
	 *         the given Unit.
	 *       | new.getUnit() == unit
	 * @throws ModelException
	 *         The given Unit is not a valid Unit for any
	 *         Task.
	 *       | ! isValidUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) throws ModelException {
		if (!isValidUnit(unit))
			throw new ModelException();
		this.unit = unit;
	}

	/**
	 * Variable registering the Unit of this Task.
	 */
	private Unit unit;


	// Activities //

	/**
	 * Return the Activities of this Task.
	 */
	@Basic
	@Raw
	@Immutable
	public Statement getActivities() {
		return this.activities;
	}

	/**
	 * Check whether this Task can have the given Activities as its Activities.
	 *  
	 * @param  activities
	 *         The Activities to check.
	 * @return 
	 *       | result == activities.size()>0
	*/
	@Raw
	public boolean canHaveAsActivities(ArrayList<Statement> activities) {
		return activities.size()>0;
	}

	/**
	 * Variable registering the Activities of this Task.
	 */
	private Statement activities;

	// Location //
	
	/**
	 * Returns the location of the task
	 */
	@Basic
	@Immutable
	public Coordinate getLocation(){
		return this.location;
	}
	
	/**
	 * Variable registering the location of the task
	 */
	private final Coordinate location;
	
	// Executing //
	
	/**
	 * Checks whether a task is well formed or not
	 * 
	 * @return
	 * 		Returns true if no exceptions are thrown in Statement.check(),
	 * 		false if there are, or the method returns false;
	 */
	boolean check(){
		try {
			return getActivities().check(getUnit(), null, null);
		} catch (ModelException | BreakException | FormException e) {
			return false;
		}
	}
	
	/**
	 * Executes the activities of this task
	 * 
	 * @effect
	 * 		| this.getActivities().execute(getUnit(),null,null))
	 */
	public void run(){
		if (this.check()) {
			try {
				inExecution = true;
				getActivities().execute(getUnit(), new VarTracker());
			} catch (BreakException e) {
				// shoudn't happen
			} 
		}
	}
	
	/**
	 * Stops the unit from executing this task, and decouples the 
	 * current unit from this task
	 * 
	 * @post | new.inExecution == false
	 * 		 | new.getUnit() == null
	 */
	public void stop(){
		inExecution = false;
		try {
			this.getUnit().setTask(null);
			this.setUnit(null);
		} catch (ModelException e) {
			// shoudn't happen
		}
	}
	
	/**
	 * flag registering whether this task is being executed.
	 */
	boolean inExecution = false;
	
	// Overrides from object //

	@Override
	public String toString() {
		String theString;
		theString = "Name: " + this.getName() + "\nPriority: "
				+ this.getPriority() + "\nActivities "
				+ this.getActivities().toString();
		return theString;
	}

}
