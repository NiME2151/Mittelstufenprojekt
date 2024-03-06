package de.szut.msp_backend.item;

public class Consumable extends GenericItem
{
    private final int healthGain;

    public Consumable(int itemID, String displayName, String description, double standardValue, Rarity rarity, int healthGain)
    {
        super(itemID, displayName, description, standardValue, ItemType.Consumable, rarity);
        this.healthGain = healthGain;
    }

    public int getHealthGain()
    {
        return this.healthGain;
    }
}
