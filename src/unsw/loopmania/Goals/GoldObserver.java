package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class GoldObserver extends Observer {

    public GoldObserver(Character character) {
        this.character = character;
        // goal.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Update state for gold");
        // notify the goal class with character.getGold()
        System.out.println(character.getGold());
        goal.notifyGoldObserver(character.getGold());
    }
}
