package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;

public class ZombiePitStrategy implements BuildingStrategy, EnemySpawning {
  private final int range = 2;

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    // TODO Auto-generated method stub

  }

  @Override
  public void useBuilding(Enemy enemy) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean usableOutsideCombat() {
    return true;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/zombie_pit.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
