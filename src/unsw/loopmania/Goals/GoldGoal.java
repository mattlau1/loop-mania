package unsw.loopmania.Goals;

public class GoldGoal extends SimpleGoal {

  /**
   * Create a simple goal for gold
   *
   * @param goalValue the amount of gold needed to complete the gold goal
   */
  public GoldGoal(int goalValue) {
    super(goalValue, "Gold");
    super.setGoalValue(goalValue);
    super.setGoalCheck(false);
  }
}
