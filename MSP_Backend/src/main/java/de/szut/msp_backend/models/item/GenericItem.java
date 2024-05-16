package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;


// @Data gibt uns alle Getter und Setter ohne sie ausschreiben zu müssen
@Data
@EqualsAndHashCode
public abstract class GenericItem
{
    protected int itemID;
    protected String displayName;
    protected String description;
    protected int standardValue;
    protected ItemType itemType;
    protected Rarity rarity;

    protected GenericItem(int itemID, String displayName, String description, int standardValue, ItemType itemType, Rarity rarity)
    {
        this.itemID = itemID;
        this.displayName = displayName;
        this.description = description;
        this.standardValue = standardValue;
        this.itemType = itemType;
        this.rarity = rarity;
    }

    public GenericItem()
    {

    }

    @Override
    public String toString()
    {
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description  + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "}";
    }
}
