package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;

public class TrapStrategy implements BuildingStrategy {
  private final int range = 2;
  private final int trapDamage = 20;

  @Override
  public int getBuildingRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    return;
  }

  @Override
  public void useBuilding(BasicEnemy enemy) {
    enemy.reduceHealth(trapDamage);
  }

  @Override
  public boolean usableOutsideCombat() {
    return true;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/trap.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
