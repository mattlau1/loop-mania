package unsw.loopmania;

public class HealthPotionStrategy implements ItemStrategy{
    @Override
    /**
     *  HealthPotion item does not have any attack mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  HealthPotion item does not have any defence mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  HealthPotion item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMultiplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  HealthPotion item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy) {}

}
