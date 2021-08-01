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
public class HowToPlayController {
  private MenuSwitcher howToPlaySwitcher;
  private MenuSwitcher menuSwitcher;

  @FXML
  Button backButton;

  @FXML
  private void initialize() {
    setButtonHoverEffects();
  }

  /**
   * facilitates switching to main menu
   */
  public void setMainMenuSwitcher(MenuSwitcher gameSwitcher) {
    this.menuSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to main menu upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMainMenu() throws IOException {
    menuSwitcher.switchMenu();
  }

  /**
   * facilitates switching to how to play
   */
  public void setHowToPlaySwitcher(MenuSwitcher gameSwitcher) {
    this.howToPlaySwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to how to play screen upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToHowToPlay() throws IOException {
    howToPlaySwitcher.switchMenu();
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
