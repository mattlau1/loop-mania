package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class GoldObserver extends Observer {

    public GoldObserver(Character character, Goal goal) {
        this.character = character;
        this.goal = goal;
        this.character.addObservers(this);
    }

    @Override
    public void update() {
        goal.updateGoldStatus(character.getGold());
    }
}
