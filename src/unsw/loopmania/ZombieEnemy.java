package unsw.loopmania;

import java.util.Random;

public class ZombieEnemy extends BasicEnemy {

    private int health;
    private int battleRange;
    private int supportRange;
    private int damage;

    public ZombieEnemy(PathPosition position) {
        super(position);
        this.health = 20;
        this.battleRange = 10;
        this.supportRange = 50;
        this.damage = 69;
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

    @Override
    public void move(){
        // zombie moves slower than all of the enemies
        int directionChoice = (new Random()).nextInt(3);
        if (directionChoice == 0)
            moveUpPath();
    }
}
