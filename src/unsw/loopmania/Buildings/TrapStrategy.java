package unsw.loopmania.Buildings;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;

public class TrapStrategy implements BuildingStrategy {
  private final int range = 2;
  private final int trapDamage = 20;
  MediaPlayer trapSound;

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
    trapSound();
  }

  @Override
  public boolean usableOutsideCombat() {
    return true;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/trap.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return false;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    return null;
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
   * sound effect for enemy walking into the trap
   */
  public void trapSound() {
    String path = "src/audio/trap.mp3";
    Media music = new Media(Paths.get(path).toUri().toString());
    trapSound = new MediaPlayer(music);
    trapSound.play();
    
  }

}
