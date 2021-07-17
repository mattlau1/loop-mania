package unsw.loopmania.Items;

import java.io.File;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public class HelmetStrategy implements ItemStrategy{
    /**
     *  Helmet item lowers damage dealt by the Character by 25% so returns 0.75
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    @Override
    public double atkMultiplier(Enemy enemy) {
        return 0.75;
    }

    /**
     *  Helmet item lowers damage recieved by the Character by 25% so
     *  returns 0.75
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    @Override
    public double defMultiplier(Enemy enemy) {
        return 0.75;
    }

    /**
     *  Helmet item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    @Override
    public double critMultiplier(Enemy enemy) {
        return 1;
    }

    /**
     *  Helmet item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    @Override
    public void onHitEffects(Enemy enemy, List<Soldier> allyList) {
        return;
    }

    /**
     *  returns the image of the item to be displayed
     * @return the imageview of the item
     */
    @Override
    public ImageView getImage() {
        Image image = new Image((new File("src/images/helmet.png")).toURI().toString());
        ImageView view = new ImageView(image);
        return view;
    }

}

