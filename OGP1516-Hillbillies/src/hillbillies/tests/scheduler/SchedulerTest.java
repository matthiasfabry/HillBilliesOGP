package hillbillies.tests.scheduler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class SchedulerTest {

	private static Faction theFaction;
	private static Scheduler theScheduler;
	private static Task taskA;
	private static Task taskB;
	private static Task taskC;
	private static Task taskD;
	
	@Before
	public void setUp() throws ModelException {
		theScheduler = new Scheduler(theFaction);
		theScheduler.addTask(taskA);
		theScheduler.addTasks(taskA, taskB, taskC);
		
		
	}

	
	@Test
	public void getFactionTest() {
		assertEquals(theFaction, theScheduler.getFaction());
	}
	
	@Test
	public void HasasTaskTest() {
		assertTrue(theScheduler.hasAsTask(taskA));
		assertFalse(theScheduler.hasAsTask(taskD));
	}
	
	@Test
	public void HasasTasksTest(){
		assertTrue(theScheduler.hasAsTasks(taskC, taskB));
		assertFalse(theScheduler.hasAsTasks(taskA, taskB, taskD));
	}
	
	@Test 
	public void HasNbTasksTest() {
		assertEquals(3, theScheduler.getNbTasks());
	}
	
	@Test
	public void canHaveAsTaskTest(){
		assertTrue(theScheduler.canHaveAsTask(taskD));
	}

}
