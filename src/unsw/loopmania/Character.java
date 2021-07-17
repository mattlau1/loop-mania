package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Buffs.Buff;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
  private final double maxHealth = 100;
  private double health = 100;
  private int gold = 0;
  private int exp = 0;
  private double damage = 15;
  private double damageMultiplier = 1;
  private int currentCycle = 0;
  private List<Buff> buffs;

  public Character(PathPosition position) {
    super(position);
    buffs = new ArrayList<>();
  }

  public double getMaxHealth() {
    return maxHealth;
  }

  public double getHealth() {
    return health;
  }

  public void setHealth(double health) {
    this.health = health;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
    // notifyAllObservers();
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
    // notifyAllObservers();
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
    return health > 0;
  }

  public boolean isDead() {
    return health <= 0;
  }

  public void addGold(int gainedGold) {
    this.gold += gainedGold;
  }

  public void deductGold(int lostGold) {
    this.gold -= lostGold;
  }

  public void addEXP(int droppedEXP) {
    this.exp += droppedEXP;
  }

  /**
   * Reduces character's health by given amount, causing character to take damage
   *
   * @param health amount of damage to take
   */
  public void reduceHealth(double health) {
    this.health -= health;
  }

  /**
   * Increases character's health by given amount, healing the character
   *
   * @param health amount of damage to take
   */
  public void addHealth(double health) {
    this.health += health;
  }

  /**
   * Adds 1 to the character's cycle count
   */
  public void incrementCycleCount() {
    this.currentCycle++;
  }

  /**
   * Gets the cycle that the character is currently on
   *
   * @return current cycle number
   */
  public int getCycleCount() {
    // System.out.printf("Current Cycle Number: %d\n", currentCycle);
    return this.currentCycle;
  }

  public List<Buff> getBuffs() {
    return buffs;
  }

  public void addBuffs(Buff buff) {
    this.buffs.add(buff);
  }

}
