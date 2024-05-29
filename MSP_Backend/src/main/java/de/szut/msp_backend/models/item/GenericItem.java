package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


// @Data gibt uns alle Getter und Setter ohne sie ausschreiben zu müssen
@Data
@NoArgsConstructor
@EqualsAndHashCode
public abstract class GenericItem
{
    protected int itemID;
    protected String displayName;
    protected String description;
    protected int standardValue;
    protected ItemType itemType;
    protected Rarity rarity;

    /**
     * Creates a generic item.
     *
     * @param itemID        unique ID of the Item.
     * @param displayName   that the player sees while playing.
     * @param description   for the player, to know what this item can do.
     * @param standardValue price if the player wants to sell the item or buy it.
     * @param itemType      consumable, weapon or tradeItem
     * @param rarity        plays part in how often the item will appear in the game.
     */
    protected GenericItem(int itemID, String displayName, String description, int standardValue, ItemType itemType, Rarity rarity)
    {
        this.itemID = itemID;
        this.displayName = displayName;
        this.description = description;
        this.standardValue = standardValue;
        this.itemType = itemType;
        this.rarity = rarity;
    }

    @Override
    public String toString()
    {
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "}";
    }
}
