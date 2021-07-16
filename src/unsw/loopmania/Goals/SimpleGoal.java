package unsw.loopmania.Goals;

public class SimpleGoal extends Goal {
    private int goalValue;
    private boolean isCompleted;
    private String goalType;

    public SimpleGoal(int goalValue, String goalType) {
        this.goalValue = goalValue;
        this.isCompleted = false;
        this.goalType = goalType;
    }

    public int getGoalValue() {
        return goalValue;
    }
    public void setGoalValue(int goalValue) {
        this.goalValue = goalValue;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setGoalCheck(boolean goalCheck) {
        this.isCompleted = goalCheck;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getGoalType() {
        return goalType;
    }

    public boolean goalMeetsRequirement(int value) {
        if ((value == goalValue) && !isCompleted()) {
            setGoalCheck(true);
            return true;
        }
        return false;
    }


    
}
