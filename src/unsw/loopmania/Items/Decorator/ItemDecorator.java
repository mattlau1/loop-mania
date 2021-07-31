package unsw.loopmania.Items.Decorator;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Enemies.Enemy;

public abstract class ItemDecorator extends StaticEntity implements Item {
  protected Item decoratedItem;

  public ItemDecorator(SimpleIntegerProperty x, SimpleIntegerProperty y, Item item) {
    super(x, y);
    this.decoratedItem = item;
  }

  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return this.decoratedItem.getAtkMultiplier(enemy);
  }

  @Override
  public double getCritMultiplier(Enemy enemy) {
    return this.decoratedItem.getCritMultiplier(enemy);
  }

  @Override
  public double getDefMultiplier(Enemy enemy) {
    return this.decoratedItem.getDefMultiplier(enemy);
  }

  @Override
  public ImageView getImage() {
    return this.decoratedItem.getImage();
  }

  @Override
  public int getPrice() {
    return this.decoratedItem.getPrice();
  }

  @Override
  public int getRange() {
    return this.decoratedItem.getRange();
  }

  @Override
  public boolean isDestroyedOnUse() {
    return this.decoratedItem.isDestroyedOnUse();
  }

  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    this.decoratedItem.onHitEffects(enemy, allyList);
  }

  @Override
  public void useItem(Character character) {
    this.decoratedItem.useItem(character);
  }

}