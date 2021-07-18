package unsw.loopmania.Shop;

import unsw.loopmania.Items.Item;

public class ShopItem {

    private Item item;
    private int price;

    public ShopItem(Item item, int price) {
        this.item = item;
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
