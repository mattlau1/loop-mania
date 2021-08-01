package unsw.loopmania.Cards;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Buildings.BuildingStrategy;
import unsw.loopmania.Buildings.ZombiePitStrategy;

public class ZombiePitCardStrategy implements CardStrategy {
  /**
   * returns the image of the item to be displayed
   *
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage(String imgLoc) {
    Image zombiePitImage = new Image((new File("src/" + imgLoc + "/zombie_pit_card.png")).toURI().toString());
    ImageView view = new ImageView(zombiePitImage);
    return view;
  }

  @Override
  public BuildingStrategy getBuildingStrategy() {
    return new ZombiePitStrategy();
  }
}
