package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class StaffStrategy implements ItemStrategy {
  private final double atkMultiplier = 2;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;

  /**
   * Staff item reduces damange dealt by the Character by 65% so returns 0.35
   *
   * @param enemy The enemy that the Character is in combat with
   * @return The attack multiplier against the enemy, may vary depending on the
   *         enemy type
   */
  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return atkMultiplier;
  }

  @Override
  public double getDefMultiplier(Enemy enemy) {
    return defMultiplier;
  }

  @Override
  public double getCritMultiplier(Enemy enemy) {
    return critMultiplier;
  }

  /**
   * "Random chance of inflicting a trance, which transforms the attacked enemy
   * into an allied soldier temporarily"
   *
   * @param enemy The enemy that the Character is in combat with
   */
  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    enemy.reduceHealth(100);
    allyList.add(new Soldier());
  };

  @Override
  public boolean isDestroyedOnUse() {
    return false;
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
    Image image = new Image((new File("src/images/staff.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
