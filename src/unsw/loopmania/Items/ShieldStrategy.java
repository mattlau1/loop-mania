package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class ShieldStrategy implements ItemStrategy {
  /**
   * Shield item does not have any attack mechanics, so returns 1
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
   * Shield items does not have ay defence mechanics
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
   * Shield item causes critical vampire attacks to have a 60% lower chance of
   * occurring so returns 0.4
   * 
   * @param enemy The monster/entity that the Character is in combat with
   * @return The crit multiplier against the monster, can vary depending on the
   *         monster type
   */
  @Override
  public double critMultiplier(Enemy enemy) {
    return 0.4;
  }

  /**
   * Shield item does not have any on hit effects so does nothing
   * 
   * @param enemy The monster/entity that the Character is in combat with
   */
  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
  }

  /**
   * returns the image of the item to be displayed
   * 
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/shield.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
