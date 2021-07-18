package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.ArmourStrategy;
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
import unsw.loopmania.Buffs.Buff;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.BuildingStrategy;
import unsw.loopmania.Buildings.HerosCastleStrategy;
import unsw.loopmania.Cards.BarracksCardStrategy;
import unsw.loopmania.Cards.CampfireCardStrategy;
import unsw.loopmania.Cards.Card;
import unsw.loopmania.Cards.CardStrategy;
import unsw.loopmania.Cards.TowerCardStrategy;
import unsw.loopmania.Cards.TrapCardStrategy;
import unsw.loopmania.Cards.VampireCastleCardStrategy;
import unsw.loopmania.Cards.VillageCardStrategy;
import unsw.loopmania.Cards.ZombiePitCardStrategy;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.SlugEnemy;
import unsw.loopmania.Enemies.VampireEnemy;
import unsw.loopmania.Goals.Goal;

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

  private Character character;

  // TODO = add more lists for other entities, for equipped inventory items,
  // etc...
  private List<Item> equippedInventoryItems;

  private List<ItemStrategy> commonItems;
  private List<ItemStrategy> lowRarityItems;
  private List<ItemStrategy> midRarityItems;
  private List<ItemStrategy> highRarityItems;
  private List<ItemStrategy> superRarityItems;

  private List<CardStrategy> lowRarityCards;
  private List<CardStrategy> midRarityCards;
  private List<CardStrategy> highRarityCards;

  // TODO = expand the range of enemies
  private List<Enemy> enemies;

  private List<Enemy> zombieSoldiers;

  // private List<Soldier> soldiers;
  private List<Soldier> trancedSoldiers;

  // TODO = expand the range of cards
  private List<Card> cardEntities;

  // TODO = expand the range of items
  private List<Item> unequippedInventoryItems;

  // TODO = expand the range of buildings
  private List<Building> buildingEntities;

  /**
   * list of x,y coordinate pairs in the order by which moving entities traverse
   * them
   */
  private List<Pair<Integer, Integer>> orderedPath;

  /**
   * create the world (constructor)
   *
   * @param width       width of world in number of cells
   * @param height      height of world in number of cells
   * @param orderedPath ordered list of x, y coordinate pairs representing
   *                    position of path cells in world
   */
  public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, Goal goal) {
    this.width = width;
    this.height = height;
    nonSpecifiedEntities = new ArrayList<>();
    character = null;
    enemies = new ArrayList<>();
    zombieSoldiers = new ArrayList<>();
    cardEntities = new ArrayList<>();
    unequippedInventoryItems = new ArrayList<>();
    equippedInventoryItems = new ArrayList<>();
    commonItems = new ArrayList<>();
    lowRarityItems = new ArrayList<>();
    midRarityItems = new ArrayList<>();
    highRarityItems = new ArrayList<>();
    superRarityItems = new ArrayList<>();
    lowRarityCards = new ArrayList<>();
    midRarityCards = new ArrayList<>();
    highRarityCards = new ArrayList<>();
    this.orderedPath = orderedPath;
    buildingEntities = new ArrayList<>();
    // soldiers = new ArrayList<>();
    trancedSoldiers = new ArrayList<>();
  }

  public void generateItemDrops() {
    commonItems.clear();
    lowRarityItems.clear();
    midRarityItems.clear();
    superRarityItems.clear();

    commonItems.add(new GoldStrategy());

    lowRarityItems.add(new SwordStrategy());
    lowRarityItems.add(new StakeStrategy());

    midRarityItems.add(new ArmourStrategy());
    midRarityItems.add(new ShieldStrategy());
    midRarityItems.add(new HelmetStrategy());

    highRarityItems.add(new StaffStrategy());
    highRarityItems.add(new HealthPotionStrategy());

    superRarityItems.add(new TheOneRingStrategy());
  }

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

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  /**
   * set the character. This is necessary because it is loaded as a special entity
   * out of the file
   *
   * @param character the character
   */
  public void setCharacter(Character character) {
    this.character = character;
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
   * add a generic entity (without it's own dedicated method for adding to the
   * world)
   *
   * @param entity
   */
  public void addEntity(Entity entity) {
    // for adding non-specific entities (ones without another dedicated list)
    // TODO = if more specialised types being added from main menu, add more methods
    // like this with specific input types...
    nonSpecifiedEntities.add(entity);
  }

  /**
   * spawns enemies if the conditions warrant it, adds to world
   *
   * @return list of the enemies to be displayed on screen
   */
  public List<Enemy> possiblySpawnEnemies() {
    // TODO = expand this very basic version

    Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
    List<Enemy> spawningEnemies = new ArrayList<>();
    if (pos != null) {
      int indexInPath = orderedPath.indexOf(pos);

      // spawns a slug
      Enemy slug = new SlugEnemy(new PathPosition(indexInPath, orderedPath));
      enemies.add(slug);
      spawningEnemies.add(slug);
    }
    // go through every building in the world
    // if the building can spawn enemies, check cycle count
    // and spawn an enemy on the closest path tile to that building
    // System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
    for (Building building : buildingEntities) {
      if (isAtHerosCastle()) {
        if (building.canSpawnEnemy(character.getCycleCount())) {
          // TODO: change spawn location to closest path
          Pair<Integer, Integer> buildingLocation = new Pair<Integer, Integer>(building.getX(), building.getY());
          int buildingIndexInPath = orderedPath.indexOf(buildingLocation);
          Enemy enemy = building.spawnEnemy(new PathPosition(buildingIndexInPath, orderedPath));

          // System.out.println(enemy);
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
   * kill an enemy
   *
   * @param enemy enemy to be killed
   */
  private void killEnemy(Enemy enemy) {
    enemy.destroy();
    enemies.remove(enemy);
  }

  private boolean isInRange(Enemy e, Character c) {
    // Pythagoras: a^2+b^2 < radius^2 to see if within radius
    // TODO = you should implement different RHS on this inequality, based on
    // influence radii and battle radii
    return Math.pow((c.getX() - e.getX()), 2) + Math.pow((c.getY() - e.getY()), 2) < e.getBattleRange();
  }

  private boolean isInSuppRange(Enemy e, Character c) {
    // Pythagoras: a^2+b^2 < radius^2 to see if within radius
    // TODO = you should implement different RHS on this inequality, based on
    // influence radii and battle radii
    return Math.pow((c.getX() - e.getX()), 2) + Math.pow((c.getY() - e.getY()), 2) < e.getSupportRange();
  }

  private boolean isInRange(Building b, Character c) {
    return Math.pow((c.getX() - b.getX()), 2) + Math.pow((c.getY() - b.getY()), 2) < b.getRange();
  }

  private boolean isInRange(Building b, Enemy e) {
    return Math.pow((e.getX() - b.getX()), 2) + Math.pow((e.getY() - b.getY()), 2) < b.getRange();
  }

  /**
   * run the expected battles in the world, based on current world state
   *
   * @return list of enemies which have been killed
   */
  public List<Enemy> runBattles() {
    System.out.println(character.soldiersSize());
    // we have three types of buildings:
    // one that is for the character outside of combat (i.e village)
    // one that is for the enemies outside of combat (i.e trap)
    // one that is for the character and enemies inside of combat (i.e tower,
    // campfire)
    List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
    // building for character outside of combat
    for (Building b : buildingEntities) {
      if (isInRange(b, character) && b.usableOutsideCombat() && !b.isHerosCastle()) {
        // System.out.printf("Character just used %s\n", b.getClass());
        System.out.println("xd");
        b.useBuilding(character);
      }
    }

    // building for enemies outside of combat
    for (Building b : buildingEntities) {
      for (Enemy e : enemies) {
        if (isInRange(b, e) && b.usableOutsideCombat()) {
          // System.out.printf("%s just used %s\n", e.getClass(), b.getClass());
          b.useBuilding(e);
          if (e.isDead()) defeatedEnemies.add(e);
        }
      }
    }

    // building for enemies and character inside of combat
    List<Enemy> battlingEnemies = new ArrayList<Enemy>();


    for (Enemy enemy : enemies) {
      if (isInRange(enemy, character)) {
        battlingEnemies.add(enemy);
        for (Enemy support : enemies) {
          if (support != enemy) {
            if (isInSuppRange(support, character))
              battlingEnemies.add(support);
          }
        }
      }
    }

    for (Enemy enemy : battlingEnemies) {
      while (enemy.isAlive()) {
        for (Building building : buildingEntities) {
          if (isInRange(building, character)) {
            building.useBuilding(character);
            building.useBuilding(enemy);
          }
        }
        // TODO = modify this - currently the character automatically wins all battles
        // without any damage!
        // TODO = check enemy hp and only add to defeatedEnemies if they are dead
        // TODO = CRITS
        double characterDamage = character.getMultipliedDamage();
        double enemyDamage = enemy.getDamage();
        // Character attacks first enemy
        for (Item equippedItems : equippedInventoryItems) {
          characterDamage *= equippedItems.atkMultiplier(enemy);
        }
        // System.out.println("WOOOOOOOOOOOO");
        // System.out.println(characterDamage);
        // System.out.println("WOOOOOOOOOOOO");
        Random random = new Random();
        int randInt = random.nextInt(2);
        if (randInt == 1) {
          for (Item equippedItems : equippedInventoryItems) {
            equippedItems.onHitEffects(enemy, trancedSoldiers);
          }
        }
        enemy.reduceHealth(characterDamage);
        // Every enemy in the battle attacks any soldiers, then the character
        for (Enemy currBattlingEnemy : battlingEnemies) {
          // test(currBattlingEnemy);
          if (currBattlingEnemy.isDead()) continue;
          boolean criticalHit = false;
          random = new Random();
          randInt = random.nextInt(100) + 1;
          double enemyCriR = currBattlingEnemy.getCritRate();
          for (Item equippedItems : equippedInventoryItems) {
            enemyCriR *= equippedItems.critMultiplier(enemy);
          }
          if (randInt <= enemyCriR)
            criticalHit = true;

          for (Item equippedItems : equippedInventoryItems) {
            enemyDamage *= equippedItems.defMultiplier(currBattlingEnemy);
          }
          // System.out.println(enemyDamage);
          if (trancedSoldiers.size() > 0) {
            Soldier s = trancedSoldiers.get(0);
            if (criticalHit) {
              if (!s.getBuffs().contains(currBattlingEnemy.criticalHit())) {
                s.addBuffs(currBattlingEnemy.criticalHit());
              }
            }
            s.reduceHealth(enemyDamage);
            for (Buff buff : s.getBuffs()) {
              buff.activateEffect(s, currBattlingEnemy, character.getSoldiers(), zombieSoldiers);
            }
            if (s.isDead())
            trancedSoldiers.remove(0);
          } else if (character.soldiersSize() > 0) {
            Soldier s = character.getSoldiersFromIndex(0);
            if (criticalHit) {
              if (!s.getBuffs().contains(currBattlingEnemy.criticalHit())) {
                s.addBuffs(currBattlingEnemy.criticalHit());
              }
            }
            s.reduceHealth(enemyDamage);
            for (Buff buff : s.getBuffs()) {
              buff.activateEffect(s, currBattlingEnemy, character.getSoldiers(), zombieSoldiers);
            }
            if (s.isDead())
              character.removeSoldiersFromIndex(0);
          } else {
            if (criticalHit) {
              if (!character.getBuffs().contains(currBattlingEnemy.criticalHit())) {
                character.addBuffs(currBattlingEnemy.criticalHit());
              }
            }
            character.reduceHealth(enemyDamage);
            for (Buff buff : character.getBuffs()) {
              buff.activateEffect(character, currBattlingEnemy, character.getSoldiers(), zombieSoldiers);
            }
            character.reduceHealth(enemyDamage);
          }
        }
        for (Enemy currBattlingEnemy : zombieSoldiers) {
          System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
          if (currBattlingEnemy.isDead()) continue;
          for (Item equippedItems : equippedInventoryItems) {
            enemyDamage *= equippedItems.defMultiplier(currBattlingEnemy);
          }
          if (trancedSoldiers.size() > 0) {
            Soldier s = trancedSoldiers.get(0);
            s.reduceHealth(enemyDamage);
            if (s.isDead()) character.removeSoldiersFromIndex(0);
          } else if (character.soldiersSize() > 0) {
            Soldier s = character.getSoldiersFromIndex(0);
            s.reduceHealth(enemyDamage);
            if (s.isDead()) character.removeSoldiersFromIndex(0);
          } else {
            character.reduceHealth(enemyDamage);
          }
        }
        System.out.println("CHARACTER HEALTH");
        System.out.println(character.getHealth());
        // System.out.println("ENEMY HEALTH");
        // System.out.println(enemy.getHealth());
      }
      defeatedEnemies.add(enemy);
      character.addEXP(enemy.getExpDrop());
      character.addGold(enemy.getGoldDrop());
    }

    trancedSoldiers.clear();
    for (Enemy e : defeatedEnemies) {
      // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
      // the enemies list
      // if we killEnemy in prior loop, we get
      // java.util.ConcurrentModificationException
      // due to mutating list we're iterating over
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
    if (cardEntities.size() >= getWidth()) {
      // TODO = give some cash/experience/item rewards for the discarding of the
      // oldest card
      removeCard(0);
    }

    Card card = new Card(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0),
        randomCardStrategy());
    cardEntities.add(card);
    return card;
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
    // TODO = expand this - we would like to be able to add multiple types of items,
    // apart from swords
    Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
    if (firstAvailableSlot == null) {
      // eject the oldest unequipped item and replace it... oldest item is that at
      // beginning of items
      // TODO = give some cash/experience rewards for the discarding of the oldest
      // sword
      removeItemByPositionInUnequippedInventoryItems(0);
      firstAvailableSlot = getFirstAvailableSlotForItem();
    }

    // now we insert the new sword, as we know we have at least made a slot
    // available...
    Item item = new Item(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
        new SimpleIntegerProperty(firstAvailableSlot.getValue1()), randomItemStrategy());
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

  public void addEquippedInventoryItemByCoordinates(int x, int y) {
    Item item = getEquippedInventoryItemEntityByCoordinates(x, y);
    addEquippedInventoryItem(item);
  }

  private void useIfAtHerosCastle() {
    for (Building building : buildingEntities) {
      if (building.isHerosCastle() && isInRange(building, character)) {
        character.incrementCycleCount();
        building.useBuilding(character);
      }
    }
  }

  private boolean isAtHerosCastle() {
    for (Building building : buildingEntities) {
      if (building.isHerosCastle() && isInRange(building, character)) {
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
    // TODO = expand to more types of enemy
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
    // TODO = modify this

    // has a chance spawning a basic enemy on a tile the character isn't on or
    // immediately before or after (currently space required = 2)...
    Random rand = new Random();
    int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
    // TODO = change based on spec
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

    addBuildingToWorld(newBuilding);

    // destroy the card
    card.destroy();
    cardEntities.remove(card);
    shiftCardsDownFromXCoordinate(cardNodeX);

    return newBuilding;
  }

  public void addBuildingToWorld(Building building) {
    buildingEntities.add(building);
  }

  public Item equipItembyCoordinates(int oldX, int oldY, int newX, int newY) {
    Item item = getUnequippedInventoryItemEntityByCoordinates(oldX, oldY);
    Item newItem = new Item(new SimpleIntegerProperty(newX), new SimpleIntegerProperty(newY), item.getStrategy());
    return newItem;
  }

  public List<Item> getUnequip() {
    return unequippedInventoryItems;
  }

  public List<Item> getEquip() {
    return equippedInventoryItems;
  }

}
