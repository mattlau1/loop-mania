package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Enemies.ZombieEnemy;

public class CriticalHitTest {
  @Test
  public void testVampireCriticalHit() {
    // tests that vampire deals additional damage after it crits
    TestSetup s = new TestSetup();
    LoopManiaWorld d = s.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
    d.setCharacter(testChar);
    VampireEnemy vampire = new VampireEnemy(new PathPosition(1, d.getOrderedPath()));
    vampire.setCritRate(100);
    d.addEnemy(vampire);
    d.runBattles();
    System.out.println(testChar.getHealth());
    assertTrue(testChar.getHealth() < 40);
  }

  @Test
  public void testZombieCriticalHit() {
    // tests that zombies turn allies into zombie after it crits
    TestSetup s = new TestSetup();
    LoopManiaWorld d = s.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
    d.setCharacter(testChar);
    ZombieEnemy zombie = new ZombieEnemy(new PathPosition(1, d.getOrderedPath()));
    zombie.setCritRate(100);
    d.addEnemy(zombie);
    testChar.addSoldier();
    d.runBattles();
    System.out.println(testChar.getSoldiers().size());
    assertTrue(testChar.getSoldiers().size() == 0);
  }

}
