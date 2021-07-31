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
public class GameModeController {
  private MenuSwitcher mapSelectionSwitcher;
  private MenuSwitcher gameSwitcher;

  @FXML
  Button backButton;

  // @FXML
  // Button howToPlayButton;

  // @FXML
  // Button startGameButton;

  @FXML
  private void initialize() {
    setButtonHoverEffects();
  }

  /**
   * facilitates switching to main game
   */
  public void setGameSwitcher(MenuSwitcher gameSwitcher) {
    this.gameSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to main game upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGame() throws IOException {
    gameSwitcher.switchMenu();
  }

  /**
   * facilitates switching to main menu
   */
  public void setMapSelectionSwitcher(MenuSwitcher gameSwitcher) {
    this.mapSelectionSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to main menu upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMapSelection() throws IOException {
    mapSelectionSwitcher.switchMenu();
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
