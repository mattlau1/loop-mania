package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.BarracksStrategy;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Buildings.HerosCastleStrategy;
import unsw.loopmania.Buildings.TowerStrategy;
import unsw.loopmania.Buildings.TrapStrategy;
import unsw.loopmania.Buildings.VampireCastleStrategy;
import unsw.loopmania.Buildings.VillageStrategy;
import unsw.loopmania.Buildings.ZombiePitStrategy;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Enemies.ZombieEnemy;

public class BuildingTest {

    @Test
    public void testBarracks() {
        // tests that character gains soldiers at barracks

        // test setup
        BarracksStrategy barracksStrategy = new BarracksStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);
        Building barracks = new Building(pathPos.getX(), pathPos.getY(), barracksStrategy);

        // checks that there are no ally soldiers initially
        assertEquals(0, testChar.soldiersSize());

        // spawn barracks into the world
        world.addBuildingToWorld(barracks);
        world.runBattles();

        // checks that the character has an allied soldier
        assertEquals(1, testChar.soldiersSize());
    }

    @Test
    public void testCampfire() {
        // tests that character deals double dmg at campfire

        // test setup
        CampfireStrategy campfireStrategy = new CampfireStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);
        Building campfire = new Building(pathPos.getX(), pathPos.getY(), campfireStrategy);
        SlugEnemy slug = new SlugEnemy(pathPos);

        // spawns slug
        world.addEnemy(slug);

        // checks that the character's damage multiplier is 1
        assertEquals(1, testChar.getDamageMultiplier());
        world.addBuildingToWorld(campfire);
        world.runBattles();

        // checks that the character is still full health meaning that the campfire

        // increased the character multiplier enough to kill the slug before it attacks
        assertEquals(100, testChar.getHealth());
    }

    @Test
    public void testHerosCastle() {
        // tests that cycle count increments at heroscastle

        // test setup
        HerosCastleStrategy herosCastleStrategy = new HerosCastleStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);

        // check if on cycle 0 initially
        assertEquals(0, testChar.getCycleCount());

        // spawn castle in world and tick
        Building castle = new Building(pathPos.getX(), pathPos.getY(), herosCastleStrategy);
        world.addBuildingToWorld(castle);
        world.runTickMoves();

        // ensure that a cycle has passed since character is at heros castle
        assertEquals(1, testChar.getCycleCount());
    }

    @Test
    public void testTowers() {
        // tests that towers deal 10 damage to enemies in range

        TowerStrategy towerStrategy = new TowerStrategy();

        // setup and load the test world
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();

        // assign the position of the character
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);

        // create a tower
        Building tower = new Building(pathPos.getX(), pathPos.getY(), towerStrategy);
        SlugEnemy slug = new SlugEnemy(pathPos);
        world.addEnemy(slug);

        // check the damage multiplier of the character when near the tower
        assertEquals(1, testChar.getDamageMultiplier());
        world.addBuildingToWorld(tower);
        world.runBattles();

        // check the difference after the tower has been placed and run battle
        assertEquals(100, testChar.getHealth());
    }

    @Test
    public void testTrap() {
        // tests that traps deal 20 dmg to enemies in range

        TrapStrategy trapStrategy = new TrapStrategy();

        // setup and load the test world
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();

        // assign the position of the character
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);

        // create a tower
        Building trap = new Building(pathPos.getX(), pathPos.getY(), trapStrategy);
        SlugEnemy slug = new SlugEnemy(pathPos);
        world.addEnemy(slug);

        // check the damage multiplier of the character when near the trap
        assertEquals(1, testChar.getDamageMultiplier());
        world.addBuildingToWorld(trap);
        world.runBattles();

        // check the difference after the trap has been placed and run battle
        assertEquals(100, testChar.getHealth());
    }

    @Test
    public void testVampireCastle() {
        // tests that vampires spawn at vampire castle at cycle increments of 5

        VampireCastleStrategy vampCastleStrategy = new VampireCastleStrategy();
        HerosCastleStrategy heroCastleStrategy = new HerosCastleStrategy();

        // setup and load the test world
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);
        Building vampCastle = new Building(pathPos.getX(), pathPos.getY(), vampCastleStrategy);

        // add a vampire building to spawn a vampire
        world.addBuildingToWorld(vampCastle);
        Building castle = new Building(pathPos.getX(), pathPos.getY(), heroCastleStrategy);

        // add a castle to count the cycles
        world.addBuildingToWorld(castle);
        world.addBuildingToWorld(vampCastle);
        List<Enemy> enemies = world.possiblySpawnEnemies();
        int vampCount = 0;
        for (Enemy enemy : enemies) {
            if (enemy instanceof VampireEnemy) {
                vampCount++;
            }
        }

        // vampire doesnt exist yet before cycle 5
        assertEquals(0, vampCount);

        // move to cycle 5
        while (testChar.getCycleCount() < 5) {
            testChar.incrementCycleCount();
        }

        enemies = world.possiblySpawnEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof VampireEnemy) {
                vampCount++;
            }
        }
        // after the 5th cycle, vampire are spawned in the world
        assertNotEquals(0, vampCount);
    }

    @Test
    public void testVillage() {
        // tests that the village restores 50 health to the player when in range

        // world setup
        VillageStrategy villageStrategy = new VillageStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);

        // create village
        Building village = new Building(pathPos.getX(), pathPos.getY(), villageStrategy);

        // character takes 80 damage, ensure character is at 20 health
        testChar.reduceHealth(80);
        assertEquals(20, testChar.getHealth());

        // add village to world and run battles
        world.addBuildingToWorld(village);
        world.runBattles();

        // check that village has restored hp to character
        assertEquals(70, testChar.getHealth());
    }

    @Test
    public void testZombiePit() {
        // tests that zombies spawn at the zombie pit after cycle 1

        // test setup
        ZombiePitStrategy zombiePitStrategy = new ZombiePitStrategy();
        HerosCastleStrategy herosCastleStrategy = new HerosCastleStrategy();
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        PathPosition pathPos = new PathPosition(1, world.getOrderedPath());
        Character testChar = new Character(pathPos);
        world.setCharacter(testChar);

        // add heros castle and zombie pit to world
        Building zombiePit = new Building(pathPos.getX(), pathPos.getY(), zombiePitStrategy);
        world.addBuildingToWorld(zombiePit);
        Building castle = new Building(pathPos.getX(), pathPos.getY(), herosCastleStrategy);
        world.addBuildingToWorld(castle);
        world.addBuildingToWorld(zombiePit);

        // spawn enemies and count zombies
        List<Enemy> enemies = world.possiblySpawnEnemies();
        int zombieCount = 0;
        for (Enemy enemy : enemies) {
            if (enemy instanceof ZombieEnemy) {
                zombieCount++;
            }
        }

        // ensure there are no zombies initially
        assertEquals(0, zombieCount);

        // move to cycle 6
        while (testChar.getCycleCount() < 5) {
            testChar.incrementCycleCount();
        }

        // make sure there are zombies in the world
        enemies = world.possiblySpawnEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof ZombieEnemy) {
                zombieCount++;
            }
        }
        assertNotEquals(0, zombieCount);
    }
}
