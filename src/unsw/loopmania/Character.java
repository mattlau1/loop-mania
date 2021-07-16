package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes
    
    private int health;
    private int gold;
    private int exp;
    private int damage;

    private List<Observer> observers = new ArrayList<Observer>();
    private EXPObserver expObserver;
    private GoldObserver goldObserver;
    private CycleCountObserver cycleCountObserver;
    // private int state;
    private int cycleCount;

    public Character(PathPosition position) {
        super(position);
        this.health = 100;
        this.gold = 0;
        this.exp = 0;
        this.damage = 15;
        this.cycleCount = 0;
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
        goldObserver.update();
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        expObserver.update();
    }

    public int getCycleCount() {
        return this.cycleCount;
    }

    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
        cycleCountObserver.update();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    // public int getState() {
    //     return state;
    // }

    // public void setState(int state) {
    //     this.state = state;
    //     notifyAllObservers();
    // }

    // public void notifyAllObservers() {
    //     for (Observer observer: observers) {
    //         observer.update();
    //     }
    // }

    
}
