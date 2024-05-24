package de.szut.msp_backend.models.inventory;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.TradeItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inventory
{
    private final Map<GenericItem, Integer> items;
    private int maxSize;

    public Inventory(int size)
    {
        this.items = new HashMap<>(maxSize);
        this.maxSize = size;
    }

    public int getMaxSize()
    {
        return this.maxSize;
    }

    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
    }

    /**
     * Adds item into inventory if not full or can be stacked.
     *
     * @param item   to add.
     * @param amount of the item.
     */
    public void addItem(GenericItem item, int amount)
    {
        //if item already in inventory
        if (this.items.containsKey(item))
        {
            this.items.replace(item, this.items.get(item) + amount);
        }
        //if item not in inventory but still space in inventory
        else if (isNotFull())
        {
            this.items.put(item, amount);
        }
    }

    /**
     * To check if inventory has space.
     *
     * @return true if inventory has space. False, if full.
     */
    public boolean isNotFull()
    {
        return this.items.size() < maxSize;
    }

    /**
     * To remove an item from the inventory.
     *
     * @param item   that gets removed.
     * @param amount of the item that gets removed.
     *               If the amount is higher than the item amount the item gets removed.
     * @throws ItemNotFoundException if item is not in the inventory.
     */
    public void removeItem(GenericItem item, int amount) throws ItemNotFoundException
    {
        //if item already in inventory
        if (this.items.containsKey(item))
        {
            //if there is still some amount of the item left after removing it
            if (this.items.get(item) - amount > 0)
            {
                this.items.put(item, this.items.get(item) - amount);
            }
            //if this item will no longer be in inventory
            else
            {
                this.items.remove(item);
            }
        }
        else
        {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Removes item by ID.
     *
     * @param itemID of the item that gets removed.
     * @param amount of the item that gets removed.
     *               If the amount is higher than the item amount the item gets removed.
     * @throws ItemNotFoundException if item with the ID is not in inventory.
     */
    public void removeItem(final int itemID, final int amount) throws ItemNotFoundException
    {
        for (GenericItem item : items.keySet())
        {
            if (item.getItemID() == itemID)
            {
                //if there is still some amount of the item left after removing it
                if (this.items.get(item) - amount > 0)
                {
                    this.items.put(item, this.items.get(item) - amount);
                }
                //if this item will no longer be in inventory
                else
                {
                    this.items.remove(item);
                }
                return;
            }
        }

        throw new ItemNotFoundException("The given ItemId does not match any given item in this inventory.");
    }

    /**
     * Gets Item of a specific type f.e. "weapon".
     *
     * @param type the type weapon, consumable or tradeItem.
     * @return a list of items of that type.
     */
    public List<GenericItem> getItemsOfType(ItemType type)
    {
        List<GenericItem> itemsOfSameType = new ArrayList<GenericItem>();
        for (GenericItem inventoryItem : items.keySet())
        {
            if (inventoryItem.getItemType() == type)
            {
                itemsOfSameType.add(inventoryItem);
            }
        }
        return itemsOfSameType;
    }

    /**
     * Checks for empty slots.
     *
     * @return the number of empty slots in the inventory.
     */
    public int getEmptySlots()
    {
        int emptySlots = 0;
        emptySlots = maxSize - items.size();
        return emptySlots;
    }

    /**
     * Deletes a randomly chosen item from the inventory.
     */
    public void removeRandomItem()
    {
        int number = items.size();
        int indexToRemove = (int) (Math.random() * number);
        ArrayList<GenericItem> listToDelete = new ArrayList<>(items.keySet());
        GenericItem toDelete = listToDelete.get(indexToRemove);
        this.items.remove(toDelete);
    }

    /**
     * Check if Item is in inventory.
     *
     * @param item that gets checked.
     * @return true, if item im present. False, if not.
     */
    public boolean isItemPresent(GenericItem item)
    {
        return items.containsKey(item);
    }

    /**
     * Clears the inventory.
     */
    public void clearInventory()
    {
        items.clear();
    }

    /**
     * Gets Item of specific type tradeItem.
     *
     * @return a list of items of type tradeItem.
     */
    public List<TradeItem> getAllTradeItems()
    {
        List<TradeItem> tradeItems = new ArrayList<>();
        items.forEach((k, v) ->
        {
            for (int i = 0; i < v; i++)
            {
                tradeItems.add(new TradeItem(k));
            }
        });
        return tradeItems;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Size:\t\t\t");
        sb.append(maxSize);
        sb.append("\n");
        sb.append("Items:\n");
        for (GenericItem item : this.items.keySet())
        {
            sb.append(item);
            sb.append("\nAmount:\t\t\t");
            sb.append(this.items.get(item));
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
