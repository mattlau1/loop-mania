package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Enemies.Enemy;

public class Building extends StaticEntity implements BuildingStrategy {
  private BuildingStrategy strategy;

  /**
   * Create a building its respective strategy with the given coordinates
   *
   * @param x                the x value the building will be spawned
   * @param y                the y value the building will be spawned
   * @param buildingStrategy the strategy which will classify the building
   */
  public Building(SimpleIntegerProperty x, SimpleIntegerProperty y, BuildingStrategy buildingStrategy) {
    super(x, y);
    this.strategy = buildingStrategy;
  }

  /**
   * Character uses the building
   *
   * @param character the character uses the building
   */
  public void useBuilding(Character character) {
    strategy.useBuilding(character);
  }

  /**
   * Enemy uses the building
   *
   * @param enemy the enemy uses the building
   */
  public void useBuilding(Enemy enemy) {
    strategy.useBuilding(enemy);
  }

  /**
   * Checks if the building is usable outside combat
   *
   * @return the boolean if the building can be used outside combat
   */
  public boolean usableOutsideCombat() {
    return strategy.usableOutsideCombat();
  }

  /**
   * Loads the image of the respective building
   *
   * @return the image of the respective building
   */
  public ImageView getImage(String imgLoc) {
    return strategy.getImage(imgLoc);
  }

  /**
   * Get the strategy for the building
   *
   * @return the strategy for the building
   */
  public BuildingStrategy getStrategy() {
    return strategy;
  }

  /**
   * Set the strategy for the building
   *
   * @param strategy the new strategy for the building
   */
  public void setStrategy(BuildingStrategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Get the range of the building
   *
   * @return the range of the building
   */
  public int getRange() {
    return strategy.getRange();
  }

  @Override
  public boolean canSpawnEnemy(Character character) {
    return strategy.canSpawnEnemy(character);
  }

  /**
   * The building spawns an enemy
   *
   * @return the newly spawned enemy from the building
   */
  public Enemy spawnEnemy(PathPosition position) {
    return strategy.spawnEnemy(position);
  }

  @Override
  public boolean isSpawnLocation() {
    return strategy.isSpawnLocation();
  }

  @Override
  public boolean canOnlySpawnNextToPath() {
    return strategy.canOnlySpawnNextToPath();
  }

  @Override
  public boolean canOnlySpawnOnPath() {
    return strategy.canOnlySpawnOnPath();
  }

}
