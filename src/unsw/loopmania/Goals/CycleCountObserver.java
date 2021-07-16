package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class CycleCountObserver extends Observer{

    public CycleCountObserver(Character character) {
        this.character = character;
        // goal.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Update state for cycle");
        // notify the goal class with character.getCycleCount()
        goal.notifyGoldObserver(character.getCycleCount());
    }
}
