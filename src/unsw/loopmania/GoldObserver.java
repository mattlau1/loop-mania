package unsw.loopmania;

public class GoldObserver extends Observer {

    public GoldObserver(Character character) {
        this.character = character;
        // goal.addObserver(this);
    }

    @Override
    public void update() {
        // notify the goal class with character.getGold()
        goal.notifyGoldObserver(character.getGold());
    }
}
