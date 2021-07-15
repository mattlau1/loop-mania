package unsw.loopmania;

public class BarracksStrategy implements BuildingStrategy {
  private final int range = 2;

  public void useBuilding() {
    // TODO: Produces allied soldier to join Character when passes through
  }

  @Override
  public void useBuilding(Character character) {
    // TODO Auto-generated method stub

  }

  @Override
  public void useBuilding(BasicEnemy enemy) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getBuildingRange() {
    return range;
  }
}
