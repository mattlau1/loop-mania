package unsw.loopmania.Items.Decorator;

import java.util.List;

import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.Enemies.Enemy;

public class BaseItem implements Item {

  @Override
  public double getAtkMultiplier(Enemy enemy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getCritMultiplier(Enemy enemy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getDefMultiplier(Enemy enemy) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ImageView getImage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getPrice() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getRange() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isDestroyedOnUse() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    // TODO Auto-generated method stub

  }

  @Override
  public void useItem(Character character) {
    // TODO Auto-generated method stub

  }

}
