package unsw.loopmania.Buildings;

import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;

public interface BuildingStrategy {
  /**
   * Activates the building for a character. Does nothing if character cannot use
   * it.
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
   * Checks if building can spawn enemies, given the current cycle number
   *
   * @param currentCycle current cycle count of character
   * @return true if building can spawn enemies else false
   */
  public boolean canSpawnEnemy(int currentCycle);

  /**
   * Checks if building is at the character's spawn location (is the hero's
   * castle)
   *
   * @return true if building is spawn location/hero's castle else false
   */
  public boolean isSpawnLocation();

  /**
   * Spawns a new enemy at given position and returns it
   *
   * @param position position to spawn enemy at
   * @return the new enemy if building can spawn enemies, else null
   */
  public Enemy spawnEnemy(PathPosition position);

  /**
   * Checks if the building can only spawn next to a path tile
   *
   * @return true if the building can only spawn next to a path tile
   */
  public boolean canOnlySpawnNextToPath();

  /**
   * Checks if the building can only spawn on path tile
   *
   * @return true if the building can only spawn on a path tile
   */
  public boolean canOnlySpawnOnPath();

}
