package unsw.loopmania.Buffs;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.Soldier;
import unsw.loopmania.Enemies.Enemy;

public class StaffBuff extends Buff{
    public StaffBuff() {
    }

    @Override
    public void activateEffect(Soldier soldier, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
        return;
    };
    @Override
    public void activateEffect(Character character, Enemy enemy, List<Soldier> allyList, List<Enemy> zombieSoldiers) {
        enemy.reduceHealth(100);
        allyList.add(new Soldier());
    };
}
