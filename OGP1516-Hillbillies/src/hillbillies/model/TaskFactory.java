/**
 * 
 */
package hillbillies.model;

import java.util.List;

import hillbillies.model.expression.*;
import hillbillies.model.statement.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;


/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createTasks(java.lang.String, int, java.lang.Object, java.util.List)
	 */
	@Override
	public List<Task> createTasks(String name, int priority, Statement activity,
			List<int[]> selectedCubes) {
		TaskList theTasks = new TaskList(name, priority, activity, selectedCubes);
		return theTasks.getTasks();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAssignment(java.lang.String, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createAssignment(String variableName, Expression<?> value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new VarAssignment<>(variableName, value);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWhile(java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createWhile(Expression<?> condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WhileLoop((Expression<Boolean>) condition, body);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIf(java.lang.Object, java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createIf(Expression<Boolean> condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IfThenElse(condition, ifBody, elseBody);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createBreak(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createPrint(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createPrint(Expression<?> value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new PrintStatement<>(value);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createSequence(java.util.List, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SequenceStatement(statements);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createMoveTo(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createMoveTo(Expression<Coordinate> position,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new MoveAction(unit, position);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWork(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createWork(Expression position,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFollow(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createFollow(Expression unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAttack(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createAttack(Expression<Unit> unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AttackAction(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createReadVariable(java.lang.String, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createReadVariable(String variableName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsSolid(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsSolid(Expression position,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsPassable(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsPassable(Expression position,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsFriend(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsFriend(Expression unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsEnemy(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression<Unit> createIsEnemy(Expression unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Expression<Unit>() {

			@Override
			public Unit evaluate() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsAlive(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsAlive(Expression unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createCarriesItem(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createCarriesItem(Expression unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createNot(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createNot(Expression expression,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAnd(java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createOr(java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createHerePosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createLogPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createBoulderPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWorkshopPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createSelectedPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createNextToPosition(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createNextToPosition(Expression position,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createLiteralPosition(int, int, int, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createLiteralPosition(int x, int y, int z,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createThis(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFriend(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createEnemy(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAny(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createTrue(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFalse(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createPositionOf(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createPositionOf(Expression unit,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
