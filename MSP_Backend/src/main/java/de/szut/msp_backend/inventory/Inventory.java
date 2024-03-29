package de.szut.msp_backend.inventory;

import de.szut.msp_backend.item.GenericItem;

import java.util.HashMap;
import java.util.Map;

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

    public void appendItem(GenericItem item, int amount)
    {
        //if item already in inventory
        if (this.items.containsKey(item))
        {
            this.items.replace(item, this.items.get(item) + amount);
        }
        //if item not in inventory but still space in inventory
        else if (this.items.size() < maxSize)
        {
            this.items.put(item, amount);
        }
    }

    public void removeItem(GenericItem item, int amount)
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
    }
}
