package unsw.loopmania.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Buildings.BuildingStrategy;

/**
 * a Card in the world which doesn't move
 */
public class Card extends StaticEntity implements CardStrategy {
  private CardStrategy strategy;

  public Card(SimpleIntegerProperty x, SimpleIntegerProperty y, CardStrategy cardStrategy) {
    super(x, y);
    this.strategy = cardStrategy;
  }

  /**
   * Gets the image for the card for javafx UI
   *
   * @return card image
   */
  public ImageView getImage(String imgLoc) {
    return strategy.getImage(imgLoc);
  }

  /**
   * Returns the corresponding building strategy for the card
   *
   * @return corresponding building strategy
   */
  public BuildingStrategy getBuildingStrategy() {
    return strategy.getBuildingStrategy();
  }

}
