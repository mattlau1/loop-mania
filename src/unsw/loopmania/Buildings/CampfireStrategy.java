package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;

public class CampfireStrategy implements BuildingStrategy {
  private final int range = 10;

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    character.setDamageMultiplier(2);
  }

  @Override
  public void useBuilding(Enemy enemy) {
    return;
  }

  @Override
  public boolean usableOutsideCombat() {
    return false;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/campfire.png")).toURI().toString());
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
