package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.experimental.SuperBuilder;


// @Data gibt uns alle Getter und Setter ohne sie ausschreiben zu müssen
@Data
@SuperBuilder
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

    @Override
    public String toString()
    {
        return String.format("itemID:\t\t\t%d\nName:\t\t\t%s\nDescription:\t%s\nValue:\t\t\t%d\nType:\t\t\t%s\nRarity:\t\t\t%s\n", itemID, displayName, description, standardValue, itemType, rarity);
    }
}
