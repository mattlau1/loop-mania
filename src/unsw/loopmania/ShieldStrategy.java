package unsw.loopmania;

public class ShieldStrategy implements ItemStrategy{
    @Override
    /**
     *  Shield item does not have any attack mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMuliplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Shield items does not have ay defence mechanics
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMuliplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Shield item causes critical vampire attacks to have a 60% lower chance
     *  of occurring so returns 0.4
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMuliplier(BasicEnemy enemy) {
        return 0.4;
    }

    @Override
    /**
     *  Shield item does not have any on hit effects so does nothing
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy) {}

}

