package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;

public class EnemyTest {

    @Test
    void testCriticalHit() {

    }

    @Test
    void testMove() {
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();

        SlugEnemy slug = new SlugEnemy(new PathPosition(1, d.getOrderedPath()));
        PathPosition x = slug.getX()

    }

    @Test
    void testReduceHealth() {
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
    void testIsAlive() {
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
}
