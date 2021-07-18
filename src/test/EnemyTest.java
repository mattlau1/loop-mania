package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;

public class EnemyTest {

    @Test
    public void testReduceHealth() {
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        // slug spawns with 20 hp reduceHealth called with value 10 should leave slug
        // with 10hp
        assertEquals(20, slug.getHealth());
        slug.reduceHealth(10);
        assertEquals(10, slug.getHealth());
    }

    @Test
    public void testIsAlive() {
        // test if returns false when enemy recieves dmg greater than their hp
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        assertEquals(true, slug.isAlive());
        // slug spawns with 20 hp reduceHealth called with value 10 should leave slug
        // with 10hp
        slug.reduceHealth(20);
        assertEquals(false, slug.isAlive());
    }

    @Test
    public void testEnemyDrops() {
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        d.addEnemy(slug);
        assertEquals(0, testChar.getGold());
        assertEquals(0, testChar.getExp());
        assertEquals(0, d.getCards().size());
        assertEquals(0, d.getUnequip().size());
        d.runBattles();
        assertEquals(10, testChar.getGold());
        assertEquals(10, testChar.getExp());

    }
}
