package unsw.loopmania;

public class CampfireStrategy implements BuildingStrategy {
  private final int range = 10;

  @Override
  public int getBuildingRange() {
    return range;
  }

  @Override
  public void useBuilding(Character character) {
    character.setDamageMultiplier(2);
  }

  @Override
  public void useBuilding(BasicEnemy enemy) {
    // enemies cannot use campfires so do nothing
    return;
  }

}
