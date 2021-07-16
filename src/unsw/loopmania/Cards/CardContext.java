package unsw.loopmania.Cards;

import javafx.scene.image.ImageView;
import unsw.loopmania.Buildings.BuildingStrategy;

public class CardContext {
  private CardStrategy strategy;

  public CardContext(CardStrategy strategy) {
    this.strategy = strategy;
  }

  public ImageView getImage() {
    return strategy.getImage();
  }

  public BuildingStrategy getBuildingStrategy() {
    return strategy.getBuildingStrategy();
  }
}
