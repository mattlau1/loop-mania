package unsw.loopmania.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Buildings.BuildingStrategy;

/**
 * a Card in the world
 * which doesn't move
 */
public class Card extends StaticEntity implements CardStrategy {
    private CardStrategy strategy;

    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y, CardStrategy cardStrategy) {
        super(x, y);
        this.strategy = cardStrategy;
    }

    public ImageView getImage() {
       return strategy.getImage();
    }

    public BuildingStrategy getBuildingStrategy() {
        return strategy.getBuildingStrategy();
    }

}
