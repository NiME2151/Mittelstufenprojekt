package de.szut.msp_backend.models.character;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
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
    //TODO charisma hier bitte als Wert zwischen 0 bis 10 halten
    private int money;
    private Inventory inventory;
    
    public void addMoney(int addMoney)
    {
        this.money = money + addMoney;
    }
    
    private void removeMoney(int subMoney)
    {
        this.money = money - subMoney;
    }
    
    public void eat(Consumable consumable)
    {
        if (maxHealthPoints > healthPoints + consumable.getHealthGain())
        {
            healthPoints = healthPoints + consumable.getHealthGain();
        }
    }

    public void buyItemFromTrader(GenericItem item, int price) {
        removeMoney(price);
        addItemToInventory(item, 1);
    }

    public void sellItemToTrader(GenericItem item, int price) {
        addMoney(price);
        removeItemFromInventory(item, 1);
    }

    public void addItemToInventory(GenericItem item, int amount)
    {
        inventory.addItem(item, amount);
    }

    public void removeItemFromInventory(GenericItem item, int amount)
    {
        try
        {
            inventory.removeItem(item, amount);
        }
        catch (ItemNotFoundException itemNotFoundException)
        {
        }

    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void clearInventory()
    {
        inventory.clearInventory();
    }
}
