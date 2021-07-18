package unsw.loopmania.Goals;

public class ExperienceGoal extends SimpleGoal {

  /**
   * Create a simple goal for experience
   *
   * @param goalValue the amount of experience needed to complete the experience goal
   */
  public ExperienceGoal(int goalValue) {
    super(goalValue, "Experience");
    super.setGoalValue(goalValue);
    super.setGoalCheck(false);
  }

}
