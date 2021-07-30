package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Goals.AndComplex;
import unsw.loopmania.Goals.ComplexGoal;
import unsw.loopmania.Goals.CycleGoal;
import unsw.loopmania.Goals.ExperienceGoal;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.GoldGoal;
import unsw.loopmania.Goals.OrComplex;

public class GoalTest {

    @Test
    public void testGoldGoal() {
        // creates world with gold goal of 200 and tests the goals completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set the goal to accumulate 200 gold
        goal.addSimpleGoal(new GoldGoal(200));
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks the game is not yet one as goal isnt complete
        assertEquals(false, goal.isGameWon());
        // adds 200 gold to complete the goal
        testChar.addGold(200);
        // check that gold is completed
        assertEquals(true, goal.isGameWon());
    }

    @Test
    public void testExpGoal() {
        // creates world with EXP goal of 200 and tests the goals completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set the goal to accumulate 200 experience
        goal.addSimpleGoal(new ExperienceGoal(200));
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks the game is not yet one as goal isnt complete
        assertEquals(false, goal.isGameWon());
        // adds 200 exp to complete the goal
        testChar.addEXP(200);
        // check that gold is completed
        assertEquals(true, goal.isGameWon());
    }

    @Test
    public void testCycleGoal() {
        // creates world with cycle goal of 20 and tests the goals completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // set the goal to accumulate 20 cycles
        goal.addSimpleGoal(new CycleGoal(20));
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks the game is not yet one as goal isnt complete
        assertEquals(false, goal.isGameWon());
        // adds 20 cycles to complete the goal
        while (testChar.getCycleCount() < 20) {
            testChar.incrementCycleCount();
        }
        // check that gold is completed
        assertEquals(true, goal.isGameWon());
    }

    // @Test
    // public void testComplexGoal() {
    //     // creates world with complex goal consisting of all other goals and tests each
    //     // one before testing them all
    //     TestSetupNoGoals s = new TestSetupNoGoals();
    //     Goal goal = new Goal();
    //     // set all the goals
    //     goal.addSimpleGoal(new GoldGoal(200));
    //     goal.addSimpleGoal(new CycleGoal(20));
    //     goal.addSimpleGoal(new ExperienceGoal(200));
    //     LoopManiaWorld d = s.makeTestWorld(goal);
    //     Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
    //     d.setCharacter(testChar);
    //     // checks that the game is not won until ALL goals are completed
    //     assertEquals(false, goal.isGameWon());
    //     testChar.addGold(200);
    //     assertEquals(false, goal.isGameWon());
    //     while (testChar.getCycleCount() < 20) {
    //         testChar.incrementCycleCount();
    //     }
    //     assertEquals(false, goal.isGameWon());
    //     testChar.addEXP(200);
    //     assertEquals(true, goal.isGameWon());
    // }

    @Test
    public void testAndComplexGoal() {
        // creates the world with the AND complex goal and test each of the
        // goals before the completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // create (200 experience AND 200 gold)
        ComplexGoal andComplexGoal = new AndComplex();
        // add 2 simple goal into the AND composite
        andComplexGoal.add(new ExperienceGoal(200));
        andComplexGoal.add(new GoldGoal(200));
        // add the complex goal into the world goal
        goal.addComplexGoal(andComplexGoal);
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks that the game is not won until ALL goals are completed
        assertEquals(false, goal.isGameWon());
        // the character has met the requirement for gold 
        // but still hasnt completed the game
        testChar.addGold(200);
        assertEquals(false, goal.isGameWon());
        // the character has met the requirement for exp
        // with both goals completed, the game should win
        testChar.addEXP(200);
        assertEquals(true, goal.isGameWon());
    }

    @Test
    public void testOrComplexGoal() {
        // creates the world with the OR complex goal and test each of the
        // goals before the completion
        TestSetupNoGoals setup = new TestSetupNoGoals();
        Goal goal = new Goal();
        // create (5 cycle OR 200 gold)
        ComplexGoal orComplexGoal = new OrComplex();
        // add 2 simple goal into the OR composite
        orComplexGoal.add(new CycleGoal(5));
        orComplexGoal.add(new GoldGoal(200));
        // add the complex goal into the world goal
        goal.addComplexGoal(orComplexGoal);
        LoopManiaWorld world = setup.makeTestWorld(goal);
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // checks that the game is not won until ALL goals are completed
        assertEquals(false, goal.isGameWon());
        // by completing one of the goal, the game should win
        while (testChar.getCycleCount() < 5) {
            testChar.incrementCycleCount();
        }
        assertEquals(true, goal.isGameWon());
    }
}
