package unsw.loopmania;

import java.util.Random;

public class SlugEnemy extends BasicEnemy {

    private final int health = 20;
    private final int battleRange = 2;
    private final int supportRange = 10;
    private final int damage = 5;
    private final int expDrop = 10;
    private final int goldDrop = 10;

    public SlugEnemy(PathPosition position) {
        super(position);
        super.setHealth(health);
        super.setBattleRange(battleRange);
        super.setSupportRange(supportRange);
        super.setDamage(damage);
        super.setExpDrop(expDrop);
        super.setGoldDrop(goldDrop);
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
