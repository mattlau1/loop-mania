package unsw.loopmania.Buildings;

import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;

public class BuildingContext {

    private BuildingStrategy strategy;

    public BuildingContext(BuildingStrategy strategy) {
        this.strategy = strategy;
    }

    public void useBuilding(Character character) {
        strategy.useBuilding(character);
    }

    public void useBuilding(BasicEnemy enemy) {
        strategy.useBuilding(enemy);
    }

    public boolean usableOutsideCombat() {
        return strategy.usableOutsideCombat();
    }

    public int getBuildingRange() {
        return strategy.getBuildingRange();
    }

    public ImageView getImage() {
        return strategy.getImage();
    }

}
