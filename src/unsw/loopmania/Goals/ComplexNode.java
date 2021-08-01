package unsw.loopmania.Goals;

public interface ComplexNode {
  /**
   * Gets the goal as a string
   *
   * @return goal string
   */
  public String getValue();

  /**
   * Updates goal
   *
   * @param quantity required quantity for goal
   * @param goalType type of goal (i.e boss goal, cycle goal)
   */
  public void updateValue(int quantity, String goalType);

  /**
   * Evaluates the goal
   *
   * @return true if goal is completed else false
   */
  public boolean evaluate();
}
