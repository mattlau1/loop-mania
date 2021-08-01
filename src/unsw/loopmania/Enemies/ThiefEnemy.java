package unsw.loopmania.Enemies;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.PathPosition;

public class ThiefEnemy extends Enemy {
  private final double health = 45;
  private final int battleRange = 2;
  private final int supportRange = 5;
  private final double damage = 2;
  private final int expDrop = 10;
  private final int goldDrop = 20;
  private final int critRate = 0;
  public final static int STEAL_AMOUNT = 10;

  public ThiefEnemy(PathPosition position) {
    super(position);
    setHealth(health);
    setMaxHealth(health);
    setBattleRange(battleRange);
    setSupportRange(supportRange);
    setDamage(damage);
    setExpDrop(expDrop);
    setGoldDrop(goldDrop);
    setCritRate(critRate);
  }

  @Override
  public boolean canStealFromCharacter() {
    return true;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/thief.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
