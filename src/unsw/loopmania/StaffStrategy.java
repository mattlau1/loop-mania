package unsw.loopmania;

public class SwordStrategy implements ItemStrategy{
    @Override
    /**
     *  Staff item reduces damange dealt by the Character by 65% so returns 0.35
     * @param enemy The monster/entity that the Character is in combat with
     * @return The attack multiplier against the monster, may vary depending on
     *          the monster type
     */
    public double atkMuliplier(BasicEnemy enemy) {
        return 2;
    }

    @Override
    /**
     *  Staff item does not have any defence mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The defence multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double defMuliplier(BasicEnemy enemy) {
        return 1;
    }

    @Override
    /**
     *  Staff item does not have any critical hit mechanics, so returns 1
     * @param enemy The monster/entity that the Character is in combat with
     * @return The crit multiplier against the monster, can vary depending on
     *          the monster type
     */
    public double critMuliplier(BasicEnemy enemy) {
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

}
