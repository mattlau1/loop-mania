package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class TheOneRingStrategy implements ItemStrategy {
  /**
   * TheOneRing item does not have any attack mechanics, so returns 1
   *
   * @param enemy The monster/entity that the Character is in combat with
   * @return The attack multiplier against the monster, may vary depending on the
   *         monster type
   */
  @Override
  public double atkMultiplier(Enemy enemy) {
    return 1;
  }

  /**
   * TheOneRing item does not have any defence mechanics, so returns 1
   *
   * @param enemy The monster/entity that the Character is in combat with
   * @return The defence multiplier against the monster, can vary depending on the
   *         monster type
   */
  @Override
  public double defMultiplier(Enemy enemy) {
    return 1;
  }

  /**
   * TheOneRing item does not have any critical hit mechanics, so returns 1
   *
   * @param enemy The monster/entity that the Character is in combat with
   * @return The crit multiplier against the monster, can vary depending on the
   *         monster type
   */
  @Override
  public double critMultiplier(Enemy enemy) {
    return 1;
  }

  /**
   * TheOneRing item does not have any on hit effects so does nothing
   *
   * @param enemy The monster/entity that the Character is in combat with
   */
  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
  }

  /**
   * Deals with any effects that may occur on character death
   * @return a boolean for if the item should be destroyed
   */
  public boolean onDeath(Character character) {
    character.setHealth(character.getInitialHealth());
    return true;
  }

  /**
   * returns the image of the item to be displayed
   *
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/the_one_ring.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }
}
