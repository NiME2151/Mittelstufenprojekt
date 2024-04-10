package de.szut.msp_backend.tradesystem;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.inventory.Inventory;
import de.szut.msp_backend.item.GenericItem;
import lombok.Data;

@Data
public class Trader
{
    private final String name;
    private int money;
    private Inventory inventory;

    public Trader(final String name, final int money) {
        this.name = name;
        this.money = money;
        this.inventory = new Inventory(30);
    }
    
    /**
     * The player buys a ware off of the trader.
     * @param item The ware the player buys. 
     * @param price The price per 1 Item. 
     */
    public void playerBuysItem(GenericItem item, int price) throws ItemNotFoundException {
        if (inventory.isItemPresent(item)) {
            inventory.removeItem(item, 1);
            money += price;
        }
        throw new ItemNotFoundException();
    }

    /**
     * The player sells a ware to the trader.
     * @param item The ware the player sells. 
     * @param price The price per 1 Ware. 
     */
    public void playerSellsItem(GenericItem item, int price) {
        if (!inventory.isNotFull() && !inventory.isItemPresent(item)) {
            inventory.removeRandomItem();
        }
        inventory.addItem(item, 1);
        money -= price;
    }
}
