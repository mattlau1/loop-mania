package unsw.loopmania;

public class CampfireStrategy implements BuildingStrategy {
  private final int range = 10;

  public void useBuilding() {
    // TODO: Character deals double damage within campfire battle radius
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
