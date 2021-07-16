package unsw.loopmania;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StakeStrategy implements ItemStrategy{
    @Override
    /**
     *  Stake item lowers danage dealt by 50% unless the Character is attacking
     *  a vampire, in which case deal three times as much damage
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMultiplier(BasicEnemy enemy) {
        if (enemy instanceof VampireEnemy) return 3;
        return 0.5;
    }

    @Override
    /**
     *  Stake item does not have any defence mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Stake item does not have any defence mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Stake item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy) {}

    @Override
    /**
     *  returns the image of the item to be displayed
     * @return the imageview of the item
     */
    public ImageView getImage() {
        Image stakeImage = new Image((new File("src/images/stake.png")).toURI().toString());
        ImageView view = new ImageView(stakeImage);
        return view;
    }

}

