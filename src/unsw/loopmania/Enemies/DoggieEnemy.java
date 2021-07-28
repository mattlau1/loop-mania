package unsw.loopmania.Enemies;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;

public class DoggieEnemy extends Enemy {
  private final double health = 400;
  private final int battleRange = 2;
  private final int supportRange = 5;
  private final double damage = 10;
  private final int expDrop = 400;
  private final int goldDrop = 250;
  private final int critRate = 0;
  private final int doggieCoinDrop = 1;
  private final int spawnCycle = 3;

  /**
   * Doggie constructor, sets doggie stats
   *
   * @param position the position where the enemy will spawn in the map
   */
  public DoggieEnemy(PathPosition position) {
    super(position);
    setHealth(health);
    setBattleRange(battleRange);
    setSupportRange(supportRange);
    setDamage(damage);
    setExpDrop(expDrop);
    setGoldDrop(goldDrop);
    setCritRate(critRate);
    setDoggieCoinDrop(doggieCoinDrop);
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/doggie.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }
}