package unsw.loopmania.Cards;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VillageCardStrategy implements CardStrategy {
  @Override
  /**
   * returns the image of the item to be displayed
   * @return the imageview of the item
   */
  public ImageView getImage() {
      Image villageImage = new Image((new File("src/images/village_card.png")).toURI().toString());
      ImageView view = new ImageView(villageImage);
      return view;
  }
}
