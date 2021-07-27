package unsw.loopmania.Enemies;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.PathPosition;

public class Doggie extends Enemy {
  private final double health = 400;
  private final int battleRange = 2;
  private final int supportRange = 5;
  private final double damage = 10;
  private final int expDrop = 400;
  private final int goldDrop = 25;
  private final int critRate = 0;
  private final int doggieCoinDrop = 1;

  /**
   * Doggie constructor, sets doggie stats
   *
   * @param position the position where the enemy will spawn in the map
   */
  public Doggie(PathPosition position) {
    super(position);
    setHealth(health);
    setBattleRange(battleRange);
    setSupportRange(supportRange);
    setDamage(damage);
    setExpDrop(expDrop);
    setGoldDrop(goldDrop);
    setCritRate(critRate);
    super.setDoggieCoinDrop(doggieCoinDrop);
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/doggie.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }
}
