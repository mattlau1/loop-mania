package unsw.loopmania.Shop;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import unsw.loopmania.MenuSwitcher;

public class ShopController {
  private MenuSwitcher gameSwitcher;

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

  @FXML
  void buyArmour(ActionEvent event) {

  }

  @FXML
  void buyHelmet(ActionEvent event) {

  }

  @FXML
  void buyPotion(ActionEvent event) {

  }

  @FXML
  void buyShield(ActionEvent event) {

  }

  @FXML
  void buyStaff(ActionEvent event) {

  }

  @FXML
  void buyStake(ActionEvent event) {

  }

  @FXML
  void buySword(ActionEvent event) {

  }

  @FXML
  void exitShop(ActionEvent event) {

  }
}
