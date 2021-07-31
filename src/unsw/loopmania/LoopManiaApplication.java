package unsw.loopmania;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the main application run main method from this class
 */
public class LoopManiaApplication extends Application {
  // TODO = possibly add other menus?

  /**
   * the controller for the game. Stored as a field so can terminate it when click
   * exit button
   */
  private LoopManiaWorldController mainController;

  @Override
  public void start(Stage primaryStage) throws IOException {
    // set title on top of window bar
    primaryStage.setTitle("Loop Mania");

    // prevent human player resizing game window (since otherwise would see white
    // space)
    // alternatively, you could allow rescaling of the game (you'd have to program
    // resizing of the JavaFX nodes)
    primaryStage.setResizable(false);

    // load the main game
    LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader("world_with_twists_and_turns.json");
    mainController = loopManiaLoader.loadController();
    FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
    gameLoader.setController(mainController);
    Parent gameRoot = gameLoader.load();

    // load the main menu
    MainMenuController mainMenuController = new MainMenuController();
    FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
    menuLoader.setController(mainMenuController);
    Parent mainMenuRoot = menuLoader.load();

    // load the how to play menu
    HowToPlayController howToPlayController = new HowToPlayController();
    FXMLLoader howToPlayLoader = new FXMLLoader(getClass().getResource("Controls.fxml"));
    howToPlayLoader.setController(howToPlayController);
    Parent howToPlayRoot = howToPlayLoader.load();

    // load the map selection menu
    MapSelectionController mapSelectionController = new MapSelectionController();
    FXMLLoader mapSelectionLoader = new FXMLLoader(getClass().getResource("MapSelection.fxml"));
    mapSelectionLoader.setController(mapSelectionController);
    Parent mapSelectionRoot = mapSelectionLoader.load();

    // load the game mode menu
    GameModeController gameModeController = new GameModeController();
    FXMLLoader gameModeLoader = new FXMLLoader(getClass().getResource("GameModes.fxml"));
    gameModeLoader.setController(gameModeController);
    Parent gameModeRoot = gameModeLoader.load();

    // create new scene with the main menu (so we start with the main menu)
    Scene scene = new Scene(mainMenuRoot);

    // set functions which are activated when button click to switch menu is pressed
    // e.g. from main menu to start the game, or from the game to return to main
    // menu
    mainController.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });
    mainController.setGameSwitcher(() -> {
      switchToRoot(scene, gameRoot, primaryStage);
      mainController.startTimer();
    });
    mainMenuController.setHowToPlaySwitcher(() -> {
      switchToRoot(scene, howToPlayRoot, primaryStage);
    });
    mainMenuController.setGameModeSwitcher(() -> {
      switchToRoot(scene, gameModeRoot, primaryStage);
    });

    howToPlayController.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });

    mapSelectionController.setGameModeSwitcher(() -> {
      switchToRoot(scene, gameModeRoot, primaryStage);
    });
    mapSelectionController.setGameSwitcher(() -> {
      switchToRoot(scene, gameRoot, primaryStage);
      mainController.startTimer();
    });

    gameModeController.setMapSelectionSwitcher(() -> {
      switchToRoot(scene, mapSelectionRoot, primaryStage);
    });
    gameModeController.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });


    // deploy the main onto the stage
    gameRoot.requestFocus();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() {
    // wrap up activities when exit program
    mainController.terminate();
  }

  /**
   * switch to a different Root
   */
  private void switchToRoot(Scene scene, Parent root, Stage stage) {
    scene.setRoot(root);
    root.requestFocus();
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
