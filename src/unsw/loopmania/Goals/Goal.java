package unsw.loopmania.Goals;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Character;

public class Goal {
  private List<SimpleGoal> goals;

  /**
   * A goal constructur which will hold lists of simple goals
   */
  public Goal() {
    this.goals = new ArrayList<SimpleGoal>();
  }

  /**
   * Add the simple goal into the goal list
   *
   * @param goal the goal will be added into the goal list
   */
  public void addGoal(SimpleGoal goal) {
    goals.add(goal);
  }

  /**
   * Get the list of goals
   *
   * @return the list of goals
   */
  public List<SimpleGoal> getGoals() {
    return goals;
  }

  /**
   * Set the list of the goals
   *
   * @param goals the set of new goals
   */
  public void setGoals(List<SimpleGoal> goals) {
    this.goals = goals;
  }

  /**
   * A method which checks each goal whether it has meet the requirement
   *
   * @param character to get the character's status to check the goal
   * @return the boolean if the character has meet all the requirement
   */
  public boolean isGameWon(Character character) {
    updateExperienceStatus(character.getExp());
    updateCycleStatus(character.getCycleCount());
    updateGoldStatus(character.getGold());
    return isGoalCompleted();
  }

  /**
   * Check if the user has meet the requirement for the experience goal
   *
   * @param exp the exp will be compared to the goal's quantity
   */
  public void updateExperienceStatus(int exp) {
    for (SimpleGoal g : goals) {
      if (g.getGoalType().equals("Experience") && g.goalMeetsRequirement(exp)) {
        g.setGoalCheck(true);
      }
    }
  }

  /**
   * Check if the user has meet the requirement for the gold goal
   *
   * @param gold the gold will be compared to the goal's quantity
   */
  public void updateGoldStatus(int gold) {
    for (SimpleGoal g : goals) {
      if (g.getGoalType().equals("Gold") && g.goalMeetsRequirement(gold)) {
        g.setGoalCheck(true);
      }
    }
  }

  /**
   * Check if the user has meet the requirement for the cycle goal
   *
   * @param cycle the cycle will be compared to the goal's quantity
   */
  public void updateCycleStatus(int cycle) {
    for (SimpleGoal g : goals) {
      if (g.getGoalType().equals("Cycle") && g.goalMeetsRequirement(cycle)) {
        g.setGoalCheck(true);
      }
    }
  }

  /**
   * Checks if all the goal has been completed and will determine if game is won
   */
  public boolean isGoalCompleted() {
    int count = 0;
    int numGoals = getGoals().size();

    for (SimpleGoal g : getGoals()) {
      if (g.isCompleted())
        count++;
    }

    // game won
    if (count == numGoals)
      return true;
    // not yet
    return false;
  }

}
