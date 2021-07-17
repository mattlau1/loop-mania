package unsw.loopmania.Buffs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;
import unsw.loopmania.SlugEnemy;
import unsw.loopmania.Soldier;
import unsw.loopmania.VampireEnemy;

public class VampireCritBuff extends Buff{

    public VampireCritBuff() {
    }
    @Override
    public void activateEffect(Character character, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {
        if (enemy instanceof VampireEnemy) {
            substractTurns(1);
            Random random = new Random();
            int randInt = random.nextInt(6) + 5;
            character.reduceHealth(randInt);
        }
    };
    @Override
    public void activateEffect(Soldier soldier, BasicEnemy enemy, List<Soldier> allyList, List<BasicEnemy> zombieSoldiers) {
        if (enemy instanceof VampireEnemy) {
            substractTurns(1);
            Random random = new Random();
            int randInt = random.nextInt(6) + 5;
            soldier.reduceHealth(randInt);
        }
    };
}
