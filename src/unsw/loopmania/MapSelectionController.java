package unsw.loopmania;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * controller for the main menu. TODO = you could extend this, for example with
 * a settings menu, or a menu to load particular maps.
 */
public class MapSelectionController {
  private MenuSwitcher gameModeSwitcher;
  private MenuSwitcher gameGrassSwitcher;
  private MenuSwitcher gameJPSwitcher;
  private MenuSwitcher gameWasteSwitcher;

  @FXML
  Button backButton;

  @FXML
  Button grassMapButton;

  @FXML
  Button japaneseMapButton;

  @FXML
  Button wasteMapButton;

  @FXML
  private void initialize() {
    setButtonHoverEffects();
  }

  /**
   * facilitates switching to main game
   */
  public void setGameGrassSwitcher(MenuSwitcher gameSwitcher) {
    this.gameGrassSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to main game
   */
  public void setGameJPSwitcher(MenuSwitcher gameSwitcher) {
    this.gameJPSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to main game
   */
  public void setGameWasteSwitcher(MenuSwitcher gameSwitcher) {
    this.gameWasteSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to main game upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGameGrass() throws IOException {
    gameGrassSwitcher.switchMenu();
  }

  /**
   * facilitates switching to main game upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGameJP() throws IOException {
    gameJPSwitcher.switchMenu();
  }

  /**
   * facilitates switching to main game upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGameWaste() throws IOException {
    gameWasteSwitcher.switchMenu();
  }

  /**
   * facilitates switching to game mode menu
   */
  public void setGameModeSwitcher(MenuSwitcher gameSwitcher) {
    this.gameModeSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to game mode menu upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGameMode() throws IOException {
    gameModeSwitcher.switchMenu();
  }

  private void setButtonHoverEffects() {
    backButton.setStyle("-fx-background-color:transparent;");

    backButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        backButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    backButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });
  }

}
