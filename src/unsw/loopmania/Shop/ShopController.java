package unsw.loopmania.Shop;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import unsw.loopmania.LoopManiaWorldController;
import unsw.loopmania.MenuSwitcher;
import unsw.loopmania.Items.ArmourStrategy;
import unsw.loopmania.Items.HealthPotionStrategy;
import unsw.loopmania.Items.HelmetStrategy;
import unsw.loopmania.Items.ShieldStrategy;
import unsw.loopmania.Items.StaffStrategy;
import unsw.loopmania.Items.StakeStrategy;
import unsw.loopmania.Items.SwordStrategy;

public class ShopController {
  private MenuSwitcher gameSwitcher;
  private LoopManiaWorldController worldController;

  @FXML
  private Button buySwordButton;

  @FXML
  private Button buyStakeButton;

  @FXML
  private Button buyStaffButton;

  @FXML
  private Button buyHelmetButton;

  @FXML
  private Button buyArmourButton;

  @FXML
  private Button buyShieldButton;

  @FXML
  private Button buyPotionButton;

  @FXML
  private Label errorMessage;

  @FXML
  private Button exitShopButton;

  @FXML
  private void initialize() {
    errorMessage.setText("");
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

  public void setErrorMessage(String text) {
    errorMessage.setText(text);
  }

  @FXML
  private void buyArmour(ActionEvent event) {
    worldController.buyItem(new ArmourStrategy());
  }

  @FXML
  private void buyHelmet(ActionEvent event) {
    worldController.buyItem(new HelmetStrategy());
  }

  @FXML
  private void buyPotion(ActionEvent event) {
    worldController.buyItem(new HealthPotionStrategy());
  }

  @FXML
  private void buyShield(ActionEvent event) {
    worldController.buyItem(new ShieldStrategy());
  }

  @FXML
  private void buyStaff(ActionEvent event) {
    worldController.buyItem(new StaffStrategy());
  }

  @FXML
  private void buyStake(ActionEvent event) {
    worldController.buyItem(new StakeStrategy());
  }

  @FXML
  private void buySword(ActionEvent event) {
    worldController.buyItem(new SwordStrategy());
  }

  @FXML
  private void exitShop(ActionEvent event) throws IOException {
    worldController.exitShop();
  }

  public void getWorldController(LoopManiaWorldController worldController) {
    this.worldController = worldController;
  }

}
