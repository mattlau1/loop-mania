package unsw.loopmania.Goals;

public class ComplexGoal extends Goal {
    // check how many goals are checked
    // if all goals are checked
    // win otherwise keep playing
    private int numGoals = getGoals().size();

    public ComplexGoal() {
    }

    public boolean isGameWon() {
        int count = 0;

        for (SimpleGoal g: getGoals()) {
            if (g.isCompleted()) 
                count++;
        }

        // game won
        if (count == numGoals) return true;

        // not yet
        return false;

    }
}
