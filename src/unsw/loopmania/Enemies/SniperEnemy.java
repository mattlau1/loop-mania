package unsw.loopmania.Enemies;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.PathPosition;

public class SniperEnemy extends Enemy {
  private final double health = 15;
  private final int battleRange = 1;
  private final int supportRange = 1;
  private final double damage = 5;
  private final int expDrop = 100;
  private final int goldDrop = 100;
  private final int critRate = 0;

  public SniperEnemy(PathPosition position) {
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
  public void move() {
    return;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/sniper.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
