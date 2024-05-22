package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
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
        return super.toString() + String.format("Damage:\t\t\t%d\n", this.healthGain);
    }
}
