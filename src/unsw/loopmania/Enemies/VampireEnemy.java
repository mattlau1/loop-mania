package unsw.loopmania.Enemies;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buffs.Buff;
import unsw.loopmania.Buffs.VampireCritBuff;

public class VampireEnemy extends Enemy {
  private final double health = 60;
  private final int battleRange = 2;
  private final int supportRange = 5;
  private final double damage = 20;
  private final int expDrop = 100;
  private final int goldDrop = 100;
  private final int critRate = 30;

  /**
   * spawn the vampire constructor
   *
   * @param position the position where the enemy will spawn in the map
   */
  public VampireEnemy(PathPosition position) {
    super(position);
    setHealth(health);
    setBattleRange(battleRange);
    setSupportRange(supportRange);
    setDamage(damage);
    setExpDrop(expDrop);
    setGoldDrop(goldDrop);
    setCritRate(critRate);
  }

  @Override
  public void move() {
    // vampire changes direction when near the campfire

    // campfire is yet to be implemented
    int directionChoice = (new Random()).nextInt(3);
    if (directionChoice == 0) {
      moveUpPath();
    }
  }

  @Override
  public Buff criticalHit() {
    return new VampireCritBuff();
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/vampire.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
