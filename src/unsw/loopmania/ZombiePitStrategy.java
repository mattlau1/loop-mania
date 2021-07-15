package unsw.loopmania;

public class ZombiePitStrategy implements BuildingStrategy {
  private final int range = 2;

  public void useBuilding() {
    // TODO: Produce zombies every cycle of the path completed by the Character, spawning nearby on the path
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

  @Override
  public boolean usableOutsideCombat() {
    return true;
  }

}
