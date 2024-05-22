package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Consumable extends GenericItem
{
    private final int healthGain;

    public Consumable(int itemID, String displayName, String description, int standardValue, Rarity rarity, int healthGain)
    {
        super(itemID, displayName, description, standardValue, ItemType.CONSUMABLE, rarity);
        this.healthGain = healthGain;
    }

    @Override
    public String toString()
    {
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description  + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "\", \"healthGain\":" + healthGain + "}";
    }
}
