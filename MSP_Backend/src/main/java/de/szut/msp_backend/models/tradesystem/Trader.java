package de.szut.msp_backend.models.tradesystem;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Data
public class Trader
{
    private final int traderID;
    private final String name;
    private int money;
    private Inventory inventory;

    public Trader(final int traderID, final String name, final int money)
    {
        this.traderID = traderID;
        this.name = name;
        this.money = money;
        this.inventory = new Inventory(30);
    }

    /**
     * The player buys a ware off of the trader.
     *
     * @param item  The ware the player buys.
     * @param price The price per 1 Item.
     */
    @Transactional
    public void playerBuysItem(final GenericItem item, final int price) throws ItemNotFoundException
    {
        if (inventory.isItemPresent(item))
        {
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
    @Transactional
    public boolean playerSellsItem(final GenericItem item, final int price)
    {
        if (!inventory.isNotFull() && !inventory.isItemPresent(item)) 
        {
            inventory.removeRandomItem();
        }
        if(price >= money){
            return false;
        }
        inventory.addItem(item, 1);
        money -= price;
        return true;
    }

    /**
     * This method shall fill the Inventory of the trader and is supposed to be called on initialize of the trader.
     *
     * @param itemsToPopulate the items and its amounts to be transferred into the inventory.
     */
    public void populateInventory(final Map<GenericItem, Integer> itemsToPopulate)
    {
        for (final GenericItem item : itemsToPopulate.keySet())
        {
            inventory.addItem(item, itemsToPopulate.get(item));
        }
    }
    
    public List<TradeItem> getAllTradeItems()
    {
        return inventory.getAllTradeItems();
    }
}
