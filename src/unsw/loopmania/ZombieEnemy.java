package unsw.loopmania;

import java.util.Random;

public class ZombieEnemy extends BasicEnemy {

    private final double health = 30;
    private final int battleRange = 10;
    private final int supportRange = 50;
    private final double damage = 10;
    private final int expDrop = 25;
    private final int goldDrop = 25;

    /**
     * spawn the zombie constructor
     *
     * @param position the position where the enemy will spawn in the map
     */
    public ZombieEnemy(PathPosition position) {
        super(position);
        setHealth(health);
        setBattleRange(battleRange);
        setSupportRange(supportRange);
        setDamage(damage);
        setExpDrop(expDrop);
        setGoldDrop(goldDrop);
    }

    @Override
    public void move(){
        // zombie moves slower than all of the enemies
        int directionChoice = (new Random()).nextInt(3);
        if (directionChoice == 0)
            moveUpPath();
    }
}
