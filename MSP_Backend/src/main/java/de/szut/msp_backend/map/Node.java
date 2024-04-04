package de.szut.msp_backend.map;

import de.szut.msp_backend.item.GenericItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node
{
    private final String description;
    private List<GenericItem> findableItems;
    private Map<Direction, Node> neighbourMap;
    private final String itemLootTableName;
    private final String entityLootTableName;

    public Node(String description, String itemLootTableName, String entityLootTableName)
    {
        this.description = description;
        this.findableItems = new ArrayList<>();
        this.neighbourMap = new HashMap<>();
        this.itemLootTableName = itemLootTableName;
        this.entityLootTableName = entityLootTableName;
    }

    public String getDescription()
    {
        return description;
    }

    public List<GenericItem> getFindableItems()
    {
        return findableItems;
    }

    public void addFindableItem(GenericItem findableItem)
    {
        this.findableItems.add(findableItem);
    }

    public void addNeighbour(Direction direction, Node neighbour)
    {
        this.neighbourMap.put(direction, neighbour);
    }

    public Node getNeighbour(Direction direction)
    {
        return this.neighbourMap.getOrDefault(direction, null);
    }

    public String getItemLootTableName()
    {
        return itemLootTableName;
    }

    public String getEntityLootTableName()
    {
        return entityLootTableName;
    }

    public void pickupItem(GenericItem item)
    {
        findableItems.remove(item);
    }
}
