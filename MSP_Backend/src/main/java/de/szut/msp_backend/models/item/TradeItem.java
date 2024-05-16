package de.szut.msp_backend.models.item;

import de.szut.msp_backend.models.tradesystem.Trade;
import lombok.Data;

@Data
public class TradeItem
{
    private int sellValue;
    private int buyValue;
    private int itemID;

    /**
     * A special item that is a part of the trading system
     * with extra parameters like sell value and buy value.
     * @param item with basic data.
     */
    public TradeItem(GenericItem item)
    {
        this.sellValue = fillSellValue(item);
        this.buyValue = fillBuyValue(item);
        this.itemID = item.getItemID();
    }

    /**
     * Sets the value with which in can be sold.
     * @param item with basic data.
     * @return the sell value that is set in the class trade.
     */
    public int fillSellValue(GenericItem item)
    {
        return Trade.getSellValue(item);
    }

    /**
     * Sets the value with which the item can be bought.
     * @param item with basic data.
     * @return the buy value that is set in the class trade.
     */
    public int fillBuyValue(GenericItem item)
    { 
        return Trade.getBuyValue(item); 
    } 
}

