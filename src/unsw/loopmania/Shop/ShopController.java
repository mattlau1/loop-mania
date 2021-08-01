package unsw.loopmania.Shop;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import unsw.loopmania.LoopManiaWorld;
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
  MediaPlayer buttonClick;

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
  private Button buyPotionButton;

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
  private Button craftSwordButton;

  @FXML
  private Button craftStakeButton;

  @FXML
  private Button craftStaffButton;

  @FXML
  private Button craftHelmetButton;

  @FXML
  private Button craftArmourButton;

  @FXML
  private Button craftShieldButton;

  @FXML
  private ImageView swordSellShopImage;

  @FXML
  private ImageView stakeSellShopImage;

  @FXML
  private ImageView staffSellShopImage;

  @FXML
  private ImageView shieldSellShopImage;

  @FXML
  private ImageView helmetSellShopImage;

  @FXML
  private ImageView armourSellShopImage;

  @FXML
  private ImageView theOneRingSellShopImage;

  @FXML
  private ImageView andurilSellShopImage;

  @FXML
  private ImageView treeStumpSellShopImage;

  @FXML
  private ImageView doggieCoinSellShopImage;

  @FXML
  private ImageView swordBuyShopImage;

  @FXML
  private ImageView stakeBuyShopImage;

  @FXML
  private ImageView staffBuyShopImage;

  @FXML
  private ImageView shieldBuyShopImage;

  @FXML
  private ImageView helmetBuyShopImage;

  @FXML
  private ImageView armourBuyShopImage;

  @FXML
  private ImageView potionBuyShopImage;

  @FXML
  private ImageView swordCraftShopImage;

  @FXML
  private ImageView stakeCraftShopImage;

  @FXML
  private ImageView staffCraftShopImage;

  @FXML
  private ImageView shieldCraftShopImage;

  @FXML
  private ImageView helmetCraftShopImage;

  @FXML
  private ImageView armourCraftShopImage;

  @FXML
  private Label buyErrorMessage;

  @FXML
  private Label craftErrorMessage;

  @FXML
  private Label sellErrorMessage;

  @FXML
  private Button exitShopButton;

  private int potionPurchaseCount;
  private int protectiveGearPurchaseCount;

  @FXML
  private void initialize() {
    buyErrorMessage.setText("");
    sellErrorMessage.setText("");
    craftErrorMessage.setText("");
    this.potionPurchaseCount = 0;
    this.protectiveGearPurchaseCount = 0;
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

  public void setCraftErrorMessage(String text) {
    craftErrorMessage.setText(text);
  }

  public void setSellErrorMessage(String text) {
    sellErrorMessage.setText(text);
  }

  @FXML
  private void buyArmour(ActionEvent event) {
    LoopManiaWorld world = worldController.getWorld();
    if (world.difficultyEquals(LoopManiaWorld.BERSERKER_MODE) && protectiveGearPurchaseCount > 0) {
      buyErrorMessage
          .setText("You can only purchase 1 piece of protective gear every time you shop in berserker mode!");
      return;
    }
    worldController.buyItem(new ArmourStrategy());
    protectiveGearPurchaseCount++;
  }

  @FXML
  private void buyHelmet(ActionEvent event) {
    LoopManiaWorld world = worldController.getWorld();
    if (world.difficultyEquals(LoopManiaWorld.BERSERKER_MODE) && protectiveGearPurchaseCount > 0) {
      buyErrorMessage
          .setText("You can only purchase 1 piece of protective gear every time you shop in berserker mode!");
      return;
    }
    worldController.buyItem(new HelmetStrategy());
    protectiveGearPurchaseCount++;
  }

  @FXML
  private void buyPotion(ActionEvent event) {
    LoopManiaWorld world = worldController.getWorld();
    if (world.difficultyEquals(LoopManiaWorld.SURVIVAL_MODE) && potionPurchaseCount > 0) {
      buyErrorMessage.setText("You can only purchase 1 potion in survival mode!");
      return;
    }
    world.buyItem(new HealthPotionStrategy());
    world.consumePotion();
    potionPurchaseCount++;
  }

  @FXML
  private void buyShield(ActionEvent event) {
    LoopManiaWorld world = worldController.getWorld();
    if (world.difficultyEquals(LoopManiaWorld.BERSERKER_MODE) && protectiveGearPurchaseCount > 0) {
      buyErrorMessage
          .setText("You can only purchase 1 piece of protective gear every time you shop in berserker mode!");
      return;
    }
    worldController.buyItem(new ShieldStrategy());
    protectiveGearPurchaseCount++;
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
    worldController.sellDoggieCoin();
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
  private void craftArmour(ActionEvent event) {
    worldController.craftItem(new ArmourStrategy());
  }

  @FXML
  private void craftHelmet(ActionEvent event) {
    worldController.craftItem(new HelmetStrategy());
  }

  @FXML
  private void craftShield(ActionEvent event) {
    worldController.craftItem(new ShieldStrategy());
  }

  @FXML
  private void craftStaff(ActionEvent event) {
    worldController.craftItem(new StaffStrategy());
  }

  @FXML
  private void craftStake(ActionEvent event) {
    worldController.craftItem(new StakeStrategy());
  }

  @FXML
  private void craftSword(ActionEvent event) {
    worldController.craftItem(new SwordStrategy());
  }

  @FXML
  private void exitShop(ActionEvent event) throws IOException {
    worldController.exitShop();
    this.protectiveGearPurchaseCount = 0;
    buttonClickSound();
  }

  public void setWorldController(LoopManiaWorldController worldController) {
    this.worldController = worldController;
  }

  public void setImages() {
    swordSellShopImage.setImage((new SwordStrategy()).getImage(worldController.getImgLoc()).getImage());
    shieldSellShopImage.setImage((new ShieldStrategy()).getImage(worldController.getImgLoc()).getImage());
    stakeSellShopImage.setImage((new StakeStrategy()).getImage(worldController.getImgLoc()).getImage());
    staffSellShopImage.setImage((new StaffStrategy()).getImage(worldController.getImgLoc()).getImage());
    helmetSellShopImage.setImage((new HelmetStrategy()).getImage(worldController.getImgLoc()).getImage());
    armourSellShopImage.setImage((new ArmourStrategy()).getImage(worldController.getImgLoc()).getImage());
    theOneRingSellShopImage.setImage((new TheOneRingStrategy()).getImage(worldController.getImgLoc()).getImage());
    andurilSellShopImage.setImage((new AndurilStrategy()).getImage(worldController.getImgLoc()).getImage());
    treeStumpSellShopImage.setImage((new TreeStumpStrategy()).getImage(worldController.getImgLoc()).getImage());
    doggieCoinSellShopImage.setImage((new DoggieCoinStrategy()).getImage(worldController.getImgLoc()).getImage());

    swordBuyShopImage.setImage((new SwordStrategy()).getImage(worldController.getImgLoc()).getImage());
    shieldBuyShopImage.setImage((new ShieldStrategy()).getImage(worldController.getImgLoc()).getImage());
    stakeBuyShopImage.setImage((new StakeStrategy()).getImage(worldController.getImgLoc()).getImage());
    staffBuyShopImage.setImage((new StaffStrategy()).getImage(worldController.getImgLoc()).getImage());
    helmetBuyShopImage.setImage((new HelmetStrategy()).getImage(worldController.getImgLoc()).getImage());
    armourBuyShopImage.setImage((new ArmourStrategy()).getImage(worldController.getImgLoc()).getImage());
    potionBuyShopImage.setImage((new HealthPotionStrategy()).getImage(worldController.getImgLoc()).getImage());

    swordCraftShopImage.setImage((new SwordStrategy()).getImage(worldController.getImgLoc()).getImage());
    shieldCraftShopImage.setImage((new ShieldStrategy()).getImage(worldController.getImgLoc()).getImage());
    stakeCraftShopImage.setImage((new StakeStrategy()).getImage(worldController.getImgLoc()).getImage());
    staffCraftShopImage.setImage((new StaffStrategy()).getImage(worldController.getImgLoc()).getImage());
    helmetCraftShopImage.setImage((new HelmetStrategy()).getImage(worldController.getImgLoc()).getImage());
    armourCraftShopImage.setImage((new ArmourStrategy()).getImage(worldController.getImgLoc()).getImage());

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

}
