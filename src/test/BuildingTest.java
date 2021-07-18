package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.BarracksStrategy;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.BuildingStrategy;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Buildings.HerosCastleStrategy;
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
}
