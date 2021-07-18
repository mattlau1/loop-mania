package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.PathPosition;
import unsw.loopmania.PathTile;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Enemies.ZombieEnemy;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.GoldGoal;
import unsw.loopmania.Items.HealthPotionStrategy;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.StaffStrategy;
import unsw.loopmania.Items.StakeStrategy;
import unsw.loopmania.Items.SwordStrategy;
import unsw.loopmania.Items.TheOneRingStrategy;
import javafx.beans.property.SimpleIntegerProperty;

public class ItemTest {
    @Test
    void testAtkMultiplier() throws FileNotFoundException {
        // test stake against vampire and non vampire
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        StakeStrategy strat = new StakeStrategy();
        Item testStake = new Item(x, y, strat);

        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        VampireEnemy vamp = new VampireEnemy(new PathPosition(1, d.getOrderedPath()));
        assertEquals(0.5, testStake.getAtkMultiplier(slug));
        assertEquals(3, testStake.getAtkMultiplier(vamp));
    }

    @Test
    void testStaffBuff() {
        // test staff's effect to convert zombies to allied soldiers
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        StaffStrategy strat = new StaffStrategy();
        Item testStaff = new Item(x, y, strat);
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        ZombieEnemy zombie = new ZombieEnemy(new PathPosition(1, d.getOrderedPath()));
        assertEquals(0, d.trancedSoldiersSize());
        testStaff.onHitEffects(zombie, d.getTrancedSoldiers());
        assertEquals(1, d.trancedSoldiersSize());
        assertEquals(false, zombie.isAlive());

    }

    void testHealthPotion() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        HealthPotionStrategy strat = new HealthPotionStrategy();
        Item testStaff = new Item(x, y, strat);
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
    }

    @Test
    void testOneRing() {
        // if character has one ring equiped should prevent death once
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        TheOneRingStrategy strat = new TheOneRingStrategy();
        Item testRing = new Item(x, y, strat);
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        d.addUnequippedItem(testRing);
        assertEquals(false, d.isGameLost());
        testChar.reduceHealth(100);
        assertEquals(true, testChar.isDead());
        assertEquals(false, d.isGameLost());
        assertEquals(100, testChar.getHealth());
        assertEquals(true, testChar.isAlive());
        testChar.reduceHealth(100);
        assertEquals(false, testChar.isAlive());
        assertEquals(true, d.isGameLost());

    }

}
