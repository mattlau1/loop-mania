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
    TestSetup s = new TestSetup();
    LoopManiaWorld d = s.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
    d.setCharacter(testChar);
    testChar.addGold(1000);
    d.buyItem(new SwordStrategy());
    List<Item> unequipedItems = d.getUnequip();
    assertTrue(unequipedItems.get(0).getStrategy() instanceof SwordStrategy);
    assertEquals(900, testChar.getGold());
  }

  @Test
  public void testBuyItemNoGold() {
    // tests if buying an item deducts money and adds it to inventory
    TestSetup s = new TestSetup();
    LoopManiaWorld d = s.makeTestWorld();
    Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
    d.setCharacter(testChar);
    testChar.addGold(50);
    d.buyItem(new SwordStrategy());
    List<Item> unequipedItems = d.getUnequip();
    assertTrue(unequipedItems.size() == 0);
    assertEquals(50, testChar.getGold());
  }

}
