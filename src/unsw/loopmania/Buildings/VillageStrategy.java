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
import unsw.loopmania.Enemies.ThiefEnemy;

public class VillageStrategy implements BuildingStrategy {
  private final int range = 1;
  private final int healAmount = 50;
  MediaPlayer healSound;

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    character.addHealth(healAmount);
    double newHealth = character.getHealth() + healAmount;
    character.setHealth(newHealth <= character.getMaxHealth() ? newHealth : character.getMaxHealth());
    healSound();
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
    Image image = new Image((new File("src/" + imgLoc + "/village.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return true;
  }

  @Override
  public Enemy spawnEnemy(PathPosition position) {
    return new ThiefEnemy(position);
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
   * sound effect for walking past the village
   */
  public void healSound() {
    String path = "src/audio/heal.mp3";
    Media music = new Media(Paths.get(path).toUri().toString());
    healSound = new MediaPlayer(music);
    healSound.setVolume(0.3);
    healSound.play();

  }

}
