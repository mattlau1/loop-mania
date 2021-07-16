package unsw.loopmania.Goals;

import java.util.ArrayList;
import java.util.List;

// import unsw.loopmania.Observer;

public class Goal {
    // private List<Observer> observers;
    private List<SimpleGoal> goals;

    // public void addObserver(Observer observer) {
    //     observers.add(observer);
    // }
    public Goal() {
        this.goals = new ArrayList<SimpleGoal>();
    }

    public void addGoal(SimpleGoal goal) {
        goals.add(goal);
    }

    // public List<Observer> getObservers() {
    //     return observers;
    // }

    // public void setObservers(List<Observer> observers) {
    //     this.observers = observers;
    // }

    public List<SimpleGoal> getGoals() {
        return goals;
    }

    public void setGoals(List<SimpleGoal> goals) {
        this.goals = goals;
    }

    public void notifyExpObserver(int exp) {
        System.out.println(exp);
        for (SimpleGoal g: goals) {
            if (g.getGoalType().equals("Experience") && g.goalMeetsRequirement(exp)) {
                g.setGoalCheck(true);
            }
        }
    }

    public void notifyGoldObserver(int gold) {
        for (SimpleGoal g: goals) {
            if (g.getGoalType().equals("Gold") && g.goalMeetsRequirement(gold)) {
                g.setGoalCheck(true);
            }
        }
    }

    public void notifyCycleObserver(int cycle) {
        for (SimpleGoal g: goals) {
            if (g.getGoalType().equals("Cycle") && g.goalMeetsRequirement(cycle)) {
                g.setGoalCheck(true);
            }
        }
    }

    

}
