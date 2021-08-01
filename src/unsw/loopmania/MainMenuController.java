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
public class MainMenuController {
  private MenuSwitcher gameSwitcher;
  MediaPlayer mainMenuMusic;
  MediaPlayer buttonClick;
  MediaPlayer buttonHover;
  private MenuSwitcher howToPlaySwitcher;
  private MenuSwitcher gameModeSwitcher;

  @FXML
  Button exitButton;

  @FXML
  Button howToPlayButton;

  @FXML
  Button startGameButton;

  @FXML
  private void initialize() {
    music();
    setButtonHoverEffects();
  }

  /**
   * facilitates switching to main game
   */
  public void setGameSwitcher(MenuSwitcher gameSwitcher) {
    this.gameSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to how to play
   */
  public void setHowToPlaySwitcher(MenuSwitcher gameSwitcher) {
    this.howToPlaySwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection
   */
  public void setGameModeSwitcher(MenuSwitcher gameSwitcher) {
    this.gameModeSwitcher = gameSwitcher;
  }

  /**
   * facilitates switching to map selection upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGameMode() throws IOException {
    gameModeSwitcher.switchMenu();
  }

  /**
   * facilitates switching to main game upon button click
   *
   * @throws IOException
   */
  @FXML
  private void switchToGame() throws IOException {
    buttonClickSound();
    mainMenuMusic.stop();
    gameSwitcher.switchMenu();
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

    howToPlayButton.setStyle("-fx-background-color:transparent;");

    howToPlayButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        howToPlayButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    howToPlayButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        howToPlayButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
      }
    });
    startGameButton.setStyle("-fx-background-color:transparent;");

    startGameButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        buttonHoverSound();
        startGameButton.setStyle("-fx-background-color:#cccccc; -fx-text-fill: #333333");
      }
    });

    startGameButton.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        startGameButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white");
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
