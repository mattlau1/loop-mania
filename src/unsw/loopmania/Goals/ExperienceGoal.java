package unsw.loopmania.Goals;

public class ExperienceGoal extends SimpleGoal {
    
    public ExperienceGoal(int goalValue) {
        super(goalValue, "Experience");
        super.setGoalValue(goalValue);
        super.setGoalCheck(false);
        addGoal(this);
    }

}
