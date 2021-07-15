package unsw.loopmania;

public interface BuildingStrategy {
  /**
   * Activates the building for a character
   *
   * @param character character to activate building on
   */
  public void useBuilding(Character character);

  /**
   * Activates the building for an enemy
   *
   * @param enemy enemy to activate building on
   */
  public void useBuilding(BasicEnemy enemy);

  /**
   * Gets the range of the building
   *
   * @return building range
   */
  public int getBuildingRange();

  public boolean usableOutsideCombat();
}
