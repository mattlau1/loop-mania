package unsw.loopmania.Enemies;

import java.util.Random;

import javafx.scene.image.ImageView;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buffs.Buff;

/**
 * a basic form of enemy in the world
 */
public abstract class Enemy extends MovingEntity {
  private double health;
  private int battleRange;
  private int supportRange;
  private double damage;
  private int expDrop;
  private int goldDrop;
  private int critRate;

  /**
   * backbone of the enemy constructor
   *
   * @param position the position where the enemy will spawn in the map
   */
  public Enemy(PathPosition position) {
    super(position);
  }

  /**
   * get the current health of the enemy
   *
   * @return the enemy's health
   */
  public double getHealth() {
    return health;
  }

  /**
   * change the health of the enemy
   *
   * @param health the new health for the enemy
   */
  public void setHealth(double health) {
    this.health = health;
  }

  /**
   * get the battle range of the enemy
   *
   * @return the enemy's battle range
   */
  public int getBattleRange() {
    return battleRange;
  }

  /**
   * change the battle range of the enemy
   *
   * @param battleRange the new battle range for the enemy
   */
  public void setBattleRange(int battleRange) {
    this.battleRange = battleRange;
  }

  /**
   * get the battle range of the enemy
   *
   * @return the enemy's damage
   */
  public double getDamage() {
    return damage;
  }

  /**
   * change the damage of the enemy
   *
   * @param damage the damage for the enemy
   */
  public void setDamage(double damage) {
    this.damage = damage;
  }

  /**
   * get the support range of the enemy
   *
   * @return the enemy's support range
   */
  public int getSupportRange() {
    return supportRange;
  }

  /**
   * change the support range of the enemy
   *
   * @param supportRange the new battle support for the enemy
   */
  public void setSupportRange(int supportRange) {
    this.supportRange = supportRange;
  }

  /**
   * get the experience drop from the enemy
   *
   * @return the enemy's experience drop
   */
  public int getExpDrop() {
    return expDrop;
  }

  /**
   * change the experience drop of the enemy
   *
   * @param expDrop the new experience drop for the enemy
   */
  public void setExpDrop(int expDrop) {
    this.expDrop = expDrop;
  }

  /**
   * get the gold drop from the enemy
   *
   * @return the enemy's gold drop
   */
  public int getGoldDrop() {
    return goldDrop;
  }

  /**
   * change the gold drop of the enemy
   *
   * @param goldDrop the new gold drop for the enemy
   */
  public void setGoldDrop(int goldDrop) {
    this.goldDrop = goldDrop;
  }

  /**
   * get the crit rate of the enemy
   *
   * @return the crit rate of the enemy
   */
  public int getCritRate() {
    return critRate;
  }

  /**
   * set the crit rate of the enemy
   *
   * @param critRate the crit rate of the enemy
   */
  public void setCritRate(int critRate) {
    this.critRate = critRate;
  }

  /**
   * Reduces enemy's health by given amount, causing enemy to "take damage"
   *
   * @param health amount of damage to take
   */
  public void reduceHealth(double health) {
    this.health -= health;
  }

  /**
   * Check if the enemy is still alive
   *
   * @return the boolean of enemy being alive
   */
  public boolean isAlive() {
    return health > 0;
  }

  /**
   * Check if the enemy is dead
   *
   * @return the boolean of the dead enemy
   */
  public boolean isDead() {
    return health <= 0;
  }

  /**
   * Enemy will give a buff status when it lands a critical hit
   *
   * @return the new buff for the enemy to inflict on character
   */
  public Buff criticalHit() {
    Buff buff = new Buff();
    return buff;
  }

  /**
   * move the enemy
   */
  public void move() {
    int directionChoice = (new Random()).nextInt(2);
    if (directionChoice == 0) {
      moveUpPath();
    } else if (directionChoice == 1) {
      moveDownPath();
    }
  }

  /**
   * Enemy changes direction
   */
  public void changeDirection() {
    return;
  }

  /**
   * a flag that confirms if the enemy has changed direction
   */
  public void resetHasChangedDirection() {
    return;
  }

  /**
   * load an image for the respective enemy
   */
  public abstract ImageView getImage();
}
