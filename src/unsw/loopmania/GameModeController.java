package unsw.loopmania;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
  MediaPlayer buttonClick;
  MediaPlayer buttonHover;

  @FXML
  Button backButton;

  @FXML
  Button standardModeButton;

  @FXML
  Button berserkerModeButton;

  @FXML
  Button survivalModeButton;

  @FXML
  Button confusingModeButton;

  /**
   * called when controller is initialised, sets the button hover effects
   */
  @FXML
  private void initialize() {
    setButtonHoverEffects();
  }

  /**
   * Sets the button hover effects
   */
  private void setButtonHoverEffects() {
    standardModeButton.setStyle("-fx-background-color:transparent;");

    standardModeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        standardModeButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    standardModeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        standardModeButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });

    berserkerModeButton.setStyle("-fx-background-color:transparent;");

    berserkerModeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        berserkerModeButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    berserkerModeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        berserkerModeButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });
    survivalModeButton.setStyle("-fx-background-color:transparent;");

    survivalModeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        survivalModeButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    survivalModeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        survivalModeButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });
    confusingModeButton.setStyle("-fx-background-color:transparent;");

    confusingModeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        confusingModeButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    confusingModeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        confusingModeButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });
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
   * sound effect for the button clicking
   */
  public void buttonClickSound() {
    String path = "src/audio/buttonClick.wav";
    Media music = new Media(Paths.get(path).toUri().toString());
    buttonClick = new MediaPlayer(music);
    buttonClick.play();
  }

  /**
   * sound effect for the button hovering
   */
  public void buttonHoverSound() {
    String path = "src/audio/buttonHover.wav";
    Media music = new Media(Paths.get(path).toUri().toString());
    buttonHover = new MediaPlayer(music);
    buttonHover.play();
  }

}
