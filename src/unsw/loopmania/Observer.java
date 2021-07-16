package unsw.loopmania;

import unsw.loopmania.Goals.Goal;

public abstract class Observer {
    protected Character character;
    protected Goal goal;
    public abstract void update();
}
