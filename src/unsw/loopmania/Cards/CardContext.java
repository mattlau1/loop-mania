package unsw.loopmania.Cards;

import javafx.scene.image.ImageView;

public class CardContext {
  private CardStrategy strategy;

  public CardContext(CardStrategy strategy) {
    this.strategy = strategy;
  }

  public ImageView getImage() {
    return strategy.getImage();
  }
}
