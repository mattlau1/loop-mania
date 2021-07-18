package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;
import unsw.loopmania.Character;

public class ArmourStrategy implements ItemStrategy {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 0.5;
  private final double critMultiplier = 1;

  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return atkMultiplier;
  }

  /**
   * Armour halves the damage recieved, so multiplier will be 0.5
   *
   * @param enemy The enemy that the Character is in combat with
   * @return defense multiplier against enemy
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
  public void useItem(Character character) {
    return;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/armour.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }
}
