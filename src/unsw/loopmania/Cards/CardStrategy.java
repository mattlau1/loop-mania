package unsw.loopmania.Cards;

import javafx.scene.image.ImageView;
import unsw.loopmania.Buildings.BuildingStrategy;

public interface CardStrategy {
  /**
   * Gets the image for the card for javafx UI
   *
   * @return card image
   */
  public ImageView getImage();

  /**
   * Returns the corresponding building strategy for the card
   *
   * @return corresponding building strategy
   */
  public BuildingStrategy getBuildingStrategy();
}
