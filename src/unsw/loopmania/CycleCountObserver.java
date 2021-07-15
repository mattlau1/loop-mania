package unsw.loopmania;

public class CycleCountObserver extends Observer{

    public CycleCountObserver(Character character) {
        this.character = character;
        this.character.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Update state for cycle");
        // notify the goal class with character.getCycleCount()
    }
}
