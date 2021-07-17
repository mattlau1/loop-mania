package unsw.loopmania.Goals;

public class CycleGoal extends SimpleGoal {
    
    public CycleGoal(int goalValue) {
        super(goalValue, "Cycle");
        super.setGoalValue(goalValue);
        super.setGoalCheck(false);
    }

}
