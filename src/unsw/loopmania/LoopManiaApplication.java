package unsw.loopmania;

import java.io.FileNotFoundException;
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
  private LoopManiaWorldController mainControllerGrass;
  private LoopManiaWorldController mainControllerJP;
  private LoopManiaWorldController mainControllerWaste;
  private static final String SURVIVAL_MODE = "Survival";
  private static final String BERSERKER_MODE = "Berserker";
  private static final String STANDARD_MODE = "Standard";
  private static final String CONFUSING_MODE = "Confusing";

  @Override
  public void start(Stage primaryStage) throws IOException {
    // set title on top of window bar
    primaryStage.setTitle("Loop Mania");

    // prevent human player resizing game window (since otherwise would see white
    // space)
    // alternatively, you could allow rescaling of the game (you'd have to program
    // resizing of the JavaFX nodes)
    primaryStage.setResizable(false);

    // load the main game (grass world)
    LoopManiaWorldControllerLoader loopManiaLoaderGrass = new LoopManiaWorldControllerLoader("world_with_twists_and_turns.json", "images");
    mainControllerGrass = loopManiaLoaderGrass.loadController();
    FXMLLoader gameLoaderGrass = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
    gameLoaderGrass.setController(mainControllerGrass);
    Parent gameRootGrass = gameLoaderGrass.load();

    // load the main game (japanese world)
    LoopManiaWorldControllerLoader loopManiaLoaderJP = new LoopManiaWorldControllerLoader("world_with_twists_and_turns.json", "images2");
    mainControllerJP = loopManiaLoaderJP.loadController();
    FXMLLoader gameLoaderJP = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
    gameLoaderJP.setController(mainControllerJP);
    Parent gameRootJP = gameLoaderJP.load();

    // load the main game (wasteland world)
    LoopManiaWorldControllerLoader loopManiaLoaderWaste = new LoopManiaWorldControllerLoader("world_with_twists_and_turns.json", "images2");
    mainControllerWaste = loopManiaLoaderWaste.loadController();
    FXMLLoader gameLoaderWaste = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
    gameLoaderWaste.setController(mainControllerWaste);
    Parent gameRootWaste = gameLoaderWaste.load();

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

    gameModeController.setMapSelectionStandardSwitcher(() -> {
      switchToRoot(scene, mapSelectionRoot, primaryStage);
      mainControllerGrass.setDifficulty(STANDARD_MODE);
      mainControllerJP.setDifficulty(STANDARD_MODE);
      mainControllerWaste.setDifficulty(STANDARD_MODE);
    });
    gameModeController.setMapSelectionBerserkerSwitcher(() -> {
      switchToRoot(scene, mapSelectionRoot, primaryStage);
      mainControllerGrass.setDifficulty(BERSERKER_MODE );
      mainControllerJP.setDifficulty(BERSERKER_MODE );
      mainControllerWaste.setDifficulty(BERSERKER_MODE );
    });
    gameModeController.setMapSelectionSurvivalSwitcher(() -> {
      switchToRoot(scene, mapSelectionRoot, primaryStage);
      mainControllerGrass.setDifficulty(SURVIVAL_MODE);
      mainControllerJP.setDifficulty(SURVIVAL_MODE);
      mainControllerWaste.setDifficulty(SURVIVAL_MODE);
    });
    gameModeController.setMapSelectionConfusingSwitcher(() -> {
      switchToRoot(scene, mapSelectionRoot, primaryStage);
      mainControllerGrass.setDifficulty(CONFUSING_MODE);
      mainControllerJP.setDifficulty(CONFUSING_MODE);
      mainControllerWaste.setDifficulty(CONFUSING_MODE);
    });

    mainControllerGrass.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });
    mainControllerGrass.setGameSwitcher(() -> {
      switchToRoot(scene, gameRootGrass, primaryStage);
      mainControllerGrass.startTimer();
    });

    mainControllerJP.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });
    mainControllerJP.setGameSwitcher(() -> {
      switchToRoot(scene, gameRootGrass, primaryStage);
      mainControllerJP.startTimer();
    });

    mainControllerWaste.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });
    mainControllerWaste.setGameSwitcher(() -> {
      switchToRoot(scene, gameRootWaste, primaryStage);
      mainControllerWaste.startTimer();
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
    mapSelectionController.setGameGrassSwitcher(() -> {
      switchToRoot(scene, gameRootGrass, primaryStage);
      mainControllerGrass.startTimer();
    });
    mapSelectionController.setGameWasteSwitcher(() -> {
      switchToRoot(scene, gameRootWaste, primaryStage);
      mainControllerWaste.startTimer();
    });
    mapSelectionController.setGameJPSwitcher(() -> {
      switchToRoot(scene, gameRootJP, primaryStage);
      mainControllerJP.startTimer();
    });

    gameModeController.setMainMenuSwitcher(() -> {
      switchToRoot(scene, mainMenuRoot, primaryStage);
    });

    // deploy the main onto the stage
    // gameRoot.requestFocus();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() {
    // wrap up activities when exit program
    mainControllerGrass.terminate();
    mainControllerJP.terminate();
    mainControllerWaste.terminate();
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
