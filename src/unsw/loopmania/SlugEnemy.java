package unsw.loopmania;

import java.util.Random;

public class SlugEnemy extends BasicEnemy {

    private final double health = 20;
    private final int battleRange = 2;
    private final int supportRange = 10;
    private final double damage = 5;
    private final int expDrop = 10;
    private final int goldDrop = 10;

    /**
     * spawn the slug constructor
     *
     * @param position the position where the enemy will spawn in the map
     */
    public SlugEnemy(PathPosition position) {
        super(position);
        setHealth(health);
        setBattleRange(battleRange);
        setSupportRange(supportRange);
        setDamage(damage);
        setExpDrop(expDrop);
        setGoldDrop(goldDrop);
    }

    @Override
    public void move() {
        // slug has random movement and a chance to not move at all
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0) {
            moveUpPath();
        } else if (directionChoice == 1) {
            moveDownPath();
        }
    }

}
