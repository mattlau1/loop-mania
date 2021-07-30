package unsw.loopmania.Shop;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import unsw.loopmania.LoopManiaWorldController;
import unsw.loopmania.MenuSwitcher;
import unsw.loopmania.Items.AndurilStrategy;
import unsw.loopmania.Items.ArmourStrategy;
import unsw.loopmania.Items.DoggieCoinStrategy;
import unsw.loopmania.Items.HealthPotionStrategy;
import unsw.loopmania.Items.HelmetStrategy;
import unsw.loopmania.Items.ShieldStrategy;
import unsw.loopmania.Items.StaffStrategy;
import unsw.loopmania.Items.StakeStrategy;
import unsw.loopmania.Items.SwordStrategy;
import unsw.loopmania.Items.TheOneRingStrategy;
import unsw.loopmania.Items.TreeStumpStrategy;

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
  private Button sellDoggieCoinButton;

  @FXML
  private Button sellTheOneRingButton;

  @FXML
  private Button sellAndurilButton;

  @FXML
  private Button sellTreeStumpButton;

  @FXML
  private Button sellSwordButton;

  @FXML
  private Button sellStakeButton;

  @FXML
  private Button sellStaffButton;

  @FXML
  private Button sellHelmetButton;

  @FXML
  private Button sellArmourButton;

  @FXML
  private Button sellShieldButton;


  @FXML
  private Label buyErrorMessage;

  @FXML
  private Label sellErrorMessage;

  @FXML
  private Button exitShopButton;

  @FXML
  private void initialize() {
    buyErrorMessage.setText("");
    sellErrorMessage.setText("");
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

  public void setBuyErrorMessage(String text) {
    buyErrorMessage.setText(text);
  }

  public void setSellErrorMessage(String text) {
    sellErrorMessage.setText(text);
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
  private void sellArmour(ActionEvent event) {
    worldController.sellItem(ArmourStrategy.class);
  }

  @FXML
  private void sellHelmet(ActionEvent event) {
    worldController.sellItem(HelmetStrategy.class);
  }

  @FXML
  private void sellDoggieCoin(ActionEvent event) {
    worldController.sellItem(DoggieCoinStrategy.class);
  }

  @FXML
  private void sellAnduril(ActionEvent event) {
    worldController.sellItem(AndurilStrategy.class);
  }

  @FXML
  private void sellTheOneRing(ActionEvent event) {
    worldController.sellItem(TheOneRingStrategy.class);
  }

  @FXML
  private void sellTreeStump(ActionEvent event) {
    worldController.sellItem(TreeStumpStrategy.class);
  }

  @FXML
  private void sellShield(ActionEvent event) {
    worldController.sellItem(ShieldStrategy.class);
  }

  @FXML
  private void sellStaff(ActionEvent event) {
    worldController.sellItem(StaffStrategy.class);
  }

  @FXML
  private void sellStake(ActionEvent event) {
    worldController.sellItem(StakeStrategy.class);
  }

  @FXML
  private void sellSword(ActionEvent event) {
    worldController.sellItem(SwordStrategy.class);
  }

  @FXML
  private void exitShop(ActionEvent event) throws IOException {
    worldController.exitShop();
  }

  public void setWorldController(LoopManiaWorldController worldController) {
    this.worldController = worldController;
  }

}
