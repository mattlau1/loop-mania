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
    public void testAtkMultiplier() throws FileNotFoundException {
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
    public void testStaffBuff() {
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

    @Test
    public void testHealthPotion() {
        // tests that the health potions heal the player
        HealthPotionStrategy strat = new HealthPotionStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        testChar.reduceHealth(50);
        assertEquals(50, testChar.getHealth());
        Item testPot = new Item(pathPos.getX(), pathPos.getY(), strat);
        d.addPathItems(testPot);
        d.runBattles();
        assertEquals(100, testChar.getHealth());

    }

    @Test
    public void testOneRing() {
        // if character has one ring equiped should prevent death once
        TheOneRingStrategy strat = new TheOneRingStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        d.addSpecificUnequippedItem(strat);
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
