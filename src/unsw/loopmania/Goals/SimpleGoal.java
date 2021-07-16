package unsw.loopmania.Goals;

public class SimpleGoal extends Goal {
    private String goalText;
    private int goalValue;
    private boolean goalCheck;
    private String goalType;

    public String getGoalText() {
        return goalText;
    }
    public void setGoalText(String goalText) {
        this.goalText = goalText;
    }
    public int getGoalValue() {
        return goalValue;
    }
    public void setGoalValue(int goalValue) {
        this.goalValue = goalValue;
    }
    public boolean isGoalCheck() {
        return goalCheck;
    }
    public void setGoalCheck(boolean goalCheck) {
        this.goalCheck = goalCheck;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getGoalType() {
        return goalType;
    }

    public void goalMeetsRequirement(int value) {
        if ((value == goalValue) && !isGoalCheck()) setGoalCheck(true);
    }


    
}
