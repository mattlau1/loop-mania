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
import unsw.loopmania.Enemies.VampireEnemy;

/**
 * a basic form of building in the world
 */
public class VampireCastleStrategy implements BuildingStrategy {
  private final int range = 0;
  private final int cycleNumberToSpawnAt = 5;
  MediaPlayer vampireSound;

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
    Image image = new Image((new File("src/" + imgLoc + "/vampire_castle_building_purple_background.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return character.getCycleCount() != 0 && character.getCycleCount() % cycleNumberToSpawnAt == 0;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    try {vampireSound();}
    catch(Exception exception) {}
    return new VampireEnemy(position);
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
   * sound effect for vampire spawn
   */
  public void vampireSound() {
    String path = "src/audio/vampire.wav";
    Media music = new Media(Paths.get(path).toUri().toString());
    vampireSound = new MediaPlayer(music);
    vampireSound.setVolume(0.3);
    vampireSound.play();
  }

}
