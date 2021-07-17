package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;

public class TowerStrategy implements BuildingStrategy {
  private final int range = 2;
  private final int towerDamage = 10;

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    return;
  }

  @Override
  public void useBuilding(Enemy enemy) {
    enemy.reduceHealth(towerDamage);
    System.out.printf("OUCH, %s JUST TOOK %d DAMAGE!\n", enemy.getClass(), towerDamage);
  }

  @Override
  public boolean usableOutsideCombat() {
    return false;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/tower.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
