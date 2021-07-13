package unsw.loopmania;

import java.util.Random;

public class SlugEnemy extends BasicEnemy {

    private int health;
    private int battleRange;
    private int supportRange;
    private int damage;

    public SlugEnemy(PathPosition position) {
        super(position);
        this.health = 50;
        this.battleRange = 2;
        this.supportRange = 9999;
        this.damage = 9999;
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
        // slug has random movement and a chance to not move at all
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

}
