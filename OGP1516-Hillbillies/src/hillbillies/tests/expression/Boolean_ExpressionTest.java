package hillbillies.tests.expression;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.expression.Boolean_Expression;

public class Boolean_ExpressionTest {

	@Test
	public void testFalseEvaluate() {
		assertEquals(false, Boolean_Expression.Boolean_Expression(false).evaluate);;
	}

}
