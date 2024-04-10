package de.szut.msp_backend.inventory;

import de.szut.msp_backend.item.GenericItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    
    public boolean isNotFull() {
        return this.items.size() < maxSize;
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

    public void removeRandomItem(){
        int number = items.size();
        int indexToRemove = (int)(Math.random() * number);
        ArrayList<GenericItem> listToDelete = new ArrayList<>(items.keySet());
        GenericItem toDelete = listToDelete.get(indexToRemove);
        this.items.remove(toDelete);
    }

    public boolean isItemPresent(GenericItem item){
        return items.containsKey(item);
    }
}
