package unsw.loopmania.Cards;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Buildings.BuildingStrategy;
import unsw.loopmania.Buildings.VampireCastleStrategy;

public class VampireCastleCardStrategy implements CardStrategy {
  /**
   * returns the image of the item to be displayed
   *
   * @return the imageview of the item
   */
  @Override
  public ImageView getImage(String imgLoc) {
    Image vampireCastleImage = new Image((new File("src/" + imgLoc + "/vampire_castle_card.png")).toURI().toString());
    ImageView view = new ImageView(vampireCastleImage);
    return view;
  }

  @Override
  public BuildingStrategy getBuildingStrategy() {
    return new VampireCastleStrategy();
  }
}
