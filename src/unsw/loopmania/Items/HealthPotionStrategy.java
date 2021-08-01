package unsw.loopmania.Items;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;

public class HealthPotionStrategy implements ItemStrategy {
  private final double atkMultiplier = 1;
  private final double defMultiplier = 1;
  private final double critMultiplier = 1;
  private final int range = 1;
  private final int price = 100;
  MediaPlayer potionSound;

  @Override
  public double getAtkMultiplier(Enemy enemy) {
    return atkMultiplier;
  }

  @Override
  public double getDefMultiplier(Enemy enemy) {
    return defMultiplier;
  }

  @Override
  public double getCritMultiplier(Enemy enemy) {
    return critMultiplier;
  }

  @Override
  public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
    return;
  }

  @Override
  public boolean isDestroyedOnUse() {
    return false;
  }

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useItem(Character character) {
    character.setHealth(character.getInitialHealth());
    potionSound();
  }

  /**
   * returns the image of the item to be displayed
   *
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/brilliant_blue_new.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

  @Override
  public int getPrice() {
    return price;
  }

  /**
   * sound effect for consuming potion
   */
  public void potionSound() {
    String path = "src/audio/bottle.mp3";
    Media music = new Media(Paths.get(path).toUri().toString());
    potionSound = new MediaPlayer(music);
    potionSound.play();
  }

}
