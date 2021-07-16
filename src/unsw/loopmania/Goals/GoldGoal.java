package unsw.loopmania.Goals;

public class GoldGoal extends SimpleGoal {
    
    public GoldGoal() {
        super.setGoalText("Collect 20,000 golds");
        super.setGoalValue(20000);
        super.setGoalCheck(false);
        super.setGoalType("Gold");
        addGoal(this);
    }
}
