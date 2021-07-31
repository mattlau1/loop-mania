package unsw.loopmania.Items.Decorator;

import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Soldier;

public class Stake extends ItemDecorator {
  private final double atkMultiplier = 0.5;
  private final double vampireAtkMultiplier = 3;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;
  private final int range = 0;
  private final int price = 100;

  public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y, Item item) {
    super(x, y, item);
  }

  /**
   * Stake item lowers danage dealt by 50% unless the Character is attacking a
   * vampire, in which case deal three times as much damage
   *
   * @param enemy The enemy that the Character is in combat with
   * @return The attack multiplier against the enemy, may vary depending on the
   *         enemy type
   */
  @Override
  public double getAtkMultiplier(Enemy enemy) {
    if (enemy instanceof VampireEnemy)
      return vampireAtkMultiplier;
    return atkMultiplier;
  }

  @Override
  public int getRange() {
    return range;
  }

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

  @Override
  public int getPrice() {
    return price;
  }

}