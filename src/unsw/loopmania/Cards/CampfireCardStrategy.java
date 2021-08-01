package unsw.loopmania.Cards;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Buildings.BuildingStrategy;
import unsw.loopmania.Buildings.CampfireStrategy;

public class CampfireCardStrategy implements CardStrategy {
  /**
   * returns the image of the item to be displayed
   *
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage(String imgLoc) {
    Image barracksImage = new Image((new File("src/" + imgLoc + "/campfire_card.png")).toURI().toString());
    ImageView view = new ImageView(barracksImage);
    return view;
  }

  @Override
  public BuildingStrategy getBuildingStrategy() {
    return new CampfireStrategy();
  }

}
