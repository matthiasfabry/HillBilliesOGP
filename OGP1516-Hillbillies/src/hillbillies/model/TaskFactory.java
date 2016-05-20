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
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new BracketExpression(variableName);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsSolid(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsSolid(Expression coordinate, SourceLocation sourceLocation) {
		return new Is_SolidExpression(coordinate);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsPassable(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsPassable(Expression coordinate, SourceLocation sourceLocation) {
		return new Is_PassableExpression(coordinate);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsFriend(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
//	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
//		return new Is_FriendExpression(unit);
	public Expression<Boolean> createIsFriend(Expression unit,
			SourceLocation sourceLocation) {
		return new Expression<Boolean>() {
			
		@Override
		public Boolean evaluate(Unit thisUnit) {
			if (thisUnit.getFaction() != ((Unit) unit.evaluate(thisUnit)).getFaction())
				return true;
			else 
				return false;
		}

		@Override
		public boolean check(Unit thisUnit) throws FormException{
			if (! (thisUnit instanceof Unit) || (! (unit.evaluate((Unit) unit) instanceof Unit)))
				throw new FormException();
			else 
				return true;
		}
		};
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsEnemy(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	
//	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
//		return new Is_EnemyExpression(unit);
	
	public Expression<Boolean> createIsEnemy(Expression unit,
			SourceLocation sourceLocation) {
	return new Expression<Boolean>() {

		@Override
		public Boolean evaluate(Unit thisUnit) {
			if (thisUnit.getFaction() != ((Unit) unit.evaluate(thisUnit)).getFaction())
				return true;
			else 
				return false;
		}

		@Override
		public boolean check(Unit thisUnit) throws FormException{
			if (! (thisUnit instanceof Unit) || (! (unit.evaluate((Unit) unit) instanceof Unit)))
				throw new FormException();
			else 
				return true;
		}
		};
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createIsAlive(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new Is_AliveExpression(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createCarriesItem(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new Carries_Item(unit);
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createNot(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
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
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression();
	}
	
	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createLogPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new LogExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createBoulderPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createWorkshopPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createSelectedPosition(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createNextToPosition(java.lang.Object, hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createNextToPosition(Expression coordinate,
			SourceLocation sourceLocation) {
		return new Next_toExpression(coordinate);
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
	public Expression createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createFriend(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	/* (non-Javadoc)
	 * @see hillbillies.part3.programs.ITaskFactory#createEnemy(hillbillies.part3.programs.SourceLocation)
	 */
	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
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
	public Expression createPositionOf(Expression unit,
			SourceLocation sourceLocation) {
		return new Position_ofExpression(unit);
	}

}
