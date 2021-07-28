package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class GoldObserver extends Observer {

  /**
   * A constructor that is reponsible for observing experience quantity
   * 
   * @param character the character contains the field for list of observers
   * @param goal goal method can be called if update function is called
   */
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
