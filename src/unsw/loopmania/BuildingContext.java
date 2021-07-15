package unsw.loopmania;

public class BuildingContext {

  private BuildingStrategy strategy;

  public BuildingContext(BuildingStrategy strategy){
      this.strategy = strategy;
  }

  public void useBuilding() {
      strategy.useBuilding();
  }

}
