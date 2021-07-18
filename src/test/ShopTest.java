package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Items.Item;
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

}
