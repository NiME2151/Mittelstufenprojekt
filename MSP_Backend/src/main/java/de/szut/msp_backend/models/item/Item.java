package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static de.szut.msp_backend.models.item.ItemType.ITEM;

@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends GenericItem
{
    /**
     * Creates an item.
     *
     * @param itemId        unique ID of the Item.
     * @param displayName   that the player sees while playing.
     * @param description   for the player, to know what this item can do.
     * @param standardValue price if the player wants to sell the item or buy it.
     * @param rarity        plays part in how often the item will appear in the game.
     */
    public Item(int itemId, String displayName, String description, int standardValue, Rarity rarity)
    {
        super(itemId, displayName, description, standardValue, ITEM, rarity);
    }

    @Override
    public String toString()
    {
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "\"}";
    }
}
