package de.szut.msp_backend.character;

import de.szut.msp_backend.inventory.Inventory;
import de.szut.msp_backend.item.Consumable;
import de.szut.msp_backend.item.GenericItem;

public class Character
{
    private int healthPoints;
    //maxHealthPoints might need additional balancing
    private int maxHealthPoints;
    private int strength;
    private int luck;
    private int charisma;
    private int gold;
    private Inventory inventory;

    public Character(int healthPoints, int maxHealthPoints, int strength, int luck, int charisma, int gold)
    {
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.strength = strength;
        this.luck = luck;
        this.charisma = charisma;
        this.gold = gold;
        inventory = new Inventory(strength);
    }

    public int getHealthPoints()
    {
        return healthPoints;
    }

    public int getMaxHealthPoints()
    {
        return maxHealthPoints; 
    }

    public int getStrength()
    {
        return strength;
    }

    public int getLuck()
    {
        return luck;
    }

    public int getCharisma()
    {
        return charisma;
    }

    public int getGold()
    {
        return gold;
    }

    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) 
    {
        this.maxHealthPoints = maxHealthPoints;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public void setLuck(int luck)
    {
        this.luck = luck;
    }

    public void setCharisma(int charisma)
    {
        this.charisma = charisma;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public void eat(Consumable consumable)
    {
        if (maxHealthPoints > healthPoints + consumable.getHealthGain())
        {
            healthPoints = healthPoints + consumable.getHealthGain();
        }
    }

    public void addItemToInventory(GenericItem item, int amount)
    {
        inventory.appendItem(item, amount);
    }

    public void removeItemInInventory(GenericItem item, int amount)
    {
        inventory.removeItem(item, amount);
    }

    public Inventory getInventory()
    {
        return inventory;
    }
}
