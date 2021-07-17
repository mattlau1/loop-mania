package unsw.loopmania.Shop;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.Items.Item;

public class Shop {

    private Character character;
    private List<ShopItem> shopItem;
    private List<Item> inventory;
    private ShopItem selectedItem;

    public Shop(Character character, List<Item> inventory) {
        this.character = character;
        this.shopItem = new ArrayList<ShopItem>();
        this.inventory = inventory;
        this.selectedItem = null;
    
    }

    public void buy() {
        // if theres room for unquipped inventory
        if (inventory.size() < 16) {
            // get the item from the menu
            Item newItem = selectedItem.getItem();
            int balance = character.getGold();
            int price = selectedItem.getPrice();
            // check if character has sufficient gold
            if (balance < price) {
                balance -= price;
                character.setGold(balance);
                inventory.add(newItem);
            }
        }
    }

    public void setSelectedItem(ShopItem item) {
        this.selectedItem = item;
    }

    public void addShopItem(ShopItem item) {
        shopItem.add(item);
    }

    public void exitShop() {
        // exit
    }

}
