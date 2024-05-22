package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Weapon extends GenericItem
{
  private final int damage;

    public Weapon(int itemID, String displayName, String description, int standardValue, Rarity rarity, int damage)
    {
        super(itemID, displayName, description, standardValue, ItemType.WEAPON, rarity);
        this.damage = damage;
    }

    @Override
    public String toString()
    {
        return super.toString() + String.format("Damage:\t\t\t%d\n", this.damage);
    }
}
