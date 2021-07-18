package unsw.loopmania.Goals;

public class SimpleGoal extends Goal {
  private int goalValue;
  private boolean isCompleted;
  private String goalType;

  /**
   * Create a simple goal varying on the goal type
   *
   * @param goalValue the amount needed to complete its respective goal
   * @param goalType a string which classify the simple goal (gold, exp, cycle)
   */
  public SimpleGoal(int goalValue, String goalType) {
    this.goalValue = goalValue;
    this.isCompleted = false;
    this.goalType = goalType;
  }

  /**
   * Get the quantity for a particular goal
   *
   * @return the quantitiy for the goal
   */
  public int getGoalValue() {
    return goalValue;
  }

  /**
   * Set the quantity for a particular goal
   *
   * @param goalValue the new quantitiy for the goal
   */
  public void setGoalValue(int goalValue) {
    this.goalValue = goalValue;
  }

  /**
   * Check if a goal is completed
   *
   * @return the boolean for if the goal is completed
   */
  public boolean isCompleted() {
    return isCompleted;
  }

  /**
   * Set a goal's status to either completed or not
   *
   * @param goalCheck the boolean to change the state of goal check
   */
  public void setGoalCheck(boolean goalCheck) {
    this.isCompleted = goalCheck;
  }

  /**
   * Set a goal's type to differentiate other goals
   *
   * @param goalType the type that will classify the goal
   */
  public void setGoalType(String goalType) {
    this.goalType = goalType;
  }

  /**
   * Gets the type of the goal
   *
   * @return the goal type
   */
  public String getGoalType() {
    return goalType;
  }

  /**
   * Checks if the value meets the goal's quantity requirement
   *
   * @param value check if the value has meet the goal's quantity
   */
  public boolean goalMeetsRequirement(int value) {
    if ((value >= goalValue) && !isCompleted()) {
      setGoalCheck(true);
      return true;
    }
    return false;
  }

}
