package unsw.loopmania.Items;

import java.util.List;

import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;

public interface ItemStrategy {
  /**
   *
   * @param enemy The enemy that the Character is in combat with
   * @return attack multiplier against the enemy. returns 1 if item does not have
   *         any attack mechanics
   */
  public double getAtkMultiplier(Enemy enemy);

  /**
   *
   * @param enemy The enemy that the Character is in combat with
   * @return critical multiplier against the enemy. returns 1 if item does not
   *         have any critical attack mechanics
   */
  public double getCritMultiplier(Enemy enemy);

  /**
   *
   * @param enemy The enemy that the Character is in combat with
   * @return defence multiplier against the enemy. returns 1 if item does not have
   *         any defence mechanics
   */
  public double getDefMultiplier(Enemy enemy);

  /**
   * Deals with any on hit effects as a result of the weapon. Does nothing if item
   * does not have an on hit effect
   *
   * @param enemy    The enemy that the Character is in combat with
   * @param allyList list of soldier allies
   */
  public void onHitEffects(Enemy enemy, List<Soldier> allyList);

  /**
   * Checks if item should be destroyed on use
   *
   * @return true if item should be destroyed on use else false
   */
  public boolean isDestroyedOnUse();

  /**
   * Uses item on character if item has an effect else do nothing
   *
   * @param character
   */
  public void useItem(Character character);

  /**
   * Gets the image of the item to be shown
   *
   * @return The image of the item to be shown
   */
  public ImageView getImage();

  /**
   * Gets the range of an item
   *
   * @return the range of an item
   */
  public int getRange();

  /**
   * Gets the price of an item
   *
   * @return the price of an item
   */
  public int getPrice();

}