package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Buffs.Buff;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes

    private double health = 6;
    private int gold = 0;
    private int exp = 0;
    private double damage = 15;
    private double damageMultiplier = 1;
    private List<Buff> buffs;

    public Character(PathPosition position) {
        super(position);
        buffs = new ArrayList<>();
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
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
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
