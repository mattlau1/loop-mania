package unsw.loopmania.Goals;

public class GoldGoal extends SimpleGoal {

  /**
   * Create a simple goal for gold
   *
   * @param goalValue the amount of gold needed to complete the gold goal
   */
  public GoldGoal(int goalValue) {
    super(goalValue, Goal.GOLD_GOAL);
    super.setGoalValue(goalValue);
    super.setGoalCheck(false);
  }

}
