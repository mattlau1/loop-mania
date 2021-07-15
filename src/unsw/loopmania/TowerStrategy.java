package unsw.loopmania;

public class TowerStrategy implements BuildingStrategy {
  private final int range = 2;

  public void useBuilding() {
    // TODO: During a battle within its shooting radius, enemies will be attacked by the tower
  }

  @Override
  public int getBuildingRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    // TODO Auto-generated method stub

  }

  @Override
  public void useBuilding(BasicEnemy enemy) {
    // TODO Auto-generated method stub

  }
}
