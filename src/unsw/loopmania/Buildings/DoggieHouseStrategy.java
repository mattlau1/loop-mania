package unsw.loopmania.Buildings;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.DoggieEnemy;
import unsw.loopmania.Enemies.Enemy;

public class DoggieHouseStrategy implements BuildingStrategy {
  private final int range = 1;
  private final int cycleNumberToSpawnAt = 20;
  MediaPlayer doggieSound;

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
    return;
  }

  @Override
  public boolean usableOutsideCombat() {
    return true;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/doggiehouse.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return character.getCycleCount() == cycleNumberToSpawnAt;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    doggieSound();
    return new DoggieEnemy(position);
  }

  @Override
  public boolean isSpawnLocation() {
    return false;
  }

  @Override
  public boolean canOnlySpawnNextToPath() {
    return false;
  }

  @Override
  public boolean canOnlySpawnOnPath() {
    return true;
  }

  /**
   * sound effect for doggie spawn
   */
  public void doggieSound() {
    String path = "src/audio/doggie.mp3";
    Media music = new Media(Paths.get(path).toUri().toString());
    doggieSound = new MediaPlayer(music);
    doggieSound.setVolume(0.3);
    doggieSound.play();
  }

}
