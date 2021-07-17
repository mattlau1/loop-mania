package unsw.loopmania.Goals;

public class GoldGoal extends SimpleGoal {
    
    public GoldGoal(int goalValue) {
        super(goalValue, "Gold");
        super.setGoalValue(goalValue);
        super.setGoalCheck(false);
    }
}
