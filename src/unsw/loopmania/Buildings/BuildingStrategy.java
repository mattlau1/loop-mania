package unsw.loopmania.Buildings;

import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;

public interface BuildingStrategy {
  /**
   * Activates the building for a character. Does nothing if character cannot use it.
   *
   * @param character character to activate building on
   */
  public void useBuilding(Character character);

  /**
   * Activates the building for an enemy. Does nothing if enemy cannot use it.
   *
   * @param enemy enemy to activate building on
   */
  public void useBuilding(Enemy enemy);

  /**
   * Gets the range of the building
   *
   * @return building range
   */
  public int getRange();

  /**
   * Checks if building is usable outside of combat
   *
   * @return true if usable outside combat else false
   */
  public boolean usableOutsideCombat();

  /**
   * Gets the image for the building for javafx UI
   *
   * @return building image
   */
  public ImageView getImage();

  /**
   * Checks if building can spawn enemies
   *
   * @return true if building can spawn enemies else false
   */
  public boolean canSpawnEnemy();

}
