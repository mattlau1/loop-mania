package unsw.loopmania.Buffs;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.Enemies.Enemy;

public class ZombieCritBuff extends Buff {
  public ZombieCritBuff() {
  }

  @Override
  public void activateEffect(Character character, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
  };

  @Override
  public void activateEffect(Soldier soldier, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
    soldier.reduceHealth(20);
    Enemy newZombie = zombieSoldiers.get(0);
    zombieSoldiers.add(newZombie);
  };
}
