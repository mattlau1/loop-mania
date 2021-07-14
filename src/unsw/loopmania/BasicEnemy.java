package unsw.loopmania;

import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends MovingEntity {

    private int health;
    private int battleRange;
    private int supportRange;
    private int damage;
    private int expDrop;
    private int goldDrop;

    /**
     * backbone of the enemy constructor
     * 
     * @param position the position where the enemy will spawn in the map
     */
    public BasicEnemy(PathPosition position) {
        super(position);
    }

    /**
     * get the current health of the enemy
     * @return the enemy's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * change the health of the enemy
     * @param health the new health for the enemy
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * get the battle range of the enemy
     * @return the enemy's battle range
     */
    public int getBattleRange() {
        return battleRange;
    }

    /**
     * change the battle range of the enemy
     * @param battleRange the new battle range for the enemy
     */
    public void setBattleRange(int battleRange) {
        this.battleRange = battleRange;
    }

    /**
     * get the battle range of the enemy
     * @return the enemy's damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * change the damage of the enemy
     * @param damage the damage for the enemy
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * get the support range of the enemy
     * @return the enemy's support range
     */
    public int getSupportRange() {
        return supportRange;
    }

    /**
     * change the support range of the enemy
     * @param supportRange the new battle support for the enemy
     */
    public void setSupportRange(int supportRange) {
        this.supportRange = supportRange;
    }

    /**
     * get the experience drop from the enemy
     * @return the enemy's experience drop
     */
    public int getExpDrop() {
        return expDrop;
    }

    /**
     * change the experience drop of the enemy
     * @param expDrop the new experience drop for the enemy
     */
    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    /**
     * get the gold drop from the enemy
     * @return the enemy's gold drop
     */
    public int getGoldDrop() {
        return goldDrop;
    }

    /**
     * change the gold drop of the enemy
     * @param goldDrop the new gold drop for the enemy
     */
    public void setGoldDrop(int goldDrop) {
        this.goldDrop = goldDrop;
    }

    /**
     * move the enemy
     */
    public void move(){
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }
}
