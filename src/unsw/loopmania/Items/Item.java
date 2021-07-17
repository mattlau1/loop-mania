package unsw.loopmania.Items;

import javafx.scene.image.ImageView;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Enemies.Enemy;
import javafx.beans.property.SimpleIntegerProperty;

public class Item extends StaticEntity implements ItemStrategy{
    private ItemStrategy strategy;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemStrategy strategy) {
        super(x, y);
        this.strategy = strategy;
    }

    public double atkMultiplier(Enemy enemy) {
        return strategy.atkMultiplier(enemy);
    }

    public double defMultiplier(Enemy enemy) {
        return strategy.defMultiplier(enemy);
    }

    public double critMultiplier(Enemy enemy) {
        return strategy.critMultiplier(enemy);
    }

    public void onHitEffects(Enemy enemy) {
        strategy.onHitEffects(enemy);
    }

    public ImageView getImage() {
        return strategy.getImage();
    }

    public ItemStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ItemStrategy strategy) {
        this.strategy = strategy;
    }



}
