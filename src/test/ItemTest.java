package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Enemies.ZombieEnemy;
import unsw.loopmania.Items.AndurilStrategy;
import unsw.loopmania.Items.ArmourStrategy;
import unsw.loopmania.Items.HealthPotionStrategy;
import unsw.loopmania.Items.HelmetStrategy;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.ItemStrategy;
import unsw.loopmania.Items.ShieldStrategy;
import unsw.loopmania.Items.StaffStrategy;
import unsw.loopmania.Items.StakeStrategy;
import unsw.loopmania.Items.SwordStrategy;
import unsw.loopmania.Items.TheOneRingStrategy;

public class ItemTest {
  @Test
  public void testAtkMultiplier() throws FileNotFoundException {
    // test stake against vampire and non vampire
    SimpleIntegerProperty x = new SimpleIntegerProperty(1);
    SimpleIntegerProperty y = new SimpleIntegerProperty(2);
    StakeStrategy strat = new StakeStrategy();
    Item testStake = new Item(x, y, strat);

    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();

    SlugEnemy slug = new SlugEnemy(new PathPosition(1, world.getOrderedPath()));
    VampireEnemy vamp = new VampireEnemy(new PathPosition(1, world.getOrderedPath()));
    // stake should have a low multiplier against non-vampire
    assertEquals(0.5, testStake.getAtkMultiplier(slug));
    // stake should have a high multiplier against vampire
    assertEquals(3, testStake.getAtkMultiplier(vamp));
  }

  @Test
  public void testStaffBuff() {
    // test staff's effect to convert zombies to allied soldiers
    SimpleIntegerProperty x = new SimpleIntegerProperty(1);
    SimpleIntegerProperty y = new SimpleIntegerProperty(2);
    StaffStrategy strat = new StaffStrategy();
    Item testStaff = new Item(x, y, strat);
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    ZombieEnemy zombie = new ZombieEnemy(new PathPosition(1, world.getOrderedPath()));
    // checks that there are initially no tranced soldiers
    assertEquals(0, world.trancedSoldiersSize());
    testStaff.onHitEffects(zombie, world.getTrancedSoldiers());
    // checks that the soldier was turned into a tranced solider
    assertEquals(1, world.trancedSoldiersSize());
    // checks that the former zombie no longer exists
    assertEquals(false, zombie.isAlive());

  }

  @Test
  public void testHealthPotion() {
    // tests that the health potions heal the player
    HealthPotionStrategy strat = new HealthPotionStrategy();
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
    Character testChar = new Character(pathPos);
    world.setCharacter(testChar);
    testChar.reduceHealth(50);
    // checks that the character initially has half health (50)
    assertEquals(50, testChar.getHealth());
    Item testPot = new Item(pathPos.getX(), pathPos.getY(), strat);
    world.addPathItems(testPot);
    world.runBattles();
    // checks that after consuming the potion the character is at maxed health
    assertEquals(100, testChar.getHealth());

  }

  @Test
  public void testOneRing() {
    // if character has one ring equiped should prevent death once
    TheOneRingStrategy strat = new TheOneRingStrategy();
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // gives the character the one ring
    world.addSpecificUnequippedItem(strat);
    assertEquals(false, world.isGameLost());
    // kills the character
    testChar.reduceHealth(100);
    // checks that the character is revived
    assertEquals(true, testChar.isDead());
    assertEquals(false, world.isGameLost());
    assertEquals(100, testChar.getHealth());
    assertEquals(true, testChar.isAlive());
    // kills the character
    testChar.reduceHealth(100);
    // checks that the character does not revive and the game is lost
    assertEquals(false, testChar.isAlive());
    assertEquals(true, world.isGameLost());

  }

  @Test
  public void testNoItemSpawned() {
    // test that no items are spawned in the world with the specific seed
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(5);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // spawning item is impossible
    world.generateItemDrops();
    // no items were spawned
    List<Item> newItems = world.possiblySpawnItems();
    assertEquals(0, newItems.size());


    Random rand = new Random(-1);
    System.out.println(rand.nextInt(2));
    
  }

  @Test
  public void testItemSpawned() {
    // test that items are spawned in the world with the specific seed
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(-1);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // spawning item is possible
    world.generateItemDrops();
    // items are spawned on the map
    List<Item> newItems = world.possiblySpawnItems();
    assertEquals(1, newItems.size());
  }

  @Test
  public void testSuperRaritySpawn() {
    // test that specific item will spawn from certain rarity categories.
    
    // set up that will spawn super rarity item
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(76);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // spawn super rare item
    ItemStrategy superRareItem = world.randomItemStrategy();
    assertEquals(true, superRareItem instanceof AndurilStrategy);
  }

  @Test
  public void testHighRaritySpawn() {
    // test that specific item will spawn from certain rarity categories.
    
    // set up that will spawn super rarity item
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(37);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // spawn high rare item
    ItemStrategy highRareItem = world.randomItemStrategy();
    assertEquals(true, highRareItem instanceof HealthPotionStrategy);
  }

  @Test
  public void testMediumRaritySpawn() {
    // test that specific item will spawn from certain rarity categories.
    
    // set up that will spawn medium rarity item
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(19);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // spawn medium rare item
    ItemStrategy mediumRareItem = world.randomItemStrategy();
    assertEquals(true, mediumRareItem instanceof HelmetStrategy);
  }

  @Test
  public void testLowRaritySpawn() {
    // test that specific item will spawn from certain rarity categories.
    
    // set up that will spawn low rarity item
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(78);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // spawn low rare item
    ItemStrategy lowRareItem = world.randomItemStrategy();
    assertEquals(true, lowRareItem instanceof StakeStrategy);
  }

  @Test
  public void testAddingUnequippedItem() {
    // testing by adding another unequip item after it is full
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(78);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // no items has been added into unequip yet
    assertEquals(0, world.getUnequip().size());
    // populate the inventory 
    for (int i = 0; i < 16; i++) {
      world.addUnequippedItem();
    }
    // check the unequip is filled
    assertEquals(16, world.getUnequip().size());
    // adding equip item even though its already full
    world.addUnequippedItem();
    // no overflow after adding item to full inventory
    assertEquals(16, world.getUnequip().size());
  }

  @Test
  public void testRemovingUnequippedItem() {
    // testing by removing unequipped item by coordinates
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(78);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // no items has been added into unequip yet
    assertEquals(0, world.getUnequip().size());
    // add few items into the unequip inventory
    for (int i = 0; i < 5; i++) {
      world.addUnequippedItem();
    }
    // checks that 5 items are added
    assertEquals(5, world.getUnequip().size());
    // remove the item at 0, 0
    world.removeUnequippedInventoryItemByCoordinates(0, 0);
    // check that 4 items remain
    assertEquals(4, world.getUnequip().size());
  }

  @Test
  public void testEquippedItem() {
    // testing by adding equip item and then removing it
    TestSetupWithSeed setup = new TestSetupWithSeed();
    LoopManiaWorld world = setup.makeTestWorld(78);
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    // generate item
    world.generateItemDrops();
    // no item in equip
    assertEquals(0, world.getEquip().size());
    // add stake into unequipped
    world.addUnequippedItem();
    // the stake is situated in the inventory grid at 0, 0
    world.addEquippedInventoryItemByCoordinates(0, 0);
    // there should be 1 item in equipped inventory
    assertEquals(1, world.getEquip().size());


    // remove the item from the equip inventory
    // world.removeEquippedInventoryItemByCoordinates(-1, 0);
    // assertEquals(0, world.getEquip().size());


  }



}
