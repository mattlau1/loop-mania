package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Building extends StaticEntity {
  private BuildingStrategy strategy;

  public Building(SimpleIntegerProperty x, SimpleIntegerProperty y, BuildingStrategy strategy) {
    super(x, y);
    this.strategy = strategy;
  }

  public void useBuilding() {
    BuildingContext context = new BuildingContext(strategy);
    context.useBuilding();
  }

}
