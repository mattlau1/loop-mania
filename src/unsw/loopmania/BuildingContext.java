package unsw.loopmania;

public class BuildingContext {

    private BuildingStrategy strategy;

    public BuildingContext(BuildingStrategy strategy) {
        this.strategy = strategy;
    }

    public void useBuilding(Character character) {
        strategy.useBuilding(character);
    }

    public void useBuilding(BasicEnemy enemy) {
        strategy.useBuilding(enemy);
    }

    public int getBuildingRange() {
        return strategy.getBuildingRange();
    }

}
