package unsw.loopmania.Buildings;

import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;

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
  public void useBuilding(BasicEnemy enemy);

  /**
   * Gets the range of the building
   *
   * @return building range
   */
  public int getBuildingRange();

  /**
   * Checks is building is usable outside of combat
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
}
