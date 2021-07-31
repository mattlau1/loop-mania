package unsw.loopmania.Items.Decorator;

import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.Enemies.Enemy;

public class Anduril extends ItemDecorator {
  private final double atkMultiplier = 5;
  private final double bossAtkMultiplier = 3;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;
  private final int range = 0;
  private final int price = 100;

  public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y, Item item) {
    super(x, y, item);
  }

  /**
   * Anduril item incresaes damage dealth by Character by a factor of five,
   * against bosses it does 3x damage
   *
   * @param enemy The enemy that the Character is in combat with
   * @return The attack multiplier against the enemy, may vary depending on the
   *         enemy type
   */
  @Override
  public double getAtkMultiplier(Enemy enemy) {
    if (enemy.isBoss())
      return bossAtkMultiplier;
    return atkMultiplier;
  }

  @Override
  public double getDefMultiplier(Enemy enemy) {
    return decoratedItem.getDefMultiplier(enemy);
  }

  @Override
  public void useItem(Character character) {
    decoratedItem.useItem(character);
  }

  @Override
  public int getRange() {
    return range;
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
  public ImageView getImage() {
    Image image = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getPrice() {
    return price;
  }
}
