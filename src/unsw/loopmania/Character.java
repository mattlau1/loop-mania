package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes

    private int health = 100;
    private int gold = 0;
    private int exp = 0;
    private int damage = 15;
    private int damageMultiplier = 1;

    public Character(PathPosition position) {
        super(position);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * Gets raw damage (not including damage multiplier)
     *
     * @return character's damage before multipliers
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets character's damage including multiplier
     *
     * @return character's damage after multiplier
     */
    public int getMultipliedDamage() {
        return damage * damageMultiplier;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(int damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public void resetDamageMultiplier() {
        this.damageMultiplier = 1;
    }


}
