package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
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
        return "{" + "\"itemID\":" + itemID + ", \"displayName\":\"" + displayName + "\", \"description\":\"" + description  + "\", \"standardValue\":" + standardValue + ", \"itemType\":\"" + itemType + "\", \"rarity\":\"" + rarity + "\", \"damage\":" + damage + "}";
    }
}
