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
    void testIsAlive() {

    }

    @Test
    void testMove() {
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        int indexInPath = 3;
        SlugEnemy slug = new SlugEnemy(new PathPosition(indexInPath, d.getOrderedPath()));
        slug.move();

    }

    @Test
    void testReduceHealth() {
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        int indexInPath = 1;
        SlugEnemy slug = new SlugEnemy(new PathPosition(indexInPath, d.getOrderedPath()));
        // slug spawns with 20 hp reduceHealth called with value 10 should leave slug
        // with 10hp
        slug.reduceHealth(10);
        assertEquals(10, slug.getHealth());
    }
}
