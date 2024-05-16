package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static de.szut.msp_backend.models.item.ItemType.ITEM;

@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends GenericItem
{
    public Item(int itemId, String displayName, String description, int standardValue, Rarity rarity)
    {
        super(itemId, displayName, description, standardValue, ITEM, rarity);
    }

    @Override
    public String toString()
    {
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description  + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "}";
    }
}
