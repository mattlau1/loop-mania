package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.css.Size;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Cards.CampfireCardStrategy;
import unsw.loopmania.Cards.Card;
import unsw.loopmania.Cards.VillageCardStrategy;

public class WorldTest {

    @Test
    public void testCardToBuilding() {
        // check that card to building generates a building and destroys the card
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        // create card
        world.generateCardDrops();
        Card testCard = world.loadCard();
        // convert card to building
        Building testBuilding = world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 1, 1);
        if (testBuilding == null) {
            // building type was not able to be placed on tile
            testBuilding = world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 2, 2);
        }
        assertNotEquals(null, testBuilding);
        // get list of buildings after card is used
        List<Building> buildingList = world.getBuildings();
        // assert second building list is greater than the first by 1
        assertEquals(1, buildingList.size());
        // assert there are no cards left
        assertEquals(0, world.getCards().size());

    }

    @Test
    public void testLoadCard() {
        // Test that load card creates a card in the world
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        // get list of buildings before card is used
        // create card
        world.generateCardDrops();
        world.loadCard();
        List<Card> cardList = world.getCards();
        assertEquals(cardList.size(), 1);

    }

    @Test
    public void testCardNextPath() {
        // test behaviour of cardtobuilding when a card is next to path
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        // create card
        Card testCard = new Card(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0),
                new CampfireCardStrategy());
        world.addCard(testCard);
        // test that isNeighbourPath returns the expected values for 1,1 and 2,2
        assertEquals(true, world.isNeighbourPath(1, 1));
        assertEquals(true, world.isNeighbourPath(2, 2));
        // convert card to building
        Building testBuilding = world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 1, 1);
        if (testBuilding == null) {
            // building type was not able to be placed on tile
            testBuilding = world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 2, 2);
        }
        assertNotEquals(null, testBuilding);
        // get list of buildings after card is used
        List<Building> buildingList = world.getBuildings();
        // assert second building list is greater than the first by 1
        assertEquals(1, buildingList.size());
        // assert there are no cards left
        assertEquals(0, world.getCards().size());
    }

    @Test
    public void testCardOnPath() {
        // test behaviour of cardtobuilding when a card on path
        TestSetup setup = new TestSetup();
        LoopManiaWorld world = setup.makeTestWorld();
        // create card
        Card testCard = new Card(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), new VillageCardStrategy());
        world.addCard(testCard);
        // test that isNeighbourPath returns the expected values for 1,1 and 2,2
        assertEquals(true, world.isNeighbourPath(1, 1));
        assertEquals(true, world.isNeighbourPath(2, 2));
        // convert card to building on path
        Building testBuilding = world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 1, 1);
        assertEquals(null, testBuilding);
        // convert card to building off path
        testBuilding = world.convertCardToBuildingByCoordinates(testCard.getX(), testCard.getY(), 2, 2);
        assertNotEquals(null, testBuilding);
        // get list of buildings after card is used
        List<Building> buildingList = world.getBuildings();
        // assert second building list is greater than the first by 1
        assertEquals(1, buildingList.size());
        // assert there are no cards left
        assertEquals(0, world.getCards().size());
    }
}
