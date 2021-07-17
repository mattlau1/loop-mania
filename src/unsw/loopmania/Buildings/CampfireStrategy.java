package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;

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
  public void useBuilding(BasicEnemy enemy) {
    // enemies cannot use campfires so do nothing
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


}
