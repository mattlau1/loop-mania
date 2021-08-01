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
  private MenuSwitcher menuSwitcher;
  private MenuSwitcher mapSelectionStandardSwitcher;
  private MenuSwitcher mapSelectionSurvivalSwitcher;
  private MenuSwitcher mapSelectionBerserkerSwitcher;
  private MenuSwitcher mapSelectionConfusingSwitcher;

  @FXML
  Button backButton;

  /**
   * called when controller is initialised, sets the button hover effects
   */
  @FXML
  private void initialize() {
    setButtonHoverEffects();
  }

  /**
   * facilitates switching to map selection
   */
  public void setMapSelectionStandardSwitcher(MenuSwitcher gameSwitcher) {
    this.mapSelectionStandardSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMapSelectionStandard() throws IOException {
    mapSelectionStandardSwitcher.switchMenu();
  }

  /**
   * facilitates switching to map selection
   */
  public void setMapSelectionSurvivalSwitcher(MenuSwitcher gameSwitcher) {
    this.mapSelectionSurvivalSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMapSelectionSurvival() throws IOException {
    mapSelectionSurvivalSwitcher.switchMenu();
  }

  /**
   * facilitates switching to map selection
   */
  public void setMapSelectionBerserkerSwitcher(MenuSwitcher gameSwitcher) {
    this.mapSelectionBerserkerSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMapSelectionBerserker() throws IOException {
    mapSelectionBerserkerSwitcher.switchMenu();
  }

  /**
   * facilitates switching to map selection
   */
  public void setMapSelectionConfusingSwitcher(MenuSwitcher gameSwitcher) {
    this.mapSelectionConfusingSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMapSelectionConfusing() throws IOException {
    mapSelectionConfusingSwitcher.switchMenu();
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
   * Set the button hover effects
   */
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
