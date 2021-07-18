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
        TestSetupNoGoals s = new TestSetupNoGoals();
        Goal goal = new Goal();
        goal.addGoal(new GoldGoal(200));
        LoopManiaWorld d = s.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        assertEquals(false, goal.isGameWon(testChar));
        testChar.addGold(200);
        assertEquals(true, goal.isGameWon(testChar));
    }

    @Test
    public void testExpGoal() {
        // creates world with EXP goal of 200 and tests the goals completion
        TestSetupNoGoals s = new TestSetupNoGoals();
        Goal goal = new Goal();
        goal.addGoal(new ExperienceGoal(200));
        LoopManiaWorld d = s.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        assertEquals(false, goal.isGameWon(testChar));
        testChar.addEXP(200);
        assertEquals(true, goal.isGameWon(testChar));
    }

    @Test
    public void testCycleGoal() {
        // creates world with cycle goal of 20 and tests the goals completion
        TestSetupNoGoals s = new TestSetupNoGoals();
        Goal goal = new Goal();
        goal.addGoal(new CycleGoal(20));
        LoopManiaWorld d = s.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        assertEquals(false, goal.isGameWon(testChar));
        while (testChar.getCycleCount() < 20) {
            testChar.incrementCycleCount();
        }
        assertEquals(true, goal.isGameWon(testChar));
    }

    @Test
    public void testComplexGoal() {
        // creates world with complex goal consisting of all other goals and tests each
        // one before testing them all
        TestSetupNoGoals s = new TestSetupNoGoals();
        Goal goal = new Goal();
        goal.addGoal(new GoldGoal(200));
        goal.addGoal(new CycleGoal(20));
        goal.addGoal(new ExperienceGoal(200));
        LoopManiaWorld d = s.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
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
