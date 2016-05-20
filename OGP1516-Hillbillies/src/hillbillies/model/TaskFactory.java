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
 * @author Matthias Fabry and Lukas Van Riel
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
	public Statement createAssignment(String variableName, Expression value,
			SourceLocation sourceLocation) {
		return new VarAssignment<>(variableName, value);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWhile(java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new WhileLoop(condition, body);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIf(java.lang.Object, java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		return new IfThenElse(condition, ifBody, elseBody);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createBreak(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createPrint(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createPrint(Expression value,
			SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createSequence(java.util.List, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new SequenceStatement(statements);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createMoveTo(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createMoveTo(Expression position,
			SourceLocation sourceLocation) {
		return new MoveAction(position);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWork(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createWork(Expression position,
			SourceLocation sourceLocation) {
		return new WorkAction(position);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFollow(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createFollow(Expression unit,
			SourceLocation sourceLocation) {
		return new FollowAction(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAttack(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Statement createAttack(Expression unit,
			SourceLocation sourceLocation) {
		return new AttackAction(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createReadVariable(java.lang.String, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createReadVariable(Unit unit, String variableName,
			SourceLocation sourceLocation) {
		return new BracketExpression(unit, variableName);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsSolid(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsSolid(Unit unit, Cube cube,
			SourceLocation sourceLocation) {
		return new Is_SolidExpression(unit, cube);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsPassable(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsPassable(Unit unit, Cube cube,
			SourceLocation sourceLocation) {
		return new Is_PassableExpression(unit, cube);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsFriend(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsFriend(Unit unit, Unit unitt,
			SourceLocation sourceLocation) {
		return new Is_FriendExpression(unit, unitt);
//		return new Expression<Unit>() {
//
//			@Override
//			public Unit evaluate() {
//				// TODO Auto-generated method stub
//				return null;
//			}	
//		};
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsEnemy(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsEnemy(Unit unit, Unit unitt,
			SourceLocation sourceLocation) {
		return new Is_EnemyExpression(unit, unitt);
		
//		public Expression<Unit> createIsEnemy(Unit unit, Unit unitt,
//				SourceLocation sourceLocation) 
//		return new Expression<Unit>() {
//
//			@Override
//			public Unit evaluate() {
				// TODO Auto-generated method stub
//				return null;
//			}
//		};
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsAlive(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsAlive(Unit unit,
			SourceLocation sourceLocation) {
		return new Is_AliveExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createCarriesItem(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createCarriesItem(Unit unit,
			SourceLocation sourceLocation) {
		return new Carries_Item(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createNot(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createNot(Expression expression,
			SourceLocation sourceLocation) {
		return new NotExpression(expression);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAnd(java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new AndExpression(left, right);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createOr(java.lang.Object, java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new OrExpression(left, right);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createHerePosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createHerePosition(Unit unit, SourceLocation sourceLocation) {
		return new HereExpression(unit);
	}
	
	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createLogPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createLogPosition(Unit unit, SourceLocation sourceLocation) {
		return new LogExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createBoulderPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createBoulderPosition(Unit unit, SourceLocation sourceLocation) {
		return new BoulderExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWorkshopPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createWorkshopPosition(Unit unit, SourceLocation sourceLocation) {
		return new WorkshopExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createSelectedPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createSelectedPosition(Unit unit, Coordinate coordinate, SourceLocation sourceLocation) {
		return new SelectedExpression(unit, coordinate);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createNextToPosition(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createNextToPosition(Unit unit, Coordinate coordinate,
			SourceLocation sourceLocation) {
		return new Next_toExpression(unit, coordinate);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createLiteralPosition(int, int, int, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createLiteralPosition(int x, int y, int z,
			SourceLocation sourceLocation) {
		return new SpecifiedExpression(x,y,z);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createThis(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createThis(Unit unit,SourceLocation sourceLocation) {
		return new ThisExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFriend(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createFriend(Unit unit,SourceLocation sourceLocation) {
		return new FriendExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createEnemy(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createEnemy(Unit unit,SourceLocation sourceLocation) {
		return new EnemyExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createAny(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createTrue(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new Boolean_Expression(true);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFalse(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new Boolean_Expression(false);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createPositionOf(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createPositionOf(Unit unit,
			SourceLocation sourceLocation) {
		return new Position_ofExpression(unit);
	}

}
