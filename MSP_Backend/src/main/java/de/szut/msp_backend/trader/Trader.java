package de.szut.msp_backend.trader;

import de.szut.msp_backend.inventory.Inventory;
import de.szut.msp_backend.item.GenericItem;
import lombok.Data;

@Data
public class Trader
{
    private final String name;
    private int money;
    private Inventory inventory;

    public Trader(final String name, final int money, final Inventory inventory)
    {
        this.name = name;
        this.money = money;
        this.inventory = inventory;
    }

    public String getName()
    {
        return this.name;
    }

    public int getMoney()
    {
        return this.money;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    /**
     * The player buys a ware off of the trader.
     * @param item The ware the player buys. This should be gotten inside the trading system, so it is ensured this item is in the inventory of the trader
     * @param price The price per 1 Ware. It should be checked in trading system if the player has enough money
     */
    public void buyItem(GenericItem item, int price)
    {
        this.inventory.removeItem(item, 1);
        this.money += price;
    }

    /**
     * The player sells a ware to the trader.
     * @param item The ware the player sells. This should be gotten inside the trading system, so it is ensured this item is in the inventory of the trader
     * @param price The price per 1 Ware. It should be checked in trading system if the trader has enough money
     */
    public void sellItem(GenericItem item, int price)
    {
        this.inventory.addItem(item, 1);
        this.money -= price;
    }
}
