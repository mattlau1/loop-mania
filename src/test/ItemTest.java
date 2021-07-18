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

        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, world.getOrderedPath()));
        VampireEnemy vamp = new VampireEnemy(new PathPosition(1, world.getOrderedPath()));
        // stake should have a low multiplier against non-vampire
        assertEquals(0.5, testStake.getAtkMultiplier(slug));
        // stake should have a high multiplier against vampire
        assertEquals(3, testStake.getAtkMultiplier(vamp));
    }

    @Test
    public void testStaffBuff() {
        // test staff's effect to convert zombies to allied soldiers
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        StaffStrategy strat = new StaffStrategy();
        Item testStaff = new Item(x, y, strat);
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        ZombieEnemy zombie = new ZombieEnemy(new PathPosition(1, world.getOrderedPath()));
        // checks that there are initially no tranced soldiers
        assertEquals(0, world.trancedSoldiersSize());
        testStaff.onHitEffects(zombie, world.getTrancedSoldiers());
        // checks that the soldier was turned into a tranced solider
        assertEquals(1, world.trancedSoldiersSize());
        // checks that the former zombie no longer exists
        assertEquals(false, zombie.isAlive());

    }

    @Test
    public void testHealthPotion() {
        // tests that the health potions heal the player
        HealthPotionStrategy strat = new HealthPotionStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);
        testChar.reduceHealth(50);
        // checks that the character initially has half health (50)
        assertEquals(50, testChar.getHealth());
        Item testPot = new Item(pathPos.getX(), pathPos.getY(), strat);
        world.addPathItems(testPot);
        world.runBattles();
        // checks that after consuming the potion the character is at maxed health
        assertEquals(100, testChar.getHealth());

    }

    @Test
    public void testOneRing() {
        // if character has one ring equiped should prevent death once
        TheOneRingStrategy strat = new TheOneRingStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        Character testChar = new Character(new PathPosition(1, world.getOrderedPath()));
        world.setCharacter(testChar);
        // gives the character the one ring
        world.addSpecificUnequippedItem(strat);
        assertEquals(false, world.isGameLost());
        // kills the character
        testChar.reduceHealth(100);
        // checks that the character is revived
        assertEquals(true, testChar.isDead());
        assertEquals(false, world.isGameLost());
        assertEquals(100, testChar.getHealth());
        assertEquals(true, testChar.isAlive());
        // kills the character
        testChar.reduceHealth(100);
        // checks that the character does not revive and the game is lost
        assertEquals(false, testChar.isAlive());
        assertEquals(true, world.isGameLost());

    }

}
