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

  public boolean isNotFull()
  {
    return this.items.size() < maxSize;
  }

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
    } else
    {
      throw new ItemNotFoundException();
    }
  }

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

  public int getEmptySlots()
  {
    int emptySlots = 0;
    emptySlots = maxSize - items.size();
    return emptySlots;
  }

  public void removeRandomItem()
  {
    int number = items.size();
    int indexToRemove = (int) (Math.random() * number);
    ArrayList<GenericItem> listToDelete = new ArrayList<>(items.keySet());
    GenericItem toDelete = listToDelete.get(indexToRemove);
    this.items.remove(toDelete);
  }

  public boolean isItemPresent(GenericItem item)
  {
    return items.containsKey(item);
  }

  public void clearInventory()
  {
    items.clear();
  }

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
}
