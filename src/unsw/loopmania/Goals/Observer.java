package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public abstract class Observer {
    protected Character character;
    public abstract void update();
}
