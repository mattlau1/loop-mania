package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Building extends StaticEntity {
  private BuildingStrategy strategy;
  private int range;

  public Building(SimpleIntegerProperty x, SimpleIntegerProperty y, BuildingStrategy strategy) {
    super(x, y);
    this.strategy = strategy;
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