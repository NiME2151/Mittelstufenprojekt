package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Consumable extends GenericItem
{
    private final int healthGain;

    /**
     * Creates a consumable, with which a player can heal itself or sell.
     *
     * @param itemID        unique ID of the Item.
     * @param displayName   that the player sees while playing.
     * @param description   for the player, to know what this item can do.
     * @param standardValue price if the player wants to sell the item or buy it.
     * @param rarity        plays part in how often the item will appear in the game.
     * @param healthGain    if the player needs to heal this param says how many healthpoints he will gain.
     */
    public Consumable(int itemID, String displayName, String description, int standardValue, Rarity rarity, int healthGain)
    {
        super(itemID, displayName, description, standardValue, ItemType.CONSUMABLE, rarity);
        this.healthGain = healthGain;
    }

    @Override
    public String toString()
    {
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "\", \"healthGain\":" + healthGain + "}";
    }
}
