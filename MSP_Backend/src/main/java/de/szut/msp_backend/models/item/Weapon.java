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

    /**
     * Creates a weapon which adds attackpoints to the players strength.
     * @param itemID unique ID of the Item.
     * @param displayName that the player sees while playing.
     * @param description for the player, to know what this item can do.
     * @param standardValue price if the player wants to sell the item or buy it.
     * @param rarity plays part in how often the item will appear in the game.
     * @param damage that gets added to the plyers strength in a fight.
     */
    public Weapon(int itemID, String displayName, String description, int standardValue, Rarity rarity, int damage)
    {
        super(itemID, displayName, description, standardValue, ItemType.WEAPON, rarity);
        this.damage = damage;
    }
}
