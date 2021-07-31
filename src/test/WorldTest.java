package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Cards.Card;
import unsw.loopmania.Cards.VillageCardStrategy;

public class WorldTest {

    @Test
    public void testCardToBuilding() {
        // setup world
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        // get list of buildings before card is used
        List<Building> buildingList = world.getBuildings();
        // create card
        VillageCardStrategy villageCardStrategy = new VillageCardStrategy();

        Card testCard = new Card(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), villageCardStrategy);
        // convert card to building
        world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 1, 1);
        // get list of buildings after card is used
        List<Building> buildingListAfter = world.getBuildings();
        // assert second building list is greater than the first by 1
        assertEquals((buildingList.size() + 1), buildingListAfter.size());

    }

}
