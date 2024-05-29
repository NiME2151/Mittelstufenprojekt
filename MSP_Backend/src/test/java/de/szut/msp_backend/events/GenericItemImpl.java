package de.szut.msp_backend.events;

import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.Rarity;

public class GenericItemImpl extends GenericItem
{
    public GenericItemImpl()
    {
        super(69, "TestItem", "TestItemDescription", 0, ItemType.ITEM, Rarity.LEGENDARY);
    }

    public static GenericItem clone(GenericItem item)
    {
        GenericItem clone = new GenericItemImpl();
        clone.setItemID(item.getItemID());
        clone.setDescription(item.getDescription());
        clone.setRarity(item.getRarity());
        clone.setItemType(item.getItemType());
        clone.setDisplayName(item.getDisplayName());
        clone.setStandardValue(item.getStandardValue());
        return clone;
    }
}
