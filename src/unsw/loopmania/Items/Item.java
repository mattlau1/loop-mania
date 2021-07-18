package unsw.loopmania.Items;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Enemies.Enemy;

public class Item extends StaticEntity implements ItemStrategy {
  private ItemStrategy strategy;

  public Item(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemStrategy strategy) {
    super(x, y);
    this.strategy = strategy;
  }

  public double getAtkMultiplier(Enemy enemy) {
    return strategy.getAtkMultiplier(enemy);
  }

  public double getDefMultiplier(Enemy enemy) {
    return strategy.getDefMultiplier(enemy);
  }

  public double getCritMultiplier(Enemy enemy) {
    return strategy.getCritMultiplier(enemy);
  }

  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    strategy.onHitEffects(enemy, allyList);
  }

  public ImageView getImage() {
    return strategy.getImage();
  }

  public ItemStrategy getStrategy() {
    return strategy;
  }

  public void setStrategy(ItemStrategy strategy) {
    this.strategy = strategy;
  }

  public boolean isDestroyedOnUse() {
    return strategy.isDestroyedOnUse();
  }

  public void useItem(Character character) {
    strategy.useItem(character);
  }

  public int getRange() {
    return strategy.getRange();
  }

  public int getPrice() {
    return strategy.getPrice();
  }

}
