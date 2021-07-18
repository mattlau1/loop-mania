package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Goals.CycleGoal;
import unsw.loopmania.Goals.ExperienceGoal;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.GoldGoal;

public class GoalTest {

    @Test
    public void testGoldGoal() {
        // creates world with gold goal of 200 and tests the goals completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set the goal to accumulate 200 gold
        goal.addGoal(new GoldGoal(200));
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks the game is not yet one as goal isnt complete
        assertEquals(false, goal.isGameWon(testChar));
        // adds 200 gold to complete the goal
        testChar.addGold(200);
        // check that gold is completed
        assertEquals(true, goal.isGameWon(testChar));
    }

    @Test
    public void testExpGoal() {
        // creates world with EXP goal of 200 and tests the goals completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set the goal to accumulate 200 experience
        goal.addGoal(new ExperienceGoal(200));
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks the game is not yet one as goal isnt complete
        assertEquals(false, goal.isGameWon(testChar));
        // adds 200 exp to complete the goal
        testChar.addEXP(200);
        // check that gold is completed
        assertEquals(true, goal.isGameWon(testChar));
    }

    @Test
    public void testCycleGoal() {
        // creates world with cycle goal of 20 and tests the goals completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set the goal to accumulate 20 cycles
        goal.addGoal(new CycleGoal(20));
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks the game is not yet one as goal isnt complete
        assertEquals(false, goal.isGameWon(testChar));
        // adds 20 cycles to complete the goal
        while (testChar.getCycleCount() < 20) {
            testChar.incrementCycleCount();
        }
        // check that gold is completed
        assertEquals(true, goal.isGameWon(testChar));
    }

    @Test
    public void testComplexGoal() {
        // creates world with complex goal consisting of all other goals and tests each
        // one before testing them all
        TestSetupNoGoals s = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set all the goals
        goal.addGoal(new GoldGoal(200));
        goal.addGoal(new CycleGoal(20));
        goal.addGoal(new ExperienceGoal(200));
        LoopManiaWorld d = s.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        // checks that the game is not won until ALL goals are completed
        assertEquals(false, goal.isGameWon(testChar));
        testChar.addGold(200);
        assertEquals(false, goal.isGameWon(testChar));
        while (testChar.getCycleCount() < 20) {
            testChar.incrementCycleCount();
        }
        assertEquals(false, goal.isGameWon(testChar));
        testChar.addEXP(200);
        assertEquals(true, goal.isGameWon(testChar));
    }
}
