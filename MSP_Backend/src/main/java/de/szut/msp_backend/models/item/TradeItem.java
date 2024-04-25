package de.szut.msp_backend.models.item;

import de.szut.msp_backend.models.tradesystem.Trade;
import lombok.Data;

@Data
public class TradeItem
{
    private int sellValue;
    private int buyValue;
    private int itemID;

    public TradeItem(GenericItem item)
    {
        this.sellValue = fillSellValue(item);
        this.buyValue = fillBuyValue(item);
        this.itemID = item.getItemID();
    }

    public int fillSellValue(GenericItem item)
    {
        return Trade.getSellValue(item);
    }
    public int fillBuyValue(GenericItem item)
    { 
        return Trade.getBuyValue(item); 
    } 
}

