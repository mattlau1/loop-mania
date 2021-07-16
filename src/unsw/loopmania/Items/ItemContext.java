package unsw.loopmania.Items;

import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;

public class ItemContext {
    private ItemStrategy itemStrategy;

    public ItemContext(ItemStrategy itemStrategy){
       this.itemStrategy = itemStrategy;
    }

    public double atkMultiplier(BasicEnemy enemy){
       return itemStrategy.atkMultiplier(enemy);
    }

    public double defMultiplier(BasicEnemy enemy){
        return itemStrategy.defMultiplier(enemy);
    }

    public double critMultiplier(BasicEnemy enemy){
        return itemStrategy.critMultiplier(enemy);
    }

    public void onHitEffects(BasicEnemy enemy){
        itemStrategy.onHitEffects(enemy);
    }

    public ImageView getImage() {
        return itemStrategy.getImage();
    }

}
