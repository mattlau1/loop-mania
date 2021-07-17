package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Soldier;

public class StakeStrategy implements ItemStrategy {
  /**
   * Stake item lowers danage dealt by 50% unless the Character is attacking a
   * vampire, in which case deal three times as much damage
   * 
   * @param enemy The monster/entity that the Character is in combat with
   * @return The attack multiplier against the monster, may vary depending on the
   *         monster type
   */
  @Override
  public double atkMultiplier(Enemy enemy) {
    if (enemy instanceof VampireEnemy)
      return 3;
    return 0.5;
  }

  /**
   * Stake item does not have any defence mechanics, so returns 1
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
   * Stake item does not have any defence mechanics, so returns 1
   * 
   * @param enemy The monster/entity that the Character is in combat with
   * @return The defence multiplier against the monster, can vary depending on the
   *         monster type
   */
  @Override
  public double critMultiplier(Enemy enemy) {
    return 1;
  }

  /**
   * Stake item does not have any on hit effects so does nothing
   * 
   * @param enemy The monster/entity that the Character is in combat with
   */
  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    return;
  }

  /**
   * returns the image of the item to be displayed
   * 
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/stake.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
