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
import unsw.loopmania.Enemies.ZombieEnemy;

public class ZombiePitStrategy implements BuildingStrategy {
  private final int range = 0;
  MediaPlayer zombieSound;

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
    Image image = new Image((new File("src/" + imgLoc + "/zombie_pit.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return character.getCycleCount() != 0;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    try {
      zombieSound();
    } catch (Exception exception) {
    }
    return new ZombieEnemy(position);
  }

  @Override
  public boolean isSpawnLocation() {
    return false;
  }

  @Override
  public boolean canOnlySpawnNextToPath() {
    return true;
  }

  @Override
  public boolean canOnlySpawnOnPath() {
    return false;
  }

  /**
   * sound effect for zombie spawn
   */
  public void zombieSound() {
    String path = "src/audio/zombie.wav";
    Media music = new Media(Paths.get(path).toUri().toString());
    zombieSound = new MediaPlayer(music);
    zombieSound.setVolume(0.2);
    zombieSound.play();
  }

}
