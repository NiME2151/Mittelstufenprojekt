package de.szut.msp_backend.models.character;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@AllArgsConstructor
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

    /**
     * Money that gets added to the player's money.
     *
     * @param addMoney the amount that gets added
     */
    public void addMoney(int addMoney)
    {
        this.money = money + addMoney;
    }

    /**
     * Removes money from the player
     *
     * @param subMoney the money amount that gets removed
     */
    public void removeMoney(int subMoney)
    {
        this.money = money - subMoney;
    }

    /**
     * This method adds healtpoints by eating.
     *
     * @param consumable the consumable with a healthpoint value that would add to the players healthpoints
     * @return the healpoints added to the player's health, not higher than maxHealthpoints.
     */
    public int eat(Consumable consumable)
    {
        if (maxHealthPoints > healthPoints + consumable.getHealthGain())
        {
            healthPoints = healthPoints + consumable.getHealthGain();
            return healthPoints;
        }
        healthPoints = maxHealthPoints;
        return healthPoints;
    }

    /**
     * Player can sell an item to a trader.
     *
     * @param item  the item that gets sold.
     * @param price the money the player gets for selling.
     * @throws ItemNotFoundException if the item is not found.
     */
    @Transactional
    public void sellItemToTrader(GenericItem item, int price) throws ItemNotFoundException
    {
        removeItemFromInventory(item, 1);
        addMoney(price);
    }

    /**
     * An item can be bought from a trader by the player with money,
     * if the inventory has space for the item and the player has enough money.
     *
     * @param item  the item the trader sells
     * @param price the costs for the item
     * @return BuyItemResponse for different occasions (NOTENOUGHMONEY, NOTENOUGHSPACE, OK)
     */
    @Transactional
    public Enum<BuyItemResponse> buyItemFromTrader(GenericItem item, int price)
    {
        if (this.money < price)
        {
            return BuyItemResponse.NOT_ENOUGH_MONEY;
        }
        if (!inventory.isNotFull())
        {
            return BuyItemResponse.NOT_ENOUGH_SPACE;
        }
        removeMoney(price);
        addItemToInventory(item, 1);
        return BuyItemResponse.OK;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item   the item that gets added
     * @param amount the amount of the added item.
     */
    public void addItemToInventory(GenericItem item, int amount)
    {
        inventory.addItem(item, amount);
    }

    /**
     * Removes chosen item from player's inventory.
     *
     * @param item   the item from the inventory that gets removed.
     * @param amount the amount of the item that gets removed,
     *               if the amount is equal or less than the item amount in the inventory.
     * @throws ItemNotFoundException if the item is not in the inventory.
     */
    public void removeItemFromInventory(GenericItem item, int amount) throws ItemNotFoundException
    {
        if (!inventory.isItemPresent(item) || (inventory.isItemPresent(item) && inventory.getItems().get(item) < amount))
        {
            throw new ItemNotFoundException();
        }
        inventory.removeItem(item, amount);
    }

    /**
     * clears the inventory.
     */
    public void clearInventory()
    {
        inventory.clearInventory();
    }
}
