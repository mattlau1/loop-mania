package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;
import unsw.loopmania.Character;

public class GoldStrategy implements ItemStrategy {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;
  private final int range = 1;
  private final int goldAmt = 10;
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
    Image image = new Image((new File("src/images/gold_pile.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getPrice() {
    return price;
  }

}
