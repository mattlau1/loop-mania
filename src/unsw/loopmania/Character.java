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

  public Character(PathPosition position) {
    super(position);
    soldiers = new ArrayList<>();
    buffs = new ArrayList<>();
    this.healthProperty = new SimpleDoubleProperty(this, "health", initialHealth);
    this.goldProperty = new SimpleIntegerProperty(this, "gold", initialGold);
    this.expProperty = new SimpleIntegerProperty(this, "exp", initialExp);
    this.cycleProperty = new SimpleIntegerProperty(this, "cycle", 0);
  }

  public List<Soldier> getSoldiers() {
    return soldiers;
  }

  public Soldier getSoldiersFromIndex(int index) {
    return soldiers.get(index);
  }

  public Soldier removeSoldiersFromIndex(int index) {
    return soldiers.remove(index);
  }

  public void setSoldiers(List<Soldier> soldiers) {
    this.soldiers = soldiers;
  }

  public void addSoldier() {
    this.soldiers.add(new Soldier());
  }

  public int soldiersSize() {
    return this.soldiers.size();
  }

  public double getInitialHealth() {
    return initialHealth;
  }

  public double getMaxHealth() {
    return initialHealth;
  }

  public double getHealth() {
    return healthProperty.get();
  }

  public void setHealth(double health) {
    healthProperty.set(health);
  }

  public int getGold() {
    return goldProperty.get();
  }

  public void setGold(int gold) {
    goldProperty.set(gold);
  }

  public int getExp() {
    return expProperty.get();
  }

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

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public double getDamageMultiplier() {
    return damageMultiplier;
  }

  public void setDamageMultiplier(double damageMultiplier) {
    this.damageMultiplier = damageMultiplier;
  }

  public void resetDamageMultiplier() {
    this.damageMultiplier = 1;
  }

  public boolean isAlive() {
    return healthProperty.get() > 0;
  }

  public boolean isDead() {
    return healthProperty.get() <= 0;
  }

  public void addGold(int gold) {
    goldProperty.set(goldProperty.get() + gold);
    notifyAllObservers();
  }

  public void deductGold(int gold) {
    goldProperty.set(goldProperty.get() - gold);
  }

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

  public List<Buff> getBuffs() {
    return buffs;
  }

  public void addBuffs(Buff buff) {
    this.buffs.add(buff);
  }

  public SimpleDoubleProperty getHealthProperty() {
    return healthProperty;
  }

  public SimpleIntegerProperty getExpProperty() {
    return expProperty;
  }

  public SimpleIntegerProperty getGoldProperty() {
    return goldProperty;
  }

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
   * Update all the observers
   */
  public void notifyAllObservers(){
    for (Observer observer : observers) {
       observer.update();
    }
 }

}
