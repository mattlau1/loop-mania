package unsw.loopmania.Goals;

public class CycleGoal extends SimpleGoal {

  /**
   * Create a simple goal for cycle
   *
   * @param goalValue the amount of cycle needed to complete the cycle goal
   */
  public CycleGoal(int goalValue) {
    super(goalValue, Goal.CYCLE_GOAL);
    super.setGoalValue(goalValue);
    super.setGoalCheck(false);
  }
}
