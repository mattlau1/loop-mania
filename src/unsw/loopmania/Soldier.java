package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Buffs.Buff;

// import javafx.beans.property.SimpleIntegerProperty;

public class Soldier {
  private double health;
  private double damage;
  private List<Buff> buffs;

  public Soldier() {
    this.health = 25;
    this.damage = 10;
    buffs = new ArrayList<>();
  }

  public double getHealth() {
    return health;
  }

  public void setHealth(double health) {
    this.health = health;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public boolean isAlive() {
    return health > 0;
  }

  public boolean isDead() {
    return health <= 0;
  }

  /**
   * Reduces enemy's health by given amount, causing enemy to "take damage"
   *
   * @param health amount of damage to take
   */
  public void reduceHealth(double health) {
    this.health -= health;
  }

  public List<Buff> getBuffs() {
    return buffs;
  }

  public void addBuffs(Buff buff) {
    this.buffs.add(buff);
  }

}
