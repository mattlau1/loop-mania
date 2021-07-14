package unsw.loopmania;

public interface ItemStrategy {
    // battle operations
    // fdmg = dmg * atkMult * (1 - defMult)
    /**
     *
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double atkMuliplier(BasicEnemy enemy);
    /**
     *
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMuliplier(BasicEnemy enemy);
    /**
     *
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMuliplier(BasicEnemy enemy);
    /**
     *  Deals with any on hit effects as a result of the weapon
     * @param enemy The monster/entity that the Character is in combat with
     */
    public void onHitEffects(BasicEnemy enemy);
}