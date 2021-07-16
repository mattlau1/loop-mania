package unsw.loopmania.Goals;

public class ExperienceGoal extends SimpleGoal {
    
    public ExperienceGoal() {
        super.setGoalText("Collect 6000 exp");
        super.setGoalValue(6000);
        super.setGoalCheck(false);
        super.setGoalType("Experience");
        addGoal(this);
    }

}
