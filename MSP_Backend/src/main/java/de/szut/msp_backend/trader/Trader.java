package de.szut.msp_backend.trader;

import de.szut.msp_backend.inventory.Inventory;
import de.szut.msp_backend.item.GenericItem;

public class Trader
{
    private final String name;
    private int money;
    private Inventory wares;

    public Trader(final String name, final int money, final Inventory wares)
    {
        this.name = name;
        this.money = money;
        this.wares = wares;
    }

    public String getName()
    {
        return this.name;
    }

    public int getMoney()
    {
        return this.money;
    }

    public Inventory getWares()
    {
        return this.wares;
    }

    /**
     * The player buys a ware off of the trader.
     * @param ware The ware the player buys. This should be gotten inside the trading system, so it is ensured this item is in the inventory of the trader
     * @param cost The price per 1 Ware. It should be checked in trading system if the player has enough money
     */
    public void buyWare(GenericItem ware, int cost)
    {
        this.wares.removeItem(ware, 1);
        this.money += cost;
    }

    /**
     * The player sells a ware to the trader.
     * @param ware The ware the player sells. This should be gotten inside the trading system, so it is ensured this item is in the inventory of the trader
     * @param cost The price per 1 Ware. It should be checked in trading system if the trader has enough money
     */
    public void sellWare(GenericItem ware, int cost)
    {
        this.wares.appendItem(ware, 1);
        this.money -= cost;
    }
}