package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buffs.Buff;
import unsw.loopmania.Goals.Observer;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
  private final double initialHealth = 100;
  private final int initialGold = 0;
  private final int initialExp = 0;
  private double damage = 15;
  private double damageMultiplier = 1;
  private SimpleDoubleProperty healthProperty;
  private SimpleIntegerProperty goldProperty;
  private SimpleIntegerProperty expProperty;
  private SimpleIntegerProperty cycleProperty;
  private List<Buff> buffs;
  private List<Soldier> soldiers;
  private List<Observer> observers = new ArrayList<Observer>();
  private int bossKillCount = 0;

  public Character(PathPosition position) {
    super(position);
    soldiers = new ArrayList<>();
    buffs = new ArrayList<>();
    this.healthProperty = new SimpleDoubleProperty(this, "health", initialHealth);
    this.goldProperty = new SimpleIntegerProperty(this, "gold", initialGold);
    this.expProperty = new SimpleIntegerProperty(this, "exp", initialExp);
    this.cycleProperty = new SimpleIntegerProperty(this, "cycle", 0);
  }

  /**
   * Gets the list of soliders
   * 
   * @return the list of soliders
   */
  public List<Soldier> getSoldiers() {
    return soldiers;
  }

  /**
   * Gets a particular soldier with the given index
   * 
   * @param index the index of the solider
   * @return the soldier at the index
   */
  public Soldier getSoldiersFromIndex(int index) {
    return soldiers.get(index);
  }

  /**
   * Gets a particular soldier with the given index
   * 
   * @param index the index of the solider
   */
  public Soldier removeSoldiersFromIndex(int index) {
    return soldiers.remove(index);
  }

  /**
   * Sets the list of soldiers with the list
   * 
   * @param soliders the new list of soldiers
   */
  public void setSoldiers(List<Soldier> soldiers) {
    this.soldiers = soldiers;
  }

  /**
   * Add a new solider into the list
   */
  public void addSoldier() {
    this.soldiers.add(new Soldier());
  }

  /**
   * Add a new solider into the list
   */
  public int soldiersSize() {
    return this.soldiers.size();
  }

  /**
   * Gets the initial health of the character
   * 
   * @return the initial health
   */
  public double getInitialHealth() {
    return initialHealth;
  }

  /**
   * Gets the max health of the character
   * 
   * @return the max health
   */
  public double getMaxHealth() {
    return initialHealth;
  }

  /**
   * Gets the current health of the character
   * 
   * @return the current health
   */
  public double getHealth() {
    return healthProperty.get();
  }

  /**
   * Sets the health of the character with the new value
   */
  public void setHealth(double health) {
    healthProperty.set(health);
  }

  /**
   * Gets the current gold the character is holding
   * 
   * @return the gold amount
   */
  public int getGold() {
    return goldProperty.get();
  }

  /**
   * Sets a new gold value for character to hold
   * 
   * @param gold the new gold amount
   */
  public void setGold(int gold) {
    goldProperty.set(gold);
  }

  /**
   * Gets the current experience the character is holding
   * 
   * @return the experience amount
   */
  public int getExp() {
    return expProperty.get();
  }

  /**
   * Sets a new experience value for character to hold
   * 
   * @param exp the new experience amount
   */
  public void setExp(int exp) {
    expProperty.set(exp);
  }

  /**
   * Gets raw damage (not including damage multiplier)
   *
   * @return character's damage before multipliers
   */
  public double getDamage() {
    return damage;
  }

  /**
   * Gets character's damage including multiplier
   *
   * @return character's damage after multiplier
   */
  public double getMultipliedDamage() {
    return damage * damageMultiplier;
  }

  /**
   * Sets character's damage
   *
   * @param damage character's new damage
   */
  public void setDamage(double damage) {
    this.damage = damage;
  }

  /**
   * Gets character's damage multiplier
   *
   * @return character's damage multiplier
   */
  public double getDamageMultiplier() {
    return damageMultiplier;
  }

  /**
   * Sets character's damage multiplier
   *
   * @param damageMultiplier character's new damage multiplier
   */
  public void setDamageMultiplier(double damageMultiplier) {
    this.damageMultiplier = damageMultiplier;
  }

  /**
   * Reset the damage multiplier to the default value
   */
  public void resetDamageMultiplier() {
    this.damageMultiplier = 1;
  }

  /**
   * Checks if the character is still alive
   * 
   * @return the boolean if the character is still alive
   */
  public boolean isAlive() {
    return healthProperty.get() > 0;
  }

  /**
   * Checks if the character is dead
   * 
   * @return the boolean if the character is dead
   */
  public boolean isDead() {
    return healthProperty.get() <= 0;
  }

  /**
   * Increases character's gold by given amount, holding more gold
   * 
   * @param gold the amount of gold to add
   */  
  public void addGold(int gold) {
    goldProperty.set(goldProperty.get() + gold);
    notifyAllObservers();
  }

  /**
   * Reduces character's gold by given amount, holding less gold
   * 
   * @param gold the amount of gold to reduce
   */  
  public void deductGold(int gold) {
    goldProperty.set(goldProperty.get() - gold);
  }

  /**
   * Increases character's experience by given amount, holding more expeirence
   * 
   * @param gold the amount of experience to add
   */  
  public void addEXP(int exp) {
    expProperty.set(expProperty.get() + exp);
    notifyAllObservers();
  }

  /**
   * Reduces character's health by given amount, causing character to take damage
   *
   * @param health amount of damage to take
   */
  public void reduceHealth(double health) {
    healthProperty.set(healthProperty.get() - health);
  }

  /**
   * Increases character's health by given amount, healing the character
   *
   * @param health amount of damage to take
   */
  public void addHealth(double health) {
    healthProperty.set(healthProperty.get() + health);
  }

  /**
   * Adds 1 to the character's cycle count
   */
  public void incrementCycleCount() {
    cycleProperty.set(cycleProperty.get() + 1);
    notifyAllObservers();
  }

  /**
   * Gets the cycle that the character is currently on
   *
   * @return current cycle number
   */
  public int getCycleCount() {
    return cycleProperty.get();
  }

  /**
   * Gets the list of buffs on the character
   *
   * @return the list of buffs
   */
  public List<Buff> getBuffs() {
    return buffs;
  }

  /**
   * Add a new buff into the list of buffs
   *
   * @return the new buff to be added
   */
  public void addBuffs(Buff buff) {
    this.buffs.add(buff);
  }

  /**
   * Gets the current health of the character
   * 
   * @return the current health
   */
  public SimpleDoubleProperty getHealthProperty() {
    return healthProperty;
  }

  /**
   * Gets the current experience the character is holding
   * 
   * @return the experience amount
   */
  public SimpleIntegerProperty getExpProperty() {
    return expProperty;
  }

  /**
   * Gets the current gold the character is holding
   * 
   * @return the gold amount
   */
  public SimpleIntegerProperty getGoldProperty() {
    return goldProperty;
  }

  /**
   * Gets the cycle that the character is currently on
   *
   * @return current cycle number
   */
  public SimpleIntegerProperty getCycleProperty() {
    return cycleProperty;
  }

  /**
   * Add the observer into the observer list
   * 
   * @param observer the observer which tracks the quantity for goals
   */
  public void addObservers(Observer observer) {
    observers.add(observer);
  }

  /**
   * Update all the observers when the stats are updated on the character
   */
  public void notifyAllObservers(){
    for (Observer observer : observers) {
       observer.update();
    }
 }

  /**
   * Gets the boss kill count number
   * 
   * @return the number of boss killed
   */
  public int getBossKillCount() {
    return bossKillCount;
  }

  /**
   * Gets the boss kill count number
   * 
   * @return the number of boss killed
   */
  public void setBossKillCount(int bossKillCount) {
    this.bossKillCount = bossKillCount;
  }

  /**
   * Add a count to the boss kill count
   */
  public void addBossKillCount() {
    setBossKillCount(getBossKillCount() + 1);
    notifyAllObservers();
  }

}
