package de.szut.msp_backend.models.item;

import de.szut.msp_backend.services.TradeService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TradeItem extends GenericItem
{
    private final TradeService tradeService = new TradeService();
    public TradeItem(int itemID, String displayName, String description, int standardValue, ItemType itemType, Rarity rarity)
    {
        super(itemID, displayName, description, standardValue, itemType, rarity);
        this.sellValue = this.tradeService.getSellValue(new GenericItem(itemID, displayName, description, standardValue, itemType, rarity)}
        this.buyValue = buyValue;
    }

    private int sellValue;
    private int buyValue;
    
}
