package unsw.loopmania.Items;

import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Soldier;
import unsw.loopmania.StaticEntity;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class Item extends StaticEntity implements ItemStrategy{
    private ItemStrategy strategy;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemStrategy strategy) {
        super(x, y);
        this.strategy = strategy;
    }

    public double atkMultiplier(BasicEnemy enemy) {
        return strategy.atkMultiplier(enemy);
    }

    public double defMultiplier(BasicEnemy enemy) {
        return strategy.defMultiplier(enemy);
    }

    public double critMultiplier(BasicEnemy enemy) {
        return strategy.critMultiplier(enemy);
    }

    public void onHitEffects(BasicEnemy enemy, List<Soldier> allyList) {
        strategy.onHitEffects(enemy, allyList);
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
