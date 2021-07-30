package unsw.loopmania.Goals;

public class BossGoal extends SimpleGoal {
    /**
   * Create a simple goal for killing boss
   *
   * @param goalValue the amount of boss kill needed to win the game
   */
  public BossGoal(int goalValue) {
    super(goalValue, "Boss");
    super.setGoalValue(goalValue);
    super.setGoalCheck(false);
  }
}
