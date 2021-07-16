package unsw.loopmania;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.beans.property.SimpleIntegerProperty;

public class Item extends StaticEntity {
    private ItemStrategy strategy;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemStrategy strategy) {
        super(x, y);
        this.strategy = strategy;
    }

    public double atkMultiplier(BasicEnemy enemy) {
        ItemContext context = new ItemContext(strategy);
        return context.atkMultiplier(enemy);
    }

    public double defMultiplier(BasicEnemy enemy) {
        ItemContext context = new ItemContext(strategy);
        return context.defMultiplier(enemy);
    }

    public double critMultiplier(BasicEnemy enemy) {
        ItemContext context = new ItemContext(strategy);
        return context.critMultiplier(enemy);
    }

    public void onHitEffects(BasicEnemy enemy) {
        ItemContext context = new ItemContext(strategy);
        context.onHitEffects(enemy);
    }

    public ImageView getImage() {
        ItemContext context = new ItemContext(strategy);
        return context.getImage();
    }

}
