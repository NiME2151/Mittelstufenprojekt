package de.szut.msp_backend.item;

public class Item extends GenericItem
{
    public Item(int itemID, String displayName, String description, int standardValue, Rarity rarity)
    {
        super(itemID, displayName, description, standardValue, ItemType.Item, rarity);
    }
    
}
