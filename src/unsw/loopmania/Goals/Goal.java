package unsw.loopmania.Goals;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Character;

public class Goal {
  private List<SimpleGoal> simpleGoals;
  private List<ComplexGoal> complexGoals;

  /**
   * A goal constructur which will hold lists of simple goals
   */
  public Goal() {
    this.simpleGoals = new ArrayList<SimpleGoal>();
    this.complexGoals = new ArrayList<ComplexGoal>();
  }

  /**
   * Add the simple goal into the goal list
   *
   * @param goal the goal will be added into the goal list
   */
  public void addSimpleGoal(SimpleGoal goal) {
    simpleGoals.add(goal);
  }

  /**
   * Add the simple goal into the goal list
   *
   * @param goal the goal will be added into the goal list
   */
  public void addComplexGoal(ComplexGoal goal) {
    complexGoals.add(goal);
  }


  /**
   * Get the list of simple goals
   *
   * @return the list of simple goals
   */
  public List<SimpleGoal> getSimpleGoals() {
    return simpleGoals;
  }

    /**
   * Get the list of goals
   *
   * @return the list of complex goals
   */
  public List<ComplexGoal> getComplexGoals() {
    return complexGoals;
  }

  /**
   * Set the list of the goals
   *
   * @param goals the set of new goals
   */
  public void setGoals(List<SimpleGoal> goals) {
    this.simpleGoals = goals;
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
    return isGoalCompleted(character);
  }

  /**
   * Check if the user has meet the requirement for the experience goal
   *
   * @param exp the exp will be compared to the goal's quantity
   */
  public void updateExperienceStatus(int exp) {
    // update goal for simple goals
    for (SimpleGoal g : simpleGoals) {
      if (g.getGoalType().equals("Experience") && g.goalMeetsRequirement(exp)) {
        g.setGoalCheck(true);
      }
    }
    updateComplexGoals(exp, "Experience");
  }

  /**
   * Check if the user has meet the requirement for the gold goal
   *
   * @param gold the gold will be compared to the goal's quantity
   */
  public void updateGoldStatus(int gold) {
    for (SimpleGoal g : simpleGoals) {
      if (g.getGoalType().equals("Gold") && g.goalMeetsRequirement(gold)) {
        g.setGoalCheck(true);
      }
    }
    updateComplexGoals(gold, "Gold");
  }

  /**
   * Check if the user has meet the requirement for the cycle goal
   *
   * @param cycle the cycle will be compared to the goal's quantity
   */
  public void updateCycleStatus(int cycle) {
    for (SimpleGoal g : simpleGoals) {
      if (g.getGoalType().equals("Cycle") && g.goalMeetsRequirement(cycle)) {
        g.setGoalCheck(true);
      }
    }
    updateComplexGoals(cycle, "Cycle");
  }

  /**
   * Checks if all the goal has been completed and will determine if game is won
   */
  public boolean isGoalCompleted(Character character) {

    // check if the simple goal has been completed
    if (getSimpleGoals().size() != 0) {
      int count = 0;
      int numGoals = getSimpleGoals().size();
  
      for (SimpleGoal g : getSimpleGoals()) {
        if (g.isValue())
          count++;
      }
  
      // game won
      if (count == numGoals) {
        return true;
      }
        
    } 

    // check if the complex goal has been completed
    for (ComplexGoal g : getComplexGoals()) {
      if (g.evaluate()) {
        return true;
      }
    }

    // hasn't been completed  yet
    return false;
  }

  // TESTING PURPOSES
  public static String prettyPrintComplex(ComplexNode expression) {
    // Pretty print the expression
    return expression.getValue();
  }

  // TESTING PURPOSES
  public void printComplexGoals() {
    for (ComplexGoal g: complexGoals) {
      System.out.println(prettyPrintComplex(g));
    }
  }

  // TESTING PURPOSES
  public String prettyPrintSimple(SimpleGoal simpleGoal) {
    return simpleGoal.getValue();
  }
  
  // TESTING PURPOSES
  public void printSimpleGoal() {
    for (SimpleGoal s:  simpleGoals) {
      System.out.println(prettyPrintComplex(s));
    }
  }

  public void updateComplexGoals(int value, String goalType) {
    for (ComplexGoal g: getComplexGoals()) {
      g.updateValue(value, goalType);
    }
  }

}
