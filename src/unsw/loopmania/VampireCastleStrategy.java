package unsw.loopmania;

/**
 * a basic form of building in the world
 */
public class VampireCastleStrategy implements BuildingStrategy {
  private final int range = 2;

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
