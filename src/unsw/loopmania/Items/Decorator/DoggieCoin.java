package unsw.loopmania.Items.Decorator;

import java.io.File;
import java.util.List;
import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;
import unsw.loopmania.Character;

public class DoggieCoin extends ItemDecorator {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;
  private final int range = 1;
  private final int goldAmt = 10;
  private final int price = 200;
  private final int fluctuationRange = 50;

  public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y, Item item) {
    super(x, y, item);
  }

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
    return false;
  }

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useItem(Character character) {
    character.addGold(goldAmt);
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/doggie_coin.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getPrice() {
    Random random = new Random();
    int randInt = random.nextInt(fluctuationRange) + 1;
    return price + randInt;
  }

}