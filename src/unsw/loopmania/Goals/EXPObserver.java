package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class EXPObserver extends Observer {

    public EXPObserver(Character character) {
        this.character = character;
        this.character.addToObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Update state for exp");
        // notify the goal class with character.getExp()
        System.out.printf("Experience: %d", character.getExp());
    }
}

