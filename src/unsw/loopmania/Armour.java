package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Armour extends StaticEntity {
    // TODO = add more weapon/item types
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
