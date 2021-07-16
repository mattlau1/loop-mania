package unsw.loopmania;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelmetStrategy implements ItemStrategy{
    @Override
    /**
     *  Helmet item lowers damage dealt by the Character by 25% so returns 0.75
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMultiplier(BasicEnemy enemy) {
        return 0.75;
    }

    @Override
    /**
     *  Helmet item lowers damage recieved by the Character by 25% so
     *  returns 0.75
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMultiplier(BasicEnemy enemy) {
        return 0.75;
    }

    @Override
    /**
     *  Helmet item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Helmet item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy) {}

    @Override
    /**
     *  returns the image of the item to be displayed
     * @return the imageview of the item
     */
    public ImageView getImage() {
        Image helmetImage = new Image((new File("src/images/helmet.png")).toURI().toString());
        ImageView view = new ImageView(helmetImage);
        return view;
    }

}

