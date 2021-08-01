package unsw.loopmania;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import unsw.loopmania.Items.AndurilStrategy;
import unsw.loopmania.Items.ArmourStrategy;
import unsw.loopmania.Items.DoggieCoinStrategy;
import unsw.loopmania.Items.GoldStrategy;
import unsw.loopmania.Items.HealthPotionStrategy;
import unsw.loopmania.Items.HelmetStrategy;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.ItemStrategy;
import unsw.loopmania.Items.ShieldStrategy;
import unsw.loopmania.Items.StaffStrategy;
import unsw.loopmania.Items.StakeStrategy;
import unsw.loopmania.Items.SwordStrategy;
import unsw.loopmania.Items.TheOneRingStrategy;
import unsw.loopmania.Items.TreeStumpStrategy;
import unsw.loopmania.Buffs.Buff;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.CampfireStrategy;
import unsw.loopmania.Buildings.ElanHouseStrategy;
import unsw.loopmania.Buildings.TrapStrategy;
import unsw.loopmania.Cards.BarracksCardStrategy;
import unsw.loopmania.Cards.CampfireCardStrategy;
import unsw.loopmania.Cards.Card;
import unsw.loopmania.Cards.CardStrategy;
import unsw.loopmania.Cards.TowerCardStrategy;
import unsw.loopmania.Cards.TrapCardStrategy;
import unsw.loopmania.Cards.VampireCastleCardStrategy;
import unsw.loopmania.Cards.VillageCardStrategy;
import unsw.loopmania.Cards.ZombiePitCardStrategy;
import unsw.loopmania.Enemies.DoggieEnemy;
import unsw.loopmania.Enemies.ElanEnemy;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Goals.BossObserver;
import unsw.loopmania.Goals.CycleObserver;
import unsw.loopmania.Goals.EXPObserver;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.GoldObserver;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one entity
 * can occupy the same square.
 */
public class LoopManiaWorld {
  public static final int unequippedInventoryWidth = 4;
  public static final int unequippedInventoryHeight = 4;

  /**
   * width of the world in GridPane cells
   */
  private int width;

  /**
   * height of the world in GridPane cells
   */
  private int height;

  /**
   * generic entitites - i.e. those which don't have dedicated fields
   */
  private List<Entity> nonSpecifiedEntities;

  // character for the world
  private Character character;

  // list of equipped items
  private List<Item> equippedInventoryItems;

  // items sorted by rarity
  private List<ItemStrategy> commonItems;
  private List<ItemStrategy> lowRarityItems;
  private List<ItemStrategy> midRarityItems;
  private List<ItemStrategy> highRarityItems;
  private List<ItemStrategy> superRarityItems;

  // cards sorted by rarity
  private List<CardStrategy> lowRarityCards;
  private List<CardStrategy> midRarityCards;
  private List<CardStrategy> highRarityCards;

  // drop for destroyed cards
  private final int destroyedCardGold = 100;
  private final int destroyedCardExp = 100;

  // list of enemies
  private List<Enemy> enemies;

  // list of items on on the path
  private List<Item> pathItems;

  // list of zombie soldiers
  private List<Enemy> zombieSoldiers;

  // list of tranced soldiers
  private List<Soldier> trancedSoldiers;

  // list of cards
  private List<Card> cardEntities;

  // cycle counter for shop opening
  private int heroCastleCycles;

  // next cycle that shop will open on
  private int nextHeroCastleCycle;

  // list of unequipped inventory
  private List<Item> unequippedInventoryItems;

  // list of buildings
  private List<Building> buildingEntities;

  // boolean for checking if card was destroyed
  private boolean cardDestroyed;

  private boolean isElanAlive;
  private boolean isElanDead;
  private final double postElanPriceMultiplier;
  private final int midElanPriceMultiplier;
  /**
   * list of x,y coordinate pairs in the order by which moving entities traverse
   * them
   */
  private List<Pair<Integer, Integer>> orderedPath;

  // the goal which contains simple goals
  private Goal goal;

  private String difficulty;
  public static final String SURVIVAL_MODE = "Survival";
  public static final String BERSERKER_MODE = "Berserker";
  public static final String STANDARD_MODE = "Standard";
  public static final String CONFUSING_MODE = "Confusing";

  // music
  MediaPlayer swingSound;
  MediaPlayer equippedSound;

  /**
   * create the world (constructor)
   *
   * @param width       width of world in number of cells
   * @param height      height of world in number of cells
   * @param orderedPath ordered list of x, y coordinate pairs representing
   *                    position of path cells in world
   * @param goal        the goal contains multiple simple goals
   */
  public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, Goal goal) {
    this.width = width;
    this.height = height;
    this.nonSpecifiedEntities = new ArrayList<>();
    this.character = null;
    this.enemies = new ArrayList<>();
    this.zombieSoldiers = new ArrayList<>();
    this.cardEntities = new ArrayList<>();
    this.unequippedInventoryItems = new ArrayList<>();
    this.equippedInventoryItems = new ArrayList<>();
    this.commonItems = new ArrayList<>();
    this.lowRarityItems = new ArrayList<>();
    this.midRarityItems = new ArrayList<>();
    this.highRarityItems = new ArrayList<>();
    this.superRarityItems = new ArrayList<>();
    this.lowRarityCards = new ArrayList<>();
    this.midRarityCards = new ArrayList<>();
    this.highRarityCards = new ArrayList<>();
    this.orderedPath = orderedPath;
    this.buildingEntities = new ArrayList<>();
    this.trancedSoldiers = new ArrayList<>();
    this.goal = goal;
    this.cardDestroyed = false;
    this.isElanAlive = false;
    this.isElanDead = false;
    this.pathItems = new ArrayList<>();
    this.heroCastleCycles = 1;
    this.nextHeroCastleCycle = 1;
    this.postElanPriceMultiplier = 0.8;
    this.midElanPriceMultiplier = 5;
    this.difficulty = BERSERKER_MODE;
  }

  public boolean difficultyEquals(String difficulty) {
    System.out.println(difficulty);
    return this.difficulty.equals(difficulty);
  }

  public String getDifficulty() {
    return this.difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public int getHeroCastleCycles() {
    return heroCastleCycles;
  }

  public void addHeroCastleCycles(int num) {
    heroCastleCycles += num;
  }

  public int getNextHeroCastleCycle() {
    return nextHeroCastleCycle;
  }

  public void addNextHeroCastleCycle(int num) {
    nextHeroCastleCycle += num;
  }

  /**
   * generate random item drop
   */
  public void generateItemDrops() {
    commonItems.clear();
    lowRarityItems.clear();
    midRarityItems.clear();
    superRarityItems.clear();

    commonItems.add(new GoldStrategy());
    commonItems.add(new HealthPotionStrategy());

    lowRarityItems.add(new SwordStrategy());
    lowRarityItems.add(new StakeStrategy());

    midRarityItems.add(new ArmourStrategy());
    midRarityItems.add(new ShieldStrategy());
    midRarityItems.add(new HelmetStrategy());

    highRarityItems.add(new StaffStrategy());
    highRarityItems.add(new HealthPotionStrategy());

    superRarityItems.add(new TheOneRingStrategy());
    superRarityItems.add(new AndurilStrategy());
    superRarityItems.add(new TreeStumpStrategy());
  }

  /**
   * generate random card drop
   */
  public void generateCardDrops() {
    lowRarityCards.clear();
    midRarityCards.clear();
    highRarityCards.clear();

    lowRarityCards.add(new ZombiePitCardStrategy());
    lowRarityCards.add(new TrapCardStrategy());
    lowRarityCards.add(new VampireCastleCardStrategy());

    midRarityCards.add(new VillageCardStrategy());
    midRarityCards.add(new CampfireCardStrategy());

    highRarityCards.add(new TowerCardStrategy());
    highRarityCards.add(new BarracksCardStrategy());
  }

  /**
   * get the grid width of the world
   */
  public int getWidth() {
    return width;
  }

  /**
   * get the grid height of the world
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the character. This is necessary because it is loaded as a special
   * entity out of the file
   *
   * @param character the character
   */
  public void setCharacter(Character character) {
    this.character = character;
    addCharacterObservers();
  }

  /**
   * Adds EXP, Gold, Cycle and Boss Count observers to character
   */
  public void addCharacterObservers() {
    character.addObservers(new EXPObserver(character, goal));
    character.addObservers(new GoldObserver(character, goal));
    character.addObservers(new CycleObserver(character, goal));
    character.addObservers(new BossObserver(character, goal));
  }

  /**
   * Gets the character of the world
   *
   * @return character
   */
  public Character getCharacter() {
    return character;
  }

  /**
   * Gets the tranced soliders of the world
   *
   * @return tranced soliders
   */
  public List<Soldier> getTrancedSoldiers() {
    return trancedSoldiers;
  }

  /**
   * Gets the number of tranced soliders of the world
   *
   * @return the number of tranced soliders
   */
  public int trancedSoldiersSize() {
    return this.trancedSoldiers.size();
  }

  /**
   * add a generic entity (without it's own dedicated method for adding to the
   * world)
   *
   * @param entity
   */
  public void addEntity(Entity entity) {
    // for adding non-specific entities (ones without another dedicated list)
    nonSpecifiedEntities.add(entity);
  }

  /**
   * Character consumes the potion to restore health
   */
  public void consumePotion() {
    character.setHealth(character.getMaxHealth());
  }

  public void spawnEnemy(Enemy enemy, List<Enemy> spawningEnemies) {
    enemies.add(enemy);
    spawningEnemies.add(enemy);
  }

  /**
   * spawns enemies if the conditions warrant it, adds to world
   *
   * @return list of the enemies to be displayed on screen
   */

  public List<Enemy> possiblySpawnEnemies() {
    List<Enemy> spawningEnemies = new ArrayList<>();
    Pair<Integer, Integer> randomPos = possiblyGetBasicEnemySpawnPosition();
    int indexInPath = orderedPath.indexOf(randomPos);

    // spawn randomly spawning enemies
    if (randomPos != null) {
      // spawns a slug
      Enemy slug = new SlugEnemy(new PathPosition(indexInPath, orderedPath));
      enemies.add(slug);
      spawningEnemies.add(slug);
    }

    // go through every building in the world
    // if the building can spawn enemies, check cycle count
    // and spawn an enemy on the closest path tile to that building
    for (Building building : buildingEntities) {
      if (isAtHerosCastle()) {
        if (building.canSpawnEnemy(character)) {
          Pair<Integer, Integer> spawnLocation = neighbourPath(building.getX(), building.getY());
          int buildingIndexInPath = orderedPath.indexOf(spawnLocation);
          Enemy enemy = building.spawnEnemy(new PathPosition(buildingIndexInPath, orderedPath));

          if (enemy != null) {
            enemies.add(enemy);
            spawningEnemies.add(enemy);
          }
        }
      }
    }
    return spawningEnemies;
  }

  /**
   * returns the adjacent path or grid with the given coordinates
   *
   * @param x the x coordinate of the path
   * @param y the y coordinate of the path
   * @return the coordinates of the adjacent path
   */
  public Pair<Integer, Integer> neighbourPath(int x, int y) {
    for (Pair<Integer, Integer> path : orderedPath) {
      if ((x + 1) == path.getValue0() && (y) == path.getValue1())
        return new Pair<Integer, Integer>(x + 1, y);
      if ((x - 1) == path.getValue0() && (y) == path.getValue1())
        return new Pair<Integer, Integer>(x - 1, y);
      if ((x) == path.getValue0() && (y + 1) == path.getValue1())
        return new Pair<Integer, Integer>(x, y + 1);
      if ((x) == path.getValue0() && (y - 1) == path.getValue1())
        return new Pair<Integer, Integer>(x, y - 1);
    }
    return null;
  }

  /**
   * Returns the array of items on the floor
   *
   * @return the list of items on the floor
   */
  public List<Item> getPathItems() {
    return pathItems;
  }

  public void addPathItems(Item item) {
    pathItems.add(item);
  }

  /**
   * spawns items if the conditions warrant it, adds to world
   *
   * @return list of the enemies to be displayed on screen
   */
  public List<Item> possiblySpawnItems() {

    Pair<Integer, Integer> pos = possiblyGetBasicItemSpawnPosition();
    List<Item> spawningItems = new ArrayList<>();
    if (pos != null) {
      int indexInPath = orderedPath.indexOf(pos);
      PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
      // spawns a slug
      // Enemy slug = new SlugEnemy(new PathPosition(indexInPath, orderedPath));
      Random random = new Random();
      int randInt = random.nextInt(2);
      Item potion = new Item(pathPos.getX(), pathPos.getY(), commonItems.get(randInt));
      pathItems.add(potion);
      spawningItems.add(potion);
    }
    return spawningItems;
  }

  /**
   * kill an enemy
   *
   * @param enemy enemy to be killed
   */
  private void killEnemy(Enemy enemy) {
    swingSound();
    enemy.destroy();
    enemies.remove(enemy);
  }

  /**
   * remove an item
   *
   * @param item item to be removed
   */
  private void killItem(Item item) {
    item.destroy();
    pathItems.remove(item);
  }

  /**
   * check if the character and enemy are in range of each other
   *
   * @return the boolean when the character and enemy in range
   */
  private boolean isInRange(Enemy e, Character c) {
    return Math.pow((c.getX() - e.getX()), 2) + Math.pow((c.getY() - e.getY()), 2) < e.getBattleRange();
  }

  private boolean isInRange(Enemy e1, Enemy e2) {
    return Math.pow((e2.getX() - e1.getX()), 2) + Math.pow((e2.getY() - e1.getY()), 2) < e1.getBattleRange();
  }

  private boolean isInSuppRange(Enemy e, Character c) {
    return Math.pow((c.getX() - e.getX()), 2) + Math.pow((c.getY() - e.getY()), 2) < e.getSupportRange();
  }

  private boolean isInRange(Building b, Character c) {
    return Math.pow((c.getX() - b.getX()), 2) + Math.pow((c.getY() - b.getY()), 2) < b.getRange();
  }

  private boolean isInRange(Item i, Character c) {
    return Math.pow((c.getX() - i.getX()), 2) + Math.pow((c.getY() - i.getY()), 2) < i.getRange();
  }

  private boolean isInRange(Building b, Enemy e) {
    return Math.pow((e.getX() - b.getX()), 2) + Math.pow((e.getY() - b.getY()), 2) < b.getRange();
  }

  /**
   * Checks if game is lost
   *
   * @return true if game is lost else false
   */
  public boolean isGameLost() {
    // if character hp is at 0
    if (character.isDead()) {
      // check if user has one ring and add to usedItems list
      List<Item> usedItems = new ArrayList<>();
      for (Item item : unequippedInventoryItems) {
        if (item.isDestroyedOnUse()) {
          item.useItem(character);
          usedItems.add(item);
        }
      }

      // check if user had The One Ring
      if (usedItems.isEmpty()) {
        // character does not have one ring, game is lost
        return true;
      } else {
        // character has the one ring, remove from inventory
        for (Item item : usedItems) {
          removeUnequippedInventoryItem(item);
          break;
        }
      }
    }
    return false;
  }

  /**
   * Use the item on the character outside the combat
   */
  private void useItemsOnCharacterOutsideCombat() {
    List<Item> pathItemsToDestroy = new ArrayList<>();
    for (Item i : pathItems) {
      if (isInRange(i, character)) {
        i.useItem(character);
        pathItemsToDestroy.add(i);
      }
    }
    for (Item item : pathItemsToDestroy) {
      killItem(item);
    }
  }

  /**
   * Changes vampire direction if they are within range of a campfire
   *
   * @param vampire  vampire whose direction to change
   * @param building building to check if campfire
   */
  private void changeVampireDirection(VampireEnemy vampire, Building building) {
    // vampire has special interaction with the campfire
    if (isInRange(building, vampire) && (building.getStrategy() instanceof CampfireStrategy)) {
      vampire.changeDirection();
    } else {
      vampire.resetHasChangedDirection();
    }
  }

  /**
   * Uses all buildings that are in range of the character
   */
  private void useBuildingsOnCharacterOutsideCombat() {
    for (Building b : buildingEntities) {
      if (isInRange(b, character) && b.usableOutsideCombat() && !b.isSpawnLocation()) {
        b.useBuilding(character);
      }
    }
  }

  /**
   * Uses buildings on enemies that are outside of combat, if in range
   *
   * @param buildingsToDestroy list of buildings that will be destroyed after
   *                           enemy usage
   * @param defeatedEnemies    list of enemies that will be defeated after using
   *                           building
   */
  private void useBuildingsOnEnemiesOutsideCombat(List<Building> buildingsToDestroy, List<Enemy> defeatedEnemies) {
    for (Building b : buildingEntities) {
      for (Enemy e : enemies) {
        if (e instanceof VampireEnemy)
          changeVampireDirection((VampireEnemy) e, b);
        if (isInRange(b, e) && b.usableOutsideCombat()) {
          b.useBuilding(e);
          if (e.isDead())
            defeatedEnemies.add(e);

          // need to destroy traps
          if (b.getStrategy() instanceof TrapStrategy) {
            buildingsToDestroy.add(b);
          }
        }
      }
    }
  }

  /**
   * Uses buildings on entities (character and enemy) that are in combat, if in
   * range
   *
   * @param enemy enemy to use building on during combat
   */
  private void useBuildingsOnEntitiesInCombat(Enemy enemy) {
    for (Building building : buildingEntities) {
      if (isInRange(building, character)) {
        building.useBuilding(character);
        building.useBuilding(enemy);
      }
    }
  }

  /**
   * Removes buildings from the world, given a list of buildings to destroy
   *
   * @param buildingsToDestroy list of buildings to destroy
   */
  private void destroyBuildings(List<Building> buildingsToDestroy) {
    for (Building buildingToDestroy : buildingsToDestroy) {
      buildingToDestroy.destroy();
      int buildingIndexInBuildingsList = buildingEntities.indexOf(buildingToDestroy);
      if (buildingIndexInBuildingsList != -1) {
        buildingEntities.remove(buildingIndexInBuildingsList);
      }
    }
  }

  /**
   * Gets all enemies that are battling the character by checking if character is
   * in range of enemy.
   *
   * @return list of all enemies in battle with the character
   */
  private List<Enemy> getBattlingEnemies() {
    List<Enemy> battlingEnemies = new ArrayList<Enemy>();
    for (Enemy enemy : enemies) {
      if (isInRange(enemy, character)) {
        battlingEnemies.add(enemy);
        for (Enemy support : enemies) {
          if (support != enemy && isInSuppRange(support, character)) {
            battlingEnemies.add(support);
          }
        }
      }
    }
    return battlingEnemies;
  }

  /**
   * Gets the damage that the character will deal against a given enemy
   *
   * @param enemy enemy that the character will deal damage against
   * @return damage amount against the enemy
   */
  private double getCharacterDamageAgainstEnemy(Enemy enemy) {
    double characterDamage = character.getMultipliedDamage();
    for (Item equippedItems : equippedInventoryItems) {
      characterDamage *= equippedItems.getAtkMultiplier(enemy);
    }
    return characterDamage;
  }

  /**
   * Randomly trigger on-hit effects for equipped items
   *
   * @param enemy enemy to trigger on-hit effect on
   */
  private void triggerOnHitEffects(Enemy enemy) {
    Random random = new Random();
    int randInt = random.nextInt(2);
    if (randInt == 1) {
      for (Item equippedItems : equippedInventoryItems) {
        equippedItems.onHitEffects(enemy, trancedSoldiers);
      }
    }
  }

  /**
   * Checks if enemy will critically strike
   *
   * @param enemy enemy that will potentially critically strike
   * @return true if enemy will critically strike else false
   */
  private boolean doesEnemyCrit(Enemy enemy) {
    boolean isCriticalHit = false;
    Random random = new Random();
    int randInt = random.nextInt(100) + 1;

    double enemyCriR = enemy.getCritRate();
    for (Item equippedItems : equippedInventoryItems) {
      enemyCriR *= equippedItems.getCritMultiplier(enemy);
    }

    if (randInt <= enemyCriR)
      isCriticalHit = true;

    return isCriticalHit;
  }

  /**
   * Calculates enemy's damage after applying character's equipped defensive items
   *
   * @param enemyDamageBeforeDef enemy's damage before taking any defensive items
   *                             into account
   * @param enemy                enemy to take damage from
   * @return new enemy damage after taking defensive items into account
   */
  private double getEnemyDamageAfterDefense(double enemyDamageBeforeDef, Enemy enemy) {
    double enemyDamageAfterDef = enemyDamageBeforeDef;
    for (Item equippedItems : equippedInventoryItems) {
      enemyDamageAfterDef *= equippedItems.getDefMultiplier(enemy);
    }
    return enemyDamageAfterDef;
  }

  /**
   * Causes the given soldier to take damage from an enemy
   *
   * @param soldier       soldier that will take damage
   * @param isCriticalHit true if soldier is taking a critical hit else false
   * @param enemy         enemy dealing the damage to soldier
   * @param enemyDamage   damage that soldier will take after defense
   */
  private void damageSoldier(Soldier soldier, boolean isCriticalHit, Enemy enemy, double enemyDamage) {
    if (isCriticalHit && !soldier.getBuffs().contains(enemy.criticalHit())) {
      soldier.addBuffs(enemy.criticalHit());
    }

    soldier.reduceHealth(enemyDamage);

    for (Buff buff : soldier.getBuffs()) {
      buff.activateEffect(soldier, enemy, character.getSoldiers(), zombieSoldiers);
    }
  }

  /**
   * Every enemy in the battle attacks any soldiers, if bo soldiers, then enemy
   * will attack the character
   *
   * @param battlingEnemies all battling enemies
   * @param enemy           enemy that is attacking soldier
   */
  private void attackSoldiers(List<Enemy> battlingEnemies, Enemy enemy) {
    double enemyDamage = enemy.getDamage();
    for (Enemy currBattlingEnemy : battlingEnemies) {
      if (currBattlingEnemy.isDead())
        continue;

      boolean isCriticalHit = doesEnemyCrit(currBattlingEnemy);
      enemyDamage = getEnemyDamageAfterDefense(enemyDamage, currBattlingEnemy);

      if (trancedSoldiers.size() > 0) {
        // tranced soldiers
        Soldier s = trancedSoldiers.get(0);
        damageSoldier(s, isCriticalHit, enemy, enemyDamage);

        // remove tranced soldier if dead
        if (s.isDead())
          trancedSoldiers.remove(0);

      } else if (character.soldiersSize() > 0) {
        // normal soldiers
        Soldier s = character.getSoldiersFromIndex(0);
        damageSoldier(s, isCriticalHit, enemy, enemyDamage);

        // remove soldier if dead
        if (s.isDead())
          character.removeSoldiersFromIndex(0);

      } else {
        // if no soldiers or tranced soldiers left, character takes the damage

        // apply buffs
        if (isCriticalHit && !character.getBuffs().contains(currBattlingEnemy.criticalHit())) {
          character.addBuffs(currBattlingEnemy.criticalHit());
        }

        // // character takes damage
        // character.reduceHealth(enemyDamage);

        // activate buffs
        for (Buff buff : character.getBuffs()) {
          buff.activateEffect(character, currBattlingEnemy, character.getSoldiers(), zombieSoldiers);
        }

        // character takes damage
        character.reduceHealth(enemyDamage);
      }
    }
  }

  /**
   * Processes zombie soldier attacks against the character
   *
   * @param enemy enemy to process
   */
  private void processZombieSoldierAttacks(Enemy enemy) {
    double enemyDamage = enemy.getDamage();
    for (Enemy currBattlingEnemy : zombieSoldiers) {
      if (currBattlingEnemy.isDead())
        continue;

      enemyDamage = getEnemyDamageAfterDefense(enemyDamage, currBattlingEnemy);

      if (trancedSoldiers.size() > 0) {
        Soldier s = trancedSoldiers.get(0);
        s.reduceHealth(enemyDamage);
        if (s.isDead())
          character.removeSoldiersFromIndex(0);
      } else if (character.soldiersSize() > 0) {
        Soldier s = character.getSoldiersFromIndex(0);
        s.reduceHealth(enemyDamage);
        if (s.isDead())
          character.removeSoldiersFromIndex(0);
      } else {
        character.reduceHealth(enemyDamage);
      }
    }
  }

  /**
   * Possibly randomly stuns the character
   */
  public void possiblyStunCharacter() {
    Random random = new Random();
    int randInt = random.nextInt(100);
    int stunChance = 50;

    if (randInt <= stunChance)
      character.setStunned(true);
  }

  /**
   * Heals all enemies in Elan's range
   */
  public void healEnemiesAroundElan() {
    for (Enemy e1 : enemies) {
      if (e1 instanceof ElanEnemy) {
        isElanAlive = true;
        for (Enemy e2 : enemies) {
          if (isInRange(e1, e2) && !(e2 instanceof ElanEnemy)) {
            e2.addHealth(ElanEnemy.HEAL_AMOUNT);
          }
        }
      }
    }
  }

  /**
   * run the expected battles in the world, based on current world state
   *
   * @return list of enemies which have been killed
   */
  public List<Enemy> runBattles() {
    List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
    List<Building> buildingsToDestroy = new ArrayList<>();
    List<Enemy> battlingEnemies = getBattlingEnemies();

    if (isGameLost()) {
      System.exit(0);
    }

    // we have three types of buildings:
    // character outside of combat (i.e village)
    // enemies outside of combat (i.e trap)
    // character and enemies inside of combat (i.e tower, campfire)
    useBuildingsOnCharacterOutsideCombat();
    useBuildingsOnEnemiesOutsideCombat(buildingsToDestroy, defeatedEnemies);
    destroyBuildings(buildingsToDestroy);
    useItemsOnCharacterOutsideCombat();
    healEnemiesAroundElan();

    for (Enemy enemy : battlingEnemies) {
      while (enemy.isAlive()) {

        useBuildingsOnEntitiesInCombat(enemy);
        triggerOnHitEffects(enemy);

        // if enemy is able to stun, randomly stun character if unlucky
        if (enemy.canStunCharacter())
          possiblyStunCharacter();

        // attack enemy is character is not stunned
        if (!character.isStunned())
          enemy.reduceHealth(getCharacterDamageAgainstEnemy(enemy));

        attackSoldiers(battlingEnemies, enemy);
        processZombieSoldierAttacks(enemy);

        // unstun character after the attack has ended
        character.setStunned(false);
      }

      defeatedEnemies.add(enemy);
      character.addEXP(enemy.getExpDrop());
      character.addGold(enemy.getGoldDrop());
      character.addScrapMetal(1);
      character.addDoggieCoins(enemy.getDoggieCoinDrop());
    }

    trancedSoldiers.clear();
    for (Enemy e : defeatedEnemies) {
      if (e instanceof ElanEnemy) {
        isElanAlive = false;
        isElanDead = true;
      }

      if (e instanceof DoggieEnemy || e instanceof ElanEnemy)
        character.incrementBossKillCount();

      killEnemy(e);
    }

    return defeatedEnemies;
  }

  /**
   * spawn a card in the world and return the card entity
   *
   * @return a card to be spawned in the controller as a JavaFX node
   */
  public Card loadCard() {
    // if adding more cards than have, remove the first card...
    cardDestroyed = false;
    if (cardEntities.size() >= getWidth()) {
      cardDestroyed = true;
      removeCard(0);
    }

    Card card = new Card(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0),
        randomCardStrategy());
    cardEntities.add(card);
    return card;
  }

  public void destroyCard() {
    character.addEXP(destroyedCardExp);
    character.addGold(destroyedCardGold);
  }

  public boolean getCardDestroyed() {
    return cardDestroyed;
  }

  public List<Card> getCards() {
    return cardEntities;
  }

  public void addEnemy(Enemy enemy) {
    enemies.add(enemy);
  }

  /**
   * remove card at a particular index of cards (position in gridpane of unplayed
   * cards)
   *
   * @param index the index of the card, from 0 to length-1
   */
  private void removeCard(int index) {
    Card c = cardEntities.get(index);
    int x = c.getX();
    c.destroy();
    cardEntities.remove(index);
    shiftCardsDownFromXCoordinate(x);
  }

  /**
   * spawn a sword in the world and return the sword entity
   *
   * @return a sword to be spawned in the controller as a JavaFX node
   */
  public Item addUnequippedItem() {
    ItemStrategy randStrat = randomItemStrategy();
    while (randStrat instanceof HealthPotionStrategy) {
      consumePotion();
      randStrat = randomItemStrategy();
    }
    Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
    if (firstAvailableSlot == null) {
      // eject the oldest unequipped item and replace it... oldest item is that at
      // beginning of items
      character.addEXP(destroyedCardExp);
      character.addGold(destroyedCardGold);
      removeItemByPositionInUnequippedInventoryItems(0);
      firstAvailableSlot = getFirstAvailableSlotForItem();
    }

    // now we insert the new sword, as we know we have at least made a slot
    // available...

    Item item = new Item(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
        new SimpleIntegerProperty(firstAvailableSlot.getValue1()), randStrat);
    unequippedInventoryItems.add(item);
    return item;
  }

  /**
   * spawn a specific item in the world and return the sword entity
   *
   * @param item strategy of the item to be spawned
   * @return an item to be spawned in the controller as a JavaFX node
   */
  public Item addSpecificUnequippedItem(ItemStrategy itemStrategy) {
    Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
    if (firstAvailableSlot == null) {
      // eject the oldest unequipped item and replace it... oldest item is that at
      // beginning of items
      character.addEXP(destroyedCardExp);
      character.addGold(destroyedCardGold);
      removeItemByPositionInUnequippedInventoryItems(0);
      firstAvailableSlot = getFirstAvailableSlotForItem();
    }

    // now we insert the new sword, as we know we have at least made a slot
    // available...

    Item item = new Item(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
        new SimpleIntegerProperty(firstAvailableSlot.getValue1()), itemStrategy);
    unequippedInventoryItems.add(item);
    return item;
  }

  /**
   * chooses a random item strategy from a list
   *
   * @return the item strategy of the item to be spawned
   */
  public ItemStrategy randomItemStrategy() {
    Random random = new Random();
    int randInt = random.nextInt(100);
    int superRarityDropRate = 1;
    int highRarityDropRate = 6;
    int mediumRarityDropRate = 29;

    if (randInt <= superRarityDropRate) {
      // super rarity items
      randInt = random.nextInt(superRarityItems.size());
      return superRarityItems.get(randInt);
    } else if (randInt <= highRarityDropRate) {
      // high rarity items
      randInt = random.nextInt(highRarityItems.size());
      return highRarityItems.get(randInt);
    } else if (randInt <= mediumRarityDropRate) {
      // medium rarity items
      randInt = random.nextInt(midRarityItems.size());
      return midRarityItems.get(randInt);
    } else {
      // low rarity items
      randInt = random.nextInt(lowRarityItems.size());
      return lowRarityItems.get(randInt);
    }
  }

  /**
   * Gets the world path in order
   *
   * @return the world path in order
   */
  public List<Pair<Integer, Integer>> getOrderedPath() {
    return orderedPath;
  }

  /**
   * chooses a random card strategy from a list
   *
   * @return the card strategy of the card to be spawned
   */
  public CardStrategy randomCardStrategy() {
    Random random = new Random();
    int randInt = random.nextInt(100);
    int highRarityDropRate = 6;
    int mediumRarityDropRate = 29;

    if (randInt <= highRarityDropRate) {
      // high rarity cards
      randInt = random.nextInt(highRarityCards.size());
      return highRarityCards.get(randInt);
    } else if (randInt <= mediumRarityDropRate) {
      // medium rarity cards
      randInt = random.nextInt(midRarityCards.size());
      return midRarityCards.get(randInt);
    } else {
      // low rarity cards
      randInt = random.nextInt(lowRarityCards.size());
      return lowRarityCards.get(randInt);
    }
  }

  /**
   * remove an item by x,y coordinates
   *
   * @param x x coordinate from 0 to width-1
   * @param y y coordinate from 0 to height-1
   */
  public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
    Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
    removeUnequippedInventoryItem(item);
  }

  /**
   * remove an item by x,y coordinates
   *
   * @param x x coordinate from 0 to width-1
   * @param y y coordinate from 0 to height-1
   */
  public void removeEquippedInventoryItemByCoordinates(int x, int y) {
    Item item = getEquippedInventoryItemEntityByCoordinates(x, y);
    removeEquippedInventoryItem(item);
  }

  /**
   * add item by x,y coordinates
   *
   * @param x x coordinate from 0 to width-1
   * @param y y coordinate from 0 to height-1
   */
  public void addEquippedInventoryItemByCoordinates(int x, int y) {
    Item item = getEquippedInventoryItemEntityByCoordinates(x, y);
    addEquippedInventoryItem(item);
  }

  /**
   * Use the hero castle strategy
   */
  private void useIfAtHerosCastle() {
    for (Building building : buildingEntities) {
      if (building.isSpawnLocation() && isInRange(building, character)) {
        character.incrementCycleCount();
        building.useBuilding(character);
      }
    }
  }

  /**
   * Checks if the character is in range of the hero castle
   *
   * @return the boolean if the character is at the hero castle
   */
  private boolean isAtHerosCastle() {
    for (Building building : buildingEntities) {
      if (building.isSpawnLocation() && isInRange(building, character)) {
        return true;
      }
    }
    return false;
  }

  /**
   * run moves which occur with every tick without needing to spawn anything
   * immediately
   */
  public void runTickMoves() {
    character.moveDownPath();
    useIfAtHerosCastle();
    moveBasicEnemies();
    if (goal.isGameWon())
      System.exit(0);
  }

  /**
   * remove an item from the unequipped inventory
   *
   * @param item item to be removed
   */
  public void removeUnequippedInventoryItem(Entity item) {
    item.destroy();
    unequippedInventoryItems.remove(item);
  }

  /**
   * remove an item from the unequipped inventory
   *
   * @param item item to be removed
   */
  public void removeEquippedInventoryItem(Item item) {
    item.destroy();
    equippedInventoryItems.remove(item);
  }

  /**
   * add an item to the equipped inventory
   *
   * @param item item to be added
   */
  public void addEquippedInventoryItem(Item item) {
    equippedSound();
    equippedInventoryItems.add(item);
  }

  /**
   * return an unequipped inventory item by x and y coordinates assumes that no 2
   * unequipped inventory items share x and y coordinates
   *
   * @param x x index from 0 to width-1
   * @param y y index from 0 to height-1
   * @return unequipped inventory item at the input position
   */
  public Item getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
    for (Item e : unequippedInventoryItems) {
      if ((e.getX() == x) && (e.getY() == y)) {
        return e;
      }
    }
    return null;
  }

  /**
   * return an unequipped inventory item by x and y coordinates assumes that no 2
   * unequipped inventory items share x and y coordinates
   *
   * @param x x index from 0 to width-1
   * @param y y index from 0 to height-1
   * @return unequipped inventory item at the input position
   */
  public Item getEquippedInventoryItemEntityByCoordinates(int x, int y) {
    for (Item e : equippedInventoryItems) {
      if ((e.getX() == x) && (e.getY() == y)) {
        return e;
      }
    }
    return null;
  }

  /**
   * remove item at a particular index in the unequipped inventory items list
   * (this is ordered based on age in the starter code)
   *
   * @param index index from 0 to length-1
   */
  private void removeItemByPositionInUnequippedInventoryItems(int index) {
    Entity item = unequippedInventoryItems.get(index);
    item.destroy();
    unequippedInventoryItems.remove(index);
  }

  /**
   * get the first pair of x,y coordinates which don't have any items in it in the
   * unequipped inventory
   *
   * @return x,y coordinate pair
   */
  private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
    // first available slot for an item...
    // IMPORTANT - have to check by y then x, since trying to find first available
    // slot defined by looking row by row
    for (int y = 0; y < unequippedInventoryHeight; y++) {
      for (int x = 0; x < unequippedInventoryWidth; x++) {
        if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
          return new Pair<Integer, Integer>(x, y);
        }
      }
    }
    return null;
  }

  /**
   * shift card coordinates down starting from x coordinate
   *
   * @param x x coordinate which can range from 0 to width-1
   */
  private void shiftCardsDownFromXCoordinate(int x) {
    for (Card c : cardEntities) {
      if (c.getX() >= x) {
        c.x().set(c.getX() - 1);
      }
    }
  }

  /**
   * move all enemies
   */
  private void moveBasicEnemies() {
    for (Enemy e : enemies) {
      e.move();
    }
  }

  /**
   * get a randomly generated position which could be used to spawn an enemy
   *
   * @return null if random choice is that wont be spawning an enemy or it isn't
   *         possible, or random coordinate pair if should go ahead
   */
  private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition() {

    // has a chance spawning a basic enemy on a tile the character isn't on or
    // immediately before or after (currently space required = 2)...
    Random rand = new Random();
    int choice = rand.nextInt(2);
    if ((choice == 0) && (enemies.size() < 2)) {
      List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
      int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
      // inclusive start and exclusive end of range of positions not allowed
      int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
      int endNotAllowed = (indexPosition + 3) % orderedPath.size();
      // note terminating condition has to be != rather than < since wrap around...
      for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
        orderedPathSpawnCandidates.add(orderedPath.get(i));
      }

      // choose random choice
      Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
          .get(rand.nextInt(orderedPathSpawnCandidates.size()));

      return spawnPosition;
    }
    return null;
  }

  public boolean isNeighbourPath(int x, int y) {
    for (Pair<Integer, Integer> path : orderedPath) {
      if ((x + 1) == path.getValue0() && (y) == path.getValue1())
        return true;
      if ((x - 1) == path.getValue0() && (y) == path.getValue1())
        return true;
      if ((x) == path.getValue0() && (y + 1) == path.getValue1())
        return true;
      if ((x) == path.getValue0() && (y - 1) == path.getValue1())
        return true;
    }
    return false;
  }

  /**
   * get a randomly generated position which could be used to spawn an enemy
   *
   * @return null if random choice is that wont be spawning an enemy or it isn't
   *         possible, or random coordinate pair if should go ahead
   */
  private Pair<Integer, Integer> possiblyGetBasicItemSpawnPosition() {

    // has a chance spawning a basic enemy on a tile the character isn't on or
    // immediately before or after (currently space required = 2)...
    Random rand = new Random();
    int choice = rand.nextInt(2);
    if ((choice == 0) && (pathItems.size() < 2)) {
      List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
      int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
      // inclusive start and exclusive end of range of positions not allowed
      int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
      int endNotAllowed = (indexPosition + 3) % orderedPath.size();
      // note terminating condition has to be != rather than < since wrap around...
      for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
        orderedPathSpawnCandidates.add(orderedPath.get(i));
      }

      // choose random choice
      Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
          .get(rand.nextInt(orderedPathSpawnCandidates.size()));

      return spawnPosition;
    }
    return null;
  }

  /**
   * remove a card by its x, y coordinates
   *
   * @param cardNodeX     x index from 0 to width-1 of card to be removed
   * @param cardNodeY     y index from 0 to height-1 of card to be removed
   * @param buildingNodeX x index from 0 to width-1 of building to be added
   * @param buildingNodeY y index from 0 to height-1 of building to be added
   */
  public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX,
      int buildingNodeY) {
    // start by getting card
    Card card = null;
    for (Card c : cardEntities) {
      if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
        card = c;
        break;
      }
    }
    // now spawn building
    SimpleIntegerProperty xIntegerProperty = new SimpleIntegerProperty(buildingNodeX);
    SimpleIntegerProperty yIntegerProperty = new SimpleIntegerProperty(buildingNodeY);
    Building newBuilding = new Building(xIntegerProperty, yIntegerProperty, card.getBuildingStrategy());
    boolean isPath = false;
    for (Pair<Integer, Integer> path : orderedPath) {
      // check if card is on path tile
      if (buildingNodeX == path.getValue0() && buildingNodeY == path.getValue1()) {
        isPath = true;
      }
    }
    if (newBuilding.canOnlySpawnOnPath()) {
      if (isPath) {
        addBuildingToWorld(newBuilding);
      } else {
        return null;
      }
    } else {
      if (newBuilding.canOnlySpawnNextToPath()) {
        if (isPath) {
          return null;
        } else if (isNeighbourPath(buildingNodeX, buildingNodeY)) {
          addBuildingToWorld(newBuilding);
        }
      } else {
        if (!isPath) {
          addBuildingToWorld(newBuilding);
        } else {
          return null;
        }
      }
    }

    // destroy the card
    card.destroy();
    cardEntities.remove(card);
    shiftCardsDownFromXCoordinate(cardNodeX);

    return newBuilding;
  }

  /**
   * Add the specified building to the world
   */
  public void addBuildingToWorld(Building building) {
    buildingEntities.add(building);
  }

  /**
   * Returns an item after dragging from unequipped inventory the the equipped
   * inventory
   *
   * @return the item after its equipped
   */
  public Item equipItembyCoordinates(int oldX, int oldY, int newX, int newY) {
    Item item = getUnequippedInventoryItemEntityByCoordinates(oldX, oldY);
    Item newItem = new Item(new SimpleIntegerProperty(newX), new SimpleIntegerProperty(newY), item.getStrategy());
    return newItem;
  }

  /**
   * Get the list of unequipped items
   *
   * @return the list of unequipped items
   */
  public List<Item> getUnequip() {
    return unequippedInventoryItems;
  }

  /**
   * Get the list of equipped items
   *
   * @return the list of equipped items
   */
  public List<Item> getEquip() {
    return equippedInventoryItems;
  }

  /**
   * buys an item from the shop, deducts gold from the charcter, if character does
   * not have enuough gold, item is set to null
   *
   * @param strat item strategy of the item to be bought
   * @return the item to be bought, null if character has insufficient gold
   */
  public Item buyItem(ItemStrategy strat) {
    Item newItem = null;
    int balance = character.getGold();
    if (balance - strat.getPrice() >= 0) {
      character.deductGold(strat.getPrice());
      if (!(strat instanceof HealthPotionStrategy))
        newItem = addSpecificUnequippedItem(strat);
    }
    return newItem;
  }

  /**
   * craft an item from the shop, deducts scrap metal from the charcter, if
   * character does not have enuough scrap metal, item is set to null
   *
   * @param strat item strategy of the item to be crafted
   * @return the item to be crafted, null if character has insufficient scrap
   *         metal
   */
  public Item craftItem(ItemStrategy strat) {
    Item newItem = null;
    int balance = character.getScrapMetal();
    if (balance - (strat.getPrice() / 10) >= 0) {
      character.deductScrapMetal(strat.getPrice() / 10);
      newItem = addSpecificUnequippedItem(strat);
    }
    return newItem;
  }

  /**
   * sells an item from the inventory, adds gold to the charcter, if character
   * does not have the item, nothing happens
   *
   * @param strat item strategy of the item to be sold
   */
  public Item sellItem(Class<?> strategy) {
    for (Item item : unequippedInventoryItems) {
      if (item.getStrategy().getClass().equals(strategy)) {
        removeUnequippedInventoryItem(item);
        character.addGold(item.getPrice() / 2);
        return item;
      }
    }
    return null;
  }

  /**
   * sells a DoggieCoin, adds gold to the charcter, if character does not have the
   * item, nothing happens
   *
   */
  public Item sellDoggieCoin() {
    Item doggieCoin = new Item(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), new DoggieCoinStrategy());
    if (character.getDoggieCoins() > 0) {
      if (isElanAlive)
        character.addGold(doggieCoin.getPrice() * midElanPriceMultiplier);
      else if (isElanDead)
        character.addGold((int) (doggieCoin.getPrice() * postElanPriceMultiplier));
      else
        character.addGold(doggieCoin.getPrice());
      character.deductDoggieCoins(1);
      return doggieCoin;
    }
    return null;
  }

  /**
   * sound effect for battle
   */
  public void swingSound() {
    String path = "src/audio/swing.wav";
    Media music = new Media(Paths.get(path).toUri().toString());
    swingSound = new MediaPlayer(music);
    swingSound.setVolume(0.2);
    swingSound.play();
  }

  /**
   * sound effect for equipping the item on the inventory
   */
  public void equippedSound() {
    String path = "src/audio/equip.wav";
    Media music = new Media(Paths.get(path).toUri().toString());
    equippedSound = new MediaPlayer(music);
    equippedSound.setVolume(0.3);
    equippedSound.play();

  }

}
