package de.szut.msp_backend.item;

public abstract class GenericItem
{
    private final int itemID;
    private String displayName;
    private String description;
    private int standardValue;
    private final ItemType itemType;
    private Rarity rarity;

    public GenericItem(int itemID, String displayName, String description, int standardValue, ItemType itemType, Rarity rarity)
    {
        this.itemID = itemID;
        this.displayName = displayName;
        this.description = description;
        this.standardValue = standardValue;
        this.itemType = itemType;
        this.rarity = rarity;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getStandardValue()
    {
        return this.standardValue;
    }

    public void setStandardValue(int standardValue)
    {
        this.standardValue = standardValue;
    }

    public ItemType getItemType()
    {
        return this.itemType;
    }

    public Rarity getRarity()
    {
        return this.rarity;
    }

    public void setRarity(Rarity rarity)
    {
        this.rarity = rarity;
    }
}
