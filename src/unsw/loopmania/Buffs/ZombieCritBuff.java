package unsw.loopmania.Buffs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.ZombieEnemy;

public class ZombieCritBuff extends Buff{
    public ZombieCritBuff() {}

    @Override
    public void activateEffect(Character character, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {};
    @Override
    public void activateEffect(Soldier soldier, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {
        soldier.reduceHealth(20);
        BasicEnemy newZombie = zombieSoldiers.get(0);
        zombieSoldiers.add(newZombie);
    };
}
