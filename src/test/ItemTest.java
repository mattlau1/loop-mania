package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.StakeStrategy;
import unsw.loopmania.Items.SwordStrategy;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

public class ItemTest {
    @Test
    void testAtkMultiplier() throws FileNotFoundException {
        // test stake against vampire and non vampire
        // LoopManiaWorldControllerLoader loopManiaLoader = new
        // LoopManiaWorldControllerLoader(
        // "world_with_twists_and_turns.json");
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        StakeStrategy strat = new StakeStrategy();
        Item testStake = new Item(x, y, strat);
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        VampireEnemy vamp = new VampireEnemy(new PathPosition(1, d.getOrderedPath()));
        assertEquals(0.5, testStake.atkMultiplier(slug));
        assertEquals(3, testStake.atkMultiplier(vamp));
    }

    @Test
    void testOnHitEffects() {

    }
}
