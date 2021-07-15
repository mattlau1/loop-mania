package unsw.loopmania;

public class EXPObserver extends Observer {

    public EXPObserver(Character character) {
        this.character = character;
        this.character.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Update state for exp");
        // notify the goal class with character.getExp()
    }
}
