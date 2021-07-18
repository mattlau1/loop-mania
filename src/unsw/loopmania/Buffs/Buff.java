package unsw.loopmania.Buffs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.Enemies.Enemy;

public class Buff {
  private int turns;

  public Buff() {
    Random random = new Random();
    this.turns = random.nextInt(3) + 1;
  }

  public void activateEffect(Character character, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
    subtractTurns(1);
  };

  public void activateEffect(Soldier soldier, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
    subtractTurns(1);
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

  public void subtractTurns(int turns) {
    this.turns -= turns;
  }

}
