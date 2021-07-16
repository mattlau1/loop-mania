package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;
import unsw.loopmania.StaticEntity;

public class Building extends StaticEntity {
  private BuildingStrategy strategy;
  private int range;

  public Building(SimpleIntegerProperty x, SimpleIntegerProperty y, BuildingStrategy buildingStrategy) {
    super(x, y);
    this.strategy = buildingStrategy;
    this.range = strategy.getBuildingRange();
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

  public ImageView getImage() {
    return strategy.getImage();
  }

  public BuildingStrategy getStrategy() {
    return strategy;
  }

  public void setStrategy(BuildingStrategy strategy) {
    this.strategy = strategy;
  }

  public int getRange() {
    return range;
  }

  public void setRange(int range) {
    this.range = range;
  }

}
