package unsw.loopmania.Items;

import java.util.List;

import javafx.scene.image.ImageView;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Soldier;

public interface ItemStrategy {
    /**
     *
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double atkMultiplier(Enemy enemy);
    /**
     *
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMultiplier(Enemy enemy);
    /**
     *
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMultiplier(Enemy enemy);
    /**
     *  Deals with any on hit effects as a result of the weapon
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(Enemy enemy, List<Soldier> allyList);

    /**
     * Gets the image of the item to be shown
     * @return The image of the item to be shown
     */
    public ImageView getImage();
}