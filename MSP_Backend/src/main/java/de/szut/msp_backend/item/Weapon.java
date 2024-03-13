package de.szut.msp_backend.item;

public class Weapon extends GenericItem
{
    private final int damage;

    public Weapon(int itemID, String displayName, String description, double standardValue, Rarity rarity, int damage)
    {
        super(itemID, displayName, description, standardValue, ItemType.Weapon, rarity);
        this.damage = damage;
    }

    public int getDamage()
    {
        return this.damage;
    }
}
