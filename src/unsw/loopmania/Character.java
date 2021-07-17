package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Goals.Observer;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    
    private int health;
    private int gold;
    private int exp;
    private int damage;
    private List<Observer> observers;
    private int cycleCount;

    public Character(PathPosition position) {
        super(position);
        this.health = 100;
        this.gold = 0;
        this.exp = 0;
        this.damage = 15;
        this.cycleCount = 0;
        this.observers = new ArrayList<Observer>();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
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

    public int getCycleCount() {
        return this.cycleCount;
    }

    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
        // notifyAllObservers();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void addToObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

    
}
