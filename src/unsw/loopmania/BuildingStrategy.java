package unsw.loopmania;

public interface BuildingStrategy {
  public void useBuilding(Character character);
  public void useBuilding(BasicEnemy enemy);
  public int getBuildingRange();
}
