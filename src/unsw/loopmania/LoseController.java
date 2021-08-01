package unsw.loopmania;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * controller for the main menu. TODO = you could extend this, for example with
 * a settings menu, or a menu to load particular maps.
 */
public class LoseController {
  private MenuSwitcher mainMenuSwitcher;
  MediaPlayer mainMenuMusic;
  MediaPlayer buttonClick;
  MediaPlayer buttonHover;

  @FXML
  Button exitButton;

  @FXML
  Button mainMenuButton;

  @FXML
  private void initialize() {
    music();
    setButtonHoverEffects();
  }

  /**
   * facilitates switching to main game
   *
   */
  public void setMainMenuSwitcher(MenuSwitcher gameSwitcher) {
    this.mainMenuSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToMainMenu() throws IOException {
    mainMenuSwitcher.switchMenu();
  }

  private void setButtonHoverEffects() {
    exitButton.setStyle("-fx-background-color:transparent;");

    exitButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        exitButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    exitButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        exitButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });

    mainMenuButton.setStyle("-fx-background-color:transparent;");
    mainMenuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        mainMenuButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    mainMenuButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        mainMenuButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });
  }

  @FXML
  private void exitGame() {
    System.exit(0);
  }

  /**
   * music for the main menu
   */
  public void music() {
    String path = "src/audio/MainMenuMusic.mp3";
    Media music = new Media(Paths.get(path).toUri().toString());
    mainMenuMusic = new MediaPlayer(music);
    mainMenuMusic.setOnEndOfMedia(new Runnable() {
      public void run() {
        mainMenuMusic.seek(Duration.ZERO);
      }
    });
    mainMenuMusic.play();
    mainMenuMusic.setVolume(0.1);
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
