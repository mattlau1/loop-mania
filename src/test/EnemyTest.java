package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.SlugEnemy;

public class EnemyTest {

    @Test
    public void testReduceHealth() {
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, world.getOrderedPath()));
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
        // tests that enemies drop gold and exp
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        Character testChar = new Character(new PathPosition(1, d.getOrderedPath()));
        d.setCharacter(testChar);
        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        d.addEnemy(slug);
        // check that character initially has 0 gold and exp
        assertEquals(0, testChar.getGold());
        assertEquals(0, testChar.getExp());
        d.runBattles();
        // check that after killing the slug, the character gained 10 gold and exp
        assertEquals(10, testChar.getGold());
        assertEquals(10, testChar.getExp());
    }
}
