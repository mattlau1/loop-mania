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
    BuildingContext context = new BuildingContext(strategy);
    context.useBuilding(character);
  }

  public void useBuilding(BasicEnemy enemy) {
    BuildingContext context = new BuildingContext(strategy);
    context.useBuilding(enemy);
  }

  public boolean usableOutsideCombat() {
    BuildingContext context = new BuildingContext(strategy);
    return context.usableOutsideCombat();
  }

  public ImageView getImage() {
    BuildingContext context = new BuildingContext(strategy);
    return context.getImage();
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
