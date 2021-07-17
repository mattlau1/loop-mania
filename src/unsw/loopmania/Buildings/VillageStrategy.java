package unsw.loopmania.Buildings;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;

public class VillageStrategy implements BuildingStrategy {
  private final int range = 2;
  private final int healAmount = 50;

  @Override
  public int getRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    character.addHealth(healAmount);
  }

  @Override
  public void useBuilding(BasicEnemy enemy) {
    return;
  }

  @Override
  public boolean usableOutsideCombat() {
    return true;
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/village.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }


}
