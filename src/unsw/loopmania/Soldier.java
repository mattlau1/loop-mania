package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Soldier extends StaticEntity {
    private double health;
    private double damage;

    public Soldier(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.health = 25;
        this.damage = 10;
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

}
