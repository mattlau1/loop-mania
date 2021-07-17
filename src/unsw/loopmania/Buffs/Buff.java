package unsw.loopmania.Buffs;

import java.util.List;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;

public class Buff {
    private int turns;

    public Buff() {
        this.turns = 3;
    }

    public void activateEffect(Character character,BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {
        substractTurns(1);
    };
    public void activateEffect(Soldier soldier,BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {
        substractTurns(1);
    };

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void addTurns(int turns) {
        this.turns += turns;
    }

    public void substractTurns(int turns) {
        this.turns -= turns;
    }

}
