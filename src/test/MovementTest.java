package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Goals.CycleGoal;
import unsw.loopmania.Goals.Goal;

public class MovementTest {

  @Test
  public void testVampireMovement() {
    // test the vampire direction when near the campfire
    CampfireStrategy strat = new CampfireStrategy();
    TestSetup setup = new TestSetup();
    Goal goal = new Goal();
    goal.addSimpleGoal(new CycleGoal(20));
    LoopManiaWorld world = setup.makeTestWorld();
    // vampire and campfire gets the same position
    PathPosition vampirePos = new PathPosition(6, world.getOrderedPath());
    PathPosition charPos = new PathPosition(1, world.getOrderedPath());
    PathPosition campPos = new PathPosition(6, world.getOrderedPath());
    // add character far away from the vampire to make sure it doesnt kill
    // the vampire
    Character testChar = new Character(charPos);
    world.setCharacter(testChar);
    Building campfire = new Building(campPos.getX(), campPos.getY(), strat);
    VampireEnemy vampire = new VampireEnemy(vampirePos);
    world.addEnemy(vampire);
    // test that the vampire initially moves in anti clockwise
    assertEquals(0, vampire.getDirection());
    // the direction changes when theres a campfire nearby
    world.addBuildingToWorld(campfire);
  }
}
