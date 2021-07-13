package unsw.loopmania;

import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity {
    // TODO = modify this, and add additional forms of enemy

    private int health;
    private int battleRange;
    private int supportRange;
    private int damage;

    public BasicEnemy(PathPosition position) {
        super(position);
        this.health = 100;
        this.battleRange = 1;
        this.supportRange = 2;
        this.damage = 1;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getbattleRange() {
        return battleRange;
    }

    public void setbattleRange(int battleRange) {
        this.battleRange = battleRange;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSupportRange() {
        return supportRange;
    }

    public void setSupportRange(int supportRange) {
        this.supportRange = supportRange;
    }

    /**
     * move the enemy
     */
    public void move(){
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
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
