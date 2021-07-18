package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.graalvm.compiler.nodes.ValuePhiNode;
import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.BarracksStrategy;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.BuildingStrategy;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Buildings.HerosCastleStrategy;
import unsw.loopmania.Buildings.TowerStrategy;
import unsw.loopmania.Buildings.TrapStrategy;
import unsw.loopmania.Buildings.VampireCastleStrategy;
import unsw.loopmania.Buildings.VillageStrategy;
import unsw.loopmania.Buildings.ZombiePitStrategy;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;

public class BuildingTest {

    @Test
    public void testBarracks() {
        BarracksStrategy strat = new BarracksStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building barracks = new Building(pathPos.getX(), pathPos.getY(), strat);
        assertEquals(0, testChar.soldiersSize());
        d.addBuildingToWorld(barracks);
        d.runBattles();
        assertEquals(1, testChar.soldiersSize());
    }

    @Test
    public void testCampfire() {
        CampfireStrategy strat = new CampfireStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building campfire = new Building(pathPos.getX(), pathPos.getY(), strat);
        SlugEnemy slug = new SlugEnemy(pathPos);
        d.addEnemy(slug);
        assertEquals(1, testChar.getDamageMultiplier());
        d.addBuildingToWorld(campfire);
        d.runBattles();
        assertEquals(100, testChar.getHealth());
    }

    @Test
    public void testHerosCastle() {
        HerosCastleStrategy strat = new HerosCastleStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        assertEquals(0, testChar.getCycleCount());
        Building castle = new Building(pathPos.getX(), pathPos.getY(), strat);
        d.addBuildingToWorld(castle);
        d.runTickMoves();
        assertEquals(1, testChar.getCycleCount());
    }

    @Test
    public void testTowers() {
        TowerStrategy strat = new TowerStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building tower = new Building(pathPos.getX(), pathPos.getY(), strat);
        SlugEnemy slug = new SlugEnemy(pathPos);
        d.addEnemy(slug);
        assertEquals(1, testChar.getDamageMultiplier());
        d.addBuildingToWorld(tower);
        d.runBattles();
        assertEquals(100, testChar.getHealth());
    }

    @Test
    public void testTrap() {
        TrapStrategy strat = new TrapStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building trap = new Building(pathPos.getX(), pathPos.getY(), strat);
        SlugEnemy slug = new SlugEnemy(pathPos);
        d.addEnemy(slug);
        assertEquals(1, testChar.getDamageMultiplier());
        d.addBuildingToWorld(trap);
        d.runBattles();
        assertEquals(100, testChar.getHealth());
    }

    @Test
    public void testVampireCastle() {
        VampireCastleStrategy strat = new VampireCastleStrategy();
        HerosCastleStrategy strat2 = new HerosCastleStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building vampCastle = new Building(pathPos.getX(), pathPos.getY(), strat);
        d.addBuildingToWorld(vampCastle);
        Building castle = new Building(pathPos.getX(), pathPos.getY(), strat2);
        d.addBuildingToWorld(castle);
        d.addBuildingToWorld(vampCastle);
        List<Enemy> enemies = d.possiblySpawnEnemies();
        int vampCount = 0;
        for (Enemy enemy : enemies) {
            if (enemy.getExpDrop() == 100) {
                vampCount++;
            }
        }
        assertEquals(0, vampCount);
        while (testChar.getCycleCount() < 5) {
            testChar.incrementCycleCount();
        }
        List<Enemy> enemies2 = d.possiblySpawnEnemies();
        for (Enemy enemy : enemies2) {
            if (enemy.getExpDrop() == 100) {
                vampCount++;
            }
        }
        assertNotEquals(0, vampCount);
    }

    @Test
    public void testVillage() {
        VillageStrategy strat = new VillageStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building village = new Building(pathPos.getX(), pathPos.getY(), strat);
        testChar.reduceHealth(80);
        assertEquals(20, testChar.getHealth());
        d.addBuildingToWorld(village);
        d.runBattles();
        assertEquals(70, testChar.getHealth());
    }

    @Test
    public void testZombiePit() {
        ZombiePitStrategy strat = new ZombiePitStrategy();
        HerosCastleStrategy strat2 = new HerosCastleStrategy();
        TestSetup s = new TestSetup();
        LoopManiaWorld d = s.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, d.getOrderedPath());
        Character testChar = new Character(pathPos);
        d.setCharacter(testChar);
        Building zombiePit = new Building(pathPos.getX(), pathPos.getY(), strat);
        d.addBuildingToWorld(zombiePit);
        Building castle = new Building(pathPos.getX(), pathPos.getY(), strat2);
        d.addBuildingToWorld(castle);
        d.addBuildingToWorld(zombiePit);
        List<Enemy> enemies = d.possiblySpawnEnemies();
        int zombieCount = 0;
        for (Enemy enemy : enemies) {
            if (enemy.getExpDrop() == 25) {
                zombieCount++;
            }
        }
        assertEquals(0, zombieCount);
        while (testChar.getCycleCount() < 5) {
            testChar.incrementCycleCount();
        }
        List<Enemy> enemies2 = d.possiblySpawnEnemies();
        for (Enemy enemy : enemies2) {
            if (enemy.getExpDrop() == 25) {
                zombieCount++;
            }
        }
        assertNotEquals(0, zombieCount);
    }
}
