package unsw.loopmania;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaffStrategy implements ItemStrategy{
    @Override
    /**
     *  Staff item reduces damange dealt by the Character by 65% so returns 0.35
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMultiplier(BasicEnemy enemy) {
        return 2;
    }

    @Override
    /**
     *  Staff item does not have any defence mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Staff item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  "Random chance of inflicting a trance, which transforms the attacked
     *  enemy into an allied soldier temporarily"
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy) {
        // TODO
        //  random chance of inflicting a trance, which transforms the attacked
        //  enemy into an allied soldier temporarily
    }

    @Override
    /**
     *  returns the image of the item to be displayed
     * @return the imageview of the item
     */
    public ImageView getImage() {
        Image staffImage = new Image((new File("src/images/staff.png")).toURI().toString());
        ImageView view = new ImageView(staffImage);
        return view;
    }

}
