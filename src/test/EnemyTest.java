package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Buildings.HerosCastleStrategy;
import unsw.loopmania.Buildings.VillageStrategy;
import unsw.loopmania.Enemies.DoggieEnemy;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.ThiefEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Enemies.ZombieEnemy;
import unsw.loopmania.Goals.CycleGoal;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.StaffStrategy;
import unsw.loopmania.Items.TheOneRingStrategy;

public class EnemyTest {

  @Test
  public void testReduceHealth() {
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();

    SlugEnemy slug = new SlugEnemy(new PathPosition(1, world.getOrderedPath()));
    // slug spawns with 20 hp reduceHealth called with value 10 should leave slug
    // with 10hp
    assertEquals(20, slug.getHealth());
    slug.reduceHealth(10);
    assertEquals(10, slug.getHealth());
  }

  @Test
  public void testIsAlive() {
    // test if returns false when enemy recieves dmg greater than their hp
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();

    SlugEnemy slug = new SlugEnemy(new PathPosition(1, world.getOrderedPath()));
    assertEquals(true, slug.isAlive());
    // slug spawns with 20 hp reduceHealth called with value 10 should leave slug
    // with 10hp
    slug.reduceHealth(20);
    assertEquals(false, slug.isAlive());
  }

  @Test
  public void testEnemyDrops() {
    // tests that enemies drop gold and exp
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    SlugEnemy slug = new SlugEnemy(new PathPosition(1, world.getOrderedPath()));
    world.addEnemy(slug);
    // check that character initially has 0 gold and exp
    assertEquals(0, testChar.getGold());
    assertEquals(0, testChar.getExp());
    world.runBattles();
    // check that after killing the slug, the character gained 10 gold and exp
    assertEquals(10, testChar.getGold());
    assertEquals(10, testChar.getExp());
  }

  @Test
  public void testCharacterStun() {
    // test if the doggie is able to stun the character
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(27);
    // place character and doggie on the same tile so the battle can start
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // add doggie in the world
    DoggieEnemy doggie = new DoggieEnemy(new PathPosition(1, world.getOrderedPath()));
    world.addEnemy(doggie);
    world.possiblyStunCharacter();
    assertEquals(true, testChar.isStunned());
  }

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
    world.runBattles();
    assertEquals(1, vampire.getDirection());
    world.runBattles();
  }

  @Test
  public void testSoldierTakingDamage() {
    // test that soldiers and tranced soldiers take damage in combat
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(1);
    SimpleIntegerProperty x = new SimpleIntegerProperty(1);
    SimpleIntegerProperty y = new SimpleIntegerProperty(2);
    StaffStrategy strat = new StaffStrategy();
    Item testStaff = new Item(x, y, strat);
    // add the character and zombies on the same tile so they can interact
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    ZombieEnemy zombie1 = new ZombieEnemy(new PathPosition(1, world.getOrderedPath()));
    ZombieEnemy zombie2 = new ZombieEnemy(new PathPosition(1, world.getOrderedPath()));
    ZombieEnemy zombie3 = new ZombieEnemy(new PathPosition(1, world.getOrderedPath()));
    world.addEnemy(zombie1);
    world.addEnemy(zombie2);
    world.addEnemy(zombie3);
    // add a soldier to the player
    testChar.addSoldier();
    // turn the zombie into a tranced soldier
    testStaff.onHitEffects(zombie1, world.getTrancedSoldiers());
    // run battle so soldiers die
    world.runBattles();
    // assert that there is no longer any soldiers or tranced soldiers
    assertEquals(0, world.getTrancedSoldiers().size());
    assertEquals(0, testChar.getSoldiers().size());
  }

  @Test
  public void testThiefSpawn() {
    // test that thief spawns at village
    // world setup
    VillageStrategy villageStrategy = new VillageStrategy();
    HerosCastleStrategy herosCastleStrategy = new HerosCastleStrategy();
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
    Character testChar = new Character(pathPos);
    world.setCharacter(testChar);
    Building castle = new Building(pathPos.getX(), pathPos.getY(), herosCastleStrategy);
    world.addBuildingToWorld(castle);
    // create village and add to world
    Building village = new Building(pathPos.getX(), pathPos.getY(), villageStrategy);
    world.addBuildingToWorld(village);
    testChar.incrementCycleCount();

    // spawn enemies and count thieves
    List<Enemy> enemies = world.possiblySpawnEnemies();
    int thiefCount = 0;
    for (Enemy enemy : enemies) {
      if (enemy instanceof ThiefEnemy) {
        thiefCount++;
      }
    }
    // assert thieves spawned
    assertNotEquals(0, thiefCount);
  }

  @Test
  public void testThiefSteal() {
    // test that the thief deducts 10 gold and 1 inventory item on hit
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
    Character testChar = new Character(pathPos);
    world.setCharacter(testChar);
    ThiefEnemy thief = new ThiefEnemy(pathPos);
    world.addEnemy(thief);
    // add 4 items to players inventory
    TheOneRingStrategy oneRingStrategy = new TheOneRingStrategy();
    world.addSpecificUnequippedItem(oneRingStrategy);
    world.addSpecificUnequippedItem(oneRingStrategy);
    world.addSpecificUnequippedItem(oneRingStrategy);
    world.addSpecificUnequippedItem(oneRingStrategy);

    // add gold to player
    testChar.addGold(50);
    // run battles so the player will fight thief
    world.runBattles();
    // assert that player has 40 gold as they would have been hit by the thief 3
    // times and then recieved 20 gold after killing it
    assertEquals(40, testChar.getGold());
    // assert player has less than 4 items after losing 3
    assertNotEquals(4, world.getUnequip().size());
    //
  }
}
