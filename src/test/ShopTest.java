package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.ShieldStrategy;
import unsw.loopmania.Items.SwordStrategy;

public class ShopTest {
  @Test
  public void testBuyItem() {
    // tests if buying an item deducts money and adds it to inventory
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    testChar.addGold(1000);
    world.buyItem(new SwordStrategy());
    List<Item> unequipedItems = world.getUnequip();
    // checks to see if the item bought is a sword
    assertTrue(unequipedItems.get(0).getStrategy() instanceof SwordStrategy);
    // checks to see if gold has been deducted from character
    assertEquals(900, testChar.getGold());
  }

  @Test
  public void testBuyItemNoGold() {
    // Should not be able to buy items with insufficient gold
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    testChar.addGold(50);
    world.buyItem(new SwordStrategy());
    List<Item> unequipedItems = world.getUnequip();
    // checks that no item has been bought
    assertTrue(unequipedItems.size() == 0);
    // checks that gold was not deducted
    assertEquals(50, testChar.getGold());
  }

  @Test
  public void testSellItem() {
    // Tests that selling items removes then from inventory and gives player gold
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    testChar.addGold(1000);
    world.buyItem(new SwordStrategy());
    List<Item> unequipedItems = world.getUnequip();
    // checks to see if the item bought is a sword
    assertTrue(unequipedItems.get(0).getStrategy() instanceof SwordStrategy);
    // checks to see if gold has been deducted from character
    assertEquals(900, testChar.getGold());
    // sell item and test againn
    world.sellItem(SwordStrategy.class);
    // check that there are no items in inventory
    assertTrue(unequipedItems.size() == 0);
    // check that player has recieved gold equal to half the items price
    assertEquals(950, testChar.getGold());
  }

  @Test
  public void testSellItemNoItem() {
    // cannot sell items player does not have
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    testChar.addGold(1000);
    world.buyItem(new SwordStrategy());
    List<Item> unequipedItems = world.getUnequip();
    // checks to see if the item bought is a sword
    assertTrue(unequipedItems.get(0).getStrategy() instanceof SwordStrategy);
    // checks to see if gold has been deducted from character
    assertEquals(900, testChar.getGold());
    // sell item and test againn
    world.sellItem(ShieldStrategy.class);
    // check that there are no changes to inventory size
    assertTrue(unequipedItems.size() == 1);
    // check that player has not recieved gold
    assertEquals(900, testChar.getGold());
  }

  @Test
  public void testSellDoggieCoin() {
    // tests selling Doggie coin before elan is spawned
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    testChar.addDoggieCoins(1);
    world.sellDoggieCoin();
    // checks that the players gold has been increased by a value between 200 and
    // 250
    assertTrue(testChar.getGold() >= 200 && testChar.getGold() <= 250);
  }

  @Test
  public void testSellDoggieCoinElanAlive() {
    // tests selling Doggie coin when elan is alive
    TestSetup setup = new TestSetup();
    LoopManiaWorld world = setup.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
    world.setCharacter(testChar);
    testChar.addDoggieCoins(1);
    world.sellDoggieCoin();
    // checks that the players gold has been increased by a value between 1000 and
    // 1250
    assertTrue(testChar.getGold() >= 1000 && testChar.getGold() <= 1250);
  }
}
