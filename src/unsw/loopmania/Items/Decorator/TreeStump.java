package unsw.loopmania.Items.Decorator;

import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;
import unsw.loopmania.Character;

public class TreeStump extends ItemDecorator {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 0.5;
  private final double bossDefMultiplier = 0.33;
  private final double critMultiplier = 1;
  private final int range = 0;
  private final int price = 100;

  public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, Item item) {
    super(x, y, item);
  }

  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return decoratedItem.getAtkMultiplier(enemy);
  }

  /**
   * Tree stump halves the damage recieved, so multiplier will be 0.5, against
   * bosses damage is reduced by 66%
   *
   *
   * @param enemy The enemy that the Character is in combat with
   * @return defense multiplier against enemy
   */
  @Override
  public double getDefMultiplier(Enemy enemy) {
    if (enemy.isBoss())
      return bossDefMultiplier;
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
    decoratedItem.useItem(character);
  }

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/tree_stump.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getPrice() {
    return price;
  }

}
