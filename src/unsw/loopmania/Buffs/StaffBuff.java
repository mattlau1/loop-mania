package unsw.loopmania.Buffs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.ZombieEnemy;

public class StaffBuff extends Buff{
    public StaffBuff() {
    }

    // public void activateEffect(Character character, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {};
    @Override
    public void activateEffect(Soldier soldier, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {};
    @Override
    public void activateEffect(Character character, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {
        enemy.reduceHealth(100);
        allyList.add(new Soldier());
    };
}
