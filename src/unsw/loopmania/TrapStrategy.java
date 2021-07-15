package unsw.loopmania;

public class TrapStrategy implements BuildingStrategy {
  private final int range = 2;

  public void useBuilding() {
    // TODO: When an enemy steps on a trap, the enemy is damaged (and potentially killed if it loses all health) and the trap is destroyed
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
