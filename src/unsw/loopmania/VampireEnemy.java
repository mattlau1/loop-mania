package unsw.loopmania;

import java.util.Random;

public class VampireEnemy extends BasicEnemy {

    private int health;
    private int battleRange;
    private int supportRange;
    private int damage;
    private int expDrop;
    private int goldDrop;

    public VampireEnemy(PathPosition position) {
        super(position);
        this.health = 60;
        this.battleRange = 20;
        this.supportRange = 50;
        this.damage = 20;
        this.expDrop = 100;
        this.goldDrop = 100;
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

    public int getExpDrop() {
        return expDrop;
    }

    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    public int getGoldDrop() {
        return goldDrop;
    }

    public void setGoldDrop(int goldDrop) {
        this.goldDrop = goldDrop;
    }

    @Override
    public void move(){
        // vampire changes direction when near the campfire
        int directionChoice = (new Random()).nextInt(3);
        if (directionChoice == 0){
            moveUpPath();
        }
    }
}
