package unsw.loopmania.Cards;

import javafx.scene.image.ImageView;
import unsw.loopmania.Buildings.BuildingStrategy;

public interface CardStrategy {
  public ImageView getImage();

  public BuildingStrategy getBuildingStrategy();
}
