package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class TheOneRingStrategy implements ItemStrategy {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;
  private final int range = 0;
  private final int price = 100;

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

  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    return;
  }

  @Override
  public boolean isDestroyedOnUse() {
    return true;
  }

  @Override
  public void useItem(Character character) {
    character.setHealth(character.getInitialHealth());
  }

  @Override
  public int getRange() {
    return range;
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

  @Override
  public int getPrice() {
    return price;
  }

}
