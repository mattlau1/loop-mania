package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Item extends StaticEntity {
    private ItemStrategy strategy;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemStrategy strategy) {
    super(x, y);
    this.strategy = strategy;
    }

    public double executeAtkMultiplier(BasicEnemy enemy) {
    ItemContext context = new ItemContext(strategy);
    return context.executeAtkMultiplier(enemy);
    }

}
