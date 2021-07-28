package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class EXPObserver extends Observer {

    public EXPObserver(Character character, Goal goal) {
        this.character = character;
        this.goal = goal;
        this.character.addObservers(this);
    }

    @Override
    public void update() {
        goal.updateExperienceStatus(character.getExp());
    }
    
}
