package unsw.loopmania;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.BarracksStrategy;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Buildings.DoggieHouseStrategy;
import unsw.loopmania.Buildings.HerosCastleStrategy;
import unsw.loopmania.Buildings.TowerStrategy;
import unsw.loopmania.Buildings.TrapStrategy;
import unsw.loopmania.Buildings.VampireCastleStrategy;
import unsw.loopmania.Buildings.VillageStrategy;
import unsw.loopmania.Buildings.ZombiePitStrategy;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.GoldGoal;
import unsw.loopmania.Goals.ExperienceGoal;
import unsw.loopmania.Goals.AndComplex;
import unsw.loopmania.Goals.BossGoal;
import unsw.loopmania.Goals.OrComplex;
import unsw.loopmania.Goals.ComplexGoal;
import unsw.loopmania.Goals.CycleGoal;

import java.util.List;

/**
 * Loads a world from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * this class is used to load the world. it loads non-spawning entities from the
 * configuration files. spawning of enemies/cards must be handled by the
 * controller.
 */
public abstract class LoopManiaWorldLoader {
  private JSONObject json;
  private Goal goal;
  private int bossCount = 2;

  public LoopManiaWorldLoader(String filename) throws FileNotFoundException {
    json = new JSONObject(new JSONTokener(new FileReader("worlds/" + filename)));
    goal = new Goal();
  }

  /**
   * Parses the JSON to create a world.
   */
  public LoopManiaWorld load() {
    int width = json.getInt("width");
    int height = json.getInt("height");

    // path variable is collection of coordinates with directions of path taken...
    List<Pair<Integer, Integer>> orderedPath = loadPathTiles(json.getJSONObject("path"), width, height);
    // extract goals from JSON
    JSONObject goalList = json.getJSONObject("goal-condition");
    // load complex goals
    loadComplexGoals(goalList);
    // or load a single simple goal
    loadSimpleGoal(goalList);

    LoopManiaWorld world = new LoopManiaWorld(width, height, orderedPath, goal);
    world.generateItemDrops();
    world.generateCardDrops();

    JSONArray jsonEntities = json.getJSONArray("entities");

    // load non-path entities later so that they're shown on-top
    for (int i = 0; i < jsonEntities.length(); i++) {
      loadEntity(world, jsonEntities.getJSONObject(i), orderedPath);
    }

    // add pretty print
    goal.printComplexGoals();
    goal.printSimpleGoal();

    return world;
  }

  /**
   * Load a simple goal into the game
   *
   * @param goals object of a simple gaol from json
   */
  private void loadSimpleGoal(JSONObject goals) {
    if (goals.getString("goal").equals("experience")) {
      goal.addSimpleGoal(new ExperienceGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("gold")) {
      goal.addSimpleGoal(new GoldGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("cycle")) {
      goal.addSimpleGoal(new CycleGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("bosses")) {
      goal.addSimpleGoal(new BossGoal(goals.getInt("quantity")));
    }
  }

  /**
   * Create a composite bool based on the boolean logic (AND/OR)
   *
   * @param goals object of goals loaded from JSON
   */
  private void loadComplexGoals(JSONObject goals) {
    if (goals.getString("goal").equals("AND")) {
      // COMPLEX GOAL FOUND
      ComplexGoal andComplexGoal = new AndComplex();
      JSONArray g = goals.getJSONArray("subgoals");
      loadComplexGoalsList(g, andComplexGoal);
      goal.addComplexGoal(andComplexGoal);
    } else if (goals.getString("goal").equals("OR")) {
      // COMPLEX GOAL FOUND
      ComplexGoal orComplexGoal = new OrComplex();
      JSONArray g = goals.getJSONArray("subgoals");
      loadComplexGoalsList(g, orComplexGoal);
      goal.addComplexGoal(orComplexGoal);
    }
  }

  /**
   * Add the simple goal as a leaf into the complex goal root
   *
   * @param goals the list of goals from JSON
   * @param root  the complex node root where the simple goals will be added
   */
  private void addComplexGoals(JSONObject goals, ComplexGoal root) {
    if (goals.getString("goal").equals("experience")) {
      root.add(new ExperienceGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("gold")) {
      root.add(new GoldGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("cycles")) {
      root.add(new CycleGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("bosses")) {
      root.add(new BossGoal(goals.getInt("quantity")));
    } else if (goals.getString("goal").equals("AND")) {
      ComplexGoal andComplexGoal = new AndComplex();
      JSONArray g = goals.getJSONArray("subgoals");
      loadComplexGoalsList(g, andComplexGoal);
      root.add(andComplexGoal);
    } else if (goals.getString("goal").equals("OR")) {
      ComplexGoal orComplexGoal = new OrComplex();
      JSONArray g = goals.getJSONArray("subgoals");
      loadComplexGoalsList(g, orComplexGoal);
      root.add(orComplexGoal);
    }
    return;
  }

  /**
   * Load each individual goals from the list and add it into the root node
   *
   * @param goals the list of goals from JSON
   * @param root  the complex node root where the simple goals will be added
   */
  private void loadComplexGoalsList(JSONArray goal, ComplexGoal root) {
    for (int i = 0; i < goal.length(); i++) {
      addComplexGoals(goal.getJSONObject(i), root);
    }
  }

  /**
   * load an entity into the world
   *
   * @param world       backend world object
   * @param json        a JSON object to parse (different from the )
   * @param orderedPath list of pairs of x, y cell coordinates representing game
   *                    path
   */
  private void loadEntity(LoopManiaWorld world, JSONObject currentJson, List<Pair<Integer, Integer>> orderedPath) {
    String type = currentJson.getString("type");
    int x = currentJson.getInt("x");
    int y = currentJson.getInt("y");
    int indexInPath = orderedPath.indexOf(new Pair<Integer, Integer>(x, y));
    assert indexInPath != -1;

    Entity entity = null;
    // TODO = load more entity types from the file
    switch (type) {
      case "hero_castle":
        Character character = new Character(new PathPosition(indexInPath, orderedPath));
        world.setCharacter(character);
        onLoad(character);
        Building heroCastle = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new HerosCastleStrategy());
        onLoad(heroCastle);
        world.addBuildingToWorld(heroCastle);

        entity = character;
        break;
      case "vampire_castle":
        Building vampireCastle = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new VampireCastleStrategy());
        onLoad(vampireCastle);
        world.addBuildingToWorld(vampireCastle);
        break;
      case "tower":
        Building tower = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y), new TowerStrategy());
        onLoad(tower);
        world.addBuildingToWorld(tower);
        break;
      case "zombie_pit":
        Building zombiePit = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new ZombiePitStrategy());
        onLoad(zombiePit);
        world.addBuildingToWorld(zombiePit);
        break;
      case "village":
        Building village = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new VillageStrategy());
        onLoad(village);
        world.addBuildingToWorld(village);
        break;
      case "barracks":
        Building barracks = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new BarracksStrategy());
        onLoad(barracks);
        world.addBuildingToWorld(barracks);
        break;
      case "trap":
        Building trap = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y), new TrapStrategy());
        onLoad(trap);
        world.addBuildingToWorld(trap);
        break;
      case "campfire":
        Building campfire = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new CampfireStrategy());
        onLoad(campfire);
        world.addBuildingToWorld(campfire);
        break;
      case "doggie_house":
        Building doggiehouse = new Building(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y),
            new DoggieHouseStrategy());
        onLoad(doggiehouse);
        world.addBuildingToWorld(doggiehouse);
        break;
      case "path_tile":
        throw new RuntimeException("path_tile's aren't valid entities, define the path externally.");
    }
    world.addEntity(entity);
  }

  /**
   * load path tiles
   *
   * @param path   json data loaded from file containing path information
   * @param width  width in number of cells
   * @param height height in number of cells
   * @return list of x, y cell coordinate pairs representing game path
   */
  private List<Pair<Integer, Integer>> loadPathTiles(JSONObject path, int width, int height) {
    if (!path.getString("type").equals("path_tile")) {
      // ... possible extension
      throw new RuntimeException("Path object requires path_tile type.  No other path types supported at this moment.");
    }
    PathTile starting = new PathTile(new SimpleIntegerProperty(path.getInt("x")),
        new SimpleIntegerProperty(path.getInt("y")));
    if (starting.getY() >= height || starting.getY() < 0 || starting.getX() >= width || starting.getX() < 0) {
      throw new IllegalArgumentException("Starting point of path is out of bounds");
    }
    // load connected path tiles
    List<PathTile.Direction> connections = new ArrayList<>();
    for (Object dir : path.getJSONArray("path").toList()) {
      connections.add(Enum.valueOf(PathTile.Direction.class, dir.toString()));
    }

    if (connections.size() == 0) {
      throw new IllegalArgumentException(
          "This path just consists of a single tile, it needs to consist of multiple to form a loop.");
    }

    // load the first position into the orderedPath
    PathTile.Direction first = connections.get(0);
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    orderedPath.add(Pair.with(starting.getX(), starting.getY()));

    int x = starting.getX() + first.getXOffset();
    int y = starting.getY() + first.getYOffset();

    // add all coordinates of the path into the orderedPath
    for (int i = 1; i < connections.size(); i++) {
      orderedPath.add(Pair.with(x, y));

      if (y >= height || y < 0 || x >= width || x < 0) {
        throw new IllegalArgumentException(
            "Path goes out of bounds at direction index " + (i - 1) + " (" + connections.get(i - 1) + ")");
      }

      PathTile.Direction dir = connections.get(i);
      PathTile tile = new PathTile(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
      x += dir.getXOffset();
      y += dir.getYOffset();
      if (orderedPath.contains(Pair.with(x, y)) && !(x == starting.getX() && y == starting.getY())) {
        throw new IllegalArgumentException("Path crosses itself at direction index " + i + " (" + dir + ")");
      }
      onLoad(tile, connections.get(i - 1), dir);
    }
    // we should connect back to the starting point
    if (x != starting.getX() || y != starting.getY()) {
      throw new IllegalArgumentException(String.format(
          "Path must loop back around on itself, this path doesn't finish where it began, it finishes at %d, %d.", x,
          y));
    }
    onLoad(starting, connections.get(connections.size() - 1), connections.get(0));
    return orderedPath;
  }

  public abstract void onLoad(Character character);

  public abstract void onLoad(Building building);

  public abstract void onLoad(PathTile pathTile, PathTile.Direction into, PathTile.Direction out);

}
