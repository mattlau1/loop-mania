package unsw.loopmania.Enemies;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.loopmania.PathPosition;

public class SlugEnemy extends Enemy {

  private final double health = 20;
  private final int battleRange = 2;
  private final int supportRange = 5;
  private final double damage = 5;
  private final int expDrop = 10;
  private final int goldDrop = 10;
  private final int critRate = 0;

  /**
   * Slug constructor, sets slug stats
   *
   * @param position the position where the enemy will spawn in the map
   */
  public SlugEnemy(PathPosition position) {
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
    // slug has random movement and a chance to not move at all
    int directionChoice = (new Random()).nextInt(2);
    if (directionChoice == 0) {
      moveUpPath();
    } else if (directionChoice == 1) {
      moveDownPath();
    }
  }

  @Override
  public ImageView getImage() {
    Image image = new Image((new File("src/images/slug.png")).toURI().toString());
    ImageView view = new ImageView(image);
    return view;
  }

}
