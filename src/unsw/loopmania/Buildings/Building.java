package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Enemies.Enemy;

public class Building extends StaticEntity implements BuildingStrategy {
  private BuildingStrategy strategy;

  public Building(SimpleIntegerProperty x, SimpleIntegerProperty y, BuildingStrategy buildingStrategy) {
    super(x, y);
    this.strategy = buildingStrategy;
  }

  public void useBuilding(Character character) {
    strategy.useBuilding(character);
  }

  public void useBuilding(Enemy enemy) {
    strategy.useBuilding(enemy);
  }

  public boolean usableOutsideCombat() {
    return strategy.usableOutsideCombat();
  }

  public ImageView getImage() {
    return strategy.getImage();
  }

  public BuildingStrategy getStrategy() {
    return strategy;
  }

  public void setStrategy(BuildingStrategy strategy) {
    this.strategy = strategy;
  }

  public int getRange() {
    return strategy.getRange();
  }

  @Override
  public boolean canSpawnEnemy(int currentCycle) {
    return strategy.canSpawnEnemy(currentCycle);
  }

  public Enemy spawnEnemy(PathPosition position) {
    return strategy.spawnEnemy(position);
  }

  @Override
  public boolean isSpawnLocation() {
    return strategy.isSpawnLocation();
  }

}
