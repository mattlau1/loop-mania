package unsw.loopmania;

public class TowerStrategy implements BuildingStrategy {
  private final int range = 2;
  private final int towerDamage = 10;

  @Override
  public int getBuildingRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    return;
  }

  @Override
  public void useBuilding(BasicEnemy enemy) {
    enemy.reduceHealth(towerDamage);
  }
}