package unsw.loopmania.Goals;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Character;

public class Goal {
    private List<SimpleGoal> goals;

    public Goal() {
        this.goals = new ArrayList<SimpleGoal>();
    }

    public void addGoal(SimpleGoal goal) {
        goals.add(goal);
    }

    public List<SimpleGoal> getGoals() {
        return goals;
    }

    public void setGoals(List<SimpleGoal> goals) {
        this.goals = goals;
    }

    public boolean isGameWon(Character character) {
        updateExperienceStatus(character.getExp());
        updateCycleStatus(character.getCycleCount());
        updateGoldStatus(character.getGold());
        return isGoalCompleted();
    }

    public void updateExperienceStatus(int exp) {
        System.out.println(exp);
        for (SimpleGoal g: goals) {
            if (g.getGoalType().equals("Experience") && g.goalMeetsRequirement(exp)) {
                g.setGoalCheck(true);
            }
        }
    }

    public void updateGoldStatus(int gold) {
        for (SimpleGoal g: goals) {
            if (g.getGoalType().equals("Gold") && g.goalMeetsRequirement(gold)) {
                g.setGoalCheck(true);
            }
        }
    }

    public void updateCycleStatus(int cycle) {
        for (SimpleGoal g: goals) {
            if (g.getGoalType().equals("Cycle") && g.goalMeetsRequirement(cycle)) {
                g.setGoalCheck(true);
            }
        }
    }

    public boolean isGoalCompleted() {
        int count = 0;
        int numGoals = getGoals().size();

        for (SimpleGoal g: getGoals()) {
            if (g.isCompleted()) 
                count++;
        }

        // game won
        if (count == numGoals) return true;
        // not yet
        return false;
    }

}
