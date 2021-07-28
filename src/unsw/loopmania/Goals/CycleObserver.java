package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class CycleObserver extends Observer {

    public CycleObserver(Character character, Goal goal) {
        this.character = character;
        this.goal = goal;
        this.character.addObservers(this);
    }

    @Override
    public void update() {
        goal.updateCycleStatus(character.getCycleCount());
    }
}
