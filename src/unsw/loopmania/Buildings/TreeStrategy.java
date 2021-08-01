package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;

public class TreeStrategy implements BuildingStrategy {

  @Override
  public boolean canOnlySpawnNextToPath() {
    return false;
  }

  @Override
  public boolean canOnlySpawnOnPath() {
    return false;
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return false;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/tree.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getRange() {
    return 0;
  }

  @Override
  public boolean isSpawnLocation() {
    return false;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    return null;
  }

  @Override
  public boolean usableOutsideCombat() {
    return false;
  }

  @Override
  public void useBuilding(Character character) {
    return;
  }

  @Override
  public void useBuilding(Enemy enemy) {
    return;
  }

}
