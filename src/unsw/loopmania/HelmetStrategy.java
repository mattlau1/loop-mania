package unsw.loopmania;

public class HelmetStrategy implements ItemStrategy{
    @Override
    /**
     *  Helmet item lowers damage dealt by the Character by 25% so returns 0.75
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMuliplier(BasicEnemy enemy) {
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
    public double defMuliplier(BasicEnemy enemy) {
        return 0.75;
    }

    @Override
    /**
     *  Helmet item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMuliplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Helmet item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy) {}

}

