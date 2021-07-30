package unsw.loopmania.Goals;

public class BossGoal extends SimpleGoal {
  /**
   * Create a simple goal for killing boss
   *
   * @param goalValue the amount of boss kills needed to win the game
   */
  public BossGoal(int goalValue) {
    super(goalValue, Goal.BOSS_GOAL);
    super.setGoalValue(goalValue);
    super.setGoalCheck(false);
  }

}
