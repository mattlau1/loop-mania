package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes

    private int health;
    private int gold;
    private int exp;
    private int damage;
    private int damageMultiplier;

    public Character(PathPosition position) {
        super(position);
        this.health = 100;
        this.gold = 0;
        this.exp = 0;
        this.damage = 15;
        this.damageMultiplier = 1;
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



}
