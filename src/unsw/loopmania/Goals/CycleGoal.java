package unsw.loopmania.Goals;

public class CycleGoal extends SimpleGoal {
    
    public CycleGoal() {
        super.setGoalText("Complete 200 cycles");
        super.setGoalValue(200);
        super.setGoalCheck(false);
        super.setGoalType("Cycle");
        addGoal(this);
    }

}
