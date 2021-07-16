package unsw.loopmania.Items;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;

public class GoldStrategy implements ItemStrategy{
    /**
     *  Gold item does not have any attack mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    @Override
    public double atkMultiplier(BasicEnemy enemy) {
        return 1;
    }

    /**
     *  Gold item does not have any defence mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    @Override
    public double defMultiplier(BasicEnemy enemy) {
        return 1;
    }

    /**
     *  Gold item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    @Override
    public double critMultiplier(BasicEnemy enemy) {
        return 1;
    }

    /**
     *  Gold item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    @Override
    public void onHitEffects(BasicEnemy enemy) {}

    /**
     *  returns the image of the item to be displayed
     * @return the imageview of the item
     */
    @Override
    public ImageView getImage() {
        Image image = new Image((new File("src/images/gold_pile.png")).toURI().toString());
        ImageView view = new ImageView(image);
        return view;
    }
}
