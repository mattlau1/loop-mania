package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;

public class TrapStrategy implements BuildingStrategy {
  private final int range = 2;
  private final int trapDamage = 20;

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

  @Override
  public boolean canSpawnEnemy(int currentCycle) {
    return false;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    return null;
  }

}
