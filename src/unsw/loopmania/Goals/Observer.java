package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public abstract class Observer {
    protected Character character;
    protected Goal goal;
    public abstract void update();
}
