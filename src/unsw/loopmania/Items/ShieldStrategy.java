package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import unsw.loopmania.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class ShieldStrategy implements ItemStrategy {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 1;
  private final double critMultiplier = 0.4;
  private final int range = 0;
  private final int price = 80;

  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return atkMultiplier;
  }

  @Override
  public double getDefMultiplier(Enemy enemy) {
    return defMultiplier;
  }

  /**
   * Shield item causes critical vampire attacks to have a 60% lower chance of
   * occurring so returns 0.4
   *
   * @param enemy The enemy that the Character is in combat with
   * @return The crit multiplier against the enemy, can vary depending on the
   *         enemy type
   */
  @Override
  public double getCritMultiplier(Enemy enemy) {
    return critMultiplier;
  }

  /**
   * Shield item does not have any on hit effects so does nothing
   *
   * @param enemy The enemy that the Character is in combat with
   */
  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    return;
  }

  @Override
  public boolean isDestroyedOnUse() {
    return false;
  }

  @Override
  public void useItem(Character character) {
    return;
  }

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/shield.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getPrice() {
    return price;
  }

}
