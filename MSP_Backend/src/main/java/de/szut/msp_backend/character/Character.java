package de.szut.msp_backend.character;

import de.szut.msp_backend.inventory.Inventory;
import de.szut.msp_backend.item.Consumable;
import de.szut.msp_backend.item.GenericItem;
import lombok.Data;

@Data
public class Character
{
    private int healthPoints;
    //maxHealthPoints might need additional balancing
    private int maxHealthPoints;
    private int strength;
    private int luck;
    private int charisma;
    //TODO charisma bitte in Wert 0 bis 100 umwandeln
    private int money;
    private Inventory inventory;

    public Character(int healthPoints, int maxHealthPoints, int strength, int luck, int charisma, int money)
    {
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.strength = strength;
        this.luck = luck;
        this.charisma = charisma;
        this.money = money;
        inventory = new Inventory(strength);
    }

    public int getHealthPoints()
    {
        return healthPoints;
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

    public int getMoney()
    {
        return money;
    }

    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
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

    public void setMoney(int money)
    {
        this.money = money;
    }

    public void eat(Consumable consumable)
    {
        if (maxHealthPoints > healthPoints + consumable.getHealthGain())
        {
            healthPoints = healthPoints + consumable.getHealthGain();
        }
    }
    
    public int addMoney(int addMoney){
        this.money = money + addMoney;
        return money;
    };
    
    public int removeMoney(int subMoney){
        this.money = money - subMoney;
        return money;
    }

    public void addItemToInventory(GenericItem item, int amount)
    {
        inventory.addItem(item, amount);
    }

    public void removeItemFromInventory(GenericItem item, int amount)
    {
        inventory.removeItem(item, amount);
    }

    public Inventory getInventory()
    {
        return inventory;
    }
}
