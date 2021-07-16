package unsw.loopmania.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.StaticEntity;

/**
 * a Card in the world
 * which doesn't move
 */
public class Card extends StaticEntity {
    private CardStrategy strategy;

    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y, CardStrategy cardStrategy) {
        super(x, y);
        this.strategy = cardStrategy;
    }

    public ImageView getImage() {
        CardContext context = new CardContext(strategy);
        return context.getImage();
    }

}
