package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import unsw.loopmania.Character;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class HelmetStrategy implements ItemStrategy {
  private final double atkMultiplier = 0.75;
  private final double defMultiplier = 0.75;
  private final double critMultiplier = 1;
  private final int range = 0;

  /**
   * Helmet item lowers damage dealt by the Character by 25% so returns 0.75
   *
   * @param enemy The enemy that the Character is in combat with
   * @return The attack multiplier against the enemy, may vary depending on the
   *         enemy type
   */
  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return atkMultiplier;
  }

  /**
   * Helmet item lowers damage recieved by the Character by 25% so returns 0.75
   *
   * @param enemy The enemy that the Character is in combat with
   * @return The defence multiplier against the enemy, can vary depending on the
   *         enemy type
   */
  @Override
  public double getDefMultiplier(Enemy enemy) {
    return defMultiplier;
  }

  @Override
  public double getCritMultiplier(Enemy enemy) {
    return critMultiplier;
  }

  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    return;
  }

  @Override
  public boolean isDestroyedOnUse() {
    return false;
  }

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useItem(Character character) {
    return;
  }

  /**
   * returns the image of the item to be displayed
   *
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/helmet.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
