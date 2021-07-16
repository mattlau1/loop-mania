package unsw.loopmania;

import java.util.Random;

public class VampireEnemy extends BasicEnemy {

    private final double health = 60;
    private final int battleRange = 20;
    private final int supportRange = 50;
    private final double damage = 20;
    private final int expDrop = 100;
    private final int goldDrop = 100;

    /**
     * spawn the vampire constructor
     *
     * @param position the position where the enemy will spawn in the map
     */
    public VampireEnemy(PathPosition position) {
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
        // vampire changes direction when near the campfire

        // campfire is yet to be implemented
        int directionChoice = (new Random()).nextInt(3);
        if (directionChoice == 0){
            moveUpPath();
        }
    }
}
