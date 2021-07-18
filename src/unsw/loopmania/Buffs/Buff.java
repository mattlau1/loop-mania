package unsw.loopmania.Buffs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.Enemies.Enemy;

public class Buff {
  private int turns;

  /**
   * Create a buff that will either lasts 1-3 turns
   */
  public Buff() {
    Random random = new Random();
    this.turns = random.nextInt(3) + 1;
  }

  /**
   * Activates buff effect but if it has no effect do nothing
   * 
   * @param character the character that has been affected
   * @param enemy the enemy responsible for the buff
   * @param allyList the allyList will get reduced by 1
   * @param zombieSoliders the solider gets infected and 
   */
  public void activateEffect(Character character, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
    subtractTurns(1);
  };

  /**
   * Activates buff effect but if it has no effect do nothing
   * 
   * @param solider the solider that has been affected
   * @param enemy the enemy responsible for the buff
   * @param allyList the allyList will get reduced by 1
   * @param zombieSoliders the solider gets infected and 
   */
  public void activateEffect(Soldier soldier, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
    subtractTurns(1);
  };

  /**
   * Get the current turn of the buff
   * 
   * @return the turn of the buff
   */
  public int getTurns() {
    return turns;
  }

  /**
   * Set the turn with the new value
   * 
   * @param turns the new value for turns
   */
  public void setTurns(int turns) {
    this.turns = turns;
  }

  /**
   * Prolong the effect of the buff 
   * 
   * @param turns add another value for the turn
   */
  public void addTurns(int turns) {
    this.turns += turns;
  }

  /**
   * Reduce the turn duration for the buff
   * 
   * @param turns subect another value for the turn
   */
  public void subtractTurns(int turns) {
    this.turns -= turns;
  }

}
