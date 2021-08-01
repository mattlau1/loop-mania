package unsw.loopmania.Enemies;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.PathPosition;

public class ElanEnemy extends Enemy {
  private final double health = 1000;
  private final int battleRange = 2;
  private final int supportRange = 5;
  private final double damage = 2;
  private final int expDrop = 400;
  private final int goldDrop = 250;
  private final int critRate = 0;
  private final int doggieCoinDrop = 20;
  public static final double HEAL_AMOUNT = 2;

  public ElanEnemy(PathPosition position) {
    super(position);
    setHealth(health);
    setMaxHealth(health);
    setBattleRange(battleRange);
    setSupportRange(supportRange);
    setDamage(damage);
    setExpDrop(expDrop);
    setGoldDrop(goldDrop);
    setCritRate(critRate);
    setDoggieCoinDrop(doggieCoinDrop);
  }

  @Override
  public boolean isBoss() {
    return true;
  }

  @Override
  public ImageView getImage(String imgLoc) {
    Image image = new Image((new File("src/" + imgLoc + "/elan.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
